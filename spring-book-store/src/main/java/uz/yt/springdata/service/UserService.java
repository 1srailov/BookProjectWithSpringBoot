package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import uz.yt.springdata.dao.Author;
import uz.yt.springdata.dao.User;
import uz.yt.springdata.dto.AuthorDTO;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.dto.UserDTO;
import uz.yt.springdata.mapping.AuthorMapping;
import uz.yt.springdata.mapping.UserMapping;
import uz.yt.springdata.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseDTO<List<UserDTO>> getAll(){
        List<User> users = userRepository.findAll();
        if(!users.isEmpty()){
            List<UserDTO> userDTOS = new ArrayList<>();
            for(User u: users){
                userDTOS.add(UserMapping.toDto(u));
            }
            return new ResponseDTO<>(true, 0, "OK", userDTOS);
        }
        return new ResponseDTO<>(false, -4, "NOT FOUND", null);
    }

    public ResponseDTO<UserDTO> getById(Integer id){
        try {
            User user = userRepository.getById(id);
            return new ResponseDTO<>(true, 0, "OK", UserMapping.toDto(user));
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseDTO<>(false, -4, "NOT FOUND", null);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "ERROR", null);

        }
    }

    public ResponseDTO<UserDTO> addUser(UserDTO userDTO){
        try {
            if(checkUserPhoneNumber(userDTO.getPhoneNumber())) {
                User user = UserMapping.toEntity(userDTO);
                userRepository.save(user);
                return new ResponseDTO<>(true, 0, "OK", UserMapping.toDto(user));
            }
            return new ResponseDTO<>(false, -2, "ERROR PHONE-NUMBER", null);

        }catch(DataIntegrityViolationException e){
            e.printStackTrace();
            return new ResponseDTO<>(false, -3, "ERROR LOGIN", null);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "ERROR", null);
        }
    }

    public ResponseDTO<UserDTO> login(UserDTO userDTO){
        Optional<User> user = userRepository.findByUsernameAndPassword(
                userDTO.getUsername(), userDTO.getPassword());
        if(user.isPresent()){
            return new ResponseDTO<>(true, 0, "OK", UserMapping.toDto(user.get()));
        }
        return new ResponseDTO<>(false, -1, "ERROR LOGIN OR PASSWORD", null);
    }

    public ResponseDTO<UserDTO> update(UserDTO userDTO) {
        try {
            if(userDTO.getId() == null)
                return new ResponseDTO<>(false, -2, "ID IS NULL", userDTO);

            Optional<User> user = userRepository.findById(userDTO.getId());
            if(!user.isPresent())
                return new ResponseDTO<>(false, -4, "NOT FOUND", userDTO);

            User user1 = user.get();
            UserMapping.toEntity(user1, userDTO);

            userRepository.save(user1);

            return new ResponseDTO<>(true, 0, "OK", userDTO);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return new ResponseDTO<>(false, -2, "USERNAME ERROR", userDTO);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDTO<>(false, -1, "ERROR", userDTO);
        }
    }


    public static boolean checkUserPhoneNumber(String phoneNumber){
        if(phoneNumber.charAt(0) == '+' && phoneNumber.length() == 13){
            for(int i = 1; i < phoneNumber.length(); i++){
                if (phoneNumber.charAt(i) >= '0' && phoneNumber.charAt(i) <= '9')
                    continue;
                return false;
            }
            return true;
        }
        else
            return false;
    }



}
