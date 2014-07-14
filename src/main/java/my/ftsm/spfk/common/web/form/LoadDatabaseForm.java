package my.ftsm.spfk.common.web.form;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import my.ftsm.spfk.common.infrastructure.ApplicationContextProvider;
import my.ftsm.spfk.common.manager.ISistemPengurusanManager;

import org.springframework.context.ApplicationContext;

@ManagedBean
@ViewScoped
public class LoadDatabaseForm implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private Boolean disable;
	
	

	@PostConstruct
	public void initForm(){	
		this.disable = false;
	}
	
	public void load(){
		if(!disable){
		ApplicationContext applicationContext = ApplicationContextProvider
				.getApplicationContext();
		ISistemPengurusanManager bean = applicationContext.getBean(ISistemPengurusanManager.class);
		bean.loadData();
		this.disable = true;
		}
		
		
	}
	
}
