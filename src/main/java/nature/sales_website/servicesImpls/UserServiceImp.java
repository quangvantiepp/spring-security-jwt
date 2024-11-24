package nature.sales_website.servicesImpls;

import nature.sales_website.dto.RoleDto;
import nature.sales_website.dto.UserDto;
import nature.sales_website.entity.Role;
import nature.sales_website.entity.User;
import nature.sales_website.models.checker.Checker;
import nature.sales_website.models.converter.DtoConverter;
import nature.sales_website.models.response.ActionStatus;
import nature.sales_website.repositories.UserRepository;
import nature.sales_website.services.UserService;
import nature.sales_website.utils.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String create(String fullName, String phoneNumber, String passWord, String userName) {
        // check phone number is exits
        if(Checker.isNumericFirst(fullName) ){
            throw new RuntimeException("Passwords, FullName cannot begin with a number!");
        }
        User user = userRepository.getUserByPhoneNumber(phoneNumber);
        if (fullName == null || phoneNumber == null || passWord == null){
            throw new RuntimeException("FullName, PhoneNumber or Password cannot be null");
        }
        if (user != null) {
            throw new RuntimeException("There is already another account using this phone number!");
        }
        if (passWord.length() < 6){
            throw new RuntimeException("Password must have a minimum of 6 characters!");
        }

        String encodePassword = EncrytedPasswordUtils.encrytePassword(passWord);
        user = new User();
        user.setFullName(fullName);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(encodePassword);
        user.setUserName(userName);
        userRepository.save(user);
        return ActionStatus.success;
    }

    @Override
    public Page<UserDto> getAllUser(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        if (userPage.isEmpty()){
            return null;
        }
        Page<UserDto> userDtoPage = DtoConverter.convertToDtoPage(userPage, UserDto.class, pageable );
        return userDtoPage;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.getUserById(id);
        if (user == null){
            return null;
        }
        UserDto userDto = DtoConverter.convertToDto(user, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getUserByPhoneNumber(String phoneNumber) {
        User user = userRepository.getUserByPhoneNumber(phoneNumber);
        if (user == null){
            return null;
        }
        UserDto userDto = DtoConverter.convertToDto(user, UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null){
            return null;
        }
        UserDto userDto = DtoConverter.convertToDto(user, UserDto.class);
        return userDto;
    }
    @Override
    public String updateUserName(String fullName, Long id) {
        User user = userRepository.getUserById(id);
        if (user == null) throw new RuntimeException("No user found to update info!");

        if (fullName.equals(user.getFullName())){
            throw new RuntimeException("You are giving the same Name as the old one!");
        }
        user.setFullName(fullName);
        userRepository.save(user);
        return ActionStatus.success;
    }

    @Override
    public String updateUserEmail(String email, Long id) {
        User user = userRepository.getUserById(id);
        if (user == null) throw new RuntimeException("No user found to update info!");
        if (email.equals(user.getEmail())){
            throw new RuntimeException("You are giving the same Email as the old one!");
        }

        User userByEmail = userRepository.getUserByEmail(email);
        if( userByEmail != null && !userByEmail.getId().equals(user.getId()) ){
            throw new RuntimeException("There is already another account using this Email!");
        }
        // need check email with Google
        user.setEmail(email);

        userRepository.save(user);
        return ActionStatus.success;
    }

    @Override
    public String updateUserPhoneNumber(String phoneNumber, Long id) {
        User user = userRepository.getUserById(id);
        if (user == null) throw new RuntimeException("No user found to update info!");

        if (phoneNumber.equals(user.getPhoneNumber())){
            throw new RuntimeException("You are giving the same Phone number as the old one!");
        }
        User userByPhone = userRepository.getUserByPhoneNumber(phoneNumber);
        if (userByPhone != null && !userByPhone.getId().equals(user.getId())){
            throw new RuntimeException("There is already another account using this phone number!");
        }
        // need check phone number with sms otp
        user.setPhoneNumber(phoneNumber);

        userRepository.save(user);
        return ActionStatus.success;
    }

    @Override
    public String updateUserPassword(String password, Long id) {
        User user = userRepository.getUserById(id);
        if (user == null) throw new RuntimeException("No user found to update info!");

        if (password.equals(user.getPassword())){
            throw new RuntimeException("You are giving the same Password as the old one!");
        }
        // need check format password
        if (password.length() < 6){
            throw new RuntimeException("Password must have a minimum of 6 characters!");
        }
        user.setPassword(password);
        userRepository.save(user);
        return ActionStatus.success;
    }

    @Override
    public String deleteUser(Long userId) {
        User user = userRepository.getUserById(userId);
        if (user == null){
            throw new RuntimeException("No user found to delete!");
        }
        userRepository.delete(user);
        return ActionStatus.success;
    }

    @Override
    public Object getListRole(Long userId) {
        User user = userRepository.getUserById(userId);
        if (user == null) throw new RuntimeException("User not found to get Role list!");

        Set<Role> roleList = user.getRoleSet();
        if (roleList == null) return null;
        Set<RoleDto> roleDtoSet = DtoConverter.convertToDtoSet(roleList, RoleDto.class);
        return roleDtoSet;
    }
}
