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
package org.modelio.linkeditor.gef;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.modelio.linkeditor.gef.background.BackgroundEditPart;
import org.modelio.linkeditor.gef.edge.EdgeEditPart;
import org.modelio.linkeditor.gef.node.BusEditPart;
import org.modelio.linkeditor.gef.node.NodeEditPart;
import org.modelio.linkeditor.panel.model.BackgroundModel;
import org.modelio.linkeditor.panel.model.EdgeBus;
import org.modelio.linkeditor.panel.model.GraphNode;

/**
 * Edit part factory for the Link Editor.
 */
@objid ("1ba1eb56-5e33-11e2-b81d-002564c97630")
public class LinkEditorEditPartFactory implements EditPartFactory {
    @objid ("d4995d77-5efd-11e2-a8be-00137282c51b")
    private final IEclipseContext context;

    @objid ("1ba1eb5a-5e33-11e2-b81d-002564c97630")
    @Override
    public EditPart createEditPart(final EditPart parent, final Object model) {
        EditPart editPart;
        if (model instanceof BackgroundModel) {
            editPart = new BackgroundEditPart(this.context);
            editPart.setModel(model);
            return editPart;
        }
        
        if (model instanceof GraphNode) {
            editPart = new NodeEditPart();
            editPart.setModel(model);
            return editPart;
        }
        
        if (model instanceof Edge) {
            editPart = new EdgeEditPart();
            editPart.setModel(model);
            return editPart;
        }
        
        if (model instanceof EdgeBus) {
            editPart = new BusEditPart();
            editPart.setModel(model);
            return editPart;
        }
        return null;
    }

    @objid ("d4995d79-5efd-11e2-a8be-00137282c51b")
    public  LinkEditorEditPartFactory(IEclipseContext context) {
        this.context = context;
    }

}
