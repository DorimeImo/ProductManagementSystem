
import config.DataConfig;
import config.SecurityConfig;
import config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationWebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
       rootContext.register(DataConfig.class, SecurityConfig.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));

        rootContext.setServletContext(servletContext);

        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);

        ServletRegistration.Dynamic appServlet
                = servletContext.addServlet("dispatcher",new DispatcherServlet(dispatcherContext));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }
}
