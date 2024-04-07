package nature.sales_website.dto;

import lombok.*;

@Data
public class UserDto {
    private Long id;
    private String fullName;

//    public static UserDto convertToDto(User user){
//        return new ModelMapper().map(user, UserDto.class);
//    }
//
//    public static Set<UserDto> convertToDtoList(List<User> userList){
//        return userList.stream().map(user -> new ModelMapper().map(user, UserDto.class)).collect(Collectors.toSet());
//    }
}
