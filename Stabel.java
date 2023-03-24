class Stabel<E> extends Lenkeliste<E> {
    //I en stabel legges det til og fjernes elementer fra toppen
    //Siste tillagte element (fremste) fjernes med fjern() fra Lenkeliste (ingen override noedvendig)

    @Override
    public void leggTil(E x) {
        Node nyNode = new Node(x);
        stoerrelse++;
        if(forste == null) {
            forste = nyNode;
            return;
        }
        nyNode.neste = forste;
        forste = nyNode;
    }

}
