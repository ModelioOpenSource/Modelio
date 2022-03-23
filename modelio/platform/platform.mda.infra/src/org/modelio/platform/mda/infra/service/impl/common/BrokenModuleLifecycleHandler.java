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

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.platform.mda.infra.plugin.MdaInfra;
import org.modelio.vbasic.version.Version;

/**
 * {@link IModuleSession} implementation for broken modules.
 * <p>
 * Refuses to select , start and upgrade and ignore other requests.
 */
@objid ("e367570f-7759-44b3-831e-0a2edb462efa")
class BrokenModuleLifecycleHandler implements IModuleLifeCycleHandler {
    @objid ("59471a99-02fb-42c6-aa44-91b29cbd6e20")
    private IModule brokenModule;

    @objid ("ba1da5e3-0b0c-4d4d-b3f2-720bd40c0371")
    private Throwable downError;

    @objid ("d7e4697e-c168-406b-add9-7da5723210a4")
    public  BrokenModuleLifecycleHandler(IModule brokenModule, Throwable downError) {
        this.brokenModule = brokenModule;
        this.downError = downError;
        
    }

    @objid ("b369ef65-95c9-47bb-b16f-f5a25f1bbabe")
    @Override
    public boolean select() throws ModuleException {
        // refuse selection
        throw new ModuleException(getMessage());
        
    }

    @objid ("c1b13e90-6179-4c65-b9c0-f9d707d98dc8")
    @Override
    public boolean start() throws ModuleException {
        // forbid start
        throw new ModuleException(getMessage());
        
    }

    @objid ("9de2d0a1-2df2-4533-93cc-f900f7ea4e53")
    @Override
    public void stop() throws ModuleException {
        // ignore
    }

    @objid ("661846fd-1c8f-41b2-ad4b-c7c8f0a8908d")
    @Override
    public void unselect() throws ModuleException {
        // ignore
    }

    @objid ("6caccdc5-62a4-4b26-811f-82f400b2bcbd")
    @Override
    public void upgrade(Version oldVersion, Map<String, String> oldParameters) throws ModuleException {
        throw new ModuleException(getMessage());
    }

    @objid ("10d8ef53-631b-464a-8b73-7641f8a982b5")
    private String getMessage() {
        return MdaInfra.I18N.getMessage("BrokenModuleSession.message",
                        this.brokenModule.getName(),
                        this.brokenModule.getVersion(),
                        this.downError.getLocalizedMessage());
        
    }

    @objid ("db0222b8-0dff-482b-925e-e7dd4a6bc733")
    @Override
    public void configurationChanged(String pName, String oldValue, String newValue) {
        // ignore
    }

}
