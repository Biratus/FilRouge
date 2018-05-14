package fr.dta.formafond.controller;

import java.io.File;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class FileUploadController {
	
	public FileUploadController() {
	}

	@RequestMapping(method=RequestMethod.POST)
	public String handleFileUpload(@RequestPart("file") MultipartFile file,@RequestParam("filename") String filename) {
		String filePath="";
		if (!file.isEmpty()) {
            try {
                String realPathtoUploads = "C:/Users/cbirette/Documents/Node/FilRouge/formafond/src/assets/";// request.getServletContext().getRealPath(uploadsDir);
                if(! new File(realPathtoUploads).exists())
                {
                    new File(realPathtoUploads).mkdir();
                }
                
                filePath = realPathtoUploads + filename+"."+file.getContentType().split("/")[1];
                File dest = new File(filePath);
                file.transferTo(dest);
            } catch(Exception e) {
            	e.printStackTrace();
            }
		}

		return filename+"."+file.getContentType().split("/")[1];
	}

}