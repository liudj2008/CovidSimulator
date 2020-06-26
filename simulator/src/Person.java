import java.util.Random;

public class Person {
    private int id;
    private boolean isSick;
    private Mask mask;
    private int timeStamp = -1; // at which step the person is sick

    Person(int id, boolean isSick, Mask mask) {
        this.id = id;
        this.isSick = isSick;
        this.mask = mask;
    }

    int getId() {
        return id;
    }

    Mask getMask() {
        return mask;
    }

    void setSick() {
        isSick = true;
    }

    boolean isSick() {return isSick;}

    void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    int getTimeStamp() {
        return this.timeStamp;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof Person)) {
            return false;
        }
        return id == ((Person) o).id;
    }

    @Override
    public int hashCode() {
        return id;
    }

        // move the next position
        // row change and col change if possible
        // update person's position
}

enum Mask {
    YES, NO;
    static Mask getRandomMask(int prob){
        // probability of wearing a mask
        if (Probability.getResult(prob) == Probability.YES) {
            return Mask.YES;
        }
        return Mask.NO;
    }
}

enum Sick {
    YES, NO;

    static Sick getRandomSickStatus(int prob){
        // probability of wearing a mask
        if (Probability.getResult(prob) == Probability.YES) {
            return Sick.YES;
        }
        return Sick.NO;
    }
}

enum Probability  {
    // rolling dice
    YES, NO;

    static Probability getResult(int prob) {
        // return YES OR NO according to given YES probability
        Random rand = new Random();
        Probability result = Probability.NO;

        if (prob > 100 || prob < 0) throw new IllegalArgumentException();
        if (prob == 0) result = Probability.NO;
        else if (prob == 100) result = Probability.YES;

        else if (1 + rand.nextInt(100) <= prob) {
            result =  Probability.YES;
        }

        return  result;
    }
}
