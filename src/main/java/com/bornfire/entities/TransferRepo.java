package com.bornfire.entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepo extends JpaRepository<TransferEntity, String> {
	
}