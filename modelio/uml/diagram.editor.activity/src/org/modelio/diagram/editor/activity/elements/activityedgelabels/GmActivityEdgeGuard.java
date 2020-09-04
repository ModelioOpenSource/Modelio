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

package org.modelio.diagram.editor.activity.elements.activityedgelabels;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.activity.elements.controlflow.GmControlFlow;
import org.modelio.diagram.editor.activity.elements.controlflow.GmControlStyleKeys;
import org.modelio.diagram.editor.activity.elements.objectflow.GmObjectFlowStyleKeys;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityEdge;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Activity edge guard label.
 * 
 * @author sbe
 */
@objid ("29a1289a-55b6-11e2-877f-002564c97630")
public class GmActivityEdgeGuard extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29a1289e-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29a128a1-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Default extension role for this node.
     */
    @objid ("c30767f8-3e0c-4977-bb8a-13afbeac4f11")
    public static final String ROLE_DEFAULT = "guard";

    /**
     * For deserialization only.
     */
    @objid ("29a128a3-55b6-11e2-877f-002564c97630")
    public GmActivityEdgeGuard() {
        // serialization
    }

    /**
     * Creates an activity edge guard label.
     * @param diagram The diagram.
     * @param relatedRef related element reference, must not be <code>null</code>.
     */
    @objid ("29a128a6-55b6-11e2-877f-002564c97630")
    public GmActivityEdgeGuard(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("29a128af-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        final ActivityEdge iActivityEdge = (ActivityEdge) getRelatedElement();
        if (iActivityEdge == null) {
            return null;
        }
        return new IEditableText() {
                    @Override
                    public String getText() {
                        return iActivityEdge.getGuard();
                    }
        
                    @Override
                    public void setText(String text) {
                        iActivityEdge.setGuard(text);
                    }
                };
    }

    @objid ("29a128b6-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        if (getParent() instanceof GmControlFlow) {
            return getDisplayedStyle().getProperty(GmControlStyleKeys.GUARDVISIBLE);
        } else {
            return getDisplayedStyle().getProperty(GmObjectFlowStyleKeys.GUARDVISIBLE);
        }
    }

    @objid ("29a128ba-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        final ActivityEdge edge = (ActivityEdge) getRelatedElement();
        String guard = edge.getGuard();
        if (!guard.isEmpty()) {
            guard = "[" + guard + "]";
            return guard;
        }
        return "";
    }

    @objid ("29a2af3c-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmActivityEdgeGuard.");
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

    @objid ("29a2af42-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmActivityEdgeGuard.", GmActivityEdgeGuard.MINOR_VERSION);
    }

    @objid ("29a2af48-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("29a2af4d-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
