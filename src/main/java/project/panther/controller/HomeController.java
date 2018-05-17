package project.panther.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.panther.model.Bruger;
import project.panther.model.GoogleMapMarker;
import project.panther.model.GoogleMapMarkerList;
import project.panther.model.FormattedMarkerData;
import project.panther.repository.MainDbRepository;
import project.panther.security.SecurityConfig;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private MainDbRepository repository;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping ("/opret-bruger")
    public String createAccount(@RequestParam String mail,
                                @RequestParam String fornavn,
                                @RequestParam String efternavn,
                                @RequestParam String telefonnummer,
                                @RequestParam String kodeord,
                                @RequestParam String kodeord2
                                ) {
        System.out.println("/opret-bruger er blevet startet");
        Bruger bruger = new Bruger(mail, fornavn,efternavn,telefonnummer,kodeord);
        bruger.setKodeord(encoder.encode(bruger.getKodeord()));
        repository.createBruger(bruger);

    return "redirect:/";
    }


   @PostMapping ("/login") //simpelt login-system
    public String login() {
            return "redirect:/mainpage";

    }
        @GetMapping ("/mainpage")
    public String mainpage() {
        return "mainpage";
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
