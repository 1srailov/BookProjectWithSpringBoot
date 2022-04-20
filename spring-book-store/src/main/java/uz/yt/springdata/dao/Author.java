package uz.yt.springdata.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Author {
        @Id
        @GeneratedValue(generator = "author_id_seq")
        @Column(name = "id")
        private Integer id;

        @Column(name = "firstname")
        private String firstname;

        @Column(name = "lastname")
        private String lastname;

        @Column(name = "birthdate")
        private Date birthdate;

}
