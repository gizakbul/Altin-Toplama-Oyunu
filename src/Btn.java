package yazlab1.pkg1;

import javax.swing.JButton;

public class Btn extends JButton {

    private int row, col;
    boolean gold;  //Altın var mı

    public Btn(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.gold = false;
    }

    public Btn() {
    }

    int gizlenenSayisi;//Gizli altin sayisini tutar
    int altinSayisi;//Toplam Altin sayisini tutar
    int adimSayisi;

    int[][] altinMatrisi = new int[100][100]; //Altinlarin yerlestigi konumlari tutar
    int[][] gizlenenTut = new int[100][100]; //Gizli altinlarin konumunu tutar
    int Askor;
    int Bskor;
    int Cskor;
    int Dskor;

    int AhamleP, AhedefP;
    int BhamleP, BhedefP;
    int ChamleP, ChedefP;
    int DhamleP, DhedefP;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isGold() {
        return gold;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

}
