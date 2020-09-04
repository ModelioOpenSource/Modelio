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

package org.modelio.diagram.elements.common.abstractdiagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.vcore.model.api.MTools;

/**
 * Command that create a Constraint in Ob model, links it to several elements and then unmask it.
 * 
 * @author fpoyer
 */
@objid ("7e0d11fa-1dec-11e2-8cad-001ec947c8cc")
public class CreateConstraintCommand extends Command {
    @objid ("7e0d11fe-1dec-11e2-8cad-001ec947c8cc")
    private ModelioLinkCreationContext context;

    @objid ("7e0d11ff-1dec-11e2-8cad-001ec947c8cc")
    private List<GmModel> sourceModels;

    @objid ("7e0d1202-1dec-11e2-8cad-001ec947c8cc")
    private Object constraint;

    @objid ("7e0d1203-1dec-11e2-8cad-001ec947c8cc")
    private GmCompositeNode parentNode;

    /**
     * Creates a constraint creation command.
     * @param sourceModels The models that are to be linked to the created constraint.
     * @param parentNode The parent node unmasking the "body" of the constraint.
     * @param context Details on the MObject and/or the node to create
     * @param constraint The initial constraint of the created node.
     */
    @objid ("7e0d1204-1dec-11e2-8cad-001ec947c8cc")
    public CreateConstraintCommand(final List<GmModel> sourceModels, final GmCompositeNode parentNode, final ModelioLinkCreationContext context, final Object constraint) {
        this.sourceModels = sourceModels;
        this.parentNode = parentNode;
        this.context = context;
        this.constraint = constraint;
    }

    @objid ("7e0d1211-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        // the diagram must be modifiable 
        if (!MTools.getAuthTool().canModify(this.parentNode.getDiagram().getRelatedElement())) {
            return false;
        }
        
        // All sourceNodes must be modifiable.
        for (GmModel sourceModel : this.sourceModels) {
            if (!MTools.getAuthTool().canModify(sourceModel.getRelatedElement())) {
                return false;
            }
        }
        return true;
    }

    @objid ("7e0d1216-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        
        Constraint newElement = (Constraint) this.context.getElementToUnmask();
        
        if (newElement == null) {
            // Create the constraint...
            final IStandardModelFactory modelFactory = diagram.getModelManager().getModelFactory().getFactory(IStandardModelFactory.class);
            newElement = modelFactory.createConstraint();
        
            // ... and attach it to it to all source models.
            for (GmModel sourceModel : this.sourceModels) {
                newElement.getConstrainedElement().add((UmlModelElement) sourceModel.getRelatedElement());
            }
        
            // Attach the stereotype if needed.
            if (this.context.getStereotype() != null) {
                newElement.getExtension().add(this.context.getStereotype());
            }
        
        }
        
        // Show the new element in the diagram (ie create its Gm )
        diagram.unmask(this.parentNode, newElement, this.constraint);
    }

}
