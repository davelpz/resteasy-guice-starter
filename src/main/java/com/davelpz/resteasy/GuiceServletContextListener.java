package com.davelpz.resteasy;

import com.google.inject.Injector;
import com.google.inject.Module;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.reflections.Reflections;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.reflections.scanners.Scanners.TypesAnnotated;

@WebListener
public class GuiceServletContextListener extends GuiceResteasyBootstrapServletContextListener {
    @Override
    protected List<? extends Module> getModules(ServletContext context) {
        List<Module> modules = new ArrayList<>();
        List<String> packages_to_scan = new ArrayList<>();
        Reflections reflections = new Reflections();
        Set<Class<?>> module_classes = reflections.get(TypesAnnotated.get(ApplicationModule.class).asClass());
        for (Class<?> cls : module_classes) {
            ApplicationModule applicationModule = cls.getAnnotation(ApplicationModule.class);
            packages_to_scan.add(applicationModule.value());
            try {
                if (Module.class.isAssignableFrom(cls)) {
                    Module module = (Module) cls.getDeclaredConstructor().newInstance();
                    modules.add(module);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ResourceModule resourceModule = new ResourceModule(packages_to_scan);
        modules.add(0, resourceModule);
        return modules;
    }

    @Override
    public void withInjector(Injector injector) {
    }
}
