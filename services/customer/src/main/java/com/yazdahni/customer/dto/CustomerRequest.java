package com.yazdahni.customer.dto;

import com.yazdahni.customer.customer.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Name;

public record CustomerRequest(    String id,

                                  @NotNull(message = "Customer fistname is required")
                                  String firstName,

                                  @NotNull(message = "customer lastname is required")
                                  String lastName,

                                  @Email(message = "Email is not valid")
                                  @NotNull(message = "Email is required")
                                  String email,

                                  Address address
) {

}
