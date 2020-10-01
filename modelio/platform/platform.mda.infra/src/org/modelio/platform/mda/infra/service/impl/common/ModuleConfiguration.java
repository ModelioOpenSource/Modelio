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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.api.module.context.configuration.IConfigParamValidator;
import org.modelio.api.module.context.configuration.IModuleConfigurationListener;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProperties.Entry;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.module.GModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;

/**
 * This class represents the means of reading and writing module parameters.
 * <p>
 * Each module owns parameters that can be read and written. The current class is used to give the opportunity to change or get the specific module parameters.
 * <p>
 * The parameters can be defined using a specific string key. The returned value is always typed by a String, meaning that needed conversion should be managed by the developer.
 */
@objid ("9709e79f-f374-11e1-9458-001ec947c8cc")
public final class ModuleConfiguration implements IModuleUserConfiguration {
    @objid ("9709e797-f374-11e1-9458-001ec947c8cc")
    private Path deploymentPath;

    @objid ("9709e7a2-f374-11e1-9458-001ec947c8cc")
    private GModule module;

    @objid ("9709e796-f374-11e1-9458-001ec947c8cc")
    private List<Path> docpath;

    @objid ("9709e795-f374-11e1-9458-001ec947c8cc")
    private final Map<String, IConfigParamValidator> validators = new HashMap<>();

    /**
     * Map of all defined diagram named styles provided by the module.
     * <p>
     * The key is the style name and the value the path of the style configuration file relative to the module resources path.
     */
    @objid ("ecdff23f-9c3b-401f-b9c2-a10aa69ec899")
    private Map<String, Path> stylepath;

    @objid ("92d6cda3-e9b7-4e61-87ab-d213704f9035")
    private List<IModuleConfigurationListener> listeners = new ArrayList<>();

    /**
     * Unique constructor of a parameter manager.
     * <p>
     * This constructor needs the id of the module on which the parameter manager is connected.
     * 
     * @param module the module this configuration is build for.
     * @param deploymentPath The module deployment path
     * @param docpath list of all files that should be loaded for documentation.
     * @param map Map of all defined diagram named styles provided by the module.
     */
    @objid ("9709e7a4-f374-11e1-9458-001ec947c8cc")
    public ModuleConfiguration(GModule module, Path deploymentPath, final List<Path> docpath, final Map<String, Path> map) {
        this.module = module;
        this.deploymentPath = deploymentPath;
        this.docpath = docpath;
        this.stylepath = map;
    }

    @objid ("9709e77a-f374-11e1-9458-001ec947c8cc")
    @Override
    public String getParameterValue(String key) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && configParam.isIsUserRead()) {
                    return this.module.getParameters().getValue(key, "");
                }
            }
        }
        return null;
    }

    @objid ("9709e779-f374-11e1-9458-001ec947c8cc")
    @Override
    public Map<String, String> getParameters() {
        Map<String, String> results = new HashMap<>();
        
        GProperties gProperties = this.module.getParameters();
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
        
            for (ModuleParameter configParam : configParams) {
                if (configParam.isIsUserRead()) {
                    results.put(configParam.getName(), gProperties.getValue(configParam.getName(), ""));
                }
            }
        }
        return results;
    }

    @objid ("9709e778-f374-11e1-9458-001ec947c8cc")
    @Override
    public boolean setParameterValue(String key, String value) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && configParam.isIsUserWrite()) {
                    GProperties gProperties = this.module.getParameters();
                    Entry gProperty = gProperties.getProperty(key);
                    if (gProperty == null || gProperty.getScope() == DefinitionScope.LOCAL) {
                        // Update property value
                        String oldValue = (gProperty != null)? gProperty.getValue() : null;
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

    @objid ("9709e777-f374-11e1-9458-001ec947c8cc")
    @Override
    public void updateFrom(Map<String, String> parameters) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            GProperties gProperties = this.module.getParameters();
            for (ModuleParameter configParam : moduleElement.getModuleParameter()) {
                String key = configParam.getName();
                if (parameters.containsKey(key) && configParam.isIsUserWrite()) {
                    Entry gProperty = gProperties.getProperty(key);
                    if (gProperty == null || gProperty.getScope() == DefinitionScope.LOCAL) {
                        // Update property value, do not fire update parameters events as this occurs when upgrading a module
                        gProperties.setProperty(key, parameters.get(key), DefinitionScope.LOCAL);
                    }
                }
            }
        }
    }

    @objid ("9709e774-f374-11e1-9458-001ec947c8cc")
    @Override
    public Path getModuleResourcesPath() {
        return this.deploymentPath;
    }

    @objid ("9709e773-f374-11e1-9458-001ec947c8cc")
    @Override
    public List<Path> getDocpath() {
        List<Path> realpath = new ArrayList<>();
        
        for (Path path : this.docpath) {
            realpath.add(getModuleResourcesPath().resolve(path));
        }
        return realpath;
    }

    @objid ("9709e772-f374-11e1-9458-001ec947c8cc")
    @Override
    public IConfigParamValidator getParameterValidator(String paramName) {
        return this.validators.get(paramName);
    }

    @objid ("9709e771-f374-11e1-9458-001ec947c8cc")
    @Override
    public void setParameterValidator(String paramName, IConfigParamValidator validator) {
        this.validators.put(paramName, validator);
    }

    @objid ("14ee6b59-33e7-46c4-a511-5d476b113518")
    @Override
    public boolean isLocked(String key) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && configParam.isIsUserRead()) {
                    Entry property = this.module.getParameters().getProperty(key);
                    if (property != null) {
                        return property.getScope() == DefinitionScope.SHARED;
                    }
                }
            }
        }
        return false;
    }

    @objid ("b56c0cf6-66de-4a9d-b0a3-d66280d15627")
    @Override
    public Map<String, Path> getStylePath() {
        Map<String, Path> realpath = new HashMap<>();
        
        for (Map.Entry<String, Path> entry : this.stylepath.entrySet()) {
            realpath.put(entry.getKey(), getModuleResourcesPath().resolve(entry.getValue()));
        }
        return realpath;
    }

    @objid ("e1c32814-d956-4b58-b59e-d45052152f26")
    private void fireListeners(String pName, String oldValue, String newValue) {
        this.listeners.forEach(l -> l.parameterChanged(pName, oldValue, newValue));
    }

    @objid ("ea30fc03-6aa8-4c1a-bd26-208ca1fdecc1")
    @Override
    public void addListener(IModuleConfigurationListener l) {
        this.listeners.add(l);
    }

    @objid ("64229828-c227-4f1f-b2fe-93c7c6e3e1c3")
    @Override
    public void removeListener(IModuleConfigurationListener l) {
        this.listeners.remove(l);
    }

}
