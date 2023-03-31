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
package org.modelio.gproject.importer.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("00776a00-d3aa-108f-8d81-001ec947cd2a")
public interface IBrokenDependencyHandler {
    @objid ("00777c48-d3aa-108f-8d81-001ec947cd2a")
    void handleBrokenDep(IBrokenDepReport brokenDepReport);

    @objid ("0077abfa-d3aa-108f-8d81-001ec947cd2a")
    void postProcess();
}

