abstract class Legemiddel { 
    private static int antLegemidler = 0;
    public final String navn;
    public final double virkestoff;
    public final int ID;
    private int pris;

    Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.virkestoff = virkestoff;
        this.pris = pris;
        ID = antLegemidler;
        antLegemidler++;
    }

    public int hentPris() {return pris;}
    public void settNyPris(int nyPris) {pris = nyPris;}
    public String hentNavn() {return navn;}

    @Override
    public String toString() {
        String info = "";
        info += "Legemiddel-ID: " + ID + "\n";
        info += "Legemiddelnavn: " + navn + "\n";
        info += "Virkestoff: " + virkestoff + "\n";
        info += "Pris: " + pris;
        return info;
    }
}