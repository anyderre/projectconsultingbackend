package com.WPM.ProjectConsultingGruppe1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bestellung")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;

    private String productQuantity;

    private String productName;

    private float productPrice;

    private String productCurrency;

    private String productCategory;

    private String productBrand;

    private String productSubCategory;

    private Long userId;

    private String username;

    private String userFirstName;

    private String userLastName;

    private String visitorId;

    private String sessionId;

    private float orderTotal;

    private Date createdDate;

}
