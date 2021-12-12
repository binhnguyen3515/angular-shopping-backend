package com.binh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.binh.entity.OrderDetail;
@Repository
public interface Repository_OrderDetail extends JpaRepository<OrderDetail, Long>{

	@Query(value="Select isnull(sum(odt.price*odt.quantity),0) as todayRevenue  "
			+ "from orders o inner join OrderDetails odt on o.Id = odt.OrderId "
			+ "Where CreateDate = Cast(GETDATE() as date)" ,nativeQuery = true)
	Double getTodayIncome();

	@Query(value="Select isnull(sum(odt.price*odt.quantity),0) as totalRevenue  "
			+ "from orders o inner join OrderDetails odt on o.Id = odt.OrderId "
			 ,nativeQuery = true)
	Double getTotalIncome();

}
