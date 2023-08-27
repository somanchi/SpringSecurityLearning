package sh.radical.office.entities;

import lombok.Data;

import java.util.Date;

@Data
public class JwtObject {
    public String iss;
    public String sub;
    public String aud;
    public String azp;
    public Date exp;
    public Date iat;
    public String gty;
}
