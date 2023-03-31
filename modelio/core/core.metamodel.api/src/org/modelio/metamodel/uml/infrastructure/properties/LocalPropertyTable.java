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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("c6473a19-c74f-48dc-a4e6-23c90944d2a2")
    public static final String MNAME = "LocalPropertyTable";

    /**
     * The metaclass qualified name.
     */
    @objid ("49b465e0-4e30-47ac-b52a-62c95b2f48ed")
    public static final String MQNAME = "Infrastructure.LocalPropertyTable";

    /**
     * Getter for relation 'LocalPropertyTable->LocalAnnoted'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("61b8c6c5-294e-4051-80eb-a6a5063b74ca")
    ModelElement getLocalAnnoted();

    /**
     * Setter for relation 'LocalPropertyTable->LocalAnnoted'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f7aaee88-34b8-424c-935d-f15c55f00af4")
    void setLocalAnnoted(ModelElement value);
}

