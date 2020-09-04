/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.mda.infra.service.impl.common;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProperties.Entry;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.module.GModule;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;

/**
 * {@link IModuleAPIConfiguration} implementation.
 */
@objid ("fb48176c-f1b6-11e1-af52-001ec947c8cc")
public class PeerModuleConfiguration implements IModuleAPIConfiguration {
    @objid ("fb4a79c3-f1b6-11e1-af52-001ec947c8cc")
    private GModule module;

    @objid ("c8527d19-03ec-11e2-8e1f-001ec947c8cc")
    private Path projectSpacePath;

    @objid ("c8527d1b-03ec-11e2-8e1f-001ec947c8cc")
    private Path deploymentPath;

    @objid ("c8527d1c-03ec-11e2-8e1f-001ec947c8cc")
    private List<Path> docpath;

    @objid ("fb4a79c4-f1b6-11e1-af52-001ec947c8cc")
    public PeerModuleConfiguration(GModule module, Path projectSpacePath, Path deploymentPath, final List<Path> docpath) {
        this.module = module;
        this.projectSpacePath = projectSpacePath;
        this.deploymentPath = deploymentPath;
        this.docpath = docpath;
    }

    @objid ("fb4cdc1d-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public String getParameterValue(String key) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && configParam.isIsApiRead()) {
                    return this.module.getParameters().getValue(key, "");
                }
            }
        }
        return null;
    }

    @objid ("fb4cdc23-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public Map<String, String> getParameters() {
        Map<String, String> results = new HashMap<>();
        
        GProperties parameters = this.module.getParameters();
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.isIsApiRead()) {
                    results.put(configParam.getName(), parameters.getValue(configParam.getName(), ""));
                }
            }
        }
        return results;
    }

    @objid ("fb4f3e7d-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public boolean setParameterValue(String key, String value) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && configParam.isIsApiWrite()) {
                    GProperties parameters = this.module.getParameters();
                    parameters.setProperty(key, value, DefinitionScope.LOCAL);
                    return parameters.getProperty(key).getScope() == DefinitionScope.LOCAL;
                }
            }
        }
        return false;
    }

    @objid ("fb51a0d5-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public void updateFrom(Map<String, String> parameters) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                String parameterName = configParam.getName();
                if (parameters.containsKey(parameterName) && configParam.isIsApiWrite()) {
                    this.module.getParameters().setProperty(parameterName, parameters.get(parameterName), DefinitionScope.LOCAL);
                }
            }
        }
    }

    @objid ("fb5b2a39-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public Path getModuleResourcesPath() {
        return this.deploymentPath;
    }

    @objid ("fb5d8c94-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public List<Path> getDocpath() {
        return this.docpath;
    }

    @objid ("b4f6e2bb-c6fd-4c28-a9bb-c2dae8be6a3c")
    @Override
    public boolean isLocked(String key) {
        ModuleComponent moduleElement = this.module.getModuleElement();
        if (moduleElement != null) {
            EList<ModuleParameter> configParams = moduleElement.getModuleParameter();
            for (ModuleParameter configParam : configParams) {
                if (configParam.getName().equals(key) && configParam.isIsApiRead()) {
                    Entry property = this.module.getParameters().getProperty(key);
                    if (property != null) {
                        return property.getScope() == DefinitionScope.SHARED;
                    }
                }
            }
        }
        return false;
    }

}
