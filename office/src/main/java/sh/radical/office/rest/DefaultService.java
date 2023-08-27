package sh.radical.office.rest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DefaultService {
	@GET
	Call<?> tableau();
}
