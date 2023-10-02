package sh.radical.basicauthtest.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sh.radical.basicauthtest.entities.UserContext;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Context {

	public UserContext userContext;

	public boolean isAuthenticated;
}
