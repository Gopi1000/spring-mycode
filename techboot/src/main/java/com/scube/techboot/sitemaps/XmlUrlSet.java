package com.scube.techboot.sitemaps;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "urlset", namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
public class XmlUrlSet{
	@XmlElement(name = "url",type = XmlUrl.class)
	private List<XmlUrl> sitemap = new ArrayList<XmlUrl>();

	public void addUrl(XmlUrl xmlUrl) {

		sitemap.add(xmlUrl);
	}

	public List<XmlUrl> getXmlUrls() {
		return sitemap;
	}
}