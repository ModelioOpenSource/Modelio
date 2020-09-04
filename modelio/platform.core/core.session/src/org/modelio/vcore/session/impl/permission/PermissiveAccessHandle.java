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

/**
 * 
 */
package org.modelio.vcore.session.impl.permission;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.IAccessManager;
import org.modelio.vcore.session.impl.handles.IAccessHandle;
import org.modelio.vcore.smkernel.AccessDeniedException;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Access handle that allows anything.
 */
@objid ("000f2940-fd1b-1f27-a7da-001ec947cd2a")
public class PermissiveAccessHandle implements IAccessHandle {
    @objid ("c7a093da-d58f-11e1-b069-001ec947ccaf")
    private List<IAccessManager> accessManagers = new ArrayList<>();

    @objid ("000f640a-fd1b-1f27-a7da-001ec947cd2a")
    @Override
    public void checkAccessFor(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) throws AccessDeniedException {
        // Always succeed
    }

    @objid ("000f9916-fd1b-1f27-a7da-001ec947cd2a")
    @Override
    public void checkAccess(final SmObjectImpl obj) {
        // Do nothing
    }

    @objid ("41ba6fce-9de0-48fc-a11c-d9747a2565d0")
    @Override
    public void checkAccessFor(final SmObjectImpl obj, final SmAttribute att, final SmObjectImpl val) throws AccessDeniedException {
        // Always succeed
    }

}
