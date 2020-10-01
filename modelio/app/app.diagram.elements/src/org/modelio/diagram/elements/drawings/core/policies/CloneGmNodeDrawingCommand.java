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
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.IGmNodeDrawing;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.model.api.MTools;

/**
 * Commands that copies a node drawing in the diagram.
 */
@objid ("eed9ba31-b815-498d-87e3-a3440e90435c")
public class CloneGmNodeDrawingCommand extends Command {
    @objid ("dace0080-d8e7-4aa6-976c-8bdc8a1a8ada")
    private Object constraint;

    @objid ("7fe4ca16-6176-4d6c-bb28-bdbca3a8b3fc")
    private IGmDrawingLayer layer;

    @objid ("250fbf90-f99b-4d56-9d75-f229a91ab97b")
    private IGmNodeDrawing toCopy;

    /**
     * Creates a node creation command.
     * 
     * @param layer The parent layer
     * @param newNodeType the node to create
     * @param constraint The initial constraint of the created node.
     */
    @objid ("ddbcdd4f-ba78-4317-a345-40ff6ba80a0b")
    public CloneGmNodeDrawingCommand(IGmDrawingLayer layer, IGmNodeDrawing newNodeType, Object constraint) {
        this.layer = layer;
        this.toCopy = newNodeType;
        this.constraint = constraint;
    }

    @objid ("41b411f8-e0e5-49a4-a7fc-7eed29779401")
    @Override
    public boolean canExecute() {
        // The layer must be valid and modifiable.
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
    @objid ("db7f3c97-1871-4855-958d-3659b2211f8a")
    protected Object getConstraint() {
        return this.constraint;
    }

    @objid ("740a67ed-1e7d-49d2-a633-d63a5a80e55b")
    @Override
    public void execute() {
        try {
            // copy the node
            IGmNodeDrawing newNode = this.toCopy.getClass().getConstructor(IGmDiagram.class, String.class).newInstance(this.layer.getDiagram(), UUID.randomUUID().toString());
            newNode.setLayoutData(this.constraint);
            newNode.setLabel(this.toCopy.getLabel());
            // TODO : handle future composite nodes
        
            // Copy graphic options
            IStyle origStyle = this.toCopy.getPersistedStyle();
            IStyle newStyle = newNode.getPersistedStyle();
            newStyle.setCascadedStyle(origStyle.getCascadedStyle());
            for (StyleKey k : origStyle.getLocalKeys()) {
                newStyle.setProperty(k, origStyle.getProperty(k));
            }
        
            this.layer.addChild(newNode);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            // TODO : report in another way ?
            throw new RuntimeException(e.toString(), e);
        }
    }

}
