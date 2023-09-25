package com.examly.springapp;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringappApplicationTests {

	@Autowired
	private  MockMvc mockMvc ;

     @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void testAddBookApi() throws Exception {
        // Create a JSON representation of the Book
        String bookJson = "{\"id\": 1000, \"title\": \"Sindbad\",\"author\": \"Ram\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)) // Send the JSON representation as content
                .andExpect(status().isCreated()); // Expect HTTP status 201 for resource creation
    }
  

	@Test
	void getAllEmployees() throws Exception{	

		 mockMvc.perform(get("/books")
		 				.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").isArray())
						.andReturn();
	}


    @Test
    public void testGetBookByIdApi() throws Exception {
        // Uncomment the lines below and add your test logic:
        int menuItemId = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", menuItemId))
                .andExpect(status().isOk());
    }
	
}
