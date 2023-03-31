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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;

/**
 * InterfaceRealization v0.0.9054
 * 
 * 
 *  In Modelio, a Realization belongs to its Classifier.
 * 
 * 
 */
@objid ("000ee2e6-c4bf-1fd8-97fe-001ec947cd2a")
public interface InterfaceRealization extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("5bbd952c-36bc-4b7b-be00-343be81160d2")
    public static final String MNAME = "InterfaceRealization";

    /**
     * The metaclass qualified name.
     */
    @objid ("0a6f4af8-53d9-4bd0-9734-16d4584d872a")
    public static final String MQNAME = "Standard.InterfaceRealization";

    /**
     * Getter for relation 'InterfaceRealization->Implemented'
     * 
     * Metamodel description:
     * <i>End of the implementation link toward an Interface.</i>
     * 
     */
    @objid ("c6c2dd64-dbd1-4a3c-84c9-ba5af2751e6b")
    Interface getImplemented();

    /**
     * Setter for relation 'InterfaceRealization->Implemented'
     * 
     * Metamodel description:
     * <i>End of the implementation link toward an Interface.</i>
     * 
     */
    @objid ("cbdc55d4-daf4-4261-9fe7-2fcc1cd02fdc")
    void setImplemented(Interface value);

    /**
     * Getter for relation 'InterfaceRealization->Implementer'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("91b7cd0d-576c-42dc-b2ec-f3fb730930fc")
    NameSpace getImplementer();

    /**
     * Setter for relation 'InterfaceRealization->Implementer'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("0cdda125-ef26-4068-98c6-13f31d9bc772")
    void setImplementer(NameSpace value);
}

