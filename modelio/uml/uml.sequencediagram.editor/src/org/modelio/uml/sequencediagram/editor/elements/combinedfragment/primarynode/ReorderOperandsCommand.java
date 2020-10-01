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

package org.modelio.uml.sequencediagram.editor.elements.combinedfragment.primarynode;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.common.resizablegroup.ReorderChildrenCommand;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;

/**
 * Much like the {@link ReorderChildrenCommand}, but handling the ObModel parts specific to sequence diagram.
 * 
 * @author fpoyer
 */
@objid ("d8d43bca-55b6-11e2-877f-002564c97630")
public class ReorderOperandsCommand extends Command {
    @objid ("4ffab369-55c2-11e2-9337-002564c97630")
    private GmCompositeNode container;

    @objid ("4ffab36a-55c2-11e2-9337-002564c97630")
    private GmNodeModel childToMove;

    @objid ("4ffab36b-55c2-11e2-9337-002564c97630")
    private GmNodeModel reference;

    @objid ("d8d43bd5-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        // TODO: use a service method that will also move the content of the operand
        CombinedFragment fragment = (CombinedFragment) this.container.getRelatedElement();
        List<InteractionOperand> allOperands = fragment.getOperand();
        
        InteractionOperand operandToMove = (InteractionOperand) this.childToMove.getRelatedElement();
        int indexOfMoved = allOperands.indexOf(operandToMove);
        InteractionOperand referenceOperand = null;
        if (this.reference != null) {
            referenceOperand = (InteractionOperand) this.reference.getRelatedElement();
        }
        int indexOfReference = referenceOperand == null ? allOperands.size()
                : allOperands.indexOf(referenceOperand);
        
        if (indexOfMoved + 1 == indexOfReference) {
            // No real movement, just return.
            return;
        }
        
        // Handle line numbers.
        int shift = operandToMove.getEndLineNumber() - operandToMove.getLineNumber() + 1;
        if (indexOfMoved < indexOfReference) {
            // Moving operand down, so everyone else has to go up
            for (int i = indexOfMoved + 1; i < indexOfReference; ++i) {
                allOperands.get(i).setLineNumber(allOperands.get(i).getLineNumber() - shift);
                allOperands.get(i).setEndLineNumber(allOperands.get(i).getEndLineNumber() - shift);
            }
            shift = allOperands.get(indexOfReference - 1).getEndLineNumber() -
                    allOperands.get(indexOfMoved + 1).getLineNumber() +
                    1;
            operandToMove.setLineNumber(operandToMove.getLineNumber() + shift);
            operandToMove.setEndLineNumber(operandToMove.getEndLineNumber() + shift);
        } else {
            // Moving up, so everyone else has to go down
            for (int i = indexOfReference; i < indexOfMoved; ++i) {
                allOperands.get(i).setLineNumber(allOperands.get(i).getLineNumber() + shift);
                allOperands.get(i).setEndLineNumber(allOperands.get(i).getEndLineNumber() + shift);
            }
            shift = allOperands.get(indexOfMoved - 1).getEndLineNumber() -
                    allOperands.get(indexOfReference).getLineNumber() +
                    1;
            operandToMove.setLineNumber(operandToMove.getLineNumber() - shift);
            operandToMove.setEndLineNumber(operandToMove.getEndLineNumber() - shift);
        }
        
        // Re-order ObModel.
        List<InteractionOperand> reorderedOperands = new ArrayList<>(fragment.getOperand().size());
        for (int i = 0; i < indexOfReference; ++i) {
            if (!operandToMove.equals(allOperands.get(i)))
                reorderedOperands.add(allOperands.get(i));
        }
        reorderedOperands.add(operandToMove);
        for (int i = indexOfReference; i < allOperands.size(); ++i) {
            if (!operandToMove.equals(allOperands.get(i)))
                reorderedOperands.add(allOperands.get(i));
        }
        allOperands.clear();
        allOperands.addAll(reorderedOperands);
        
        // Re-order gm model.
        this.container.moveChild(this.childToMove, this.container.getChildren().indexOf(this.reference));
    }

    /**
     * C'tor.
     * 
     * @param container the container in which children will be moved.
     * @param childToMove the child to move.
     * @param reference the reference: moved child will be moved just before this reference. If it is null, child will be moved at the end of the container.
     */
    @objid ("d8d5c23a-55b6-11e2-877f-002564c97630")
    public ReorderOperandsCommand(final GmCompositeNode container, final GmNodeModel childToMove, final GmNodeModel reference) {
        this.container = container;
        this.childToMove = childToMove;
        this.reference = reference;
    }

}
