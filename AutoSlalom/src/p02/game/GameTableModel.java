package p02.game;

import javax.swing.table.AbstractTableModel;

public class GameTableModel extends AbstractTableModel {
    Integer [] przeszkody= new Integer[7];
    private Board board;
    public GameTableModel(Board board) {
        this.board = board;
        for(int i =1;i<board.getPlansza().length;i++)
            przeszkody[i]=board.getPlansza()[i];

    }
    @Override
    public int getRowCount() { return 2; }

    @Override
    public int getColumnCount() { return 7; }



    @Override
    public Integer getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex == 0) {
            return board.getPlansza()[columnIndex];
        } else if (rowIndex == 1) {
            return board.panel[columnIndex];
        }
        return null;
    }
}
