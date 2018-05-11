package project.panther.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.panther.model.Bruger;
import project.panther.model.GoogleMapMarkerList;
import project.panther.repository.MainDbRepository;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MainDbRepository repository;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping ("/opret-bruger")
    public String createAccount(@ModelAttribute Bruger bruger) {
        repository.createBruger(bruger);
    return "redirect:/";
    }

    @PostMapping ("/login")
    public String login() {

        return mainpage();
    }

    @PostMapping ("/mainpage")
    public String mainpage() {

        return "/mainpage";
    }

    @RequestMapping(value = "/fetch-markers", method= RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List fetchMarkers(){
        //opretter en markørliste
        GoogleMapMarkerList googleMapMarkerList = new GoogleMapMarkerList();
        googleMapMarkerList.setMarkerList(repository.readAllGoogleMapMarkers());

        return googleMapMarkerList.getMarkerList(); //viewmodel
    }

    @GetMapping("/lidt-om-os")
    public String omOs(Model model) {
        return "lidt-om-os";
    }

    @GetMapping ("/pantherprojektet")
    public String pantherProjektet(Model model) {
        return "pantherprojektet";
    }

    @GetMapping ("/samarbejdspartnere")
    public String partnere(Model model) {
        return "samarbejdspartnere";
    }

    @GetMapping ("/panther")
    public String panther(Model model){
        return "panther";
    }

}
