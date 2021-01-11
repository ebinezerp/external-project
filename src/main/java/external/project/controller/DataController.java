package external.project.controller;

import external.project.exceptions.InvalidateMonth;
import external.project.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;

@RestController
public class DataController {

    @Autowired
    private FileManager fileManager;

    @GetMapping("/data/{date}")
    public ResponseEntity<Void> dataFile(@PathVariable("date")String date){
        try {
            fileManager.getData(date);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ParseException e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Month should in 'yyyyMM' format");
        }catch (InvalidateMonth e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Month always should be less than current");
        }
    }
}
