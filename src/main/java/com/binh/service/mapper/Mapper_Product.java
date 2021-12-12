package com.binh.service.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.dto.DTO_Product;
import com.binh.entity.Product;

@Service
public class Mapper_Product implements ConvertToDtoAndReverse<Product, DTO_Product>{
	@Autowired private ModelMapper modelmapper;
	
	@Override
	public DTO_Product convertEntityToDto(Product entity) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(entity, DTO_Product.class);
	}

	@Override
	public Product convertToEntity(DTO_Product dto) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(dto, Product.class);
	}

}
