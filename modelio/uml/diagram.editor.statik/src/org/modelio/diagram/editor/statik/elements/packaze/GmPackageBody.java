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

package org.modelio.diagram.editor.statik.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.common.freezone.GmBodyFreeZone;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the content of the package. Specialisation of the default {@link GmBodyFreeZone} that handles the specific
 * auto-unmask strategies.
 * 
 * @author phv
 */
@objid ("361d7586-55b7-11e2-877f-002564c97630")
public class GmPackageBody extends GmBodyFreeZone {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("361d758a-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("361d758d-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("361d758f-55b7-11e2-877f-002564c97630")
    public GmPackageBody() {
    }

    /**
     * Creates the package body.
     * 
     * @param diagram The diagram
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("361efbf9-55b7-11e2-877f-002564c97630")
    public GmPackageBody(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("361efc02-55b7-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        refreshFromObModel();
    }

    @objid ("361efc08-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final Package packaze = (Package) getRelatedElement();
        if (packaze == null || !packaze.isValid())
            return;
        
        final StyleKey.UmaskByVisibilityStragegy mode = this.getDisplayedStyle().getProperty(GmPackageStructuredStyleKeys.UNMASKINGSTRATEGY);
        
        // TODO get a better layout
        int size = getChildren().size();
        final Rectangle constraint = new Rectangle(10 * size + 5, 10 * size + 5, -1, -1);
        
        // Unmask all missing children
        switch (mode) {
        case ALL:
            for (ModelTree e : packaze.getOwnedElement(NameSpace.class)) {
                if (getChild(new MRef(e)) == null) {
                    getDiagram().unmask(this, e, constraint.getCopy());
                    constraint.translate(10, 10);
                }
            }
            break;
        
        case ALL_PUBLIC:
            for (ModelTree e : packaze.getOwnedElement(NameSpace.class)) {
                NameSpace ns = (NameSpace) e;
                if (ns.getVisibility() == VisibilityMode.PUBLIC) {
                    if (getChild(new MRef(ns)) == null) {
                        getDiagram().unmask(this, ns, constraint.getCopy());
                        constraint.translate(10, 10);
                    }
                }
            }
            break;
        
        case ALL_NON_PRIVATE:
            for (ModelTree e : packaze.getOwnedElement(NameSpace.class)) {
                NameSpace ns = (NameSpace) e;
                if (ns.getVisibility() != VisibilityMode.PRIVATE) {
                    if (getChild(new MRef(ns)) == null) {
                        getDiagram().unmask(this, ns, constraint.getCopy());
                        constraint.translate(10, 10);
                    }
                }
        
            }
            break;
        case MANUAL:
            // unmask or hide nothing.
            break;
        }
        
        final Boolean unmaskInstances = this.getDisplayedStyle().getProperty(GmPackageStructuredStyleKeys.INSTANCEUNMASKING);
        
        // Unmask all missing children
        if (unmaskInstances) {
            for (Instance e : packaze.getDeclared()) {
                if (getChild(new MRef(e)) == null) {
                    getDiagram().unmask(this, e, constraint.getCopy());
                    constraint.translate(10, 10);
                }
            }
        }
    }

    @objid ("361efc0b-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == GmPackageStructuredStyleKeys.UNMASKINGSTRATEGY) {
            refreshFromObModel();
        } else {
            super.styleChanged(property, newValue);
        }
    }

    @objid ("361efc12-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        refreshFromObModel();
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("361efc18-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmPackageBody.");
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

    @objid ("361efc1e-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmPackageBody.", GmPackageBody.MINOR_VERSION);
    }

    @objid ("361efc24-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("361efc29-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
