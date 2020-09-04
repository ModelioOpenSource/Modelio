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

package org.modelio.diagram.editor.state.elements.transition;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Transition;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * PostCondition label for Transition
 * 
 * @author fpoyer
 */
@objid ("f5aeb533-55b6-11e2-877f-002564c97630")
public class GmTransitionPostConditionLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f5aeb537-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f5aeb53a-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor.
     * 
     * @param diagram the diagram.
     * @param relatedRef related element reference, must not be null.
     */
    @objid ("f5aeb53c-55b6-11e2-877f-002564c97630")
    public GmTransitionPostConditionLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("f5aeb545-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        final Transition theTransition = (Transition) getRelatedElement();
        
        if (theTransition == null)
            return "{}";
        
        final StringBuilder symbol = new StringBuilder();
        
        // postGard
        final String postCondition = theTransition.getPostCondition();
        if (postCondition != null && !postCondition.equals("")) {
            symbol.append("{");
            symbol.append(postCondition);
            symbol.append("}");
        }
        return symbol.toString();
    }

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("f5aeb54a-55b6-11e2-877f-002564c97630")
    public GmTransitionPostConditionLabel() {
        // Nothing to do.
    }

    @objid ("f5aeb54d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return getDisplayedStyle().getProperty(GmTransitionStyleKeys.POSTCONDITIONVISIBLE);
    }

    @objid ("f5aeb552-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return new IEditableText() {
                                                                        
                                                                            @Override
                                                                            public void setText(String text) {
                                                                                final Transition theTransition = (Transition) getRelatedElement();
                                                                                theTransition.setPostCondition(text);
                                                                            }
                                                                        
                                                                            @Override
                                                                            public String getText() {
                                                                                final Transition theTransition = (Transition) getRelatedElement();
                                                                        return theTransition.getPostCondition();
                                                                                    }
                                                                                };
    }

    @objid ("f5aeb559-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmTransitionPostConditionLabel.");
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

    @objid ("f5aeb55f-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmTransitionPostConditionLabel.", GmTransitionPostConditionLabel.MINOR_VERSION);
    }

    @objid ("f5b03bbc-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("f5b03bc1-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
