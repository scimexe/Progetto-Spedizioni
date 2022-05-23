package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;
 
@Entity 
@Table(name = "magazzini")
public class Magazzino {

  @Id 
  @GeneratedValue 
  public Long magazzinoId;
  @NotNull(message = "l'indirizzo non può essere nullo")
  @NotBlank(message = "l'indirizzo non può essere nullo")
  private String indirizzo;
  @NotNull(message = "il nome non può essere nullo")
  @NotBlank(message = "il nome non può essere nullo")
  private String nome;
  @NotNull(message = "Il numero di colli in giacenza non può essere nullo")
  @Min(value = 0, message = "Almeno 0 colli")
  private int nColli;
  @OneToMany(mappedBy = "magazzinoId")
  private Set<Magazzino> idMagazzino;
  
  public Magazzino(Long magazzinoId, String indirizzo, String nome, int nColli, Set<Magazzino> idMagazzino){
    this.magazzinoId = magazzinoId;
    this.indirizzo = indirizzo;
    this.nome = nome;
    this.nColli = nColli;
    this.idMagazzino = idMagazzino;
  }

  public Magazzino() {
  }

  public Set<Magazzino> getIdMagazzino() {return this.idMagazzino;}
  public void setIdMagazzino(Set<Magazzino> idMagazzino) {this.idMagazzino = idMagazzino;}

  public Long getMagazzinoId() {return this.magazzinoId;}
  public void setMagazzinoId(Long magazzinoId) {this.magazzinoId = magazzinoId;}

  public String getIndirizzo() {return this.indirizzo;}
  public void setIndirizzo(String indirizzo) {this.indirizzo = indirizzo;}

  public String getNome() {return this.nome;}
  public void setNome(String nome) {this.nome = nome;}

  public int getNColli() {return this.nColli;}
  public void setNColli(int nColli) {this.nColli = nColli;}

  @Override
  public String toString() {
    return "{" +
      " id='" + getMagazzinoId() + "'" +
      ", indirizzo='" + getIndirizzo() + "'" +
      ", nome='" + getNome() + "'" +
      ", nColli='" + getNColli() + "'" +
      "}";
  }
}