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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * LocalPropertyTable v0.0.9054
 * 
 * 
 * <p>Non typed property table that is owned by nobody.</p><p>This table is not copied nor imported with the element referencing it. This table is not versioned either.</p>
 * 
 * 
 * 
 */
@objid ("006c6ff6-ec87-1098-b22e-001ec947cd2a")
public interface LocalPropertyTable extends PropertyTable {
    /**
     * The metaclass simple name.
     */
    @objid ("0a500151-e42c-4fcf-ab28-278b466eaa0c")
    public static final String MNAME = "LocalPropertyTable";

    /**
     * The metaclass qualified name.
     */
    @objid ("eeadc232-913e-4b93-831b-373f42404945")
    public static final String MQNAME = "Infrastructure.LocalPropertyTable";

    /**
     * Getter for relation 'LocalPropertyTable->LocalAnnoted'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("85cc69e4-6d3f-4897-8078-6264c7cc9e87")
    ModelElement getLocalAnnoted();

    /**
     * Setter for relation 'LocalPropertyTable->LocalAnnoted'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ddefdd6c-e4bf-4b67-aa36-d52b0a4f6b63")
    void setLocalAnnoted(ModelElement value);
}

