package com.preproject.UserServices.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        String authHeader=httpServletRequest.getHeader("Authorization");
        if(authHeader==null|| !authHeader.startsWith("Bearer ")){
            System.out.println("AuthHeader"+authHeader);
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletOutputStream.print("Missing or Invalid Tocken ");
            servletOutputStream.close();

        }else{
            String jwtToken = authHeader.substring(7);
            Claims users= Jwts.parser().setSigningKey("code").parseClaimsJws(jwtToken).getBody();
            httpServletRequest.setAttribute("current user email", users.get("user_email"));
            httpServletRequest.setAttribute("current_role",users.get("user_role"));
            System.out.println("Filter ------------------"+users.get("user_role"));
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
