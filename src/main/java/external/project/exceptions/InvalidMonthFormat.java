package external.project.exceptions;

public class InvalidMonthFormat extends InvalidMonth {
    public InvalidMonthFormat(String month) {
        super(month);
    }

    @Override
    public String getMessage() {
        return "Month format should be in yyyyMM, not "+month;
    }
}
