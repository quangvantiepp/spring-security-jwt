package nature.sales_website.controllers;

import nature.sales_website.entity.data.UserAddressData;
import nature.sales_website.models.response.ActionStatus;
import nature.sales_website.models.response.ResponseData;
import nature.sales_website.servicesImpls.AddressServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImp addressServiceImp;
    @PostMapping("/create-user-address")
    public ResponseEntity<ResponseData> createAddress(@RequestParam(value = "userId") Long userId,
                                                      @RequestParam(value = "provinceCode") String provinceCode,
                                                      @RequestParam(value = "districtCode") String districtCode,
                                                      @RequestParam(value = "wardsCode") String wardsCode,
                                                      @RequestParam(value = "village", required = false) String village,
                                                      @RequestParam(value = "streetAndHouseNumber") String streetAndHouseNumber,
                                                      @RequestParam(value = "recipientFullName", required = false) String recipientFullName,
                                                      @RequestParam(value = "recipientPhoneNumber", required = false) String recipientPhoneNumber){

        try {
            String status = addressServiceImp.create(userId,
                    provinceCode,
                    districtCode,
                    wardsCode,
                    village,
                    streetAndHouseNumber,
                    recipientFullName,
                    recipientPhoneNumber);

            return ActionStatus.data(status, ActionStatus.created, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData> updateUserAddress(@RequestBody UserAddressData newUserAddressData){

        try {
            String resData = addressServiceImp.updateUserAddress(newUserAddressData);

            return ActionStatus.data(ActionStatus.success, resData, HttpStatus.OK);
        }
        catch(Exception e) {
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
    @GetMapping("/getAll-user-address")
    public ResponseEntity<ResponseData> getAllUserAddress(){

        try {
            Object resData = addressServiceImp.getAllAddress();

            return ActionStatus.data(ActionStatus.success, resData, HttpStatus.OK);
        }
        catch(Exception e) {
            return ActionStatus.exceptionData(e.getMessage());
        }
    }

    @GetMapping("/get-user-address-by-user-id")
    public ResponseEntity<ResponseData> getUserAddressByUserId(@RequestParam(value = "userId") Long userId ){

        try {
            Object resData = addressServiceImp.getUserAddressByUserId(userId);

            return ActionStatus.data(ActionStatus.success, resData, HttpStatus.OK);
        }
        catch(Exception e) {
            return ActionStatus.exceptionData(e.getMessage());
        }
    }
}
