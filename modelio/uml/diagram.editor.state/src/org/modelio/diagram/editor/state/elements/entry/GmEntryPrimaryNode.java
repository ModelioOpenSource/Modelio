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

package org.modelio.diagram.editor.state.elements.entry;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.stateMachineModel.EntryPointPseudoState;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a Entry.
 */
@objid ("f5118afa-55b6-11e2-877f-002564c97630")
public class GmEntryPrimaryNode extends GmNoStyleSimpleNode implements IImageableNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f513115d-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f5131160-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the element is unmasked.
     * @param relatedRef related element reference, must not be null.
     */
    @objid ("f5131162-55b6-11e2-877f-002564c97630")
    public GmEntryPrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("f513116b-55b6-11e2-877f-002564c97630")
    @Override
    public EntryPointPseudoState getRelatedElement() {
        return (EntryPointPseudoState) super.getRelatedElement();
    }

    @objid ("f5131172-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        if (getRelatedElement() != null) {
            firePropertyChange(PROPERTY_LABEL, null, getRelatedElement().getName());
        }
        // forcing visual refresh in case Image changed 
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("f5131175-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return true;
    }

    @objid ("f513117d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("f5131185-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return (RepresentationMode) getDisplayedStyle().getProperty(GmEntry.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE));
    }

    @objid ("f513118c-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("f5131191-55b6-11e2-877f-002564c97630")
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
     * Deserialization constructor.
     */
    @objid ("f5131198-55b6-11e2-877f-002564c97630")
    public GmEntryPrimaryNode() {
        // empty for the serialization
    }

    @objid ("f513119b-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmEntryPrimaryNode.");
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

    @objid ("f51497fc-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmEntryPrimaryNode.", GmEntryPrimaryNode.MINOR_VERSION);
    }

    @objid ("f5149802-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("f5149807-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
