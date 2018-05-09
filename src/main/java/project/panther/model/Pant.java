package project.panther.model;

public class Pant {

    private int estimeretBeløb;
    private String pantBilledSti;

    public Pant(int estimeretBeløb, String pantBilledSti) {
        this.estimeretBeløb = estimeretBeløb;
        this.pantBilledSti = pantBilledSti;
    }

    public int getEstimeretBeløb() {
        return estimeretBeløb;
    }

    public void setEstimeretBeløb(int estimeretBeløb) {
        this.estimeretBeløb = estimeretBeløb;
    }

    public String getPantBilledSti() {
        return pantBilledSti;
    }

    public void setPantBilledSti(String pantBilledSti) {
        this.pantBilledSti = pantBilledSti;
    }
}
