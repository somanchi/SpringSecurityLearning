package sh.radical.testingid.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sh.radical.testingid.entities.UserContext;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Context {

	private UserContext userContext;

	private boolean isAuthenticated;
}
