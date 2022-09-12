package com.smartstore.smartstorewebservice.dataAccess.entities;

import javax.persistence.*;

@Entity
@Table(name = "reception_detail")
public class ReceptionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "scheduled_qty")
    private Integer scheduledQty;

    @Column(name = "received_qty")
    private Integer receivedQty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reception_list_id")
    private ReceptionList receptionList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_code")
    private Product productCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScheduledQty() {
        return scheduledQty;
    }

    public void setScheduledQty(Integer scheduledQty) {
        this.scheduledQty = scheduledQty;
    }

    public Integer getReceivedQty() {
        return receivedQty;
    }

    public void setReceivedQty(Integer receivedQty) {
        this.receivedQty = receivedQty;
    }

    public ReceptionList getReceptionList() {
        return receptionList;
    }

    public void setReceptionList(ReceptionList receptionList) {
        this.receptionList = receptionList;
    }

    public Product getProductCode() {
        return productCode;
    }

    public void setProductCode(Product productCode) {
        this.productCode = productCode;
    }

}