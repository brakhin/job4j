package ru.bgbrakhi.carseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bgbrakhi.carseller.models.Role;
import ru.bgbrakhi.carseller.models.User;
import ru.bgbrakhi.carseller.repository.IRoleRepository;
import ru.bgbrakhi.carseller.repository.IUserRepository;

import java.util.Collection;
import java.util.HashSet;

@Service()
@Repository
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        HashSet<Role> roles = new HashSet<Role>((Collection<? extends Role>) roleRepository.findAll());
        if (roles.isEmpty()) {
            roles.add(new Role("ROLE_USER"));
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
