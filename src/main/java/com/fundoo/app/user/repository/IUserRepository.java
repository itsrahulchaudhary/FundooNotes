package com.fundoo.app.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundoo.app.user.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);


}
