/* Written by: Michael Hansen, Max Møller Hoffmeyer, Thomas Bo Nielsen & Sidney Schultz */

package project.panther.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import project.panther.model.*;
import project.panther.repository.MainDbRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private MainDbRepository repository;

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @PostMapping ("/opret-bruger")
    public String createAccount(@RequestParam String mail,
                                @RequestParam String fornavn,
                                @RequestParam String efternavn,
                                @RequestParam String telefonnummer,
                                @RequestParam String kodeord
                                ) {
        Bruger bruger = new Bruger(mail, fornavn,efternavn,telefonnummer,kodeord);
        bruger.setKodeord(encoder.encode(bruger.getKodeord()));
        repository.createBruger(bruger);

        return "redirect:/";
    }

    @GetMapping ("/mainpage")
    public String mainpage(Model model) {

        //TODO dette kald skal laves om til en funtkion, da vi bruger den ofte.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        Bruger b = repository.readBruger(mail);

        model.addAttribute(b);

        boolean userHasMarker = false;

        //check if user has any markers already
        for (int i = 0; i < repository.readAllGoogleMapMarkers().size(); i++) {
            if(repository.readAllGoogleMapMarkers().get(i).getBrugerID() == b.getBrugerID()){
                userHasMarker = true;
            }

        }
        model.addAttribute("userHasMarker", userHasMarker);

        return "mainpage";
    }

    @PostMapping("/create-marker")
    public String createMarker(@RequestParam int estimeretBeloeb, double latHidden, double lngHidden){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        Bruger b = repository.readBruger(mail);

        double latitude = latHidden;
        double longitude = lngHidden;

        LocalDateTime ldtNow = LocalDateTime.now();
        LocalDateTime ldtExpire = ldtNow.plusDays(7);

        Pant p = new Pant(estimeretBeloeb);

        GoogleMapMarker g = new GoogleMapMarker(b.getBrugerID(), latitude, longitude, ldtNow, ldtExpire, p);
        repository.createGoogleMapMarker(g);

        return "redirect:/mainpage";
    }
    @PostMapping("/delete-marker")
    public String deleteMarker(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        Bruger b = repository.readBruger(mail);
        repository.deleteGoogleMapMarker(b.getBrugerID());
        return "redirect:/mainpage";
    }

    @GetMapping("/bruger")
    public String BrugerSide(Model model) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String mail = authentication.getName();
            Bruger b = repository.readBruger(mail);
            model.addAttribute(b);
            return "bruger";

    }
    @PostMapping("/edit-bruger")
    public String editBruger (@RequestParam String mail,
                              @RequestParam String fornavn,
                              @RequestParam String efternavn,
                              @RequestParam String telefonnummer,
                              @RequestParam String kodeord){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authmail = authentication.getName();
        Bruger b = repository.readBruger(authmail);
        b.setMail(mail);
        b.setFornavn(fornavn);
        b.setEfternavn(efternavn);
        b.setTelefonnummer(telefonnummer);
        b.setKodeord(kodeord);
        b.setKodeord(encoder.encode(b.getKodeord()));
        repository.updateBruger(b);
        /*TODO lav muligvis en if statement hvis der bliver ændret mail til at returne en anden side
        redirecter til logout fordi at hvis man ændrer mailadresse vil der være
        problemer med spring security da mail definerer den loggede ind bruger*/
        return "redirect:/logout";
    }

    @PostMapping("/slet-bruger")
    public String sletBruger() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        Bruger b = repository.readBruger(mail);

        //FJERNER googlemarker før at brugeren bliver slettet fordi at der er en foreign key.
        repository.deleteGoogleMapMarker(b.getBrugerID());
        repository.deleteBruger(b.getBrugerID());
        return "redirect:/logout";
    }

    @PostMapping("/skab-adresse")
    public String skabAdresse(@RequestParam String gade,
                              @RequestParam String husnummer,
                              @RequestParam String etage,
                              @RequestParam int postnummer,
                              @RequestParam String bynavn) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        Bruger b = repository.readBruger(mail);
        Adresse adresse = new Adresse(gade,husnummer,etage,postnummer,bynavn);
        repository.createAdresse(adresse,b.getBrugerID());
        Adresse a = repository.readadresse(adresse.getAdresseID());

        int brugerID = b.getBrugerID();
        int adresseID = a.getAdresseID();

        //TODO fungerer ikke endnu da vi ikke kan få fat i adresseID
        //repository.createBrugerAdresse(b.getBrugerID(),adresse.getAdresseID());
        return "redirect:/bruger";
    }

    @GetMapping("/logout")
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        //fjerner remember-me-cookien.
        String cookieName = "remember-me";
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
        response.addCookie(cookie);

        return "redirect:/";
    }


    //----------------------------------------------------------------------------------
    //TODO lav en lækrere opret-markør-funktion. A la den nedenfor:
    @RequestMapping(value = "/fetch-user", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Bruger formattedUserData(){
        Bruger bruger = repository.readBruger(null);
        return bruger;
    }

    @RequestMapping(value = "/fetch-markers", method = RequestMethod.GET, produces = "application/json")
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
                    b.getProfilbilledesti()
            ));
        }
        return formattedData; //viewmodel
    }

    @GetMapping("/lidt-om-os")
    public String omOs(Model model) {
        return "lidt-om-os";
    }

    @GetMapping ("/elepantprojektet")
    public String pantherProjektet(Model model) {
        return "elepantprojektet";
    }

    @GetMapping ("/samarbejdspartnere")
    public String partnere(Model model) {
        return "samarbejdspartnere";
    }
}
