package widua.it.recruitmentEmpik.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import widua.it.recruitmentEmpik.models.RequestCountEntity;
import widua.it.recruitmentEmpik.repository.RequestCountRepository;

@Service
@AllArgsConstructor
public class RequestCountingServiceImpl implements RequestCountingService{
    private final RequestCountRepository countRepository;
    @Override
    public void registerNewRequest(String login) {
        if (countRepository.existsById(login)){
            countRepository.incrementRequestCount(login);
        } else {
            countRepository.save(new RequestCountEntity(login,1));
        }
    }
}
