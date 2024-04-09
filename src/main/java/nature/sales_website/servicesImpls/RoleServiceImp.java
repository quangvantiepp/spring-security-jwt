package nature.sales_website.servicesImpls;

import nature.sales_website.dto.RoleDto;
import nature.sales_website.entity.Role;
import nature.sales_website.models.converter.DtoConverter;
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
    public List<RoleDto> getAllRole() {
        List<Role> roleList = roleRepository.findAll();
        if (roleList == null){
            return null;
        }
        List<RoleDto> roleDtoList = DtoConverter.convertToDtoList(roleList, RoleDto.class);
        return roleDtoList;
    }

    @Override
    public Object getRoleById(Long id) {
        Role role = roleRepository.getById(id);
        if (role == null){
            return "Not found role";
        }
        RoleDto roleDto = DtoConverter.convertToDto(role, RoleDto.class);
        return roleDto;
    }

    @Override
    public Object updateRole(Long id, String name) {
        Role role = roleRepository.getById(id);
        if (role == null){
            return "Not found role";
        }
        if (name == role.getName()){
            return "The name is the same as the current name!";
        }
        role.setName(name);
        roleRepository.save(role);
        return "success";
    }

    @Override
    public Object deleteRole(Long id) {
        Role role = roleRepository.getById(id);
        if (role == null){
            return "Not found role";
        }
        roleRepository.delete(role);
        return "success";
    }
}
