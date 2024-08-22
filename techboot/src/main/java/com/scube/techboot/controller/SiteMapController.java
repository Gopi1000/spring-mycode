/**
 * 
 */
package com.scube.techboot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scube.techboot.sitemaps.XmlUrl;
import com.scube.techboot.sitemaps.XmlUrlSet;
import com.scube.techboot.utils.TechbootResourceBundle;


/**
 * @author SCUBE
 *
 */
@Controller
public class SiteMapController {
	private static final Logger log = Logger
			.getLogger(SiteMapController.class);

	
	    @RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody
    public XmlUrlSet main() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
             
         try {
        	String sitemapPath = TechbootResourceBundle.getValue("path.sitemap");
			File file = new File(sitemapPath);
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
			        create(xmlUrlSet, "/"+value, XmlUrl.Priority.HIGH);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			log.info(e);
		}
       
       

        return xmlUrlSet;
    }

    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
        xmlUrlSet.addUrl(new XmlUrl("http://www.techboot.com" + link, priority));
    }

}