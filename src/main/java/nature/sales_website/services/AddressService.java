package nature.sales_website.services;

import nature.sales_website.entity.UserAddress;
import nature.sales_website.entity.data.UserAddressData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    String create(Long userId,
                  String provinceCode,
                  String districtCode,
                  String wardsCode,
                  String village,
                  String streetAndHouseNumber,
                  String recipientFullName,
                  String recipientPhoneNumber
                  );
    List<UserAddress> getAllAddress();
    List<UserAddress> getUserAddressByUserId(Long userId);
    String updateUserAddress(UserAddressData userAddressData);
}
