package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Posting;
import senior.project.firework.repositories.repoPosting;

import java.util.List;

@RestController
public class PostingController {
    @Autowired
    private repoPosting repoPosting;

    @GetMapping("/allPosting")
    public List<Posting> allPosting(){
        return repoPosting.findAll();
    }
}
