package met.local.cs330.pz.model;

public class Pacijent {

    private String jmbg;
    private String ime, prezime, simptomi, terapija;
    private boolean izlecen;

    public Pacijent() {
    }

    public Pacijent(String jmbg, String ime, String prezime, String simptomi, String terapija, boolean izlecen) {
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.simptomi = simptomi;
        this.terapija = terapija;
        this.izlecen = izlecen;
    }

    public String getJmbg() {
        return  jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return  ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return  prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(String simptomi) {
        this.simptomi = simptomi;
    }

    public String getTerapija() {
        return  terapija;
    }

    public void setTerapija(String terapija) {
        this.terapija = terapija;
    }

    public boolean isIzlecen() {
        return izlecen;
    }

    public void setIzlecen(boolean izlecen) {
        this.izlecen = izlecen;
    }

    @Override
    public String toString() {
        return "Pacijent{" +
                "jmbg='" + jmbg + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", simptomi='" + simptomi + '\'' +
                ", terapija='" + terapija + '\'' +
                ", izlecen=" + izlecen +
                '}';
    }
}
