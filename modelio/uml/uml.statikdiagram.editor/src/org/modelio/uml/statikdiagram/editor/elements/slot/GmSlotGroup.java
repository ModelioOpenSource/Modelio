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
package org.modelio.uml.statikdiagram.editor.elements.slot;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.AttributeLink;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.uml.statikdiagram.editor.elements.instance.GmInstanceStructuredStyleKeys;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Slot group model that displays {@link AttributeLink}.
 * 
 * @author cmarin
 */
@objid ("369f28b4-55b7-11e2-877f-002564c97630")
public class GmSlotGroup extends GmGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("369f28b8-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("369f28bb-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("369f28bd-55b7-11e2-877f-002564c97630")
    public  GmSlotGroup() {
        
    }

    /**
     * Creates an attribute group.
     * @param diagram The diagram.
     * @param relatedRef The related element reference, must not be null.
     */
    @objid ("36a0af19-55b7-11e2-877f-002564c97630")
    public  GmSlotGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * The attribute group only support {@link GmSlot} nodes.
     */
    @objid ("36a0af22-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmSlot.class.isAssignableFrom(nodeClass);
    }

    /**
     * Only attributes can be created here.
     */
    @objid ("36a0af2b-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return AttributeLink.class.isAssignableFrom(type);
    }

    @objid ("36a0af34-55b7-11e2-877f-002564c97630")
    @Override
    public Instance getRelatedElement() {
        return (Instance) super.getRelatedElement();
    }

    /**
     * The inner class zone can be visible only if the {@link GmInstanceStructuredStyleKeys#SLOTGROUPVISIBLE} property
     * is true.
     */
    @objid ("36a0af3b-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        if (getParent() != null && getParent().getRepresentationMode() == RepresentationMode.STRUCTURED) {
            return getDisplayedStyle().getProperty(GmInstanceStructuredStyleKeys.SLOTGROUPVISIBLE);
        } else {
            return false;
        }
        
    }

    @objid ("36a0af46-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final Instance instance = getRelatedElement();
        if (instance != null && instance.isValid()) {
            // Unmask missing children
            for (AttributeLink part : instance.getSlot()) {
                if (getChild(new MRef(part)) == null)
                    getDiagram().unmask(this, part, null);
            }
        }
        
    }

    @objid ("36a0af49-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == GmInstanceStructuredStyleKeys.SLOTGROUPVISIBLE) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
        
    }

    @objid ("36a0af50-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        refreshFromObModel();
        fireVisibilityChanged();
        super.styleChanged(style);
        
    }

    @objid ("36a235b9-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            getParent().getDisplayedStyle().setProperty(getStyleKey(MetaKey.REPMODE), RepresentationMode.STRUCTURED);
        }
        getDisplayedStyle().setProperty(GmInstanceStructuredStyleKeys.SLOTGROUPVISIBLE, visible);
        
    }

    /**
     * Checks whether the given model element can be and still be displayed here.
     * <p>
     * Check all conditions except the case where it is already unmasked.
     * @param el The element to unmask
     * @return true if it satisfies all conditions, else false.
     */
    @objid ("36a235bd-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Cannot unmask anything else than a valid attribute link
        if (!(el instanceof AttributeLink) || !el.isValid())
            return false;
        
        // Cannot unmask a foreign attribute (not belonging to the class)
        return (el.getCompositionOwner().equals(this.getRelatedElement()));
    }

    @objid ("36a235c6-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        final Instance classifier = getRelatedElement();
        if (classifier != null && classifier.isValid()) {
            boolean hasHiddenFeature = false;
        
            hasHiddenFeature = classifier.getSlot().size() != getChildren().size();
        
            setHiddenFeature(hasHiddenFeature);
        
        }
        
    }

    @objid ("36a235c9-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmSlotGroup.");
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

    @objid ("36a235cf-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmSlotGroup.", GmSlotGroup.MINOR_VERSION);
        
    }

    @objid ("36a235d5-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("36a235da-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
