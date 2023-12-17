package lt.Words.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Word {

    @Id
    private Long id;
    private String value;
    private boolean isPalindrome;
}
