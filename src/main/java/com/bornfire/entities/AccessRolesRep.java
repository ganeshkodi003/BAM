package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

	@Repository
	public interface AccessRolesRep extends JpaRepository<AccessRoles, String> {


		public Optional<AccessRoles> findById(String role_id);

		@Query(value = "select * from BTM_ACS_RLS", nativeQuery = true)
		List<AccessRoles> getRolelist();
		
	}