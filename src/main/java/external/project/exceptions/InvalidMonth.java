package external.project.exceptions;

public abstract class InvalidMonth extends Exception{
    protected String month;

    public InvalidMonth(String month){
        this.month = month;
    }

    @Override
    public String getMessage() {
        return "Invalid Month";
    }
}
