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
  public double amount;

  @Column(name = "nameOrig")
  public String nameOrig;

  // The false capitalization is to match the schema

  @Column(name = "oldbalanceOrig")
  public double oldbalanceOrig;

  @Column(name = "newbalanceOrig")
  public double newbalanceOrig;

  @Column(name = "nameDest")
  public String nameDest;

  @Column(name = "oldbalanceDest")
  public double oldbalanceDest;

  @Column(name = "newbalanceDest")
  public double newbalanceDest;

  @Column(name = "isFraud")
  public int isFraud;

  @Column(name = "isFlaggedFraud")
  public int isFlaggedFraud;

  public Transaction() {

  }

  public Transaction(
    int step, String type, double amount,
    String nameOrig, double oldbalanceOrig, double newbalanceOrig,
    String nameDest, double oldbalanceDest, double newbalanceDest,
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
