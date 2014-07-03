package my.ftsm.spfk.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import my.ftsm.spfk.common.constent.EntityFileConstent;

@Entity
@Table(name = EntityFileConstent.ET_TITLE + "REKOD_URUSAN")
public class RekodUrusan extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "rekodUrusanSeq")
    @SequenceGenerator(name = "rekodUrusanSeq", sequenceName = "REKOD_SEQ")
    @Column(name = "REKOT_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "AKTIF")
    @Type(type = "yes_no")
	private Boolean aktif;
	
	@Getter
	@Setter
	@JoinColumn(name = "SUBJEK_ID")
	@ForeignKey(name = "FK_TIJU_SUBJEK")
	private Subjek subjekId;
	
	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "CONDINATOR_ID")
    @ForeignKey(name = "FK_TIJU_PENGGUNA")
	private Pengguna penggunaId;
	
	@Getter
	@Setter
	@Column(name="CACATAN")
	private String cacatan;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "SESI_AKADEMIK")
	@ForeignKey(name = "FK_TIJU_KALENDAR")
	private KalendarSesiAkedemik kalendarSesiAkedemik;
	

	public RekodUrusan(){
		
	}
}
