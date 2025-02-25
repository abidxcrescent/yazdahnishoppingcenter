package com.yazdahni.customer.service;

import com.yazdahni.customer.Repository.CustomerRepository;
import com.yazdahni.customer.customer.Customer;
import com.yazdahni.customer.dto.CustomerRequest;
import com.yazdahni.customer.dto.CustomerResponse;
import com.yazdahni.customer.exception.CustomerNotFoundException;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository
                .findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        mergeCustomer(customer,customerRequest);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstName())){
            customer.setFirstName(customerRequest.firstName());
        }
        if(StringUtils.isNotBlank(customerRequest.lastName())){
            customer.setLastName(customerRequest.lastName());
        }
        if(StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if(customerRequest.address() != null){
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        if(StringUtils.isNotBlank(customerId)){
            return customerRepository.existsById(customerId);
        }
        return false;
    }

    public CustomerResponse findById(String customerId) {

       return customerRepository
               .findById(customerId)
               .map(customerMapper::fromCustomer)
               .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

    }

    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
