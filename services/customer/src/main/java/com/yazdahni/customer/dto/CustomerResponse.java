package com.yazdahni.customer.dto;

import com.yazdahni.customer.customer.Address;

public record CustomerResponse(String id,
                               String firstName,
                               String lastName,
                               String email,
                               Address address) {
}
