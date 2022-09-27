package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import senior.project.firework.models.District;
import senior.project.firework.models.Province;

import java.util.List;

public interface repoDistrict extends JpaRepository<District,String> {

    District findByDistrictName(String district);
}
