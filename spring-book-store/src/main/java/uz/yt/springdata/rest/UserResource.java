package uz.yt.springdata.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.dto.UserDTO;
import uz.yt.springdata.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/get-all-users")
    public ResponseDTO<List<UserDTO>> getAll(){
        return userService.getAll();
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseDTO<UserDTO> getById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @PostMapping("/add-user")
    public ResponseDTO<UserDTO> addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @GetMapping("/login")
    public ResponseDTO<UserDTO> login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }

    @PutMapping("/update-user")
    public ResponseDTO<UserDTO> update(@RequestBody UserDTO userDTO){
        return userService.update(userDTO);
    }
}
