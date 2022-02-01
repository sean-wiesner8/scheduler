package schedule;

public class Course {

    private String name;
    private int credits;
    private int creditsLeft;

    public Course(String name, int credits) {

        this.name= name;
        this.credits= credits;
        creditsLeft= credits;
    }

    @Override
    public String toString() {
        return name() + ", " + Integer.toString(credits());
    }

    public String name() {
        return name;
    }

    public int credits() {
        return credits;
    }

    public int creditsLeft() {
        return creditsLeft;
    }

    public void reduceCreditsLeft() {
        creditsLeft= creditsLeft - 1;
    }

    public void increaseCreditsLeft() {
        creditsLeft++ ;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }

        if (obj.getClass() != this.getClass()) { return false; }

        final Course other= (Course) obj;
        if (name == null ? other.name != null : !name.equals(other.name)) { return false; }

        if (credits != other.credits) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int hash= 3;
        hash= 53 * hash + (name != null ? name.hashCode() : 0);
        hash= 53 * hash + credits;
        return hash;
    }

}
