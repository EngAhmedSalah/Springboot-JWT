package com.example.springjwtdemo.Controller;

import com.example.springjwtdemo.Model.AuthRequest;
import com.example.springjwtdemo.Model.AuthResponse;
import com.example.springjwtdemo.Utils.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class HomeController
{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetails userDetails;

    @Autowired
    JWTutil jwTutil;

    @RequestMapping("/")
    public String homeController()
    {
        return "home";
    }

    @PostMapping("/auth")
    public ResponseEntity createAuthToken(@RequestBody AuthRequest authRequest)
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername() , authRequest.getPassword()));
        }
        catch (BadCredentialsException b)
        {
            throw new BadCredentialsException("username or password incorrect");
        }
        UserDetails userDetail = userDetails.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwTutil.generateToken(userDetail);

        //Create a builder with the status set to OK
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
