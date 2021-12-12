package com.binh.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binh.repository.Repository_OrderDetail;
import com.binh.service.Service_OrderDetail;
import com.binh.utils.XDate;

@Service
public class ServiceImpl_OrderDetail implements Service_OrderDetail{
	
	@Autowired private Repository_OrderDetail repoDetail;
//	@Autowired private XDate xdate;
	
	@Override
	public Double getTodayIncome() {
		return repoDetail.getTodayIncome();
	}

	@Override
	public Double getTotalIncome() {
		return repoDetail.getTotalIncome();
	}

}
