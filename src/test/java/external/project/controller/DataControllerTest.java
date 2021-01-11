package external.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import external.project.util.FileManager;
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
        String date ="200404";
        doCallRealMethod().when(fileManager).getData(date);
        MvcResult result = mockMvc.perform(get("/data/"+date)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(response.getStatus(),200);
    }

    @Test
    public void dataFileTestAfterCurrentDate() throws Exception {
        String date ="202404";
        doCallRealMethod().when(fileManager).getData(date);
        MvcResult result = mockMvc.perform(get("/data/"+date)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(response.getStatus(), HttpStatus.NOT_ACCEPTABLE.value());
        assertEquals(response.getErrorMessage(),"Month always should be less than current");
    }

    @Test
    public void dataFileTestInvalidMonth() throws Exception {
        String date ="202415";
        doCallRealMethod().when(fileManager).getData(date);
        MvcResult result = mockMvc.perform(get("/data/"+date)).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(response.getStatus(), HttpStatus.NOT_ACCEPTABLE.value());
        assertEquals(response.getErrorMessage(),"Month always should be less than current");
    }
}
