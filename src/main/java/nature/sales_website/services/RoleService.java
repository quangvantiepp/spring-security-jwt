package nature.sales_website.services;

import nature.sales_website.entity.Role;
import nature.sales_website.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Object create(Role role);
    List<Role> getAllRole();
    Object getRoleById(Long id);
}
