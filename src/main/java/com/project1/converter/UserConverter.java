package com.project1.converter;
import com.project1.entity.UserEntity;
import com.project1.model.dto.UserDTO;
import com.project1.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toUserDTO(UserEntity userEntity){
        UserDTO result = modelMapper.map(userEntity, UserDTO.class);
        return result;
    }

    public UserEntity toUserEntity(UserDTO userDTO){
        UserEntity result = modelMapper.map(userDTO, UserEntity.class);
        return result;
    }
}
