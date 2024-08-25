package nature.sales_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_address")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAddressId;
    // Tinh, Thanh pho Trung uong
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
    private boolean isUse;
    // using vn public api
    //https://documenter.getpostman.com/view/5504327/Tzscq7KS#dfef9f72-2ed8-44f6-8e33-d146f31f19ec
    // update data https://danhmuchanhchinh.gso.gov.vn/

    // relationship
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}