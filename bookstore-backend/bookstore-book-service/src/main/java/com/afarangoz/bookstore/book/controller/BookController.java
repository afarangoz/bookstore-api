package com.afarangoz.bookstore.book.controller;

import static com.afarangoz.bookstore.api.common.util.CommonConstants.BOOKS;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.DELETE_BY_ID;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.DOWNLOAD;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.DOWNLOAD_PICTURE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.FIND_ALL;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.FIND_BY_ID;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.ID_PATH_VARIABLE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.NEW;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.SLASH_SYMBOL;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.UPDATE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.UPLOAD;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.UPLOAD_PICTURE;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;

import com.afarangoz.bookstore.api.common.controller.base.IBaseController;
import com.afarangoz.bookstore.api.common.model.dto.BookDTO;
import com.afarangoz.bookstore.api.common.model.entity.Book;
import com.afarangoz.bookstore.api.common.model.service.file.IFileHandlerService;
import com.afarangoz.bookstore.api.common.util.ApiUtil;
import com.afarangoz.bookstore.book.model.service.IBookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = BOOKS)
public class BookController implements IBaseController<BookDTO, Long>{
	
	private static final String API_NAME = SLASH_SYMBOL + BOOKS;

	@Autowired
	private IBookService bookService;
	
	@Autowired
	private IFileHandlerService fileHandlerService;
	
	@GetMapping(API_NAME)
	@ApiOperation(value = FIND_ALL)
	@Override
	public ResponseEntity<?> findAll(@RequestParam(name = "page-number", defaultValue = "0") int number,  @RequestParam(name = "size", defaultValue = "10") int size) {
		Page<BookDTO> responsePage = bookService.findAll(PageRequest.of(number, size)).map(BookDTO::new);
		if(responsePage.isEmpty()) {
			return ApiUtil.getNotFoundResponseEntity();
		} 
		return new ResponseEntity<>(responsePage, HttpStatus.OK);
	}

	@GetMapping(API_NAME + ID_PATH_VARIABLE)
	@ApiOperation(value = FIND_BY_ID)
	@Override
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Book entity = bookService.findById(id).orElse(null);
		if(entity == null) {
			return ApiUtil.getNotFoundResponseEntity();
		} 
		return  new ResponseEntity<>(new BookDTO(entity), HttpStatus.OK);
	}

	@DeleteMapping(API_NAME + ID_PATH_VARIABLE)
	@ApiOperation(value = DELETE_BY_ID)
	@Override
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Book entity = bookService.findById(id).orElse(null);
		if(entity == null) {
			return ApiUtil.getNotFoundResponseEntity();
		} 
		bookService.delete(entity);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(API_NAME)
	@ApiOperation(value = NEW)
	@Override
	public ResponseEntity<?> create(@Valid @RequestBody BookDTO entityDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return ApiUtil.getResponseEntity(bindingResult);
		}
		Book entity = new Book(entityDTO);
		entity = bookService.createOrUpdate(entity);
		return new ResponseEntity<>(new BookDTO(entity), HttpStatus.CREATED);
	}

	@PutMapping(API_NAME + ID_PATH_VARIABLE)
	@ApiOperation(value = UPDATE)
	@Override
	public ResponseEntity<?> update(@Valid @RequestBody BookDTO entityDTO, BindingResult bindingResult, @PathVariable Long id) {
		
		if(bindingResult.hasErrors()) {
			return ApiUtil.getResponseEntity(bindingResult);
		}
		
		Book entity = bookService.findById(id).orElse(null);
		if(entity == null) {
			return ApiUtil.getNotFoundResponseEntity();
		} 
		
		Book updatedEntity = new Book(entityDTO);
		updatedEntity.setId(id);
		entity = bookService.createOrUpdate(updatedEntity); 
		
		return  new ResponseEntity<>(new BookDTO(entity), HttpStatus.OK);
	}
	
	
	@PostMapping(UPLOAD)
	@ApiOperation(value = UPLOAD_PICTURE)
	public ResponseEntity<?> upload(@RequestParam(required = true) MultipartFile file, @RequestParam(required = true) Long id) throws IOException{
		Book entity = bookService.findById(id).orElse(null);
		if(entity == null) {
			return ApiUtil.getNotFoundResponseEntity();
		} 
		String pictureName = fileHandlerService.save(file);
		entity.setPicture(pictureName);
		entity = bookService.createOrUpdate(entity);
		return new ResponseEntity<>(new BookDTO(entity), HttpStatus.OK);	
	}

	
	@GetMapping(DOWNLOAD)
	@ApiOperation(value = DOWNLOAD_PICTURE)
	public ResponseEntity<?> download(@PathVariable Long id) {
		Book entity = bookService.findById(id).orElse(null);
		if(entity == null) {
			return ApiUtil.getNotFoundResponseEntity();
		} 
		
		if(entity.getPicture() == null || entity.getPicture().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		Resource picture = fileHandlerService.load(entity.getPicture());
		 return ResponseEntity.ok()
			        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + picture.getFilename() + "\"").body(picture);
			  
	}
}
