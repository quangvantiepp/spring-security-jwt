package nature.sales_website.repositories;

import nature.sales_website.entity.Role;
import nature.sales_website.repositories.queryValue.RoleQueryValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAll();
    @Query(value = RoleQueryValue.GET_ROLE_BY_ID, nativeQuery = true)
    Role getById(Long roleId);
}
