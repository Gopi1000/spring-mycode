package com.scube.techboot.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ReadImage {
	
	private static final  Logger LOGGER=Logger.getLogger(ReadImage.class);
	
	@RequestMapping(value = "/read_images", method = RequestMethod.GET)
	public void readImageFromlocalFolder(Model model,HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws FileNotFoundException{
		try{
			String imageName=request.getParameter("imgName");
			String imagePath=null;
			String img=null;
			if(null!=request.getParameter("imgName") && null!=request.getParameter("image")){
				String image=request.getParameter("image");
				if(null!=image && image.equalsIgnoreCase("testimonialLogo")){
					img	=TechbootResourceBundle.getValue("testimonial.Logo");
				}
				else if(null!=image && image.equalsIgnoreCase("companyLogo")){
					img	=TechbootResourceBundle.getValue("company.Logo");
				}
				else if(null!=image && image.equalsIgnoreCase("eventLogo")) {
					img	=TechbootResourceBundle.getValue("event.Logo");
				}
				else if(null!=image && image.equalsIgnoreCase("newsLogo")){
					img	=TechbootResourceBundle.getValue("RecentNews.Logo");
				}
				else if(null!=image && image.equalsIgnoreCase("courseLogo")){
					img	=TechbootResourceBundle.getValue("course.Logo");
				}
				
				imagePath=img;
			}

			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");

			try{
				File f=new File(imagePath,imageName);
				if(f.exists()){
					BufferedImage bi = ImageIO.read(f);
					OutputStream out = response.getOutputStream();
					ImageIO.write(bi, imageName.split("\\.")[1], out);
					out.close();
				}
			}
			catch (Exception ex) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Read image from local folder has failed:"
							+ ex.getMessage());
				}
				LOGGER.info("Read image from local folder has failed:"
						+ ex.getMessage());
			}
		}
		catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Read image from local folder has failed:"
						+ ex.getMessage());
			}
			LOGGER.info("Read image from local folder has failed:"
					+ ex.getMessage());
		}

	}
}
