package com.aceba1.cd.capstone.processor.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

  public static int indexOf(String column) {
    return switch (column) {
      case "step" -> 0;
      case "type" -> 1;
      case "amount" -> 2;
      case "nameOrig" -> 3;
      case "oldbalanceOrig" -> 4;
      case "newbalanceOrig" -> 5;
      case "nameDest" -> 6;
      case "oldbalanceDest" -> 7;
      case "newbalanceDest" -> 8;
      case "isFraud" -> 9;
      case "isFlaggedFraud" -> 10;
      default -> throw new IllegalArgumentException("Transaction - Column [" + column + "] does not exist!");
    };
  }

  public static int[] indexesOf(String[] columns) {
    return Arrays.stream(columns)
      .mapToInt(Transaction::indexOf)
      .toArray();
  }

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

  public Transaction set(
    int step, String type, double amount,
    String nameOrig, double oldbalanceOrig, double newbalanceOrig,
    String nameDest, double oldbalanceDest, double newbalanceDest,
    int isFraud, int isFlaggedFraud
  ) {
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
    return this;
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
