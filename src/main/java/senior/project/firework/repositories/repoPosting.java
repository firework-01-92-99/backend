package senior.project.firework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import senior.project.firework.models.Posting;

import java.util.List;

public interface repoPosting extends JpaRepository<Posting,Long> {

    @Query(value = "SELECT * " +
            "FROM posting ps,employer e,hiring_type h,position po " +
            "WHERE ps.position_idposition = po.idposition " +
            "AND ps.employer_idEmployer = e.idEmployer " +
            "AND ps.hiring_type_idHiringtype = h.idHiringtype " +
            "AND e.establishmentName LIKE %:establishmentName% " +
            "AND po.positionName LIKE %:positionName% " +
            "AND if(:idHiringtype !='',h.idHiringtype = :idHiringtype,h.idHiringtype is not null) " +
            "AND if(:idProvince !='',e.province_idProvince = :idProvince,e.province_idProvince is not null) " +
            "AND if(:idDistrict !='',e.district_idDistrict = :idDistrict,e.district_idDistrict is not null) " +
            "AND if(:idSubdistrict !='',e.sub_district_idSubdistrict = :idSubdistrict,e.sub_district_idSubdistrict is not null) " +
            "ORDER BY   (case when :sortSalary='DESC' then ps.maxSalary end ) DESC " +
            "           ,(case when :sortSalary='ASC' then ps.minSalary end) ASC " +
            "           ,(case when :sortSalary='' then ps.idPosting end) DESC ",nativeQuery = true)
    List<Posting> searchPosting(String establishmentName, String positionName, String idHiringtype, String idProvince, String idDistrict, String idSubdistrict, String sortSalary);

}
