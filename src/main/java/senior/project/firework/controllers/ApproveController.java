package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Approve;
import senior.project.firework.repositories.repoApprove;

import java.util.List;

@RestController
public class ApproveController {
    @Autowired
    private repoApprove repoApprove;

    @GetMapping("/admin/allApprove")
    public List<Approve> allApprove(){
        return repoApprove.findAll();
    }

}
