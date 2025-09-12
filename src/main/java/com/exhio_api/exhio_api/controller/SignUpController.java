package com.exhio_api.exhio_api.controller;

import com.exhio_api.exhio_api.domain.User;
import com.exhio_api.exhio_api.dto.user.CreateUserDTO;
import com.exhio_api.exhio_api.dto.user.ReadUserDTO;
import com.exhio_api.exhio_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<ReadUserDTO> registerUser(@RequestBody @Valid CreateUserDTO userDTO, UriComponentsBuilder uriBuilder) throws Exception {
        ReadUserDTO user = userService.registerUser(userDTO);
        return ResponseEntity.created(uriBuilder.path("/user/id").buildAndExpand(user.id()).toUri()).body(user);
    }

}
