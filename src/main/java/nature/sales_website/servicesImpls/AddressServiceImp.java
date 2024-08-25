package nature.sales_website.servicesImpls;

import nature.sales_website.entity.User;
import nature.sales_website.entity.UserAddress;
import nature.sales_website.entity.data.UserAddressData;
import nature.sales_website.repositories.AddressRepository;
import nature.sales_website.repositories.UserRepository;
import nature.sales_website.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public String create(Long userId,
                         String provinceCode,
                         String districtCode,
                         String wardsCode,
                         String village,
                         String streetAndHouseNumber,
                         String recipientFullName,
                         String recipientPhoneNumber ) {

        UserAddress newAddress = new UserAddress();
        if (provinceCode != null &&
                districtCode != null &&
                wardsCode != null &&
                streetAndHouseNumber != null
        ){
            newAddress.setProvinceCode(provinceCode);
            newAddress.setDistrictCode(districtCode);
            newAddress.setWardsCode(wardsCode);
            newAddress.setStreetAndHouseNumber(streetAndHouseNumber);
        }else {
            throw new RuntimeException("provinceCode, districtCode, wardsCode and"
                    + "streetAndHouseNumber cannot be null");
        }

        if (village != null){
            newAddress.setVillage(village);
        }
        if ( recipientFullName != null && recipientPhoneNumber != null){
            newAddress.setRecipientFullName(recipientFullName);
            newAddress.setRecipientPhoneNumber(recipientPhoneNumber);
        }

        User currentUser = userRepository.getUserById(userId);
        if (currentUser == null) throw new RuntimeException("User not found!");
        newAddress.setUser(currentUser);
        addressRepository.save(newAddress);
        return "Success";
    }

    @Override
    public List<UserAddress> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public List<UserAddress> getUserAddressByUserId(Long userId) {
        User user = userRepository.getUserById(userId);

        if (user == null) throw new RuntimeException("User not found!");
        List<UserAddress> userAddressList = addressRepository.getUserAddressByUserId(userId);
        return userAddressList;
    }

    @Override
    public String updateUserAddress(UserAddressData newUserAddressData) {
        if (newUserAddressData.getUserAddressId() == null) {
            throw new RuntimeException("UserAddressId can not be null!");
        }

        UserAddress oldUserAddress = addressRepository.getReferenceById(newUserAddressData.getUserAddressId());
        if (oldUserAddress == null){
            throw new RuntimeException("Not found UserAddress by UserAddressId!");
        }

        //
        if (newUserAddressData.getProvinceCode() != null){
            oldUserAddress.setProvinceCode(newUserAddressData.getProvinceCode());
        }
        //
        if (newUserAddressData.getDistrictCode() != null){
            oldUserAddress.setDistrictCode(newUserAddressData.getDistrictCode());
        }
        //
        if (newUserAddressData.getWardsCode() != null){
            oldUserAddress.setWardsCode(newUserAddressData.getWardsCode());
        }
        //
        if (newUserAddressData.getVillage() != null){
            oldUserAddress.setVillage(newUserAddressData.getVillage());
        }
        if (newUserAddressData.getStreetAndHouseNumber() != null){
            oldUserAddress.setStreetAndHouseNumber(newUserAddressData.getStreetAndHouseNumber());
        }
        //
        if (newUserAddressData.getRecipientFullName() != null){
            oldUserAddress.setRecipientFullName(newUserAddressData.getRecipientFullName());
        }
        //
        if (newUserAddressData.getRecipientPhoneNumber() != null){
            oldUserAddress.setRecipientPhoneNumber(newUserAddressData.getRecipientPhoneNumber());
        }
        addressRepository.save(oldUserAddress);
        return "Success";
    }
}
