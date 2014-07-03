package my.ftsm.spfk.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = EntityFileConstent.ET_TITLE + "JADUAL_SEMESTER")
public class JadualSemester extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "jadualSemesterSeq")
    @SequenceGenerator(name = "jadualSemesterSeq", sequenceName = "JADUAL_SEQ")
    @Column(name = "JADUAL_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "AKTIF")
    @Type(type = "yes_no")
	private Boolean aktif;
	
	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "FAKULTI")
     private Fakulti fakulti;
	
	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "KALENDAR_AKADEMIK")
     private KalendarSesiAkedemik kalendarSesiAkedemik;
	
    @ManyToOne
    @JoinColumn(name = "JNSPERIHAL_ID")
    @ForeignKey(name = "FK_TIJU_JNS_PERIHAL")
    private SenaraiAhliKumpulan jenisPerihal;	
	
	@Getter
	@Setter
	@Column(name = "JUMLAH_MINGGU")
    private Integer minggu;
	
	@Getter
	@Setter
	@OneToOne
	@JoinColumn(name = "SEM_ID", nullable=false)
	private Semester semester;
	
	public JadualSemester(){
	}
	
}
