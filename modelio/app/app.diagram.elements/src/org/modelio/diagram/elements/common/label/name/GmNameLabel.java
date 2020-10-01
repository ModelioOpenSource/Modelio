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

package org.modelio.diagram.elements.common.label.name;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a label that displays the name, tags and stereotypes of an element.
 * <p>
 * The label visibility is controlled by the {@link MetaKey#SHOWLABEL} meta key.
 */
@objid ("7ea5a86c-1dec-11e2-8cad-001ec947c8cc")
public final class GmNameLabel extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7ea5a86e-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("7ea5a871-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Create a model element label
     * 
     * @param diagram the diagram.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("7ea5a873-1dec-11e2-8cad-001ec947c8cc")
    public GmNameLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("7ea5a878-1dec-11e2-8cad-001ec947c8cc")
    public GmNameLabel() {
        // serialization
    }

    @objid ("7ea80ac5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String computeMainLabel() {
        if (getRelatedElement() != null) {
            return (getRelatedElement().getName());
        } else {
            return "";
        }
    }

    @objid ("7ea80ad2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNameLabel.");
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

    @objid ("7ea80ad6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNameLabel.", MINOR_VERSION);
    }

    @objid ("7ea80ada-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("7ea80add-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
