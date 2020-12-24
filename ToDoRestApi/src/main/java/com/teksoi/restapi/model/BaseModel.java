package com.teksoi.restapi.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mozahid on Nov 9, 2017
 */
@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	private Integer updatedBy;

	private Boolean active;

	@PrePersist
	public void prePersist() {
		this.createdAt = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = new Date();
	}

}
