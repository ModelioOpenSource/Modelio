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
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.bpmn.events;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;

/**
 * BpmnThrowEvent v0.0.9054
 * 
 * 
 * <p>Events that throw a Result. All End Events and some Intermediate Events are throwing Events that may eventually be caught by another Event.</p>
 */
@objid ("00956d84-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnThrowEvent extends BpmnEvent {
    /**
     * The metaclass simple name.
     */
    @objid ("15490ea8-d0db-4b97-afe3-71fddc683259")
    public static final String MNAME = "BpmnThrowEvent";

    /**
     * The metaclass qualified name.
     */
    @objid ("6f55d378-93db-40b0-ac31-eda26627b5c6")
    public static final String MQNAME = "Standard.BpmnThrowEvent";

    /**
     * Getter for relation 'BpmnThrowEvent->DataInputAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9650ab25-bbc5-4efd-b1fe-99d14eb8ec12")
    EList<BpmnDataAssociation> getDataInputAssociation();

    /**
     * Filtered Getter for relation 'BpmnThrowEvent->DataInputAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("05f85754-5895-4c96-b6b2-4c8e0097390f")
    <T extends BpmnDataAssociation> List<T> getDataInputAssociation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnThrowEvent->DataInput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d3422f19-dfe7-462c-8d96-44373a07e151")
    BpmnDataInput getDataInput();

    /**
     * Setter for relation 'BpmnThrowEvent->DataInput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("84da87fc-93ce-489a-bd83-66e59d539dcd")
    void setDataInput(BpmnDataInput value);

}
