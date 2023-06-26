package widua.it.recruitmentEmpik;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class IntegrationTest {

	@Container
	static PostgreSQLContainer postgresql = new PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
			.withDatabaseName("requests")
			.withUsername("user")
			.withPassword("password");

	@DynamicPropertySource
	static void redisPropertySet(DynamicPropertyRegistry registry) {
		postgresql.start();
		registry.add("spring.datasource.port", postgresql::getFirstMappedPort);
		registry.add("spring.datasource.host", postgresql::getHost);
		registry.add("spring.datasource.url",postgresql::getJdbcUrl);
	}

}
