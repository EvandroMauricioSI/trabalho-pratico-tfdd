
package com.UFF.ProjetoRide.models;

import java.io.Serializable;
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
public class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idmensagem;
    private String conteudo;
    @ManyToOne
    private Grupo grupo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datacriacao;
    @ManyToOne
    private Perfil perfil;
    
	public int getIdmensagem() {
		return idmensagem;
	}
	public void setIdmensagem(int idmensagem) {
		this.idmensagem = idmensagem;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Date getDatacriacao() {
		return datacriacao;
	}
	public void setDatacriacao(Date datacriacao) {
		this.datacriacao = datacriacao;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	@Override
	public String toString() {
		return "Mensagem [idmensagem=" + idmensagem + ", conteudo=" + conteudo + ", grupo=" + grupo + ", datacriacao="
				+ datacriacao + "]";
	}
	
    

}
