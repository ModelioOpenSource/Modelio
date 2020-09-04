/* 
 * Copyright 2013-2019 Modeliosoft
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

/**
 * BpmnLinkEventDefinition v0.0.9054
 * 
 * 
 * <p>A Link Event is a mechanism for connecting two sections of a Process. Link Events can be used to create looping situations or to avoid long Sequence Flow lines. The use of Link Events is limited to a single Process level (i.e., they cannot link a parent Process with a Sub-Process).</p><p>Paired Link Events can also be used as &quot;Off-Page Connectors&quot; for printing a Process across multiple pages. They can also be used as generic &quot;Go To&quot; objects within the Process level. There can be multiple source Link Events, but there can only be one target Link Event. When used to &quot;catch&quot; from the source Link, the Event marker will be unfilled. When used to &quot;throw&quot; to the target Link, the Event marker will be filled.</p><p>&nbsp;</p>
 */
@objid ("0090129e-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnLinkEventDefinition extends BpmnEventDefinition {
    /**
     * The metaclass simple name.
     */
    @objid ("9452e0fb-108e-4a39-adc3-fc65d7e190c4")
    public static final String MNAME = "BpmnLinkEventDefinition";

    /**
     * The metaclass qualified name.
     */
    @objid ("5af0f455-d73d-492e-bc18-f4d2d448e72b")
    public static final String MQNAME = "Standard.BpmnLinkEventDefinition";

    /**
     * Getter for relation 'BpmnLinkEventDefinition->Source'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f61ff97b-ab29-4762-8288-10eeefc1e031")
    EList<BpmnLinkEventDefinition> getSource();

    /**
     * Filtered Getter for relation 'BpmnLinkEventDefinition->Source'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d0794415-fb73-4fe4-a347-64051e76471d")
    <T extends BpmnLinkEventDefinition> List<T> getSource(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'BpmnLinkEventDefinition->Target'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("31e66cf7-a38c-4d78-8012-94afcdaab85d")
    BpmnLinkEventDefinition getTarget();

    /**
     * Setter for relation 'BpmnLinkEventDefinition->Target'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("355ad102-c4d5-4a17-b8dc-d02a5baf48d2")
    void setTarget(BpmnLinkEventDefinition value);

}
