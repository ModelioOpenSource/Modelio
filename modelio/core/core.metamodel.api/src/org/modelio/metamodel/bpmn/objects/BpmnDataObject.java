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
package org.modelio.metamodel.bpmn.objects;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * BpmnDataObject v0.0.9054
 * 
 * 
 * The primary construct for modeling data within the Process flow is the DataObject element. A DataObject has a well-defined lifecycle, with resulting visibility constraints.
 * Data Object elements must be contained within Process or Sub-Process elements. Data Object elements are visible in a Process diagram.
 */
@objid ("000345d0-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnDataObject extends BpmnItemAwareElement {
    /**
     * The metaclass simple name.
     */
    @objid ("fc688a77-656d-43ef-97f3-f97b38e8ae2c")
    public static final String MNAME = "BpmnDataObject";

    /**
     * The metaclass qualified name.
     */
    @objid ("79ab2ac5-3e55-4f52-872e-6b8aacd33046")
    public static final String MQNAME = "Standard.BpmnDataObject";

    /**
     * Getter for attribute 'BpmnDataObject.IsCollection'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("df094be9-d1ec-4e1a-a0a1-58639469a79a")
    boolean isIsCollection();

    /**
     * Setter for attribute 'BpmnDataObject.IsCollection'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("48bba1eb-e40f-43c4-8b85-26291e0c283e")
    void setIsCollection(boolean value);

}
