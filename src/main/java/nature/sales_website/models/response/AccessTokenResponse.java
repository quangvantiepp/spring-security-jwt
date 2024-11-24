package nature.sales_website.models.response;

import lombok.Data;
import nature.sales_website.dto.UserDto;

@Data
public class AccessTokenResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserDto userDto;
    public AccessTokenResponse(String accessToken, UserDto userDto){
        this.accessToken = accessToken;
        this.userDto = userDto;
    }
}
