package widua.it.recruitmentEmpik.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import widua.it.recruitmentEmpik.service.GithubUserClient;

@Configuration
public class GithubApiConfig {

    @Bean
    GithubUserClient githubUserClient(){
        WebClient client = WebClient.builder()
                .baseUrl("https://api.github.com/users")
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client))
                .build();

        return factory.createClient(GithubUserClient.class);
    }

}
