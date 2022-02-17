package cz.tul.scan;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LifecycleBean1 implements DisposableBean, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - initializing");
    }

    @Override public void destroy() throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - destroyed");
    }
}
