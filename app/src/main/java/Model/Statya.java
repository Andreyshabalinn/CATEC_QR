package Model;

public class Statya {
    private int id;
    private String Zag;
    private String Tekst;

    public Statya() {
    }

    public Statya(int id, String zag, String tekst) {
        this.id = id;
        Zag = zag;
        Tekst = tekst;
    }

    public Statya(String zag, String tekst) {
        Zag = zag;
        Tekst = tekst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZag() {
        return Zag;
    }

    public void setZag(String zag) {
        Zag = zag;
    }

    public String getTekst() {
        return Tekst;
    }

    public void setTekst(String tekst) {
        Tekst = tekst;
    }
}
