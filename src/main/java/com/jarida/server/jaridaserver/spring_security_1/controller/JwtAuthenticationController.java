/*
package com.jarida.server.jaridaserver.spring_security_1.controller;

import com.jarida.server.jaridaserver.spring_security_1.config.JwtTokenUtil;
import com.jarida.server.jaridaserver.spring_security_1.model.JwtRequest;
import com.jarida.server.jaridaserver.spring_security_1.model.JwtResponse;
import com.jarida.server.jaridaserver.spring_security_1.model.UserDto;
import com.jarida.server.jaridaserver.spring_security_1.service.JwtUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3")
@Validated
@Api(tags = "Security API v3 - Not Working")
// https://www.techgeeknext.com/spring/spring-boot-security-token-authentication-jwt-mysql
@SwaggerDefinition(tags = {
        @Tag(name = "Security API 3", description = "This is for getting Security API 3")
})
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<?> getUser(Authentication authentication) {
        return ResponseEntity.ok(authentication.getName());
        //return ResponseEntity.ok(userDetailsService.loadUserByUsername(authentication));
        // return ResponseEntity.ok(userDetailsService.getUsername(authentication));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {

        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtTokenUtil.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("You've been signed out!");
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
*/
