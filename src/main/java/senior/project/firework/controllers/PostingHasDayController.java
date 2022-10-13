package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Posting;
import senior.project.firework.models.PostingHasDay;
import senior.project.firework.repositories.repoPostingHasDay;
import senior.project.firework.repositories.repoPosting;

import java.util.List;

@RestController
public class
PostingHasDayController {
    @Autowired
    private repoPostingHasDay repoPostingHasDay;
    @Autowired
    private repoPosting repoPosting;

    @GetMapping("/main/allPostingHasDay")
    public List<PostingHasDay> allPostingHasDay(){
        return repoPostingHasDay.findAll();
    }

    @GetMapping("/main/selectPostingHasDay")
    public PostingHasDay selectPostingHasDay(@RequestParam(name = "idPostingHasDay") long idPostingHasDay){
        return repoPostingHasDay.findById(idPostingHasDay).orElse(null);
    }

    @GetMapping("/main/getPostingHasDayByPosting")
    public List<PostingHasDay> getPostingHasDayByPosting(@RequestParam(name = "idPosting") long idPosting){
        Posting posting = repoPosting.findById(idPosting).orElse(null);
        return repoPostingHasDay.getPostingHasDayByPosting(posting);
    }
}
