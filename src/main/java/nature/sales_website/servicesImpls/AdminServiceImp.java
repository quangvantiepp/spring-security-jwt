package nature.sales_website.servicesImpls;

import nature.sales_website.entity.Role;
import nature.sales_website.entity.User;
import nature.sales_website.models.response.ActionStatus;
import nature.sales_website.repositories.RoleRepository;
import nature.sales_website.repositories.UserRepository;
import nature.sales_website.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public String setRoleToUser(Long userId, Integer roleId) {

        User user = userRepository.getUserById(userId);
        Role role = roleRepository.getById(roleId);

        if (user == null) throw new RuntimeException("User not found");
        if ( role == null) throw new RuntimeException("Role not found");

        Set<Role> roleSet = user.getRoleSet();
        if (roleSet == null) roleSet = new HashSet<>();
        roleSet.add(role);

        user.setRoleSet(roleSet);
        userRepository.save(user);

        return ActionStatus.success;
    }
}
