package uz.jurayev.academy.security;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.jurayev.academy.service.impl.UserDetailsServiceImpl;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthTokenFilter.class);

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

                String headerAuth = request.getHeader("Authorization");
                String jwtTOKEN = null;
                if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(SecurityConstant.TOKEN_HEADER)) {
                    jwtTOKEN = headerAuth.substring(7);
                }

             try {
                 if (jwtTOKEN != null && jwtUtils.validateJwtToken(jwtTOKEN)) {
                     String username = jwtUtils.getUsernameFromJwtToken(jwtTOKEN);
                     UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                     UsernamePasswordAuthenticationToken authenticationToken =
                             new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                     authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                 }
             }
            catch (Exception e) {
                LOG.error("Cannot set user authentication: {}", e.getMessage());
            }
            filterChain.doFilter(request, response);
    }
}
