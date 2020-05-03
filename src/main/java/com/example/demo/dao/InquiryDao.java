package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Inquiry;

public interface InquiryDao {
	
	void insertInquiry(Inquiry inquiry);
	
	List<Inquiry> getAll();
	
	//反応した件数を返す
	int updateInquiry(Inquiry inquiry);

}
