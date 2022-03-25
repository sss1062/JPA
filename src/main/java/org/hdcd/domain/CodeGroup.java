package org.hdcd.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@ToString(exclude = "codeDetails")
@Entity
@EqualsAndHashCode(of="groupCode")
@Table(name="code_group")
public class CodeGroup {

	@NotBlank
	@Id
	@Column(length = 3)
	private String groupCode;
	
	@NotBlank
	@Column(length = 30, nullable = false)
	private String groupName;
	
	@Column(length = 1)
	private String useYn = "Y";
	
	@CreationTimestamp
	private LocalDateTime regDate;
	@UpdateTimestamp
	private LocalDateTime updDate;

	@OneToMany
	@JoinColumn(name="groupCode")
	private List<CodeDetail> codeDetails;
	
}
