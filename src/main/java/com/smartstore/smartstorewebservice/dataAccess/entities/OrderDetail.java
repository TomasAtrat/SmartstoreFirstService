package com.smartstore.smartstorewebservice.dataAccess.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ordered_quantity")
    private Integer orderedQuantity;

    @Column(name = "prepared_quantity")
    private Integer preparedQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode")
    private Barcode barcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public Integer getPreparedQuantity() {
        return preparedQuantity;
    }

    public void setPreparedQuantity(Integer preparedQuantity) {
        this.preparedQuantity = preparedQuantity;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
    }

}