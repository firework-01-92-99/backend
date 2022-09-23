package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "edit_worker")
public class EditWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEditWorker")
    private long idEditWorker;
    @Column(name = "verifyPic")
    private String verifyPic;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "middleName")
    private String middleName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @ManyToOne
    @JoinColumn(name = "worker_idWorker")
    @JsonIgnore
    private Worker worker;

    public EditWorker(){

    }

    public EditWorker(long idEditWorker, String verifyPic, String firstName, String middleName, String lastName, String phone, Worker worker) {
        this.idEditWorker = idEditWorker;
        this.verifyPic = verifyPic;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.worker = worker;
    }

    public EditWorker(String verifyPic, String firstName, String middleName, String lastName, String phone, Worker worker) {
        this.verifyPic = verifyPic;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.worker = worker;
    }

    public long getIdEditWorker() {
        return idEditWorker;
    }

    public void setIdEditWorker(long idEditWorker) {
        this.idEditWorker = idEditWorker;
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

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
