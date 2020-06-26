import java.util.*;

public class Position {
    private boolean affected;
    private List<Person> p;
    private int row;
    private int col;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
        this.p = new ArrayList<>();
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    void setAffected() {
        affected = true;
    }

    boolean isAffected() {
        return affected;
    }

    void addPerson(Person person) {
        p.add(person);
    }

    void removePerson(Person person) {
        p.remove(person);
    }

    List<Person> getPeopleList() {return p;}

    boolean canLeave() {
        // if row and col is at enter/exit, the person should leave
        return !p.isEmpty();
    }

    List<Person> leave() {
        List<Person> peopleLeft = new ArrayList<>();

        for (Person person: p) {
            peopleLeft.add(person);
        }

        // clear the list of people
        p.clear();
        return peopleLeft;

    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (! (o instanceof Position)) {
            return false;
        }

        // class cast
        Position newPosition = (Position) o;
        return  newPosition.row == row && newPosition.col == col;

    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }

}

