package nature.sales_website.models.response;

import lombok.Data;
import nature.sales_website.dto.UserDto;

@Data
public class AccessTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private UserDto userDto;
    public AccessTokenResponse(String accessToken, String refreshToken, UserDto userDto){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userDto = userDto;

    }
}
