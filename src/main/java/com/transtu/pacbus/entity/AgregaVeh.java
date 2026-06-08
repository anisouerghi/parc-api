package com.transtu.pacbus.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Access(AccessType.FIELD)
@Table(name = "agrega_veh")
public class AgregaVeh {

   // @Id
	@Id
	@Column(name = "vehnum")
    private String vehnum;
    private String vehimmat;
    private String etacod;
    private String etavehcod;
    private String sitcod;
    private String sousitcod;
    private LocalDate etadatdb;
    private String designe;
    private LocalDate date;
    private String depcod;
    private LocalDate affectdatdb;
    private LocalDate affectdatfi;
    private LocalDate vsttechdatvst;
    private LocalDate vsttechdatprovst;
    private LocalDate vsttechdatvalid;
    private LocalDate lpdatlp;
    private LocalDate lpdatprolp;
    private LocalDate lpdatvalid;
    private String typbtvcod;
    private String typchascod;
    private String motcod;
    private String gabcod;
    private String normecons;
    private LocalDate vehdatcartgris;
    private LocalDate vehdatcircl;
    private Integer kmchas;
    private Integer kmdebann;
    private String disp;
    private String marquevl;
    private String typconvl;
    private String gencod;
    private String projet;
    private LocalDate datemj;

    // Getters seulement (lecture seule)
    public String getVehnum() { return vehnum; }
    public String getVehimmat() { return vehimmat; }
    public String getEtacod() { return etacod; }
    public String getEtavehcod() { return etavehcod; }
    public String getSitcod() { return sitcod; }
    public String getSousitcod() { return sousitcod; }
    public LocalDate getEtadatdb() { return etadatdb; }
    public String getDesigne() { return designe; }
    public LocalDate getDate() { return date; }
    public String getDepcod() { return depcod; }
    public LocalDate getAffectdatdb() { return affectdatdb; }
    public LocalDate getAffectdatfi() { return affectdatfi; }
    public LocalDate getVsttechdatvst() { return vsttechdatvst; }
    public LocalDate getVsttechdatprovst() { return vsttechdatprovst; }
    public LocalDate getVsttechdatvalid() { return vsttechdatvalid; }
    public LocalDate getLpdatlp() { return lpdatlp; }
    public LocalDate getLpdatprolp() { return lpdatprolp; }
    public LocalDate getLpdatvalid() { return lpdatvalid; }
    public String getTypbtvcod() { return typbtvcod; }
    public String getTypchascod() { return typchascod; }
    public String getMotcod() { return motcod; }
    public String getGabcod() { return gabcod; }
    public String getNormecons() { return normecons; }
    public LocalDate getVehdatcartgris() { return vehdatcartgris; }
    public LocalDate getVehdatcircl() { return vehdatcircl; }
    public Integer getKmchas() { return kmchas; }
    public Integer getKmdebann() { return kmdebann; }
    public String getDisp() { return disp; }
    public String getMarquevl() { return marquevl; }
    public String getTypconvl() { return typconvl; }
    public String getGencod() { return gencod; }
    public String getProjet() { return projet; }
    public LocalDate getDatemj() { return datemj; }
}
