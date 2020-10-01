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

package org.modelio.uml.activitydiagram.editor.elements.pincontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityAction;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for all nodes that can contain pins.
 */
@objid ("2b3a663a-55b6-11e2-877f-002564c97630")
public abstract class GmPinContainer extends GmPortContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2b3a663e-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2b3a6641-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * 
     * @param diagram The diagram in which this port container will be unmasked.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("2b3a6643-55b6-11e2-877f-002564c97630")
    public GmPinContainer(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("2b3a664e-55b6-11e2-877f-002564c97630")
    public GmPinContainer() {
        super();
    }

    @objid ("2b3a6651-55b6-11e2-877f-002564c97630")
    @Override
    public abstract ActivityAction getRepresentedElement();

    @objid ("2b3a6656-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        if (this.getRelatedElement() != null && this.getRelatedElement().isValid()) {
            refreshPinsFromObModel();
        }
    }

    @objid ("2b3becda-55b6-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final IStyle changedStyle) {
        super.styleChanged(changedStyle);
        
        if (this.getRelatedElement() != null && this.getRelatedElement().isValid()) {
            refreshPinsFromObModel();
        }
    }

    @objid ("2b3bece1-55b6-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        super.styleChanged(property, newValue);
        
        if (property.equals(getStyleKeyStrict(MetaKey.AUTOSHOWPINS))) {
            if (this.getRelatedElement() != null && this.getRelatedElement().isValid()) {
                refreshPinsFromObModel();
            }
        }
    }

    /**
     * Automatically unmask pins if asked.
     */
    @objid ("2b3becea-55b6-11e2-877f-002564c97630")
    private void refreshPinsFromObModel() {
        if (arePinsAutoDisplayed()) {
            final ActivityAction node = getRepresentedElement();
            for (Pin pin : node.getInput()) {
                if (getChild(new MRef(pin)) == null) {
                    GmNodeModel gmPin = getDiagram().unmask(this, pin, Border.West);
                    gmPin.setRoleInComposition(GmPortContainer.PORT_ROLE);
                }
            }
        
            for (Pin pin : node.getOutput()) {
                if (getChild(new MRef(pin)) == null) {
                    GmNodeModel gmPin = getDiagram().unmask(this, pin, Border.East);
                    gmPin.setRoleInComposition(GmPortContainer.PORT_ROLE);
                }
            }
        }
    }

    /**
     * @return true if pins are to be unmasked automatically.
     */
    @objid ("2b3beced-55b6-11e2-877f-002564c97630")
    protected Boolean arePinsAutoDisplayed() {
        return getDisplayedStyle().getProperty(getStyleKeyStrict(MetaKey.AUTOSHOWPINS));
    }

    @objid ("2b3becf3-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPinContainer.");
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

    @objid ("2b3becf9-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPinContainer.", GmPinContainer.MINOR_VERSION);
    }

    @objid ("2b3becff-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2b3bed04-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("2b3bed09-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

}
