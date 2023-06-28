package widua.it.recruitmentEmpik.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import widua.it.recruitmentEmpik.TestContainerTest;
import widua.it.recruitmentEmpik.models.RequestCountEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RequestCountRepositoryTestIntegration extends TestContainerTest {


    private final RequestCountRepository underTest;
    @Autowired
    public RequestCountRepositoryTestIntegration(RequestCountRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void requestCountShouldIncreaseWhenMethodIsCalled(){
        // Given
        String login = "widua";
        RequestCountEntity entity = new RequestCountEntity(login,1);
        underTest.save(entity);
        // When
        underTest.incrementRequestCount(login);
        // Then
        RequestCountEntity databaseEntity = underTest.findById(login).get();
        assertEquals(2,databaseEntity.getRequestCount());

    }

}