package com.myapp.myapp.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.myapp.Security.AuthenticationRequest;
import com.myapp.myapp.Security.AuthenticationResponse;
import com.myapp.myapp.Security.JwtUtil;
import com.myapp.myapp.Security.UserDetailsImpl;
import com.myapp.myapp.Security.UserDetailsServiceImpl;



@CrossOrigin
@RestController
public class JwtAuthenticationController {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("wrong password or email");
        }

        final UserDetailsImpl userDetails = userDetailsServiceImpl
                .loadUserByUsername(authenticationRequest.getEmail());
        if(userDetails.isIsactive()==true){
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println(jwt);

        return ResponseEntity.ok(new AuthenticationResponse(jwt,userDetails.getId()));
    }
        else throw new BadCredentialsException("compte not activeted"); }
}
	