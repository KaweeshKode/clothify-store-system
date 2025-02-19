package com.clothifystore.crm.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Supplier {
    private Integer supplierId;
    private String supplierName;
    private String email;
    private String company;
    private String contactInfo;
}
