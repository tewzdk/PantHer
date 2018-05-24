/* Written by: Michael Hansen & Sidney Schultz */

package project.panther.model;

import java.util.ArrayList;

public class Brugerliste {

    private ArrayList<Bruger> brugerliste;

    private Brugerliste () {
        brugerliste = new ArrayList<>();
    }

    public ArrayList<Bruger> getArray() {
        return this.brugerliste;
    }

    public void addToArray(Bruger value) {
        brugerliste.add(value);
    }
}
