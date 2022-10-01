package com.smartstore.smartstorewebservice.dataAccess.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "add_date")
    private Instant addDate;

    @Column(name = "qt_reserve")
    private Long qtReserve;

    @Column(name = "qt_stock")
    private Long qtStock;

    @Column(name = "update_date")
    private Instant updateDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "barcode_barcode")
    private Barcode barcodeBarcode;

    @Column(name = "id_branch")
    private Long idBranch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Instant getAddDate() {
        return addDate;
    }

    public void setAddDate(Instant addDate) {
        this.addDate = addDate;
    }

    public Long getQtReserve() {
        return qtReserve;
    }

    public void setQtReserve(Long qtReserve) {
        this.qtReserve = qtReserve;
    }

    public Long getQtStock() {
        return qtStock;
    }

    public void setQtStock(Long qtStock) {
        this.qtStock = qtStock;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Barcode getBarcodeBarcode() {
        return barcodeBarcode;
    }

    public void setBarcodeBarcode(Barcode barcodeBarcode) {
        this.barcodeBarcode = barcodeBarcode;
    }

    public Long getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Long idBranch) {
        this.idBranch = idBranch;
    }

}