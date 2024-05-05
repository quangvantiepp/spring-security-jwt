package nature.sales_website.controllers.admin;

import nature.sales_website.models.response.ActionStatus;
import nature.sales_website.models.response.ResponseData;
import nature.sales_website.servicesImpls.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImp adminServiceImp;
    @PutMapping("set-role-to-user")
    public ResponseEntity<ResponseData> setRoleToUser(@RequestParam(value = "userId") Long userId,
                                                      @RequestParam(value = "roleId") Integer roleId){
        try {
            String status = adminServiceImp.setRoleToUser(userId, roleId);
            return ActionStatus.data(status, null, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
}
