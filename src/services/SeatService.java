package services;

import model.Reservation;
import model.ReservedSeats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Xuan Li
 * @project MovieSeating
 */


/**
 * SeatService checks seats availabilities and signed seats according to request list
 */
public class SeatService {

    public int[][] theater;

    public final int rowNumber = 10;
    public final int colNumber = 20;

/**
 * constructor
 */
    public SeatService() {
        theater = new int[rowNumber][colNumber];
    }
    /**
     * booking is to assign seats according to the request list
     * @param reservation
     * @param customerSatisfy
     * @return HashMap<String, List<ReservedSeats>>
     *     it takes in the reservation list and check if it's possible to assign seats,
     *     and it also assign 3 buffered seats to the left of reserved seats.
     *
     *     oit contains 2 algo:
     *     customerSatisfy - always starts in the middle row and middle col to place customers in the middle.
     *     max efficiency  - always starts in the left-up corner to place customers.
     *
     */


    public HashMap<String, List<ReservedSeats>> booking(List<Reservation> reservation, boolean customerSatisfy) {
        int rowMid = 0;
        int colMid = 0;
        // for customer satisfaction, try to place in the middle
        if (customerSatisfy) {
            rowMid = rowNumber / 2;
            colMid = colNumber / 2;
        }
        // rowUp is the upper row of the current row
        int rowUp = rowMid-1;
        // rowDown is the lower row of the current row
        int rowDown = rowMid+1;

        HashMap<String, List<ReservedSeats>> reservedSeats = new HashMap();
        // loop the reservation list
        for (int i = 0; i < reservation.size(); i++) {
            boolean reservedSuccess = false;
            // for larger groups, customer need to contact ticket office
            if (reservation.get(i).getSeatCount() > 20) {
                System.out.println("For group tickets, Please contact ticket office.");
                continue;
            }

            if (customerSatisfy) {
                while (rowUp >= 0 || rowDown < rowNumber) {

                    //check availability of current row
                    if (checkAvailable(colMid, rowMid, reservation.get(i).getSeatCount(), reservedSeats, reservation.get(i).getReqNo())) {
                        reservedSuccess = true;
                        break;
                    } else {
                        // check availability of rowDown if rowUp reaches to border
                        if (rowUp == 0 && rowDown < rowNumber) {
                            rowMid = rowDown;
                            if (checkAvailable(colMid, rowDown, reservation.get(i).getSeatCount(), reservedSeats, reservation.get(i).getReqNo())) {

                                reservedSuccess = true;
//                                rowDown++;
                                break;
                            }
                            rowDown++;
                        }
                        // check availability of rowUp if rowDown reaches to border
                        if(rowDown == rowNumber-1 && rowUp >= 0){
                            rowMid = rowUp;
                            if (checkAvailable(colMid, rowUp, reservation.get(i).getSeatCount(), reservedSeats, reservation.get(i).getReqNo())) {

                                reservedSuccess = true;
//                                rowUp--;
                                break;
                            }
                            rowUp--;
                        }

                        // if current row not available, go to the min-distance neighbor row
                        if (Math.abs(rowDown - rowMid) >= Math.abs(rowUp - rowMid)) {
                            rowMid = rowUp;
                            if (checkAvailable(colMid, rowUp, reservation.get(i).getSeatCount(), reservedSeats, reservation.get(i).getReqNo())) {

                                reservedSuccess = true;
//                                rowUp--;
                                break;
                            }
                            rowUp--;

                        } else {
                            rowMid = rowDown;
                            if (checkAvailable(colMid, rowDown, reservation.get(i).getSeatCount(), reservedSeats, reservation.get(i).getReqNo())) {

                                reservedSuccess = true;
//                                rowDown++;
                                break;
                            }
                            rowDown++;
                        }

                    }
                }
                // for company max efficiency, always starts placement from up-left
            } else {
                for (int n = 0; n < rowNumber; n++) {
                    if (checkAvailable(colMid, n, reservation.get(i).getSeatCount(), reservedSeats, reservation.get(i).getReqNo())) {
                        reservedSuccess = true;
                        break;
                    }
                }
            }

            // if seats are available to book, update theater
            // 1 means reserved seats
            // 2 means buffered seats
            if (reservedSuccess) {
                List<ReservedSeats> seats = reservedSeats.get(reservation.get(i).getReqNo());
                for (int m = 0; m < seats.size(); m++) {
                    theater[seats.get(m).getResSeatsRow()][seats.get(m).getResSeatsCol()] = 1;
                }
                int buffer = 0;
                while (buffer < 3 ) {
                    if(seats.get(seats.size() - 1).getResSeatsCol() + buffer +1 == colNumber){
                        break;
                    }
                    theater[seats.get(0).getResSeatsRow()][seats.get(seats.size() - 1).getResSeatsCol() + buffer + 1] = 2;
                    buffer++;

                }
            } else {
                reservedSeats.remove(reservation.get(i).getReqNo());
                System.out.println("Can't find available seats for reservation - " + reservation.get(i));
            }


        }
        // prints
        for (String key : reservedSeats.keySet()) {
            System.out.println(key + " " + reservedSeats.get(key).toString());
        }

        return reservedSeats;
    }

    /**
     * this is check is seats are available to book and update reservationSeats list for output
     *
     * @param colStart
     * @param nextRow
     * @param seatCnt
     * @param reservedSeats
     * @param resNum
     * @return boolean
     */
    private boolean checkAvailable(int colStart, int nextRow, int seatCnt, HashMap<String, List<ReservedSeats>> reservedSeats, String resNum) {

        int[] currRow = theater[nextRow];
        int colMid = colStart - seatCnt / 2 >= 0 ? colStart - seatCnt / 2 : 0;
        int seatsStart = colMid;

        List<ReservedSeats> currSeats = new ArrayList<>();

        int seatCntTemp = seatCnt;

        while (seatCntTemp > 0 && seatsStart < colNumber) {
            // if mid seat available, add into currSeats
            if (currRow[seatsStart] == 0) {
                currSeats.add(new ReservedSeats(nextRow, seatsStart));
                seatCntTemp--;
                seatsStart++;
                // else, add empty list into currSeats
            } else {
                currSeats = new ArrayList<>();
                break;
            }
        }
        // if not empty , add into reservedSeats
        if (currSeats.size() > 0) {
            reservedSeats.put(resNum, currSeats);
            return true;
        }

        // if midseat taken, place at the beginning of the row
        seatCntTemp = seatCnt;


        for (int i = 0; i < colNumber-1; i++) {
            if (currRow[i] ==0 && currRow[i+1] ==0 ) {
                currSeats.add(new ReservedSeats(nextRow, i));
                seatCntTemp--;

            }else {

                currSeats = new ArrayList<>();
                seatCntTemp = seatCnt;
                continue;
            }

            if (seatCntTemp == 0) {
                break;
            }
        }

        if (seatCntTemp != 0) currSeats = new ArrayList<>();
        // if seats assigned, add to reservedSeats
        if (currSeats.size() > 0) {
            reservedSeats.put(resNum, currSeats);
            return true;
        }

        return false;
    }
}
