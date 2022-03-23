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
package org.modelio.uml.ui.dg.usecase;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramLink;
import org.modelio.diagram.api.dg.common.DiagramDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Represents an usecase diagram.
 */
@objid ("f041e484-0995-4042-a566-c8291eee1f0a")
public class UseCaseDiagramDG extends DiagramDG {
    /**
     * Initialize the activity diagram graphic element.
     * @param diagramHandle The diagram handle
     * @param node The internal graphic node representing the diagram.
     */
    @objid ("4b35ec3e-58d1-4eca-a1d7-feafd2f32887")
    public  UseCaseDiagramDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("19ac10f4-93a8-45a7-a301-d757d28c96a2")
    @Override
    public DiagramNode getParent() {
        return null;
    }

    @objid ("a601356b-7608-4bc9-be7d-fd2c4d029052")
    @Override
    public List<IDiagramLink> getFromLinks() {
        return Collections.emptyList();
    }

    @objid ("d18f4c2a-0387-4470-930a-0b53ab40f062")
    @Override
    public List<IDiagramLink> getToLinks() {
        return Collections.emptyList();
    }

}
