package ohtu.kevatpaiva;

import ohtu.kevatpaiva.controllers.ViiteController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Daniel Lillqvist <daniel.lillqvist@helsinki.fi>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class ViiteControllerTest {
 
    @Autowired
    private WebApplicationContext context;
 
    private MockMvc mockMvc;
 
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    public void naytaLomake() throws Exception {
        mockMvc.perform(get("/").accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().contentType(MediaType.TEXT_HTML));
    }
    
    @Configuration
    public static class TestConfiguration {
 
        @Bean
        public ViiteController referenceController() {
            return new ViiteController();
        }
 
    }
    
}
