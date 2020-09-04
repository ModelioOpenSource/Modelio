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

package org.modelio.diagram.editor.statik.elements.instancelink;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInfoFlowsGroup;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInformationFlowArrow;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.link.extensions.IGmLocator;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a {@link LinkEnd}.
 */
@objid ("35589ed3-55b7-11e2-877f-002564c97630")
public class GmInstanceLink extends GmLink {
    @objid ("35589edc-55b7-11e2-877f-002564c97630")
    private boolean fromNavigable;

    @objid ("355a2559-55b7-11e2-877f-002564c97630")
    private boolean toNavigable;

    /**
     * Current version of this Gm.
     */
    @objid ("355a2561-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 2;

    @objid ("355a2564-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d6a340a3-8584-4515-b69a-d740dbfb2480")
    public static final String ROLE_TARGET_MAIN = "target_label";

    @objid ("1ddac7bb-8f97-4cb1-baf8-0ee2b908e405")
    public static final String ROLE_SRC_INFOFLOW_GRP = "source_infoflow_group";

    @objid ("58830352-0288-48c3-a9a9-6bcbc075efcf")
    public static final String ROLE_SRC_INFOFLOW_ARROW = "source_infoflow_arrow";

    @objid ("ceee1685-fe57-4919-b06b-1492b4ad6808")
    public static final String ROLE_TARGET_CARD = "target_card";

    @objid ("c9285425-3f53-4046-97b2-4be495629fc1")
    public static final String ROLE_SRC_MAIN = "source_label";

    @objid ("9652a943-26ec-4cbc-84e8-d669807c1009")
    public static final String ROLE_SRC_CARD = "source_card";

    @objid ("9e590a5e-ee02-48b2-b85f-ba5a04c48c8a")
    public static final String ROLE_TARGET_INFOFLOW_GRP = "target_infoflow_group";

    @objid ("1a6bed68-8b55-4771-b6e6-6e1670aec130")
    public static final String ROLE_TARGET_INFOFLOW_ARROW = "target_infoflow_arrow";

    /**
     * Represented element reference.
     */
    @objid ("58a85648-5bd5-11e2-9e33-00137282c51b")
    private MRef roleRef = null;

    @objid ("58a8564a-5bd5-11e2-9e33-00137282c51b")
    private static final InstanceLinkStructuredStyleKeys STRUCTURED_KEYS = new InstanceLinkStructuredStyleKeys();

    @objid ("981ef922-7b10-4ccd-95b4-0a79cfec67f2")
    private LinkEnd roleEl;

    @objid ("daac7fa5-83dc-444c-a92d-7e9bb17d970c")
    private LinkEnd oppositeRole;

    /**
     * Constructor for deserialization.
     */
    @objid ("355a2566-55b7-11e2-877f-002564c97630")
    public GmInstanceLink() {
        // Nothing to do.
    }

    /**
     * Creates a GmLink.
     * 
     * @param diagram The diagram containing the link.
     * @param theRole The represented element.
     * @param roleRef The represented role reference. May not be null.
     * @param linkRef The represented link reference. May not be null.
     */
    @objid ("355a2569-55b7-11e2-877f-002564c97630")
    public GmInstanceLink(IGmDiagram diagram, LinkEnd theRole, MRef roleRef, MRef linkRef) {
        super(diagram, linkRef);
        
        this.roleEl = theRole;
        this.roleRef = roleRef;
        
        if (theRole != null) {
            this.oppositeRole = theRole.getOpposite();
        
            // initialize fields
            updateNavigability();
        
            // Create extensions
            GmFractionalConnectionLocator constraint;
            final MRef oppositeRoleRef = new MRef(this.oppositeRole);
        
            // source side extensions
            addExtension(ExtensionLocation.TargetNW, GmInstanceLink.ROLE_TARGET_MAIN, new GmLinkRoleNameLabel(diagram, theRole, roleRef));
            addExtension(ExtensionLocation.TargetSE, GmInstanceLink.ROLE_TARGET_CARD, new GmLinkRoleCardinalityLabel(diagram, theRole, roleRef));
            constraint = new GmFractionalConnectionLocator(0.25, 0, -10);
            addExtension(new GmInfoFlowsGroup(diagram, oppositeRoleRef), GmInstanceLink.ROLE_SRC_INFOFLOW_GRP, constraint);
            constraint = new GmFractionalConnectionLocator(0.25, 0, 0, false);
            addExtension(new GmInformationFlowArrow(diagram, oppositeRoleRef), GmInstanceLink.ROLE_SRC_INFOFLOW_ARROW, constraint);
        
            // Target side extensions
            addExtension(ExtensionLocation.SourceNW, GmInstanceLink.ROLE_SRC_MAIN, new GmLinkRoleNameLabel(diagram, this.oppositeRole, oppositeRoleRef));
            addExtension(ExtensionLocation.SourceSE, GmInstanceLink.ROLE_SRC_CARD, new GmLinkRoleCardinalityLabel(diagram, this.oppositeRole, oppositeRoleRef));
            constraint = new GmFractionalConnectionLocator(0.75, 0, -10);
            addExtension(new GmInfoFlowsGroup(diagram, roleRef), GmInstanceLink.ROLE_TARGET_INFOFLOW_GRP, constraint);
            constraint = new GmFractionalConnectionLocator(0.75, 0, 0, true);
            addExtension(new GmInformationFlowArrow(diagram, roleRef), GmInstanceLink.ROLE_TARGET_INFOFLOW_ARROW, constraint);
        
            // Middle extensions
            addExtension(ExtensionLocation.MiddleSE, IGmLink.ROLE_MAIN_LABEL, new GmLinkLabel(diagram, linkRef));
        
        }
    }

    @objid ("355a2578-55b7-11e2-877f-002564c97630")
    @Override
    public LinkEnd getRelatedElement() {
        return this.roleEl;
    }

    @objid ("355a257f-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmInstanceLink.STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("355a2589-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmInstanceLink.STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("355a2592-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        updateNavigability();
        
        // post change event
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, null, this);
    }

    /**
     * @return the opposite role of the represented role, or null if none (model broken)
     */
    @objid ("355a2595-55b7-11e2-877f-002564c97630")
    LinkEnd getOppositeRole() {
        return this.oppositeRole;
    }

    /**
     * Get the source side navigability.
     * 
     * @return true if the source side is navigable.
     */
    @objid ("355babf9-55b7-11e2-877f-002564c97630")
    public boolean isFromNavigable() {
        return this.fromNavigable;
    }

    /**
     * Get the target side navigability.
     * 
     * @return true if the target side of the link is navigable.
     */
    @objid ("355babfe-55b7-11e2-877f-002564c97630")
    public boolean isToNavigable() {
        return this.toNavigable;
    }

    @objid ("355bac03-55b7-11e2-877f-002564c97630")
    @Override
    public Instance getFromElement() {
        return (this.roleEl != null && this.roleEl.getSource() != null) ? this.roleEl.getSource() : (this.oppositeRole != null ? this.oppositeRole.getTarget() : null);
    }

    @objid ("355bac0a-55b7-11e2-877f-002564c97630")
    @Override
    public Instance getToElement() {
        return (this.roleEl != null && this.roleEl.getTarget() != null) ? this.roleEl.getTarget() : (this.oppositeRole != null ? this.oppositeRole.getSource() : null);
    }

    @objid ("355bac17-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        out.writeProperty("representedRole", this.roleRef);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmInstanceLink.", GmInstanceLink.MINOR_VERSION);
    }

    /**
     * Get the represented link role.
     * 
     * @return the link role.
     */
    @objid ("355bac28-55b7-11e2-877f-002564c97630")
    public LinkEnd getRepresentedRole() {
        return this.roleEl;
    }

    @objid ("355bac2f-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.roleEl;
    }

    @objid ("355bac36-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmInstanceLink.MAJOR_VERSION;
    }

    @objid ("de485a13-e009-4578-ba08-4df9f63ae825")
    private void updateNavigability() {
        if (this.roleEl != null && this.roleEl.isValid() && this.oppositeRole != null && this.oppositeRole.isValid()) {
            this.fromNavigable = this.oppositeRole.isNavigable();
            this.toNavigable = this.roleEl.isNavigable();
        } else {
            this.fromNavigable = false;
            this.toNavigable = false;
        }
    }

    @objid ("eee8ce21-1a32-451e-a590-2ded2bc75706")
    @Override
    protected void readLink(IDiagramReader in) {
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmInstanceLink.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        case 2: {
            read_2(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 2
            read_2(in);
            break;
        }
        }
    }

    @objid ("4262e390-8259-46a0-8381-72364232c272")
    @Override
    protected void read_GmLinkV0_roles() {
        for (GmNodeModel n : getExtensions()) {
            boolean isMainRole = n.getRepresentedRef().equals(this.roleRef);
        
            if (n instanceof GmLinkRoleNameLabel) {
                if (isMainRole) {
                    n.setRoleInComposition(GmInstanceLink.ROLE_TARGET_MAIN);
                } else {
                    n.setRoleInComposition(GmInstanceLink.ROLE_SRC_MAIN);
                }
            } else if (n instanceof GmLinkLabel) {
                n.setRoleInComposition(IGmLink.ROLE_MAIN_LABEL);
            } else if (n instanceof GmLinkRoleCardinalityLabel) {
                if (isMainRole) {
                    n.setRoleInComposition(GmInstanceLink.ROLE_TARGET_CARD);
                } else {
                    n.setRoleInComposition(GmInstanceLink.ROLE_SRC_CARD);
                }
            } else if (n instanceof GmInfoFlowsGroup) {
                if (isMainRole) {
                    n.setRoleInComposition(GmInstanceLink.ROLE_TARGET_INFOFLOW_GRP);
                } else {
                    n.setRoleInComposition(GmInstanceLink.ROLE_SRC_INFOFLOW_GRP);
                }
            } else if (n instanceof GmInformationFlowArrow) {
                if (isMainRole) {
                    n.setRoleInComposition(GmInstanceLink.ROLE_TARGET_INFOFLOW_ARROW);
                } else {
                    n.setRoleInComposition(GmInstanceLink.ROLE_SRC_INFOFLOW_ARROW);
                }
        
            }
        
        }
    }

    /**
     * Modelio 2.2.1 -> Modelio 3.0 migration.
     * Replace the Link label with a {@link GmLinkLabel}.
     */
    @objid ("d4f28602-c0d9-4cd1-9898-f16627290e01")
    private void read_0(IDiagramReader in) {
        read_1(in);
        
        // Look for an Link label to migrate... there should be one
        GmDefaultModelElementHeader oldLabel = null;
        for (GmNodeModel extension : this.getExtensions()) {
            if (extension.getRepresentedRef().mc.equals("ModelElement")
                    && extension.getClass() == GmDefaultModelElementHeader.class) {
                oldLabel = (GmDefaultModelElementHeader) extension;
            }
        }
        
        if (oldLabel != null) {
            // Create a new label, with the appropriate Gm
            final GmLinkLabel newLabel = new GmLinkLabel(getDiagram(), getRepresentedRef());
            addExtension(ExtensionLocation.MiddleSE, IGmLink.ROLE_MAIN_LABEL, newLabel);
            newLabel.setLayoutData(oldLabel.getLayoutData());
        
            // Delete the old association label
            removeExtension(oldLabel);
            oldLabel.delete();
        }
    }

    /**
     * Modelio 3.7.0 -> Modelio 3.7.1 migration.
     * Reset width and height values for card labels
     */
    @objid ("902783b0-87ba-4f27-a6b0-3385291fabdd")
    private void read_1(IDiagramReader in) {
        read_2(in);
        
        for (GmNodeModel n : getExtensions()) {
            String role = n.getRoleInComposition();
            if (role.equals(GmInstanceLink.ROLE_SRC_CARD) ||
                    role.equals(GmInstanceLink.ROLE_TARGET_CARD)) {
                IGmLocator layoutContraint = getLayoutContraint(n);
                if (layoutContraint.getWidthConstraint() != -1) {
                    layoutContraint.setWidthConstraint(-1);
                }
                if (layoutContraint.getHeightConstraint() != -1) {
                    layoutContraint.setHeightConstraint(-1);
                }
            }
        }
    }

    @objid ("355bac11-55b7-11e2-877f-002564c97630")
    protected void read_2(IDiagramReader in) {
        this.roleRef = (MRef) in.readProperty("representedRole");
        this.roleEl = (LinkEnd) resolveRef(this.roleRef);
        
        if (this.roleEl != null) {
            this.oppositeRole = this.roleEl.getOpposite();
            updateNavigability();
        } else {
            this.fromNavigable = false;
            this.toNavigable = true;
        }
    }

}
