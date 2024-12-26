package com.bornfire.entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteOffRepo extends JpaRepository<WriteOffReport, String> {
	
}