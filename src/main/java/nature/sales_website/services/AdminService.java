package nature.sales_website.services;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    String setRoleToUser(Long userId, Integer RoleId);
}
