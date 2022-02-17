package cz.tul.scan;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class LifecycleBean2  {

    @PostConstruct
    public void afterPropertiesSet()  {
        System.out.println(this.getClass().getSimpleName() + " - initializing");
    }

    @PreDestroy
    public void destroy()  {
        System.out.println(this.getClass().getSimpleName() + " - destroyed");
    }
}
