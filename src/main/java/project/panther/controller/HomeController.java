package project.panther.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.panther.model.Bruger;
import project.panther.model.GoogleMapMarkerList;
import project.panther.repository.MainDbRepository;

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
    public String login(Model model) {

        return mainpage(model);
    }

    @PostMapping ("/mainpage")
    public String mainpage(Model model) {

        //opretter en markørliste
        GoogleMapMarkerList googleMapMarkerList = new GoogleMapMarkerList();
        googleMapMarkerList.setMarkerList(repository.readAllGoogleMapMarkers());
        model.addAttribute(googleMapMarkerList.getMarkerList());

        return "/mainpage";
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
