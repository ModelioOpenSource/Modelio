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
package org.modelio.bpmn.diagram.editor.elements.bpmndataobject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.bpmn.objects.BpmnDataState;
import org.modelio.metamodel.bpmn.objects.BpmnItemAwareElement;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Label model for all {@link BpmnItemAwareElement}.
 */
@objid ("60d452f9-55b6-11e2-877f-002564c97630")
public class GmBpmnDataLabel extends GmDefaultModelElementLabel {
    @objid ("60d452ff-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("60d452fc-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    /**
     * Create a model element label
     * @param diagram the diagram.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("60d45301-55b6-11e2-877f-002564c97630")
    public  GmBpmnDataLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("60d5d963-55b6-11e2-877f-002564c97630")
    public  GmBpmnDataLabel() {
        // serialization
    }

    @objid ("60d76013-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnDataLabel.MAJOR_VERSION;
    }

    @objid ("60d76002-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnDataLabel.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
        
    }

    @objid ("60d76008-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnDataLabel.", GmBpmnDataLabel.MINOR_VERSION);
        
    }

    @objid ("60d5d98f-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        StringBuilder result = new StringBuilder();
        BpmnItemAwareElement relatedElement = (BpmnItemAwareElement) getRelatedElement();
        
        if (relatedElement != null && relatedElement.isValid()) {
            result.append(relatedElement.getName());
        
            // Append represented element name if required
            String referenceName = getReferencedElementName(relatedElement);
            if (referenceName != null && Boolean.TRUE.equals(getDisplayedStyle().getProperty(GmBpmnDataObjectStyleKeys.SHOWREPRESENTED))) {
                result.append(": ");
                result.append(referenceName);
            }
        
            // Append state
            String stateName = getInStateName(relatedElement);
            if (stateName != null) {
                result.append(" [");
                result.append(stateName);
                result.append("]");
            }
        }
        return result.toString();
    }

    @objid ("68066ea9-6d1a-464a-8f24-9a37eb7403c4")
    private String getInStateName(BpmnItemAwareElement element) {
        // a BPMN can reference a UML state by too much ways:
        // 1) with 'inState : State' relation
        // 2) with 'dataState:BpmnState' (not used anywhere, not visible in Modelio GUI)
        // 2.1) then BpmnState.inState : State
        // 2.2) No UML state on the BpmnState , use the BpmnState name
        ModelElement inState = State.getTarget(element);
        BpmnDataState dataState = element.getDataState();
        String stateName = null;
        
        if (inState == null && dataState != null) {
            inState = State.getTarget(dataState);
            stateName = dataState.getName();
        }
        
        if (inState != null) {
            stateName = inState.getName();
        }
        return stateName;
    }

    @objid ("60d7600e-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("78479f84-2c25-4aef-9e2e-f80094fd935a")
    private String getReferencedElementName(BpmnItemAwareElement relatedElement) {
        ModelElement representedElement = Represents.getTarget(relatedElement);
        if (representedElement != null) {
            return representedElement.getName();
        } else if (relatedElement.getItemSubjectRef() != null) {
            BpmnItemDefinition item = relatedElement.getItemSubjectRef();
            ModelElement ref = Reference.getTarget(item);
            if (ref != null) {
                return ref.getName();
            } else {
                return item.getName();
            }
        } else {
            return null;
        }
        
    }

}
