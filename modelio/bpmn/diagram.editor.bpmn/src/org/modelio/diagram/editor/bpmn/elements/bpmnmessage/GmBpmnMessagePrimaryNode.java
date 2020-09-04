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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.flows.BpmnMessage;
import org.modelio.metamodel.bpmn.flows.BpmnMessageFlow;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link BpmnMessage}.
 */
@objid ("61654843-55b6-11e2-877f-002564c97630")
public final class GmBpmnMessagePrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("61654849-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("6165484c-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Create a initial graphic node.
     * 
     * @param diagram The diagram
     * @param relatedRef The related element reference, may not be null.
     */
    @objid ("6165484e-55b6-11e2-877f-002564c97630")
    public GmBpmnMessagePrimaryNode(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("61654859-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnMessage getRelatedElement() {
        return (BpmnMessage) super.getRelatedElement();
    }

    @objid ("61654860-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        BpmnMessage relatedIElement = getRelatedElement();
        if (relatedIElement == null || !relatedIElement.isValid()) {
            return;
        }
        
        firePropertyChange(IGmObject.PROPERTY_LABEL, null, relatedIElement.getName());
        
        // forcing visual refresh in case Image changed
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, getLayoutData());
        
        refreshMessageLinks();
    }

    @objid ("61654863-55b6-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(final MObject movedEl) {
        refreshFromObModel();
    }

    @objid ("6166cebe-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return true;
    }

    @objid ("6166cec7-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    /**
     * Get the parent model representation mode.
     * 
     * @return the parent representation mode or null if the node has still no parent.
     */
    @objid ("6166ced0-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmBpmnMessage.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("6166ced8-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("6166cedd-55b6-11e2-877f-002564c97630")
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

    /**
     * Constructor for deserialization only.
     */
    @objid ("6166cee4-55b6-11e2-877f-002564c97630")
    public GmBpmnMessagePrimaryNode() {
        // for the serialization
    }

    @objid ("6166cee7-55b6-11e2-877f-002564c97630")
    List<Image> getRepresentedIcon() {
        Boolean showrepresented = getDisplayedStyle().getProperty(GmBpmnMessageStructuredStyleKeys.SHOWREPRESENTED);
        List<Image> res = new ArrayList<>();
        if (showrepresented) {
            ModelElement type = Represents.getTarget(getRelatedElement());
            ModelElement state = State.getTarget(getRelatedElement());
            if (type != null) {
                res.add(ElementImageService.getIcon(type));
            } else if (state != null) {
                res.add(ElementImageService.getIcon(state));
            } else if (getRelatedElement().getItemRef() != null) {
                BpmnItemDefinition item = getRelatedElement().getItemRef();
                ModelElement ref = Reference.getTarget(item);
                if (ref != null) {
                    res.add(ElementImageService.getIcon(ref));
                } else {
                    res.add(ElementImageService.getIcon(item));
                }
            }
        }
        return res;
    }

    @objid ("6166ceed-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnMessagePrimaryNode.");
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

    @objid ("6166cef3-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnMessagePrimaryNode.", GmBpmnMessagePrimaryNode.MINOR_VERSION);
    }

    @objid ("6168555c-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("61685561-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnMessagePrimaryNode.MAJOR_VERSION;
    }

    /**
     * Refresh the links between this BpmnMessage and its related BpmnMessageFlow.
     * <p>
     * All unnecessary links are deleted, and missing links are unmasked.
     * </p>
     */
    @objid ("762597b9-6b66-4f41-a191-9107103fcf16")
    private void refreshMessageLinks() {
        BpmnMessage relatedElement = getRelatedElement();
        if (relatedElement == null || !relatedElement.isValid()) {
            return;
        }
        
        List<BpmnMessageFlow> messageFlows = new ArrayList<>(relatedElement.getMessageFlow());
        
        // Start by scanning existing links
        for (IGmLink link : new ArrayList<>(getStartingLinks())) {
            if (link instanceof GmBpmnMessageLink) {
                MObject toElement = link.getToElement();
                if (messageFlows.contains(toElement)) {
                    // link to message flow found, remove it from the list
                    messageFlows.remove(toElement);
                } else {
                    // destroying unnecessary link
                    link.delete();
                }
            }
        }
        
        // unmask remaining links.
        for (BpmnMessageFlow messageFlow : messageFlows) {
            Collection<GmModel> models = getDiagram().getAllGMRelatedTo(new MRef(messageFlow));
            for (GmModel model : models) {
                if (model instanceof GmLink || ((GmNodeModel) model).isVisible()) {
                    // fire property change to force creation of missing link by edit part.
                    firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, null, messageFlow);
                    break;
                }
            }
        }
    }

    @objid ("615da71f-55b6-11e2-877f-002564c97630")
    @Override
    public void removeStartingLink(final IGmLink gmLink) {
        boolean selfDelete = false;
        if (getRelatedElement() != null && getRelatedElement().equals(gmLink.getRelatedElement())) {
            // the removed link represents the same element (the bpmn message) and is the last link.
            selfDelete = getStartingLinks().size() == 1;
        }
        super.removeStartingLink(gmLink);
        if (selfDelete) {
            // last link is gone, delete the message itself.
            getParent().delete();
        }
    }

}
