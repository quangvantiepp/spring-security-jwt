package nature.sales_website.services;

import nature.sales_website.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String create(String fullName, String phoneNumber, String passWord);
    Page<UserDto> getAllUser(Pageable pageable);
    UserDto getUserById(Long id);
    UserDto getUserByPhoneNumber(String phoneNumber);
    UserDto getUserByEmail(String email);
    String updateUserName(String fullName, Long id);
    String updateUserEmail(String email, Long id);
    String updateUserPhoneNumber(String phoneNumber, Long id);
    String updateUserPassword(String password, Long id);
    String deleteUser(Long userId);
    // other actions
    Object getListRole(Long userId);
}
