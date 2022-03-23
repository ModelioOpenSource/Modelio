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

package org.modelio.metamodel.bpmn.bpmnService;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.rootElements.BpmnSharedElement;

/**
 * BpmnEndPoint v0.0.9054
 * 
 * 
 * <p>The actual definition of the service address is out of scope of BPMN 2.0. The EndPoint element is an extension point&nbsp;and extends from RootElement. The EndPoint element MAY be extended with endpoint reference definitions&nbsp;introduced in other specifications (e.g., WS-Addressing).</p><p>EndPoints can be specified for Participants.</p>
 */
@objid ("000e89b8-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnEndPoint extends BpmnSharedElement {
    /**
     * The metaclass simple name.
     */
    @objid ("3a20f2f7-0be5-451a-b0cd-9f566bfedef0")
    public static final String MNAME = "BpmnEndPoint";

    /**
     * The metaclass qualified name.
     */
    @objid ("dd321f16-2f70-4f4c-93bc-4af2c8988127")
    public static final String MQNAME = "Standard.BpmnEndPoint";

    /**
     * Getter for relation 'BpmnEndPoint->ParticipantRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9b1674b0-329c-4c3b-af27-1a73c148c94c")
    EList<BpmnParticipant> getParticipantRefs();

    /**
     * Filtered Getter for relation 'BpmnEndPoint->ParticipantRefs'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f361cbc3-1f82-4066-9212-64f6c840171d")
    <T extends BpmnParticipant> List<T> getParticipantRefs(java.lang.Class<T> filterClass);

}
