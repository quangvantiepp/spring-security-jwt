package nature.sales_website.services;

import nature.sales_website.dto.RoleDto;
import nature.sales_website.entity.Role;
import nature.sales_website.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    Object create(Role role);
    List<RoleDto> getAllRole();
    Object getRoleById(Long id);
    Object updateRole(Long id, String name);
    Object deleteRole(Long id);
}
