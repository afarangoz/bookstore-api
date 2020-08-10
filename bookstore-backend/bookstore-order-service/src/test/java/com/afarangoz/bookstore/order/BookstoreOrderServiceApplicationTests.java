package com.afarangoz.bookstore.order;

import static com.afarangoz.bookstore.api.common.util.CommonConstants.ID_PATH_VARIABLE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.ORDERS;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.SLASH_SYMBOL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.afarangoz.bookstore.api.common.model.dto.BookDTO;
import com.afarangoz.bookstore.api.common.model.dto.OrderBookDTO;
import com.afarangoz.bookstore.api.common.model.dto.OrderDTO;
import com.afarangoz.bookstore.api.common.model.dto.UserDTO;
import com.afarangoz.bookstore.api.common.util.ApiUtil;

@SpringBootTest
@AutoConfigureMockMvc
class BookstoreOrderServiceApplicationTests {

	private static final String API_NAME = SLASH_SYMBOL + ORDERS;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void controllerTests() throws Exception {
		//Test Case 1: Create
		OrderDTO dto = new OrderDTO();
		
		//Set userDTO
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		dto.setUserDTO(userDTO);
		
		//Set orderBookDTO
		OrderBookDTO orderBookDTO = new OrderBookDTO();
		orderBookDTO.setQuantity(3L);
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(1L);
		orderBookDTO.setBookDTO(bookDTO);
		dto.setOrderBooks(Arrays.asList(orderBookDTO));

		String inputJson = ApiUtil.mapToJson(dto);
		MvcResult mvcResult = mockMvc.perform(post(API_NAME).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andDo(print()).andExpect(status().isCreated()).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		OrderDTO dtoResponse = ApiUtil.mapFromJson(content, OrderDTO.class);
		assertNotNull(dtoResponse);
		assertNotNull(dtoResponse.getId());

		//Test Case 2: Find by Id
		mvcResult = this.mockMvc.perform(get(API_NAME + ID_PATH_VARIABLE, dtoResponse.getId())).andDo(print()).andExpect(status().isOk()).andReturn();
		content = mvcResult.getResponse().getContentAsString();
		dtoResponse = ApiUtil.mapFromJson(content, OrderDTO.class);
		assertNotNull(dtoResponse);
		
		
		//Test Case 3: Find all
		mvcResult = this.mockMvc.perform(get(API_NAME)).andDo(print()).andExpect(status().isOk()).andReturn();
		content = mvcResult.getResponse().getContentAsString();
		OrderDTO[] dtoArray = ApiUtil.mapFromJson(content, OrderDTO[].class);
		assertNotNull(dtoArray);
		assertTrue(dtoArray.length >= 1);
		
		//Test Case 5: Delete
		mvcResult = mockMvc.perform(delete(API_NAME + ID_PATH_VARIABLE , dtoResponse.getId()).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNoContent()).andReturn();
	}
}
