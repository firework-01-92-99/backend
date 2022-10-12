package senior.project.firework.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "otp")
public class OTP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOtp")
    private long idOtp;
    @Column(name = "otpNo")
    private long otpNo;
    @ManyToOne
    @JoinColumn(name = "account_idAccount")
    @JsonIgnore
    private Account account;

    public OTP(){

    }

    public OTP(long otpNo, Account account) {
        this.otpNo = otpNo;
        this.account = account;
    }

    public long getIdOtp() {
        return idOtp;
    }

    public void setIdOtp(long idOtp) {
        this.idOtp = idOtp;
    }

    public long getOtpNo() {
        return otpNo;
    }

    public void setOtpNo(long otpNo) {
        this.otpNo = otpNo;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
