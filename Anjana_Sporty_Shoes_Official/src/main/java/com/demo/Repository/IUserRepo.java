package com.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.Entity.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
	
	// Login Query
	Optional<User> findByUserNameAndPassword(@Param("user_name") String userName, @Param("password") String password);

	@Query("SELECT user FROM User user WHERE CONCAT(user.userName) LIKE %?1%")
	List<User> search(String keyword);
}
