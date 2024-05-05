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
public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findAll();
    @Query(value = RoleQueryValue.getRoleById, nativeQuery = true)
    Role getById(Integer roleId);

    @Query(value = "SELECT * FROM sales_website.roles u where u.name= :roleName", nativeQuery = true)
    Role getRoLeByName(String roleName);
}
