package com.bornfire.account_creation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepo extends JpaRepository<ApiResponse_Entity, String> {

}
