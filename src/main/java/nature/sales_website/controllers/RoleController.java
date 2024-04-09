package nature.sales_website.controllers;

import nature.sales_website.entity.Role;
import nature.sales_website.servicesImpls.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServiceImp roleServiceImp;

    @PostMapping("/create-role")
    public Object createRole(Role role){
        return roleServiceImp.create(role);
    }

    @GetMapping("/get-all-role")
    public Object getAllRole(){
       return roleServiceImp.getAllRole();
    }

    @GetMapping("/get-role-by-id")
    public Object getRoleById(Long id){
        Object res = roleServiceImp.getRoleById(id);
        return res;
    }

    @PutMapping("/update-role")
    public Object updateRole(Long id, String name){
        return roleServiceImp.updateRole(id, name);
    }

    @DeleteMapping("/delete-role")
    public Object deleteRole(Long id){
        return roleServiceImp.deleteRole(id);
    }
}
