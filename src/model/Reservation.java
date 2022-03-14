package model;

/**
 * @author Xuan Li
 * @project MovieSeating
 */
public class Reservation {

    String reqNo;
    int seatCount;

    public Reservation(String reqNo, int seatCount) {
        this.reqNo = reqNo;
        this.seatCount = seatCount;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reqNo='" + reqNo + '\'' +
                ", seatCount=" + seatCount +
                '}';
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
