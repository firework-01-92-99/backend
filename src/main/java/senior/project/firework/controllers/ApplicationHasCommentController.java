package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.ApplicationHasComment;
import senior.project.firework.repositories.repoApplicationHasComment;

import java.util.List;

@RestController
public class ApplicationHasCommentController {
    @Autowired
    private repoApplicationHasComment repoApplicationHasComment;

    @GetMapping("/allApplicationHasComment")
    public List<ApplicationHasComment> allApplicationHasComment(){
        return repoApplicationHasComment.findAll();
    }
}
