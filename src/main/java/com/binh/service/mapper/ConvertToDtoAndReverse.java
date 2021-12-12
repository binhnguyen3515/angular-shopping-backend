package com.binh.service.mapper;

import org.springframework.stereotype.Service;

@Service
public interface ConvertToDtoAndReverse<E,D> {
	D convertEntityToDto(E entity);
	E convertToEntity(D dto);
}
