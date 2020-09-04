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
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;

/**
 * BpmnCatchEvent v0.0.9054
 * 
 * 
 * Events catching some sort of signal or condition (message, condition, timer, etc.).
 * Initial events are always catch events. Intermediate events may catch events.
 */
@objid ("00889ec4-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnCatchEvent extends BpmnEvent {
    /**
     * The metaclass simple name.
     */
    @objid ("1c3f5c24-8621-4bad-bf29-61846fc7f957")
    public static final String MNAME = "BpmnCatchEvent";

    /**
     * The metaclass qualified name.
     */
    @objid ("cac2abf6-b501-4855-9130-055dc5038cae")
    public static final String MQNAME = "Standard.BpmnCatchEvent";

    /**
     * Getter for attribute 'BpmnCatchEvent.ParallelMultiple'
     * 
     * Metamodel description:
     * <i>This means that there are multiple triggers required before the events triggers outcoming sequence flows. All of the types of triggers that are listed in the catcht Event MUST be triggered before the processing continues.  </i>
     */
    @objid ("5c27ebdf-7c6b-484d-b90f-f4f062d25e1b")
    boolean isParallelMultiple();

    /**
     * Setter for attribute 'BpmnCatchEvent.ParallelMultiple'
     * 
     * Metamodel description:
     * <i>This means that there are multiple triggers required before the events triggers outcoming sequence flows. All of the types of triggers that are listed in the catcht Event MUST be triggered before the processing continues.  </i>
     */
    @objid ("f9ba2a79-18a4-4beb-920e-8d72fbd64fe0")
    void setParallelMultiple(boolean value);

    /**
     * Getter for relation 'BpmnCatchEvent->DataOutputAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("169e46a6-16a0-44e8-9617-281d498a255a")
    EList<BpmnDataAssociation> getDataOutputAssociation();

    /**
     * Filtered Getter for relation 'BpmnCatchEvent->DataOutputAssociation'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0a40eedb-0520-4fe9-98d0-1d093116dd31")
    <T extends BpmnDataAssociation> List<T> getDataOutputAssociation(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnCatchEvent->DataOutput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("dd4e6c10-1f85-46a5-9c1a-7ac77ef0e50d")
    BpmnDataOutput getDataOutput();

    /**
     * Setter for relation 'BpmnCatchEvent->DataOutput'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8d1db025-5c08-4096-90ba-bf5095a78c00")
    void setDataOutput(BpmnDataOutput value);

}
