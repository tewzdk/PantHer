/* Written by: Max Møller Hoffmeyer */

package project.panther.model;

import java.util.List;

public class Brugerhistorik {
    //Skal indeholde markører, som brugeren har og har haft.
    //Markørene skal stamme fra markører-tabellen og evt. en inaktive-markører-tabel og passe til brugerens brugerID

    private List<GoogleMapMarker> brugerMarkers;
    private int brugerID;

    public Brugerhistorik(List<GoogleMapMarker> brugerMarkers, int brugerID){
        this.brugerMarkers = brugerMarkers;
        this.brugerID = brugerID;
    }

    private int sumOfTotalUploadedPant(){
        int sum = 0;
        for (int i = 0; i < brugerMarkers.size(); i++) {
            sum += brugerMarkers.get(i).getPant().getEstimeretBeloeb();
        }
        return sum;
    }

    private int countOfTotalUploadedPant(){
        /*
        int count = 1;
        for (int i = 0; i < brugerMarkers.size(); i++) {
            count++;
        }
        return count;
        */

        //eller:
        return brugerMarkers.size();
    }
}
