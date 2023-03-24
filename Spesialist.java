class Spesialist extends Lege implements Godkjenningsfritak {
    private String kontrollkode;

    Spesialist(String navn, String kontrollkode) {
        super(navn);
        this.kontrollkode = kontrollkode;
    }

    public String hentKontrollkode() {
        return kontrollkode;
    }

    @Override
    public Resept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) {
        BlaaResept nyResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(nyResept); pasient.leggTilResept(nyResept);
        return nyResept;
    }

    @Override
    public String toString() {
        return super.toString() + " (Spesialist) - Kontrollkode: " + kontrollkode;
    }

}
