import com.sun.jdi.connect.Connector;

public class Simulator {
    // run the simulation for 1000 times
    public static void main(String[] args) {
        int numOfPeople = Integer.parseInt(args[0]);
        int initialSick = Integer.parseInt(args[1]);
        String path = args[2];
        int maskPercent = Integer.parseInt(args[3]);
        int maskSickPercent = Integer.parseInt(args[4]);
        int unMaskSickPercent = Integer.parseInt(args[5]);

        for (int i = 0; i < 1000; i++) {
            Main newMain = new Main(numOfPeople, initialSick, i, path, maskPercent, maskSickPercent, unMaskSickPercent);
            newMain.run();
        }
    }
}