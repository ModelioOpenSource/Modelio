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

package org.modelio.uml.ui.dg.activity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.diagram.api.dg.DGFactory;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramNode;
import org.modelio.diagram.editor.activity.elements.clause.GmClause;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'Clause' element.
 */
@objid ("781803ea-6b10-461f-9d7a-e6609d5bdf06")
public class ClauseDG extends DiagramNode {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("749a21f6-747f-4a35-8a60-0e797367f0f9")
    public ClauseDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    @objid ("df2d05ab-929a-4a70-b6d3-12b3cf8a7701")
    @Override
    public List<IDiagramNode> getNodes() {
        GmCompositeNode innerZone = ((GmClause) this.gmNode).getInnerZone();
        if (innerZone != null) {
            return DGFactory.getInstance().getDiagramNodes(this.diagramHandle, innerZone.getVisibleChildren());
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("cb990625-28e2-4ba0-a549-bb3b86f4a328")
    @Override
    public Collection<IDiagramNode> getNodes(Role role) {
        if (role == Role.INNER) {
            return getNodes();
        } else {
            return Collections.emptyList();
        }
    }

}
