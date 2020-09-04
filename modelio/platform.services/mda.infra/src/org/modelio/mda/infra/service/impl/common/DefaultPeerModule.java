/* 
 * Copyright 2013-2019 Modeliosoft
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IPeerModule;
import org.modelio.api.module.context.configuration.IModuleAPIConfiguration;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.vbasic.version.Version;

/**
 * Default implementation of {@link IPeerModule}.
 */
@objid ("b31582fd-f11c-11e1-af52-001ec947c8cc")
public class DefaultPeerModule implements IPeerModule {
    @objid ("b31582fe-f11c-11e1-af52-001ec947c8cc")
    private ModuleComponent moduleComponent;

    @objid ("f8bf97c9-f1b6-11e1-af52-001ec947c8cc")
    private IModuleAPIConfiguration configuration;

    /**
     * Instantiate the Default peer module.
     * 
     * @param moduleComponent the module model element
     * @param configuration the access to module parameter and resource paths.
     */
    @objid ("b31582ff-f11c-11e1-af52-001ec947c8cc")
    public DefaultPeerModule(ModuleComponent moduleComponent, IModuleAPIConfiguration configuration) {
        super();
        this.moduleComponent = moduleComponent;
        this.configuration = configuration;
    }

    @objid ("b3158303-f11c-11e1-af52-001ec947c8cc")
    @Override
    public IModuleAPIConfiguration getConfiguration() {
        return this.configuration;
    }

    @objid ("b3158309-f11c-11e1-af52-001ec947c8cc")
    @Override
    public String getDescription() {
        return "";
    }

    @objid ("b317e515-f11c-11e1-af52-001ec947c8cc")
    @Override
    public String getName() {
        return this.moduleComponent.getName();
    }

    @objid ("b317e51b-f11c-11e1-af52-001ec947c8cc")
    @Override
    public Version getVersion() {
        return new Version(this.moduleComponent.getMajVersion() + "." + this.moduleComponent.getMinVersion() + "." + this.moduleComponent.getMinMinVersion());
    }

}
