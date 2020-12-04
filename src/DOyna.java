package yazlab1.pkg1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import static java.lang.Math.abs;
import javax.swing.ImageIcon;

public class DOyna {

    int[] DKonum = new int[2];
    int[] DHedef = new int[2];
    int altin;
    File fileD;
    BufferedWriter bWriter;

    public DOyna(int DKonum[], int DHedef[], int altinS) throws IOException {
        this.DKonum[0] = DKonum[0];
        this.DKonum[1] = DKonum[1];
        this.DHedef[0] = DHedef[0];
        this.DHedef[1] = DHedef[1];
        this.altin = altinS;

        this.fileD = new File("OyuncuD.txt");

        this.bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileD)));
        try {

            bWriter.append("D Oyuncusunun İzlediği Yolların Koordinatları :");
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

    public void Oyna(Btn tahta, Btn board[][]) {
        int adimSay = 0;
        while ((this.DKonum[0] != this.DHedef[0] || this.DKonum[1] != this.DHedef[1]) && adimSay < tahta.adimSayisi && altin >= tahta.DhamleP) {

            if (this.DKonum[0] < this.DHedef[0]) {
                board[DKonum[0]][DKonum[1]].setIcon(null);
                this.DKonum[0]++;
                adimSay++;
            } else if (this.DKonum[1] < this.DHedef[1]) {
                board[DKonum[0]][DKonum[1]].setIcon(null);
                this.DKonum[1]++;
                adimSay++;
            } else if (this.DKonum[0] > this.DHedef[0]) {
                board[DKonum[0]][DKonum[1]].setIcon(null);
                this.DKonum[0]--;
                adimSay++;
            } else if (this.DKonum[1] > this.DHedef[1]) {
                board[DKonum[0]][DKonum[1]].setIcon(null);
                this.DKonum[1]--;
                adimSay++;

            }
            board[DKonum[0]][DKonum[1]].setIcon(new ImageIcon("images/D.png"));
            try {

                bWriter.write("[" + DKonum[0] + " - " + DKonum[1] + "]" + ",");
                bWriter.flush();

            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(AOyna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            System.out.println(this.DKonum[0] + " " + this.DKonum[1] + "a Varıldı");
            if (tahta.altinMatrisi[this.DKonum[0]][this.DKonum[1]] != 0) {
                System.out.println(tahta.altinMatrisi[this.DKonum[0]][this.DKonum[1]] + "ALTIN BULUNDU");
                board[this.DKonum[0]][this.DKonum[1]].setText(" ");
                this.altin += tahta.altinMatrisi[this.DKonum[0]][this.DKonum[1]];

                this.altin -= tahta.DhamleP;
                tahta.Dskor = this.altin;
                tahta.altinSayisi--;
                System.out.println("Tahtadaki toplam altın sayısı : " + tahta.altinSayisi);

                tahta.altinMatrisi[this.DKonum[0]][this.DKonum[1]] = 0;
                System.out.println(this.altin);
                System.out.println("DSKOR : " + tahta.Dskor);
                board[DKonum[0]][DKonum[1]].setIcon(new ImageIcon("images/D.png"));
            } else {
                this.altin -= tahta.DhamleP;
                tahta.Dskor = this.altin;
            }
            uyku();

        }
    }

    public void hedefAra(Btn tahta, Btn board[][], int AHedef[], int BHedef[], int CHedef[], int AKonum[], int BKonum[], int CKonum[]) {
        int karliButon = 0, deger = 0, uzaklik;
        int enYuksekKar = -333333;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isGold() && tahta.altinMatrisi[i][j] != 0 && tahta.gizlenenTut[i][j] != 1) {
                    uzaklik = (abs(i - this.DKonum[0]) + abs(j - this.DKonum[1]));
                    karliButon = tahta.altinMatrisi[i][j] - 5 * uzaklik;

                    if (AHedef[0] == i && AHedef[1] == j) {
                        if (abs(AKonum[0] - AHedef[0]) + abs(AKonum[1] - AHedef[1]) < abs(this.DKonum[0] - i) + abs(this.DKonum[1] - j)) {
                            System.out.println("a oyuncusu hedefe d den daha yakın");
                            continue;
                        }
                        if (abs(AKonum[0] - AHedef[0]) + abs(AKonum[1] - AHedef[1]) > abs(this.DKonum[0] - i) + abs(this.DKonum[1] - j)) {
                            System.out.println("d oyuncusu a nı hedefini aldı");
                            if (karliButon > enYuksekKar) {
                                enYuksekKar = karliButon;
                                this.DHedef[0] = i;
                                this.DHedef[1] = j;
                                deger = tahta.altinMatrisi[i][j];
                            }
                        }

                    }
                    if (BHedef[0] == i && BHedef[1] == j) {
                        if (abs(BKonum[0] - BHedef[0]) + abs(BKonum[1] - BHedef[1]) < abs(DKonum[0] - i) + abs(DKonum[1] - j)) {
                            System.out.println("b oyuncusu hedefe d den daha yakın");
                            continue;
                        }
                        if (abs(BKonum[0] - BHedef[0]) + abs(BKonum[1] - BHedef[1]) > abs(DKonum[0] - i) + abs(DKonum[1] - j)) {
                            System.out.println("d oyuncusu b nin hedefini aldı");
                            if (karliButon > enYuksekKar) {
                                enYuksekKar = karliButon;
                                this.DHedef[0] = i;
                                this.DHedef[1] = j;
                                deger = tahta.altinMatrisi[i][j];
                            }
                        }

                    }
                    if (CHedef[0] == i && CHedef[1] == j) {
                        if (abs(CKonum[0] - CHedef[0]) + abs(CKonum[1] - CHedef[1]) < abs(DKonum[0] - i) + abs(DKonum[1] - j)) {
                            System.out.println("c oyuncusu hedefe daha yakın");
                            continue;
                        }
                        if (abs(CKonum[0] - CHedef[0]) + abs(CKonum[1] - CHedef[1]) > abs(DKonum[0] - i) + abs(DKonum[1] - j)) {
                            System.out.println("d oyuncusu c nin hedefini aldı");
                            if (karliButon > enYuksekKar) {
                                enYuksekKar = karliButon;
                                this.DHedef[0] = i;
                                this.DHedef[1] = j;
                                deger = tahta.altinMatrisi[i][j];
                            }
                        }

                    } else {
                        if (karliButon > enYuksekKar) {
                            enYuksekKar = karliButon;
                            this.DHedef[0] = i;
                            this.DHedef[1] = j;
                            deger = tahta.altinMatrisi[i][j];
                        }
                    }

                }

            }
            //System.out.println("KONUM:" + this.DKonum[0] + " " + this.DKonum[1]);
            //System.out.println("HEDEF:" + this.DHedef[0] + "-" + this.DHedef[1]);

        }
        this.altin -= tahta.DhedefP;
        tahta.Dskor = this.altin;

    }
}
