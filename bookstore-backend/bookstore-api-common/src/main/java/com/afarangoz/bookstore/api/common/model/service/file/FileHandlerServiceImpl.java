package com.afarangoz.bookstore.api.common.model.service.file;

import static com.afarangoz.bookstore.api.common.util.CommonConstants.COLON_SYMBOL;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.COULD_NOT_READ_FILE;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.FILES_DIRECTORY_PATH;
import static com.afarangoz.bookstore.api.common.util.CommonConstants.HYPHEN_SYMBOL;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.afarangoz.bookstore.api.common.exception.handler.LoadFileException;


/**
 * The Class FileHandlerServiceImpl.
 */
@Service
public class FileHandlerServiceImpl implements IFileHandlerService{

	/** The path. */
	private final Path path = Paths.get(FILES_DIRECTORY_PATH);
	
	/**
	 * Save.
	 *
	 * @param file the file
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public String save(MultipartFile file) throws IOException {
		validateDirectoryPath();
		String name = generateName(file.getOriginalFilename());
	    Files.copy(file.getInputStream(), path.resolve(name));
	    return name;
	}
	
	/**
	 * Removes the.
	 *
	 * @param name the name
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public boolean remove(String name) throws IOException {
		Path filePath = Paths.get(FILES_DIRECTORY_PATH, name);
		return Files.deleteIfExists(filePath);
	}
	
	
	/**
	 * Load.
	 *
	 * @param name the name
	 * @return the resource
	 */
	@Override
	public Resource load(String name){
		try {
			Path file = path.resolve(name);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new LoadFileException(COULD_NOT_READ_FILE);
			}
		} catch (Exception e) {
			throw new LoadFileException(e.getMessage(), e);
		}
	}
	
	

	/**
	 * Validate directory path.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void validateDirectoryPath() throws IOException {
		File file = new File(FILES_DIRECTORY_PATH);
		if(!file.isDirectory()) {
			Files.createDirectories(path);
		}
	}
	
	/**
	 * Generate name.
	 *
	 * @param name the name
	 * @return the string
	 */
	public String generateName(String name) {
		LocalDateTime localDateTime = LocalDateTime.now();
		StringBuilder newName = new StringBuilder(localDateTime.toString());
		newName.append(HYPHEN_SYMBOL);
		newName.append(name);
		return newName.toString().replace(COLON_SYMBOL, HYPHEN_SYMBOL);
	}

}
