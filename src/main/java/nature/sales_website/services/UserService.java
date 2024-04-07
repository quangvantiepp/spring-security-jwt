package nature.sales_website.services;

import nature.sales_website.dto.UserDto;
import nature.sales_website.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Object create(String fullName, String email, String phoneNumber, String passWord);
    List<UserDto> getAllUser();
    UserDto getUserById(Long id);
    UserDto getUserByPhoneNumber(String phoneNumber);
    UserDto getUserByEmail(String email);
    Object updateUserInfo(String fullName, String email, String phoneNumber, String passWord, Long id);
    Object deleteUser(Long userId);
}
