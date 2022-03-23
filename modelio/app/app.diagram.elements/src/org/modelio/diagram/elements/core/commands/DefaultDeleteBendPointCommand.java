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
 * Command that deletes a link bend point.
 * 
 * @author cmarin
 */
@objid ("7f371802-1dec-11e2-8cad-001ec947c8cc")
public class DefaultDeleteBendPointCommand extends Command {
    @objid ("7f371806-1dec-11e2-8cad-001ec947c8cc")
    private final int index;

    @objid ("7f371808-1dec-11e2-8cad-001ec947c8cc")
    private final IGmLinkObject gmLink;

    /**
     * Constructor.
     * @param iGmLinkObject The link to edit
     * @param index the index of the point to remove.
     */
    @objid ("7f37180a-1dec-11e2-8cad-001ec947c8cc")
    public  DefaultDeleteBendPointCommand(IGmLinkObject iGmLinkObject, int index) {
        this.index = index;
        
        this.gmLink = iGmLinkObject;
        
    }

    @objid ("7f397a38-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings("unchecked")
    @Override
    public void execute() {
        // we need a new list in all cases otherwise no property change is detected...
        IGmPath path = new GmPath(this.gmLink.getPath());
        
        List<Bendpoint> bendpoints = new ArrayList<>((List<Bendpoint>) path.getPathData());
        path.setPathData(bendpoints);
        
        bendpoints.remove(this.index);
        
        this.gmLink.setLayoutData(path);
        
    }

    @objid ("7f397a3b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        return MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement());
    }

}
