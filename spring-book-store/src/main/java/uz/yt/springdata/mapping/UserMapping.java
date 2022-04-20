package uz.yt.springdata.mapping;

import uz.yt.springdata.dao.Author;
import uz.yt.springdata.dao.User;
import uz.yt.springdata.dto.AuthorDTO;
import uz.yt.springdata.dto.UserDTO;
import uz.yt.springdata.service.UserService;

public class UserMapping{
    public static UserDTO toDto(User user){
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                user.getAccount(),
                user.getUsername(),
                user.getPassword());
    }

    public static User toEntity(UserDTO userDTO){
        return new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getBirthDate(),
                userDTO.getPhoneNumber(),
                userDTO.getAccount(),
                userDTO.getUsername(),
                userDTO.getPassword());
    }

    public static void toEntity(User user, UserDTO userDTO) {
        if (userDTO.getFirstName() != null)
            user.setFirstName(userDTO.getFirstName());
        if (userDTO.getLastName() != null)
            user.setLastName(userDTO.getLastName());
        if(userDTO.getBirthDate() != null)
            user.setBirthDate(userDTO.getBirthDate());
        if(userDTO.getPhoneNumber() != null && UserService.checkUserPhoneNumber(userDTO.getPhoneNumber()))
            user.setPhoneNumber(userDTO.getPhoneNumber());
        if(userDTO.getAccount() != null)
            user.setAccount(userDTO.getAccount());
        if(userDTO.getUsername() != null)
            user.setUsername(userDTO.getUsername());
        if(userDTO.getPassword() != null)
            user.setPassword(userDTO.getPassword());
        user.setId(userDTO.getId());


    }
}
