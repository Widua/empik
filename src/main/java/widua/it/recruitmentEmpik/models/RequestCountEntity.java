package widua.it.recruitmentEmpik.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "REQUESTS")
@AllArgsConstructor
@NoArgsConstructor
public class RequestCountEntity {

    @Id
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "REQUEST_COUNT")
    private Integer requestCount;

}
