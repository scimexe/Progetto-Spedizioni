package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
 
@Entity 
@Table(name = "veicoli") 
public class Veicolo{ 

  @Id 
  @GeneratedValue 
  private Long veicoloId; 
  @NonNull
  private int colliDaConsegnare;
  @NonNull 
  private String marca;
  @NonNull
  private String modello; 
  @OneToMany(mappedBy = "veicoloId")
  private Set<Veicolo> idVeicolo;

  public Veicolo(Long veicoloId, int colliDaConsegnare, String marca, String modello, Set<Veicolo> idVeicolo) {
    this.veicoloId = veicoloId; 
    this.colliDaConsegnare = colliDaConsegnare;
    this.marca = marca; 
    this.modello = modello; 
    this.idVeicolo = idVeicolo;
  } 
  
  public Set<Veicolo> getFkVeicolo() {return this.idVeicolo;}
  public void setFkVeicolo(Set<Veicolo> idVeicolo) {this.idVeicolo = idVeicolo;}

  public Long getVeicoloId() {return this.veicoloId;} 
  public void setVeicoloId(Long veicoloId) {this.veicoloId = veicoloId;} 

  public int getColliDaConsegnare() {return this.colliDaConsegnare;} 
  public void setColliDaConsegnare(int colliDaConsegnare) {this.colliDaConsegnare = colliDaConsegnare;} 
  
  public String getMarca() {return this.marca;} 
  public void setMarca(String marca) {this.marca = marca;} 
  
  public String getModello() {return this.modello;} 
  public void setModello(String modello) {this.modello = modello;} 
  
  @Override public String toString() { 
    return "{" + " id='" + getVeicoloId() + "'" + ", colliDaConsegnare='" + getColliDaConsegnare() + "'" + ", marca='" + getMarca() + "'" + ", modello='" + getModello() + "'" + "}"; } }