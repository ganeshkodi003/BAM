package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserProfileRep extends CrudRepository<UserProfile, String> {

	public Optional<UserProfile> findByusername(String userName);


	@Query(value = "select count(*) from BAM_USER_PROFILE  where USER_ID =?1", nativeQuery = true)

	int getcount(String userid);
	
	@Query(value = "select count(*) from BAM_USER_PROFILE where email_id =?1", nativeQuery = true)
	int getEmailSentCount();
	
	@Query(value = "SELECT * FROM BAM_USER_PROFILE where email_id =?1", nativeQuery = true)
	List<UserProfile> getEmailDetails();
	@Query(value = "SELECT * FROM BAM_USER_PROFILE where user_id =?1", nativeQuery = true)
	UserProfile getUserDetails(String userid);
	@Query(value = "SELECT * FROM BAM_USER_PROFILE", nativeQuery = true)
	List<UserProfile> getUserList();
	
	
	@Query(value = "SELECT * FROM BGLS_USER_PROFILE_TABLE WHERE DEL_FLG='N' and role_id ='ADM'", nativeQuery = true)
	List<UserProfile> getUserId();

}