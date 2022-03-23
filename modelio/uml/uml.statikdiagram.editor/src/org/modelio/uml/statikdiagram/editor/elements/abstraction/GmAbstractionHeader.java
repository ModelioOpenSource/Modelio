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
package org.modelio.uml.statikdiagram.editor.elements.abstraction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * MObject import header displayed on the node link.
 * 
 * @author cmarin
 */
@objid ("43a128a5-5353-493d-ae7e-31b037c14379")
public class GmAbstractionHeader extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("402b0626-0260-41f2-9781-4fd6edcc6df4")
    private static final int MINOR_VERSION = 0;

    @objid ("23e15af5-70fa-4bdf-a8ad-e41d184ac320")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * @param diagram the diagram
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("384dbae0-b51c-418d-a612-f24db0c610b2")
    public  GmAbstractionHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("901ff967-e4c6-4953-ac33-5d5c03be56f1")
    public  GmAbstractionHeader() {
        
    }

    @objid ("18080172-15c6-4846-8cbb-4a6ad15335cf")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmAbstractionHeader.");
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

    @objid ("240aa6c4-0a07-4d21-84e0-184901cb6a7e")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmAbstractionHeader.", GmAbstractionHeader.MINOR_VERSION);
        
    }

    @objid ("5a853233-3f17-42cd-b65e-16d94ab1d466")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("c2b697d3-ccbb-471d-9fce-37a9ca27fe0e")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("543b599d-90de-4b23-a2b6-03aa87e0dd37")
    @Override
    protected String getMetaclassKeyword() {
        return "abstraction";
    }

}
