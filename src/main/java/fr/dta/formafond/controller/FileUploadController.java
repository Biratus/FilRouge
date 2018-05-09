package fr.dta.formafond.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/api/image")
public class FileUploadController {
	
	@Autowired
    private MultipartHttpServletRequest request;
	
	@Autowired
	private ResourceLoader resourceLoader;
	

	public FileUploadController() {
	}

	@RequestMapping(method=RequestMethod.GET)
	public String serveFile(@RequestParam String imagename) {
        String uploadsDir = "/uploads/";
        String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
        String filePath = uploadsDir +imagename;
        System.out.println("looking for: "+filePath);
		Resource file;
		try {
			file = this.resourceLoader.getResource(filePath);
			File f = new File(filePath);
			System.out.println(f.getAbsolutePath());
			System.out.println(file.getFilename());
		} catch (Exception e) {
			System.out.println("resource");
			e.printStackTrace();
			return null;
		}
		return realPathtoUploads;
	}

	@RequestMapping(method=RequestMethod.POST)
	public String handleFileUpload(@RequestPart("file") MultipartFile file,@RequestParam("filename") String filename) {
		String filePath="";
		if (!file.isEmpty()) {
            try {
                String uploadsDir = "/uploads/";
                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                if(! new File(realPathtoUploads).exists())
                {
                    new File(realPathtoUploads).mkdir();
                }
               
                filePath = realPathtoUploads + filename;
                File dest = new File(filePath);
                file.transferTo(dest);
            } catch(Exception e) {
            	e.printStackTrace();
            }
		}

		return filePath;
	}

}