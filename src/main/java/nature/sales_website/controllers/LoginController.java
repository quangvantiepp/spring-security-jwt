package nature.sales_website.controllers;

import nature.sales_website.data.CustomUserDetails;
import nature.sales_website.data.LoginRequest;
import nature.sales_website.dto.UserDto;
import nature.sales_website.entity.User;
import nature.sales_website.jwt.JwtTokenProvider;
import nature.sales_website.models.response.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public AccessTokenResponse authenticateUser(@Valid @RequestBody LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        User authUserInfo = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        UserDto userDto = new UserDto();
        userDto.setId(authUserInfo.getId());
        userDto.setFullName(authUserInfo.getFullName());
        userDto.setUserName(authUserInfo.getUserName());
        userDto.setRoleSet(authUserInfo.getRoleSet().stream().map(role->role.getName()).collect(Collectors.toSet()));

        return new AccessTokenResponse(jwt, userDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        // Logout simply deletes the client-side (Frontend) token.
        // Response logout
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/random")
    public String randomStuff(){
        return ("JWT is good to access this page");
    }

}
