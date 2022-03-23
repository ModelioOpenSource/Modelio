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
package org.modelio.diagram.elements.umlcommon.namespaceuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * {@link ImpactLink} link label displayed on the link.
 */
@objid ("817cd5a6-1dec-11e2-8cad-001ec947c8cc")
public class GmNamespaceUseLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("817cd5a8-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("817cd5ab-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * @param diagram the diagram
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("817cd5ad-1dec-11e2-8cad-001ec947c8cc")
    public  GmNamespaceUseLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("817cd5b4-1dec-11e2-8cad-001ec947c8cc")
    public  GmNamespaceUseLabel() {
        
    }

    @objid ("817cd5b7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String computeLabel() {
        MObject relatedElement = getRelatedElement();
        if (relatedElement != null) {
            ImpactLink node = (ImpactLink) relatedElement;
        
            // Name should be "CommunicationNodeName"
            return ("use(" + node.getCauses().size() + " cause(s))");
        }
        // No name to display
        return "";
    }

    @objid ("817cd5bc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNamespaceUseLabel.");
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

    @objid ("817cd5c0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNamespaceUseLabel.", GmNamespaceUseLabel.MINOR_VERSION);
        
    }

    @objid ("817cd5c4-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("817cd5c7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmNamespaceUseLabel.MAJOR_VERSION;
    }

    @objid ("07b4eadd-67d8-495e-aa18-2ad428bbcb14")
    @Override
    public boolean isWrapped() {
        return false;
    }

}
