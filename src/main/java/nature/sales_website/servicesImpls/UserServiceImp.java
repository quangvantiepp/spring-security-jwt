package nature.sales_website.servicesImpls;

import nature.sales_website.dto.UserDto;
import nature.sales_website.entity.User;
import nature.sales_website.models.converter.DtoConverter;
import nature.sales_website.repositories.UserRepository;
import nature.sales_website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Object create(String fullName, String email, String phoneNumber, String passWord) {
        // check phone number is exits
        User user = userRepository.getUserByPhoneNumber(phoneNumber);
        if (fullName == null ){
            return "Enter your name, please!";
        } else if (phoneNumber == null) {
            return "Enter your phone number, please!";
        } else if (passWord == null) {
            return "Enter your password, please!";
        } else if (user != null) {
            return "There is already another account using this phone number!";
        }
        user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassWord(passWord);
        userRepository.save(user);
        return "success";
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        if (userList.size()  == 0 ){
            return null;
        }
        List<UserDto> userDtoList = DtoConverter.convertToDtoList(userList, UserDto.class);
        return userDtoList;
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
    public Object updateUserInfo(String fullName, String email, String phoneNumber, String passWord, Long id) {
        User user = userRepository.getUserById(id);
        if (user == null){
            return "not found user";
        }

        if (fullName != null){
            if ( fullName == user.getFullName()){
                return "You are giving the same name as the old one";
            }
            user.setFullName(fullName);
        }else if (email != null){
            if( email == user.getEmail()){
                return "You are giving the same email as the old one";
            }
            // need check email with Google
            user.setEmail(email);
        }else if (phoneNumber != null){
            if (phoneNumber == user.getPhoneNumber() ){
                return "You are giving the same phone number as the old one";
            }
            // need check phone number with sms otp
            user.setPhoneNumber(phoneNumber);
        }else if (passWord != null ){
            if (passWord == user.getPassWord()){
                return "You are giving the same pass word as the old one";
            }
            user.setPassWord(passWord);
        }
        userRepository.save(user);
        return "ok";
    }

    @Override
    public Object deleteUser(Long userId) {
        User user = userRepository.getUserById(userId);
        if (user == null){
            return "not found user";
        }
        userRepository.delete(user);
        return "delete success";
    }

}
