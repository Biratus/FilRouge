package fr.dta.formafond.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
@PropertySource("classpath:application.properties")
public class FileUploadController {
	
	@Autowired
	private Environment env;
	
	public FileUploadController() {
	}

	@RequestMapping(method=RequestMethod.POST)
	public String handleFileUpload(@RequestPart("file") MultipartFile file,@RequestParam("filename") String filename) {
		String filePath="";
		if (!file.isEmpty()) {
            try {
                String realPathtoUploads = env.getRequiredProperty("uploadUrl")+"/assets/";
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

		return "assets/"+filename+"."+file.getContentType().split("/")[1];
	}

}