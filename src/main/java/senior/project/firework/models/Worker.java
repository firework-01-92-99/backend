package senior.project.firework.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idWorker")
  private long idWorker;
  @Column(name = "identificationNumber")
  private String identificationNumber;
  @Column(name = "verifyPic")
  private String verifyPic;
  @Column(name = "sex")
  private String sex;
  @Column(name = "firstName")
  private String firstName;
  @Column(name = "middleName")
  private String middleName;
  @Column(name = "lastName")
  private String lastName;
  @Column(name = "phone")
  private String phone;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_idAccount")
  @JsonIgnore
  private Account account;
  @OneToMany(mappedBy = "worker")
  private List<Application> applicationList;
  @ManyToOne
  @JoinColumn(name = "WorkerType_idWorkerType")
  private WorkerType workerType;
  @OneToMany(mappedBy = "worker")
  private List<Favorite> favoriteList;
  @OneToMany(mappedBy = "worker")
  private List<Ratings> ratingsList;
  @OneToMany(mappedBy = "worker")
  private List<EditWorker> editWorkerList;
  @ManyToOne
  @JoinColumn(name = "nationality_idnationality")
  private Nationality nationality;

  public Worker(String identificationNumber, String verifyPic, String sex, String firstName, String middleName, String lastName, String phone, Account account, Nationality nationality, WorkerType workerType) {
    this.identificationNumber = identificationNumber;
    this.verifyPic = verifyPic;
    this.sex = sex;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.phone = phone;
    this.account = account;
    this.nationality = nationality;
    this.workerType = workerType;
  }

  public Worker() {

  }

  public List<EditWorker> getEditWorkerList() {
    return editWorkerList;
  }

  public void setEditWorkerList(List<EditWorker> editWorkerList) {
    this.editWorkerList = editWorkerList;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public long getIdWorker() {
    return idWorker;
  }

  public void setIdWorker(long idWorker) {
    this.idWorker = idWorker;
  }

  public String getIdentificationNumber() {
    return identificationNumber;
  }

  public void setIdentificationNumber(String identificationNumber) {
    this.identificationNumber = identificationNumber;
  }

  public String getVerifyPic() {
    return verifyPic;
  }

  public void setVerifyPic(String verifyPic) {
    this.verifyPic = verifyPic;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public List<Application> getApplicationList() {
    return applicationList;
  }

  public void setApplicationList(List<Application> applicationList) {
    this.applicationList = applicationList;
  }

  public WorkerType getWorkerType() {
    return workerType;
  }

  public void setWorkerType(WorkerType workerType) {
    this.workerType = workerType;
  }

  public List<Favorite> getFavoriteList() {
    return favoriteList;
  }

  public void setFavoriteList(List<Favorite> favoriteList) {
    this.favoriteList = favoriteList;
  }

  public List<Ratings> getRatingsList() {
    return ratingsList;
  }

  public void setRatingsList(List<Ratings> ratingsList) {
    this.ratingsList = ratingsList;
  }

  public Nationality getNationality() {
    return nationality;
  }

  public void setNationality(Nationality nationality) {
    this.nationality = nationality;
  }
}
