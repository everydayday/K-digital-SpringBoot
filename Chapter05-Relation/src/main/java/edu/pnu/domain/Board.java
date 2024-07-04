package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	//private String writer;
	private String content;
	@Builder.Default
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	@Builder.Default
	private Long cnt = 0L;
	
	@ManyToOne	// Board가 자기(many) to One(member)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
}
