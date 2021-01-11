package external.project.util;

import external.project.exceptions.AfterDate;
import external.project.exceptions.InvalidateMonth;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileManager {
    public void getData(String date) throws ParseException, InvalidateMonth{
        if(validateDate(date)){

        }
    }

    private boolean validateDate(String date) throws ParseException, InvalidateMonth {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        if(dateFormat.parse(date).after(new Date())){
            throw new AfterDate(date);
        }
        return true;
    }
}
