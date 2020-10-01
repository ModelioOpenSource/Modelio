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
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Instance;

/**
 * AttributeLink v0.0.9054
 * 
 * 
 * An AttributeLink defines an Attribute's value at instance level. AttributeLinks appear attached to Instances or Parts. 
 * 
 * In UML 2.0, attribute links are called slots. 
 * 
 * In Modelio, an AttributeLink belongs to an Instance.
 */
@objid ("009893c4-c4be-1fd8-97fe-001ec947cd2a")
public interface AttributeLink extends UmlModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("fb12cbd3-7d5e-4883-9d6e-08fd0e6eadb2")
    public static final String MNAME = "AttributeLink";

    /**
     * The metaclass qualified name.
     */
    @objid ("4eaa0768-ed32-4896-b103-2031b13429ab")
    public static final String MQNAME = "Standard.AttributeLink";

    /**
     * Getter for attribute 'AttributeLink.Value'
     * 
     * Metamodel description:
     * <i>Current value of the Attribute's Slot for the Instance.</i>
     */
    @objid ("3b52c931-5c0c-419d-8527-2ee8aaed783c")
    String getValue();

    /**
     * Setter for attribute 'AttributeLink.Value'
     * 
     * Metamodel description:
     * <i>Current value of the Attribute's Slot for the Instance.</i>
     */
    @objid ("95242cdd-14e2-487e-bf47-a6192c3e2eee")
    void setValue(String value);

    /**
     * Getter for relation 'AttributeLink->Attributed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cb9d6796-615e-469c-a192-bf29c6d2b917")
    Instance getAttributed();

    /**
     * Setter for relation 'AttributeLink->Attributed'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("249632be-2c71-4832-ab98-9c4eb17ce914")
    void setAttributed(Instance value);

    /**
     * Getter for relation 'AttributeLink->Base'
     * 
     * Metamodel description:
     * <i>Defines the optional Attribute that specifies the AttributeLink.</i>
     */
    @objid ("477ca386-f13f-4c62-873f-a6908afe9d77")
    Attribute getBase();

    /**
     * Setter for relation 'AttributeLink->Base'
     * 
     * Metamodel description:
     * <i>Defines the optional Attribute that specifies the AttributeLink.</i>
     */
    @objid ("0afee4ce-79d4-412c-a70e-13ba7e8013ec")
    void setBase(Attribute value);

}
