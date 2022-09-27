package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senior.project.firework.models.SubDistrict;

public interface repoSubDistrict extends JpaRepository<SubDistrict,String> {
    SubDistrict findByPostcode(String postcode);
}
