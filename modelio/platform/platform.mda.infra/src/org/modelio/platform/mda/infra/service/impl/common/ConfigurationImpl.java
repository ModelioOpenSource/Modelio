/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.platform.mda.infra.service.impl.common;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.api.module.context.configuration.IModuleConfigurationListener;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.project.GProperties.Entry;
import org.modelio.gproject.module.GModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;

/**
 * This class handles reading and writing module parameters and listener management.
 */
@objid ("b27240fd-7a19-4f98-911c-cc0e3e2c0772")
public final class ConfigurationImpl {
    @objid ("2e9b01b4-de57-4250-b681-bb6df562964a")
    private Path resourcesPath;

    @objid ("3990c224-f5f2-4ea5-b1df-f7b0ee131b71")
    private GModule module;

    @objid ("057f3298-d829-45c6-a780-d5adda170450")
    private List<IModuleConfigurationListener> listeners = new ArrayList<>();

    /**
     * C'tor.
     * @param module the module this configuration is build for.
     * @param resourcesPath The module deployment path
     */
    @objid ("691a65e8-4549-4a6d-826c-ee3e2e23ab64")
    public  ConfigurationImpl(GModule module, Path resourcesPath) {
        this.module = module;
        this.resourcesPath = resourcesPath;
        
    }

    @objid ("dc77637b-a1ea-40b3-b19e-b496354a5cd5")
    String getParameterValue(String key, Predicate<ModuleParameter> visibilityTester) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && visibilityTester.test(configParam)) {
                    return this.module.getParameters().getValue(key, "");
                }
            }
        }
        return null;
    }

    @objid ("cff8e035-077a-4bea-afb8-cdcef067288c")
    Map<String, String> getParameters(Predicate<ModuleParameter> visibilityTester) {
        Map<String, String> results = new HashMap<>();
        
        GProperties gProperties = this.module.getParameters();
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
        
            for (ModuleParameter configParam : configParams) {
                if (visibilityTester.test(configParam)) {
                    results.put(configParam.getName(), gProperties.getValue(configParam.getName(), ""));
                }
            }
        }
        return results;
    }

    @objid ("9f738bd3-dd9a-4434-9764-95c849a508d6")
    boolean setParameterValue(String key, String value, Predicate<ModuleParameter> editabilityTester) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && editabilityTester.test(configParam)) {
                    GProperties gProperties = this.module.getParameters();
                    Entry gProperty = gProperties.getProperty(key);
                    if (gProperty == null || gProperty.getScope() == DefinitionScope.LOCAL) {
                        // Update property value
                        String oldValue = gProperty != null ? gProperty.getValue() : null;
                        gProperties.setProperty(key, value, DefinitionScope.LOCAL);
                        fireListeners(key, oldValue, value);
                        return true;
                    }
                    // This property can't be edited
                    return false;
                }
            }
        }
        return false;
    }

    @objid ("e366281d-26db-4f8e-b406-1ca3fc17519e")
    void updateFrom(Map<String, String> parameters, Predicate<ModuleParameter> editabilityTester) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            GProperties gProperties = this.module.getParameters();
            for (ModuleParameter configParam : moduleElement.getModuleParameter()) {
                String key = configParam.getName();
                if (parameters.containsKey(key) && editabilityTester.test(configParam)) {
                    Entry gProperty = gProperties.getProperty(key);
                    if (gProperty == null || gProperty.getScope() == DefinitionScope.LOCAL) {
                        // Update property value, do not fire update parameters events as this occurs when upgrading a module
                        gProperties.setProperty(key, parameters.get(key), DefinitionScope.LOCAL);
                    }
                }
            }
        }
        
    }

    @objid ("f1e2d212-e988-463e-bbab-b20c3ba03da6")
    Path getModuleResourcesPath() {
        return this.resourcesPath;
    }

    @objid ("60935083-7a79-45da-94d9-c8855255cf6d")
    boolean isLocked(String key, Predicate<ModuleParameter> lockTester) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && lockTester.test(configParam)) {
                    Entry property = this.module.getParameters().getProperty(key);
                    if (property != null) {
                        return property.getScope() == DefinitionScope.SHARED;
                    }
                }
            }
        }
        return false;
    }

    @objid ("6313224a-288f-47de-9eca-a8e3f5c7e04a")
    void fireListeners(String pName, String oldValue, String newValue) {
        this.listeners.forEach(l -> l.parameterChanged(pName, oldValue, newValue));
    }

    @objid ("79a7225b-dfa7-4b2e-bff2-0fe1c7e09c2f")
    void addListener(IModuleConfigurationListener l) {
        this.listeners.add(l);
    }

    @objid ("5b2bf0f5-abbb-45a0-9176-bf07b6598921")
    void removeListener(IModuleConfigurationListener l) {
        this.listeners.remove(l);
    }

}
