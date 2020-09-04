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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.uml.infrastructure.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * LocalPropertyTable v0.0.9054
 * 
 * 
 * <p>Non typed property table that is owned by nobody.</p><p>This table is not copied nor imported with the element referencing it. This table is not versioned either.</p>
 */
@objid ("006c6ff6-ec87-1098-b22e-001ec947cd2a")
public interface LocalPropertyTable extends PropertyTable {
    /**
     * The metaclass simple name.
     */
    @objid ("9e062c8c-bba7-4ba4-9647-4d42d60e4184")
    public static final String MNAME = "LocalPropertyTable";

    /**
     * The metaclass qualified name.
     */
    @objid ("5fa9dbac-e377-4dba-915f-1c344ac31e64")
    public static final String MQNAME = "Infrastructure.LocalPropertyTable";

    /**
     * Getter for relation 'LocalPropertyTable->LocalAnnoted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8f8c6fd1-658b-444d-89de-02bc0e168771")
    ModelElement getLocalAnnoted();

    /**
     * Setter for relation 'LocalPropertyTable->LocalAnnoted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("51d36188-38e7-4e38-8cda-54363159545f")
    void setLocalAnnoted(ModelElement value);

}
