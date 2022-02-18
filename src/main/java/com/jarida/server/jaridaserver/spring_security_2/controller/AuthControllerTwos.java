package com.jarida.server.jaridaserver.spring_security_2.controller;

import com.jarida.server.jaridaserver.spring_security_2.entity.RoleTwos;
import com.jarida.server.jaridaserver.spring_security_2.entity.UserTwos;
import com.jarida.server.jaridaserver.spring_security_2.exception.BadCredentialsExceptionTwos;
import com.jarida.server.jaridaserver.spring_security_2.exception.ResourceNotFoundExceptionTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.ApiResponseTwos;
import com.jarida.server.jaridaserver.spring_security_2.payload.*;
import com.jarida.server.jaridaserver.spring_security_2.repository.RoleRepositoryTwos;
import com.jarida.server.jaridaserver.spring_security_2.repository.UserRepositoryTwos;
import com.jarida.server.jaridaserver.spring_security_2.security.CustomUserDetailsServiceTwos;
import com.jarida.server.jaridaserver.spring_security_2.security.JwtTokenProviderTwos;
import com.jarida.server.jaridaserver.spring_security_2.service.impl.AuthServiceImplTwos;
import com.jarida.server.jaridaserver.spring_security_2.utils.PasswordEncoderGeneratorTwo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping("/api/v3")
@Validated
@Api(tags = "REST APIs for Sign-in, Sign-up and Profile")
@SwaggerDefinition(tags = {
        @Tag(name = "Security API 3.1", description = "This is for getting Security API 3.1")
})
@CrossOrigin
public class AuthControllerTwos {

    private final AuthenticationManager authenticationManagerTwos;

    private final UserRepositoryTwos userRepositoryTwos;

    private final RoleRepositoryTwos roleRepositoryTwos;

    private final PasswordEncoder passwordEncoderTwo;

    private final JwtTokenProviderTwos tokenProviderTwos;

    private final CustomUserDetailsServiceTwos customUserDetailsServiceTwos;

    private final AuthServiceImplTwos authService;

    @Autowired
    public AuthControllerTwos(AuthenticationManager authenticationManagerTwos, UserRepositoryTwos userRepositoryTwos, RoleRepositoryTwos roleRepositoryTwos, PasswordEncoder passwordEncoderTwo, JwtTokenProviderTwos tokenProviderTwos, CustomUserDetailsServiceTwos customUserDetailsServiceTwos, AuthServiceImplTwos authService) {
        this.authenticationManagerTwos = authenticationManagerTwos;
        this.userRepositoryTwos = userRepositoryTwos;
        this.roleRepositoryTwos = roleRepositoryTwos;
        this.passwordEncoderTwo = passwordEncoderTwo;
        this.tokenProviderTwos = tokenProviderTwos;
        this.customUserDetailsServiceTwos = customUserDetailsServiceTwos;
        this.authService = authService;
    }

    @ApiOperation(value = "REST API to Signin or Login user to Blog app")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponseTwos> authenticateUser(@Valid @RequestBody LoginDtoTwos loginDtoTwos) throws BadCredentialsExceptionTwos {

        try {
            Authentication authentication = authenticationManagerTwos.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDtoTwos.getUsernameOrEMail(), loginDtoTwos.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // get token form tokenProvider
            String token = tokenProviderTwos.generateToken(authentication);


            return ResponseEntity.ok(new JWTAuthResponseTwos(token, tokenProviderTwos.getExpiryDuration()));
        }catch (Exception e) {
            throw new BadCredentialsExceptionTwos(HttpStatus.BAD_REQUEST, "Invalid username or password");
        }
    }

    @ApiOperation(value = "REST API to Register or Signup user to Blog app")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseTwos> registerUser(@Valid @RequestBody SignUpDtoTwos signUpDtoTwos){

        // add check for username exists in a DB
        if(userRepositoryTwos.existsByUsername(signUpDtoTwos.getUserName())){
            throw new BadCredentialsExceptionTwos(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        // add check for email exists in DB
        if(userRepositoryTwos.existsByEmail(signUpDtoTwos.getEmail())){
            throw new BadCredentialsExceptionTwos(HttpStatus.BAD_REQUEST, "Email is already taken!");
        }

        // create user object
        UserTwos user = new UserTwos();
        user.setFirstName(signUpDtoTwos.getFirstName());
        user.setLastName(signUpDtoTwos.getLastName());
        user.setPhoneNumber(signUpDtoTwos.getPhoneNumber());
        user.setUsername(signUpDtoTwos.getUserName());
        user.setEmail(signUpDtoTwos.getEmail());
        user.setUserUUID(PasswordEncoderGeneratorTwo.generateRandomUuid());
        user.setPassword(passwordEncoderTwo.encode(signUpDtoTwos.getPassword()));

        RoleTwos roles = roleRepositoryTwos.findByName("ROLE_USER").get();
        user.setRoleTwos(Collections.singleton(roles));

       UserTwos result = userRepositoryTwos.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/{userId}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponseTwos(Boolean.TRUE, "User registered successfully"));

    }

    @ApiOperation(value = "REST API to get user profile")
    @GetMapping("/{username}/me")
    public ResponseEntity<UserDetails> currentUser(@PathVariable(value = "username") String username){

        UserDetails currentUser = customUserDetailsServiceTwos.loadUserByUsername(username);

        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }


    @ApiOperation(value = "REST API to get user profile")
    @GetMapping("/user/{userId}/profile")
    //@PreAuthorize(value = "hasRole('ROLE_USER')" + "and authentication.principal.username.equals(#userId) ")
    // @PreAuthorize("@permissionService.hasPermissionToModifyEvent(#userId)")
    @Transactional
    public ResponseEntity<UserTwos> profileUser(@PathVariable(value = "userId") long userId) throws NoHandlerFoundException {

            //authService.findByUserId(userId);

        try {

            if(!userRepositoryTwos.existsById(userId)){
                throw new ResourceNotFoundExceptionTwos("User", "id", userId);
            }

            UserTwos user = userRepositoryTwos.findById(userId).orElseThrow(
                    () -> new ResourceNotFoundExceptionTwos("User", "id", userId));



          /*  Optional<UserTwos> userTwosOptional = Optional.ofNullable(authService.findByUserId(userId)).
                    orElseThrow(() -> new ResourceNotFoundExceptionTwos("User", "Username", userId));*/

            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception e){
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }

    }

    @ApiOperation(value = "REST API to update name")
    @PutMapping("/user/{userId}/updateFirstnameLastname")
    // @PreAuthorize("@permissionService.hasPermissionToModifyEvent(#id)")
    @Transactional
    public ResponseEntity<ApiResponseTwos> updateFirstNameLastName(
            @PathVariable(value = "userId") long userId,
            @Valid @RequestBody UpdateNameRequestTwos updateNameRequestTwos
            ) throws NoHandlerFoundException {

        try {

            UserTwos user = userRepositoryTwos.findById(userId).orElseThrow(
                    () -> new ResourceNotFoundExceptionTwos("User", "id", userId));

            UserTwos userTwos = new UserTwos();
            userTwos.setFirstName(updateNameRequestTwos.getFirstName());
            userTwos.setLastName(updateNameRequestTwos.getLastName());

            userRepositoryTwos.updateName(userTwos.getFirstName(), userTwos.getLastName(), user.getId());

            ApiResponseTwos apiResponse = new ApiResponseTwos(Boolean.TRUE, "Username updated successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (Exception e) {
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }

    @ApiOperation(value = "REST API to update phone number")
    @PutMapping("/user/{userId}/updatePhonenumber")
    // @PreAuthorize("@permissionService.hasPermissionToModifyEvent(#id)")
    @Transactional
    public ResponseEntity<ApiResponseTwos> updatePhoneNumber(
            @PathVariable(value = "userId") long userId,
            @Valid @RequestBody UpdatePhoneNumberRequestTwos updatePhoneNumberRequest
    ) throws NoHandlerFoundException {

        try {

            UserTwos userTwos = new UserTwos();
            userTwos.setPhoneNumber(updatePhoneNumberRequest.getPhoneNumber());

            userRepositoryTwos.updatePhoneNumber(userTwos.getPhoneNumber(), userId);

            ApiResponseTwos apiResponse = new ApiResponseTwos(Boolean.TRUE, "Phone number updated successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }catch (Exception e) {
            throw new NoHandlerFoundException("", "", HttpHeaders.EMPTY);
        }
    }

    @ApiOperation(value = "REST API to update password")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Successful"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized: token has expired or is not valid"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "The resource you were trying to reach exist/conflict"),
            @io.swagger.annotations.ApiResponse(code = 415, message = "Unsupported Media Type"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error")
    })
    @PutMapping("/user/{userId}/passwordReset")
    // @PreAuthorize("@permissionService.hasPermissionToModifyEvent(#id)")
    @Transactional
    public ResponseEntity<ApiResponseTwos> updatePassword(
            @PathVariable(value = "userId") long userId,
            @Valid @RequestBody UpdatePasswordRequestTwos updatePasswordRequestTwos
    ) {

        authService.updatePassword(userId, updatePasswordRequestTwos);

        ApiResponseTwos apiResponse = new ApiResponseTwos(Boolean.TRUE, "Password updated successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @GetMapping("/user/tokenExpirationDate")
    public ResponseEntity<ApiResponseTwos> getTokenExpirationDate(
            @Valid @RequestParam("token") String token
    ) {
        Date tokens = tokenProviderTwos.getTokenExpiryFromJWT(token);

        ApiResponseTwos apiResponse = new ApiResponseTwos(Boolean.TRUE, tokens.toString());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}


