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
@Table(name = EntityFileConstent.ET_TITLE + "HEAD_DEPARTMENT")
public class Jabatan extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "jabatanSeq")
    @SequenceGenerator(name = "jabatanSeq", sequenceName = "JABATAN_SEQ")
    @Column(name = "JABATAN_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "AKTIF")
    @Type(type = "yes_no")
	private Boolean aktif;
	
	@Getter
	@Setter
	@Column(name = "KOD")
    private String kodJabatan;
	
	@Getter
	@Setter
	@Column(name = "NAMA")
    private String namaJabatan;
	
	@Getter
	@Setter
	@Column(name = "PERIHAL")
    private String perihal;
	
	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "PUSAT_ID")
    @ForeignKey(name = "FK_TIJU_PUSAT_PGAJIAN")
	private PusatPengajian pusatPengajian;
	
//	@Getter
//	@Setter
//	@ManyToOne
//	@JoinColumn(name = "PUSAT_PGAJIAN")
//	private SenaraiAhliKumpulan pusatPengajian;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "BLOK")
	private SenaraiAhliKumpulan blok;
	
	@Getter
	@Setter
    @OneToMany(mappedBy = "jabatan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Subjek> subjekList = new ArrayList<>();

}
