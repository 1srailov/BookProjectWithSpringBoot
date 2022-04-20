package uz.yt.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.dto.AuthorDTO;
import uz.yt.springdata.dto.BookDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookResource {

    private final BookService bookService;

    @GetMapping("/get-all-books")
    public ResponseDTO<List<BookDTO>> getAll(){
        return bookService.getAll();
    }

    @GetMapping("/get-book-by-id/{id}")
    public ResponseDTO<BookDTO> getBookById(@PathVariable Integer id){
        return bookService.getById(id);
    }

    @PostMapping("/add-book")
    public ResponseDTO<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        return bookService.addNew(bookDTO);
    }

    @GetMapping("/delete-book-by-id/{id}")
    public ResponseDTO<BookDTO> deleteBookById(@PathVariable Integer id){
        return bookService.deleteById(id);
    }

    @PutMapping("/update-book")
    public ResponseDTO<BookDTO> updateBook(@RequestBody BookDTO bookDTO){
        return bookService.update(bookDTO);
    }
}
