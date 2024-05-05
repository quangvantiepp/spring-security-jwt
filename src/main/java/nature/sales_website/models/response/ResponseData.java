package nature.sales_website.models.response;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class ResponseData {
    private  String message;
    private Object data;
     public ResponseData(String message, Object data){
        this.message = message;
        this.data = data;
    }
}
