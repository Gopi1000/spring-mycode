package com.scube.techboot.bo;

import java.io.Serializable;
import java.util.Date;

public class BaseBo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2398118119617999503L;
	private long createdBy;
	private int version;
	private long totalPages;
	private long currentPage;
	private int recordsPerPage;
	private boolean infoStatus;
	private boolean checkStatus;
	private int recordIndex;
	private int maxRecord;
	private String pagination = null;
	private String tempRealPath;
	private Date createdTime;
	private Date modifiedTime;
	private long modifiedBy;
	private int s_No;
	private boolean isDelete;
	private boolean sending_status;

	//private CompanyVO companyVO;
/*	private CompanyBO companyBO;
*/	
	


	/**
	 * @return the infoStatus
	 */
	public boolean isInfoStatus() {
		return infoStatus;
	}

	/**
	 * @param infoStatus
	 *            the infoStatus to set
	 */
	public void setInfoStatus(boolean infoStatus) {
		this.infoStatus = infoStatus;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return this.version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the modifiedBy
	 */
	public long getModifiedBy() {
		return this.modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the createdBy
	 */
	public long getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the tempRealPath
	 */
	public String getTempRealPath() {
		return tempRealPath;
	}

	/**
	 * @param tempRealPath
	 *            the tempRealPath to set
	 */
	public void setTempRealPath(String tempRealPath) {
		this.tempRealPath = tempRealPath;
	}

	/**
	 * @return the recordIndex
	 */
	public int getRecordIndex() {
		return recordIndex;
	}

	/**
	 * @param recordIndex
	 *            the recordIndex to set
	 */
	public void setRecordIndex(int recordIndex) {
		this.recordIndex = recordIndex;
	}

	/**
	 * @return the maxRecord
	 */
	public int getMaxRecord() {
		return maxRecord;
	}

	/**
	 * @param maxRecord
	 *            the maxRecord to set
	 */
	public void setMaxRecord(int maxRecord) {
		this.maxRecord = maxRecord;
	}

	/**
	 * @return the pagination
	 */
	public String getPagination() {
		return pagination;
	}

	/**
	 * @param pagination
	 *            the pagination to set
	 */
	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the totalPages
	 */
	public long getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the currentPage
	 */
	public long getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the recordsPerPage
	 */
	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	/**
	 * @param recordsPerPage
	 *            the recordsPerPage to set
	 */
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public boolean isCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public int getS_No() {
		return s_No;
	}

	public void setS_No(int s_No) {
		this.s_No = s_No;
	}

	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean getSending_status() {
		return sending_status;
	}

	public void setSending_status(boolean sending_status) {
		this.sending_status = sending_status;
	}

/*	public CompanyBO getCompanyBO() {
		return companyBO;
	}

	public void setCompanyBO(CompanyBO companyBO) {
		this.companyBO = companyBO;
	}
*/
	



}
