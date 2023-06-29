package widua.it.recruitmentEmpik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import widua.it.recruitmentEmpik.models.RequestCountEntity;
import widua.it.recruitmentEmpik.repository.RequestCountRepository;

@Service
@RequiredArgsConstructor
public class RequestCountingServiceImpl implements RequestCountingService{
    private final RequestCountRepository countRepository;
    @Override
    @Transactional
    public void registerNewRequest(String login) {
        if (countRepository.existsById(login)){
            countRepository.incrementRequestCount(login);
        } else {
            countRepository.save(new RequestCountEntity(login,1));
        }
    }
}
