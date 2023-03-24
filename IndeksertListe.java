public class IndeksertListe<E> extends Lenkeliste<E> {
    
    public void leggTil(int pos, E x) throws UgyldigListeindeks {
        if(pos < 0 || pos > stoerrelse) {
            throw new UgyldigListeindeks(pos);
        }

        if(pos == stoerrelse) {
            super.leggTil(x); 
            return;
        }

        Node nyNode = new Node(x);
        stoerrelse++;
        
        if(pos == 0) {
            nyNode.neste = forste;
            forste = nyNode;
            return;
        }

        Node peker = forste;
        for(int i = 0; i < pos-1; i++) {
            peker = peker.neste;
        }
        nyNode.neste = peker.neste;
        peker.neste = nyNode;
    }

    public void sett(int pos, E x) throws UgyldigListeindeks {
        if(pos < 0 || pos >= stoerrelse || stoerrelse == 0) {
            throw new UgyldigListeindeks(pos);
        }
        
        Node peker = forste;
        for(int i = 0; i < pos; i++) {
            peker = peker.neste;
        }
        peker.data = x;
    }

    public E hent(int pos) throws UgyldigListeindeks {
        if(pos < 0 || pos >= stoerrelse || stoerrelse == 0) {
            throw new UgyldigListeindeks(pos);
        }

        if(pos == 0) return super.hent();

        Node peker = forste;
        for(int i = 0; i < pos; i++) {
            peker = peker.neste;
        }
        return peker.data;
    }

    public E fjern(int pos) throws UgyldigListeindeks {
        if(pos < 0 || pos >= stoerrelse || stoerrelse == 0) {
            throw new UgyldigListeindeks(pos);
        }

        if(pos == 0) return super.fjern();
        
        Node peker = forste;
        for(int i = 0; i < pos-1; i++) {
            peker = peker.neste;
        }
        E fjernetData = peker.neste.data;
        peker.neste = peker.neste.neste;
        stoerrelse--;
        return fjernetData;
    }

}
