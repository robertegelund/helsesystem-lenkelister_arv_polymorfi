import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Legesystem {
    private static IndeksertListe<Pasient> pasienter = new IndeksertListe<>();
    private static Prioritetskoe<Lege> leger = new Prioritetskoe<>();
    private static IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
    private static IndeksertListe<Resept> resepter = new IndeksertListe<>();

    public static void main(String[] args) {
        if (args.length != 0) {
            lesFraFil(args[0]);
        } else {
            lesFraFil("legedata.txt");
        }

        visBrukermeny();
    }

    private static void visBrukermeny() {
        Scanner sc = null;
        sc = new Scanner(System.in);
        
        

        sc.close();
    }

    private static void lesFraFil(String filnavn) {
        Scanner sc = null;
        int antLinjer = 0;
        
        try {
            sc = new Scanner(new File(filnavn));
            while(sc.hasNextLine()) {antLinjer++; sc.nextLine();}
            sc.close();
            sc = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e) {
            System.out.println("Filen ble ikke funnet.");
            System.exit(1);
        }

        int linjerPerKlasse = (antLinjer - 4) / 4;
        while(sc.hasNextLine()) {
            String linje = sc.nextLine();
            if (linje.contains("Pasienter")) {
                leggTilPasienter(sc, linjerPerKlasse); 
            } else if(linje.contains("Legemidler")) {
                leggTilLegemidler(sc, linjerPerKlasse);
            } else if(linje.contains("Leger")) {
                leggTilLeger(sc, linjerPerKlasse);
            } else if(linje.contains("Resepter")) {
                leggTilResepter(sc, linjerPerKlasse);
            }
        }
        sc.close();
    }

    private static void leggTilPasienter(Scanner sc, int antPasienter) {
        for(int i = 0; i < antPasienter; i++) {
            String[] deler = sc.nextLine().trim().split(",");
            pasienter.leggTil(new Pasient(deler[0], deler[1]));
        }
    }

    private static void leggTilLegemidler(Scanner sc, int antLegemidler) {
        for(int i = 0; i < antLegemidler; i++) {
            String[] deler = sc.nextLine().trim().split(",");
            String navn = deler[0], type = deler[1]; 
            int pris = Integer.parseInt(deler[2]); 
            double virkestoff = Double.parseDouble(deler[3]); 
            int styrke = (deler.length == 4) ? Integer.parseInt(deler[3]) : 0;
    
            if(type.equals("vanlig")) {
                legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
            } else if(type.equals("vanedannende")) {
                legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));
            } else if(type.equals("narkotisk")) {
                legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));
            }
        }
    }

    private static void leggTilLeger(Scanner sc, int antLeger) {
        for(int i = 0; i < antLeger; i ++) {
            String[] deler = sc.nextLine().trim().split(",");
            if(deler[1].equals("0")) {
                leger.leggTil(new Lege(deler[0]));
            } else {
                leger.leggTil(new Spesialist(deler[0], deler[1]));
            }
        }
    }

    private static void leggTilResepter(Scanner sc, int antResepter) {
        for(int i = 0; i < antResepter; i++) {
            String[] deler = sc.nextLine().trim().split(",");
            Legemiddel legemiddel = legemidler.hent(Integer.parseInt(deler[0]));
            Lege lege = null;
            for(Lege l : leger) { if(l.hentNavn().equals(deler[1])) lege = l; }
            Pasient pasient = pasienter.hent(Integer.parseInt(deler[2]));
            String type = deler[3];
            int reit = (deler.length == 5) ? Integer.parseInt(deler[4]) : 3;

            try {
                if(type.equals("hvit")) {
                    resepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                } else if(type.equals("militaer")) {
                    resepter.leggTil(lege.skrivMilResept(legemiddel, pasient));
                } else if(type.equals("p")) {
                    resepter.leggTil(lege.skrivPResept(legemiddel, pasient, reit));
                } else if(type.equals("blaa")) {
                    resepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                }
            } catch(UlovligUtskrift e) {
                System.out.println(e.getMessage() + ". Resept ikke opprettet.");
            }
        }
    }
}
