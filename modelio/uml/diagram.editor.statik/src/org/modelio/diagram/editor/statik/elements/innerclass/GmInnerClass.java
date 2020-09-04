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

package org.modelio.diagram.editor.statik.elements.innerclass;

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
 * Encapsulation of the duet Inner Zone/Group.
 */
@objid ("97037a5b-55b6-11e2-877f-002564c97630")
public final class GmInnerClass extends GmNoStyleCompositeNode implements PropertyChangeListener {
    @objid ("3520299c-55b7-11e2-877f-002564c97630")
    private static final String ZONE = "Zone";

    @objid ("3520299e-55b7-11e2-877f-002564c97630")
    private static final String GROUP = "Group";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("352029a8-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("352029ab-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Inner classes list group
     */
    @objid ("a5955faa-55c2-11e2-9337-002564c97630")
    private GmGroup innerGroup;

    /**
     * Inner classes zone
     */
    @objid ("a5955fac-55c2-11e2-9337-002564c97630")
    private GmFreeZone innerZone;

    /**
     * Creates an inner classes group.
     * @param gmDiagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("352029ad-55b7-11e2-877f-002564c97630")
    public GmInnerClass(final IGmDiagram gmDiagram, final MRef relatedRef) {
        super(gmDiagram, relatedRef);
        
        this.innerZone = new GmInnerClassesZone(gmDiagram, relatedRef);
        this.innerZone.setRoleInComposition(ZONE);
        // Register as a property change listener for content synchronization in MANUAL unmask mode. 
        this.innerZone.addPropertyChangeListener(this);
        
        this.innerGroup = new GmInnerClassGroup(gmDiagram, relatedRef);
        this.innerGroup.setRoleInComposition(GROUP);
        // Register as a property change listener for content synchronization in MANUAL unmask mode.
        this.innerGroup.addPropertyChangeListener(this);
        
        super.addChild(this.innerGroup);
        super.addChild(this.innerZone);
    }

    /**
     * Creates an inner classes group from existing zone & group.
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     * @param zone an existing InnerClass zone.
     * @param group an existing InnerClass group.
     */
    @objid ("3521b042-55b7-11e2-877f-002564c97630")
    public GmInnerClass(final IGmDiagram diagram, final MRef relatedRef, final GmFreeZone zone, final GmGroup group) {
        super(diagram, relatedRef);
        
        this.innerZone = zone;
        this.innerZone.setRoleInComposition(ZONE);
        
        this.innerGroup = group;
        this.innerGroup.setRoleInComposition(GROUP);
        
        super.addChild(this.innerGroup);
        super.addChild(this.innerZone);
        
        // Register as a property change listener for content synchronization. 
        this.innerZone.addPropertyChangeListener(this);
        this.innerGroup.addPropertyChangeListener(this);
    }

    /**
     * This group can contain only labels.
     */
    @objid ("3521b055-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(final Class<? extends GmNodeModel> nodeClass) {
        return false;
    }

    @objid ("3521b05f-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("3521b068-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInnerClass.");
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

    @objid ("3521b06f-55b7-11e2-877f-002564c97630")
    @Override
    public void write(final IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInnerClass.", GmInnerClass.MINOR_VERSION);
    }

    @objid ("3521b076-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.innerZone = (GmFreeZone) getFirstChild(ZONE);
        this.innerZone.addPropertyChangeListener(this);
        
        this.innerGroup = (GmGroup) getFirstChild(GROUP);
        this.innerGroup.addPropertyChangeListener(this);
    }

    @objid ("352336d9-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        if (getDisplayedStyle().getProperty(getStyleKey(MetaKey.InnerGroup.INNERVIEWMODE)) == InternalsViewMode.DIAGRAM) {
            return this.innerZone;
        } else {
            return this.innerGroup;
        }
    }

    @objid ("352336e3-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        if (getDisplayedStyle().getProperty(getStyleKey(MetaKey.InnerGroup.INNERVIEWMODE)) == InternalsViewMode.DIAGRAM) {
            return this.innerZone.canUnmask(el);
        } else {
            return this.innerGroup.canUnmask(el);
        }
    }

    @objid ("352336eb-55b7-11e2-877f-002564c97630")
    @Override
    public void addChild(final GmNodeModel child) {
        super.addChild(child);
    }

    @objid ("352336f2-55b7-11e2-877f-002564c97630")
    @Override
    public void removeChild(final GmNodeModel child) {
        super.removeChild(child);
        if (child == this.innerGroup || child == this.innerZone) {
            child.removePropertyChangeListener(this);
        }
    }

    /**
     * Synchronize innerGroup and innerZone contents when INNERUNMASKFILTER is set to MANUAL.
     */
    @objid ("352336f9-55b7-11e2-877f-002564c97630")
    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        // Only synchronize contents in MANUAL mode for "Children" changes
        if (IGmObject.PROPERTY_CHILDREN.equals(evt.getPropertyName()) && isManualUnmask()) {
            if (evt.getSource() == this.innerGroup && this.innerGroup.isVisible()) {
                // InnerGroup -> InnerZone
                synchronizeChildren(this.innerGroup, this.innerZone);
            } else if (evt.getSource() == this.innerZone && this.innerZone.isVisible()) {
                // InnerZone -> InnerGroup
                synchronizeChildren(this.innerZone, this.innerGroup);
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
     * @param source the gm having the right children.
     * @param target the gm that might have unwanted/missing children.
     */
    @objid ("352336ff-55b7-11e2-877f-002564c97630")
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
                    child.delete();
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
     * Check if the INNERUNMASKFILTER style key is set to MANUAL.
     */
    @objid ("3523370a-55b7-11e2-877f-002564c97630")
    private boolean isManualUnmask() {
        StyleKey styleKey = getStyleKey(MetaKey.VISIBILITYFILTER);
        if (styleKey != null) {
            final StyleKey.UmaskByVisibilityStragegy mode = getDisplayedStyle().getProperty(styleKey);
            return mode == StyleKey.UmaskByVisibilityStragegy.MANUAL;
        } else {
            return true;
        }
    }

    @objid ("3523370e-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("35233713-55b7-11e2-877f-002564c97630")
    public GmInnerClass() {
    }

    /**
     * The inner class group can be visible only if the
     * {@link org.modelio.diagram.styles.core.MetaKey.InnerGroup#INNERVIEWMODE} property is
     * {@link InternalsViewMode#LIST}.
     */
    @objid ("35233716-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        if (getParent() != null && getParent().getRepresentationMode() == RepresentationMode.STRUCTURED) {
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
     * Get the style key for the {@link MetaKey.InnerGroup#INNERVIEWMODE} meta key.
     */
    @objid ("3524bd7e-55b7-11e2-877f-002564c97630")
    private StyleKey getViewModeStyleKey() {
        return getStyleKey(MetaKey.InnerGroup.INNERVIEWMODE);
    }

    @objid ("3524bd85-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(final boolean visible) {
        if (visible) {
            getParent().getDisplayedStyle().setProperty(getStyleKey(MetaKey.REPMODE), RepresentationMode.STRUCTURED);
        }
    }

    @objid ("3524bd8a-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        // The visibility may have changed so fires a notification.
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("3524bd90-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getViewModeStyleKey()) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
    }

}
