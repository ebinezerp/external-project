package external.project.exceptions;

public class InvalidateMonth extends Exception{
    protected String month;

    public InvalidateMonth(String month){
        this.month = month;
    }

    @Override
    public String getMessage() {
        return "Invalid Month";
    }
}
