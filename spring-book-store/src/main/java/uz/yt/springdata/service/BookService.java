package uz.yt.springdata.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.dao.Author;
import uz.yt.springdata.dao.Book;
import uz.yt.springdata.dto.AuthorDTO;
import uz.yt.springdata.dto.BookDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.mapping.AuthorMapping;
import uz.yt.springdata.mapping.BookMapping;
import uz.yt.springdata.repository.AuthorRepository;
import uz.yt.springdata.repository.BookRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public ResponseDTO<BookDTO> addNew(BookDTO bookDTO){
        try {
            Book book = BookMapping.toEntity(bookDTO);
            bookRepository.save(book);
            return new ResponseDTO<>(true, 0, "OK", BookMapping.toDto(book));
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "ERROR", null);
        }
    }

    public ResponseDTO<List<BookDTO>> getAll(){
        List<Book> books = bookRepository.findAll();
        if(!books.isEmpty()){
            List<BookDTO> responce = new ArrayList<>();
            for (Book book: books)
                responce.add(BookMapping.toDto(book));

            return new ResponseDTO<>(true, 0, "OK", responce);
        }
        return new ResponseDTO<>(false, -4, "NOT FOUND", null);
    }

    public ResponseDTO<BookDTO> getById(Integer id) {
        try {
            Book book = bookRepository.getById(id);
            BookDTO bookDTO = BookMapping.toDto(book);
            bookDTO.setAuthorDTO(
                    AuthorMapping.toDto(authorRepository.getById(book.getAuthorId()))
            );
            return new ResponseDTO<>(true, 0, "OK", bookDTO);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseDTO<>(false, -4, "NOT FOUND", null);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "ERROR", null);
        }
    }

    public ResponseDTO<BookDTO> deleteById(Integer id){
        try {
            Book book = bookRepository.getById(id);
            bookRepository.delete(book);
            return new ResponseDTO<>(true, 0, "OK", BookMapping.toDto(book));
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "FALSE", null);
        }
    }

    public ResponseDTO<BookDTO> update(BookDTO bookDTO){
        try {
            if(bookDTO.getId() == null)
                return new ResponseDTO<>(false, -2, "ID IS NULL", bookDTO);

            Optional<Book> book = bookRepository.findById(bookDTO.getId());
            if(!book.isPresent())
                return new ResponseDTO<>(false, -4, "NOT FOUND", bookDTO);

            Book book1 = book.get();
            BookMapping.toEntity(book1, bookDTO);

            bookRepository.save(book1);

            return new ResponseDTO<>(true, 0, "OK", bookDTO);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, 0, "ERROR", null);
        }
    }
}
