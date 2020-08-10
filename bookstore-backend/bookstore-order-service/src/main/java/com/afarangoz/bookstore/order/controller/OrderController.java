package com.afarangoz.bookstore.order.controller;

import static com.afarangoz.bookstore.api.common.util.CommonConstants.DELETE_BY_ID;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.FIND_ALL;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.FIND_BY_ID;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.ID_PATH_VARIABLE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.NEW;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.ORDERS;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.SLASH_SYMBOL;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.UPDATE;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.afarangoz.bookstore.api.common.controller.base.IBaseController;
import com.afarangoz.bookstore.api.common.model.dto.BookDTO;
import com.afarangoz.bookstore.api.common.model.dto.OrderBookDTO;
import com.afarangoz.bookstore.api.common.model.dto.OrderDTO;
import com.afarangoz.bookstore.api.common.model.entity.Order;
import com.afarangoz.bookstore.api.common.util.ApiUtil;
import com.afarangoz.bookstore.order.client.feing.IBookFeingClient;
import com.afarangoz.bookstore.order.model.service.IOrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = ORDERS)
public class OrderController implements IBaseController<OrderDTO, Long>{
	
	private static final String API_NAME = SLASH_SYMBOL + ORDERS;

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IBookFeingClient bookFeingClient;
	
	@GetMapping(API_NAME)
	@ApiOperation(value = FIND_ALL)
	@Override
	public ResponseEntity<?> findAll(@RequestParam(name = "page-number", defaultValue = "0") int number,  @RequestParam(name = "size", defaultValue = "10") int size) {
		Page<OrderDTO> responsePage = orderService.findAll(PageRequest.of(number, size)).map(OrderDTO::new);
		if(responsePage.isEmpty()) {
			return ApiUtil.getNotFoundResponseEntity();
		} 
		return new ResponseEntity<>(responsePage, HttpStatus.OK);
	}

	@GetMapping(API_NAME + ID_PATH_VARIABLE)
	@ApiOperation(value = FIND_BY_ID)
	@Override
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Order entity = orderService.findById(id).orElse(null);
		if(entity == null) {
			return ApiUtil.getNotFoundResponseEntity();
		} 
		return  new ResponseEntity<>(new OrderDTO(entity), HttpStatus.OK);
	}

	@DeleteMapping(API_NAME + ID_PATH_VARIABLE)
	@ApiOperation(value = DELETE_BY_ID)
	@Override
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Order entity = orderService.findById(id).orElse(null);
		if(entity == null) {
			return ApiUtil.getNotFoundResponseEntity();
		} 
		orderService.delete(entity);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(API_NAME)
	@ApiOperation(value = NEW)
	@Override
	public ResponseEntity<?> create(@Valid @RequestBody OrderDTO entityDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return ApiUtil.getResponseEntity(bindingResult);
		}

		for(OrderBookDTO orderBookDTO : entityDTO.getOrderBooks()) {
			Long id = orderBookDTO.getBookDTO().getId();
			ResponseEntity<?> responseEntity = bookFeingClient.findById(id);
			if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
				ObjectMapper mapper = new ObjectMapper();
				BookDTO bookDTO = mapper.convertValue(responseEntity.getBody(), new TypeReference<BookDTO>() {});
				if(bookDTO.getAvailable() - orderBookDTO.getQuantity() < 0) {
					return ApiUtil.getBadRequestResponseEntity("Validate order quantity and book available: BookId="+bookDTO.getId());
				}
				bookDTO.setAvailable(bookDTO.getAvailable() - orderBookDTO.getQuantity());
				orderBookDTO.setBookDTO(bookDTO);
				responseEntity = bookFeingClient.update(bookDTO, id);
				if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
					return responseEntity;
				}
			} else {
				return responseEntity;
			}
		}
		Order entity = new Order(entityDTO);
		entity.setDate(new Date());
		entity = orderService.createOrUpdate(entity);
		return new ResponseEntity<>(new OrderDTO(entity), HttpStatus.CREATED);
	}

	@PutMapping(API_NAME + ID_PATH_VARIABLE)
	@ApiOperation(value = UPDATE)
	@Override
	public ResponseEntity<?> update(@Valid @RequestBody OrderDTO entityDTO, BindingResult bindingResult, @PathVariable Long id) {
		
		if(bindingResult.hasErrors()) {
			return ApiUtil.getResponseEntity(bindingResult);
		}
		
		Order entity = orderService.findById(id).orElse(null);
		if(entity == null) {
			return ApiUtil.getNotFoundResponseEntity();
		} 
		//Restar cantidad
		Order updatedEntity = new Order(entityDTO);
		updatedEntity.setId(id);
		entity = orderService.createOrUpdate(updatedEntity); 
		
		return  new ResponseEntity<>(new OrderDTO(entity), HttpStatus.OK);
	}

}
