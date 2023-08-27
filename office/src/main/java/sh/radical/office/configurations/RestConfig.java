package sh.radical.office.configurations;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import sh.radical.office.rest.DefaultService;

@Configuration
public class RestConfig {

	@Value(value = "${app.defaultservice.timeoutinmillis:45000}")
	Integer defaultServiceTimeOutInMilliSeconds;

	private OkHttpClient getClient(
		Integer timeout,
		HashMap<String, String> headers
	) {
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		httpClient.addInterceptor(chain -> {
			Request original = chain.request();

			var requestBuilder = original
				.newBuilder()
				.method(original.method(), original.body());
			headers.forEach((k, v) -> {
				requestBuilder.addHeader(k, v);
			});

			return chain.proceed(requestBuilder.build());
		});
		OkHttpClient client = httpClient
			.callTimeout(timeout, TimeUnit.MILLISECONDS)
			.build();
		return client;
	}

	@Bean
	public DefaultService provideDefaultService() {
		var client = getClient(
			defaultServiceTimeOutInMilliSeconds,
			new HashMap<>() {
				{
					put("Content-Type", "application/json");
				}
			}
		);

		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("https://radical.sh/api/")
			.client(client)
			.build();

		DefaultService service = retrofit.create(DefaultService.class);
		return service;
	}
}
