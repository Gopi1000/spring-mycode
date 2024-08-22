package com.scube.techboot.controller;

import java.util.ArrayList;
import java.util.List;

import com.scube.techboot.bo.PaginationBO;

public class Pagination {

	@SuppressWarnings("unchecked")
	public static PaginationBO<Object> paginationLimitedRecords(long page,
			List<?> dataLsit, long records, long totalRecordCount) {

		long recordsPerPage = records;
		long noOfRecords = (long) totalRecordCount;
		long pageLinkLimit = 1;
		List<Long> noOfPages = new ArrayList<Long>();

		PaginationBO<Object> ro = new PaginationBO<Object>();

		ro.setListSize(totalRecordCount);
		ro.setCurrentPage(page);
		long tempPages = (long) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		for (long i = (page > 7 ? (page < (tempPages - 8) ? (page - 5)
				: ((page - 9) <= 0 ? 1 : (tempPages - 9))) : 1); i <= tempPages; i++) {
			noOfPages.add(i);
			pageLinkLimit++;
			if (pageLinkLimit > 10) {
				break;
			}
		}
		ro.setNoOfPages(noOfPages);
		ro.setTotalPages(tempPages);
		ro.setLastRecordValue((tempPages));
		List<Object> list = (List<Object>) dataLsit;
		ro.setList(list);

		return ro;
	}

	/**
	 * @param page
	 * @param employerProfileList
	 * @param maxRecord
	 * @param totalRecruitersListCount
	 * @return
	 */
	public static PaginationBO<Object> paginationLimitedRecords(long page, long records,
			long totalRecordCount) {

		long recordsPerPage = records;
		long noOfRecords = (long) totalRecordCount;
		long pageLinkLimit = 1;
		List<Long> noOfPages = new ArrayList<Long>();

		PaginationBO<Object> ro = new PaginationBO<Object>();

		ro.setListSize(totalRecordCount);
		ro.setCurrentPage(page);
		long tempPages = (long) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		for (long i = (page > 7 ? (page < (tempPages - 8) ? (page - 5)
				: ((page - 9) <= 0 ? 1 : (tempPages - 9))) : 1); i <= tempPages; i++) {
			noOfPages.add(i);
			pageLinkLimit++;
			if (pageLinkLimit > 10) {
				break;
			}
		}
		ro.setNoOfPages(noOfPages);
		ro.setTotalPages(tempPages);
		ro.setLastRecordValue((tempPages));
	  //  Map<String, String> list=(Map<String,String>) dataLsit;
		//ro.setMapList(list);

		return ro;
	}
	
	
}
