package com.egypt.daily.life.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.egypt.daily.life.shopping.model.ContactUs;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Long>{

}
