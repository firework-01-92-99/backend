package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "posting")
public class Posting {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idPosting")
  private long idPosting;
  @Column(name = "sex")
  private String sex;
  @Column(name = "workDescription")
  private String workDescription;
  @Column(name = "minAge")
  private long minAge;
  @Column(name = "maxAge")
  private long maxAge;
  @Column(name = "minSalary")
  private long minSalary;
  @Column(name = "maxSalary")
  private long maxSalary;
  @Column(name = "overtimePayment")
  private String overtimePayment;
  @Column(name = "startTime")
  private String startTime;
  @Column(name = "endTime")
  private String endTime;
  @Column(name = "properties")
  private String properties;
  @Column(name = "welfare")
  private String welfare;
  @Column(name = "date")
  private ZonedDateTime date;
  @ManyToOne
  @JoinColumn(name = "hiring_type_idHiringtype")
  private HiringType hiringType;
  @Column(name = "employer_idEmployer" , updatable = false , insertable = false)
  private long idEmployer;
  @ManyToOne
  @JoinColumn(name = "employer_idEmployer")
  @JsonIgnore
  private Employer employer;
  @ManyToOne
  @JoinColumn(name = "status_idStatus")
  private Status status;
  @ManyToOne
  @JoinColumn(name = "WorkerType_idWorkerType")
  private WorkerType workerType;
  @OneToMany(mappedBy = "posting")
  private List<Application> applicationList;
  @OneToMany(mappedBy = "posting")
  private List<PostingHasDay> postingHasDayList;
  @ManyToOne
  @JoinColumn(name = "position_idposition")
  private Position position;
  @OneToMany(mappedBy = "posting")
  @JsonIgnore
  private List<Favorite> favoriteList;
  @OneToMany(mappedBy = "posting")
  @JsonIgnore
  private List<Ratings> ratingsList;
  @OneToMany(mappedBy = "posting")
  private List<PostingOpenClose> postingOpenCloseList;

  public Posting(){

  }

  public Posting(String sex, String workDescription, long minAge, long maxAge, long minSalary, long maxSalary, String overtimePayment, String startTime, String endTime, String properties, String welfare, ZonedDateTime date,HiringType hiringType, Employer employer, Status status, WorkerType workerType,Position position) {
    this.sex = sex;
    this.workDescription = workDescription;
    this.minAge = minAge;
    this.maxAge = maxAge;
    this.minSalary = minSalary;
    this.maxSalary = maxSalary;
    this.overtimePayment = overtimePayment;
    this.startTime = startTime;
    this.endTime = endTime;
    this.properties = properties;
    this.welfare = welfare;
    this.date = date;
    this.hiringType = hiringType;
    this.employer = employer;
    this.status = status;
    this.workerType = workerType;
    this.position = position;
  }

  public long getIdPosting() {
    return idPosting;
  }

  public void setIdPosting(long idPosting) {
    this.idPosting = idPosting;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getWorkDescription() {
    return workDescription;
  }

  public void setWorkDescription(String workDescription) {
    this.workDescription = workDescription;
  }

  public long getMinAge() {
    return minAge;
  }

  public void setMinAge(long minAge) {
    this.minAge = minAge;
  }

  public long getMaxAge() {
    return maxAge;
  }

  public void setMaxAge(long maxAge) {
    this.maxAge = maxAge;
  }

  public long getMinSalary() {
    return minSalary;
  }

  public void setMinSalary(long minSalary) {
    this.minSalary = minSalary;
  }

  public long getMaxSalary() {
    return maxSalary;
  }

  public void setMaxSalary(long maxSalary) {
    this.maxSalary = maxSalary;
  }

  public WorkerType getWorkerType() {
    return workerType;
  }

  public void setWorkerType(WorkerType workerType) {
    this.workerType = workerType;
  }

  public String getOvertimePayment() {
    return overtimePayment;
  }

  public void setOvertimePayment(String overtimePayment) {
    this.overtimePayment = overtimePayment;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getProperties() {
    return properties;
  }

  public void setProperties(String properties) {
    this.properties = properties;
  }

  public String getWelfare() {
    return welfare;
  }

  public void setWelfare(String welfare) {
    this.welfare = welfare;
  }

  public HiringType getHiringType() {
    return hiringType;
  }

  public void setHiringType(HiringType hiringType) {
    this.hiringType = hiringType;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public List<Application> getApplicationList() {
    return applicationList;
  }

  public void setApplicationList(List<Application> applicationList) {
    this.applicationList = applicationList;
  }

  public List<PostingHasDay> getPostingHasDayList() {
    return postingHasDayList;
  }

  public void setPostingHasDayList(List<PostingHasDay> postingHasDayList) {
    this.postingHasDayList = postingHasDayList;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public List<Favorite> getFavoriteList() {
    return favoriteList;
  }

  public void setFavoriteList(List<Favorite> favoriteList) {
    this.favoriteList = favoriteList;
  }

  public long getIdEmployer() {
    return idEmployer;
  }

  public void setIdEmployer(long idEmployer) {
    this.idEmployer = idEmployer;
  }

  public List<Ratings> getRatingsList() {
    return ratingsList;
  }

  public void setRatingsList(List<Ratings> ratingsList) {
    this.ratingsList = ratingsList;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public void setDate(ZonedDateTime date) {
    this.date = date;
  }

  public List<PostingOpenClose> getPostingOpenCloseList() {
    return postingOpenCloseList;
  }

  public void setPostingOpenCloseList(List<PostingOpenClose> postingOpenCloseList) {
    this.postingOpenCloseList = postingOpenCloseList;
  }
}
