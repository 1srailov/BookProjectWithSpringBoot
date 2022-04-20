package uz.yt.springdata.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookDTO{

    private Integer id;

    private String name;

    private AuthorDTO authorDTO;

    private Double cost;

    private String genre;


    public BookDTO(Integer id, String nameUz, Double cost, String genre) {
        this.id = id;
        this.name = nameUz;
        this.cost = cost;
        this.genre = genre;
    }
}
