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
package org.modelio.diagram.elements.umlcommon.diagramview;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a different diagram to display in the current diagram.
 */
@objid ("3ab5a87b-6d23-4114-888f-fbcaa60df6a6")
public class GmDiagramViewBody extends GmNoStyleCompositeNode {
    @objid ("a351482c-021b-42be-a419-13d3bb08aca6")
    private static final int MAJOR_VERSION = 0;

    @objid ("f5c51630-e4db-4223-9ab8-ff91eb1c76e1")
    private static final String MINOR_PREFIX = "GmDiagramViewBody.";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f4b70860-d6b8-4d4b-ae9b-c438ced1da78")
    private static final int MINOR_VERSION = 0;

    @objid ("0cca84d5-e1bc-4e39-8ada-9a4d6986aaf2")
    private AbstractDiagram viewedDiagram;

    /**
     * For deserialization only.
     */
    @objid ("936c10fd-6995-4e9d-93ba-69e2663bb7eb")
    public  GmDiagramViewBody() {
        super();
    }

    /**
     * Creates the model.
     * @param diagram The diagram owning this diagram view
     * @param viewedDiagram The represented diagram.
     * @param ref The represented diagram reference.
     */
    @objid ("f2ad1ce6-8754-4bed-8895-4253e8d3d76d")
    public  GmDiagramViewBody(final IGmDiagram diagram, AbstractDiagram viewedDiagram, MRef ref) {
        super(diagram, ref);
        this.viewedDiagram = viewedDiagram;
        
    }

    @objid ("b009499f-10ef-4d73-80c6-ac00173d206e")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        return null;
    }

    @objid ("d8e0406d-98aa-4d43-8613-f0c6a02ca8a2")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("23d31368-3010-4842-88f4-508f9294ce15")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("096328b5-0ad8-4040-832a-80311af14749")
    @Override
    public AbstractDiagram getRelatedElement() {
        return this.viewedDiagram;
    }

    @objid ("d29b90fe-06d3-4b5b-b214-583f025f3ee9")
    @Override
    public boolean isUserEditable() {
        return false;
    }

    @objid ("e300ba9d-214e-4875-ad7a-f3a516202c36")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        final int readVersion = GmAbstractObject.readMinorVersion(in, GmDiagramViewBody.MINOR_PREFIX);
        switch (readVersion) {
        case 0:
            read_0(in);
            break;
        default:
            assert false : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        
    }

    @objid ("d51484bd-6e25-4d54-87b6-000f2dee5fa0")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.viewedDiagram = (AbstractDiagram) resolveRef(getRepresentedRef());
        
    }

    @objid ("c80e5dfe-e5ad-46d3-b9d0-971e2451b822")
    @Override
    public void write(final IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, GmDiagramViewBody.MINOR_PREFIX, GmDiagramViewBody.MINOR_VERSION);
        
    }

    @objid ("2cd414aa-4796-4529-842e-48b579094253")
    @Override
    public int getMajorVersion() {
        return GmDiagramViewBody.MAJOR_VERSION;
    }

}
