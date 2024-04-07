package nature.sales_website.controllers;

import nature.sales_website.entity.User;
import nature.sales_website.servicesImpls.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping("/create-user")
    public Object Create(@RequestParam(value = "fullName") String fullName,
                         @RequestParam(required = false)  String email,
                         @RequestParam(value = "phoneNumber")  String phoneNumber,
                         @RequestParam(value ="passWord")  String passWord){
        return userServiceImp.create(fullName, email, phoneNumber, passWord);
    }

    @GetMapping("/get-user-by-id")
    public Object getUserById(@RequestParam(value = "id") Long id){
        return userServiceImp.getUserById(id);
    }

    @GetMapping("/get-user-by-email")
    public Object getUserByEmail(@RequestParam(value = "email") String email){
        return userServiceImp.getUserByEmail(email);
    }

    @GetMapping("/get-user-by-phone-number")
    public Object getUserByPhoneNumber(@RequestParam(value = "phoneNumber") String phoneNumber){
        return userServiceImp.getUserByPhoneNumber(phoneNumber);
    }

    @PutMapping("/update-user-info")
    public Object updateUserInfo(@RequestParam(required = false) String fullName,
                                 @RequestParam(required = false)  String email,
                                 @RequestParam(required = false)  String phoneNumber,
                                 @RequestParam(required = false)  String passWord,
                                 @RequestParam(value = "id") Long id){
        return userServiceImp.updateUserInfo(fullName, email, phoneNumber, passWord,id);
    }

    @DeleteMapping("/delete-user-by-id")
    public Object deleteUserById(@RequestParam(value = "id") Long id){
        return userServiceImp.deleteUser(id);
    }
}
