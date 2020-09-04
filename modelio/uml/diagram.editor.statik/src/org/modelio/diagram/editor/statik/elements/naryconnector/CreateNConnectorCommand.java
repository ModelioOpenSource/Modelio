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

package org.modelio.diagram.editor.statik.elements.naryconnector;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.helpers.UnmaskHelper;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.MTools;

/**
 * Command that create a n-ary Connector in Ob model, links it to several elements and then unmask it.
 * 
 * @author cmarin
 */
@objid ("35d43789-55b7-11e2-877f-002564c97630")
public class CreateNConnectorCommand extends Command {
    @objid ("35d4378e-55b7-11e2-877f-002564c97630")
    private List<IGmLinkable> sourceModels;

    @objid ("611acb2f-5bd5-11e2-9e33-00137282c51b")
    private EditPart editPart;

    @objid ("a58ab14b-55c2-11e2-9337-002564c97630")
    private ModelioLinkCreationContext context;

    @objid ("a58ab14f-55c2-11e2-9337-002564c97630")
    private GmCompositeNode parentNode;

    /**
     * Initial layout constraint
     */
    @objid ("396ac692-111e-4d2a-b466-926874eb31eb")
    private Rectangle layoutConstraint;

    /**
     * Creates a n-ary association creation command.
     * @param editPart the edit part producing this command
     * @param sourceModels The models that are to be linked to the created association.
     * @param parentNode The parent node unmasking the "diamond" of the association.
     * @param context Details on the MObject and/or the node to create
     * @param constraint The initial layout constraint of the association diamond.
     */
    @objid ("35d5bdfa-55b7-11e2-877f-002564c97630")
    public CreateNConnectorCommand(final EditPart editPart, final List<IGmLinkable> sourceModels, final GmCompositeNode parentNode, final ModelioLinkCreationContext context, final Rectangle constraint) {
        this.editPart = editPart;
        this.sourceModels = sourceModels;
        this.parentNode = parentNode;
        this.context = context;
        this.layoutConstraint = constraint;
    }

    @objid ("35d5be0f-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        // the diagram must be modifiable 
        if (!MTools.getAuthTool().canModify(this.parentNode.getDiagram().getRelatedElement())) {
            return false;
        }
        
        // Must have at least 3 ends
        if (this.sourceModels.size() < 3) {
            return false;
        }
        
        // All sourceNodes must be modifiable.
        for (IGmLinkable sourceModel : this.sourceModels) {
            if (!MTools.getAuthTool().canModify(sourceModel.getRelatedElement())) {
                return false;
            }
        }
        return true;
    }

    @objid ("35d5be14-55b7-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.parentNode.getDiagram();
        IModelManager modelManager = diagram.getModelManager();
        
        NaryConnector newNaryConnector = (NaryConnector) this.context.getElementToUnmask();
        ArrayList<NaryConnectorEnd> createdAssocs = new ArrayList<>(this.sourceModels.size());
        
        if (newNaryConnector == null) {
        
            // Configure element from properties
            final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
        
            // Create the association node...
            final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
            newNaryConnector = modelFactory.createNaryConnector();
        
            // ... and create all roles.
            for (IGmLinkable sourceModel : this.sourceModels) {
                final Instance el = (Instance) sourceModel.getRelatedElement();
                final NaryConnectorEnd role = modelFactory.createNaryConnectorEnd();
                role.setSource(el);
                role.setNaryLink(newNaryConnector);
                createdAssocs.add(role);
        
                elementConfigurer.configure(role, this.context.getProperties());
            }
        
            // Attach the stereotype if needed.
            if (this.context.getStereotype() != null) {
                newNaryConnector.getExtension().add(this.context.getStereotype());
            }
        }
        
        // Show the new element in the diagram (ie create its Gm )
        diagram.unmask(this.parentNode, newNaryConnector, this.layoutConstraint);
        
        // Unmask all roles
        EditPartViewer viewer = this.editPart.getViewer();
        for (NaryConnectorEnd r : createdAssocs) {
            Command cmd = UnmaskHelper.getUnmaskCommand(viewer, r, this.layoutConstraint.getCenter());
            cmd.execute();
        }
    }

}
