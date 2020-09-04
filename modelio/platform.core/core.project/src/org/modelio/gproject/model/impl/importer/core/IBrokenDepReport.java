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

package org.modelio.gproject.model.impl.importer.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("001c697a-f71a-1090-8d81-001ec947cd2a")
public interface IBrokenDepReport {
    @objid ("001c7910-f71a-1090-8d81-001ec947cd2a")
    SmObjectImpl getLocalObject();

    @objid ("001c8874-f71a-1090-8d81-001ec947cd2a")
    SmObjectImpl getMissingRefObject();

    @objid ("001c7172-f71a-1090-8d81-001ec947cd2a")
    SmObjectImpl getRefObject();

    @objid ("001c809a-f71a-1090-8d81-001ec947cd2a")
    SmDependency getSmDep();

}
