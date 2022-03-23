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
package org.modelio.diagram.elements.core.commands;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.vcore.model.api.MTools;

/**
 * Command that adds a bend point to a {@link IGmLinkObject}.
 */
@objid ("7f32532b-1dec-11e2-8cad-001ec947c8cc")
public class DefaultAddBendPointCommand extends Command {
    @objid ("7f32532f-1dec-11e2-8cad-001ec947c8cc")
    private final int index;

    @objid ("7f325331-1dec-11e2-8cad-001ec947c8cc")
    private final IGmLinkObject gmLink;

    @objid ("66fd7d87-1e83-11e2-8cad-001ec947c8cc")
    private final Bendpoint newpoint;

    /**
     * Initialize the command.
     * @param gmLink The link to modify
     * @param index The index where the new bend point will be inserted.
     * @param newpoint The bend point to insert.
     */
    @objid ("7f325337-1dec-11e2-8cad-001ec947c8cc")
    public  DefaultAddBendPointCommand(IGmLinkObject gmLink, int index, Bendpoint newpoint) {
        this.index = index;
        this.newpoint = newpoint;
        this.gmLink = gmLink;
        
    }

    @objid ("7f34b586-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings("unchecked")
    @Override
    public void execute() {
        // we need a new list in all cases otherwise no property change is detected...
        assert (this.gmLink.getLayoutData() != null);
        IGmPath path = new GmPath((IGmPath) this.gmLink.getLayoutData());
        
        List<Bendpoint> bendpoints = new ArrayList<>((List<Bendpoint>) path.getPathData());
        path.setPathData(bendpoints);
        
        bendpoints.add(this.index, this.newpoint);
        
        this.gmLink.setLayoutData(path);
        
    }

    @objid ("7f34b589-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        return MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement());
    }

}
