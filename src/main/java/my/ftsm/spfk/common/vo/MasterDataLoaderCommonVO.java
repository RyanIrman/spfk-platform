package my.ftsm.spfk.common.vo;


import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;


public class MasterDataLoaderCommonVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String kod;
	@Getter
	@Setter
	private String perkara;
	
	private Boolean aktif;
	
	@Getter
	@Setter
	private Integer urutan;
	
	
	
	
	public MasterDataLoaderCommonVO(){
		super();
	}


	public Boolean getAktif() {
		return aktif;
	}


	public void setAktif(Boolean aktif) {
		this.aktif = aktif;
	}
	

}
