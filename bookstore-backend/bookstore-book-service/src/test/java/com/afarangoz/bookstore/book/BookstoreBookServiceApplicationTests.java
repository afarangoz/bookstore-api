package com.afarangoz.bookstore.book;

import static com.afarangoz.bookstore.api.common.util.CommonConstants.BOOKS;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.ID_PATH_VARIABLE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.SLASH_SYMBOL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.afarangoz.bookstore.api.common.model.dto.BookDTO;
import com.afarangoz.bookstore.api.common.util.ApiUtil;

@SpringBootTest(classes = BookstoreBookServiceApplication.class)
@AutoConfigureMockMvc
class BookstoreBookServiceApplicationTests {

	private static final String API_NAME = SLASH_SYMBOL + BOOKS;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void controllerTests() throws Exception {
		//Test Case 1: Create
		BookDTO dto = new BookDTO();
		dto.setName("TestName");
		dto.setAvailable(25L);
		dto.setPrice(85000L);
		String inputJson = ApiUtil.mapToJson(dto);
		MvcResult mvcResult = mockMvc.perform(post(API_NAME).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andDo(print()).andExpect(status().isCreated()).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		BookDTO dtoResponse = ApiUtil.mapFromJson(content, BookDTO.class);
		assertNotNull(dtoResponse);
		assertNotNull(dtoResponse.getId());

		//Test Case 2: Find by Id
		mvcResult = this.mockMvc.perform(get(API_NAME + ID_PATH_VARIABLE, dtoResponse.getId())).andDo(print()).andExpect(status().isOk()).andReturn();
		content = mvcResult.getResponse().getContentAsString();
		dtoResponse = ApiUtil.mapFromJson(content, BookDTO.class);
		assertNotNull(dtoResponse);
		assertEquals(dto.getName(), dtoResponse.getName());
		
		//Test Case 3: Update
		BookDTO newDTO = new BookDTO();
		newDTO.setId(dtoResponse.getId());
		newDTO.setAvailable(dtoResponse.getAvailable());
		newDTO.setPrice(dtoResponse.getPrice());
		String name = "BookNameUpdated";
		newDTO.setName(name);
		inputJson = ApiUtil.mapToJson(newDTO);
		mvcResult = this.mockMvc.perform(put(API_NAME + ID_PATH_VARIABLE , dtoResponse.getId()).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andExpect(status().isOk()).andReturn();
		content = mvcResult.getResponse().getContentAsString();
		dtoResponse = ApiUtil.mapFromJson(content, BookDTO.class);
		assertNotNull(dtoResponse);
		assertEquals(name, dtoResponse.getName());
		
		//Test Case 4: Find all
		mvcResult = this.mockMvc.perform(get(API_NAME)).andDo(print()).andExpect(status().isOk()).andReturn();
		content = mvcResult.getResponse().getContentAsString();
		BookDTO[] dtoArray = ApiUtil.mapFromJson(content, BookDTO[].class);
		assertNotNull(dtoArray);
		assertTrue(dtoArray.length >= 1);
		
		//Test Case 5: Delete
		mvcResult = mockMvc.perform(delete(API_NAME + ID_PATH_VARIABLE , dtoResponse.getId()).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNoContent()).andReturn();
	}
}


