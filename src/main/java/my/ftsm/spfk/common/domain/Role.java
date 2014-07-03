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

@Entity
@Table(name = "ROLE")
public class Role extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "roleSeq")
    @SequenceGenerator(name = "roleSeq", sequenceName = "ROLE_SEQ")
    @Column(name = "ROLE_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "AKTIF")
    @Type(type = "yes_no")
	private Boolean aktif;
		
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "PUSAT_PGJIAN", nullable = false)
	private SenaraiAhliKumpulan pusatPengajian;

	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "PENGGUNA_ID")
    @ForeignKey(name = "FK_TIJU_PENGGUNA")
	private Pengguna pengguna;
	
//	@Getter
//	@Setter
//	@ManyToOne
//    @JoinColumn(name = "PENGGUNA_ID")
//    @ForeignKey(name = "FK_TIJU_PENGGUNA")
//	private Pengguna pengguna;
	
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Pengguna> penggunaList = new ArrayList<>();
	
	
	public Role(){
		
	}
}
