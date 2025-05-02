package nature.sales_website.services;

import org.springframework.stereotype.Service;

@Service
public interface RefreshTokenService {
    String create(String refreshToken, String deviceId, Long userId);
    String update(String refreshToken, String deviceId, Long userId);
    String delete(String deviceId, Long userId);
    boolean isExpired(String deviceId, Long userId);
}
