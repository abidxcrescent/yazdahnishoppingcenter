package com.yazdahni.customer.repository;

import com.yazdahni.customer.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
