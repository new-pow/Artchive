package com.spring_boot_final.project.controller.ocr;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring_boot_final.project.model.TicketVO;
import com.spring_boot_final.project.service.OCRService;

@Controller 
public class OCRViewController {
	
	@Autowired
	OCRService ocrService;

	@RequestMapping("/myPage/OCRForm")
	public String OCRGeneral() {
		return "/myPage/uploadTicket";
	}
	
	@ResponseBody
	@RequestMapping("/myPage/uploadTicket")
	public TicketVO OCRTemplate(@RequestParam("uploadFile") MultipartFile file) throws IOException {
		
		String uploadPath = "/Library/upload/";
		
		// String uploadPath = "/usr/local/project/images/";
		
		String originalFileName = file.getOriginalFilename();
		String filePathName = uploadPath + originalFileName;
		
		File newFile = new File(filePathName);
		
		file.transferTo(newFile);
		
		return ocrService.ocrTemplate(filePathName);
	}
	
}










