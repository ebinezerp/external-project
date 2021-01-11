package external.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import external.project.util.FileManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.annotation.security.RunAs;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DataController.class)
public class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FileManager fileManager;

    @Test
    public void dataFileTest() throws Exception {
        String fromMonth ="200404";
        String toMonth = "200507";
        doCallRealMethod().when(fileManager).getData(fromMonth,toMonth);
        MvcResult result = mockMvc.perform(get("/data").param("frommonth",fromMonth).param("tomonth",toMonth)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(response.getStatus(),200);
    }

    @Test
    public void dataFileTestWithOneDigitMonth() throws Exception {
        String fromMonth ="200404";
        String toMonth = "20057";
        doCallRealMethod().when(fileManager).getData(fromMonth,toMonth);
        MvcResult result = mockMvc.perform(get("/data").param("frommonth",fromMonth).param("tomonth",toMonth)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(response.getStatus(),200);
    }

    @Test
    public void dataFileTestWhenFromMonthIsAfterCurrentDate() throws Exception {
        String fromMonth ="202404";
        String toMonth = "200507";
        doCallRealMethod().when(fileManager).getData(fromMonth,toMonth);
        MvcResult result = mockMvc.perform(get("/data").param("frommonth",fromMonth).param("tomonth",toMonth)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(response.getStatus(), HttpStatus.NOT_ACCEPTABLE.value());
        assertEquals(response.getErrorMessage(),fromMonth+" is greater than current date");
    }


    @Test
    public void dataFileTestWhenToMonthIsAfterCurrentDate() throws Exception {
        String fromMonth ="201404";
        String toMonth = "202507";
        doCallRealMethod().when(fileManager).getData(fromMonth,toMonth);
        MvcResult result = mockMvc.perform(get("/data").param("frommonth",fromMonth).param("tomonth",toMonth)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(response.getStatus(), HttpStatus.NOT_ACCEPTABLE.value());
        assertEquals(response.getErrorMessage(),toMonth+" is greater than current date");
    }


    @Test
    public void dataFileTestInvalidMonth() throws Exception {
        String fromMonth ="200414";
        String toMonth = "200507";
        doCallRealMethod().when(fileManager).getData(fromMonth,toMonth);
        MvcResult result = mockMvc.perform(get("/data").param("frommonth",fromMonth).param("tomonth",toMonth)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(response.getStatus(), HttpStatus.NOT_ACCEPTABLE.value());
        assertEquals(response.getErrorMessage(),"Month format should be in yyyyMM, not "+fromMonth);
    }
}
