package quiz.service;
import quiz.models.Report;

public interface ReportService {
    public Report getResult(Long reportId);
    public void CreateResult(Report report);

}
