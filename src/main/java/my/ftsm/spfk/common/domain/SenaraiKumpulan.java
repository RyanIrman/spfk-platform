package my.ftsm.spfk.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import my.ftsm.spfk.common.constent.EntityFileConstent;

@Entity
@Table(name = EntityFileConstent.ET_TITLE + "SENARAI_KUMPULAN")
public class SenaraiKumpulan extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "senaraiKumpulanSeq")
    @SequenceGenerator(name = "senaraiKumpulanSeq", sequenceName = "SK_SEQ")
    @Column(name = "SK_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "AKTIF")
    @Type(type = "yes_no")
	private Boolean aktif;
	
	@Getter
	@Setter
	@Column(name = "KOD")
    private String kod;
	
	@Getter
	@Setter
	@Column(name = "NAMA")
    private String nama;
	
	@Getter
	@Setter
	@Column(name = "PERIHAL")
    private String perihal;
	
	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "PARENT_ID")
    @ForeignKey(name = "FK_TRSKP_PARENT")
    private SenaraiKumpulan parent;
	
	@Getter
	@Setter
	@Column(name = "NILAISTR")
    private String nilaiStr;
	
	public SenaraiKumpulan(){
		super();
	}
}
