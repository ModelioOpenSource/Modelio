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

package org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.bpmn.flows.BpmnSequenceFlow;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Activity edge guard label.
 */
@objid ("619dbd66-55b6-11e2-877f-002564c97630")
public class GmBpmnEdgeGuard extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("619dbd6a-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("619dbd6d-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * For deserialization only.
     */
    @objid ("619dbd6f-55b6-11e2-877f-002564c97630")
    public GmBpmnEdgeGuard() {
        // serialization
    }

    /**
     * Creates an activity edge guard label.
     * 
     * @param diagram The diagram.
     * @param relatedRef ref
     */
    @objid ("619dbd72-55b6-11e2-877f-002564c97630")
    public GmBpmnEdgeGuard(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("619dbd7b-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        final BpmnSequenceFlow iActivityEdge = (BpmnSequenceFlow) getRelatedElement();
        if (iActivityEdge == null)
            return null;
        return new IEditableText() {
                            @Override
                            public String getText() {
                                return iActivityEdge.getConditionExpression();
                            }
                
                            @Override
                            public void setText(String text) {
                                iActivityEdge.setConditionExpression(text);
                            }
                        };
    }

    @objid ("619dbd82-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        if (getParent() instanceof GmBpmnSequenceFlow)
            return getDisplayedStyle().getProperty(GmBpmnSequenceFlowStyleKeys.GUARDVISIBLE);
        else
            return getDisplayedStyle().getProperty(GmBpmnSequenceFlowStyleKeys.GUARDVISIBLE);
    }

    @objid ("619f43db-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        final BpmnSequenceFlow edge = (BpmnSequenceFlow) getRelatedElement();
        String guard = edge.getConditionExpression();
        if (!guard.isEmpty() &&
                edge.getDefaultFrom() == null &&
                edge.getDefaultOfExclusive() == null &&
                edge.getDefaultOfInclusive() == null &&
                edge.getDefaultOfComplex() == null) {
            return guard;
        }
        return "";
    }

    @objid ("619f43e0-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnEdgeGuard.");
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

    @objid ("619f43e6-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnEdgeGuard.", MINOR_VERSION);
    }

    @objid ("619f43ec-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("619f43f1-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
