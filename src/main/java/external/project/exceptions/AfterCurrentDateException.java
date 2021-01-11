package external.project.exceptions;

public class AfterCurrentDateException extends InvalidMonth {

    public AfterCurrentDateException(String month) {
        super(month);
    }

    @Override
    public String getMessage() {
        return month+" is greater than current date";
    }
}
