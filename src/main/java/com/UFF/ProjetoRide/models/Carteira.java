
package com.UFF.ProjetoRide.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Matheus
 */
@Entity
public class Carteira implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idcarteira;
    @OneToOne
    private Usuario usuario;
    private String formapagamento;
    private double saldoconta;
    
	public int getIdcarteira() {
		return idcarteira;
	}
	public void setIdcarteira(int idcarteira) {
		this.idcarteira = idcarteira;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getFormapagamento() {
		return formapagamento;
	}
	public void setFormapagamento(String formapagamento) {
		this.formapagamento = formapagamento;
	}
	public double getSaldoconta() {
		return saldoconta;
	}
	public void setSaldoconta(double saldoconta) {
		this.saldoconta = saldoconta;
	}
	@Override
	public String toString() {
		return "Carteira [idcarteira=" + idcarteira + ", usuario=" + usuario + ", formapagamento=" + formapagamento
				+ ", saldoconta=" + saldoconta + "]";
	}
    
    

}
