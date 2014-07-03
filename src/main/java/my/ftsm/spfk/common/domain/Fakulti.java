package my.ftsm.spfk.common.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import my.ftsm.spfk.common.constent.EntityFileConstent;

@Entity
@Table(name = EntityFileConstent.ET_TITLE + "FAKULTI")
public class Fakulti extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "fakultiSeq")
    @SequenceGenerator(name = "fakultiSeq", sequenceName = "FAKULTI_SEQ")
    @Column(name = "FAKULTI_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name="KOD", nullable = false)
	private String kodFakulti;

	@Getter
	@Setter
	@Column(name="NAMA_FAL", nullable = false)
	private String namaFakulti;

	@Getter
	@Setter
	@Column(name="PERIHAL")
	private String perihal;
	
	@Getter
	@Setter
	@Size(max = 50)
	@Column(name = "ALAMAT", nullable = false)
	private String alamat1;

	@Getter
	@Setter
	@Size(max = 100)
	@Column(name = "ALAMAT_STR2")
	private String alamat2;

	@Getter
	@Setter
	@Size(max = 100)
	@Column(name = "ALAMAT_STR3")
	private String alamat3;

	@Getter
	@Setter
	@Size(max = 5)
	@Column(name = "POSKOD")
	private String poskod;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "BDR_ID")
	private SenaraiAhliKumpulan bandar;

	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "NGR_ID")
	private SenaraiAhliKumpulan negeri;

	@Getter
	@Setter
	@OneToMany(mappedBy = "fakulti", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<JadualSemester> jadualSemesterList = new ArrayList<>();
	
//	@Getter
//	@Setter
//	@OneToMany(mappedBy = "fakulti", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Role> roleList = new ArrayList<>();
	
	
//	@Getter
//	@Setter
//	@OneToMany(mappedBy = "fakulti", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<PusatPengajian> pusatPengajianList = new ArrayList<>();

	
}
