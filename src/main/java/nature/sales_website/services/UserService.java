package nature.sales_website.services;

import nature.sales_website.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    String create(String fullName, String phoneNumber, String passWord);
    List<UserDto> getAllUser();
    UserDto getUserById(Long id);
    UserDto getUserByPhoneNumber(String phoneNumber);
    UserDto getUserByEmail(String email);
    String updateUserName(String fullName, Long id);
    String updateUserEmail(String email, Long id);
    String updateUserPhoneNumber(String phoneNumber, Long id);
    String updateUserPassword(String password, Long id);
    String deleteUser(Long userId);
}
