package com.binh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.binh.entity.Account;

public interface Repository_Account extends JpaRepository<Account, String>{

	Optional<Account> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);

	@Query(value="Select a.Username, a.Fullname, a.email, a.photo, "
			+ "sum(odt.price * odt.quantity) as totalPayment "
			+ "From Accounts a inner join orders o on a.Username = o.Username "
			+ "inner join OrderDetails odt on o.Id = odt.OrderId "
			+ "Group by a.Username, a.Fullname, a.email, a.photo "
			+ "order by totalPayment desc",nativeQuery = true)
	List<Object[]> top10Customer();

	@Query(value="Select count(*) from Accounts",nativeQuery=true)
	Long getTotalAccount();
}
