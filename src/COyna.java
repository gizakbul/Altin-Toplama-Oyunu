package yazlab1.pkg1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import static java.lang.Math.abs;
import javax.swing.ImageIcon;

public class COyna {

    int[] CKonum = new int[2];
    int[] CHedef = new int[2];
    int altin;
    File fileC;
    BufferedWriter bWriter;

    public COyna(int CKonum[], int CHedef[], int altinS) throws IOException {

        this.CKonum[0] = CKonum[0];
        this.CKonum[1] = CKonum[1];
        this.CHedef[0] = CHedef[0];
        this.CHedef[1] = CHedef[1];
        this.altin = altinS;

        this.fileC = new File("OyuncuC.txt");

        this.bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileC)));
        try {

            bWriter.append("C Oyuncusunun İzlediği Yolların Koordinatları :");
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
        while ((this.CKonum[0] != this.CHedef[0] || this.CKonum[1] != this.CHedef[1]) && adimSay < tahta.adimSayisi && altin >= tahta.ChamleP) {

            if (this.CKonum[0] < this.CHedef[0]) {
                board[CKonum[0]][CKonum[1]].setIcon(null);
                this.CKonum[0]++;
                adimSay++;
            } else if (this.CKonum[1] < this.CHedef[1]) {
                board[CKonum[0]][CKonum[1]].setIcon(null);
                this.CKonum[1]++;
                adimSay++;
            } else if (this.CKonum[0] > this.CHedef[0]) {
                board[CKonum[0]][CKonum[1]].setIcon(null);
                this.CKonum[0]--;
                adimSay++;
            } else if (this.CKonum[1] > this.CHedef[1]) {
                board[CKonum[0]][CKonum[1]].setIcon(null);
                this.CKonum[1]--;
                adimSay++;

            }
            board[CKonum[0]][CKonum[1]].setIcon(new ImageIcon("images/C.png"));
            try {

                bWriter.write("[" + CKonum[0] + " - " + CKonum[1] + "]" + ",");
                bWriter.flush();

            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(AOyna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            System.out.println(this.CKonum[0] + " " + this.CKonum[1] + "a Varıldı");
            if (tahta.altinMatrisi[this.CKonum[0]][this.CKonum[1]] != 0) {
                System.out.println(tahta.altinMatrisi[this.CKonum[0]][this.CKonum[1]] + "ALTIN BULUNDU");
                board[this.CKonum[0]][this.CKonum[1]].setText(" ");
                this.altin += tahta.altinMatrisi[this.CKonum[0]][this.CKonum[1]];
                
                this.altin -= tahta.ChamleP;
                tahta.Cskor = this.altin;
                tahta.altinSayisi--;
                System.out.println("Tahtadaki toplam altın sayısı : " + tahta.altinSayisi);

                tahta.altinMatrisi[this.CKonum[0]][this.CKonum[1]] = 0;
                System.out.println(this.altin);
                System.out.println("CSKOR : " + tahta.Cskor);
                board[CKonum[0]][CKonum[1]].setIcon(new ImageIcon("images/C.png"));
            } else {
                this.altin -= tahta.ChamleP;
                tahta.Cskor = this.altin;
            }
            uyku();
        }

    }

    public void gizliAc(Btn tahta, Btn board[][]) {
        int enYakinGizli1 = 99999;
        int enYakinGizli = 0;
        int count = 0, sayac = 0;
        int acilacakAdet = tahta.gizlenenSayisi;
        int gizlix = 0;
        int gizliy = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (tahta.gizlenenTut[i][j] == 1 && tahta.gizlenenSayisi != 0) {
                    enYakinGizli = (abs(CKonum[0] - i) + abs(CKonum[1] - j));
                    if (enYakinGizli < enYakinGizli1) {
                        enYakinGizli1 = enYakinGizli;
                        gizlix = i;
                        gizliy = j;
                    }
                    count++;
                }
            }
        }

        count = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (i == gizlix && j == gizliy) {
                    if (board[i][j].getText().equals("bes.png")) {
                        board[i][j].setIcon(new ImageIcon("images/bes.png"));

                        tahta.gizlenenSayisi--;
                    }
                    if (board[i][j].getText().equals("on.png")) {
                        board[i][j].setIcon(new ImageIcon("images/on.png"));

                        tahta.gizlenenSayisi--;
                    }
                    if (board[i][j].getText().equals("onbes.png")) {
                        board[i][j].setIcon(new ImageIcon("images/onbes.png"));

                        tahta.gizlenenSayisi--;
                    }
                    if (board[i][j].getText().equals("yirmi.png")) {
                        board[i][j].setIcon(new ImageIcon("images/yirmi.png"));
                        tahta.gizlenenSayisi--;
                    }
                    tahta.gizlenenTut[i][j] = 0;
                }

                if (tahta.gizlenenSayisi == 0) {
                    break;
                }
                sayac++;

            }
        }
    }

    public void hedefAra(Btn tahta, Btn board[][]) {

        System.out.println("Gizlenen Altın Sayısı: " + tahta.gizlenenSayisi);

        if (tahta.gizlenenSayisi >= 2) {
            gizliAc(tahta, board);
            gizliAc(tahta, board);
        }
        if (tahta.gizlenenSayisi == 1) {
            gizliAc(tahta, board);
        }

        int karliButon = 0, deger = 0, uzaklik;
        int enYuksekKar = -99999;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isGold() && tahta.altinMatrisi[i][j] != 0 && tahta.gizlenenTut[i][j] != 1) {

                    uzaklik = (abs(i - CKonum[0]) + abs(j - CKonum[1]));
                    karliButon = tahta.altinMatrisi[i][j] - 5 * uzaklik;

                    if (karliButon > enYuksekKar) {
                        enYuksekKar = karliButon;
                        this.CHedef[0] = i;
                        this.CHedef[1] = j;
                        deger = tahta.altinMatrisi[i][j];
                    }

                }

            }
            //System.out.println("KONUM:" + this.CKonum[0] + " " + this.CKonum[1]);
            //System.out.println("HEDEF:" + this.CHedef[0] + "-" + this.CHedef[1]);

        }
        this.altin -= tahta.ChedefP;
        tahta.Cskor = this.altin;

    }

}
