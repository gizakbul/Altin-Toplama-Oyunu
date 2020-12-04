package yazlab1.pkg1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import static java.lang.Math.abs;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.ImageIcon;

public class BOyna {

    int[] BKonum = new int[2];
    int[] BHedef = new int[2];
    int altin;
    File fileB;
    BufferedWriter bWriter;

    public BOyna(int BKonum[], int BHedef[], int altinS) throws IOException {
        this.BKonum[0] = BKonum[0];
        this.BKonum[1] = BKonum[1];
        this.BHedef[0] = BHedef[0];
        this.BHedef[1] = BHedef[1];
        this.altin = altinS;

        this.fileB = new File("OyuncuB.txt");

        this.bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileB)));
        try {

            bWriter.append("B Oyuncusunun İzlediği Yolların Koordinatları :");
            bWriter.newLine();
            bWriter.flush();

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(AOyna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        while ((this.BKonum[0] != this.BHedef[0] || this.BKonum[1] != this.BHedef[1]) && adimSay < tahta.adimSayisi && altin >= tahta.BhamleP) {

            if (this.BKonum[0] < this.BHedef[0]) {
                board[BKonum[0]][BKonum[1]].setIcon(null);
                this.BKonum[0]++;
                adimSay++;
            } else if (this.BKonum[1] < this.BHedef[1]) {
                board[BKonum[0]][BKonum[1]].setIcon(null);
                this.BKonum[1]++;
                adimSay++;
            } else if (this.BKonum[0] > this.BHedef[0]) {
                board[BKonum[0]][BKonum[1]].setIcon(null);
                this.BKonum[0]--;
                adimSay++;
            } else if (this.BKonum[1] > this.BHedef[1]) {
                board[BKonum[0]][BKonum[1]].setIcon(null);
                this.BKonum[1]--;
                adimSay++;

            }
            board[BKonum[0]][BKonum[1]].setIcon(new ImageIcon("images/B.png"));
            try {

                bWriter.write("[" + BKonum[0] + " - " + BKonum[1] + "]" + ",");
                bWriter.flush();

            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(AOyna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            System.out.println(this.BKonum[0] + " " + this.BKonum[1] + "a Varıldı");
            if (tahta.altinMatrisi[this.BKonum[0]][this.BKonum[1]] != 0) {
                System.out.println(tahta.altinMatrisi[this.BKonum[0]][this.BKonum[1]] + "ALTIN BULUNDU");
                board[this.BKonum[0]][this.BKonum[1]].setText(" ");
                this.altin += tahta.altinMatrisi[this.BKonum[0]][this.BKonum[1]];
                
                this.altin -= tahta.BhamleP;
                tahta.Bskor = this.altin;
                tahta.altinSayisi--;
                System.out.println("Tahtadaki toplam altın sayısı : " + tahta.altinSayisi);

                tahta.altinMatrisi[this.BKonum[0]][this.BKonum[1]] = 0;
                System.out.println(this.altin);
                //tahta.Bskor = this.altin - 15;
                System.out.println("BSKOR : " + tahta.Bskor);
                board[BKonum[0]][BKonum[1]].setIcon(new ImageIcon("images/B.png"));
            } else {
                this.altin -= tahta.BhamleP;
                tahta.Bskor = this.altin;
            }
            uyku();
        }
    }

    public void hedefAra(Btn tahta, Btn board[][]) {

        int karliButon = 0, deger = 0, uzaklik;
        int enYuksekKar = -333333;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isGold() && tahta.altinMatrisi[i][j] != 0 && tahta.gizlenenTut[i][j] != 1) {

                    uzaklik = (abs(i - BKonum[0]) + abs(j - BKonum[1]));
                    karliButon = tahta.altinMatrisi[i][j] - 5 * uzaklik;

                    if (karliButon > enYuksekKar) {
                        enYuksekKar = karliButon;
                        this.BHedef[0] = i;
                        this.BHedef[1] = j;
                        deger = tahta.altinMatrisi[i][j];
                    }

                }

            }
            //System.out.println("KONUM:" + this.BKonum[0] + " " + this.BKonum[1]);
            //System.out.println("HEDEF:" + this.BHedef[0] + "-" + this.BHedef[1]);

        }
        this.altin -= tahta.BhedefP;
        tahta.Bskor = this.altin;

    }
}
