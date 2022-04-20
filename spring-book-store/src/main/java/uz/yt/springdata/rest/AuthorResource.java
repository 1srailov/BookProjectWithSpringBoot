package uz.yt.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.dto.AuthorDTO;
import uz.yt.springdata.dto.BookDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class AuthorResource {

    private final AuthorService authorService;

    @GetMapping("/get-all-authors")
    public ResponseDTO<List<AuthorDTO>> getAll(){
        return authorService.getAll();
    }

    @GetMapping("/get-author-by-id/{id}")
    private ResponseDTO<AuthorDTO> getAuthorById(@PathVariable Integer id){
        return authorService.getById(id);
    }

    @GetMapping("/delete-author-by-id/{id}")
    private ResponseDTO<AuthorDTO> deleteAuthorById(@PathVariable Integer id){
        return authorService.deleteById(id);
    }

    @PostMapping("/add-author")
    public ResponseDTO<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDTO){
        return authorService.addNew(authorDTO);
    }

    @PutMapping("/update-author")
    public ResponseDTO<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO){
        return authorService.update(authorDTO);
    }

}
