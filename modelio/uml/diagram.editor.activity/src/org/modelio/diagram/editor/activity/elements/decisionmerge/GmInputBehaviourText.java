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

package org.modelio.diagram.editor.activity.elements.decisionmerge;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.text.GmElementText;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.DecisionMergeNode;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a label that displays the input behavior of a DecisionMerge.
 * <p>
 */
@objid ("2a45f41f-55b6-11e2-877f-002564c97630")
public class GmInputBehaviourText extends GmElementText {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2a45f423-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2a45f426-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialization.
     */
    @objid ("2a45f428-55b6-11e2-877f-002564c97630")
    public GmInputBehaviourText() {
    }

    /**
     * Default c'tor.
     * 
     * @param diagram the diagram.
     * @param relatedRef reference of the related element, must not be null.
     */
    @objid ("2a45f42b-55b6-11e2-877f-002564c97630")
    public GmInputBehaviourText(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("2a45f434-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        final StyleKey key = getParent().getStyleKey(MetaKey.SHOWLABEL);
        if (key == null) {
            return true;
        } else {
            return getDisplayedStyle().getProperty(key);
        }
    }

    @objid ("2a45f438-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        final DecisionMergeNode el = (DecisionMergeNode) getRelatedElement();
        
        if (el == null)
            return null;
        return new IEditableText() {
                    @Override
                    public String getText() {
                        return el.getDecisionInputBehavior();
                    }
        
                    @Override
                    public void setText(String text) {
                        el.setDecisionInputBehavior(text);
                    }
                };
    }

    @objid ("2a45f43f-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeText() {
        String label1 = "\n" + ((DecisionMergeNode) getRelatedElement()).getDecisionInputBehavior();
        return label1;
    }

    @objid ("2a45f444-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInputBehaviourText.");
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

    @objid ("2a45f44a-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInputBehaviourText.", GmInputBehaviourText.MINOR_VERSION);
    }

    @objid ("2a45f450-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2a477ab9-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
