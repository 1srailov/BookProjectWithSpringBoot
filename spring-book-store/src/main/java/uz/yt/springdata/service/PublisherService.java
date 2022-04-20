package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.yt.springdata.dao.Publisher;
import uz.yt.springdata.dto.PublisherDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.mapping.BookMapping;
import uz.yt.springdata.mapping.PublisherMapping;
import uz.yt.springdata.repository.PublisherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public ResponseDTO<PublisherDTO> addNew(PublisherDTO publisherDTO){
        try {
            Publisher publisher = PublisherMapping.toEntity(publisherDTO);
            publisherRepository.save(publisher);
            return new ResponseDTO<>(true, 0, "OK", PublisherMapping.toDto(publisher));
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "ERROR", null);
        }
        }

    public ResponseDTO<PublisherDTO> getById(Integer id){
        try{
            Optional<Publisher> publisher = publisherRepository.findById(id);
            if (publisher.isPresent()) {
                return new ResponseDTO<>(true, 0, "Ok", PublisherMapping.toDto(publisher.get()));
            } else
                return new ResponseDTO<>(false, -4, "NOT FOUND", null);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "ERROR", null);
        }
    }


    public ResponseDTO<List<PublisherDTO>> getAll() {
        List<Publisher> publishers = publisherRepository.findAll();
        if(!publishers.isEmpty()){
            List<PublisherDTO> publisherDTOS = new ArrayList<>();
            for(Publisher p:publishers)
                publisherDTOS.add(PublisherMapping.toDto(p));

            return new ResponseDTO<>(true, 0, "OK", publisherDTOS);
        }
        return new ResponseDTO<>(true, -4, "NOT FOUND", null);
    }

    public ResponseDTO<PublisherDTO> deleteById(Integer id){
        try {
            Optional<Publisher> publisher = publisherRepository.findById(id);
            publisherRepository.delete(publisher.get());
            return new ResponseDTO<>(true, 0, "OK", PublisherMapping.toDto(publisher.get()));
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "ERROR", null);
        }
    }
}
