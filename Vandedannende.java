class Vanedannende extends Legemiddel {
    public final int styrke;
    private String type = "vanedannende";

    Vanedannende(String navn, int pris, double virkestoff,  int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    @Override
    public String hentType() {
        return type;
    }

    @Override
    public String toString() {
        String info = super.toString();
        info += "\nStyrke: " + styrke;
        return info;
    }

}
