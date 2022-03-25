package senior.project.firework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senior.project.firework.models.Day;
import senior.project.firework.repositories.repoDay;

import java.util.List;
import java.util.Optional;

@RestController
public class DayController {
    @Autowired
    private repoDay repoDay;

    @GetMapping("/allDay")
    public List<Day> allDay(){
        return repoDay.findAll();
    }

    @GetMapping("/selectDay")
    public Optional<Day> selectDay(@RequestParam(name = "idDay") long idDay){
        return repoDay.findById(idDay);
    }
}
