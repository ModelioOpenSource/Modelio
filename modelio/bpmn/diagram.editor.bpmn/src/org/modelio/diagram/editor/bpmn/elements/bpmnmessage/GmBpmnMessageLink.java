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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow.GmBpmnSequenceFlow;
import org.modelio.diagram.elements.common.linkednode.IGmNodeLink;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the link between a {@link GmBpmnSequenceFlow} and a {@link GmBpmnMessage}.
 */
@objid ("61623b0d-55b6-11e2-877f-002564c97630")
public class GmBpmnMessageLink extends GmLink implements IGmNodeLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("61623b13-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("61623b16-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor that must be used for deserialization only.
     */
    @objid ("61623b18-55b6-11e2-877f-002564c97630")
    public GmBpmnMessageLink() {
        // Nothing to do.
    }

    /**
     * Creates a new GmNoteLink
     * 
     * @param diagram The diagram containing the link.
     * @param relatedRef a reference to the represented Note.
     */
    @objid ("6163c179-55b6-11e2-877f-002564c97630")
    public GmBpmnMessageLink(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("6163c184-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnMessage getRelatedElement() {
        final IGmLinkable lfrom = getFrom();
        
        assert (lfrom != this);
        
        if (lfrom != null && lfrom.getRelatedElement() instanceof BpmnMessage) {
            return (BpmnMessage) lfrom.getRelatedElement();
        }
        
        final IGmLinkable lto = getTo();
        
        assert (lto != this);
        
        if (lto != null && lto.getRelatedElement() instanceof BpmnMessage) {
            return (BpmnMessage) lto.getRelatedElement();
        }
        return null;
    }

    @objid ("6163c18a-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return GmBpmnMessage.STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("6163c195-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmBpmnMessage.STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("6163c19e-55b6-11e2-877f-002564c97630")
    @Override
    protected IStyle createStyle(final IGmDiagram diagram) {
        return new ProxyStyle(diagram.getPersistedStyle());
    }

    @objid ("6163c1a9-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnMessage getFromElement() {
        return getRelatedElement();
    }

    @objid ("6163c1b0-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnMessageFlow getToElement() {
        final BpmnMessage relatedElement = getRelatedElement();
        
        if (relatedElement != null && !relatedElement.getMessageFlow().isEmpty()) {
            return relatedElement.getMessageFlow().get(0);
        }
        return null;
    }

    /**
     * Updates the proxy style to point to the given node style.
     * 
     * @param ref the reference node, may be null.
     */
    @objid ("6163c1b7-55b6-11e2-877f-002564c97630")
    private void refreshStyle(final GmAbstractObject ref) {
        // Modify the style
        if (ref != null) {
            getPersistedStyle().setCascadedStyle(ref.getPersistedStyle());
        } else {
            getPersistedStyle().setCascadedStyle(getDiagram().getPersistedStyle());
        }
    }

    @objid ("6165481b-55b6-11e2-877f-002564c97630")
    @Override
    public void setTo(final IGmLinkable to) {
        super.setTo(to);
        if (to instanceof GmAbstractObject) {
            refreshStyle((GmAbstractObject) to);
        }
    }

    @objid ("61654822-55b6-11e2-877f-002564c97630")
    @Override
    protected void readLink(final IDiagramReader in) {
        super.readLink(in);
        if (getTo() instanceof GmAbstractObject) {
            refreshStyle((GmAbstractObject) getTo());
        }
    }

    @objid ("61654829-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnMessageLink.", GmBpmnMessageLink.MINOR_VERSION);
    }

    @objid ("6165482f-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnMessageLink.MAJOR_VERSION;
    }

    @objid ("92fe8e1e-1313-4545-8721-646e9d664d81")
    @Override
    protected void read_GmLinkV0_roles() {
        // no extension
    }

}
