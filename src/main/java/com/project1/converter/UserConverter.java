package com.project1.converter;
import com.project1.entity.UserEntity;
import com.project1.model.dto.UserDTO;
import com.project1.model.response.UserSearchResponse;
import com.project1.repository.UserRepository;
import com.project1.utils.ClassIdUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO toUserDTO(UserEntity userEntity){
//        UserDTO result = modelMapper.map(userEntity, UserDTO.class);
        UserDTO result = new UserDTO();
        result.setId(userEntity.getId());
        result.setFullname(userEntity.getFullname());
        result.setUsername(userEntity.getUsername());
        result.setPassword(userEntity.getPassword());
        result.setStatus(userEntity.getStatus());
        result.setPhone_number(userEntity.getPhone_number());
        result.setCreatedAt(userEntity.getCreatedAt());
        result.setUpdatedAt(userEntity.getUpdatedAt());
        result.setRole(userEntity.getRole());
        List<String> list = new ArrayList<>();
        if(!Objects.equals(userEntity.getClassId(), "")){
            StringTokenizer st = new StringTokenizer(userEntity.getClassId(),",");
            while(st.hasMoreTokens()){
                String tmp = st.nextToken();
                list.add(String.valueOf(ClassIdUtils.toClassId(Long.parseLong(tmp))));
            }
        }
        result.setClass_id(list);
        return result;
    }

    public UserEntity toUserEntity(UserDTO userDTO){
        UserEntity result = modelMapper.map(userDTO, UserEntity.class);
        StringBuilder classList = new StringBuilder();
        if(userDTO.getClass_id() == null || !userDTO.getClass_id().isEmpty()){
            for(int i = 0;i<userDTO.getClass_id().size();i++){
                if(i != userDTO.getClass_id().size()-1){
                    classList.append(ClassIdUtils.toClassId(userDTO.getClass_id().get(i)).toString()+",");
                }
                else{
                    classList.append(ClassIdUtils.toClassId(userDTO.getClass_id().get(i)).toString());
                }
            }
        }
        else{
            result.setClassId("");
        }
        result.setClassId(classList.toString());
        return result;
    }

    public UserSearchResponse toUserSearchResponse(UserEntity userEntity){
        UserSearchResponse result = new UserSearchResponse();
        result.setId(userEntity.getId());
        result.setFullname(userEntity.getFullname());
        result.setUsername(userEntity.getUsername());
        result.setPhone_number(userEntity.getPhone_number());
        result.setRole(userEntity.getRole());
        return result;
    }
}
