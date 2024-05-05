package nature.sales_website.services;

import nature.sales_website.dto.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    String create(String roleName);
    List<RoleDto> getAllRole();
    RoleDto getRoleById(Integer id);
    String updateRole(Integer id, String name);
    String deleteRole(Integer id);

}
