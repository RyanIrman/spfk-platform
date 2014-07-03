package my.ftsm.spfk.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import my.ftsm.spfk.common.constent.EntityFileConstent;

@Entity
@Table(name = EntityFileConstent.ET_TITLE + "SEMESTER")
public class Semester extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "semesterSeq")
    @SequenceGenerator(name = "semesterSeq", sequenceName = "SEM_SEQ")
    @Column(name = "SEM_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "KOD")
	private String kod;
	
	@Getter
	@Setter
	@Column(name = "NAMA")
	private String nameSemester;
	
	
	
}
