package nature.sales_website.servicesImpls;

import nature.sales_website.entity.Role;
import nature.sales_website.repositories.RoleRepository;
import nature.sales_website.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Object create(Role role) {
        if (role.getName() == null){
            return null;
        }
      return  roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRole() {
        return null;
    }

    @Override
    public Object getRoleById(Long id) {
        return null;
    }
}
