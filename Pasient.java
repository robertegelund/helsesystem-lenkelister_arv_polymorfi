public class Pasient {
    private static int antPasienter = 0;
    private String navn, fodselsnr;
    private int pasientID;
    private Koe<Resept> resepter = new Koe<>();

    Pasient(String navn, String fodselsnr) {
        this.navn = navn; this.fodselsnr = fodselsnr;
        pasientID = antPasienter;
        antPasienter++;
    }

    public int hentPasientID() {
        return pasientID;
    }

    public void leggTilResept(Resept resept) {
        resepter.leggTil(resept);
    }

    public Koe<Resept> hentResepter() {
        return resepter;
    }

    @Override
    public String toString() {
        return navn + " (fnr.: " + fodselsnr + ")"; 
    }

}
