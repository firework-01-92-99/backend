package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senior.project.firework.exceptions.AccountException;
import senior.project.firework.exceptions.ExceptionRepo;
import senior.project.firework.models.Position;
import senior.project.firework.models.Employer;
import senior.project.firework.models.Posting;
import senior.project.firework.repositories.repoPosition;
import senior.project.firework.repositories.repoEmployer;
import senior.project.firework.repositories.repoPosting;

import java.util.List;

@RestController
public class PositionController {
    @Autowired
    private repoPosition repoPosition;
    @Autowired
    private repoEmployer repoEmployer;
    @Autowired
    private repoPosting repoPosting;

    @GetMapping("/admin/allPosition")
    public List<Position> allPosition(){
        return repoPosition.findAll();
    }

    @GetMapping("/emp/getMyPosition")
    public List<Position> getMyPosition(@RequestParam(name = "idEmployer") long idEmployer){
        Employer employer = repoEmployer.findById(idEmployer).orElse(null);
        return repoPosition.findByEmployer(employer);
    }

    @GetMapping("/emp/getMyPostingByPosition")
    public List<Posting> getMyPostingByPosition(@RequestParam(name = "idPosition") long idPosition){
        Position position = repoPosition.findById(idPosition).orElse(null);
        return repoPosting.findByPosition(position);
    }

    @PostMapping("/emp/createPosition")
    public void createPosition(@RequestParam(name = "namePosition") String namePosition,
                               @RequestParam(name = "idEmployer") long idEmployer){
        Employer employer = repoEmployer.findById(idEmployer).orElse(null);
        Position position = new Position(namePosition,employer);
        repoPosition.save(position);
    }

    @PutMapping("/emp/editPosition")
    public void editPosition(@RequestParam(name = "idPosition") long idPosition,
                             @RequestParam(name = "namePosition") String namePosition){
        Position position = repoPosition.findById(idPosition).orElse(null);
        position.setPositionName(namePosition);
        repoPosition.save(position);
    }

    @DeleteMapping("/emp/deletePosition")
    public void deletePosition(@RequestParam(name = "idPosition") long idPosition) throws Exception{
        Position position = repoPosition.findById(idPosition).orElse(null);
        if(!position.getPosting().isEmpty()){
            throw new AccountException(ExceptionRepo.ERROR_CODE.POSITION_POSTING_NOT_NULL,"Position have Posting!");
        }
        repoPosition.deleteById(idPosition);
    }
}
