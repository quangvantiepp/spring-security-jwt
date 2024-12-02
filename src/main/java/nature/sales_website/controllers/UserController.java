package nature.sales_website.controllers;

import nature.sales_website.dto.UserDto;
import nature.sales_website.models.response.ActionStatus;
import nature.sales_website.models.response.ResponseData;
import nature.sales_website.servicesImpls.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    @PostMapping("/register")
    public ResponseEntity<ResponseData> Create(@RequestParam(value = "fullName") String fullName,
                                               @RequestParam(value = "phoneNumber")  String phoneNumber,
                                               @RequestParam(value ="passWord")  String passWord,
                                               @RequestParam(value = "userName") String userName){

        try {
            String status = userServiceImp.create(fullName, phoneNumber, passWord, userName);
            return ActionStatus.data(status, ActionStatus.created, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @GetMapping("/all-users")
    public Object getAllUser(@RequestParam() Integer pageNumber, @RequestParam() Integer pageSize,
                             @RequestParam(value ="sortField", required = false) String sortField,
                             @RequestParam(value ="sortOrder",required = false) String sortOrder
                             ){

        if (pageNumber == null || pageSize == null) throw new RuntimeException("PageNumber and pageSize are required!");

        Pageable pageable;
        if (sortOrder != null && sortField != null){
            Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortField));
        }else{
            pageable = PageRequest.of(pageNumber, pageSize);
        }

        try {
            Page<UserDto> userDtoPage = userServiceImp.getAllUser(pageable);
            if (userDtoPage == null){
                return ActionStatus.data(ActionStatus.not_found,new ArrayList<>(),HttpStatus.NOT_FOUND);
            }else{
                return ActionStatus.data(ActionStatus.success,userDtoPage,HttpStatus.OK);
            }
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
    @GetMapping("/by-id")
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

    @GetMapping("/by-phone-number")
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

    @GetMapping("/by-email")
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

    @PutMapping("/update-full-name")
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
    @PutMapping("/update-email")
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
    @PutMapping("/update-phone-number")
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

    @DeleteMapping("/delete")
    public Object deleteUserById(@RequestParam(value = "id") Long id){

        try {
            String status = userServiceImp.deleteUser(id);
            return ActionStatus.data(status, ActionStatus.deleted, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
    // other actions
    @GetMapping("/role-list")
    public Object getRoleListOfUser(@RequestParam(value = "userId") Long userId){

        try {
            Object roleList = userServiceImp.getListRole(userId);
            if (roleList == null){
                return ActionStatus.data(ActionStatus.not_found, new ArrayList<>(), HttpStatus.NOT_FOUND);
            }else{
                return ActionStatus.data(ActionStatus.success, roleList, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }

    }
}
