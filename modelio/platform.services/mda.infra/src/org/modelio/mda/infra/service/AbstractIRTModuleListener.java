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

package org.modelio.mda.infra.service;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Default implementation of {@link IRTModuleListener}.
 */
@objid ("16ce6f0f-4059-40ba-89bc-01d18eb27dd4")
public class AbstractIRTModuleListener implements IRTModuleListener {
    @objid ("c0a6cf0e-6689-44c9-a7c9-eb16580afb32")
    @Override
    public void moduleStarted(IRTModule module) {
        // nothing
    }

    @objid ("27574fab-12e0-4b7b-9972-6a71ab1f1278")
    @Override
    public void moduleStopping(IRTModule module) {
        // nothing
    }

    @objid ("e794781d-40d3-456d-b19c-de23c7d4e171")
    @Override
    public void moduleStopped(IRTModule module) {
        // nothing
    }

    @objid ("a892eb06-f4fd-4cd1-9e3b-6d377fd2ad84")
    @Override
    public void moduleUnloading(IRTModule module) {
        // nothing
    }

    @objid ("0f8f408b-7ec2-4cf4-bf38-67394e124065")
    @Override
    public void moduleUnloaded(IRTModule module) {
        // nothing
    }

    @objid ("e827ca5d-246a-4d3c-bb91-57ac06466040")
    @Override
    public void moduleLoaded(IRTModule module) {
        // nothing
    }

    @objid ("4e4c265b-71e2-4fbb-8428-b96cbd9191ff")
    @Override
    public void moduleRemoving(IRTModule module) {
        // nothing
    }

    @objid ("3197a3bd-ab5a-4c40-971f-837044e7aa47")
    @Override
    public void moduleRemoved(IRTModule module) {
        // nothing
    }

}
