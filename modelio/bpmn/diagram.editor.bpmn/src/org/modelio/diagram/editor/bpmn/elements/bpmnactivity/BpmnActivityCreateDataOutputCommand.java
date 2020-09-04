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

package org.modelio.diagram.editor.bpmn.elements.bpmnactivity;

import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.dataoutput.GmBpmnDataOutput;
import org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.anchors.GmLinkAnchor;
import org.modelio.diagram.elements.core.model.IGmDiagram.IModelManager;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmPath;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.metamodel.bpmn.activities.BpmnActivity;
import org.modelio.metamodel.bpmn.objects.BpmnDataAssociation;
import org.modelio.metamodel.bpmn.objects.BpmnDataOutput;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command dedicated to the creation of BpmnBoundaryEvent
 */
@objid ("0f869a44-debd-4ae5-bebb-06f3f3ca5ba3")
public class BpmnActivityCreateDataOutputCommand extends DefaultCreateElementCommand {
    @objid ("74a526d3-692c-445f-9034-7f3d7ecd08f2")
    public BpmnActivityCreateDataOutputCommand(MObject parentElement, GmCompositeNode parentNode, ModelioCreationContext context, Object constraint) {
        super(parentElement, parentNode, context, constraint);
    }

    @objid ("5f1d303d-1a97-4fb5-8f92-8f39e31cf6ca")
    @Override
    public boolean canExecute() {
        final IGmDiagram gmDiagram = getParentNode().getDiagram();
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        return true;
    }

    @objid ("ffc1a874-f00b-45ba-b88d-8d05cf38c8b7")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.getParentNode().getDiagram();
        
        // Otherwise this command should not be used
        assert (getParentElement() instanceof BpmnActivity);
        assert (this.getContext().getElementToUnmask() == null);
        
        // Get the activity
        BpmnActivity activity = (BpmnActivity) getParentElement();
        GmNodeModel gmActivity = getParentNode();
        Rectangle gmActivityBounds = (Rectangle) gmActivity.getLayoutData();
        
        // Create the data input
        BpmnDataOutput dataOutput = createDataOutputElement(diagram.getModelManager());
        
        // Create the data assoc
        BpmnDataAssociation dataAssoc = createDataAssociation(diagram.getModelManager(), activity, dataOutput);
        
        // Unmask the data input
        Rectangle dataOutputConstraint = new Rectangle(gmActivityBounds.x + gmActivityBounds.width, gmActivityBounds.y + gmActivityBounds.height + 10, -1, -1);
        GmBpmnDataOutput gmDataOutput = (GmBpmnDataOutput) diagram.unmask(this.getParentNode().getParentNode(), dataOutput, dataOutputConstraint);
        
        // Umask the data assoc
        IGmPath gmpath = new GmPath();
        gmpath.setSourceAnchor(new GmLinkAnchor(new Dimension(dataOutputConstraint.width / 2, dataOutputConstraint.height / 2)));
        gmpath.setTargetAnchor(new GmLinkAnchor(new Dimension(gmActivityBounds.width / 2, gmActivityBounds.height / 2)));
        gmpath.setPathData(Collections.EMPTY_LIST);
        gmpath.setRouterKind(ConnectionRouterId.DIRECT);
        
        diagram.unmaskLink(dataAssoc, getParentNode(), gmDataOutput, gmpath);
    }

    @objid ("0eaedbd7-d1e3-4c54-ab4e-9b17f2e42890")
    private BpmnDataAssociation createDataAssociation(IModelManager modelManager, BpmnActivity activity, BpmnDataOutput dataOutput) {
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnDataAssociation dataAssoc = modelFactory.createElement(BpmnDataAssociation.class);
        
        dataAssoc.setStartingActivity(activity);
        
        dataAssoc.getSourceRef().add(dataOutput);
        return dataAssoc;
    }

    @objid ("41382555-922b-4a73-8edd-0b3ffd1d2a75")
    private BpmnDataOutput createDataOutputElement(IModelManager modelManager) {
        MObject newElement;
        
        // Create the model element...
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        newElement = modelFactory.createElement(this.getContext().getMetaclass());
        
        assert (newElement instanceof BpmnDataOutput);
        
        BpmnDataOutput dataOutput = (BpmnDataOutput) newElement;
        BpmnActivity activity = (BpmnActivity) getParentElement();
        
        dataOutput.setContainer(activity.getContainer());
        
        // Attach the stereotype if needed.
        if (this.getContext().getStereotype() != null) {
            dataOutput.getExtension().add(this.getContext().getStereotype());
        }
        
        // Configure element from properties
        final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
        elementConfigurer.configure(dataOutput, getContext().getProperties());
        
        // Set default name
        IElementNamer elementNamer = modelManager.getModelServices().getElementNamer();
        dataOutput.setName(elementNamer.getUniqueName(dataOutput));
        return dataOutput;
    }

}
