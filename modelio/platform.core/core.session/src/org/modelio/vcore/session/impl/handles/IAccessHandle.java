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

package org.modelio.vcore.session.impl.handles;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.AccessDeniedException;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Handle used by StdMetaObject to handle access rights.
 */
@objid ("006f90d2-0d1e-1f20-85a5-001ec947cd2a")
public interface IAccessHandle {
    /**
     * Check the given dependency on the given object can be modified. Also checks the symmetric dependency.
     * <p>
     * Throw an exception if the dependency modification is denied.
     * @param obj the object to modify
     * @param dep the dependency to modify
     * @param val the object to append or remove to the dependency
     * @throws org.modelio.vcore.smkernel.AccessDeniedException if the modification is denied.
     */
    @objid ("000c4a54-702c-1f21-85a5-001ec947cd2a")
    void checkAccessFor(final SmObjectImpl obj, final SmDependency dep, final SmObjectImpl val) throws AccessDeniedException;

    /**
     * Check the given object is modifiable.
     * Throws an {@link IllegalStateException} if the object is not modifiable.
     * @param obj the object  to check.
     * @throws org.modelio.vcore.smkernel.AccessDeniedException if the object is not modifiable.
     */
    @objid ("000c5e90-702c-1f21-85a5-001ec947cd2a")
    void checkAccess(final SmObjectImpl obj) throws AccessDeniedException;

    /**
     * Check the given attribute on the given object can be modified.
     * <p>
     * Throw an exception if the attribute modification is denied.
     * @param obj the object to modify
     * @param att the attribute to modify
     * @param val the object to append or remove to the dependency
     * @throws org.modelio.vcore.smkernel.AccessDeniedException if the modification is denied.
     */
    @objid ("8cb387a0-283b-4a98-b2eb-f7783e65b469")
    void checkAccessFor(SmObjectImpl obj, SmAttribute att, SmObjectImpl val) throws AccessDeniedException;

}
