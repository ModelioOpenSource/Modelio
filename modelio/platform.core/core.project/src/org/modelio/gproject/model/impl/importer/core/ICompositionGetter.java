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

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("00737454-d3aa-108f-8d81-001ec947cd2a")
public interface ICompositionGetter {
    @objid ("0073a014-d3aa-108f-8d81-001ec947cd2a")
    Collection<SmObjectImpl> getAllChildren(final Collection<SmObjectImpl> roots);

    @objid ("00738606-d3aa-108f-8d81-001ec947cd2a")
    List<SmObjectImpl> getChildren(SmObjectImpl anObject);

}
