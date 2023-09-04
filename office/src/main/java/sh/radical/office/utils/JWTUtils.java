package sh.radical.office.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.stereotype.Component;
import sh.radical.office.entities.JwtObject;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JWTUtils {

    @Value(value = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUrl;





    ObjectMapper objectMapper = new ObjectMapper();

    public UsernamePasswordAuthenticationToken validateJwt (String token) {
        UsernamePasswordAuthenticationToken auth = null;
        try {
           var jwtDecoder = JwtDecoders.fromIssuerLocation(issuerUrl);
           var decodedToken =  jwtDecoder.decode(token);
           /**
           *  Either Class to decode JWT is not selected or userId mapping is not selected in Radical Authentication Settings
            *  So the code generated here is partial. Please update selection for fully generated code
           * */
           var jwtObject = objectMapper.convertValue(decodedToken.getClaims(),JwtObject.class);
           List<String> authoritiesData = List.of();

           //TODO Add authorization for JWT here

           var authorities = authoritiesData.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            //TODO use deserialized jwtObject to update UserName
           var user = new User(decodedToken.getSubject(),"", authorities);
           auth = new UsernamePasswordAuthenticationToken(user,jwtObject);
        }
        catch (Exception e) {
            log.error("Failed to validate token",e);
        }
        return auth;
    }
}
