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
package org.modelio.uml.sequencediagram.editor.elements.modelmanipulation;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.facilities.interaction.InteractionHelper;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionUse;

/**
 * Helper class for manipulating the model of Sequence Diagrams.
 * 
 * @author fpoyer
 */
@objid ("feaa0c2c-6179-4501-91d3-241eec658a2f")
public class SequenceModelManipulationServices {
    @objid ("70c5f0a2-01cf-4a6d-8ee0-119e392df9dc")
    private final Collection<InteractionFragment> existingFragments;

    @objid ("5dd09812-acc4-4c5c-923b-28997aaa6971")
    public  SequenceModelManipulationServices(Interaction interaction) {
        this.existingFragments = InteractionHelper.getAllInteractionFragments(interaction);
    }

    /**
     * Ensure there is at least <i>minDelta</i> vertical space between the given line
     * and other interaction fragments.
     * @param newLine a line to insert
     * @param minDelta the minimum space between the new line and interaction fragments below.
     * @since 3.7.1
     */
    @objid ("c5d42cba-e7cd-43eb-a211-60c9c1495597")
    public void ensureMinDelta(int newLine, int minDelta) {
        int yToMove = Integer.MAX_VALUE;
        for (InteractionFragment f : this.existingFragments) {
            {
                int y = f.getLineNumber();
                int delta = y - newLine;
                if (delta > 0 && delta < minDelta && y < yToMove) {
                    yToMove = y;
                }
            }
            
            if (f instanceof InteractionUse) {
                InteractionUse endLineHolder = (InteractionUse) f;
                int y = endLineHolder.getEndLineNumber();
                int delta = y - newLine;
                if (delta > 0 && delta < minDelta && y < yToMove) {
                    yToMove = y;
                }
            } else if (f instanceof InteractionOperand) {
                InteractionOperand endLineHolder = (InteractionOperand) f;
                int y = endLineHolder.getEndLineNumber();
                int delta = y - newLine;
                if (delta > 0 && delta < minDelta && y < yToMove) {
                    yToMove = y;
                }
            }
               }
        
        if (yToMove < Integer.MAX_VALUE) {
            final int moveDelta = minDelta - (yToMove - newLine);
            moveAllBelow(yToMove, moveDelta);
               }
        
    }

    /**
     * Move all interaction fragments situated at <i>yToMove</i> or below by <i>moveDelta</i> .
     * @param yToMove minimum y coordinate of interaction fragments to move, included
     * @param moveDelta the move delta
     */
    @objid ("a7726f01-1d31-4a14-8b70-25256e6add53")
    public void moveAllBelow(int yToMove, final int moveDelta) {
        for (InteractionFragment f : this.existingFragments) {
            int y = f.getLineNumber();
            if (y >= yToMove) {
                f.setLineNumber(y + moveDelta);
            }
            if (f instanceof InteractionUse) {
                InteractionUse endLineHolder = (InteractionUse) f;
                int endY = endLineHolder.getEndLineNumber();
                if (endY >= yToMove) {
                    endLineHolder.setEndLineNumber(endY + moveDelta);
                }
            } else if (f instanceof InteractionOperand) {
                InteractionOperand endLineHolder = (InteractionOperand) f;
                int endY = endLineHolder.getEndLineNumber();
                if (endY >= yToMove) {
                    endLineHolder.setEndLineNumber(endY + moveDelta);
                }
            }
        }
        
    }

}
