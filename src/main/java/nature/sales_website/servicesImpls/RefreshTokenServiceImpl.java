package nature.sales_website.servicesImpls;

import nature.sales_website.entity.User;
import nature.sales_website.entity.security.RefreshToken;
import nature.sales_website.jwt.JwtTokenProvider;
import nature.sales_website.repositories.RefreshTokenRepository;
import nature.sales_website.repositories.UserRepository;
import nature.sales_website.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String create(String refreshToken, String deviceId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        RefreshToken newToken = new RefreshToken();
        newToken.setRefreshToken(refreshToken);
        newToken.setDeviceId(deviceId);
        newToken.setUser(user);

        refreshTokenRepository.save(newToken);

        return "Success";
    }

    @Override
    public String update(String newRefreshToken, String deviceId, Long userId) {
        RefreshToken token = refreshTokenRepository.findByUserIdAndDeviceId(userId, deviceId)
                .orElseThrow(()-> new RuntimeException("Refresh token not found!"));
        token.setRefreshToken(newRefreshToken);
        refreshTokenRepository.save(token);
        return "Success";
    }

    @Override
    public String delete(String deviceId, Long userId) {
        RefreshToken token = refreshTokenRepository.findByUserIdAndDeviceId(userId, deviceId)
                .orElseThrow(() -> new RuntimeException("Refresh token not found!"));

        refreshTokenRepository.delete(token);

        return "Success";
    }

    @Override
    public boolean isExpired(String deviceId, Long userId) {
        RefreshToken token = refreshTokenRepository.findByUserIdAndDeviceId(userId, deviceId)
                .orElseThrow(() -> new RuntimeException("Refresh token not found!"));

        return jwtTokenProvider.validateToken(token.getRefreshToken());
    }
}
