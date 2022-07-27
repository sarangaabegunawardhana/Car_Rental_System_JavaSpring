package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Registration {
    @Id
    private String registrationId;
    private String email;
    private String password;
    private String nic;
    private String nicImage;
    private String dlicense;
    private String dlicenseImage;
    private String address;
    private int contactNo;
    private String cusStatus;

}
