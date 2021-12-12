package com.binh.service.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.dto.DTO_Role;
import com.binh.entity.Role;

@Service
public class Mapper_Role implements ConvertToDtoAndReverse<Role, DTO_Role>{
	@Autowired private ModelMapper modelmapper;
	
	@Override
	public DTO_Role convertEntityToDto(Role entity) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(entity, DTO_Role.class);
	}

	@Override
	public Role convertToEntity(DTO_Role dto) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(dto, Role.class);
	}

}
