package com.binh.service.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.dto.DTO_Order;
import com.binh.entity.Order;

@Service
public class Mapper_Order implements ConvertToDtoAndReverse<Order, DTO_Order>{
	@Autowired private ModelMapper modelmapper;
	
	@Override
	public DTO_Order convertEntityToDto(Order entity) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(entity, DTO_Order.class);
	}

	@Override
	public Order convertToEntity(DTO_Order dto) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(dto, Order.class);
	}

}
