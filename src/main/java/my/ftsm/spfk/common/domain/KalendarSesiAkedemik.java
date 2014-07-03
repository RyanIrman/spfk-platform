package my.ftsm.spfk.common.domain;


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import my.ftsm.spfk.common.constent.EntityFileConstent;

@Entity
@Table(name = EntityFileConstent.ET_TITLE + "KALENDAR_AKADEMIK")
public class KalendarSesiAkedemik extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "kalendarAkademikSeq")
    @SequenceGenerator(name = "kalendarAkademikSeq", sequenceName = "KALENDAR_SEQ")
    @Column(name = "KALENDAR_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "AKTIF")
    @Type(type = "yes_no")
	private Boolean aktif;
	
	@Getter
	@Setter
	@Column(name = "SESI_AKADEMIK", nullable = false)
    private String sesiAkademik;
	
	@Getter
	@Setter
	@Column(name = "TARIKH_MULA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Date tarikhMulaSesi;
	
	@Getter
	@Setter
	@Column(name = "TARIKH_TAMAT", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Date tarikhTamat;
	
	//TODO: Create Getter and Setter 
	@Column(name = "JUMLAH_HARI")
    private Integer totalDay;
	
	
//	@Getter
//	@Setter
//	@ManyToOne
//	@JoinColumn(name = "FAKULTI_ID")
//	private Fakulti fakulti;
//	
	@Getter
	@Setter
	@OneToMany(mappedBy = "kalendarSesiAkedemik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JadualSemester> jadualSemesterList = new ArrayList<>();
    
//    @Getter
//	@Setter
//	@OneToMany(mappedBy = "kalendarSesiAkedemik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<RekodUrusan> rekodUrusanList = new ArrayList<>();

	
	public KalendarSesiAkedemik() {
		// TODO Auto-generated constructor stub
	}


	public Integer getTotalDay() {
		return totalDay;
	}


	public void setTotalDay(Integer totalDay) {
		if(tarikhMulaSesi != null && tarikhTamat != null){
			
			LocalDate mula = new LocalDate(tarikhMulaSesi);
			LocalDate akhir = new LocalDate(tarikhTamat);
			totalDay = Days.daysBetween(mula, akhir).getDays();
			
		}
		
		this.totalDay = totalDay;
	}
	
}
