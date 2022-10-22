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

    @GetMapping("/main/allDay")
    public List<Day> allDay(){
        return repoDay.findAll();
    }

    @GetMapping("/main/selectDay")
    public Optional<Day> selectDay(@RequestParam(name = "idDay") long idDay){
        return repoDay.findById(idDay);
    }

    @GetMapping("/main/getMondayToFriday")
    public List<Day> getMondayToFriday(){
        return repoDay.getMondayToFriday();
    }

    @GetMapping("/main/getSundayToSaturday")
    public List<Day> getSundayToSaturday(){
        return repoDay.getSundayToSaturday();
    }
}
