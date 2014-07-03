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

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;
import my.ftsm.spfk.common.constent.EntityFileConstent;

@Entity
@Table(name = EntityFileConstent.ET_TITLE + "SENARAI_AHLI_KUMPULAN")
public class SenaraiAhliKumpulan extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "senaraiAhliKumpulanSeq")
    @SequenceGenerator(name = "senaraiAhliKumpulanSeq", sequenceName = "SAK_SEQ")
    @Column(name = "SAK_ID")
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
	@Column(name = "NAMA_ENG")
    private String namaEng;
	
	@Getter
	@Setter
	@Column(name = "PERIHAL")
    private String perihal;
	
	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "PARENT_ID")
    @ForeignKey(name = "FK_TRSKP_PARENT")
    private SenaraiAhliKumpulan parent;
	
	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "SENARAI_KUMPULAN_ID")
    @ForeignKey(name = "FK_TRSAK_SENR_KUMPLN")
    private SenaraiKumpulan senaraiKumpulan;

	
	@Getter
	@Setter
	@Column(name = "NILAISTR")
    private String nilaiStr;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    private List<SenaraiAhliKumpulan> childList = new ArrayList<SenaraiAhliKumpulan>();
	
	public SenaraiAhliKumpulan(){
	}
}
