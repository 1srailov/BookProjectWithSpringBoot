package uz.yt.springdata.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Book{
    @Id
    @GeneratedValue(generator = "books_id_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_eng")
    private String nameEng;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "published_date")
    private Date published_date;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "genre")
    private String genre;

    @Column(name = "publisher_id")
    private Integer publisherId;

public Book(Integer id, String nameUz, Double cost, Integer authorId, String genre){
    this.id = id;
    this.nameUz = nameUz;
    this.cost = cost;
    this.authorId = authorId;
    this.genre = genre;

}



}
