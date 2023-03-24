public class Lege implements Comparable<Lege> {
    private String navn;
    private IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();

    Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {return navn;}
    public IndeksertListe<Resept> hentUtskrevneResepter() {return utskrevneResepter;}

    public Resept skrivResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        HvitResept nyResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyResept);
        return nyResept;
    }

    @Override
    public String toString() {
        return "Dr. " + navn;
    }

    @Override
    public int compareTo(Lege annen) {
        return toString().compareTo(annen.toString());
    }
}
