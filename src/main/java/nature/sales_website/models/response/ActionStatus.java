package nature.sales_website.models.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ActionStatus {
    public static final String success = "Success";
    public static final String not_found = "Not found";
    public static final String bad_request = "Bad request";
    public static final  String updated = "Updated";
    public static final String created = "Created";
    public static final String deleted = "Deleted";

    public static ResponseEntity<ResponseData> data(String message, Object data, HttpStatus httpStatus){
        return new ResponseEntity<ResponseData>(new ResponseData(message, data), httpStatus);
    }

    public static ResponseEntity<ResponseData> exceptionData(String message){
        return new ResponseEntity<ResponseData>(new ResponseData(message, null), HttpStatus.BAD_REQUEST
        );
    }
}
