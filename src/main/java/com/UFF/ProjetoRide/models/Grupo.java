
package com.UFF.ProjetoRide.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Matheus
 */
@Entity
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idgrupo;
    @ManyToOne
    private Perfil perfil1;
    @ManyToOne
    private Perfil perfil2;
    @ManyToOne
    private Perfil perfil3;
    @ManyToOne
    private Perfil perfil4;
    @ManyToOne
    private Perfil perfil5;
    private String descricao;
    private int tipogrupo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datacriacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datafinalizacao;
    private int qtdparticipantes;
    private int qtdatual;
    private double preco;
    private String localpartida;
    private String localchegada;
    private double medianotamotorista;
    private double medianotapassageiro;
    
	public int getIdgrupo() {
		return idgrupo;
	}
	public void setIdgrupo(int idgrupo) {
		this.idgrupo = idgrupo;
	}
	public Perfil getPerfil1() {
		return perfil1;
	}
	public void setPerfil1(Perfil perfil1) {
		this.perfil1 = perfil1;
	}
	public Perfil getPerfil2() {
		return perfil2;
	}
	public void setPerfil2(Perfil perfil2) {
		this.perfil2 = perfil2;
	}
	public Perfil getPerfil3() {
		return perfil3;
	}
	public void setPerfil3(Perfil perfil3) {
		this.perfil3 = perfil3;
	}
	public Perfil getPerfil4() {
		return perfil4;
	}
	public void setPerfil4(Perfil perfil4) {
		this.perfil4 = perfil4;
	}
	public Perfil getPerfil5() {
		return perfil5;
	}
	public void setPerfil5(Perfil perfil5) {
		this.perfil5 = perfil5;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getTipogrupo() {
		return tipogrupo;
	}
	public void setTipogrupo(int tipogrupo) {
		this.tipogrupo = tipogrupo;
	}
	public Date getDatacriacao() {
		return datacriacao;
	}
	public void setDatacriacao(String datacriacao) {
		try {
            this.datacriacao = new SimpleDateFormat("yyyy-MM-dd").parse(datacriacao);
        } catch (ParseException e) {
            e.printStackTrace();
        };
	}
	public Date getDatafinalizacao() {
		return datafinalizacao;
	}
	public void setDatafinalizacao(Date datafinalizacao) {
		this.datafinalizacao = datafinalizacao;
	}
	public int getQtdparticipantes() {
		return qtdparticipantes;
	}
	public void setQtdparticipantes(int qtdparticipantes) {
		this.qtdparticipantes = qtdparticipantes;
	}
	public int getQtdatual() {
		return qtdatual;
	}
	public void setQtdatual(int qtdatual) {
		this.qtdatual = qtdatual;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getLocalpartida() {
		return localpartida;
	}
	public void setLocalpartida(String localpartida) {
		this.localpartida = localpartida;
	}
	public String getLocalchegada() {
		return localchegada;
	}
	public void setLocalchegada(String localchegada) {
		this.localchegada = localchegada;
	}
	public double getMedianotamotorista() {
		return medianotamotorista;
	}
	public void setMedianotamotorista(double medianotamotorista) {
		this.medianotamotorista = medianotamotorista;
	}
	public double getMedianotapassageiro() {
		return medianotapassageiro;
	}
	public void setMedianotapassageiro(double medianotapassageiro) {
		this.medianotapassageiro = medianotapassageiro;
	}
	@Override
	public String toString() {
		return "Grupo [idgrupo=" + idgrupo + ", perfil1=" + perfil1 + ", perfil2=" + perfil2 + ", perfil3=" + perfil3
				+ ", perfil4=" + perfil4 + ", perfil5=" + perfil5 + ", descricao=" + descricao + ", tipogrupo="
				+ tipogrupo + ", datacriacao=" + datacriacao + ", datafinalizacao=" + datafinalizacao
				+ ", qtdparticipantes=" + qtdparticipantes + ", qtdatual=" + qtdatual + ", preco=" + preco
				+ ", localpartida=" + localpartida + ", localchegada=" + localchegada + ", medianotamotorista="
				+ medianotamotorista + ", medianotapassageiro=" + medianotapassageiro + "]";
	}
	
	
	
}
