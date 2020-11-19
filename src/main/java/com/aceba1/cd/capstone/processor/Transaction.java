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

  @Column(name = "oldBalanceOrig")
  public long oldBalanceOrig;

  @Column(name = "newBalanceOrig")
  public long newBalanceOrig;

  @Column(name = "nameDest")
  public String nameDest;

  @Column(name = "oldBalanceDest")
  public long  oldBalanceDest;

  @Column(name = "newBalanceDest")
  public long newBalanceDest;

  @Column(name = "isFraud")
  public int isFraud;

  @Column(name = "isFlaggedFraud")
  public int isFlaggedFraud;

  @Override
  public String toString() {
    return "Transaction{" +
      "id=" + id +
      ", step=" + step +
      ", type='" + type + '\'' +
      ", amount=" + amount +
      ", nameOrig='" + nameOrig + '\'' +
      ", oldBalanceOrig=" + oldBalanceOrig +
      ", newBalanceOrig=" + newBalanceOrig +
      ", nameDest='" + nameDest + '\'' +
      ", oldBalanceDest=" + oldBalanceDest +
      ", newBalanceDest=" + newBalanceDest +
      ", isFraud=" + isFraud +
      ", isFlaggedFraud=" + isFlaggedFraud +
      '}';
  }
}
