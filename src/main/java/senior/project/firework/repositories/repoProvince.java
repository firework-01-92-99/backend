package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import senior.project.firework.models.Posting;
import senior.project.firework.models.Province;

import java.util.List;

public interface repoProvince extends JpaRepository<Province,String> {

    @Query(value = "SELECT * FROM firework.province order by firework.province.provinceName asc",nativeQuery = true)
    List<Province> ListProvinceSort();

}
