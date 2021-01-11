package external.project.exceptions;

public class AfterDate extends InvalidateMonth{

    public AfterDate(String month) {
        super(month);
    }

    @Override
    public String getMessage() {
        return month+" is greater than current date";
    }
}
