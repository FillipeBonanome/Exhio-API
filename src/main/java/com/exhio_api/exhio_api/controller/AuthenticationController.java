package com.exhio_api.exhio_api.controller;

import com.exhio_api.exhio_api.domain.User;
import com.exhio_api.exhio_api.dto.token.TokenDTO;
import com.exhio_api.exhio_api.dto.user.UserLoginDTO;
import com.exhio_api.exhio_api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<TokenDTO> loginUser(@RequestBody @Valid UserLoginDTO loginDTO) {
        var token = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.password());
        var authentication  = authenticationManager.authenticate(token);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }

}
