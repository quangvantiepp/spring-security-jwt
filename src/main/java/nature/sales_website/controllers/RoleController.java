package nature.sales_website.controllers;

import nature.sales_website.dto.RoleDto;
import nature.sales_website.models.response.ActionStatus;
import nature.sales_website.models.response.ResponseData;
import nature.sales_website.servicesImpls.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServiceImp roleServiceImp;
    @PostMapping("/create-role")
    public ResponseEntity<ResponseData> createRole(@RequestParam(value = "roleName") String roleName){
        try {
                String status = roleServiceImp.create(roleName);
                return ActionStatus.data(status, ActionStatus.created, HttpStatus.CREATED);
        }
        catch (Exception e){
            return ActionStatus.data(e.getMessage(), null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all-roles")
    public ResponseEntity<ResponseData> getAllRole(){
        try {
            List<RoleDto> listRole = roleServiceImp.getAllRole();
            if (listRole == null){
                return ActionStatus.data(ActionStatus.not_found,new ArrayList<>(),HttpStatus.NOT_FOUND);
            }else{
                return ActionStatus.data(ActionStatus.success,listRole,HttpStatus.OK);
            }
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @GetMapping("/get-role-by-id")
    public ResponseEntity<ResponseData> getRoleById(@RequestParam(value = "id") Integer id){

        try {
            RoleDto roleDto = roleServiceImp.getRoleById(id);
            if (roleDto == null){
                return ActionStatus.data(ActionStatus.not_found, null, HttpStatus.NOT_FOUND);
            }else{
                return ActionStatus.data(ActionStatus.success, roleDto, HttpStatus.OK);
            }
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @PutMapping("/update-role")
    public ResponseEntity<ResponseData> updateRole(@RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name){
        try {
            String status = roleServiceImp.updateRole(id, name);
            return ActionStatus.data(status, ActionStatus.updated, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @DeleteMapping("/delete-role")
    public ResponseEntity<ResponseData> deleteRole(@RequestParam(value = "roleId") Integer roleId){
        try {
            String status = roleServiceImp.deleteRole(roleId);
            return ActionStatus.data(status, ActionStatus.deleted, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
}
