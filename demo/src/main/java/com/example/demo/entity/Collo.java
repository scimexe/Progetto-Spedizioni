package com.example.demo.entity;

import java.util.Set;

import javax.persistence.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
 
@Entity 
@Table(name = "colli")
public class Collo {

  @Id 
  @GeneratedValue 
  private Long colloId; 
  @NonNull
  private float peso;
  @NonNull
  private float altezza;
  @NonNull
  private float larghezza;
  @NonNull
  private float profondita;
  @NonNull
  private String bolla;
  @Nullable
  @ManyToOne
  @JoinColumn(name = "idMagazzino")
  private Magazzino magazzinoId;
  @OneToMany(mappedBy = "colloId")
  private Set<Collo> idCollo;

  public Collo(Long colloId, float peso, float altezza, float larghezza, float profondita, String bolla, @Nullable Magazzino magazzinoId, Set<Collo> idCollo) {
    this.colloId = colloId;
    this.peso = peso;
    this.altezza = altezza;
    this.larghezza = larghezza;
    this.profondita = profondita;
    this.bolla = bolla;
    this.magazzinoId = magazzinoId;
    this.idCollo = idCollo;
  }

  public Set<Collo> getIdCollo() {return this.idCollo;}
  public void setIdCollo(Set<Collo> idCollo) {this.idCollo = idCollo;}

  public Long getColliId() {return this.colloId;}
  public void setColliId(Long colloId) {this.colloId = colloId;}

  public float getPeso() {return this.peso;}
  public void setPeso(float peso) {this.peso = peso;}

  public float getAltezza() {return this.altezza;}
  public void setAltezza(float altezza) {this.altezza = altezza;}

  public float getLarghezza() {return this.larghezza;}
  public void setLarghezza(float larghezza) {this.larghezza = larghezza;}

  public float getProfondita() {return this.profondita;}
  public void setProfondita(float profondita) {this.profondita = profondita;}

  public String getBolla() {return this.bolla;}
  public void setBolla(String bolla) {this.bolla = bolla;}

  public Magazzino getMagazzinoId() {return this.magazzinoId;}
  public void setMagazzinoId(Magazzino magazzinoId) {this.magazzinoId = magazzinoId;}

  @Override
  public String toString() {
    return "{" +
      " colliId='" + getColliId() + "'" +
      ", peso='" + getPeso() + "'" +
      ", altezza='" + getAltezza() + "'" +
      ", larghezza='" + getLarghezza() + "'" +
      ", profondita='" + getProfondita() + "'" +
      ", bolla='" + getBolla() + "'" +
      ", magazzinoId='" + getMagazzinoId() + "'" +
      "}";
  }
}