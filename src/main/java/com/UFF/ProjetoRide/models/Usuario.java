
package com.UFF.ProjetoRide.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements Serializable, UserDetails {
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idusuario;
    private String nome;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datanascimento;
    private String telefone;
    private String endereco;
    private String curso;
    private String fotoperfil;
    private String email;
    private String senha;
    
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDatanascimento() {
		return datanascimento;
	}
	public void setDatanascimento(String datanascimento) {
		try {
            this.datanascimento = new SimpleDateFormat("yyyy-MM-dd").parse(datanascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getFotoperfil() {
		return fotoperfil;
	}
	public void setFotoperfil(String fotoperfil) {
		this.fotoperfil = fotoperfil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", nome=" + nome + ", datanascimento=" + datanascimento
				+ ", telefone=" + telefone + ", endereco=" + endereco + ", curso=" + curso + ", fotoperfil="
				+ fotoperfil + ", email=" + email + ", senha=" + senha + "]";
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new HashSet<GrantedAuthority>();
	}
	@Override
	public String getPassword() {
		return this.senha;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
    
        


}

