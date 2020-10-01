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

package org.modelio.uml.activitydiagram.editor.elements.interruptible;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.InterruptibleActivityRegion;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a Interruptible.
 */
@objid ("2ab24a7a-55b6-11e2-877f-002564c97630")
public class GmInterruptible extends GmCompositeNode {
    @objid ("2ab24a80-55b6-11e2-877f-002564c97630")
     InterruptibleActivityRegion element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2ab24a83-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2ab24a86-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("30f74e86-58a2-11e2-9574-002564c97630")
     static final GmInterruptibleStructuredStyleKeys STRUCTURED_KEYS = new GmInterruptibleStructuredStyleKeys();

    /**
     * Initializes an Interruptible region.
     * 
     * @param diagram The diagram owning the node.
     * @param theInterruptible The represented element,may be null .
     * @param ref The represented element reference, may not be null.
     */
    @objid ("2ab24a88-55b6-11e2-877f-002564c97630")
    public GmInterruptible(IGmDiagram diagram, InterruptibleActivityRegion theInterruptible, MRef ref) {
        super(diagram, ref);
        this.element = theInterruptible;
    }

    /**
     * For deserialization only.
     */
    @objid ("2ab3d11f-55b6-11e2-877f-002564c97630")
    public GmInterruptible() {
        // serialization
    }

    @objid ("2ab3d122-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("2ab3d12a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        // nothing can be unmasked
        return false;
    }

    @objid ("2ab3d132-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        // nothing can be unmasked
        return null;
    }

    @objid ("2ab3d13c-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return new IEditableText() {
        
                    @Override
                    public String getText() {
                        return GmInterruptible.this.element.getName();
                    }
        
                    @Override
                    public void setText(String text) {
                        GmInterruptible.this.element.setName(text);
                    }
        
                };
    }

    @objid ("2ab3d143-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2ab3d14a-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("2ab3d151-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2ab3d158-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("2ab557bd-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("2ab557c6-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInterruptible.");
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

    @objid ("2ab557cc-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInterruptible.", GmInterruptible.MINOR_VERSION);
    }

    @objid ("2ab557d2-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (InterruptibleActivityRegion) resolveRef(this.getRepresentedRef());
    }

    @objid ("2ab557d7-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
