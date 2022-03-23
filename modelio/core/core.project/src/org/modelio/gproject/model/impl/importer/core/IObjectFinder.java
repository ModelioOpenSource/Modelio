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
package org.modelio.gproject.model.impl.importer.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("007462f6-d3aa-108f-8d81-001ec947cd2a")
public interface IObjectFinder {
    @objid ("5861eabf-9ff7-430a-bc4c-5e7fff1b1e05")
    SmAttribute getSameAttribute(SmAttribute dep);

    @objid ("04d995fe-153b-4eaa-9ed4-53baf130e049")
    SmDependency getSameDependency(SmDependency dep);

    @objid ("9ab4ac2e-ece4-40ee-8042-ab5ae42b617e")
    MClass getSameMetaclass(MClass srcClassof);

    @objid ("00747354-d3aa-108f-8d81-001ec947cd2a")
    SmObjectImpl getSameObject(final SmObjectImpl searchedObject);

}
