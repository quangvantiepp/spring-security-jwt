package nature.sales_website.servicesImpls;

import nature.sales_website.dto.RoleDto;
import nature.sales_website.entity.Role;
import nature.sales_website.models.checker.Checker;
import nature.sales_website.models.converter.DtoConverter;
import nature.sales_website.models.response.ActionStatus;
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
    public String create(String roleName) {
        if(Checker.isNumericFirst(roleName)){
            throw new RuntimeException("Names cannot begin with a number!");
        }
        Role role = roleRepository.getRoLeByName(roleName);
        if (role != null){
            throw new RuntimeException("Role already exists!");
        }
        role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
        return ActionStatus.success;
    }

    @Override
    public List<RoleDto> getAllRole() {
        List<Role> roleList = roleRepository.findAll();
        if (roleList.size() == 0){
            return null;
        }
        List<RoleDto> roleDtoList = DtoConverter.convertToDtoList(roleList, RoleDto.class);
        return roleDtoList;
    }

    @Override
    public RoleDto getRoleById(Integer id) {
        Role role = roleRepository.getById(id);
        if (role == null){
            return null;
        }
        RoleDto roleDto = DtoConverter.convertToDto(role, RoleDto.class);
        return roleDto;
    }

    @Override
    public String updateRole(Integer id, String name) {
        if(Checker.isNumericFirst(name)){
            throw new RuntimeException("Names cannot begin with a number!");
        }
        Role role = roleRepository.getById(id);
        Role roleByName = roleRepository.getRoLeByName(name);
        if (role == null){
            throw new RuntimeException("No Roles found to update!");
        }
        if (roleByName != null){
            if (name.equals(roleByName.getName())){
                throw new RuntimeException("Name already exists!");
            }
        }
        role.setName(name);
        roleRepository.save(role);
        return ActionStatus.success;
    }

    @Override
    public String deleteRole(Integer id) {
        Role role = roleRepository.getById(id);
        if (role == null){
            throw new RuntimeException("No Role found to delete!");
        }
        roleRepository.delete(role);
        return ActionStatus.success;
    }
}
