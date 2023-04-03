package com.wipro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	// public Account findByEmail(String email);
	public Account findByAccountNumber(long from);

	public void deleteByAccountNumber(long accountNum);

	// @Query()
	// public List<Employee> findEmployeeByEmailEndingWithCom(String email);

	// @Query()
	// public List<Employee> findEmployeeBySalRange(long start, long end);
}
