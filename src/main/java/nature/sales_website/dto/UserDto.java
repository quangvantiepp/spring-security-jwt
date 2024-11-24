package nature.sales_website.dto;

import lombok.*;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String userName;
    private Set<String> roleSet;

}
