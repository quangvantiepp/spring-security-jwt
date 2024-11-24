package nature.sales_website.servicesImpls;

import nature.sales_website.data.CustomUserDetails;
import nature.sales_website.entity.User;
import nature.sales_website.repositories.RoleRepository;
import nature.sales_website.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
// spring security only
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User currentUser = userRepository.getUserByName(userName);
//
//        if (currentUser == null) {
//            System.out.println("User not found! " + userName);
//            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
//        }
//
//        System.out.println("Found User: " + currentUser);
//
//        // [ROLE_USER, ROLE_ADMIN,..]
//        Set<Role> userRoles = currentUser.getRoleSet();
//
//        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//        if (userRoles != null) {
//            for (Role userRole : userRoles) {
//                // ROLE_USER, ROLE_ADMIN,..
//                GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getName());
//                grantList.add(authority);
//            }
//        }
//
//        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(currentUser.getUserName(), //
//                currentUser.getPassword(), grantList);
//
//        return userDetails;
//    }


    // spring security + json web token
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Check user in the database
        User user = userRepository.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return new CustomUserDetails(user);
    }

}
