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

package org.modelio.diagram.editor.statik.elements.innerclass;

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
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model of the inner classes list.
 */
@objid ("3527cadd-55b7-11e2-877f-002564c97630")
public class GmInnerClassGroup extends GmGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3527cae1-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3527cae4-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates an inner classes group.
     * 
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("3527cae6-55b7-11e2-877f-002564c97630")
    public GmInnerClassGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("3527caef-55b7-11e2-877f-002564c97630")
    public GmInnerClassGroup() {
    }

    /**
     * This group can contain only labels.
     */
    @objid ("3527caf2-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmElementLabel.class.isAssignableFrom(nodeClass);
    }

    @objid ("3527cafb-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return NameSpace.class.isAssignableFrom(type) && !TemplateParameter.class.isAssignableFrom(type);
    }

    /**
     * The inner class group can be visible only if the
     * {@link org.modelio.diagram.styles.core.MetaKey.InnerGroup#INNERVIEWMODE} property is
     * {@link InternalsViewMode#LIST}.
     */
    @objid ("3527cb03-55b7-11e2-877f-002564c97630")
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

    @objid ("3529515d-55b7-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        refreshFromObModel();
    }

    @objid ("35295163-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Do clean obsolete nodes
        super.refreshFromObModel();
        
        final NameSpace classifier = (NameSpace) getRelatedElement();
        if (classifier != null && classifier.isValid()) {
            // Auto unmask
            StyleKey unmaskFilterStyleKey = getUnmaskFilterStyleKey();
            if (unmaskFilterStyleKey != null) {
                final StyleKey.UmaskByVisibilityStragegy mode = getDisplayedStyle().getProperty(unmaskFilterStyleKey);
                switch (mode) {
                case ALL:
                    for (ModelTree part : classifier.getOwnedElement()) {
                        GmNodeModel child = getChild(new MRef(part));
                        if (child == null) {
                            child = getDiagram().unmask(this, part, null);
                        } else {
                            moveChild(child, -1);
                        }
                    }
                    break;
        
                case ALL_PUBLIC:
                    for (ModelTree part : classifier.getOwnedElement(NameSpace.class)) {
                        if (((NameSpace) part).getVisibility() == VisibilityMode.PUBLIC) {
                            GmNodeModel child = getChild(new MRef(part));
                            if (child == null) {
                                child = getDiagram().unmask(this, part, null);
                            } else {
                                moveChild(child, -1);
                            }
                        }
                    }
                    break;
        
                case ALL_NON_PRIVATE:
                    for (ModelTree part : classifier.getOwnedElement(NameSpace.class)) {
                        if (((NameSpace) part).getVisibility() != VisibilityMode.PRIVATE) {
                            GmNodeModel child = getChild(new MRef(part));
                            if (child == null) {
                                child = getDiagram().unmask(this, part, null);
                            } else {
                                moveChild(child, -1);
                            }
                        }
                    }
                    break;
                case MANUAL:
                    // unmask nothing.
                    break;
                }
            }
        }
    }

    @objid ("35295166-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        // The visibility may have changed so fires a notification.
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("3529516c-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getViewModeStyleKey()) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
    }

    @objid ("35295173-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            getDisplayedStyle().setProperty(getViewModeStyleKey(), InternalsViewMode.LIST);
            ((GmInnerClass) getParent()).setVisible(true);
        } else {
            getDisplayedStyle().setProperty(getViewModeStyleKey(), InternalsViewMode.NONE);
        }
    }

    @objid ("35295177-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Cannot unmask anything else than a valid namespace.
        if (!(el instanceof NameSpace) || !(el.isValid())) {
            return false;
        }
        
        // Cannot unmask a foreign namespace (not belonging to the class)
        if (el.getCompositionOwner() != null && !el.getCompositionOwner().equals(getRelatedElement())) {
            return false;
        }
        
        final NameSpace att = (NameSpace) el;
        
        // Cannot unmask an namespace whose visibility does not match the current visualization options.
        final StyleKey.UmaskByVisibilityStragegy unmaskmode = getDisplayedStyle().getProperty(getUnmaskFilterStyleKey());
        switch (unmaskmode) {
        case ALL:
            return true;
        case ALL_PUBLIC:
            return att.getVisibility() == VisibilityMode.PUBLIC;
        case ALL_NON_PRIVATE:
            return att.getVisibility() != VisibilityMode.PRIVATE;
        case MANUAL:
            return true;
        }
        return false;
    }

    @objid ("3529517f-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        StyleKey unmaskFilterStyleKey = getUnmaskFilterStyleKey();
        if (unmaskFilterStyleKey == null) {
            return;
        }
        final StyleKey.UmaskByVisibilityStragegy mode = getDisplayedStyle().getProperty(unmaskFilterStyleKey);
        final NameSpace classifier = (NameSpace) getRelatedElement();
        if (classifier != null && classifier.isValid()) {
            boolean hasHiddenFeature = false;
        
            switch (mode) {
            case ALL:
                break;
        
            case ALL_PUBLIC:
                for (ModelTree part : classifier.getOwnedElement(NameSpace.class)) {
                    if (((NameSpace) part).getVisibility() != VisibilityMode.PUBLIC) {
                        hasHiddenFeature = true;
                    }
                }
                break;
        
            case ALL_NON_PRIVATE:
                for (ModelTree part : classifier.getOwnedElement(NameSpace.class)) {
                    if (((NameSpace) part).getVisibility() == VisibilityMode.PRIVATE) {
                        hasHiddenFeature = true;
                    }
                }
                break;
            case MANUAL:
                hasHiddenFeature = classifier.getOwnedElement(NameSpace.class).size() != getChildren().size();
                break;
            }
        
            setHiddenFeature(hasHiddenFeature);
        
        }
    }

    /**
     * Get the style key for the {@link MetaKey.InnerGroup#INNERUNMASKFILTER} meta key.
     */
    @objid ("35295182-55b7-11e2-877f-002564c97630")
    private StyleKey getUnmaskFilterStyleKey() {
        return getStyleKey(MetaKey.VISIBILITYFILTER);
    }

    /**
     * Get the style key for the {@link MetaKey.InnerGroup#INNERVIEWMODE} meta key.
     */
    @objid ("35295189-55b7-11e2-877f-002564c97630")
    private StyleKey getViewModeStyleKey() {
        return getStyleKey(MetaKey.InnerGroup.INNERVIEWMODE);
    }

    @objid ("35295190-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmInnerClassGroup.");
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

    @objid ("35295196-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        GmAbstractObject.writeMinorVersion(out, "GmInnerClassGroup.", Integer.valueOf(GmInnerClassGroup.MINOR_VERSION));
    }

    @objid ("352ad7fd-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("352ad802-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmInnerClassGroup.MAJOR_VERSION;
    }

}
