package widua.it.recruitmentEmpik.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import widua.it.recruitmentEmpik.TestContainerTest;
import widua.it.recruitmentEmpik.repository.RequestCountRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppUserServiceImplTestIntegration extends TestContainerTest {

    private final AppUserService appUserService;
    private final RequestCountRepository countRepository;

    @Autowired
    public AppUserServiceImplTestIntegration(AppUserService appUserService, RequestCountRepository countRepository) {
        this.appUserService = appUserService;
        this.countRepository = countRepository;
    }

    @Test
    public void findingExistingUserShouldReturnsValidAppUserAndSaveRequestCountToDatabase(){
        String existingUserLogin = "octocat";

        assertDoesNotThrow( ()-> { appUserService.findUserByLogin(existingUserLogin); } );
        assertEquals(1,countRepository.findById(existingUserLogin).get().getRequestCount());
        appUserService.findUserByLogin(existingUserLogin);
        assertEquals(2, countRepository.findById(existingUserLogin).get().getRequestCount());
    }

}