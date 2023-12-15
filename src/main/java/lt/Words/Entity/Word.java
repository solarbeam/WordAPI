package lt.Words.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Word {

    @Id
    public Long id;
    public String value;
    public boolean isPalindrome;
}
