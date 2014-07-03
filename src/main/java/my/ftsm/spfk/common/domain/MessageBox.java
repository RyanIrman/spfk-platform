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
@Table(name = EntityFileConstent.ET_TITLE + "MESSAGE_DASHBOARD")
public class MessageBox extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(generator = "inboxSeq")
    @SequenceGenerator(name = "inboxSeq", sequenceName = "INBOX_SEQ")
    @Column(name = "INBOX_ID")
	private Long id;
	
	@Getter
	@Setter
	@Column(name = "AKTIF")
    @Type(type = "yes_no")
	private Boolean aktif;
	
	@Getter
	@Setter
	@Column(name = "TARIKH_TERIMA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
    private Date tarikhTerima;
	
	@Getter
	@Setter
	@Column(name = "STATUS")
	private String status;
	
	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "RECEIVER_ID")
    @ForeignKey(name = "FK_TIJU_PENGGUNA")
	private Pengguna penerimaId;
	
	@Getter
	@Setter
	@ManyToOne
    @JoinColumn(name = "SENDER_ID")
    @ForeignKey(name = "FK_TIJU_PP")
	private PusatPengajian pusatPengajian;
	
	
	public MessageBox(){
		
	}
}
