/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a label that displays the name of an element and nothing else.
 */
@objid ("7ea80ae2-1dec-11e2-8cad-001ec947c8cc")
public final class GmNameSimpleLabel extends GmElementLabel implements IEditableText {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7ea80ae4-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("7ea80ae7-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates a name label.
     * 
     * @param diagram The diagram
     * @param relatedRef The reference to the related elements.
     */
    @objid ("7ea80ae9-1dec-11e2-8cad-001ec947c8cc")
    public GmNameSimpleLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("7ea80aee-1dec-11e2-8cad-001ec947c8cc")
    public GmNameSimpleLabel() {
        // Empty c'tor.
    }

    @objid ("7ea80af1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IEditableText getEditableText() {
        return this;
    }

    @objid ("7ea80af6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public String getText() {
        return ((ModelElement) getRelatedElement()).getName();
    }

    @objid ("7ea80afb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNameSimpleLabel.");
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

    @objid ("7ea80aff-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setText(String text) {
        ((ModelElement) getRelatedElement()).setName(text);
    }

    @objid ("7ea80b03-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String computeLabel() {
        return ((ModelElement) getRelatedElement()).getName();
    }

    @objid ("7ea80b08-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNameSimpleLabel.", MINOR_VERSION);
    }

    @objid ("7ea80b0c-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("7eaa6d1f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
