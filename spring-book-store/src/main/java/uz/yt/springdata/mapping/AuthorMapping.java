package uz.yt.springdata.mapping;

import uz.yt.springdata.dao.Author;
import uz.yt.springdata.dao.Book;
import uz.yt.springdata.dto.AuthorDTO;
import uz.yt.springdata.dto.BookDTO;

public class AuthorMapping {
    public static AuthorDTO toDto(Author author){
        return new AuthorDTO(
                author.getId(),
                author.getFirstname(),
                author.getLastname(),
                author.getBirthdate());
    }

    public static Author toEntity(AuthorDTO authorDTO){
        return new Author(
                authorDTO.getId(),
                authorDTO.getFirstname(),
                authorDTO.getLastname(),
                authorDTO.getBirth_date());
    }

    public static void toEntity(Author author, AuthorDTO authorDTO) {
        if (authorDTO.getFirstname() != null)
            author.setFirstname(authorDTO.getFirstname());
        if (authorDTO.getLastname() != null)
            author.setLastname(authorDTO.getLastname());
            author.setLastname(authorDTO.getLastname());
        if(authorDTO.getBirth_date() != null)
            author.setBirthdate(authorDTO.getBirth_date());
        author.setId(authorDTO.getId());
    }
}
