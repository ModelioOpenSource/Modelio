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
import org.modelio.vbasic.version.Version;

/**
 * {@link IPeerModule} implementation for {@link BrokenRTModule}.
 */
@objid ("592c3df8-2f2d-11e2-8f16-002564c97630")
class BrokenPeerModule implements IPeerModule {
    @objid ("8d8317ea-2f2d-11e2-8f16-002564c97630")
    private String moduleName;

    @objid ("8d74cfb3-2f2d-11e2-8f16-002564c97630")
    private IModuleAPIConfiguration configuration;

    @objid ("8d773110-2f2d-11e2-8f16-002564c97630")
    private Version moduleVersion;

    @objid ("8d79926d-2f2d-11e2-8f16-002564c97630")
    BrokenPeerModule(String moduleName, Version moduleVersion, IModuleAPIConfiguration configuration) {
        this.moduleName = moduleName;
        this.moduleVersion = moduleVersion;
        this.configuration = configuration;
    }

    @objid ("8d799272-2f2d-11e2-8f16-002564c97630")
    @Override
    public IModuleAPIConfiguration getConfiguration() {
        return this.configuration;
    }

    @objid ("8d799278-2f2d-11e2-8f16-002564c97630")
    @Override
    public String getDescription() {
        return "";
    }

    @objid ("8d79927e-2f2d-11e2-8f16-002564c97630")
    @Override
    public String getName() {
        return this.moduleName;
    }

    @objid ("8d799284-2f2d-11e2-8f16-002564c97630")
    @Override
    public Version getVersion() {
        return this.moduleVersion;
    }

}
