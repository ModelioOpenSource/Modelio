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

package org.modelio.diagram.editor.sequence.elements.stateinvariant;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Creation Command for Execution. Creates (and Unmask) Execution on a Lifeline or on another Execution.
 */
@objid ("d9947e5a-55b6-11e2-877f-002564c97630")
public class CreateStateInvariantCommand extends Command {
    @objid ("506b9db6-55c2-11e2-9337-002564c97630")
    private GmCompositeNode parentNode;

    @objid ("12b0e66c-901a-4872-998e-bdb38a68f192")
    private Rectangle initialLayoutData;

    /**
     * C'tor.
     * 
     * @param parentNode the node into which the created execution should be unmasked.
     * @param initialLayoutData the initial layout data to use. X coordinate will be ignored, since it will be updated in the container's layout.
     */
    @objid ("d9947e60-55b6-11e2-877f-002564c97630")
    public CreateStateInvariantCommand(final GmCompositeNode parentNode, final Rectangle initialLayoutData) {
        this.initialLayoutData = initialLayoutData;
        this.parentNode = parentNode;
    }

    @objid ("d9947e69-55b6-11e2-877f-002564c97630")
    @Override
    public void execute() {
        // Create the MObject
        MObject parentElement = this.parentNode.getRelatedElement();
        final IGmDiagram diagram = this.parentNode.getDiagram();
        IModelManager modelManager = diagram.getModelManager();
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        
        Interaction interaction = null;
        Lifeline lifeline = null;
        
        if (parentElement instanceof Lifeline) {
            lifeline = ((Lifeline) parentElement);
            interaction = lifeline.getOwner();
        }
        
        // Use the collected elements to create and initialise the execution.
        StateInvariant newStateInvariant = modelFactory.createStateInvariant();
        newStateInvariant.setEnclosingInteraction(interaction);
        newStateInvariant.getCovered().add(lifeline);
        newStateInvariant.setLineNumber(this.initialLayoutData.y);
        newStateInvariant.setEndLineNumber(this.initialLayoutData.bottom());
        newStateInvariant.setName(modelManager.getModelServices().getElementNamer().getUniqueName(newStateInvariant));
        
        // Show the new element in the diagram (ie create its Gm )
        diagram.unmask(this.parentNode, newStateInvariant, this.initialLayoutData);
    }

}
