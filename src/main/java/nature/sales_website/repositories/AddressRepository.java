package nature.sales_website.repositories;

import nature.sales_website.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface AddressRepository extends JpaRepository<UserAddress, Long> {
    public List<UserAddress> findAll();
    @Query(value = "SELECT * FROM sales_website.user_address u where u.user_id = :userId",
    nativeQuery = true)
    public List<UserAddress> getUserAddressByUserId(Long userId);
}
