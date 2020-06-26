import java.util.*;

public class Test {
    public static void main(String[] args) {
        // test case
        int size = 10;
        int numOfPeople = 100;
        Space market = new Space(size, numOfPeople, 1,0, 0, 100);
        List<Person> personList = market.getPeopleStayed();
        List<Person> personLeft = market.getPeopleLeft();

        // check personList size
        assert personList.size() == size;
        // check personLeft size
        assert personLeft.size() == 0;
        // check people at the same position with person 0 is affected to be sick
        Position position = market.getPersonPosition(personList.get(0));
        int affectedRow = position.getRow();
        int affectedCol = position.getCol();

        for (int i = 1; i < personList.size(); i++) {
            Person p = personList.get(i);
            Position position1 = market.getPersonPosition(p);

            if (p.isSick()) {
                assert position1.getRow() == position.getRow() && position1.getCol() == position.getCol();
            } else {
                assert position1.getRow() != position.getRow() || position1.getCol() != position.getCol();
            }
        }

        for (int i = 0; i < 100; i++) {
            market.nextMove();

            assert market.getPeopleStayed().size() == size;

            Position p = market.getPosition(affectedRow, affectedCol);
            for (Person person : p.getPeopleList()) {
                // make sure people who stepped into affected area are sick
                assert person.isSick();
                assert person.getTimeStamp() == market.getTimeStamp();
            }

        }

        List<Person> sickPeople = market.getSickPeople();
        List<Person> healthyPeople = market.getHealthyPeople();
        for (Person p : sickPeople) {
            assert !p.isSick();
        }

        for (Person p : healthyPeople) {
            assert !p.isSick();
            assert p.getTimeStamp() == -1;
        }
    }
}
