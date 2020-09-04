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

package org.modelio.diagram.editor.statik.elements.instanceinternalstructure;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Encapsulation of the duet InternalStructure Zone/Group for Instances.
 */
@objid ("354c69ba-55b7-11e2-877f-002564c97630")
public final class GmInstanceInternalStructure extends GmNoStyleCompositeNode implements PropertyChangeListener {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("354c69c6-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("354c69c9-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("354c69cb-55b7-11e2-877f-002564c97630")
    private static final String ZONE = "Zone";

    @objid ("354c69cd-55b7-11e2-877f-002564c97630")
    private static final String GROUP = "Group";

    /**
     * InternalStructure classes list group
     */
    @objid ("a69f2e8a-55c2-11e2-9337-002564c97630")
    private GmGroup InternalGroup;

    /**
     * InternalStructure classes zone
     */
    @objid ("a69f2e8c-55c2-11e2-9337-002564c97630")
    private GmFreeZone InternalStructureZone;

    /**
     * Creates an InternalStructure classes group.
     * 
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("354c69cf-55b7-11e2-877f-002564c97630")
    public GmInstanceInternalStructure(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
        
        this.InternalStructureZone = new GmInstanceInternalStructureZone(diagram, relatedRef);
        this.InternalStructureZone.setRoleInComposition(ZONE);
        // Register as a property change listener for content synchronization in MANUAL unmask mode.
        this.InternalStructureZone.addPropertyChangeListener(this);
        
        this.InternalGroup = new GmInstanceInternalStructureGroup(diagram, relatedRef);
        this.InternalGroup.setRoleInComposition(GROUP);
        // Register as a property change listener for content synchronization in MANUAL unmask mode.
        this.InternalGroup.addPropertyChangeListener(this);
        
        super.addChild(this.InternalGroup);
        super.addChild(this.InternalStructureZone);
    }

    /**
     * Creates an InternalStructure classes group from existing zone & group.
     * 
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     * @param zone an existing InternalStructure zone.
     * @param group an existing InternalStructure group.
     */
    @objid ("354c69da-55b7-11e2-877f-002564c97630")
    public GmInstanceInternalStructure(final IGmDiagram diagram, final MRef relatedRef, final GmFreeZone zone, final GmGroup group) {
        super(diagram, relatedRef);
        
        this.InternalStructureZone = zone;
        this.InternalStructureZone.setRoleInComposition(ZONE);
        
        this.InternalGroup = group;
        this.InternalGroup.setRoleInComposition(GROUP);
        
        super.addChild(this.InternalGroup);
        super.addChild(this.InternalStructureZone);
        
        // Register as a property change listener for content synchronization.
        this.InternalStructureZone.addPropertyChangeListener(this);
        this.InternalGroup.addPropertyChangeListener(this);
    }

    /**
     * This group can contain only labels.
     */
    @objid ("354c69ed-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(final Class<? extends GmNodeModel> nodeClass) {
        return false;
    }

    @objid ("354df059-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("354df062-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInternalStructure.");
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

    @objid ("354df069-55b7-11e2-877f-002564c97630")
    @Override
    public void write(final IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInternalStructure.", GmInstanceInternalStructure.MINOR_VERSION);
    }

    @objid ("354df070-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.InternalStructureZone = (GmFreeZone) getFirstChild(ZONE);
        this.InternalStructureZone.addPropertyChangeListener(this);
        
        this.InternalGroup = (GmGroup) getFirstChild(GROUP);
        this.InternalGroup.addPropertyChangeListener(this);
    }

    @objid ("354df076-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        StyleKey styleKey = getStyleKey(MetaKey.InnerGroup.INNERVIEWMODE);
        if (getDisplayedStyle().getProperty(styleKey) == InternalsViewMode.DIAGRAM) {
            return this.InternalStructureZone;
        } else {
            return this.InternalGroup;
        }
    }

    @objid ("354df080-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        StyleKey styleKey = getStyleKey(MetaKey.InnerGroup.INNERVIEWMODE);
        if (getDisplayedStyle().getProperty(styleKey) == InternalsViewMode.DIAGRAM) {
            return this.InternalStructureZone.canUnmask(el);
        } else {
            return this.InternalGroup.canUnmask(el);
        }
    }

    @objid ("354df088-55b7-11e2-877f-002564c97630")
    @Override
    public void addChild(final GmNodeModel child) {
        super.addChild(child);
    }

    @objid ("354df08f-55b7-11e2-877f-002564c97630")
    @Override
    public void removeChild(final GmNodeModel child) {
        super.removeChild(child);
        if (child == this.InternalGroup || child == this.InternalStructureZone) {
            child.removePropertyChangeListener(this);
        }
    }

    /**
     * Synchronize InternalGroup and InternalStructureZone contents when InternalStructureUNMASKFILTER is set to MANUAL.
     */
    @objid ("354df096-55b7-11e2-877f-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        // Only synchronize contents in MANUAL mode for "Children" changes
        if (IGmObject.PROPERTY_CHILDREN.equals(evt.getPropertyName()) && isManualUnmask()) {
            if (evt.getSource() == this.InternalGroup && this.InternalGroup.isVisible()) {
                // InternalGroup -> InternalStructureZone
                synchronizeChildren(this.InternalGroup, this.InternalStructureZone);
            } else if (evt.getSource() == this.InternalStructureZone && this.InternalStructureZone.isVisible()) {
                // InternalStructureZone -> InternalGroup
                synchronizeChildren(this.InternalStructureZone, this.InternalGroup);
            } else {
                // Both group & zone are not visible.
                return;
            }
        }
    }

    /**
     * Synchronize target's children according to the source's children.
     * Unwanted children are all masked.
     * Missing children are unmasked.
     * 
     * @param source the gm having the right children.
     * @param target the gm that might have unwanted/missing children.
     */
    @objid ("354f76f9-55b7-11e2-877f-002564c97630")
    private void synchronizeChildren(final GmCompositeNode source, final GmCompositeNode target) {
        // Get elements unmasked in source
        Set<MObject> sourceChildren = new HashSet<>();
        for (GmNodeModel child : source.getChildren()) {
            MObject representedIElement = child.getRepresentedElement();
            if (representedIElement != null && representedIElement.isValid()) {
                sourceChildren.add(representedIElement);
            }
        }
        
        // - Get elements unmasked in both source & target
        // - Remove unwanted gms
        Set<MObject> targetChildren = new HashSet<>();
        for (GmNodeModel child : target.getChildren()) {
            MObject representedIElement = child.getRepresentedElement();
            if (representedIElement != null && representedIElement.isValid()) {
                if (sourceChildren.contains(representedIElement)) {
                    targetChildren.add(representedIElement);
                } else {
                    // Remove unwanted gm
                    target.removeChild(child);
                }
            }
        }
        
        // Get all elements to unmask in target
        Set<MObject> toUnmask = new HashSet<>(sourceChildren);
        toUnmask.removeAll(targetChildren);
        
        // Unmask them
        // TODO get a better layout
        int size = target.getChildren().size();
        final Rectangle constraint = new Rectangle(10 * size + 5, 10 * size + 5, -1, -1);
        
        for (MObject elt : toUnmask) {
            getDiagram().unmask(target, elt, constraint.getCopy());
            constraint.translate(10, 10);
        }
    }

    /**
     * Check if the InternalGroup.INTAUTOUNMASK style key is set to false.
     */
    @objid ("354f7704-55b7-11e2-877f-002564c97630")
    private boolean isManualUnmask() {
        StyleKey styleKey = getStyleKey(MetaKey.InternalGroup.INTAUTOUNMASK);
        if (styleKey != null) {
            boolean autoUnmask = this.getDisplayedStyle().getProperty(styleKey);
            return autoUnmask == false;
        } else {
            return true;
        }
    }

    @objid ("354f7708-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("354f770d-55b7-11e2-877f-002564c97630")
    public GmInstanceInternalStructure() {
    }

    /**
     * The InternalStructure class group can be visible only if the
     * {@link org.modelio.diagram.styles.core.MetaKey.InternalGroup#INTVIEWMODE} property is
     * {@link InternalsViewMode#LIST}.
     */
    @objid ("354f7710-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        GmCompositeNode parentNode = getParentNode();
        if (parentNode != null && parentNode.isVisible() && parentNode.getRepresentationMode() == RepresentationMode.STRUCTURED) {
            StyleKey viewModeStyleKey = getViewModeStyleKey();
            if (viewModeStyleKey == null) {
                return false;
            }
            InternalsViewMode v = getDisplayedStyle().getProperty(viewModeStyleKey);
            return (v != InternalsViewMode.NONE);
        }
        return false;
    }

    /**
     * Get the style key for the {@link MetaKey.InternalGroup#INTVIEWMODE} meta key.
     */
    @objid ("354f7716-55b7-11e2-877f-002564c97630")
    private StyleKey getViewModeStyleKey() {
        return getStyleKey(MetaKey.InnerGroup.INNERVIEWMODE);
    }

    @objid ("354f771d-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(final boolean visible) {
        if (visible) {
            getParent().getDisplayedStyle().setProperty(getStyleKey(MetaKey.REPMODE), RepresentationMode.STRUCTURED);
        }
    }

    @objid ("d1c60a64-7fe3-4b21-8694-b5683329a628")
    @Override
    public void styleChanged(IStyle style) {
        // The visibility may have changed so fires a notification.
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("9d70ec13-4cee-41bb-bd3a-eebe2c4cbde1")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getViewModeStyleKey()) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
    }

}
