package uz.yt.springdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AuthorDTO {

    private Integer id;

    private String firstname;

    private String lastname;

    private Date birth_date;

}
