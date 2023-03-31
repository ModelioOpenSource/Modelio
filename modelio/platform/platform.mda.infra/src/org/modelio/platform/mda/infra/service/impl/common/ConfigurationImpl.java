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
import org.modelio.gproject.parts.module.GModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;

/**
 * This class handles reading and writing module parameters and listener management.
 */
@objid ("a50d909f-1779-4a48-8517-07a2cd5e469d")
public final class ConfigurationImpl {
    @objid ("8f4b9849-a65a-4a24-9048-5fd491a52a4f")
    private Path resourcesPath;

    @objid ("5fe5e3c5-8290-4333-bd32-861d0257ddf5")
    private GModule module;

    @objid ("8ba09120-11f7-4ab8-adf8-dfcd7050047d")
    private List<IModuleConfigurationListener> listeners = new ArrayList<>();

    /**
     * C'tor.
     * @param module the module this configuration is build for.
     * @param resourcesPath The module deployment path
     */
    @objid ("0a08a22e-ca53-439f-953f-bd183d5bb6b9")
    public  ConfigurationImpl(GModule module, Path resourcesPath) {
        this.module = module;
        this.resourcesPath = resourcesPath;
        
    }

    @objid ("7b768f76-19fa-4492-b44c-9c4494e96b40")
    String getParameterValue(String key, Predicate<ModuleParameter> visibilityTester) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && visibilityTester.test(configParam)) {
                    return this.module.getProperties().getValue(key, "");
                }
            }
        }
        return null;
    }

    @objid ("58fb6dbc-0644-4e55-a17f-59ad3065092c")
    Map<String, String> getParameters(Predicate<ModuleParameter> visibilityTester) {
        Map<String, String> results = new HashMap<>();
        
        GProperties gProperties = this.module.getProperties();
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

    @objid ("23105b02-cab7-4c13-a968-6d257ed609b3")
    boolean setParameterValue(String key, String value, Predicate<ModuleParameter> editabilityTester) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && editabilityTester.test(configParam)) {
                    GProperties gProperties = this.module.getProperties();
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

    @objid ("d9738cea-592b-44ea-8357-9a6dabdd1eb6")
    void updateFrom(Map<String, String> parameters, Predicate<ModuleParameter> editabilityTester) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            GProperties gProperties = this.module.getProperties();
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

    @objid ("472b1d39-e105-4c9e-9963-97990500d935")
    Path getModuleResourcesPath() {
        return this.resourcesPath;
    }

    @objid ("000201c0-578a-43cc-b56e-baef65c2b886")
    boolean isLocked(String key, Predicate<ModuleParameter> lockTester) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && lockTester.test(configParam)) {
                    Entry property = this.module.getProperties().getProperty(key);
                    if (property != null) {
                        return property.getScope() == DefinitionScope.SHARED;
                    }
                }
            }
        }
        return false;
    }

    @objid ("ab8c8360-233f-4d0c-8afb-35be7813e95c")
    void fireListeners(String pName, String oldValue, String newValue) {
        this.listeners.forEach(l -> l.parameterChanged(pName, oldValue, newValue));
    }

    @objid ("993a655b-f5d3-4788-afb0-88d375a6ec1e")
    void addListener(IModuleConfigurationListener l) {
        this.listeners.add(l);
    }

    @objid ("f59c33ac-a2fc-4fb8-912d-e5f0ab33015e")
    void removeListener(IModuleConfigurationListener l) {
        this.listeners.remove(l);
    }

}
