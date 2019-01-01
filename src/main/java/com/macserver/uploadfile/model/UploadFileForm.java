package com.macserver.uploadfile.model;

import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private MultipartFile file;

	
	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	
	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

}
