package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import senior.project.firework.models.OTP;

import java.util.List;

public interface repoOTP extends JpaRepository<OTP,Long> {

    @Query(value = "select max(o.idOtp) from OTP o,Account a where o.account.idAccount = a.idAccount and a.idAccount = ?1 ")
    long getMaxIdOTP(long idAccount);
}
