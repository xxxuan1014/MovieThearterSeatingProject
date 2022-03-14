package utils;

import model.Reservation;
import model.ReservedSeats;

import java.io.*;
import java.util.*;

/**
 * @author Xuan Li
 * @project MovieSeating
 */

public class FileProcess {
    public FileProcess(){}

    /**
     * read the file
     * @param inputFilename
     * @return
     */
    public List<Reservation> readFile(String inputFilename) {
        List<Reservation> content = new ArrayList<>();
        try {
            File file = new File(inputFilename);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] items = line.split( " ");
                content.add(new Reservation(items[0], Integer.valueOf(items[1])));
                line = bufferedReader.readLine();
            }

//            System.out.println(content.toString());
        } catch (FileNotFoundException ex) {
            System.err.println("Input file not Found.");
            ex.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * write to output file
     *
     * @param content
     * @param reservationList
     */
    public void writeToFile(HashMap<String, List<ReservedSeats>> content, List<Reservation> reservationList) {
        BufferedWriter wr = null;
        try {
            wr = new BufferedWriter(new FileWriter("../output.txt"));

            for (int m=0; m<reservationList.size(); m++) {
                StringBuffer seatNo = new StringBuffer();
                List<ReservedSeats> list = content.get(reservationList.get(m).getReqNo());
                if(list == null||list.size() ==0){
//                    System.out.println("No available seats");
                    throw new NullPointerException("No available seats");
//                    continue;
                }else {
                    // convert int to ASICII letter
                    for (int i = 0; i < list.size(); i++) {
                        seatNo.append(Character.toString((char) (list.get(i).getResSeatsRow() + 65)) +
                                (list.get(i).getResSeatsCol() + 1));
                        if (i != list.size() - 1) {
                            seatNo.append(",");

                        }
                    }
                }
                String str = reservationList.get(m).getReqNo() + " " + seatNo.toString();
                System.out.print(str + "\n");
                wr.write(str + "\n");
            }
            wr.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Output file not Found.");
            ex.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
