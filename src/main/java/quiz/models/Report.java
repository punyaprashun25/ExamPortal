package quiz.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;
    private String testName;
    @Temporal(TemporalType.DATE)
    private Date addedDate;
    @Temporal(TemporalType.TIME)
    private Date addedTime;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    public Report(){}
    public Report(Long reportId, String testName, Date addedDate, Date addedTime, User user) {
        this.reportId = reportId;
        this.testName = testName;
        this.addedDate = addedDate;
        this.addedTime = addedTime;
        this.user = user;
    }
    public Long getReportId() {
        return reportId;
    }
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }
    public String getTestName() {
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    public Date getAddedDate() {
        return addedDate;
    }
    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
    public Date getAddedTime() {
        return addedTime;
    }
    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    
}
