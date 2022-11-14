package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.exceptions.AccountException;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.Position;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Posting;
import senior.project.firework.models.Status;
import senior.project.firework.repositories.repoPosition;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoPosting;
import senior.project.firework.repositories.repoStatus;

import java.util.List;

@RestController
public class PositionController {
    @Autowired
    private repoPosition repoPosition;
    @Autowired
    private repoStatus repoStatus;

    @GetMapping("/admin_emp/allPosition")
    public List<Position> allPosition(){
        return repoPosition.findAll();
    }

    @GetMapping("/admin_emp/allActivePosition")
    public List<Position> allActivePosition(){
        Status status = repoStatus.findById(1L).orElse(null);
        return repoPosition.findByStatusOrderByPositionNameAsc(status);
    }

    @GetMapping("/admin_emp/allInactivePosition")
    public List<Position> allInactivePosition(){
        Status status = repoStatus.findById(2L).orElse(null);
        return repoPosition.findByStatusOrderByPositionNameAsc(status);
    }

    @GetMapping("/admin_emp/allWaitingPosition")
    public List<Position> allWaitingPosition(){
        Status status = repoStatus.findById(3L).orElse(null);
        return repoPosition.findByStatusOrderByPositionNameAsc(status);
    }

    @GetMapping("/admin_emp/allDeletePosition")
    public List<Position> allDeletePosition(){
        Status status = repoStatus.findById(9L).orElse(null);
        return repoPosition.findByStatusOrderByPositionNameAsc(status);
    }

    @GetMapping("/admin_emp/selectPosition")
    public Position selectPosition(@RequestParam(name = "idPosition") long idPosition){
        return repoPosition.findById(idPosition).orElse(null);
    }

    @PostMapping("/admin/createPosition")
    public void createPosition(@RequestBody Position position) throws Exception {
        if(repoPosition.findByPositionName(position.getPositionName()) != null){
            throw new AccountException(ExceptionRepo.ERROR_CODE.POSITION_NAME,"This position name have already.");
        }
        Status status = repoStatus.findById(1L).orElse(null);
        Position newPosition = new Position(position.getPositionName(),status);
        repoPosition.save(newPosition);
    }

    @PutMapping("/admin/editPosition")
    public void editPosition(@RequestBody Position position) throws Exception {
        if(repoPosition.findByPositionName(position.getPositionName()) != null){
            throw new AccountException(ExceptionRepo.ERROR_CODE.POSITION_NAME,"This position name have already.");
        }
        Position newPosition = repoPosition.findById(position.getIdposition()).orElse(null);
        newPosition.setPositionName(position.getPositionName());
        repoPosition.save(newPosition);
    }
    //-------------------------------------------------------------------------------------------------------------
    @PostMapping("/emp/empRequestPosition")
    public void empRequestPosition(@RequestBody Position position) throws Exception {
        if(repoPosition.findByPositionName(position.getPositionName()) != null){
            throw new AccountException(ExceptionRepo.ERROR_CODE.POSITION_NAME,"This position name have already.");
        }
        Status status = repoStatus.findById(3L).orElse(null);
        Position newPosition = new Position(position.getPositionName(),status);
        repoPosition.save(newPosition);
    }

    @DeleteMapping("/admin/rejectEmpRequest")//กรณีที่ emp ขอมาแล้ว ไม่เอา กูให้แม่งลบหายตายจากไปเลย จะได้ไม่คาใน db
    public void deletePosition(@RequestParam(name = "idPosition") long idPosition) throws Exception {
        repoPosition.deleteById(idPosition);
    }

    @PutMapping("/admin/adminActivePosition")
    public void adminActivePosition(@RequestParam(name = "idPosition") long idPosition) throws Exception {
        Position position = repoPosition.findById(idPosition).orElse(null);
        Status status = repoStatus.findById(1L).orElse(null);
        position.setStatus(status);
        repoPosition.save(position);
    }

    @PutMapping("/admin/adminInactivePosition")
    public void adminInactivePosition(@RequestParam(name = "idPosition") long idPosition) throws Exception {
        Position position = repoPosition.findById(idPosition).orElse(null);
        Status status = repoStatus.findById(2L).orElse(null);
        position.setStatus(status);
        repoPosition.save(position);
    }

    @PutMapping("/admin/adminDeletePosition")//ลบแบบยังอยู่นะ แต่สถานะเป็น deleted
    public void adminDeletePosition(@RequestParam(name = "idPosition") long idPosition) throws Exception {
        Position position = repoPosition.findById(idPosition).orElse(null);
        Status status = repoStatus.findById(9L).orElse(null);
        position.setStatus(status);
        repoPosition.save(position);
    }
}
