import model.Reservation;
import services.SeatService;
import utils.FileProcess;

import java.util.List;

/**
 * @author Xuan Li
 * @project MovieSeating
 */


public class Solutions {

    public static void main(String[] args) {
        //"src/resouces/2or1seats"
        if (args.length > 0) {
            String filePath = args[0];
            FileProcess fileProcess = new FileProcess();
            List<Reservation> reservationList = fileProcess.readFile(filePath);

            SeatService seatService = new SeatService();
            fileProcess.writeToFile(seatService.booking(reservationList, false), reservationList);

        } else {
            System.out.println("Please indicate input file path");
        }
    }

}
