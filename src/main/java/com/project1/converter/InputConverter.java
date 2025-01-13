package com.project1.converter;

import com.project1.entity.InputEntity;
import com.project1.model.dto.InputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InputConverter {
    @Autowired
    private ModelMapper modelMapper;

    public InputEntity toInputEntity(InputDTO inputDTO) {
        InputEntity inputEntity = modelMapper.map(inputDTO, InputEntity.class);
        return inputEntity;
    }

    public InputDTO toInputDTO(InputEntity inputEntity) {
        InputDTO inputDTO = modelMapper.map(inputEntity, InputDTO.class);
        return inputDTO;
    }
}
