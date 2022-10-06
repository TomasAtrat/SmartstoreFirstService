package com.smartstore.smartstorewebservice.dataAccess.entities;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Mapping for DB view
 */
@Entity
@Immutable
@Table(name = "v_orders_to_prepare")
public class VOrdersToPrepare {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @Column(name = "addrow_date")
    private Date addrowDate;

    @Column(name = "address")
    private String address;

    @Column(name = "accepts_partial_expedition")
    private Boolean acceptsPartialExpedition;

    @Column(name = "description_1")
    private String description1;

    @Column(name = "description_2")
    private String description2;

    @Column(name = "description_3")
    private String description3;

    @Column(name = "description_4")
    private String description4;

    @Column(name = "expedition_id", nullable = false)
    private Long expeditionId;

    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @Column(name = "customer_id", length = 64)
    private String customerId;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "is_completely_prepared")
    private Boolean isCompletelyPrepared;

    public Long getId() {
        return id;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public Date getAddrowDate() {
        return addrowDate;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getAcceptsPartialExpedition() {
        return acceptsPartialExpedition;
    }

    public String getDescription1() {
        return description1;
    }

    public String getDescription2() {
        return description2;
    }

    public String getDescription3() {
        return description3;
    }

    public String getDescription4() {
        return description4;
    }

    public Long getExpeditionId() {
        return expeditionId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Integer getVersion() {
        return version;
    }

    public Boolean getIsCompletelyPrepared() {
        return isCompletelyPrepared;
    }

    protected VOrdersToPrepare() {
    }
}