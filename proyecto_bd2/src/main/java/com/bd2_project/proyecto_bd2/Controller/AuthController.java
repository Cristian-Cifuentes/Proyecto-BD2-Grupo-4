package com.bd2_project.proyecto_bd2.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bd2_project.proyecto_bd2.Entity.UsuarioOracle;
import com.bd2_project.proyecto_bd2.Service.OracleAuthService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {

    private final OracleAuthService oracleAuthService;

    public AuthController(OracleAuthService oracleAuthService) {

        this.oracleAuthService = oracleAuthService;

    }

   @PostMapping("/login")
public ResponseEntity<Map<String, Object>> login(@RequestBody UsuarioOracle usuario) {
    boolean isAuthenticated = oracleAuthService.autenticar("C##" + usuario.getUsername(), usuario.getPassword());

    Map<String, Object> response = new HashMap<>();
    
    if (isAuthenticated) {
        response.put("message", "Autenticación exitosa");
        response.put("status", 200);
        return ResponseEntity.ok(response);
    } else {
        response.put("message", "Credenciales incorrectas");
        response.put("status", 401);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}


}
