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
package org.modelio.uml.usecasediagram.editor.elements.usecasedependency;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("5e7c8a9a-55b7-11e2-877f-002564c97630")
public class GmExtensionPointLabel extends GmElementLabel {
    @objid ("5e7c8a9e-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("5e7c8aa1-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5e7c8aa3-55b7-11e2-877f-002564c97630")
    public  GmExtensionPointLabel() {
        // serialization
    }

    @objid ("5e7c8aa6-55b7-11e2-877f-002564c97630")
    public  GmExtensionPointLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("5e7c8ab1-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        final UseCaseDependency useCaseDependency = (UseCaseDependency) getRelatedElement();
        
        StringBuilder extLabel = new StringBuilder();
        List<ExtensionPoint> extensionLocation = useCaseDependency.getExtensionLocation();
        for (int i = 0; i < extensionLocation.size(); i++) {
            extLabel.append(extensionLocation.get(i).getName());
            if (i < extensionLocation.size() - 1) {
                extLabel.append(", ");
            }
        }
        return extLabel.toString();
    }

    @objid ("5e7c8ab6-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmExtensionPointLabel.");
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

    @objid ("5e7c8abc-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmExtensionPointLabel.", GmExtensionPointLabel.MINOR_VERSION);
        
    }

    @objid ("5e7c8ac2-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("5e7e1139-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
