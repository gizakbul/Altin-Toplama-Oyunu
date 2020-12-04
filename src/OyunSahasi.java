package yazlab1.pkg1;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class OyunSahasi {

    JFrame frame;
    JFrame frame2;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JLabel label6;
    JLabel label7;
    JLabel label8;

    int adimSayisi;//atacağı adım sayısını tutar
    int baslangicAltin;//oyuncuların başlayacağı altın değerini tutar
    int Askor;//A oyuncusunun skorunu ve skordaki değişiklikleri tutar
    int Bskor;//B oyuncusunun skorunu ve skordaki değişiklikleri tutar
    int Cskor;//C oyuncusunun skorunu ve skordaki değişiklikleri tutar
    int Dskor;//D oyuncusunun skorunu ve skordaki değişiklikleri tutar

    int gizlenenSayisi;//Gizli altin sayisini tutar
    int altinSayisi;//Toplam Altin sayisini tutar
    int AKonum[];//A'nin son konumunu tutar
    int BKonum[];//B'nin son konumunu tutar
    int CKonum[];//C'nin son konumunu tutar
    int DKonum[];//D'nin son konumunu tutar
    int AHedef[];
    int BHedef[];
    int CHedef[];
    int DHedef[];

    int AhamleP, AhedefP;
    int BhamleP, BhedefP;
    int ChamleP, ChedefP;
    int DhamleP, DhedefP;

    int x, y;
    int Ax, Ay;
    Btn[][] board;
    Btn tahta = new Btn();
    ArrayList<String> list = new ArrayList<String>();

    public OyunSahasi(int x, int y, int adimSayisi, int baslangicAltin, int AhamleP, int AhedefP, int BhamleP, int BhedefP, int ChamleP, int ChedefP, int DhamleP, int DhedefP) {

        this.label1 = new JLabel();
        this.label2 = new JLabel();
        this.label3 = new JLabel();
        this.label4 = new JLabel();
        this.label5 = new JLabel();
        this.label6 = new JLabel();
        this.label7 = new JLabel();
        this.label8 = new JLabel();

        list.add("bes.png");
        list.add("on.png");
        list.add("onbes.png");
        list.add("yirmi.png");

        this.x = x;
        this.y = y;
        this.Ax = Ax;
        this.Ay = Ay;

        this.adimSayisi = adimSayisi;
        tahta.adimSayisi = adimSayisi;

        this.AhamleP = AhamleP;
        this.AhedefP = AhedefP;

        tahta.AhamleP = AhamleP;
        tahta.AhedefP = AhedefP;

        this.BhamleP = BhamleP;
        this.BhedefP = BhedefP;

        tahta.BhamleP = BhamleP;
        tahta.BhedefP = BhedefP;

        this.ChamleP = ChamleP;
        this.ChedefP = ChedefP;

        tahta.ChamleP = ChamleP;
        tahta.ChedefP = ChedefP;

        this.DhamleP = DhamleP;
        this.DhedefP = DhedefP;

        tahta.DhamleP = DhamleP;
        tahta.DhedefP = DhedefP;

        this.baslangicAltin = baslangicAltin;
        this.Askor = baslangicAltin;
        this.Bskor = baslangicAltin;
        this.Cskor = baslangicAltin;
        this.Dskor = baslangicAltin;

        tahta.gizlenenSayisi = gizlenenSayisi;
        this.gizlenenSayisi = gizlenenSayisi;
        this.AKonum = new int[2];
        this.BKonum = new int[2];
        this.CKonum = new int[2];
        this.DKonum = new int[2];
        this.AHedef = new int[2];
        this.BHedef = new int[2];
        this.CHedef = new int[2];
        this.DHedef = new int[2];
        this.AKonum[0] = AKonum[0];
        this.AKonum[1] = AKonum[1];

        this.BKonum[0] = BKonum[0];
        this.BKonum[1] = BKonum[1];

        this.CKonum[0] = CKonum[0];
        this.CKonum[1] = CKonum[1];

        this.DKonum[0] = DKonum[0];
        this.DKonum[1] = DKonum[1];

        this.altinSayisi = altinSayisi;

        //System.out.println("ADIM SAYISIIII : " + adimSayisi);
        this.board = new Btn[x][y];

        frame = new JFrame();
        frame2 = new JFrame();
        frame2.getContentPane().setLayout(new FlowLayout());

        frame2.setSize(400, 400);
        frame2.setLocation(800, 100);

        label1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label1.setText("A Skor: ");
        frame2.getContentPane().add(label1);
        label5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label5.setText(Integer.toString(baslangicAltin));
        frame2.getContentPane().add(label5);

        label2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label2.setText("B Skor:");
        frame2.getContentPane().add(label2);
        label6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label6.setText(Integer.toString(baslangicAltin));
        frame2.getContentPane().add(label6);

        label3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label3.setText("C Skor:");
        frame2.getContentPane().add(label3);
        label7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label7.setText(Integer.toString(baslangicAltin));
        frame2.getContentPane().add(label7);

        label4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label4.setText("D Skor:");
        frame2.getContentPane().add(label4);
        label8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label8.setText(Integer.toString(baslangicAltin));
        frame2.getContentPane().add(label8);

        frame.setSize(800, 800);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(x, y)); //Izgara bu olacak.

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                Btn izgara = new Btn(x, y);
                frame.add(izgara);
                board[i][j] = izgara;
                board[i][j].setIcon(new ImageIcon("images/toprak.png"));
            }
        }
        int degisken = 0;
        altinYerlestir();
        altinlariGizle();
        //    koordinatYaz();

        altinSayisiHesapla();
        altinDegerihesapla(board);
        degisken++;

        System.out.println("0");
        frame.setVisible(true);
        System.out.println("1");
        frame2.setVisible(true);
        System.out.println("2");
        startPollingTimer();
    }
    public static Timer t;

    public synchronized void startPollingTimer() { //https://stackoverflow.com/questions/2258066/java-run-a-function-after-a-specific-number-of-seconds   Sayfasından alınmıştır.
        if (t == null) {
            TimerTask task = new TimerTask() {
                int second = 3;

                @Override
                public void run() {
                    second--;
                    if (second == 0) {
                        try {
                            Oynat();
                        } catch (IOException ex) {
                            Logger.getLogger(OyunSahasi.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (second < 0) {
                        t.cancel();
                    }
                }
            };

            t = new Timer();
            t.scheduleAtFixedRate(task, 0, 2000);
        }
    }

    public void run() {

        /*for(int i=1;i<5;i++)   {    
            try  {  
                //call sleep method of thread
                Thread.sleep(1000);  
            }catch(InterruptedException e){System.out.println(e);}    
            //print current thread instance with loop variable
            System.out.println(Thread.currentThread().getName() + "   : " + i);    
        }  */
    }

    public void uyku() {
        try {
            Thread.sleep(1000);

        } catch (InterruptedException ex) {
        }
    }

    public void altinYerlestir() {
        AKonum[0] = 0;
        AKonum[1] = 0;

        BKonum[0] = 0;
        BKonum[1] = y - 1;

        CKonum[0] = x - 1;
        CKonum[1] = y - 1;

        DKonum[0] = x - 1;
        DKonum[1] = 0;

        AHedef[0] = 0;
        AHedef[1] = 0;

        BHedef[0] = 0;
        BHedef[1] = y - 1;

        CHedef[0] = x - 1;
        CHedef[1] = y - 1;

        DHedef[0] = x - 1;
        DHedef[1] = 0;
        int i = 0;
        board[0][0].setIcon(new ImageIcon("images/A.png"));
        board[0][y - 1].setIcon(new ImageIcon("images/B.png"));
        board[x - 1][y - 1].setIcon(new ImageIcon("images/C.png"));
        board[x - 1][0].setIcon(new ImageIcon("images/D.png"));
        while (i < (x * y * 0.2)) {
            int randomRow = (int) (Math.random() * board.length);
            int randomCol = (int) (Math.random() * board[0].length);
            if (randomCol == 0 && randomRow == 0) {
                randomRow = (int) (Math.random() * board.length);
                randomCol = (int) (Math.random() * board[0].length);
            }
            if (randomCol == 0 && randomRow == x - 1) {
                randomRow = (int) (Math.random() * board.length);
                randomCol = (int) (Math.random() * board[0].length);
            }
            if (randomCol == y - 1 && randomRow == 0) {
                randomRow = (int) (Math.random() * board.length);
                randomCol = (int) (Math.random() * board[0].length);
            }
            if (randomCol == y - 1 && randomRow == x - 1) {
                randomRow = (int) (Math.random() * board.length);
                randomCol = (int) (Math.random() * board[0].length);
            }
            while (board[randomRow][randomCol].isGold()) {
                randomRow = (int) (Math.random() * board.length);
                randomCol = (int) (Math.random() * board[0].length);
            }
            board[randomRow][randomCol].setGold(true);
            board[randomRow][randomCol].setText(altinDegerleri());
            if (board[randomRow][randomCol].getText().equals("bes.png")) {
                board[randomRow][randomCol].setIcon(new ImageIcon("images/bes.png"));

            }
            if (board[randomRow][randomCol].getText().equals("on.png")) {
                board[randomRow][randomCol].setIcon(new ImageIcon("images/on.png"));

            }
            if (board[randomRow][randomCol].getText().equals("onbes.png")) {
                board[randomRow][randomCol].setIcon(new ImageIcon("images/onbes.png"));

            }
            if (board[randomRow][randomCol].getText().equals("yirmi.png")) {
                board[randomRow][randomCol].setIcon(new ImageIcon("images/yirmi.png"));

            }

            i++;

        }

    }

    public void altinlariGizle() {

        gizlenenSayisi = 0;

        while (gizlenenSayisi < (x * y * 0.2 * 0.8)) {

            int r1 = (int) (Math.random() * board.length);
            int r2 = (int) (Math.random() * board.length);
            if (board[r1][r2].isGold()) {
                board[r1][r2].setIcon(new ImageIcon("images/gizli.png"));
                tahta.gizlenenTut[r1][r2] = 1;
                System.out.println("Gizlenen Koordinat " + r1 + " - " + r2);
                gizlenenSayisi++; // this.
            } else {
                tahta.gizlenenTut[r1][r2] = 0;
            }

        }
        System.out.println("GIZLENEN " + gizlenenSayisi);
        System.out.println("\n");
        tahta.gizlenenSayisi = gizlenenSayisi;

    }

    public int altinSayisiHesapla() {
        altinSayisi = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].gold == true) {
                    tahta.altinSayisi++;
                }
            }
        }
        return tahta.altinSayisi;
    }

    public String altinDegerleri() {
        int r;
        r = (int) (Math.random() * 4);
        return list.get(r);
    }

    public void koordinatYaz() {
        int a, b;
        for (a = 0; a < board.length; a++) {
            for (b = 0; b < board[0].length; b++) {
                if (board[a][b].isGold()) {
                    System.out.println(a + " - " + b);
                }
            }
            System.out.println("\n");
        }
    }

    public void altinDegerihesapla(Btn[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isGold()) {
                    if (board[i][j].getText().equals("bes.png")) {

                        tahta.altinMatrisi[i][j] = 5;
                    }
                    if (board[i][j].getText().equals("on.png")) {
                        tahta.altinMatrisi[i][j] = 10;

                    }
                    if (board[i][j].getText().equals("onbes.png")) {
                        tahta.altinMatrisi[i][j] = 15;
                    }
                    if (board[i][j].getText().equals("yirmi.png")) {
                        tahta.altinMatrisi[i][j] = 20;

                    }

                } else {
                    tahta.altinMatrisi[i][j] = 0;

                }
            }
        }

    }

    public void Oynat() throws IOException {

        AOyna A = new AOyna(AKonum, AHedef, baslangicAltin);
        BOyna B = new BOyna(BKonum, BHedef, baslangicAltin);
        COyna C = new COyna(CKonum, CHedef, baslangicAltin);
        DOyna D = new DOyna(DKonum, DHedef, baslangicAltin);

        //A.hedefAra(tahta,board, Askor, altinSayisi,  adimSayisi);
        //label5.setText(Integer.toString(Askor));
        //A.oyna(tahta, board);
        // A.hedefAra(tahta,board,  Askor, altinSayisi,  adimSayisi);
        //label5.setText(Integer.toString(Askor));
        //int i = 0;
        while (tahta.altinSayisi > 0) {
            A.hedefAra(tahta, board);
            A.oyna(tahta, board);
            label5.setText(Integer.toString(tahta.Askor));

            B.hedefAra(tahta, board);
            B.oyna(tahta, board);
            label6.setText(Integer.toString(tahta.Bskor));

            C.hedefAra(tahta, board);
            C.Oyna(tahta, board);
            label7.setText(Integer.toString(tahta.Cskor));

            D.hedefAra(tahta, board, A.AHedef, B.BHedef, C.CHedef, A.AKonum, B.BKonum, C.CKonum);
            D.Oyna(tahta, board);
            label8.setText(Integer.toString(tahta.Dskor));
            if(tahta.Askor<=0)
            {
                tahta.Askor=0;
                label5.setText("ELENDİ");
                board[AKonum[0]][AKonum[1]].setIcon(null);
            }
            if(tahta.Bskor<=0)
            {
                tahta.Bskor=0;
                label6.setText("ELENDİ");
                board[BKonum[0]][BKonum[1]].setIcon(null);
            }
            if(tahta.Cskor<=0)
            {
                tahta.Cskor=0;
                label7.setText("ELENDİ");
                board[CKonum[0]][CKonum[1]].setIcon(null);
            }
            if(tahta.Dskor<=0)
            {
                tahta.Dskor=0;
                label8.setText("ELENDİ");
                board[DKonum[0]][DKonum[1]].setIcon(null);
            }

            if (tahta.altinSayisi == 0) {

                if (tahta.Askor > tahta.Bskor && tahta.Askor > tahta.Cskor && tahta.Askor > tahta.Dskor) {
                    JOptionPane.showMessageDialog(null, "KAZANAN: A", "Tahtada Altın Kalmadı", JOptionPane.WARNING_MESSAGE);
                }
                if (tahta.Bskor > tahta.Askor && tahta.Bskor > tahta.Cskor && tahta.Bskor > tahta.Dskor) {
                    JOptionPane.showMessageDialog(null, "KAZANAN: B", "Tahtada Altın Kalmadı", JOptionPane.WARNING_MESSAGE);
                }
                if (tahta.Cskor > tahta.Askor && tahta.Cskor > tahta.Bskor && tahta.Cskor > tahta.Dskor) {
                    JOptionPane.showMessageDialog(null, "KAZANAN: C", "Tahtada Altın Kalmadı", JOptionPane.WARNING_MESSAGE);
                }
                if (tahta.Dskor > tahta.Askor && tahta.Dskor > tahta.Bskor && tahta.Dskor > tahta.Cskor) {
                    JOptionPane.showMessageDialog(null, "KAZANAN: D", "Tahtada Altın Kalmadı", JOptionPane.WARNING_MESSAGE);
                }

                break;
            }

            if (tahta.Askor <= 0 && tahta.Bskor <= 0 && tahta.Cskor <= 0 && tahta.Dskor <= 0) {
                JOptionPane.showMessageDialog(null, "Oyun Sona Erdi", "Oyuncuların yeterli sayıda altını kalmadı", JOptionPane.WARNING_MESSAGE);
                break;
            }
            if (tahta.Askor > 0 && tahta.Bskor <= 0 && tahta.Cskor <= 0 && tahta.Dskor <= 0) {
                JOptionPane.showMessageDialog(null, "KAZANAN: A", "Diğer oyuncuların yeterli sayıda altını kalmadı", JOptionPane.WARNING_MESSAGE);
                
                break;
            }
            if (tahta.Askor <= 0 && tahta.Bskor > 0 && tahta.Cskor <= 0 && tahta.Dskor <= 0) {
                JOptionPane.showMessageDialog(null, "KAZANAN: B", "Diğer oyuncuların yeterli sayıda altını kalmadı", JOptionPane.WARNING_MESSAGE);
                break;
            }
            if (tahta.Askor <= 0 && tahta.Bskor <= 0 && tahta.Cskor > 0 && tahta.Dskor <= 0) {
                JOptionPane.showMessageDialog(null, "KAZANAN: C", "Diğer oyuncuların yeterli sayıda altını kalmadı", JOptionPane.WARNING_MESSAGE);
                break;
            }
            if (tahta.Askor <= 0 && tahta.Bskor <= 0 && tahta.Cskor <= 0 && tahta.Dskor > 0) {
                JOptionPane.showMessageDialog(null, "KAZANAN: D", "Diğer oyuncuların yeterli sayıda altını kalmadı", JOptionPane.WARNING_MESSAGE);
                break;
            }

        }

        /*B.hedefAra(tahta, board, Bskor, altinSayisi, adimSayisi);
        B.oyna(tahta, board);
        label6.setText(Integer.toString(tahta.Bskor));
        A.hedefAra(tahta, board, Askor, altinSayisi, adimSayisi);
        A.oyna(tahta, board);
        label5.setText(Integer.toString(tahta.Askor));
        B.hedefAra(tahta, board, Bskor, altinSayisi, adimSayisi);
        B.oyna(tahta, board);
        label6.setText(Integer.toString(tahta.Bskor));*/
 /*COyna C = new COyna();
        Cskor=C.Oyna(board, altinMatrisi, Cskor,altinSayisi);
        label7.setText(Integer.toString(Cskor));
        DOyna D = new DOyna();
        Dskor=D.Oyna(board, altinMatrisi, Dskor,altinSayisi);
        label8.setText(Integer.toString(Dskor));*/
    }

}
