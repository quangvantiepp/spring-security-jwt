package nature.sales_website.entity.data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Data
public class UserAddressData {
    private Long userAddressId;
    private String provinceCode;
    // Thanh pho, Quan, Huyen, Thi xa
    private String districtCode;
    // Phuong, Xa, Thi tran, Thon, Doi
    private String wardsCode;
    // Thon , lang, ban
    private String  village;
    private String streetAndHouseNumber;

    // other recipient
    private String recipientFullName;
    private String recipientPhoneNumber;
}
