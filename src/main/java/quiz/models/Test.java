package quiz.models;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "tests")
public class Test {
    @Id
    private String testCode;
    private String testName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(nullable = true)
    Set<Question> quesions = new HashSet<Question>();
    public Test(){}
    public Test(String testCode, String testName) {
        this.testCode = testCode;
        this.testName = testName;
    }
    
    public Set<Question> getQuesions() {
        return quesions;
    }
    public void setQuesions(Set<Question> quesions) {
        this.quesions = quesions;
    }
    public String getTestName() {
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    public String getTestCode() {
        return testCode;
    }
    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

}
