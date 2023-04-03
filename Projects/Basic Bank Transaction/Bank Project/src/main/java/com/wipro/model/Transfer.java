package com.wipro.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transfer")
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private LocalDateTime dateTime;
	private long sourceAccountNum;
	private long targetAccountNum;
	private long transferId;
	private String status;

	public Transfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transfer(int id, LocalDateTime dateTime, long sourceAccountNum, long targetAccountNum, long transferId,
			String status) {
		super();
		this.dateTime = dateTime;
		this.sourceAccountNum = sourceAccountNum;
		this.targetAccountNum = targetAccountNum;
		this.transferId = transferId;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public long getSourceAccountNum() {
		return sourceAccountNum;
	}

	public void setSourceAccountNum(long sourceAccountNum) {
		this.sourceAccountNum = sourceAccountNum;
	}

	public long getTargetAccountNum() {
		return targetAccountNum;
	}

	public void setTargetAccountNum(long targetAccountNum) {
		this.targetAccountNum = targetAccountNum;
	}

	public long getTransferId() {
		return transferId;
	}

	public void setTransferId(long transferId) {
		this.transferId = transferId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id + ", dateTime=" + dateTime + ", sourceAccountNum=" + sourceAccountNum
				+ ", targetAccountNum=" + targetAccountNum + ", transferId=" + transferId + ", status=" + status + "]";
	}

}
