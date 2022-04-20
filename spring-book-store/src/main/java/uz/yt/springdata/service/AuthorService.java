package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.dao.Author;
import uz.yt.springdata.dao.Book;
import uz.yt.springdata.dto.AuthorDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.mapping.AuthorMapping;
import uz.yt.springdata.mapping.BookMapping;
import uz.yt.springdata.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthorService{

    private final AuthorRepository authorRepository;

    public ResponseDTO<AuthorDTO> addNew(AuthorDTO authorDTO){
        try {
            Author author = AuthorMapping.toEntity(authorDTO);
            authorRepository.save(author);
            return new ResponseDTO<>(true, 0, "OK", AuthorMapping.toDto(author));
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, 0, "ERROR", null);
        }
    }

    public ResponseDTO<List<AuthorDTO>> getAll(){
        List<Author> authors = authorRepository.findAll();
        if(!authors.isEmpty()){
            List<AuthorDTO> authorDTOS = new ArrayList<>();
            for(Author a: authors){
                authorDTOS.add(AuthorMapping.toDto(a));
            }
            return new ResponseDTO<>(true, 0, "OK", authorDTOS);
        }
        return new ResponseDTO<>(false, -4, "NOT FOUND", null);
    }

    public ResponseDTO<AuthorDTO> getById(Integer id){
        try {
            Author author = authorRepository.getById(id);
            return new ResponseDTO<>(true, 0, "OK", AuthorMapping.toDto(author));

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "FALSE", null);
        }

        }

    public ResponseDTO<AuthorDTO> deleteById(Integer id){
        try {
            Author author = authorRepository.getById(id);
            authorRepository.delete(author);
            return new ResponseDTO<>(true, 0, "OK", AuthorMapping.toDto(author));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "FALSE", null);
        }
        }

    public ResponseDTO<AuthorDTO> update(AuthorDTO authorDTO) {
        try {
            if(authorDTO.getId() == null)
                return new ResponseDTO<>(false, -2, "ID IS NULL", authorDTO);

            Optional<Author> author = authorRepository.findById(authorDTO.getId());
            if(!author.isPresent())
                return new ResponseDTO<>(false, -4, "NOT FOUND", authorDTO);

            Author author1 = author.get();
            AuthorMapping.toEntity(author1, authorDTO);

            authorRepository.save(author1);

            return new ResponseDTO<>(true, 0, "OK", authorDTO);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, 0, "ERROR", authorDTO);
        }
    }
}
