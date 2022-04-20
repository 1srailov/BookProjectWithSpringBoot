package uz.yt.springdata.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "publisher")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Publisher{
    @Id
    @GeneratedValue(generator = "publisher_id_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address_id")
    private Integer adressId;
}
