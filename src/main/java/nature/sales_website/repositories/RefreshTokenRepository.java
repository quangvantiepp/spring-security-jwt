package nature.sales_website.repositories;


import nature.sales_website.entity.security.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    @Query(value = "SELECT * FROM refresh_tokens r where r.user_id= :userId and r.device_id= :deviceId", nativeQuery = true)
    Optional<RefreshToken> findByUserIdAndDeviceId(Long userId, String deviceId);
}
