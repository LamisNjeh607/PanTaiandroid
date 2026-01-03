package com.example.pantaijava.com.getcapacitor;


import com.example.pantaijava.com.getcapacitor.annotation.CapacitorPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PluginHandle {
    private final Bridge bridge;
    private Plugin instance;
    private NativePlugin legacyPluginAnnotation;
    private CapacitorPlugin pluginAnnotation;
    private final Class<? extends Plugin> pluginClass;
    private final String pluginId;
    private final Map<String, com.getcapacitor.PluginMethodHandle> pluginMethods;

    private PluginHandle(Class<? extends Plugin> cls, Bridge bridge2) throws InvalidPluginException {
        this.pluginMethods = new HashMap();
        this.bridge = bridge2;
        this.pluginClass = cls;
        CapacitorPlugin capacitorPlugin = (CapacitorPlugin) cls.getAnnotation(CapacitorPlugin.class);
        if (capacitorPlugin == null) {
            NativePlugin nativePlugin = (NativePlugin) cls.getAnnotation(NativePlugin.class);
            if (nativePlugin != null) {
                if (!nativePlugin.name().equals("")) {
                    this.pluginId = nativePlugin.name();
                } else {
                    this.pluginId = cls.getSimpleName();
                }
                this.legacyPluginAnnotation = nativePlugin;
            } else {
                throw new InvalidPluginException("No @CapacitorPlugin annotation found for plugin " + cls.getName());
            }
        } else {
            if (!capacitorPlugin.name().equals("")) {
                this.pluginId = capacitorPlugin.name();
            } else {
                this.pluginId = cls.getSimpleName();
            }
            this.pluginAnnotation = capacitorPlugin;
        }
        indexMethods(cls);
    }

    public PluginHandle(Bridge bridge2, Class<? extends Plugin> cls) throws InvalidPluginException, com.getcapacitor.PluginLoadException {
        this(cls, bridge2);
        load();
    }

    public PluginHandle(Bridge bridge2, Plugin plugin) throws InvalidPluginException {
        this((Class<? extends Plugin>) plugin.getClass(), bridge2);
        loadInstance(plugin);
    }

    public Class<? extends Plugin> getPluginClass() {
        return this.pluginClass;
    }

    public String getId() {
        return this.pluginId;
    }

    public NativePlugin getLegacyPluginAnnotation() {
        return this.legacyPluginAnnotation;
    }

    public CapacitorPlugin getPluginAnnotation() {
        return this.pluginAnnotation;
    }

    public Plugin getInstance() {
        return this.instance;
    }

    public Collection<com.getcapacitor.PluginMethodHandle> getMethods() {
        return this.pluginMethods.values();
    }

    public Plugin load() throws com.getcapacitor.PluginLoadException {
        Plugin plugin = this.instance;
        if (plugin != null) {
            return plugin;
        }
        try {
            Plugin plugin2 = (Plugin) this.pluginClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            this.instance = plugin2;
            return loadInstance(plugin2);
        } catch (Exception unused) {
            throw new com.getcapacitor.PluginLoadException("Unable to load plugin instance. Ensure plugin is publicly accessible");
        }
    }

    public Plugin loadInstance(Plugin plugin) {
        this.instance = plugin;
        plugin.setPluginHandle(this);
        this.instance.setBridge(this.bridge);
        this.instance.load();
        this.instance.initializeActivityLaunchers();
        return this.instance;
    }

    public void invoke(String str, PluginCall pluginCall) throws com.getcapacitor.PluginLoadException, InvalidPluginMethodException, InvocationTargetException, IllegalAccessException {
        if (this.instance == null) {
            load();
        }
        com.getcapacitor.PluginMethodHandle pluginMethodHandle = this.pluginMethods.get(str);
        if (pluginMethodHandle != null) {
            pluginMethodHandle.getMethod().invoke(this.instance, new Object[]{pluginCall});
            return;
        }
        throw new InvalidPluginMethodException("No method " + str + " found for plugin " + this.pluginClass.getName());
    }

    private void indexMethods(Class<? extends Plugin> cls) {
        for (Method method : this.pluginClass.getMethods()) {
            com.getcapacitor.PluginMethod pluginMethod = (com.getcapacitor.PluginMethod) method.getAnnotation( com.getcapacitor.PluginMethod.class);
            if (pluginMethod != null) {
                this.pluginMethods.put(method.getName(), new com.getcapacitor.PluginMethodHandle(method, pluginMethod));
            }
        }
    }
}
