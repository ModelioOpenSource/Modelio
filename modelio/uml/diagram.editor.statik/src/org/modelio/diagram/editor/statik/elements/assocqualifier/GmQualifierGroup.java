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

package org.modelio.diagram.editor.statik.elements.assocqualifier;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.association.GmAssociation;
import org.modelio.diagram.editor.statik.elements.attribute.GmAttribute;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Qualifier group model.
 * 
 * @author cmarin
 */
@objid ("33f8245a-55b7-11e2-877f-002564c97630")
public class GmQualifierGroup extends GmGroup {
    /**
     * Related role.
     */
    @objid ("33f8245e-55b7-11e2-877f-002564c97630")
    private AssociationEnd role;

    @objid ("33f82462-55b7-11e2-877f-002564c97630")
    private boolean visible = false;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("33f82463-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("33f82466-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("33f82468-55b7-11e2-877f-002564c97630")
    public GmQualifierGroup() {
    }

    /**
     * Creates a qualifier group.
     * @param diagram The diagram.
     * @param relatedRef The related association role reference, must not be <code>null</code>.
     * @param role The represented association role, may be <code>null</code>.
     */
    @objid ("33f8246b-55b7-11e2-877f-002564c97630")
    public GmQualifierGroup(IGmDiagram diagram, MRef relatedRef, final AssociationEnd role) {
        super(diagram, relatedRef);
        this.role = role;
    }

    /**
     * The qualifier group only support {@link GmAttribute} nodes.
     */
    @objid ("33f9aafc-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmAttribute.class.isAssignableFrom(nodeClass);
    }

    /**
     * Only attributes can be created here.
     */
    @objid ("33f9ab05-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return Attribute.class.isAssignableFrom(type);
    }

    @objid ("33f9ab0e-55b7-11e2-877f-002564c97630")
    @Override
    public AssociationEnd getRelatedElement() {
        return this.role;
    }

    /**
     * Visible only if it has children.
     */
    @objid ("33f9ab15-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @objid ("33f9ab1b-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Mask invalid children
        super.refreshFromObModel();
        
        // Unmask missing children
        if (this.role != null && this.role.isValid()) {
            boolean childAdded = false;
            for (Feature part : this.role.getQualifier()) {
                if (getChild(new MRef(part)) == null) {
                    getDiagram().unmask(this, part, null);
                    childAdded = true;
                }
            }
        
            if (childAdded && !isVisible())
                setVisible(true);
        }
    }

    @objid ("33f9ab1e-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(final boolean vis) {
        this.visible = vis;
    }

    /**
     * Checks whether the given model element can be and still be displayed here.
     * <p>
     * Check all conditions except the case where it is already unmasked.
     * @param el The element to unmask
     * @return true if it satisfies all conditions, else false.
     */
    @objid ("33f9ab23-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Cannot unmask anything else than a valid attribute
        if (!(el instanceof Attribute) || !el.isValid())
            return false;
        
        // Cannot unmask a foreign attribute (not belonging to the class)
        if (!el.getCompositionOwner().equals(this.getRelatedElement()))
            return false;
        return true;
    }

    @objid ("33f9ab2c-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        // Nothing to do
    }

    /**
     * Update the cascaded style of the group.
     * @param parentLink a link model
     */
    @objid ("33f9ab2f-55b7-11e2-877f-002564c97630")
    protected void updateStyle(final GmLink parentLink) {
        IGmLinkable gmRef = getReferenceNode();
        if (gmRef != null)
            getPersistedStyle().setCascadedStyle(gmRef.getPersistedStyle());
        else
            getPersistedStyle().setCascadedStyle(getDiagram().getPersistedStyle());
    }

    /**
     * Get the node on which this qualifier group is sticked.
     * @return the reference node, or <code>null</code> if not yet initialized.
     */
    @objid ("33f9ab36-55b7-11e2-877f-002564c97630")
    protected IGmLinkable getReferenceNode() {
        GmAssociation gmAssoc = (GmAssociation) getParentLink();
        if (gmAssoc == null) {
            return null;
        } else if (getRepresentedRef().equals(gmAssoc.getRepresentedRoleRef())) {
            // source side
            return gmAssoc.getFrom();
        } else {
            // Target side
            return (gmAssoc.getTo());
        }
    }

    /**
     * Redefined to return the reference node style key.
     */
    @objid ("33fb319b-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metaKey) {
        IGmLinkable gmRef = getReferenceNode();
        if (gmRef != null)
            return gmRef.getStyleKey(metaKey);
        else
            return null;
    }

    @objid ("33fb31a6-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmQualifierGroup.");
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

    @objid ("33fb31ad-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmQualifierGroup.", GmQualifierGroup.MINOR_VERSION);
    }

    @objid ("33fb31b3-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
                
        this.role = (AssociationEnd) resolveRef(getRepresentedRef());
        this.visible = (!getChildren().isEmpty());
    }

    @objid ("33fb31b9-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
