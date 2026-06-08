package com.transtu.pacbus.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Critères de filtrage multicritère pour l'entité {@code AgregaVeh}.
 *
 * <p>Tous les champs sont optionnels : seuls les critères renseignés sont
 * appliqués (combinaison AND). La structure est volontairement extensible :
 * il suffit d'ajouter un champ ici puis de le prendre en compte dans
 * {@code AgregaVehSpecification} pour enrichir le filtre.</p>
 */
public class AgregaVehFilter {

    /** Filtre IN sur {@code depcod}. */
    private List<String> listDepcod;

    /** Filtre IN sur {@code vehnum}. */
    private List<String> listVehnum;

    /** Filtre IN sur {@code vehimmat}. */
    private List<String> listVehimmat;

    /** Égalité optionnelle sur {@code etacod}. */
    private String etacod;

    /** Égalité optionnelle sur {@code etavehcod}. */
    private String etavehcod;

    /** Borne inférieure (incluse) de l'intervalle sur {@code affectdatdb}. */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate affectdatdbFrom;

    /** Borne supérieure (incluse) de l'intervalle sur {@code affectdatdb}. */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate affectdatdbTo;

    /** Borne inférieure (incluse) de l'intervalle sur {@code vehdatcartgris}. */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate vehdatcartgrisFrom;

    /** Borne supérieure (incluse) de l'intervalle sur {@code vehdatcartgris}. */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate vehdatcartgrisTo;

    public List<String> getListDepcod() {
        return listDepcod;
    }

    public void setListDepcod(List<String> listDepcod) {
        this.listDepcod = listDepcod;
    }

    public List<String> getListVehnum() {
        return listVehnum;
    }

    public void setListVehnum(List<String> listVehnum) {
        this.listVehnum = listVehnum;
    }

    public List<String> getListVehimmat() {
        return listVehimmat;
    }

    public void setListVehimmat(List<String> listVehimmat) {
        this.listVehimmat = listVehimmat;
    }

    public String getEtacod() {
        return etacod;
    }

    public void setEtacod(String etacod) {
        this.etacod = etacod;
    }

    public String getEtavehcod() {
        return etavehcod;
    }

    public void setEtavehcod(String etavehcod) {
        this.etavehcod = etavehcod;
    }

    public LocalDate getAffectdatdbFrom() {
        return affectdatdbFrom;
    }

    public void setAffectdatdbFrom(LocalDate affectdatdbFrom) {
        this.affectdatdbFrom = affectdatdbFrom;
    }

    public LocalDate getAffectdatdbTo() {
        return affectdatdbTo;
    }

    public void setAffectdatdbTo(LocalDate affectdatdbTo) {
        this.affectdatdbTo = affectdatdbTo;
    }

    public LocalDate getVehdatcartgrisFrom() {
        return vehdatcartgrisFrom;
    }

    public void setVehdatcartgrisFrom(LocalDate vehdatcartgrisFrom) {
        this.vehdatcartgrisFrom = vehdatcartgrisFrom;
    }

    public LocalDate getVehdatcartgrisTo() {
        return vehdatcartgrisTo;
    }

    public void setVehdatcartgrisTo(LocalDate vehdatcartgrisTo) {
        this.vehdatcartgrisTo = vehdatcartgrisTo;
    }
}
