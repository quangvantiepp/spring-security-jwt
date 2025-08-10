package nature.sales_website.controllers;

import nature.sales_website.data.LoginRequest;
import nature.sales_website.models.response.AccessTokenResponse;
import nature.sales_website.models.response.ResponseData;
import nature.sales_website.servicesImpls.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginServiceImp loginServiceImp;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest request, HttpServletResponse response){

        try {
            AccessTokenResponse accessTokenResponse = loginServiceImp.loginAuthenticate(request, response);
            return ResponseEntity.ok().body(accessTokenResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refreshToken(HttpServletRequest request) {
        try {
            AccessTokenResponse accessTokenResponse = loginServiceImp.refreshToken(request);
            return ResponseEntity.ok().body(accessTokenResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        // Logout simply deletes the client-side (Frontend) token.
        // Response logout
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/validate-token")
    public ResponseEntity<ResponseData> validateToken(HttpServletRequest request){

        try {
          boolean isValid =  loginServiceImp.checkValidateToken(request);
            if (isValid) {
                return ResponseEntity.ok(new ResponseData("Valid Token!", true));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData("Token Expired!", null));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData("Error while verifying token!", null));
        }

    }

    @GetMapping("/admin/admin-page")
    public String adminPage(){
        return ("JWT is good to access this ADMIN PAGE page");
    }

    @GetMapping("/user/user-page")
    public String userPage(){
        return ("JWT is good to access this USER PAGE page");
    }

}
