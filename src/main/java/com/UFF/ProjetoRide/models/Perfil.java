
package com.UFF.ProjetoRide.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Matheus
 */
@Entity
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idperfil;
    @OneToOne
    private Usuario usuario;
    private int tipoperfil;
    private int qtdgruposMcriados;
    private String modelocarro;
    private double notamotorista;
    private String placacarro;
    private double notapassageiro;
    private int qtdgruposPcriados;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Grupo> grupos;
    
    
	public int getIdperfil() {
		return idperfil;
	}
	public void setIdperfil(int idperfil) {
		this.idperfil = idperfil;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getTipoperfil() {
		return tipoperfil;
	}
	public void setTipoperfil(int tipoperfil) {
		this.tipoperfil = tipoperfil;
	}
	public int getQtdgruposMcriados() {
		return qtdgruposMcriados;
	}
	public void setQtdgruposMcriados(int qtdgruposMcriados) {
		this.qtdgruposMcriados = qtdgruposMcriados;
	}
	public String getModelocarro() {
		return modelocarro;
	}
	public void setModelocarro(String modelocarro) {
		this.modelocarro = modelocarro;
	}
	public double getNotamotorista() {
		return notamotorista;
	}
	public void setNotamotorista(double notamotorista) {
		this.notamotorista = notamotorista;
	}
	public String getPlacacarro() {
		return placacarro;
	}
	public void setPlacacarro(String placacarro) {
		this.placacarro = placacarro;
	}
	public double getNotapassageiro() {
		return notapassageiro;
	}
	public void setNotapassageiro(double notapassageiro) {
		this.notapassageiro = notapassageiro;
	}
	public int getQtdgruposPcriados() {
		return qtdgruposPcriados;
	}
	public void setQtdgruposPcriados(int qtdgruposPcriados) {
		this.qtdgruposPcriados = qtdgruposPcriados;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idperfil;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (idperfil != other.idperfil)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Perfil [idperfil=" + idperfil + ", usuario=" + usuario + ", tipoperfil=" + tipoperfil
				+ ", qtdgruposMcriados=" + qtdgruposMcriados + ", modelocarro=" + modelocarro + ", notamotorista="
				+ notamotorista + ", placacarro=" + placacarro + ", notapassageiro=" + notapassageiro
				+ ", qtdgruposPcriados=" + qtdgruposPcriados + "]";
	}
	
}
