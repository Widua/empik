package widua.it.recruitmentEmpik;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
public abstract class TestContainerTest {

	private static final PostgreSQLContainer postgresql ;

	static{
		postgresql = new PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
				.withDatabaseName("requests")
				.withUsername("user")
				.withPassword("password");
	}

	@DynamicPropertySource
	static void postgresPropertySet(DynamicPropertyRegistry registry) {
		postgresql.start();
		registry.add("spring.datasource.port", postgresql::getFirstMappedPort);
		registry.add("spring.datasource.host", postgresql::getHost);
		registry.add("spring.datasource.url",postgresql::getJdbcUrl);
	}

	@Test
	void postgresRunning() {
		assertTrue(postgresql.isRunning());
	}

}
