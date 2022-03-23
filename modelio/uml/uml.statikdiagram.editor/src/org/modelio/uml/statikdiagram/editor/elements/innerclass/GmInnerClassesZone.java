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
package org.modelio.uml.statikdiagram.editor.elements.innerclass;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
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
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the inner classes zone.
 */
@objid ("3524bda8-55b7-11e2-877f-002564c97630")
public final class GmInnerClassesZone extends GmFreeZone {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3524bdac-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3524bdaf-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * For deserialization only.
     */
    @objid ("3524bdb1-55b7-11e2-877f-002564c97630")
    public  GmInnerClassesZone() {
        // Nothing to do.
    }

    /**
     * Creates the inner class zone.
     * @param gmDiagram the diagram
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("3524bdb4-55b7-11e2-877f-002564c97630")
    public  GmInnerClassesZone(IGmDiagram gmDiagram, MRef relatedRef) {
        super(gmDiagram, relatedRef);
    }

    @objid ("3524bdbd-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return NameSpace.class.isAssignableFrom(type) && !TemplateParameter.class.isAssignableFrom(type);
    }

    @objid ("3526441d-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        // Cannot unmask anything else than namespaces
        if (!(el instanceof NameSpace) || (el instanceof TemplateParameter)) {
            return false;
        }
        
        // Cannot unmask a foreign class (not belonging to the class)
        if (!el.getCompositionOwner().equals(getRelatedElement())) {
            return false;
        }
        return true;
    }

    /**
     * The inner class zone can be visible only if the
     * {@link org.modelio.diagram.styles.core.MetaKey.InnerGroup#INNERVIEWMODE} property is
     * {@link InternalsViewMode#DIAGRAM}.
     */
    @objid ("35264425-55b7-11e2-877f-002564c97630")
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

    @objid ("3526442b-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        // The visibility may have changed so fires a notification.
        fireVisibilityChanged();
        super.styleChanged(style);
        
    }

    @objid ("35264431-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getViewModeStyleKey()) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
        
    }

    @objid ("35264438-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            getDisplayedStyle().setProperty(getViewModeStyleKey(), InternalsViewMode.DIAGRAM);
            ((GmInnerClass) getParent()).setVisible(true);
        } else {
            getDisplayedStyle().setProperty(getViewModeStyleKey(), InternalsViewMode.NONE);
        }
        
    }

    @objid ("3526443c-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidChild(GmNodeModel node) {
        final MObject el = node.getRelatedElement();
        
        if (el != null && !isValidElement(el)) {
            return false;
        }
        
        // Cannot unmask if the element is already displayed
        final GmNodeModel sameChild = getChild(node.getRepresentedRef());
        return sameChild == null || sameChild == node;
    }

    /**
     * Get the style key for the {@link MetaKey.InnerGroup#INNERVIEWMODE} meta key.
     */
    @objid ("35264444-55b7-11e2-877f-002564c97630")
    private StyleKey getViewModeStyleKey() {
        return getStyleKey(MetaKey.InnerGroup.INNERVIEWMODE);
    }

    @objid ("3526444b-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmInnerClassesZone.");
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

    @objid ("35264451-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        GmAbstractObject.writeMinorVersion(out, "GmInnerClassesZone.", Integer.valueOf(GmInnerClassesZone.MINOR_VERSION));
        
    }

    @objid ("35264457-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("3526445c-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmInnerClassesZone.MAJOR_VERSION;
    }

    @objid ("3527cabc-55b7-11e2-877f-002564c97630")
    private StyleKey getUnmaskFilterStyleKey() {
        return getStyleKey(MetaKey.VISIBILITYFILTER);
    }

    @objid ("3527cac2-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Do clean obsolete nodes
        super.refreshFromObModel();
        
        final NameSpace classifier = (NameSpace) getRelatedElement();
        if (classifier != null && classifier.isValid()) {
            // TODO upgrade layout...
            int size = this.getChildren().size();
            final Rectangle constraint = new Rectangle(10 * size + 5, 10 * size + 5, -1, -1);
        
            // Auto unmask
            StyleKey unmaskFilterStyleKey = getUnmaskFilterStyleKey();
            if (unmaskFilterStyleKey != null) {
                final StyleKey.UmaskByVisibilityStragegy mode = getDisplayedStyle().getProperty(unmaskFilterStyleKey);
                switch (mode) {
                case ALL:
                    for (ModelTree part : classifier.getOwnedElement()) {
                        if (getChild(new MRef(part)) == null) {
                            getDiagram().unmask(this, part, constraint.getCopy());
                            constraint.translate(10, 10);
                        }
                    }
                    break;
        
                case ALL_PUBLIC:
                    for (ModelTree part : classifier.getOwnedElement(NameSpace.class)) {
                        if (((NameSpace) part).getVisibility() == VisibilityMode.PUBLIC) {
                            if (getChild(new MRef(part)) == null) {
                                getDiagram().unmask(this, part, constraint.getCopy());
                                constraint.translate(10, 10);
                            }
                        }
                    }
                    break;
        
                case ALL_NON_PRIVATE:
                    for (ModelTree part : classifier.getOwnedElement(NameSpace.class)) {
                        if (((NameSpace) part).getVisibility() != VisibilityMode.PRIVATE) {
                            if (getChild(new MRef(part)) == null) {
                                getDiagram().unmask(this, part, constraint.getCopy());
                                constraint.translate(10, 10);
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

    @objid ("3527cac5-55b7-11e2-877f-002564c97630")
    private boolean isValidElement(final MObject el) {
        // Cannot unmask anything else than a valid namespace.
        if (!(el instanceof NameSpace) || !(el.isValid())) {
            return false;
        }
        
        // Cannot unmask a foreign namespace (not belonging to the class)
        if (el.getCompositionOwner() == null || !el.getCompositionOwner().equals(getRelatedElement())) {
            return false;
        }
        
        final NameSpace att = (NameSpace) el;
        
        // Cannot unmask an namespace whose visibility does not match the current visualization options.
        final StyleKey.UmaskByVisibilityStragegy unmaskmode = getDisplayedStyle().getProperty(getUnmaskFilterStyleKey());
        switch (unmaskmode) {
        case ALL: {
            return true;
        }
        case ALL_PUBLIC: {
            return att.getVisibility() == VisibilityMode.PUBLIC;
        }
        case ALL_NON_PRIVATE: {
            return att.getVisibility() != VisibilityMode.PRIVATE;
        }
        case MANUAL: {
            return true;
        }
        default: {
            return false;
        }
        }
        
    }

}
