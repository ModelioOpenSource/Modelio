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
package org.modelio.uml.statikdiagram.editor.elements.internalstructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the {@link Classifier} or {@link NameSpace} internal structure as a group of labels.
 * 
 * @author cmarin
 */
@objid ("358e06c0-55b7-11e2-877f-002564c97630")
public class GmInternalStructureGroup extends GmGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("358e06c4-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("358e06c7-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates an internal structure group.
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("358e06c9-55b7-11e2-877f-002564c97630")
    public  GmInternalStructureGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("358e06d2-55b7-11e2-877f-002564c97630")
    public  GmInternalStructureGroup() {
        
    }

    @objid ("358e06d5-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return ((Instance.class.isAssignableFrom(type) && !Port.class.isAssignableFrom(type)) || CollaborationUse.class.isAssignableFrom(type));
    }

    /**
     * The inner class group can be visible only if the
     * {@link org.modelio.diagram.styles.core.MetaKey.InternalGroup#INTVIEWMODE} property is
     * {@link InternalsViewMode#LIST}.
     */
    @objid ("358e06dd-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        GmCompositeNode parent = getParentNode();
        if (parent != null && parent.isVisible() && parent.getRepresentationMode() == RepresentationMode.STRUCTURED) {
            StyleKey viewModeStyleKey = getViewModeStyleKey();
            if (viewModeStyleKey == null) {
                return false;
            }
            InternalsViewMode v = getDisplayedStyle().getProperty(viewModeStyleKey);
            return (v == InternalsViewMode.LIST);
        }
        return false;
    }

    @objid ("358f8d42-55b7-11e2-877f-002564c97630")
    @Override
    public void obElementsUpdated() {
        refreshFromObModel();
    }

    @objid ("358f8d45-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Do clean obsolete nodes
        super.refreshFromObModel();
        
        final MObject relatedIElement = getRelatedElement();
        
        if (relatedIElement == null || !relatedIElement.isValid()) {
            return;
        }
        
        StyleKey styleKey = getStyleKey(MetaKey.InternalGroup.INTAUTOUNMASK);
        if (styleKey == null) {
            return;
        }
        final Boolean mode = getDisplayedStyle().getProperty(styleKey);
        if (mode) {
        
            if (relatedIElement instanceof Classifier) {
                final Classifier classifier = (Classifier) relatedIElement;
                // Bindable instances
                for (BindableInstance part : classifier.getInternalStructure()) {
                    if (!(part instanceof Port) && getChild(new MRef(part)) == null) {
                        getDiagram().unmask(this, part, null);
                    }
                }
            }
        
            if (relatedIElement instanceof NameSpace) {
                final NameSpace namespace = (NameSpace) relatedIElement;
                // Instances
                for (Instance part : namespace.getDeclared()) {
                    if (!(part instanceof Port) && getChild(new MRef(part)) == null) {
                        getDiagram().unmask(this, part, null);
                    }
                }
        
                // Collaboration uses
                for (CollaborationUse part : namespace.getOwnedCollaborationUse()) {
                    if (getChild(new MRef(part)) == null) {
                        getDiagram().unmask(this, part, null);
                    }
                }
            }
        }
        
    }

    @objid ("358f8d48-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        // The visibility may have changed so fires a notification.
        fireVisibilityChanged();
        super.styleChanged(style);
        
    }

    @objid ("358f8d4e-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getViewModeStyleKey()) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
        
    }

    @objid ("358f8d55-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            getDisplayedStyle().setProperty(getViewModeStyleKey(), InternalsViewMode.LIST);
            ((GmInternalStructure) getParent()).setVisible(true);
        } else {
            getDisplayedStyle().setProperty(getViewModeStyleKey(), InternalsViewMode.NONE);
        }
        
    }

    @objid ("358f8d59-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Only unmaskable elements are valid
        if (!el.isValid() || !canCreate(el.getClass())) {
            return false;
        }
        
        // Cannot unmask a foreign instance (not belonging to the class)
        if (!el.getCompositionOwner().equals(getRelatedElement())) {
            return false;
        }
        return true;
    }

    @objid ("358f8d61-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        final MObject relatedElement = getRelatedElement();
        if (relatedElement == null || !relatedElement.isValid()) {
            return;
        }
        if (relatedElement instanceof Classifier) {
            final Classifier classifier = (Classifier) relatedElement;
            final Boolean mode = getDisplayedStyle().getProperty(getStyleKeyStrict(MetaKey.InternalGroup.INTAUTOUNMASK));
            boolean hasHiddenFeature = false;
        
            if (!mode) {
                hasHiddenFeature = classifier.getInternalStructure().size() +
                        classifier.getDeclared().size() +
                        classifier.getOwnedCollaborationUse().size() != getChildren().size();
            }
            setHiddenFeature(hasHiddenFeature);
        } else if (relatedElement instanceof NameSpace) {
            final NameSpace namespace = (NameSpace) relatedElement;
            final Boolean mode = getDisplayedStyle().getProperty(getStyleKeyStrict(MetaKey.InternalGroup.INTAUTOUNMASK));
            boolean hasHiddenFeature = false;
        
            if (!mode) {
                hasHiddenFeature = namespace.getDeclared().size() + namespace.getOwnedCollaborationUse().size() != getChildren().size();
            }
            setHiddenFeature(hasHiddenFeature);
        }
        
    }

    /**
     * This group can contain only labels.
     */
    @objid ("358f8d64-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmElementLabel.class.isAssignableFrom(nodeClass);
    }

    /**
     * Get the style key for the {@link MetaKey.InternalGroup#INTVIEWMODE} meta key.
     */
    @objid ("358f8d6d-55b7-11e2-877f-002564c97630")
    private StyleKey getViewModeStyleKey() {
        return getStyleKey(MetaKey.InternalGroup.INTVIEWMODE);
    }

    @objid ("358f8d74-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmInternalStructureGroup.");
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

    @objid ("359113da-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmInternalStructureGroup.", GmInternalStructureGroup.MINOR_VERSION);
        
    }

    @objid ("359113e0-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("359113e5-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmInternalStructureGroup.MAJOR_VERSION;
    }

}
