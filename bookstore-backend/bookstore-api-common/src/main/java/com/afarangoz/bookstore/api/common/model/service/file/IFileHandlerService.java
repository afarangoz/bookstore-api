package com.afarangoz.bookstore.api.common.model.service.file;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * The Interface IFileHandlerService.
 */
public interface IFileHandlerService {
	
	/**
	 * Save.
	 *
	 * @param file the file
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String save(MultipartFile file) throws IOException;
	
	/**
	 * Removes the.
	 *
	 * @param name the name
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean remove(String name) throws IOException;
	
	/**
	 * Load.
	 *
	 * @param name the name
	 * @return the resource
	 */
	public Resource load(String name);
	
}
