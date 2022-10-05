package com.smartstore.smartstorewebservice.dataAccess.entities;

import javax.persistence.*;

@Entity
@Table(name = "epc_barcode")
public class EpcBarcode {
    @Id
    @Column(name = "epc", nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "barcode", nullable = false)
    private Barcode barcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
    }

}