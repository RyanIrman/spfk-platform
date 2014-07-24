package my.ftsm.spfk.common.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationListenerBean implements ApplicationListener<ContextRefreshedEvent>{

	/*
	 * (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 * Description:
	 * Call bean from applicationcontext.xml to load data loader
	 */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        IDataLoaderSistemPengurusanService bean = applicationContext.getBean(IDataLoaderSistemPengurusanService.class);
        bean.initData();
    }
}
