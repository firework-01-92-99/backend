package senior.project.firework.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Posting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface repoPosting extends JpaRepository<Posting,Long> {

    @Query(value = "select * " +
            "FROM posting ps,employer e,hiring_type h,position po ,status s " +
            "where ps.position_idposition = po.idposition " +
            "and ps.employer_idEmployer = e.idEmployer " +
            "and ps.hiring_type_idHiringtype = h.idHiringtype " +
            "and ps.status_idStatus = s.idStatus " +
            "and ps.status_idStatus = 1 " +
            "and (e.establishmentName LIKE %:establishmentAndpositionName% or po.positionName LIKE %:establishmentAndpositionName%) " +
            "AND if(:idHiringtype !='',h.idHiringtype = :idHiringtype,h.idHiringtype is not null) " +
            "AND if(:idProvince !='',e.province_idProvince = :idProvince,e.province_idProvince is not null) " +
            "ORDER BY   (case when :sortSalary='DESC' then ps.maxSalary end ) DESC " +
            "           ,(case when :sortSalary='ASC' then ps.minSalary end) ASC " +
            "           ,(case when :sortSalary='' then ps.idPosting end) DESC ",nativeQuery = true)
    Page<Posting> searchPosting(String establishmentAndpositionName, String idHiringtype, String sortSalary, String idProvince,Pageable pageable );

    @Query(value =  "SELECT p FROM Posting p, Status s " +
            "WHERE p.status.idStatus = s.idStatus " +
            "AND p.status.idStatus = 1 ")
    Page<Posting> findAllActive(Pageable pageable);

    @Query(value =  "SELECT e FROM Posting p, Employer e " +
                    "WHERE p.employer.idEmployer = e.idEmployer AND p.idPosting = :idPosting ")
    Employer selectEmployerByPostingId(long idPosting);
}
