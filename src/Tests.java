import model.Reservation;
import model.ReservedSeats;
import services.SeatService;
import utils.FileProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Xuan Li
 * @project MovieSeating
 */

public class Tests {

    public static void main(String[] args) throws Exception {
        testBooking1();
        testBooking2();
        testBooking3();
        testBooking4();
        testBooking5();
        testBooking6();
    }




    //test booking for max efficiency for place 1 seat reservation --Passed
    public static void testBooking1() throws Exception {
        String filePath = "resouces/tests/testHappyPath.txt";
        FileProcess fileProcess = new FileProcess();
        List<Reservation> reservationList = fileProcess.readFile(filePath);

        SeatService seatService = new SeatService();
        HashMap<String, List<ReservedSeats>> actual  =  seatService.booking(reservationList, false);
        HashMap<String, List<ReservedSeats>> expect = new HashMap<>();
        List<ReservedSeats> expectSeats = new ArrayList<>();
        expectSeats.add(new ReservedSeats(0, 0));
        expect.put("R001", expectSeats);
        if(expect.keySet().size()!=actual.keySet().size()){
//            System.out.println("Test failed! expect and actual doesn't match");
            throw new Exception("testBooking1  - Test failed! expect and actual doesn't match");
        }
        for(String resNo : expect.keySet()) {
            if (!expect.get(resNo).toString().equals(actual.get(resNo).toString())){
                throw new Exception("testBooking1  - Test failed! expect and actual doesn't match");
            }
        }

        System.out.println("testBooking1 - Test passed");
    }

     //check available seats max profit one --- PASSED
    public static void testBooking2() throws Exception {
        String filePath = "resouces/tests/testHappyPath.txt";
        FileProcess fileProcess = new FileProcess();
        List<Reservation> reservationList = fileProcess.readFile(filePath);

        SeatService seatService = new SeatService();
        // first seat taken, should place at (0, 4)
        seatService.theater[0][0] =1;
        seatService.theater[0][1] =2;
        seatService.theater[0][2] =2;
        seatService.theater[0][3] =2;
        HashMap<String, List<ReservedSeats>> actual  =  seatService.booking(reservationList, false);
        HashMap<String, List<ReservedSeats>> expect = new HashMap<>();
        List<ReservedSeats> expectSeats = new ArrayList<>();
        expectSeats.add(new ReservedSeats(0, 4));
        expect.put("R001", expectSeats);
        if(expect.keySet().size()!=actual.keySet().size()){
//            System.out.println("Test failed! expect and actual doesn't match");
            throw new Exception("testBooking2  - Test failed! expect and actual size don't match");
        }
        for(String resNo : expect.keySet()) {
            if (!expect.get(resNo).toString().equals(actual.get(resNo).toString())){
                throw new Exception("testBooking2  - Test failed! expect and actual seats number don't match");
            }
        }

        System.out.println("testBooking2 - Test passed");
    }

         //check available seats max profit one --- PASSED
    public static void testBooking3() throws Exception {
        String filePath = "resouces/tests/testPlaceToSecondRow.txt";
        FileProcess fileProcess = new FileProcess();
        List<Reservation> reservationList = fileProcess.readFile(filePath);

        SeatService seatService = new SeatService();
        // first row 0-12 seats taken, should place 10-seat reservation at second row
        seatService.theater[0][0] =1;
        seatService.theater[0][1] =1;
        seatService.theater[0][2] =1;
        seatService.theater[0][3] =1;
        seatService.theater[0][4] =1;
        seatService.theater[0][5] =1;
        seatService.theater[0][6] =1;
        seatService.theater[0][7] =1;
        seatService.theater[0][8] =1;
        seatService.theater[0][9] =1;
        seatService.theater[0][10] =1;
        seatService.theater[0][11] =1;

        HashMap<String, List<ReservedSeats>> actual  =  seatService.booking(reservationList, false);
        HashMap<String, List<ReservedSeats>> expect = new HashMap<>();
        List<ReservedSeats> expectSeats = new ArrayList<>();
        expectSeats.add(new ReservedSeats(1, 0));
        expectSeats.add(new ReservedSeats(1, 1));
        expectSeats.add(new ReservedSeats(1, 2));
        expectSeats.add(new ReservedSeats(1, 3));
        expectSeats.add(new ReservedSeats(1, 4));
        expectSeats.add(new ReservedSeats(1, 5));
        expectSeats.add(new ReservedSeats(1, 6));
        expectSeats.add(new ReservedSeats(1, 7));
        expectSeats.add(new ReservedSeats(1, 8));
        expectSeats.add(new ReservedSeats(1, 9));
        expect.put("R001", expectSeats);
        if(expect.keySet().size()!=actual.keySet().size()){
//            System.out.println("Test failed! expect and actual doesn't match");
            throw new Exception("testBooking3  - Test failed! expect and actual size don't match");
        }
        for(String resNo : expect.keySet()) {
            System.out.println(expect.get(resNo));
            System.out.println(actual.get(resNo));
            if (!expect.get(resNo).toString().equals(actual.get(resNo).toString())){
                throw new Exception("testBooking3 - Test failed! expect and actual seats number don't match");
            }
        }

        System.out.println("testBooking3 - Test passed");
    }

    //check customer satisfy available seats  -- PASSED
    public static void testBooking4() throws Exception {
        String filePath = "resouces/tests/testHappyPath.txt";
        FileProcess fileProcess = new FileProcess();
        List<Reservation> reservationList = fileProcess.readFile(filePath);

        SeatService seatService = new SeatService();
        seatService.theater[5][0] =2;
        HashMap<String, List<ReservedSeats>> actual  =  seatService.booking(reservationList, true);
        HashMap<String, List<ReservedSeats>> expect = new HashMap<>();
        List<ReservedSeats> expectSeats = new ArrayList<>();
        expectSeats.add(new ReservedSeats(5, 10));
        expect.put("R001", expectSeats);
        if(expect.keySet().size()!=actual.keySet().size()){
//            System.out.println("Test failed! expect and actual doesn't match");
            throw new Exception("testBooking4 - Test failed! expect and actual size don't match");
        }
        for(String resNo : expect.keySet()) {
            if (!expect.get(resNo).toString().equals(actual.get(resNo).toString())){
                throw new Exception("testBooking4 - Test failed! expect and actual seats number don't match");
            }
        }

        System.out.println("testBooking4 - Test passed");
    }

    //check customer satisfy availble seats  -- PASSED
    public static void testBooking5() throws Exception {
        String filePath = "resouces/tests/testHappyPath.txt";
        FileProcess fileProcess = new FileProcess();
        List<Reservation> reservationList = fileProcess.readFile(filePath);

        SeatService seatService = new SeatService();
        seatService.theater[5][10] =2;
        HashMap<String, List<ReservedSeats>> actual  =  seatService.booking(reservationList, true);
        HashMap<String, List<ReservedSeats>> expect = new HashMap<>();
        List<ReservedSeats> expectSeats = new ArrayList<>();
        expectSeats.add(new ReservedSeats(5, 0));
        expect.put("R001", expectSeats);
        if(expect.keySet().size()!=actual.keySet().size()){
//            System.out.println("Test failed! expect and actual doesn't match");
            throw new Exception("testBooking5 - Test failed! expect and actual size don't match");
        }
        for(String resNo : expect.keySet()) {
            if (!expect.get(resNo).toString().equals(actual.get(resNo).toString())){
                throw new Exception("testBooking5 - Test failed! expect and actual seats number don't match");
            }
        }

        System.out.println("testBooking5 - Test passed");
    }


//    check customer satisfy available seats  -- PASSED
    public static void testBooking6() throws Exception {
        String filePath = "resouces/tests/testHappyPath.txt";
        FileProcess fileProcess = new FileProcess();
        List<Reservation> reservationList = fileProcess.readFile(filePath);

        SeatService seatService = new SeatService();
        // mid seat and first seats taken, should place at (5,4)
        seatService.theater[5][0] =1;
        seatService.theater[5][1] =2;
        seatService.theater[5][2] =2;
        seatService.theater[5][3] =2;
        seatService.theater[5][10] =1;
        seatService.theater[5][11] =2;
        seatService.theater[5][12] =2;
        seatService.theater[5][13] =2;
        HashMap<String, List<ReservedSeats>> actual  =  seatService.booking(reservationList, true);
        HashMap<String, List<ReservedSeats>> expect = new HashMap<>();
        List<ReservedSeats> expectSeats = new ArrayList<>();
        expectSeats.add(new ReservedSeats(5, 4));
        expect.put("R001", expectSeats);
        if(expect.keySet().size()!=actual.keySet().size()){
//            System.out.println("Test failed! expect and actual doesn't match");
            throw new Exception("testBooking6 - Test failed! expect and actual size don't match");
        }
        for(String resNo : expect.keySet()) {
            if (!expect.get(resNo).toString().equals(actual.get(resNo).toString())){
                throw new Exception(" testBooking6 - Test failed! expect and actual seats number don't match");
            }
        }

        System.out.println("testBooking6 - Test passed");
    }
}

