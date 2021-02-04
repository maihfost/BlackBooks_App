package com.pcedu.blackbooks.security.controllers;

import com.pcedu.blackbooks.dao.RoleDao;
import com.pcedu.blackbooks.dao.UserDao;
import com.pcedu.blackbooks.dao.UserRoleDao;
import com.pcedu.blackbooks.entities.Role;
import com.pcedu.blackbooks.entities.User;
import com.pcedu.blackbooks.entities.UserRole;
import com.pcedu.blackbooks.security.jwt.JwtUtils;
import com.pcedu.blackbooks.security.payload.request.LoginRequest;
import com.pcedu.blackbooks.security.payload.request.SignupRequest;
import com.pcedu.blackbooks.security.payload.response.JwtResponse;
import com.pcedu.blackbooks.security.payload.response.MessageResponse;
import com.pcedu.blackbooks.services.UserDetailsImpl;
import com.pcedu.blackbooks.services.UserService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDao userDao;
    
    @Autowired
    UserService userService;

    @Autowired
    UserRoleDao userRoleDao;
    
    @Autowired
    RoleDao roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("userDetails" + userDetails.isAccountNonExpired());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    
    
    @PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userDao.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userDao.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User();
                user.setActive(Boolean.TRUE);
                user.setUserName(signUpRequest.getUsername());
                user.setPassword(encoder.encode(signUpRequest.getPassword()));
                user.setEmail(signUpRequest.getEmail());
                user.setFirstName(signUpRequest.getFirstname());
                user.setLastName(signUpRequest.getLastname());
                user.setDigitWallet(signUpRequest.getDigitwallet());
                user.setTotalPaymentAmount(BigDecimal.ZERO);
                user.setCountry(signUpRequest.getCountry());
                user.setDateofbirth(signUpRequest.getDateofbirth());
                user.setTelephone(signUpRequest.getTelephone());
                Role role = roleRepository.findByName("user");
                
		userService.save(user);
                user = userService.findByUserName(user.getUserName());
                
                UserRole userRole = new UserRole();
                userRole.setRoleId(role);
                userRole.setUserId(user);
                userRoleDao.save(userRole);
                
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
