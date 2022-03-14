package model;

/**
 * @author Xuan Li
 * @project MovieSeating
 */


public class ReservedSeats {
    int resSeatsRow;
    int resSeatsCol;

    public ReservedSeats(int resSeatsRow, int resSeatsCol) {
        this.resSeatsRow = resSeatsRow;
        this.resSeatsCol = resSeatsCol;
    }

    @Override
    public String toString() {
        return "ReservedSeats{" +
                "resSeatsRow=" + resSeatsRow +
                ", resSeatsCol=" + resSeatsCol +
                '}';
    }

    public int getResSeatsRow() {
        return resSeatsRow;
    }

    public void setResSeatsRow(int resSeatsRow) {
        this.resSeatsRow = resSeatsRow;
    }

    public int getResSeatsCol() {
        return resSeatsCol;
    }

    public void setResSeatsCol(int resSeatsCol) {
        this.resSeatsCol = resSeatsCol;
    }
}
