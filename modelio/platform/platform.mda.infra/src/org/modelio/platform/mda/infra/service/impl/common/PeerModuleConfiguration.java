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
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.api.module.context.configuration.IModuleConfigurationListener;
import org.modelio.metamodel.mda.ModuleParameter;

/**
 * This class represents the means of reading and writing module parameters through the module's API (peer services), aka in code outside of the module.
 * <p>
 * Each module owns parameters that can be read and written. The current class is used to give the opportunity to change or get the specific module parameters.
 * <p>
 * The parameters can be defined using a specific string key. The returned value is always typed by a String, meaning that needed conversion should be managed by the developer.
 */
@objid ("fb48176c-f1b6-11e1-af52-001ec947c8cc")
public final class PeerModuleConfiguration implements IModuleAPIConfiguration {
    @objid ("c8527d1c-03ec-11e2-8e1f-001ec947c8cc")
    private List<Path> docpath;

    /**
     * The instance to delegate access to the module parameters.
     */
    @objid ("9803ebfc-76d6-4385-aa63-2bf49abdaaa2")
    private ConfigurationImpl impl;

    /**
     * Unique constructor of an API parameter manager.
     * @param impl the actual instance accessing the module parameters.
     * @param docpath list of all files that should be loaded for documentation.
     */
    @objid ("fb4a79c4-f1b6-11e1-af52-001ec947c8cc")
    public  PeerModuleConfiguration(ConfigurationImpl impl, final List<Path> docpath) {
        this.impl = impl;
        this.docpath = docpath;
        
    }

    @objid ("fb4cdc1d-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public String getParameterValue(String key) {
        return this.impl.getParameterValue(key, ModuleParameter::isIsApiRead);
    }

    @objid ("fb4cdc23-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public Map<String, String> getParameters() {
        return this.impl.getParameters(ModuleParameter::isIsApiRead);
    }

    @objid ("fb4f3e7d-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public boolean setParameterValue(String key, String value) {
        return this.impl.setParameterValue(key, value, ModuleParameter::isIsApiWrite);
    }

    @objid ("fb51a0d5-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public void updateFrom(Map<String, String> parameters) {
        this.impl.updateFrom(parameters, ModuleParameter::isIsApiWrite);
    }

    @objid ("fb5b2a39-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public Path getModuleResourcesPath() {
        return this.impl.getModuleResourcesPath();
    }

    @objid ("fb5d8c94-f1b6-11e1-af52-001ec947c8cc")
    @Override
    public List<Path> getDocpath() {
        return this.docpath;
    }

    @objid ("b4f6e2bb-c6fd-4c28-a9bb-c2dae8be6a3c")
    @Override
    public boolean isLocked(String key) {
        return this.impl.isLocked(key, ModuleParameter::isIsApiRead);
    }

    @objid ("159c5e27-f47a-42d7-a579-44980b0a479b")
    private void fireListeners(String pName, String oldValue, String newValue) {
        this.impl.fireListeners(pName, oldValue, newValue);
    }

    @objid ("fa32bc8b-eef5-4392-af98-fcdf1585409d")
    @Override
    public void addListener(IModuleConfigurationListener l) {
        this.impl.addListener(l);
    }

    @objid ("03f12cef-27bf-41cf-9956-41dc453fffc2")
    @Override
    public void removeListener(IModuleConfigurationListener l) {
        this.impl.removeListener(l);
    }

}
