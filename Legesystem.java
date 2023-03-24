import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Legesystem {
    private static IndeksertListe<Pasient> pasienter = new IndeksertListe<>();
    private static IndeksertListe<Lege> leger = new IndeksertListe<>();
    private static IndeksertListe<Legemiddel> legemidler = new IndeksertListe<>();
    private static IndeksertListe<Resept> resepter = new IndeksertListe<>();

    public static void main(String[] args) {
        if (args.length != 0) {
            lesFraFil(args[0]);
        } else {
            lesFraFil("legedata.txt");
        }

        System.out.println(leger.hent(2));
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
            // } else if(linje.contains("Legemidler")) {
            //     leggTilLegemiddel(sc, linjerPerKlasse);
            } else if(linje.contains("Leger")) {
                leggTilLege(sc, linjerPerKlasse);
            }
            // } else if(linje.contains("Resepter")) {
            //     leggTilResept(sc, linjerPerKlasse);
            // }
        }
        sc.close();
    }

    private static void leggTilPasienter(Scanner sc, int antIter) {
        for(int i = 0; i < antIter; i++) {
            String[] deler = sc.nextLine().trim().split(",");
            pasienter.leggTil(new Pasient(deler[0], deler[1]));
        }
    }

    private static void leggTilLegemiddel(Scanner sc, int antIter) {
        String[] deler = sc.nextLine().trim().split(",");
    }

    private static void leggTilLege(Scanner sc, int antIter) {
        for(int i = 0; i < antIter; i ++) {
            String[] deler = sc.nextLine().trim().split(",");
            if(deler[1].equals("0")) {
                leger.leggTil(new Lege(deler[0]));
            } else {
                leger.leggTil(new Spesialist(deler[0], deler[1]));
            }
        }
    }

    private static void leggTilResept(Scanner sc, int antIter) {
        String[] deler = sc.nextLine().trim().split(",");
    }

}
