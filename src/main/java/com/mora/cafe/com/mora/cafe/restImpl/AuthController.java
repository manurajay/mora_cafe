package com.mora.cafe.com.mora.cafe.restImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mora.cafe.com.mora.cafe.POJO.ERole;
import com.mora.cafe.com.mora.cafe.POJO.Role;
import com.mora.cafe.com.mora.cafe.POJO.User;
import com.mora.cafe.com.mora.cafe.dto.request.LoginUserDto;
import com.mora.cafe.com.mora.cafe.dto.request.RegisterUserDto;
import com.mora.cafe.com.mora.cafe.dto.response.LoginResponse;
import com.mora.cafe.com.mora.cafe.dto.response.MessageResponse;
import com.mora.cafe.com.mora.cafe.repo.RoleRepository;
import com.mora.cafe.com.mora.cafe.repo.UserRepository;
import com.mora.cafe.com.mora.cafe.security.jwt.JwtUtils;
import com.mora.cafe.com.mora.cafe.security.services.UserDetailsImpl;
import com.mora.cafe.com.mora.cafe.serviceImpl.UserService;
import com.mora.cafe.com.mora.cafe.wrapper.UserWrapper;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  private UserService userService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginUserDto loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    System.out.println("authorites " + userDetails.getAuthorities());
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    System.out.println("these are roles " + roles);
    if (userRepository.findByEmail(userDetails.getEmail()).getStatus()) {
      return ResponseEntity.ok(new LoginResponse(jwt,
              userDetails.getId(),
              userDetails.getUsername(),
              userDetails.getEmail(),
              roles));
    } else {
      return ResponseEntity.badRequest().body("Waiting for admin approval.");
    }


  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDto signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    if (strRoles.contains("admin")) {
      user.setStatus();
    }
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @GetMapping("/allUsers")
  public ResponseEntity<List<UserWrapper>> getAllUsers() {
    try {
      return (ResponseEntity<List<UserWrapper>>) userService.allUsers();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
