package com.binh.service.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.dto.DTO_Account;
import com.binh.entity.Account;

@Service
public class Mapper_Account implements ConvertToDtoAndReverse<Account, DTO_Account>{
	@Autowired private ModelMapper modelmapper;
	
	@Override
	public DTO_Account convertEntityToDto(Account entity) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(entity, DTO_Account.class);
	}

	@Override
	public Account convertToEntity(DTO_Account dto) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(dto, Account.class);
	}

}
