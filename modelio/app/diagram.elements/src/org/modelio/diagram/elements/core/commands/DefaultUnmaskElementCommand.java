/* 
 * Copyright 2013-2018 Modeliosoft
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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that unmask an element.
 */
@objid ("7f3bdca0-1dec-11e2-8cad-001ec947c8cc")
public class DefaultUnmaskElementCommand extends Command {
    @objid ("7f3bdca7-1dec-11e2-8cad-001ec947c8cc")
    private MObject toUnmask;

    @objid ("7f3bdca8-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode parentNode;

    @objid ("ff86a9ad-5f48-43ad-879d-71298d62924b")
    private Point location;

    @objid ("bf177912-30fa-4ce8-a593-d65fa891d7e4")
    private Dimension size;

    /**
     * Constructor.
     * @param parentNode The parent node
     * @param toUnmask the element to unmask
     * @param location the location of the element to unmask.
     * @param size the size of the unmasked element.
     */
    @objid ("7f3bdcac-1dec-11e2-8cad-001ec947c8cc")
    public DefaultUnmaskElementCommand(GmCompositeNode parentNode, MObject toUnmask, Point location, Dimension size) {
        this.parentNode = parentNode;
        this.location = location;
        this.size = size;
        this.toUnmask = toUnmask;
    }

    @objid ("7f3bdcb7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        IGmDiagram diagram = this.parentNode.getDiagram();
        
        // Show the new element in the diagram (ie create its Gm )
        Rectangle rect = this.size != null ? new Rectangle(this.location, this.size)
                : new Rectangle(this.location, new Dimension(-1, -1));
        
        diagram.unmask(this.parentNode, this.toUnmask, rect);
    }

    @objid ("7f3bdcba-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        return (MTools.getAuthTool().canModify(this.parentNode.getDiagram().getRelatedElement()));
    }

}
