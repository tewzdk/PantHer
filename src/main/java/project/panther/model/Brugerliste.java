package project.panther.model;

import java.util.ArrayList;

public class Brugerliste {

    private static Brugerliste Singleton = new Brugerliste();
    private ArrayList<Bruger> brugerliste;


    private Brugerliste () {
        brugerliste = new ArrayList<>();
    }

    public static Brugerliste getSingleton() {
        return Singleton;
    }

    public ArrayList<Bruger> getArray() {
        return this.brugerliste;
    }

    public void addToArray(Bruger value) {
        brugerliste.add(value);
    }
}
