package com.binh.service.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.dto.DTO_Category;
import com.binh.entity.Category;

@Service
public class Mapper_Category implements ConvertToDtoAndReverse<Category, DTO_Category>{
	@Autowired private ModelMapper modelmapper;
	
	@Override
	public DTO_Category convertEntityToDto(Category entity) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(entity, DTO_Category.class);
	}

	@Override
	public Category convertToEntity(DTO_Category dto) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(dto, Category.class);
	}

}
