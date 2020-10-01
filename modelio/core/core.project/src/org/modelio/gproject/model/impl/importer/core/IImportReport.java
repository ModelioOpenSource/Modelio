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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("008cf5f0-5246-1091-8d81-001ec947cd2a")
public interface IImportReport {
    @objid ("ebc90e98-b078-423c-988b-c44900906f49")
    SmObjectImpl getCreatedObject(final SmObjectImpl refObject);

    @objid ("008cfdc0-5246-1091-8d81-001ec947cd2a")
    List<SmObjectImpl> getCreatedObjects();

    @objid ("008d2584-5246-1091-8d81-001ec947cd2a")
    List<SmObjectImpl> getDeletedObjects();

    @objid ("a462f5be-50bb-4ef0-847b-af1cbc9f00eb")
    SmObjectImpl getUpdatedObject(final SmObjectImpl refObject);

    @objid ("008d1256-5246-1091-8d81-001ec947cd2a")
    List<SmObjectImpl> getUpdatedObjects();

}
