package my.ftsm.spfk.common.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import my.ftsm.spfk.common.constent.EntityFileConstent;

@Entity
@Table(name = EntityFileConstent.ET_TITLE + "PENGGUNA")
public class Pengguna extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "penggunaSeq")
    @SequenceGenerator(name = "penggunaSeq", sequenceName = "PENGGUNA_SEQ")
    @Column(name = "PENGGUNA_ID")
	private Long id;
	
	@Column(name = "AKTIF", nullable = false)
    @Type(type = "yes_no")
   	private Boolean aktif;

	
	@Getter
	@Setter
	@Column(name="NO_STAFF")
	private String noStaff;

	@Getter
	@Setter
	@Column(name="NAMA_PTM")
	private String namaPertama;

	@Getter
	@Setter
	@Column(name="NAMA_LAST")
	private String namaAkhir;
	
	@Getter
	@Setter
	@Column(name="NO_KP")
	private String noKadPengenalan;
	
	@Getter
	@Setter
	@Column(name="JANTINA")
	private SenaraiAhliKumpulan jantina;
	
	@Getter
	@Setter
	@Column(name="ROLE")
	private SenaraiAhliKumpulan role;
	
	
	@Getter
	@Setter
	@Column(name="EMAIL")
	private String email;
	
	@Getter
	@Setter
	@Column(name="KATA_LALUAN")
	private String kataLaluan;
	
	@Getter
	@Setter
	@Column(name="JAWATAN")
	private SenaraiAhliKumpulan jawatan;
	
	@Getter
	@Setter
	@Column(name="TITLE")
	private SenaraiAhliKumpulan gelaran;
	
	@Getter
	@Setter
	@Column(name="BLOK_BGN")
	private SenaraiAhliKumpulan blok;
	
//	@Getter
//	@Setter
//	@JoinColumn(name = "INDOX_ID")
//    @ForeignKey(name = "FK_TIJU_REKOD_MESSAGE")
//	private RekodMessage inbox;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name= "PUSAT_PENGAJIAN")
	private PusatPengajian pusatPengajian;
	
	@Getter
	@Setter
    @OneToMany(mappedBy = "pengguna", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubjekTerlibat> subjekTerlibatList = new ArrayList<>();
    
 
	
	public Pengguna(){
		
	}
}
