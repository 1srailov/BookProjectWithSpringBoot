package uz.yt.springdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String phoneNumber;

    private BigDecimal account;

    private String username;

    private String password;
}
