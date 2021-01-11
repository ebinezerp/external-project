package external.project.controller;

import external.project.exceptions.AfterCurrentDateException;
import external.project.exceptions.AfterToMonthException;
import external.project.exceptions.InvalidMonth;
import external.project.exceptions.InvalidMonthFormat;
import external.project.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;

@RestController
public class DataController {

    @Autowired
    private FileManager fileManager;

    @GetMapping("/data")
    public ResponseEntity<Void> dataFile(@RequestParam("frommonth")String fromMonth, @RequestParam("tomonth")String toMonth){
        try {
            fileManager.getData(fromMonth,toMonth);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (InvalidMonthFormat e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,e.getMessage());
        }catch (AfterCurrentDateException e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,e.getMessage());
        }catch (AfterToMonthException e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,e.getMessage());
        }
    }
}
