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
package org.modelio.vcore.session.api.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface allowing to filter MObjects through its {@link #accept(MObject) method}.
 */
@objid ("cc438b71-0529-4637-9448-2218b3a2e8b5")
public interface IMObjectFilter {
    /**
     * This method checks if an element is accepted.
     * @param element the element to check.
     * @return <code>true</code> if the element is accepted.
     */
    @objid ("1ad704b9-ff53-4e66-a53f-e67d232a7622")
    boolean accept(final MObject element);
}

