package com.example.StudentJWT.security;

import com.example.StudentJWT.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


// JWT Auth Filter: Extracts token from Authorization header,
// validates it, loads user details, and if valid, sets the user
// into Spring Security’s context so secured endpoints know who the user is.

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


// 1. Extract JWT token from Authorization header (Bearer <token>).
// 2. Validate the token and extract the user’s email/username.
// 3. Load the user’s details from the database using UserDetailsService.
// 4. If the token is valid, create an Authentication object with roles/authorities.
// 5. Store this Authentication in SecurityContext so Spring Security knows the user.
// 6. Pass the request forward in the filter chain.
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization"); //standard HTTP header name used worldwide for sending authentication info.
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {  //If there’s no header → request goes forward without authentication.
                                                                        //If the header doesn’t start with "Bearer " → also just pass the request along
            filterChain.doFilter(request, response); //“I won’t touch this request, let the next filter/controller handle it.”
            return;
        }

        //What is "Bearer"? Why check it?
        //
        //"Bearer" is just a label that tells the server what kind of authentication token this is.
        //
        //There are different auth schemes like:
        //
        //Basic <base64-encoded username:password>
        //
        //Bearer <JWT token>

        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { //If we did find an email in the token, and the user is not already logged in for this request, continue
                                                                   //SecurityContextHolder stores the current thread’s authentication. If it’s null, no one has authenticated this request yet.

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);  //Load the user from your system using the email (their account, roles, status).


            if (jwtService.isTokenValid(jwt, userDetails)) {  //Typically verifies: signature matches your secret, token not expired, and subject equals this user.

                //Create an “I vouch for this user” object with their roles.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails( //Attach request info (like IP and session id) to the authentication for auditing or features that rely on request metadata.
                        new WebAuthenticationDetailsSource().buildDetails(request)  //WebAuthenticationDetails holds remoteAddress, sessionId, etc. Some components/logging use it.
                );
                SecurityContextHolder.getContext().setAuthentication(authToken); //Mark the user as authenticated for the rest of this request.
            }
        }
        filterChain.doFilter(request, response); //Always pass the request onward (to the next filter or your controller).
    }
}