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

package org.modelio.uml.statikdiagram.editor.elements.attributegroup;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.uml.statikdiagram.editor.elements.attribute.GmAttribute;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Attribute group model.
 * 
 * @author cmarin
 */
@objid ("96d11fbb-55b6-11e2-877f-002564c97630")
public class GmAttributeGroup extends GmGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("34045976-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34045979-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("3404597b-55b7-11e2-877f-002564c97630")
    public GmAttributeGroup() {
    }

    /**
     * Creates an attribute group.
     * 
     * @param gmDiagram The diagram.
     * @param relatedRef The related element reference, must not be null.
     */
    @objid ("3404597e-55b7-11e2-877f-002564c97630")
    public GmAttributeGroup(IGmDiagram gmDiagram, MRef relatedRef) {
        super(gmDiagram, relatedRef);
    }

    /**
     * The attribute group only support {@link GmAttribute} nodes.
     */
    @objid ("34045987-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmAttribute.class.isAssignableFrom(nodeClass);
    }

    /**
     * Only attributes can be created here.
     */
    @objid ("34045990-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return Attribute.class.isAssignableFrom(type);
    }

    @objid ("34045999-55b7-11e2-877f-002564c97630")
    @Override
    public Classifier getRelatedElement() {
        return (Classifier) super.getRelatedElement();
    }

    /**
     * The inner class zone can be visible only if the {@link MetaKey.AttGroup#ATTSHOWGROUP} property is true.
     */
    @objid ("3405dfff-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        if (getParent() != null && getParent().getRepresentationMode() == RepresentationMode.STRUCTURED) {
            StyleKey styleKey = getStyleKey(MetaKey.AttGroup.ATTSHOWGROUP);
            if (styleKey == null) {
                return false;
            }
            return getDisplayedStyle().getProperty(styleKey);
        }
        return false;
    }

    @objid ("3405e005-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        //TODO : move this in parent class
        firePropertyChange(PROP_REFRESH_FROM_OBMODEL, null, this);
    }

    @objid ("3405e008-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getStyleKey(MetaKey.VISIBILITYFILTER)) {
            refreshFromObModel();
        } else if (property == getStyleKey(MetaKey.AttGroup.ATTSHOWGROUP)) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
    }

    @objid ("3405e00f-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        refreshFromObModel();
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("3405e015-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            getParent().getDisplayedStyle().setProperty(getStyleKey(MetaKey.REPMODE), RepresentationMode.STRUCTURED);
        }
        StyleKey styleKey = getStyleKey(MetaKey.AttGroup.ATTSHOWGROUP);
        if (styleKey == null) {
            return;
        }
        getDisplayedStyle().setProperty(styleKey, visible);
    }

    /**
     * Checks whether the given model element can be and still be displayed here.
     * <p>
     * Check all conditions except the case where it is already unmasked.
     * 
     * @param el The element to unmask
     * @return true if it satisfies all conditions, else false.
     */
    @objid ("3405e019-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Cannot unmask anything else than a valid attribute
        if (!(el instanceof Attribute) || !el.isValid()) {
            return false;
        }
        
        // Cannot unmask a foreign attribute (not belonging to the class)
        if (!el.getCompositionOwner().equals(getRelatedElement())) {
            return false;
        }
        
        final Attribute att = (Attribute) el;
        
        // Cannot unmask an attribute whose visibility does not match the current visualization options.
        StyleKey.UmaskByVisibilityStragegy unmaskmode = getVisibilityFilter();
        if (unmaskmode == null) {
            return false;
        }
        
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

    @objid ("3405e022-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        final Classifier classifier = getRelatedElement();
        if (classifier != null && classifier.isValid()) {
            boolean hasHiddenFeature = false;
        
            StyleKey.UmaskByVisibilityStragegy mode = getVisibilityFilter();
            if (mode == null) {
                return;
            }
            
            switch (mode) {
            case ALL:
                break;
        
            case ALL_PUBLIC:
                for (Feature part : classifier.getOwnedAttribute()) {
                    if (part.getVisibility() != VisibilityMode.PUBLIC) {
                        hasHiddenFeature = true;
                    }
                }
                break;
        
            case ALL_NON_PRIVATE:
                for (Feature part : classifier.getOwnedAttribute()) {
                    if (part.getVisibility() == VisibilityMode.PRIVATE) {
                        hasHiddenFeature = true;
                    }
                }
                break;
            case MANUAL:
                hasHiddenFeature = classifier.getOwnedAttribute().size() != getChildren().size();
                break;
            }
        
            setHiddenFeature(hasHiddenFeature);
        
        }
    }

    @objid ("3405e025-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmAttributeGroup.");
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

    @objid ("3405e02b-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmAttributeGroup.", GmAttributeGroup.MINOR_VERSION);
    }

    @objid ("3405e031-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("3405e036-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmAttributeGroup.MAJOR_VERSION;
    }

    @objid ("c07d3022-d786-44e6-986e-456701bdd18a")
    public org.modelio.diagram.styles.core.StyleKey.UmaskByVisibilityStragegy getVisibilityFilter() {
        StyleKey styleKey = getStyleKey(MetaKey.VISIBILITYFILTER);
        if (styleKey == null) {
            return null;
        }
        return getDisplayedStyle().getProperty(styleKey);
    }

}
