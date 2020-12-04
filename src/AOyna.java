package yazlab1.pkg1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import static java.lang.Math.abs;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class AOyna {

    int[] AKonum = new int[2];
    int[] AHedef = new int[2];
    int altin;

    File fileA;
    BufferedWriter bWriter;

    public AOyna(int AKonum[], int AHedef[], int altinS) throws IOException {
        this.AKonum[0] = AKonum[0];
        this.AKonum[1] = AKonum[1];
        this.AHedef[0] = AHedef[0];
        this.AHedef[1] = AHedef[1];
        this.altin = altinS;
        this.fileA = new File("OyuncuA.txt");

        this.bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileA)));
        try {

            bWriter.append("A Oyuncusunun İzlediği Yolların Koordinatları :");
            bWriter.newLine();
            bWriter.flush();

        } catch (IOException ex) {
            Logger.getLogger(AOyna.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void uyku() {

        try {
            Thread.sleep(1000);

        } catch (InterruptedException ex) {
        }
    }

    public void oyna(Btn tahta, Btn board[][]) {

        int adimSay = 0;
        System.out.println("konum: " + this.AKonum[0] + " " + this.AKonum[1] + " hedef:" + this.AHedef[0] + " " + this.AHedef[1]);
        while ((this.AKonum[0] != this.AHedef[0] || this.AKonum[1] != this.AHedef[1]) && adimSay < tahta.adimSayisi && altin >= tahta.AhamleP) {

            if (this.AKonum[0] < this.AHedef[0]) {
                board[AKonum[0]][AKonum[1]].setIcon(null);
                this.AKonum[0]++;
                adimSay++;
            } else if (this.AKonum[1] < this.AHedef[1]) {
                board[AKonum[0]][AKonum[1]].setIcon(null);
                this.AKonum[1]++;
                adimSay++;
            } else if (this.AKonum[0] > this.AHedef[0]) {
                board[AKonum[0]][AKonum[1]].setIcon(null);
                this.AKonum[0]--;
                adimSay++;
            } else if (this.AKonum[1] > this.AHedef[1]) {
                board[AKonum[0]][AKonum[1]].setIcon(null);
                this.AKonum[1]--;
                adimSay++;

            }
            board[AKonum[0]][AKonum[1]].setIcon(new ImageIcon("images/A.png"));
            System.out.println(this.AKonum[0] + " " + this.AKonum[1] + "a Varıldı");
            try {

                bWriter.write("[" + AKonum[0] + " - " + AKonum[1] + "]" + ",");
                bWriter.flush();

            } catch (IOException ex) {
                Logger.getLogger(AOyna.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (tahta.altinMatrisi[this.AKonum[0]][this.AKonum[1]] != 0) {
                System.out.println(tahta.altinMatrisi[this.AKonum[0]][this.AKonum[1]] + "ALTIN BULUNDU");
                board[this.AKonum[0]][this.AKonum[1]].setText(" ");
                this.altin += tahta.altinMatrisi[this.AKonum[0]][this.AKonum[1]];

                this.altin -= tahta.AhamleP;
                tahta.Askor = this.altin;
                tahta.altinSayisi--;
                System.out.println("Tahtadaki toplam altın sayısı : " + tahta.altinSayisi);

                tahta.altinMatrisi[this.AKonum[0]][this.AKonum[1]] = 0;
                System.out.println(this.altin);
                board[AKonum[0]][AKonum[1]].setIcon(new ImageIcon("images/A.png"));
            } else {
                this.altin -= tahta.AhamleP;
                tahta.Askor = this.altin;
            }
            uyku();

        }

    }

    public void hedefAra(Btn tahta, Btn board[][]) {
        /*AKonum[0] = 0;
        AKonum[1] = 0;*/

        int yakin, deger = 0;
        int enYakin = 9999;
        int hamleSayisi = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (tahta.altinMatrisi[i][j] != 0 && tahta.gizlenenTut[i][j] != 1) {
                    yakin = (abs(i - AKonum[0]) + abs(j - AKonum[1]));

                    if (yakin < enYakin) {
                        enYakin = yakin;
                        this.AHedef[0] = i;
                        this.AHedef[1] = j;
                        deger = tahta.altinMatrisi[i][j];
                    }

                }
            }

        }
        this.altin -= tahta.AhedefP;
        tahta.Askor = this.altin;

        System.out.println("KONUM:" + this.AKonum[0] + " " + this.AKonum[1]);
        //System.out.println("HEDEF:" + this.AHedef[0] + "-" + this.AHedef[1]);

    }

}
