package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.repositories.repoLocationPic;

@RestController
public class LocationPicController {

    @Autowired
    private repoLocationPic repoLocationPic;
}
