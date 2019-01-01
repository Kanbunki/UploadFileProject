package com.macserver.uploadfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import com.macserver.uploadfile.model.*;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
	
	@ModelAttribute
	public UploadFileForm getUploadFileForm() {
		return new UploadFileForm();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getUploadFileMenu() {
		return "uploadFileMenu";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String upload(@RequestParam MultipartFile uploadFile) {		
		MultipartFile file = uploadFile;
		
		String name = file.getName();
		String originalName = file.getOriginalFilename();
		String contextType = file.getContentType();
		
		System.out.println("contextType: " + contextType);
		System.out.println("name: " + name);
		System.out.println("originalName: " + originalName);
		
		try {
			InputStreamReader isr = new InputStreamReader(file.getInputStream(), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			File outputFile = new File("C:/Temp/temp.txt");
			if (outputFile.createNewFile()) {
				System.out.println("successed to create file");
			} else {
				System.out.println("failed to create file");
			}
			FileOutputStream fos = new FileOutputStream(outputFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			String line;
			while((line = br.readLine()) != null) {
				bw.write(line);
				bw.newLine();
			}
			
			bw.close();
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/upload?complete";
	}
	
	@RequestMapping(method=RequestMethod.GET, params="complete")
	public String completeUploading() {
		return "completeUploading";
	}
}
