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

package org.modelio.metamodel.bpmn.bpmnDiagrams;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.BehaviorDiagram;

/**
 * BpmnSubProcessDiagram v0.0.9054
 * 
 * 
 * Diagram dedicated to subprocesses. A sub process can be collapsed, in that case, a separated SubProcessDiagram is created. If it is expanded, then the elements are drawn within the subprocess inside the process-collaboration diagram. 
 * 
 * SubProcess Diagrams contain a subset of the elements that supports a collaboration-process diagram: in particular, elements specific to collaboration, such as message flow, participants, pools and lanes are not presented in SubProcessDiagrams.
 * 
 * 
 */
@objid ("000c897e-c4c0-1fd8-97fe-001ec947cd2a")
public interface BpmnSubProcessDiagram extends BehaviorDiagram {
    /**
     * The metaclass simple name.
     */
    @objid ("1c4ffbe8-917e-42b3-a428-c0b34c31fd71")
    public static final String MNAME = "BpmnSubProcessDiagram";

    /**
     * The metaclass qualified name.
     */
    @objid ("afcf6df6-43a1-4fb8-a38b-6fec5eff8bad")
    public static final String MQNAME = "Standard.BpmnSubProcessDiagram";
}

