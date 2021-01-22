package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//비활용
//리스너 활용 예시
public class ApplicationListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.debug("INIT");
		ServletContext sc = sce.getServletContext();
		sc.setAttribute("cp", sc.getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
