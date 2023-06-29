package widua.it.recruitmentEmpik.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import widua.it.recruitmentEmpik.models.RequestCountEntity;

@Repository
public interface RequestCountRepository extends CrudRepository<RequestCountEntity,String> {

    @Query(value="UPDATE RequestCountEntity rs SET rs.requestCount = rs.requestCount + 1 WHERE rs.login = :login")
    @Modifying
     public void incrementRequestCount(@Param("login") String login);


}
