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

package org.modelio.diagram.editor.statik.elements.instanceinternalstructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.instance.GmInstanceStructuredStyleKeys;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the {@link Instance} internal structure as a group of labels.
 * <p>
 * Handles auto unmasking of inner instances.
 * 
 * @author cmarin
 */
@objid ("354f7732-55b7-11e2-877f-002564c97630")
public class GmInstanceInternalStructureGroup extends GmGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("354f7736-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("354f7739-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates an internal structure group.
     * 
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("3550fd9a-55b7-11e2-877f-002564c97630")
    public GmInstanceInternalStructureGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        refreshFromObModel();
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("3550fda3-55b7-11e2-877f-002564c97630")
    public GmInstanceInternalStructureGroup() {
    }

    @objid ("3550fda6-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return BindableInstance.class.isAssignableFrom(type);
    }

    /**
     * The inner class group can be visible only if the {@link GmInstanceStructuredStyleKeys#INTERNALSVIEWMODE} property
     * is {@link InternalsViewMode#LIST}.
     */
    @objid ("3550fdae-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        GmCompositeNode parentNode = getParentNode();
        if (parentNode != null && parentNode.isVisible() && parentNode.getRepresentationMode() == RepresentationMode.STRUCTURED) {
            InternalsViewMode v = getDisplayedStyle().getProperty(GmInstanceStructuredStyleKeys.INTERNALSVIEWMODE);
            return (v == InternalsViewMode.LIST);
        }
        return false;
    }

    @objid ("3550fdb4-55b7-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        refreshFromObModel();
    }

    @objid ("3550fdba-55b7-11e2-877f-002564c97630")
    @Override
    public void obElementsUpdated() {
        refreshFromObModel();
    }

    @objid ("3550fdbd-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final Instance instance = (Instance) getRelatedElement();
        if (instance != null && instance.isValid()) {
            final Boolean autoShow = this.getDisplayedStyle().getProperty(GmInstanceStructuredStyleKeys.InternalStructure.AUTOUNMASK);
            if (autoShow) {
                for (BindableInstance part : instance.getPart()) {
                    if (!(part instanceof Port) && getChild(new MRef(part)) == null)
                        getDiagram().unmask(this, part, null);
                }
            }
        }
    }

    @objid ("3550fdc0-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        // The visibility may have changed so fires a notification.
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("3550fdc6-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == GmInstanceStructuredStyleKeys.INTERNALSVIEWMODE)
            fireVisibilityChanged();
        else
            super.styleChanged(property, newValue);
    }

    @objid ("3550fdcd-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            getDisplayedStyle().setProperty(GmInstanceStructuredStyleKeys.INTERNALSVIEWMODE, InternalsViewMode.LIST);
            ((GmInstanceInternalStructure) getParent()).setVisible(true);
        } else {
            getDisplayedStyle().setProperty(GmInstanceStructuredStyleKeys.INTERNALSVIEWMODE, InternalsViewMode.NONE);
        }
    }

    @objid ("3550fdd1-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Cannot unmask anything else than a valid instance
        if (!(el instanceof Instance) || !el.isValid())
            return false;
        
        // Cannot unmask a foreign instance (not belonging to the class)
        if (!el.getCompositionOwner().equals(this.getRelatedElement()))
            return false;
        return true;
    }

    @objid ("3550fdd9-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        final Instance instance = (Instance) getRelatedElement();
        if (instance != null && instance.isValid()) {
            Boolean autoShow = this.getDisplayedStyle().getProperty(GmInstanceStructuredStyleKeys.InternalStructure.AUTOUNMASK);
            boolean hasHiddenFeature = false;
        
            if (!autoShow) {
                hasHiddenFeature = instance.getPart().size() != getChildren().size();
            }
            setHiddenFeature(hasHiddenFeature);
        }
    }

    /**
     * This group can contain only labels.
     */
    @objid ("3552843b-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmElementLabel.class.isAssignableFrom(nodeClass);
    }

    @objid ("35528444-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInstanceInternalStructureGroup.");
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

    @objid ("3552844a-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInstanceInternalStructureGroup.", GmInstanceInternalStructureGroup.MINOR_VERSION);
    }

    @objid ("35528450-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("35528455-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
