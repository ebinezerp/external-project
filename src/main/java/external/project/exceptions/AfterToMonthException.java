package external.project.exceptions;

public class AfterToMonthException extends Exception{
    private String fromMonth;
    private String toMonth;

    public AfterToMonthException(String fromMonth, String toMonth) {
        this.fromMonth = fromMonth;
        this.toMonth = toMonth;
    }

    @Override
    public String getMessage() {
        return "From month is greater than to month: "+fromMonth+">"+toMonth;
    }
}
