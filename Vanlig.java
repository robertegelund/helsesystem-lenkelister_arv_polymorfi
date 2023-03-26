class Vanlig extends Legemiddel {
    private String type = "vanlig";

    Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }

    @Override
    public String hentType() {
        return type;
    }

}

