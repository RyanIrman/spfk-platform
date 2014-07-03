package my.ftsm.spfk.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseForEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Version
	@Column (name="VERSION", nullable=false)
	private Long version;
	
	@Column (name="CREATE_BY")
	private String createBy;
	
	@Column(name="CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	 @Column(name = "LAST_MODIFIED_BY")
	  private String lastModifiedBy;

	 @Column(name = "LAST_MODIFIED_DATE")
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date lastModifiedDate;
	
	    
	    
	// Gatter and Setter
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	

}
