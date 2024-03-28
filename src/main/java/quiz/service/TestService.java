package quiz.service;

import java.util.List;
import java.util.Set;

import quiz.models.Question;
import quiz.models.Test;

public interface TestService {
    public void createTest(Test test);
    public Test getTest(String testcode);
    public List<Test> getAllTests();
    public void updateTestName(String testcode, String testname);
    public void deleteTest(String testcode);
    public Set<Question> getAllQuestions(String testcode);
    public void startTest(String testName, Set<Question> question);
}
