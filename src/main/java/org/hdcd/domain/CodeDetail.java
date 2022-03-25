package org.hdcd.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"groupCode", "codeValue"})
@Entity
@IdClass(CodeDetailId.class)
@Table(name="code_detail")
public class CodeDetail {

	@NotBlank
	@Id
	@Column(length = 3)
	private String groupCode;
	
	@NotBlank
	@Id
	@Column(length = 3)
	private String codeValue;
	
	@NotBlank
	@Column(length = 30, nullable = false)
	private String codeName;
	
	private int sortSeq;
	
	@Column(length = 1)
	private String useYn = "Y";
	
	@CreationTimestamp
	private LocalDateTime regDate;
	@UpdateTimestamp
	private LocalDateTime updDate;

}
