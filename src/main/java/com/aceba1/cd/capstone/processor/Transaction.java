package com.aceba1.cd.capstone.processor;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  public long getId() {
    return id;
  }

  @Column(name = "step")
  public int step;

  @Column(name = "type")
  public String type;

  @Column(name = "amount")
  public long amount;

  @Column(name = "nameOrig")
  public String nameOrig;

  // The false capitalization is to match the schema

  @Column(name = "oldbalanceOrig")
  public long oldbalanceOrig;

  @Column(name = "newbalanceOrig")
  public long newbalanceOrig;

  @Column(name = "nameDest")
  public String nameDest;

  @Column(name = "oldbalanceDest")
  public long oldbalanceDest;

  @Column(name = "newbalanceDest")
  public long newbalanceDest;

  @Column(name = "isFraud")
  public int isFraud;

  @Column(name = "isFlaggedFraud")
  public int isFlaggedFraud;

  public Transaction() {

  }

  public Transaction(
    int step, String type, long amount,
    String nameOrig, long oldbalanceOrig, long newbalanceOrig,
    String nameDest, long oldbalanceDest, long newbalanceDest,
    int isFraud, int isFlaggedFraud) {
    this.step = step;
    this.type = type;
    this.amount = amount;
    this.nameOrig = nameOrig;
    this.oldbalanceOrig = oldbalanceOrig;
    this.newbalanceOrig = newbalanceOrig;
    this.nameDest = nameDest;
    this.oldbalanceDest = oldbalanceDest;
    this.newbalanceDest = newbalanceDest;
    this.isFraud = isFraud;
    this.isFlaggedFraud = isFlaggedFraud;
  }

  @Override
  public String toString() {
    return "Transaction{" +
      "id=" + id +
      ", step=" + step +
      ", type='" + type + '\'' +
      ", amount=" + amount +
      ", nameOrig='" + nameOrig + '\'' +
      ", oldBalanceOrig=" + oldbalanceOrig +
      ", newBalanceOrig=" + newbalanceOrig +
      ", nameDest='" + nameDest + '\'' +
      ", oldBalanceDest=" + oldbalanceDest +
      ", newBalanceDest=" + newbalanceDest +
      ", isFraud=" + isFraud +
      ", isFlaggedFraud=" + isFlaggedFraud +
      '}';
  }
}
