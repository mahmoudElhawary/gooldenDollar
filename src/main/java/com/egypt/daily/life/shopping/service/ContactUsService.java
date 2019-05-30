package com.egypt.daily.life.shopping.service;

import java.util.List;

import com.egypt.daily.life.shopping.model.ContactUs;

public interface ContactUsService {

	List<ContactUs> findAll();

	ContactUs save(ContactUs contactUs);

}
