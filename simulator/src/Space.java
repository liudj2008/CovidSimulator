import java.util.*;

public class Space {
    private Position[][] matrix;
    private int personId;
    private List<Person> peopleStayed = new ArrayList<>();
    private List<Person> peopleLeft = new ArrayList<>();
    private final int size;
    private final int numOfPeople;
    private final int EXITROW = 0;
    private final int EXITCOL = 0;
    private final int ENTERROW = 0;
    private final int ENTERCOL = 0;
    private int timeStamp;
    private Set<Position> affectedPosition;
    private Map<Person, Position> personPositionMap = new HashMap<>();
    private int maskPercent;
    private int maskSickPercent;
    private int unMaskSickPercent;
    private int initialSick;

    public Space(int size, int numOfPeople, int initialSick, int maskPercent, int maskSickPercent, int unMaskSickPercent) {
        // construct matrix and randomized people distribution
        this.size = size;
        this.numOfPeople = numOfPeople;
        this.initialSick = initialSick;
        this.affectedPosition = new HashSet<>();
        this.matrix = new Position[size][size];
        this.maskPercent = maskPercent;
        this.maskSickPercent = maskSickPercent;
        this.unMaskSickPercent = unMaskSickPercent;

        // init the matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = new Position(i,j);
            }
        }

        for (int i = 0; i < numOfPeople; i++) {
            // take random position and assign it to people
            Position position = helper.getRandomPosition(size);
            int row = position.getRow();
            int col = position.getCol();
            Person newPerson;
            Sick sickStatus;
            boolean isSick = false;

            Mask mask = Mask.getRandomMask(maskPercent);
            if (mask == Mask.YES) {
                sickStatus = Sick.getRandomSickStatus(maskSickPercent);
            }
            else {
                sickStatus = Sick.getRandomSickStatus(unMaskSickPercent);
            }

            if (i < initialSick) {
                isSick = true;
                // update position info to be affected
                matrix[row][col].setAffected();
                affectedPosition.add(matrix[row][col]);
            }
            else if (affectedPosition.contains(new Position(row, col))) {
                if (sickStatus == Sick.YES) {
                    isSick = true;
                }
            }

            newPerson = new Person(personId, isSick, mask);

            if (newPerson.isSick()) {
                newPerson.setTimeStamp(timeStamp);
            }

            peopleStayed.add(newPerson);

            // update position information
            matrix[row][col].addPerson(newPerson);
            // update map information
            personPositionMap.put(newPerson, matrix[row][col]);
            // update personId
            personId++;
        }

    }

    List<Person> getPeopleStayed()  {
        return peopleStayed;
    }

    List<Person> getPeopleLeft() {
        return peopleLeft;
    }

    Position getPersonPosition(Person person) {
        return personPositionMap.get(person);
    }

    public List<Person> getSickPeople() {
        List<Person> sickPeople = new ArrayList<>();
        for (Person p: peopleStayed) {
            if(p.isSick()) {
                sickPeople.add(p);
            }
        }
        for (Person p: peopleLeft) {
            if (p.isSick()) {
                sickPeople.add(p);
            }
        }

        return sickPeople;
    }

    public List<Person> getHealthyPeople() {
        List<Person> healthyPeople = new ArrayList<>();
        for (Person p: peopleStayed) {
            if (!p.isSick()) {
                healthyPeople.add(p);
            }
        }

        for (Person p: peopleLeft) {
            if (!p.isSick()) {
                healthyPeople.add(p);
            }
        }
        return healthyPeople;
    }


    Position getPosition(int row, int col) {
        return matrix[row][col];
    }

    int getTimeStamp() {
        return timeStamp;
    }


    boolean canLeave() {
        return matrix[EXITROW][EXITCOL].canLeave();
    }

    void leave() {
        // call position leave method
        // offer people left into queue
        if (canLeave()) {
            List<Person> left = matrix[EXITROW][EXITCOL].leave();
            for (Person p: left) {
                peopleLeft.add(p);
                peopleStayed.remove(p);
                personPositionMap.remove(p);
            }
        }
    }

    void enter() {
        // person with person id will occupy this position
        // if the position is affected, add sick person

        Position p = matrix[ENTERROW][ENTERCOL];
        int numToEnter = numOfPeople - peopleStayed.size();
        boolean isSick = p.isAffected();
        Sick sickStatus;

        for (int i = 0; i < numToEnter; i++) {
            // check if the person is affected
            Mask mask = Mask.getRandomMask(maskPercent);

            if (mask == Mask.YES) {
                sickStatus = Sick.getRandomSickStatus(maskSickPercent);
            }
            else {
                sickStatus = Sick.getRandomSickStatus(unMaskSickPercent);
            }

            if (isSick) {
                // affected area
                if (sickStatus == Sick.NO) {
                    isSick = false;
                }
            }

            Person newPerson = new Person(personId, isSick, mask);
            if (newPerson.isSick()) {
                newPerson.setTimeStamp(timeStamp);
            }

            peopleStayed.add(newPerson);
            p.addPerson(newPerson);
            personPositionMap.put(newPerson, p);
            personId++;
        }
    }

    public void nextMove() {
        // update all the person listed to a new position
        // update person's isSick if they move to the affected area
        // remove people in the exit
        // add people to the enter
        timeStamp++;

        for (Person person: peopleStayed) {

            // next move
            Position cur = getPersonPosition(person);

            cur.removePerson(person);

            Position nextPosition = helper.getNextPosition(matrix, cur.getRow(), cur.getCol());
            int row = nextPosition.getRow();
            int col = nextPosition.getCol();
            Position next = matrix[row][col];

            // check if the area is affect and change person's profile
            if (next.isAffected()) {
                if (!person.isSick()) {
                    if ((person.getMask() == Mask.YES && Sick.getRandomSickStatus(maskSickPercent) == Sick.YES)
                            || (person.getMask() == Mask.NO && Sick.getRandomSickStatus(unMaskSickPercent) == Sick.YES)) {
                        // only set person who was not sick to be sick
                        person.setSick();
                        person.setTimeStamp(timeStamp);
                    }
                }
            }

            // update position information
            next.addPerson(person);

            // update person's new position in the positionMap
            personPositionMap.put(person, next);
        }

        // remove people in the exit and enter appropriate amount of new people
        leave();
        enter();
    }

}
