package uz.yt.springdata.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.dto.PublisherDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.service.PublisherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PublisherResource {
    private final PublisherService publisherService;

    @GetMapping("/get-publisher-by-id/{id}")
    public ResponseDTO<PublisherDTO> getPublisherById(@PathVariable Integer id){
        return publisherService.getById(id);
    }

    @GetMapping("/get-all-publishers")
    public ResponseDTO<List<PublisherDTO>> getAllPublishers(){
        return publisherService.getAll();
    }

    @PostMapping("/add-publisher")
    public ResponseDTO<PublisherDTO> addNewPublisher(@RequestBody PublisherDTO publisherDTO){
        return publisherService.addNew(publisherDTO);
    }
}
