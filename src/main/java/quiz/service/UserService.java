package quiz.service;
import quiz.models.User;

public interface UserService {
    public boolean createUser(User user);
    public User getUser(String Username);
    public void updateAttempt(String username, Integer count);
    public void blockUser(String username);
    public void unblockUser(String username);
    public void updatePassword(String username, String new_password);
    public void updateBasicDeails(User user);
    public void deleteUser(String username);
}
