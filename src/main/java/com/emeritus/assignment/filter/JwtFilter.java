package com.emeritus.assignment.filter;


import com.emeritus.assignment.entity.Role;
import com.emeritus.assignment.entity.SecurityUser;
import com.emeritus.assignment.entity.User;
import com.emeritus.assignment.service.SchoolUserDetailsService;
import com.emeritus.assignment.utility.JWTUtility;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private SchoolUserDetailsService authenticationService;
    @Autowired
    private JWTUtility jwtUtility;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException, ServletException {

        String authorization = httpServletRequest.getHeader("Authorization");
        String token = null;
        String userName = null;

        if(null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtUtility.getUsernameFromToken(token);
        }

        if(null!=userName && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails = authenticationService.loadUserByUsername(userName);
            System.out.println(userDetails.getAuthorities()     .toString());
            if(jwtUtility.validateToken(token,userDetails) )
            {
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null,userDetails.getAuthorities());
                authenticationToken.setDetails( new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }



        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }



}
