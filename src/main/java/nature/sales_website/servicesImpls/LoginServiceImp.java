package nature.sales_website.servicesImpls;

import nature.sales_website.data.CustomUserDetails;
import nature.sales_website.data.LoginRequest;
import nature.sales_website.dto.UserDto;
import nature.sales_website.entity.User;
import nature.sales_website.jwt.JwtTokenProvider;
import nature.sales_website.models.response.AccessTokenResponse;
import nature.sales_website.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class LoginServiceImp {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    public AccessTokenResponse loginAuthenticate( LoginRequest request, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal(), false);
        String refresh_jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal(), true);

        User authUserInfo = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        UserDto userDto = new UserDto();
        userDto.setId(authUserInfo.getId());
        userDto.setFullName(authUserInfo.getFullName());
        userDto.setUserName(authUserInfo.getUserName());
        userDto.setRoleSet(authUserInfo.getRoleSet().stream().map(role->role.getName()).collect(Collectors.toSet()));
        // Save Refresh Token in HttpOnly Cookie
        Cookie refreshCookie = new Cookie("refreshToken", refresh_jwt);
        refreshCookie.setHttpOnly(true); // Protection from JavaScript
//        refreshCookie.setSecure(true); // only use in HTTPS
        refreshCookie.setSecure(false); // only use in local dev
        refreshCookie.setPath("/api/refresh-token"); // limit use url, only return cookie with the path
//        refreshCookie.setPath("/"); attach cookie for all the path
//        refreshCookie.setMaxAge(7 * 24 * 60 * 60); // expiration seven days
        refreshCookie.setMaxAge(1 * 24 * 60 * 60); // expiration one day
        response.addCookie(refreshCookie);
        // spring automatically send to front-end

        return new AccessTokenResponse(jwt, "true", userDto);
    }

    public AccessTokenResponse refreshToken(HttpServletRequest request){
         String refreshToken = getRefreshTokenFromCookie(request);

         if (refreshToken ==null){
             throw new RuntimeException("Refresh token is null");
         }

          boolean isValid = checkValidateRefreshToken(refreshToken);
          if (!isValid){
              throw new RuntimeException("Refresh token is not valid");
          }

          Long userId = jwtTokenProvider.getUserIdFromJWT(refreshToken);

          CustomUserDetails customUserDetails = loadUserById(userId);
          if (customUserDetails == null){
              throw new RuntimeException("User not found!");
          }

          String newJwtToken = jwtTokenProvider.generateToken(customUserDetails, false);
          return new AccessTokenResponse(newJwtToken, "true", null);
    }
    @Transactional
    public CustomUserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return new CustomUserDetails(user);
    }

    public boolean checkValidateToken(HttpServletRequest request){
        String jwt = getJwtFromRequest(request);
        return jwtTokenProvider.validateToken(jwt);
    }

    public boolean checkValidateRefreshToken(String refreshToken){
        return jwtTokenProvider.validateToken(refreshToken);
    }

    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getRefreshTokenFromCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        String refreshToken = null;
        for (Cookie cookie : cookies){
            if ("refreshToken".equals(cookie.getName())) {
                refreshToken = cookie.getValue();
                break;
            }
        }
        return refreshToken;
    }
}
