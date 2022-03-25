package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.repositories.repoLocation;

@RestController
public class LocationController {
    @Autowired
    private repoLocation repoLocation;
}
