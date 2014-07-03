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
@Table(name = EntityFileConstent.ET_TITLE + "DEPARTMENT")
public class PusatPengajian extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "pusatPengajianSeq")
    @SequenceGenerator(name = "pusatPengajianSeq", sequenceName = "PUSAT_SEQ")
    @Column(name = "PP_ID")
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
	
//	@Getter
//	@Setter
//	@ManyToOne
//    @JoinColumn(name = "FAKULTI_ID")
//    @ForeignKey(name = "FK_TIJU_FAKULTI")
//	private Fakulti fakulti;
	
	@Getter
	@Setter
    @OneToMany(mappedBy = "pusatPengajian", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Jabatan> jabatanList = new ArrayList<>();
	
    @Getter
	@Setter
    @OneToMany(mappedBy = "pusatPengajian", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MessageBox> messageList = new ArrayList<>();

}
