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

package org.modelio.uml.activitydiagram.editor.elements.activityedgelabels;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.uml.activitydiagram.editor.elements.controlflow.GmControlFlow;
import org.modelio.uml.activitydiagram.editor.elements.controlflow.GmControlStyleKeys;
import org.modelio.uml.activitydiagram.editor.elements.objectflow.GmObjectFlowStyleKeys;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Activity edge weight label
 * 
 * @author sbe
 */
@objid ("29a2af5a-55b6-11e2-877f-002564c97630")
public class GmActivityEdgeWeight extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29a2af5e-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29a2af61-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Default extension role for this node.
     */
    @objid ("1588c45c-8bf5-4c43-b02f-4e351912027d")
    public static final String ROLE_DEFAULT = "weight";

    /**
     * For deserialization only.
     */
    @objid ("29a2af63-55b6-11e2-877f-002564c97630")
    public GmActivityEdgeWeight() {
        // serialization
    }

    /**
     * Create the weight label.
     * 
     * @param diagram the diagram
     * @param relatedRef related element reference, must not be <code>null</code>.
     */
    @objid ("29a2af66-55b6-11e2-877f-002564c97630")
    public GmActivityEdgeWeight(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("29a2af6f-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        final ActivityEdge iActivityEdge = (ActivityEdge) getRelatedElement();
        if (iActivityEdge == null) {
            return null;
        }
        return new IEditableText() {
                    @Override
                    public String getText() {
                        return iActivityEdge.getWeight();
                    }
        
                    @Override
                    public void setText(String text) {
                        iActivityEdge.setWeight(text);
                    }
                };
    }

    @objid ("29a2af76-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        if (getParent() instanceof GmControlFlow) {
            return getDisplayedStyle().getProperty(GmControlStyleKeys.WEIGHTVISIBLE);
        } else {
            return getDisplayedStyle().getProperty(GmObjectFlowStyleKeys.WEIGHTVISIBLE);
        }
    }

    @objid ("29a2af7a-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        ActivityEdge edge = (ActivityEdge) getRelatedElement();
        String weight = edge.getWeight();
        if (!weight.equals("")) {
            weight = "{weight=" + weight + "}";
            return weight;
        }
        return "";
    }

    @objid ("29a435dc-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmActivityEdgeWeight.");
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

    @objid ("29a435e2-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmActivityEdgeWeight.", GmActivityEdgeWeight.MINOR_VERSION);
    }

    @objid ("29a435e8-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("29a435ed-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
