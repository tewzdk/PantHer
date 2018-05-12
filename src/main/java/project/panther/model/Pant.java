package project.panther.model;

public class Pant {

    private int estimeretBeloeb;
    private String pantBilledSti = "pictures/defaultPantBillede.jpg";

    public Pant(int estimeretBeloeb, String pantBilledSti) {
        this.estimeretBeloeb = estimeretBeloeb;
        this.pantBilledSti = pantBilledSti;
    }

    public int getEstimeretBeloeb() {
        return estimeretBeloeb;
    }

    public void setEstimeretBeloeb(int estimeretBeloeb) {
        this.estimeretBeloeb = estimeretBeloeb;
    }

    public String getPantBilledSti() {
        return pantBilledSti;
    }

    public void setPantBilledSti(String pantBilledSti) {
        this.pantBilledSti = pantBilledSti;
    }
}
