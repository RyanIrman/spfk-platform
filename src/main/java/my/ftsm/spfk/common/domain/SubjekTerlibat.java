package my.ftsm.spfk.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import my.ftsm.spfk.common.constent.EntityFileConstent;

@Entity
@Table(name = EntityFileConstent.ET_TITLE + "SUBJEK_TERLIBAT")
public class SubjekTerlibat extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "subjekTerlibatSeq")
    @SequenceGenerator(name = "subjekTerlibatSeq", sequenceName = "STERLIBAT_SEQ")
    @Column(name = "SBTERLIBAT_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "AKTIF")
    @Type(type = "yes_no")
	private Boolean aktif;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "SUBJEK_ID")
	@ForeignKey(name = "FK_TIJU_SUBJEK")
    private Subjek subjek;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "PENGGUNA")
    private Pengguna pengguna;
	
//	@Getter
//	@Setter
//	@ManyToOne
//	@JoinColumn(name = "ASST_CON")
//    private Pengguna asstCondinatorID;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "TUGASAN")
	private SenaraiAhliKumpulan tugasan;
	
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "SESI_ID")
	private KalendarSesiAkedemik sesiAkademik;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "SEM_ID")
	private Semester semester;
	
	
	public SubjekTerlibat(){
		super();
	}
}
