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

package org.modelio.diagram.editor.bpmn.elements.bpmnmessage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specific label for {@link GmBpmnMessage}.
 */
@objid ("615f2dda-55b6-11e2-877f-002564c97630")
public class GmBpmnMessageLabel extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("615f2ddd-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("615f2de0-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Create a model element label
     * 
     * @param diagram the diagram.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("6160b43a-55b6-11e2-877f-002564c97630")
    public GmBpmnMessageLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("6160b445-55b6-11e2-877f-002564c97630")
    public GmBpmnMessageLabel() {
        // serialization
    }

    @objid ("6160b471-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        StringBuilder result = new StringBuilder();
        BpmnMessage relatedElement = (BpmnMessage) getRelatedElement();
        
        if (relatedElement != null && relatedElement.isValid()) {
            result.append(relatedElement.getName());
        
            // Append represented element name if required
            String referenceName = getReferencedElementName(relatedElement);
            if (referenceName != null && Boolean.TRUE.equals(getDisplayedStyle().getProperty(GmBpmnMessageStructuredStyleKeys.SHOWREPRESENTED))) {
                result.append(" : ");
                result.append(referenceName);
            }
        
            // Append state
            ModelElement inState = State.getTarget(relatedElement);
            if (inState != null) {
                result.append(" [");
                result.append(inState.getName());
                result.append("]");
            }
        }
        return result.toString();
    }

    @objid ("61623ae7-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnMessageLabel.");
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

    @objid ("61623aed-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnMessageLabel.", GmBpmnMessageLabel.MINOR_VERSION);
    }

    @objid ("61623af3-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("61623af8-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnMessageLabel.MAJOR_VERSION;
    }

    /**
     * Elaborate a name for the 'referenced' element of the Message. May return null;
     * 
     * @param element @return
     */
    @objid ("0eb83089-491e-4993-83fb-554e079279e3")
    private String getReferencedElementName(BpmnMessage element) {
        ModelElement type = Represents.getTarget(element);
        if (type != null) {
            return type.getName();
        } else if (element.getItemRef() != null) {
            BpmnItemDefinition item = element.getItemRef();
            ModelElement ref = Reference.getTarget(item);
            if (ref != null) {
                return ref.getName();
            } else {
                return item.getName();
            }
        }
        return null;
    }

}
