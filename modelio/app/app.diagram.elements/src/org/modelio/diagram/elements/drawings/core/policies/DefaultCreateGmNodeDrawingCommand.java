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

package org.modelio.diagram.elements.drawings.core.policies;

import java.lang.reflect.InvocationTargetException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.IGmNodeDrawing;
import org.modelio.vcore.model.api.MTools;

/**
 * Commands that creates a new node drawing in the diagram.
 */
@objid ("f38d7b23-6af8-49b6-99c6-2a873f8529fd")
public class DefaultCreateGmNodeDrawingCommand extends Command {
    @objid ("90403d84-643d-4cc3-9e55-99b2e33de454")
    private String drawingIdentifier;

    @objid ("c25a3daf-0836-4252-8fd1-43df416c385c")
    private Object constraint;

    @objid ("db765307-1ab3-44cc-8a5e-47e3649aa9ab")
    private IGmDrawingLayer layer;

    @objid ("0794a979-9cdd-4854-876f-a851a2d6ca16")
    private Class<? extends IGmNodeDrawing> newNodeType;

    /**
     * Creates a node creation command.
     * 
     * @param layer The parent layer
     * @param newNodeType the node to create
     * @param drawingIdentifier the drawing identifier.
     * @param constraint The initial constraint of the created node.
     */
    @objid ("0f667fd0-6eaf-4672-b6c8-377503a43049")
    public DefaultCreateGmNodeDrawingCommand(IGmDrawingLayer layer, Class<? extends IGmNodeDrawing> newNodeType, String drawingIdentifier, Object constraint) {
        this.layer = layer;
        this.newNodeType = newNodeType;
        this.drawingIdentifier = drawingIdentifier;
        this.constraint = constraint;
    }

    @objid ("c1acf188-041a-4e05-b433-f7e5064a25e0")
    @Override
    public boolean canExecute() {
        // The diagram must be valid and modifiable.
        if (!MTools.getAuthTool().canModify(this.layer.getRelatedElement())) {
            return false;
        }
        return true;
    }

    /**
     * Get the initial layout constraint.
     * 
     * @return the initial layout constraint.
     */
    @objid ("77cc7a82-16fe-4563-b425-1d2e92d568bc")
    protected Object getConstraint() {
        return this.constraint;
    }

    @objid ("c749fd66-be00-4a1b-b358-9fe2ce8f4950")
    @Override
    public void execute() {
        try {
            IGmNodeDrawing newNode = this.newNodeType.getConstructor(IGmDiagram.class, String.class)
                    .newInstance(this.layer.getDiagram(), this.drawingIdentifier);
            newNode.setLayoutData(this.constraint);
            this.layer.addChild(newNode);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            // TODO : report in another way ?
            throw new RuntimeException(e.toString(), e);
        }
    }

}
