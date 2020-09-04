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

/*
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
package org.modelio.diagram.editor.statik.elements.internalstructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the internal structure zone.
 */
@objid ("359113fe-55b7-11e2-877f-002564c97630")
public final class GmInternalStructureZone extends GmFreeZone {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35911402-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35911405-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates the zone
     * 
     * @param diagram The diagram
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("35911407-55b7-11e2-877f-002564c97630")
    public GmInternalStructureZone(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("35911410-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        if (!el.isValid() || !canCreate(el.getClass()))
            return false;
        
        // Cannot unmask a instance class (not belonging to the class)
        return (el.getCompositionOwner().equals(this.getRelatedElement()));
    }

    /**
     * The internal structure zone can be visible only if the
     * {@link org.modelio.diagram.styles.core.MetaKey.InternalGroup#INTVIEWMODE} property is
     * {@link InternalsViewMode#DIAGRAM}.
     */
    @objid ("35911418-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        GmCompositeNode parent = getParentNode();
        if (parent != null && parent.isVisible() && parent.getRepresentationMode() == RepresentationMode.STRUCTURED) {
            StyleKey viewModeStyleKey = getViewModeStyleKey();
            if (viewModeStyleKey == null) {
                return false;
            }
            InternalsViewMode v = getDisplayedStyle().getProperty(viewModeStyleKey);
            return (v == InternalsViewMode.DIAGRAM);
        }
        return false;
    }

    @objid ("3591141e-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        // The visibility may have changed so fires a notification.
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("35911424-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getViewModeStyleKey())
            fireVisibilityChanged();
        else
            super.styleChanged(property, newValue);
    }

    @objid ("35929a7e-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return ((Instance.class.isAssignableFrom(type) && !Port.class.isAssignableFrom(type)) || CollaborationUse.class.isAssignableFrom(type));
    }

    /**
     * For deserialization only.
     */
    @objid ("35929a86-55b7-11e2-877f-002564c97630")
    public GmInternalStructureZone() {
        // Nothing to do.
    }

    @objid ("35929a89-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            getDisplayedStyle().setProperty(getViewModeStyleKey(), InternalsViewMode.DIAGRAM);
            ((GmInternalStructure) getParent()).setVisible(true);
        } else
            getDisplayedStyle().setProperty(getViewModeStyleKey(), InternalsViewMode.NONE);
    }

    @objid ("35929a8d-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidChild(GmNodeModel node) {
        final MObject el = node.getRelatedElement();
        
        if (el != null && !canUnmask(el)) {
            return false;
        }
        
        // Cannot unmask if the element is already displayed
        final GmNodeModel sameChild = getChild(node.getRepresentedRef());
        return sameChild == null || sameChild == node;
    }

    /**
     * Get the style key for the {@link MetaKey.InternalGroup#INTVIEWMODE} meta key.
     */
    @objid ("35929a95-55b7-11e2-877f-002564c97630")
    private StyleKey getViewModeStyleKey() {
        return getStyleKey(MetaKey.InternalGroup.INTVIEWMODE);
    }

    @objid ("35929a9c-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Do clean obsolete nodes
        super.refreshFromObModel();
        
        final MObject relatedIElement = getRelatedElement();
        
        if (relatedIElement == null || !relatedIElement.isValid())
            return;
        
        StyleKey styleKey = getStyleKey(MetaKey.InternalGroup.INTAUTOUNMASK);
        if (styleKey == null) {
            return;
        }
        final Boolean mode = this.getDisplayedStyle().getProperty(styleKey);
        if (mode) {
            final Rectangle constraint = new Rectangle(5, 5, -1, -1);
            if (relatedIElement instanceof Classifier) {
                final Classifier classifier = (Classifier) relatedIElement;
                // Bindable instances
                for (BindableInstance part : classifier.getInternalStructure()) {
                    if (!(part instanceof Port) && getChild(new MRef(part)) == null) {
                        getDiagram().unmask(this, part, constraint.getCopy());
                        constraint.translate(10, 10);
                    }
                }
            }
        
            if (relatedIElement instanceof NameSpace) {
                final NameSpace namespace = (NameSpace) relatedIElement;
                // Instances
                for (Instance part : namespace.getDeclared()) {
                    if (!(part instanceof Port) && getChild(new MRef(part)) == null) {
                        getDiagram().unmask(this, part, constraint.getCopy());
                        constraint.translate(10, 10);
                    }
                }
        
                // Collaboration uses
                for (CollaborationUse part : namespace.getOwnedCollaborationUse()) {
                    if (getChild(new MRef(part)) == null) {
                        getDiagram().unmask(this, part, constraint.getCopy());
                        constraint.translate(10, 10);
                    }
                }
            }
        }
    }

    @objid ("35929a9f-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInternalStructureZone.");
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

    @objid ("35929aa5-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInternalStructureZone.", GmInternalStructureZone.MINOR_VERSION);
    }

    @objid ("35929aab-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("35929ab0-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
