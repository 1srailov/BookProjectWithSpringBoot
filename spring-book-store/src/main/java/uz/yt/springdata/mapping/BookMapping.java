package uz.yt.springdata.mapping;

import uz.yt.springdata.dao.Book;
import uz.yt.springdata.dto.BookDTO;
import uz.yt.springdata.repository.AuthorRepository;

import java.util.Optional;

public class BookMapping{

    public static BookDTO toDto(Book book){
        return new BookDTO(
                book.getId(),
                book.getNameUz(),
                book.getCost(),
                book.getGenre());
    }

    public static Book toEntity(BookDTO bookDTO){
        return new Book(
                bookDTO.getId(),
                bookDTO.getName(),
                bookDTO.getCost(),
                bookDTO.getAuthorDTO().getId(),
                bookDTO.getGenre());
    }

    public static void toEntity(Book book, BookDTO bookDTO) {
        if (bookDTO.getName() != null)
            book.setNameUz(bookDTO.getName());
        if (bookDTO.getGenre() != null)
            book.setGenre(bookDTO.getGenre());
        if(bookDTO.getCost() != null)
            book.setCost(bookDTO.getCost());
        book.setId(bookDTO.getId());
    }
}
