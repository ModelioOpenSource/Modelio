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
package org.modelio.uml.communicationdiagram.editor.elements.communicationmessage;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Label representing a {@link ICommunicationMessage}.
 */
@objid ("7a469b1f-55b6-11e2-877f-002564c97630")
public class GmCommunicationMessageLabel extends GmDefaultModelElementLabel {
    @objid ("7a469b23-55b6-11e2-877f-002564c97630")
    private CommunicationMessage element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7a469b26-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("7a469b29-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("7a469b2b-55b6-11e2-877f-002564c97630")
    public  GmCommunicationMessageLabel() {
        
    }

    /**
     * Constructor.
     * @param diagram The diagram
     * @param el The represented element, may be null.
     * @param ref The represented element reference, may not be null.
     */
    @objid ("7a469b2e-55b6-11e2-877f-002564c97630")
    public  GmCommunicationMessageLabel(final IGmDiagram diagram, final CommunicationMessage el, final MRef ref) {
        super(diagram, ref);
        this.element = el;
        
        init();
        
    }

    @objid ("7a469b3d-55b6-11e2-877f-002564c97630")
    @Override
    public List<Stereotype> filterStereotypes(final List<Stereotype> stereotypes) {
        return stereotypes;
    }

    @objid ("7a4821a5-55b6-11e2-877f-002564c97630")
    @Override
    public List<TaggedValue> filterTags(final List<TaggedValue> taggedValues) {
        return taggedValues;
    }

    @objid ("7a4821b4-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return new IEditableText() {
        
                    @Override
                    public String getText() {
                        return getRelatedElement().getName();
                    }
        
                    @Override
                    public void setText(String text) {
                        getRelatedElement().setName(text);
                    }
        
                };
        
    }

    @objid ("7a4821bb-55b6-11e2-877f-002564c97630")
    @Override
    public CommunicationMessage getRelatedElement() {
        return this.element;
    }

    @objid ("7a4821c2-55b6-11e2-877f-002564c97630")
    @Override
    public CommunicationMessage getRepresentedElement() {
        return this.element;
    }

    @objid ("7a4821dd-55b6-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCommunicationMessageLabel.");
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

    @objid ("7a49a83e-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        return computeSignature(getRelatedElement());
    }

    @objid ("7a49a843-55b6-11e2-877f-002564c97630")
    @Override
    protected void setParent(final GmCompositeNode parent) {
        if (getParent() != parent) {
            super.setParent(parent);
        
            if (parent != null)
                getPersistedStyle().setCascadedStyle(parent.getPersistedStyle());
        }
        
    }

    @objid ("7a49a84a-55b6-11e2-877f-002564c97630")
    private String computeSignature(final CommunicationMessage att) {
        StringBuilder ret = new StringBuilder();
        
        if (!att.getSequence().isEmpty()) {
            ret.append(att.getSequence());
            ret.append(": ");
        }
        
        if (att.getInvoked() != null) {
            ret.append(att.getInvoked().getName());
            ret.append(" (");
            if (!att.getArgument().isEmpty()) {
                ret.append(att.getArgument());
            }
            ret.append(")");
        } else if (att.getSignalSignature() != null) {
            ret.append(att.getSignalSignature().getName());
        } else {
            ret.append(att.getName());
        }
        return ret.toString();
    }

    @objid ("7a49a852-55b6-11e2-877f-002564c97630")
    private void init() {
        setShowMetaclassKeyword(false);
        setShowMetaclassIcon(false);
        
    }

    @objid ("7a49a854-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCommunicationMessageLabel.", GmCommunicationMessageLabel.MINOR_VERSION);
        
    }

    @objid ("7a49a85a-55b6-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.element = (CommunicationMessage) resolveRef(this.getRepresentedRef());
        
    }

    @objid ("7a49a860-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
