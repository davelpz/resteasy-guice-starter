package com.davelpz.resteasy;

import com.google.inject.AbstractModule;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;

import static org.reflections.scanners.Scanners.TypesAnnotated;

public class ResourceModule extends AbstractModule {
    private final List<String> packages;

    public ResourceModule(List<String> packages) {
        this.packages = packages;
    }

    public void configure() {
        for (String pkg : packages) {
            if (!pkg.isEmpty()) {
                bindResources(pkg);
            }
        }
    }

    private void bindResources(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> resources = reflections.get(TypesAnnotated.with(javax.ws.rs.Path.class).asClass());
        for (Class<?> cls : resources) {
            binder().bind(cls);
        }
    }
}
