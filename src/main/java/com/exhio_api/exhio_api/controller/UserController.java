package com.exhio_api.exhio_api.controller;

import com.exhio_api.exhio_api.dto.user.ReadUserDTO;
import com.exhio_api.exhio_api.dto.user.ReadUserDetailedDTO;
import com.exhio_api.exhio_api.dto.user.UpdateUserPermissionDTO;
import com.exhio_api.exhio_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{id}/permissions")
    @Transactional
    public ResponseEntity<ReadUserDetailedDTO> updatePermissions(@PathVariable(name = "id") Long id, @RequestBody @Valid UpdateUserPermissionDTO permissionDTO) {
        return userService.updatePermissions(id, permissionDTO);
    }

}
