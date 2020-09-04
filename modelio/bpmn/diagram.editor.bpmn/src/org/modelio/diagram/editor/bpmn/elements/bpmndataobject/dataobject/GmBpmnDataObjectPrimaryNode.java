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

package org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataobject;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.GmBpmnDataObjectStyleKeys;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datastore.GmBpmnDataStore;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.objects.BpmnDataObject;
import org.modelio.metamodel.bpmn.objects.BpmnItemDefinition;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Reference;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.Represents;
import org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink.State;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link BpmnDataObject}.
 */
@objid ("60bef614-55b6-11e2-877f-002564c97630")
public final class GmBpmnDataObjectPrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("60bef61a-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("60bef61d-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Create a initial graphic node.
     * 
     * @param diagram The diagram
     * @param relatedRef The related element reference, may not be null.
     */
    @objid ("60bef61f-55b6-11e2-877f-002564c97630")
    public GmBpmnDataObjectPrimaryNode(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("60bef62a-55b6-11e2-877f-002564c97630")
    @Override
    public BpmnDataObject getRelatedElement() {
        return (BpmnDataObject) super.getRelatedElement();
    }

    @objid ("60bef631-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        if (getRelatedElement() != null) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, null, getRelatedElement().getName());
        }
    }

    @objid ("60bef634-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return true;
    }

    @objid ("60bef63d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    /**
     * Get the parent model representation mode.
     * 
     * @return the parent representation mode or null if the node has still no parent.
     */
    @objid ("60c07c9f-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmBpmnDataStore.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("60c07ca7-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("60c07cac-55b6-11e2-877f-002564c97630")
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
    @objid ("60c07cb3-55b6-11e2-877f-002564c97630")
    public GmBpmnDataObjectPrimaryNode() {
        // for the serialization
    }

    @objid ("60c07cb6-55b6-11e2-877f-002564c97630")
    public List<Image> getReferenceIcone() {
        Boolean showrepresented = getDisplayedStyle().getProperty(GmBpmnDataObjectStyleKeys.SHOWREPRESENTED);
        List<Image> res = new ArrayList<>();
        if (showrepresented) {
            ModelElement represented = Represents.getTarget(getRelatedElement());
            ModelElement state = State.getTarget(getRelatedElement());
        
            if (represented != null) {
                res.add(ElementImageService.getIcon(represented));
            } else if (state != null) {
                res.add(ElementImageService.getIcon(state));
            } else if (getRelatedElement().getItemSubjectRef() != null) {
                BpmnItemDefinition item = getRelatedElement().getItemSubjectRef();
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

    @objid ("60c07cbc-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnDataObjectPrimaryNode.");
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

    @objid ("60c07cc2-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnDataObjectPrimaryNode.", GmBpmnDataObjectPrimaryNode.MINOR_VERSION);
    }

    @objid ("60c07cc8-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("60c07ccd-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmBpmnDataObjectPrimaryNode.MAJOR_VERSION;
    }

}
