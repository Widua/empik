package widua.it.recruitmentEmpik.service;

import org.springframework.stereotype.Service;

@Service
public interface RequestCountingService {
    void registerNewRequest(String login);
}
