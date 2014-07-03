package my.ftsm.spfk.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RUNNING_NUMBER")
public class RunningNumber extends BaseForEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HB_SEQ_RUN")
	@SequenceGenerator(name = "HB_SEQ_RUN", sequenceName = "SEQ_RUN")
	protected Long id;
	
	@Getter
	@Setter
	@Column(name="CODE")
	private String code;
	
	@Getter
	@Setter
	@Column(name="TYPE")
	private String type;
	
	@Getter
	@Setter
	@Column(name="RUNNING_NUMBER")
	private Long runningNumber;


}
