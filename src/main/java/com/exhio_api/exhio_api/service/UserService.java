package com.exhio_api.exhio_api.service;

import com.exhio_api.exhio_api.domain.Permission;
import com.exhio_api.exhio_api.domain.User;
import com.exhio_api.exhio_api.dto.user.CreateUserDTO;
import com.exhio_api.exhio_api.dto.user.ReadUserDTO;
import com.exhio_api.exhio_api.dto.user.ReadUserDetailedDTO;
import com.exhio_api.exhio_api.dto.user.UpdateUserPermissionDTO;
import com.exhio_api.exhio_api.infra.exception.UserException;
import com.exhio_api.exhio_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReadUserDTO registerUser(@Valid CreateUserDTO userDTO) throws Exception {

        if(userRepository.existsByLogin(userDTO.login())) {
            throw new UserException("This login credential is already in use");
        }

        if(userRepository.existsByEmail(userDTO.email())) {
            throw new UserException("This email is already in use");
        }

        User user = new User(userDTO);
        String encodedPassword = passwordEncoder.encode(userDTO.password());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        return new ReadUserDTO(savedUser);
    }

    public ResponseEntity<ReadUserDetailedDTO> updatePermissions(Long id, @Valid UpdateUserPermissionDTO permissionDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));
        Set<Permission> permissions = new HashSet<>();
        for(String name : permissionDTO.permissions()) {
            if(permissionService.existsByName(name)) {
                Permission permission = permissionService.getByName(name);
                permissions.add(permission);
            }
        }
        user.setPermissions(permissions);
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(new ReadUserDetailedDTO(savedUser));
    }
}
