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

package org.modelio.uml.ui.dg.statik;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.common.PortContainerDG;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'CommunicationInteraction' element.
 */
@objid ("9850d75c-a596-473f-9ce6-212e287cf628")
public class CommunicationInteractionDG extends PortContainerDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("504b7719-68b2-4730-b569-d4cf79596f69")
    public CommunicationInteractionDG(final DiagramHandle diagramHandle, final GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("42049c2d-1ac0-463c-b0af-b7926e756c38")
    @Override
    protected List<IDiagramNode> getPrimaryChildrenNodes() {
        return Collections.emptyList();
    }

}
