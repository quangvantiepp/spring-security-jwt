package nature.sales_website.repositories;

import nature.sales_website.entity.User;
import nature.sales_website.repositories.queryValue.UserQueryValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
     Page<User> findAll(Pageable pageable);
     @Query(value = UserQueryValue.getUserById, nativeQuery = true)
     User getUserById(Long userId);
     @Query(value = "SELECT * FROM sales_website.users u where u.full_name= :userName", nativeQuery = true)
     User getUserByName(String userName);
     @Query(value = UserQueryValue.getUserByEmail, nativeQuery = true)
     User getUserByEmail(String userEmail);
     @Query(value = UserQueryValue.getUserByPhoneNumber, nativeQuery = true)
     User getUserByPhoneNumber(String phoneNumber);
     @Query(value = "SELECT * FROM sales_website.users u where u.id= :userId and u.phone_number= :phoneNumber", nativeQuery = true)
     User getUserByIdAndPhoneNumber(Long userId, String phoneNumber);
     @Query(value = "SELECT * FROM sales_website.users u where u.id= :userId and u.password= :password", nativeQuery = true)
     User getUserByPassword(Long userId, String password);
     // others actions

}
