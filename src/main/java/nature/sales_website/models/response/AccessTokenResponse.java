package nature.sales_website.models.response;

import lombok.Data;

@Data
public class AccessTokenResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    public AccessTokenResponse(String accessToken){
        this.accessToken = accessToken;
    }
}
