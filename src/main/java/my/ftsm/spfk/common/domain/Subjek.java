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
@Table(name = EntityFileConstent.ET_TITLE + "SUBJEK")
public class Subjek extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "subjekSeq")
    @SequenceGenerator(name = "subjekSeq", sequenceName = "SUBJEK_SEQ")
    @Column(name = "SUBJEK_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "AKTIF")
    @Type(type = "yes_no")
	private Boolean aktif;
	
	@Getter
	@Setter
	@Column(name = "GLOBAL_SUBJEK")
    @Type(type = "yes_no")
	private Boolean globalSubjek;
	
	@Getter
	@Setter
	@Column(name = "KOD")
    private String kodSubjek;
	
	@Getter
	@Setter
	@Column(name = "NAMA_BM")
    private String namaSubjekBM;
	
	@Getter
	@Setter
	@Column(name = "NAMA_ENG")
    private String namaSubjekEng;
	
	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "KODJABATAN_ID")
  	private Jabatan jabatan;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "JENIS_SUBJEK")
	private SenaraiAhliKumpulan jenisSubjek;
	
//	@Getter
//	@Setter
//	@OneToMany(mappedBy = "subjek", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<SubjekTerlibat> subjekTelibatList = new ArrayList<>();

}
