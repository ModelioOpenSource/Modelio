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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.vcore.model.api.MTools;

/**
 * Command that moves a link bend point.
 */
@objid ("7f397a43-1dec-11e2-8cad-001ec947c8cc")
public class DefaultMoveBendPointCommand extends Command {
    @objid ("7f397a47-1dec-11e2-8cad-001ec947c8cc")
    private final int index;

    @objid ("7f397a4d-1dec-11e2-8cad-001ec947c8cc")
    private final IGmLinkObject gmLink;

    @objid ("65c9ee2f-1e83-11e2-8cad-001ec947c8cc")
    private final Bendpoint newpoint;

    /**
     * Constructor.
     * 
     * @param gmLink the link to edit
     * @param index the index of the point to move
     * @param newpoint the new bend point position.
     */
    @objid ("7f397a4f-1dec-11e2-8cad-001ec947c8cc")
    public DefaultMoveBendPointCommand(IGmLinkObject gmLink, int index, Bendpoint newpoint) {
        this.index = index;
        this.newpoint = newpoint;
        this.gmLink = gmLink;
    }

    @objid ("7f397a57-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings("unchecked")
    @Override
    public void execute() {
        // we need a new list in all cases otherwise no property change is detected...
        assert (this.gmLink.getLayoutData() != null);
        IGmPath path = new GmPath((IGmPath) this.gmLink.getLayoutData());
        
        List<Bendpoint> bendpoints = (List<Bendpoint>) path.getPathData();
        bendpoints.set(this.index, this.newpoint);
        
        this.gmLink.setLayoutData(path);
    }

    @objid ("7f397a5a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        return MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement());
    }

}
