package project.panther.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.panther.model.Bruger;
import project.panther.model.GoogleMapMarker;
import project.panther.model.GoogleMapMarkerList;
import project.panther.model.FormattedMarkerData;
import project.panther.repository.MainDbRepository;

import java.util.ArrayList;
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
    
    //----------------------------------------------------------------------------------
    //TODO lav et ordentlig loginsystem.

    //skal slettes:
    boolean loggedIn = false;

    @PostMapping ("/login") //simpelt login-system
    public String login(@ModelAttribute Bruger bruger) {
        Bruger compareBruger = repository.readBruger(bruger.getMail());
        if(bruger.getKodeord().equals(compareBruger.getKodeord())) {
            loggedIn = true;
            return "redirect:/mainpage";
        }
        return "redirect:/";
    }

    @GetMapping ("/mainpage")
    public String mainpage() {
        if(loggedIn) {
            return "/mainpage";
        }
        return "redirect:/";
    }
    //----------------------------------------------------------------------------------

    @RequestMapping(value = "/fetch-markers", method= RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List formattedMarkerData(){
        //opretter en markørliste
        GoogleMapMarkerList googleMapMarkerList = new GoogleMapMarkerList();
        googleMapMarkerList.setMarkerList(repository.readAllGoogleMapMarkers());

        //her sørger vi for udelukkende at sende brugbar data med
        List<FormattedMarkerData> formattedData = new ArrayList<>();
        for (int i = 0; i < googleMapMarkerList.getMarkerList().size(); i++) {
            GoogleMapMarker m = googleMapMarkerList.getMarkerList().get(i);
            Bruger b = repository.readBruger(m.getBrugerID());
            formattedData.add(new FormattedMarkerData(
                    m.getLatitude(), m.getLongitude(),
                    m.getOprettelsesTidspunkt(),
                    m.getAfslutningsTidspunkt(),
                    m.getPant().getEstimeretBeloeb(),
                    m.getPant().getPantBilledSti(),
                    b.getFornavn(),
                    b.getEfternavn(),
                    b.getTelefonnummer(),
                    b.getProfilbilledeSti()
            ));
        }

        return formattedData;                          //viewmodel
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
