import java.io.*;

public class Main {
    private int numOfPeople;
    private int version;
    private String path;
    private final int SIZE = 10;
    private final int maskPercent;
    private final int maskSickPercent;
    private final int unMaskSickPercent;
    private final int initialSick;


    Main(int numOfPeople, int initialSick, int version, String path, int maskPercent, int maskSickPercent, int unMaskSickPercent) {
        this.numOfPeople = numOfPeople;
        this.version = version;
        this.path = path;
        this.maskPercent = maskPercent;
        this.maskSickPercent = maskSickPercent;
        this.unMaskSickPercent = unMaskSickPercent;
        this.initialSick = initialSick;
    }

    void run() {
    // write the results to file
    // round, id, row, col, isSick, timeStampIfSick, stay

        Space market = new Space(SIZE, numOfPeople, initialSick, maskPercent, maskSickPercent, unMaskSickPercent);

        try {
            FileWriter myWriter = new FileWriter(path + "output" + version + ".csv");

            // write column name
            myWriter.write("round" + ","
                    + "id" + ","
                    + "row" + ","
                    + "col" + ","
                    + "isSick" + ","
                    + "timeStampIfSick" + ","
                    + "stay" + ","
                    + "mask"
                    + "\n");

            for (int i = 0; i < 100; i++) {
                if (i == 0) {
                    writeToFile(myWriter, market);
                }
                else {
                    market.nextMove();
                    writeToFile(myWriter, market);
                }
            }

            myWriter.close();

            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    private static void writeToFile(FileWriter myWriter, Space market) {
        try {
            for (Person p: market.getPeopleStayed()) {
                myWriter.write(market.getTimeStamp() + ","
                        + p.getId() + ","
                        + market.getPersonPosition(p).getRow() + ","
                        + market.getPersonPosition(p).getCol() + ","
                        + p.isSick() + ","
                        + p.getTimeStamp() + ","
                        + "true" + ","
                        + p.getMask()
                        + "\n");
            }

            for (Person p: market.getPeopleLeft()) {
                myWriter.write(market.getTimeStamp() + ","
                        + p.getId() + ","
                        + "null" + ","
                        + "null" + ","
                        + p.isSick() + ","
                        + p.getTimeStamp() + ","
                        + "false" + ","
                        + p.getMask()
                        + "\n");
            }

        } catch (IOException e) {
            System.out.println("Documentation error occurred.");
            e.printStackTrace();
        }
    }

}
