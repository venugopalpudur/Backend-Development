package com.wipro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.model.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

}
