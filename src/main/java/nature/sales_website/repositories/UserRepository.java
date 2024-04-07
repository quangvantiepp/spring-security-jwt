package nature.sales_website.repositories;

import nature.sales_website.entity.User;
import nature.sales_website.repositories.queryValue.UserQueryValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {

     List<User> findAll();

     @Query(value = UserQueryValue.GET_USER_BY_ID, nativeQuery = true)
     User getUserById(Long userId);

     @Query(value = UserQueryValue.GET_USER_BY_EMAIL, nativeQuery = true)
     User getUserByEmail(String userEmail);

     @Query(value = UserQueryValue.GET_USER_BY_PHONE_NUMBER, nativeQuery = true)
     User getUserByPhoneNumber(String phoneNumber);

}
