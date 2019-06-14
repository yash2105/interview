package com.json.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.json.constants.ViewConstants;
import com.json.model.UploadFile;
import com.json.service.JsonToTopologyService;

@Controller
public class JsonToTopologyController {
	
	@Autowired
	JsonToTopologyService jsonToTopologyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonToTopologyController.class);

	@RequestMapping(value = "/doLandingPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String doLandingPage(@ModelAttribute UploadFile uploadFile, BindingResult result, Model model, 
			HttpServletRequest req, HttpServletResponse response) {
		return ViewConstants.UPLOAD_JSON;	
	}
	
	@RequestMapping(value = "/doUploadJson.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String doUploadJson(@RequestParam("uploadFile") MultipartFile file, HttpServletRequest req, HttpServletResponse response) {
		
		String fileName = file.getName() + System.currentTimeMillis();
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "TopologyVisualizer");
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName + ".txt");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				LOGGER.info("Server File Location=" + serverFile.getAbsolutePath());
				return "jsonStatus";
			} catch (Exception e) {
				return "You failed to upload " + fileName + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + fileName + " because the file was empty.";
		}
	}
	
	@RequestMapping(value = "/doJsonStatus.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String doJsonStatus(@ModelAttribute UploadFile uploadFile, BindingResult result, Model model, 
			HttpServletRequest req, HttpServletResponse response) {
		
		return ViewConstants.JSON_STATUS;
	}
}