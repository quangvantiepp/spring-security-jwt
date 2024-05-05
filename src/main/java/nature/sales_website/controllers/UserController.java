package nature.sales_website.controllers;

import nature.sales_website.dto.UserDto;
import nature.sales_website.models.response.ActionStatus;
import nature.sales_website.models.response.ResponseData;
import nature.sales_website.servicesImpls.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping("/create-user")
    public ResponseEntity<ResponseData> Create(@RequestParam(value = "fullName") String fullName,
                                               @RequestParam(value = "phoneNumber")  String phoneNumber,
                                               @RequestParam(value ="passWord")  String passWord){

        try {
            String status = userServiceImp.create(fullName, phoneNumber, passWord);
            return ActionStatus.data(status, ActionStatus.created, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @GetMapping("/get-all-users")
    public Object getAllUser(){

        try {
            List<UserDto> userDtoList = userServiceImp.getAllUser();
            if (userDtoList == null){
                return ActionStatus.data(ActionStatus.not_found,new ArrayList<>(),HttpStatus.NOT_FOUND);
            }else{
                return ActionStatus.data(ActionStatus.success,userDtoList,HttpStatus.OK);
            }
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
    @GetMapping("/get-user-by-id")
    public Object getUserById(@RequestParam(value = "id") Long id){

        try {
            UserDto userDto = userServiceImp.getUserById(id);
            if (userDto == null){
                return ActionStatus.data(ActionStatus.not_found, null, HttpStatus.NOT_FOUND);
            }else{
                return ActionStatus.data(ActionStatus.success, userDto, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @GetMapping("/get-user-by-phone-number")
    public Object getUserByPhoneNumber(@RequestParam(value = "phoneNumber") String phoneNumber){
        try {
            UserDto userDto = userServiceImp.getUserByPhoneNumber(phoneNumber);
            if (userDto == null){
                return ActionStatus.data(ActionStatus.not_found, null, HttpStatus.NOT_FOUND);
            }else{
                return ActionStatus.data(ActionStatus.success, userDto, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @GetMapping("/get-user-by-email")
    public Object getUserByEmail(@RequestParam(value = "email") String email){

        try {
            UserDto userDto = userServiceImp.getUserByEmail(email);
            if (userDto == null){
                return ActionStatus.data(ActionStatus.not_found, null, HttpStatus.NOT_FOUND);
            }else{
                return ActionStatus.data(ActionStatus.success, userDto, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }

    }

    @PutMapping("/update-user-name")
    public Object updateUserName(@RequestParam(value = "fullName") String fullName,
                                 @RequestParam(value = "id") Long id){
        try {
            String status = userServiceImp.updateUserName(fullName,id);
            return ActionStatus.data(status, ActionStatus.updated, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
    @PutMapping("/update-user-email")
    public Object updateUserEmail(@RequestParam(value = "email")  String email,
                                  @RequestParam(value = "id") Long id){
        try {
            String status = userServiceImp.updateUserEmail(email,id);
            return ActionStatus.data(status, ActionStatus.updated, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
    @PutMapping("/update-user-phone-number")
    public Object updateUserPhoneNumber(@RequestParam(value = "phoneNumber")  String phoneNumber,
                                        @RequestParam(value = "id") Long id){
        try {
            String status = userServiceImp.updateUserPhoneNumber( phoneNumber,id);
            return ActionStatus.data(status, ActionStatus.updated, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
    @PutMapping("/update-user-password")
    public Object updateUserPassword(@RequestParam(value = "passWord")  String passWord,
                                     @RequestParam(value = "id") Long id){
        try {
            String status = userServiceImp.updateUserPassword(passWord,id);
            return ActionStatus.data(status, ActionStatus.updated, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @DeleteMapping("/delete-user-by-id")
    public Object deleteUserById(@RequestParam(value = "id") Long id){

        try {
            String status = userServiceImp.deleteUser(id);
            return ActionStatus.data(status, ActionStatus.deleted, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
}
