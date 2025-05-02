package nature.sales_website.controllers.security;

import nature.sales_website.models.response.ActionStatus;
import nature.sales_website.models.response.ResponseData;
import nature.sales_website.servicesImpls.RefreshTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/refresh-token")
public class RefreshTokenController {

    @Autowired
    private RefreshTokenServiceImpl refreshTokenServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<ResponseData> Create(@RequestParam(value = "refreshToken") String refreshToken,
                                               @RequestParam(value = "deviceId") String deviceId,
                                               @RequestParam(value = "userId") Long userId){

        try {
            String status = refreshTokenServiceImpl.create(refreshToken, deviceId, userId);
            return ActionStatus.data(status, ActionStatus.created, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData> updateRefreshToken(@RequestParam(value = "newRefreshToken") String newRefreshToken,
                                                           @RequestParam(value = "deviceId") String deviceId,
                                                           @RequestParam(value = "userId") Long userId){
        try {
            String status = refreshTokenServiceImpl.update(newRefreshToken,deviceId, userId);
            return ActionStatus.data(status, ActionStatus.updated, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseData> delete(@RequestParam(value = "deviceId") String deviceId,
                                               @RequestParam(value = "userId") Long userId){
        try {
            String status = refreshTokenServiceImpl.delete(deviceId, userId);
            return ActionStatus.data(status, ActionStatus.deleted, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @GetMapping("/is-expired")
    public ResponseEntity<ResponseData> checkExpired(@RequestParam(value = "deviceId") String deviceId,
                                                     @RequestParam(value = "userId") Long userId){
        try {
            boolean status = refreshTokenServiceImpl.isExpired(deviceId, userId);
            return ActionStatus.data(status ? "true" : "false", ActionStatus.success, HttpStatus.OK);
        }
        catch (Exception e){
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
}
