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

package org.modelio.diagram.elements.drawings.core;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;

/**
 * Command that moves drawings to another layer
 */
@objid ("7d8669f7-3ac5-4313-bbe2-c2131aa95ded")
public class MoveToLayerCommand extends Command {
    @objid ("bd6e0702-e8ee-4490-b0a7-6174944004c7")
    private Collection<IGmDrawing> toMove = new ArrayList<>();

    @objid ("fbdb4fcc-fab8-419b-af64-5c65651068d3")
    private IGmDrawingLayer targetLayer;

    /**
     * Creates the command.
     */
    @objid ("a4daf866-77ae-4cd7-b5d7-c5709c9c6b8c")
    public MoveToLayerCommand() {
    }

    /**
     * Add elements to move
     * @param els elements to move
     */
    @objid ("d76228d6-51fd-49d2-a8af-2f0fe349244d")
    public void addElements(Collection<IGmDrawing> els) {
        this.toMove.addAll(els);
    }

    /**
     * Set the target layer.
     * @param targetLayer the target layer.
     */
    @objid ("a8bd11f6-9468-461a-b08b-9b5a5f65532c")
    public void setTarget(IGmDrawingLayer targetLayer) {
        this.targetLayer = targetLayer;
    }

    @objid ("0e468530-2461-408f-9584-3790791a12bb")
    @Override
    public void execute() {
        for (IGmDrawing el : this.toMove ) {
            if (el instanceof IGmNodeDrawing) {
                IGmNodeDrawing node = (IGmNodeDrawing) el;
                el.getLayer().removeChild(node);
                this.targetLayer.addChild(node);
            } else if (el instanceof IGmDrawingLink) {
                IGmDrawingLink link = (IGmDrawingLink) el;
                if (link.getFrom() instanceof IGmDrawingLayer) {
                    link.getFrom().removeStartingDrawingLink(link);
                    this.targetLayer.addStartingDrawingLink(link);
                }
                
                if (link.getTo() instanceof IGmDrawingLayer) {
                    link.getTo().removeEndingDrawingLink(link);
                    this.targetLayer.addEndingDrawingLink(link);
                }
            }
        }
    }

    @objid ("38a858ea-36b2-4b2c-ab97-e186d6afeb91")
    @Override
    public boolean canExecute() {
        if (this.targetLayer == null)
            return false;
        
        boolean somethingToDo = false;
        
        for (IGmDrawing el : this.toMove ) {
            if (el.getLayer() != this.targetLayer) {
                if (el instanceof IGmDrawingLink) {
                    // Cannot move a link that starts from something else than the layer
                    IGmDrawingLink l = (IGmDrawingLink) el;
                    if (!(l.getFrom() instanceof IGmDrawingLayer))
                        return false;
                    
                }
                somethingToDo = true;
            }
        }
        return somethingToDo;
    }

}
