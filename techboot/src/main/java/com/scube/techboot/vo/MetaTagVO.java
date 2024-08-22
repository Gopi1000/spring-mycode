package com.scube.techboot.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="metaTag")
public class MetaTagVO {
	
	private long metaTagId;
	
	private String metaName;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="metatag_id")
	public long getMetaTagId() {
		return metaTagId;
	}
	public void setMetaTagId(long metaTagId) {
		this.metaTagId = metaTagId;
	}
	@Column(name="meta_name")
	public String getMetaName() {
		return metaName;
	}
	public void setMetaName(String metaName) {
		this.metaName = metaName;
	}
	

}
