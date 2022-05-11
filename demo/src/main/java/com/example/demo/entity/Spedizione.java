package com.example.demo.entity;

import java.sql.Date;
import javax.persistence.*;
import org.springframework.lang.NonNull;
 
@Entity 
@Table(name = "spedizioni", indexes = @Index(columnList = "idCollo, inUscita"))
public class Spedizione {

  @Id 
  @GeneratedValue 
  private Long spedizioneId;
  @NonNull
  private String codiceTracking;
  @NonNull
  private String destinazione;
  @NonNull
  private String provenienza;
  @NonNull
  private boolean spedizioneOrRitiro;
  @NonNull
  private String nomeDestinatario;
  @NonNull
  private String cognomeDestinatario;
  @NonNull
  private Date dataSpedizione;
  @NonNull
  private String stato;
  @NonNull
  private boolean inUscita;
  @NonNull
  @ManyToOne
  @JoinColumn(name = "idVeicolo")
  private Veicolo veicoloId;
  @NonNull
  @ManyToOne
  @JoinColumn(name = "idCollo")
  private Collo colloId;

  public Spedizione(Long spedizioneId, String codiceTracking, String destinazione, String provenienza, boolean spedizioneOrRitiro, String nomeDestinatario, String cognomeDestinatario, Date dataSpedizione, String stato, boolean inUscita, Veicolo veicoloId, Collo colloId) {
    this.spedizioneId = spedizioneId;
    this.codiceTracking = codiceTracking;
    this.destinazione = destinazione;
    this.provenienza = provenienza;
    this.spedizioneOrRitiro = spedizioneOrRitiro;
    this.nomeDestinatario = nomeDestinatario;
    this.cognomeDestinatario = cognomeDestinatario;
    this.dataSpedizione = dataSpedizione;
    this.stato = stato;
    this.inUscita = inUscita;
    this.veicoloId = veicoloId;
    this.colloId = colloId;
  }

  public Long getSpedizioneId() {return this.spedizioneId;}
  public void setSpedizioneId(Long spedizioneId) {this.spedizioneId = spedizioneId;}

  public String getCodiceTracking() {return this.codiceTracking;}
  public void setCodiceTracking(String codiceTracking) {this.codiceTracking = codiceTracking;}

  public String getDestinazione() {return this.destinazione;}
  public void setDestinazione(String destinazione) {this.destinazione = destinazione;}

  public String getProvenienza() {return this.provenienza;}
  public void setProvenienza(String provenienza) {this.provenienza = provenienza;}

  public boolean isSpedizioneOrRitiro() {return this.spedizioneOrRitiro;}
  public void setSpedizioneOrRitiro(boolean spedizioneOrRitiro) {this.spedizioneOrRitiro = spedizioneOrRitiro;}
  
  public String getNomeDestinatario() {return this.nomeDestinatario;}
  public void setNomeDestinatario(String nomeDestinatario) {this.nomeDestinatario = nomeDestinatario;}
  
  public String getCognomeDestinatario() {return this.cognomeDestinatario;}
  public void setCognomeDestinatario(String cognomeDestinatario) {this.cognomeDestinatario = cognomeDestinatario;}
  
  public Date getDataSpedizione() {return this.dataSpedizione;}
  public void setDataSpedizione(Date dataSpedizione) {this.dataSpedizione = dataSpedizione;}
  
  public String getStato() {return this.stato;}
  public void setStato(String stato) {this.stato = stato;}

  public boolean isInUscita() {return this.inUscita;}
  public void setInUscita(boolean inUscita) {this.inUscita = inUscita;}

  public Veicolo getVeicoloId() {return this.veicoloId;}
  public void setVeicoloId(Veicolo veicoloId) {this.veicoloId = veicoloId;}

  public Collo getColloId() {return this.colloId;}
  public void setColloId(Collo colloId) {this.colloId = colloId;}

  @Override
  public String toString() {
    return "{" +
      " spedizioneId='" + getSpedizioneId() + "'" +
      ", codiceTracking='" + getCodiceTracking() + "'" +
      ", destinazione='" + getDestinazione() + "'" +
      ", provenienza='" + getProvenienza() + "'" +
      ", spedizioneOrRitiro='" + isSpedizioneOrRitiro() + "'" +
      ", nomeDestinatario='" + getNomeDestinatario() + "'" +
      ", cognomeDestinatario='" + getCognomeDestinatario() + "'" +
      ", dataSpedizione='" + getDataSpedizione() + "'" +
      ", stato='" + getStato() + "'" +
      ", inUscita='" + isInUscita() + "'" +
      ", veicoloId='" + getVeicoloId() + "'" +
      ", colloId='" + getColloId() + "'" +
      "}";
  }
} 