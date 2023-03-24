class Testprogram {
    
    public static void main(String[] args) {
        
        Lege lege = new Lege("Arnfrid Jugda");
        Spesialist spesialist = new Spesialist("Joergen Feinschmecker", "NARK-3987");

        Vanlig paracet = new Vanlig("Paracet", 42, 500);
        Narkotisk predizol = new Narkotisk("Predizol", 450, 75, 8);
        Vanedannende paralginForte = new Vanedannende("Paralgin Forte", 65, 400, 5);
        
        BlaaResept blaaRes = new BlaaResept(paracet, lege, 0, 5);
        HvitResept hvitRes = new HvitResept(predizol, spesialist, 3, 2);
        MilResept milRes = new MilResept(paralginForte, spesialist, 1);
        PResept pRes = new PResept(paralginForte, lege, 4, 1);

        Lege[] leger = {lege, spesialist};
        Legemiddel[] legemidler = {paracet, predizol, paralginForte};
        Resept[] resepter = {blaaRes, hvitRes, milRes, pRes};

        System.out.println("---------- RESEPTER ---------- \n");
        for(Resept r : resepter) { 
            System.out.println(r+"\n"); 
        }
        System.out.println();

        System.out.println("---------- LEGER ---------- \n");
        for(Lege l: leger) { System.out.println(l+"\n"); }
        System.out.println();

        System.out.println("---------- LEGEMIDLER ---------- \n");
        for(Legemiddel l : legemidler) { System.out.println(l+"\n"); }
        System.out.println();
       
    }
}
