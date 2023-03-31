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

package org.modelio.metamodel.bpmn.rootElements;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;

/**
 * BpmnArtifact v0.0.9054
 * 
 * 
 * BPMN provides modelers with the capability of showing additional information about a Process that is not directly related to the Sequence Flow or Message Flow of the Process.
 * At this point, BPMN provides three (3) standard Artifacts: Associations, Groups, and Text Annotations
 * 
 * 
 */
@objid ("00787d78-c4bf-1fd8-97fe-001ec947cd2a")
public interface BpmnArtifact extends BpmnBaseElement {
    /**
     * The metaclass simple name.
     */
    @objid ("e7c9300e-2bab-4b9d-b9bb-b13b302c555c")
    public static final String MNAME = "BpmnArtifact";

    /**
     * The metaclass qualified name.
     */
    @objid ("f9ac350b-8576-4a18-8d26-31be6d0a79dc")
    public static final String MQNAME = "Standard.BpmnArtifact";

    /**
     * Getter for relation 'BpmnArtifact->SubProcess'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8a76ff1f-802e-485a-87f5-2c946efac6f6")
    BpmnSubProcess getSubProcess();

    /**
     * Setter for relation 'BpmnArtifact->SubProcess'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f6dd448d-08b0-4eb8-9bab-fe9a1cac779e")
    void setSubProcess(BpmnSubProcess value);

    /**
     * Getter for relation 'BpmnArtifact->Collaboration'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c52668c1-c695-4b5a-8238-14e99a64beab")
    BpmnCollaboration getCollaboration();

    /**
     * Setter for relation 'BpmnArtifact->Collaboration'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("bf45ebc1-bb85-498a-8287-a95de66105e1")
    void setCollaboration(BpmnCollaboration value);

    /**
     * Getter for relation 'BpmnArtifact->Process'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("621be8c0-59b3-4806-835f-03dae6e6e780")
    BpmnProcess getProcess();

    /**
     * Setter for relation 'BpmnArtifact->Process'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("546c8dd1-f4f5-4dd2-8711-4b6c6db62a46")
    void setProcess(BpmnProcess value);
}

