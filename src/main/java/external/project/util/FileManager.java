package external.project.util;

import external.project.exceptions.AfterCurrentDateException;
import external.project.exceptions.AfterToMonthException;
import external.project.exceptions.InvalidMonth;
import external.project.exceptions.InvalidMonthFormat;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileManager {

    public void getData(String fromMonth, String toMonth) throws InvalidMonthFormat, AfterCurrentDateException ,AfterToMonthException{

        if(validateDate(fromMonth) && validateDate(toMonth)){
                if (getDateType(fromMonth).after(getDateType(toMonth))) {
                    throw new AfterToMonthException(fromMonth, toMonth);
                }

        }
    }

    private boolean validateDate(String month) throws  InvalidMonthFormat,AfterCurrentDateException {
            if (getDateType(month).after(new Date())) {
                throw new AfterCurrentDateException(month);
            }
            return true;

    }
    
    private Date getDateType(String month) throws InvalidMonthFormat{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(month);
        }catch (ParseException e){
            throw new InvalidMonthFormat(month);
        }
    }
}
