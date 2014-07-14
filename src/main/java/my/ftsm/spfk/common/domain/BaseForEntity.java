package my.ftsm.spfk.common.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.joda.time.DateTime;

@MappedSuperclass
public abstract class BaseForEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
	
	 public String getCreatedBy() {
	        return null == createdBy ? null : "admin";
	    }

	 public void setCreatedBy(String createdBy) {
	    
	        this.createdBy =null == createdBy ? null : createdBy;
	    }

	public String getLastModifiedBy() {
	        return null == lastModifiedBy ? null : "admin";
	    }

	  
	public void setLastModifiedBy(String lastModifiedBy) {
	        this.lastModifiedBy = null == lastModifiedBy ? null : lastModifiedBy;
	    }

	public Long getVersion() {
	        return version;
	    }

	public void setVersion(Long version) {
	        this.version = version;
	    }

	public void setCreatedDate(Date createdDate) {
	    
	        this.createdDate = null == createdDate ? null : createdDate;
	    }

	public void setLastModifiedDate(Date lastModifiedDate) {
	    
	        this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate;
	    }

	public Date getCreatedDate() {
			
			return null == createdDate ? null : new Date();
		}

	public Date getLastModifiedDate() {
			return null == lastModifiedDate ? null : new Date() ;
		}
	    
	    
}
