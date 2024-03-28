package quiz.service;
import quiz.models.Question;

public interface QuestionService {
    public void createQuestion(String testcode, Question question);
    public Question getQuestion(Long id);
    public void updateQuestion(Question question);
    public void deleteQuestion(Long id);
    public void printQuestion(Question question);
    
}
