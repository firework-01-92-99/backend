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

    @GetMapping("/admin/allPosition")
    public List<Position> allPosition(){
        return repoPosition.findAll();
    }

    @PostMapping("/admin/createPosition")
    public void createPosition(@RequestBody Position position){
        Position newPosition = new Position(position.getPositionName(),position.getStatus());
        repoPosition.save(newPosition);
    }
}
