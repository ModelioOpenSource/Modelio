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
 * Guard label for transition
 * 
 * @author fpoyer
 */
@objid ("f5aba7fd-55b6-11e2-877f-002564c97630")
public class GmTransitionGuardLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f5aba801-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f5aba804-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * C'tor.
     * @param diagram the diagram.
     * @param relatedRef related element reference, must not be null.
     */
    @objid ("f5aba806-55b6-11e2-877f-002564c97630")
    public GmTransitionGuardLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("f5aba80f-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        final Transition theTransition = (Transition) getRelatedElement();
        
        if (theTransition == null)
            return "[]";
        
        final StringBuilder symbol = new StringBuilder();
        
        // Guard condition
        final String condition = theTransition.getGuard();
        if (condition != null && !condition.equals("")) {
            symbol.append("[");
            symbol.append(condition);
            symbol.append("]");
        }
        return symbol.toString();
    }

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("f5aba814-55b6-11e2-877f-002564c97630")
    public GmTransitionGuardLabel() {
        // Nothing to do.
    }

    @objid ("f5aba817-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return getDisplayedStyle().getProperty(GmTransitionStyleKeys.GUARDVISIBLE);
    }

    @objid ("f5aba81c-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return new IEditableText() {
                                                                        
                                                                            @Override
                                                                            public void setText(String text) {
                                                                                final Transition theTransition = (Transition) getRelatedElement();
                                                                                theTransition.setGuard(text);
                                                                            }
                                                                        
                                                                            @Override
                                                                            public String getText() {
                                                                                final Transition theTransition = (Transition) getRelatedElement();
                                                                        return theTransition.getGuard();
                                                                                    }
                                                                                };
    }

    @objid ("f5ad2e7a-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmTransitionGuardLabel.");
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

    @objid ("f5ad2e80-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmTransitionGuardLabel.", GmTransitionGuardLabel.MINOR_VERSION);
    }

    @objid ("f5ad2e86-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("f5ad2e8b-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
