package nature.sales_website.controllers;

import nature.sales_website.entity.Role;
import nature.sales_website.servicesImpls.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServiceImp roleServiceImp;

    @PostMapping("/create-role")
    public Object createRole(Role role){
        return roleServiceImp.create(role);
    }
}
