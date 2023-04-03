package com.wipro.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
