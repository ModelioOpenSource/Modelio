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

package org.modelio.mda.infra.service.impl.common;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.lifecycle.IModuleLifeCycleHandler;
import org.modelio.api.module.lifecycle.ModuleException;
import org.modelio.vbasic.version.Version;

@objid ("5709159d-b52a-4304-9492-c8ba62152d20")
class FakeIModuleSession implements IModuleLifeCycleHandler {
    @objid ("30ba7f3f-11d5-4dba-9b43-77ea25b1cb82")
    @Override
    public boolean select() throws ModuleException {
        return true;
    }

    @objid ("5e72d54a-59dd-41f6-8950-ef97bed0ff50")
    @Override
    public boolean start() throws ModuleException {
        return true;
    }

    @objid ("7ae5a547-348e-48ab-af45-81c3131bd3b3")
    @Override
    public void stop() throws ModuleException {
        // noop
    }

    @objid ("cacccc3f-5ce0-42b7-a8d5-c1c576bf770b")
    @Override
    public void unselect() throws ModuleException {
        // noop
    }

    @objid ("a3906db7-dff9-4d86-8ef3-273e04eb6a95")
    @Override
    public void upgrade(Version oldVersion, Map<String, String> oldParameters) throws ModuleException {
        // noop
    }

}
