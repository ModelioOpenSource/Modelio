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

package org.modelio.diagram.editor.statik.elements.enumliteral;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys;
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
import org.modelio.metamodel.uml.statik.Enumeration;
import org.modelio.metamodel.uml.statik.EnumerationLiteral;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Attribute group model.
 * 
 * @author cmarin
 */
@objid ("34e62e03-55b7-11e2-877f-002564c97630")
public class GmEnumLitteralGroup extends GmGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("34e62e07-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34e62e0a-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("34e62e0c-55b7-11e2-877f-002564c97630")
    public GmEnumLitteralGroup() {
    }

    /**
     * Creates an attribute group.
     * @param diagram The diagram.
     * @param relatedRef The related element reference, must not be null.
     */
    @objid ("34e62e0f-55b7-11e2-877f-002564c97630")
    public GmEnumLitteralGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Only {@link EnumerationLiteral} can be created here.
     */
    @objid ("34e62e18-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return EnumerationLiteral.class.isAssignableFrom(type);
    }

    @objid ("34e7b479-55b7-11e2-877f-002564c97630")
    @Override
    public Enumeration getRelatedElement() {
        return (Enumeration) super.getRelatedElement();
    }

    /**
     * The inner class zone can be visible only if the {@link EnumStructuredStyleKeys#LITGROUPVISIBLE} property is true.
     */
    @objid ("34e7b480-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return getParent() != null && ((Boolean) getDisplayedStyle().getProperty(EnumStructuredStyleKeys.LITGROUPVISIBLE) && getParent().getRepresentationMode() == RepresentationMode.STRUCTURED);
    }

    @objid ("34e7b486-55b7-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        refreshFromObModel();
    }

    @objid ("34e7b48c-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final Enumeration classifier = getRelatedElement();
        if (classifier != null && classifier.isValid()) {
            for (EnumerationLiteral part : classifier.getValue()) {
                GmNodeModel child = getChild(new MRef(part));
                if (child == null) {
                    child = getDiagram().unmask(this, part, null);
                } else {
                    moveChild(child, -1);
                }
            }
        }
    }

    @objid ("34e7b48f-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == EnumStructuredStyleKeys.LITGROUPVISIBLE) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
    }

    @objid ("34e7b496-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        refreshFromObModel();
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("34e7b49c-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        getDisplayedStyle().setProperty(EnumStructuredStyleKeys.LITGROUPVISIBLE, visible);
        if (visible) {
            StyleKey key = getStyleKey(MetaKey.REPMODE);
            if (key != null) {
                getParent().getDisplayedStyle().setProperty(key, RepresentationMode.STRUCTURED);
            }
        }
    }

    @objid ("34e7b4a0-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Cannot unmask anything else than a valid literal
        if (!(el instanceof EnumerationLiteral) || !el.isValid()) {
            return false;
        }
        
        // Cannot unmask a foreign attribute (not belonging to the class)
        if (!el.getCompositionOwner().equals(getRelatedElement())) {
            return false;
        }
        return true;
    }

    @objid ("34e7b4a8-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        // Nothing to do
    }

    /**
     * The attribute group only support {@link GmEnumLitteral} nodes.
     */
    @objid ("34e7b4ab-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmEnumLitteral.class.isAssignableFrom(nodeClass);
    }

    @objid ("34e7b4b4-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmEnumLitteralGroup.");
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

    @objid ("34e93b1b-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmEnumLitteralGroup.", GmEnumLitteralGroup.MINOR_VERSION);
    }

    @objid ("34e93b21-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("34e93b26-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmEnumLitteralGroup.MAJOR_VERSION;
    }

}
