package com.transtu.pacbus.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "parc_reseau_ferre",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "number")
    }
)
public class ParcReseauFerre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String matricule;

    @Column(nullable = false, length = 50)
    private String number;

    @Column(nullable = false, length = 100)
    private String type;

    @Column(name = "transport_type", length = 50)
    private String transportType;

    @Column(name = "depcod", length = 50)
    private String depcod;

    // ✅ ENUM UTILISÉ ICI
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MaterialStatus status = MaterialStatus.ACTIVE;

    // Constructeur JPA
    public ParcReseauFerre() {
    }

    // Constructeur complet
    public ParcReseauFerre(
            Long id,
            String matricule,
            String number,
            String type,
            String transportType,
            String depcod,
            MaterialStatus status
    ) {
        this.id = id;
        this.matricule = matricule;
        this.number = number;
        this.type = type;
        this.transportType = transportType;
        this.depcod = depcod;
        this.status = status;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getDepcod() {
        return depcod;
    }

    public void setDepcod(String depcod) {
        this.depcod = depcod;
    }

    public MaterialStatus getStatus() {
        return status;
    }

    public void setStatus(MaterialStatus status) {
        this.status = status;
    }
}
