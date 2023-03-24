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
    public String toString() {
        return super.toString() + "(Spesialist)\nKontrollkode: " + kontrollkode;
    }

}
