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
import org.modelio.diagram.editor.bpmn.elements.bpmndataobject.datainput.GmBpmnDataInput;
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
import org.modelio.metamodel.bpmn.objects.BpmnDataInput;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Smart Command dedicated to the creation of data input for an activity.
 * <ul>
 * <li>create the data input</li>
 * <li>layout the data input at its conventional place (bottom left of target activity)</li>
 * <li>create a data association from data input to the targeted activity</li>
 * </ul>
 */
@objid ("91e483ff-8a1c-44d3-b0f1-0e6c6bcd3b70")
public class BpmnActivityCreateDataInputCommand extends DefaultCreateElementCommand {
    @objid ("e6c69266-ec85-467c-a967-2b1f8d72e9a9")
    public BpmnActivityCreateDataInputCommand(MObject parentElement, GmCompositeNode parentNode, ModelioCreationContext context, Object constraint) {
        super(parentElement, parentNode, context, constraint);
    }

    @objid ("86d341fa-cc98-4b8c-987c-1d0027f512f8")
    @Override
    public void execute() {
        final IGmDiagram diagram = this.getParentNode().getDiagram();
        
        // Otherwise this command should not be used
        assert (getParentElement() instanceof BpmnActivity);
        assert (this.getContext().getElementToUnmask() == null);
        
        // Get the activity
        BpmnActivity activity = (BpmnActivity) getParentElement();
        GmNodeModel gmActivity = getParentNode();
        Rectangle gmActivityBounds = (Rectangle)gmActivity.getLayoutData();
        
        // Create the data input
        BpmnDataInput dataInput = createDataInputElement(diagram.getModelManager());
        
        // Create the data assoc
        BpmnDataAssociation dataAssoc = createDataAssociation(diagram.getModelManager(), activity, dataInput);
        
        // Unmask the data input
        Rectangle dataInputConstraint = new Rectangle(gmActivityBounds.x - 40, gmActivityBounds.y + gmActivityBounds.height + 10, -1, -1);
        GmBpmnDataInput gmDataInput = (GmBpmnDataInput) diagram.unmask(this.getParentNode().getParentNode(), dataInput, dataInputConstraint);
        
        // Umask the data assoc
        IGmPath gmpath = new GmPath();
        gmpath.setSourceAnchor(new GmLinkAnchor(new Dimension(dataInputConstraint.width / 2, dataInputConstraint.height / 2)));
        gmpath.setTargetAnchor(new GmLinkAnchor(new Dimension(gmActivityBounds.width / 2, gmActivityBounds.height / 2)));
        gmpath.setPathData(Collections.EMPTY_LIST);
        gmpath.setRouterKind(ConnectionRouterId.DIRECT);
        
        diagram.unmaskLink(dataAssoc, gmDataInput, getParentNode(), gmpath);
    }

    @objid ("57656d8c-2759-4183-aa7f-b46b6d0a24f2")
    private BpmnDataAssociation createDataAssociation(IModelManager modelManager, BpmnActivity activity, BpmnDataInput dataInput) {
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnDataAssociation dataAssoc = modelFactory.createElement(BpmnDataAssociation.class);
        dataAssoc.setTargetRef(dataInput);
        dataAssoc.setEndingActivity(activity);
        return dataAssoc;
    }

    @objid ("314d3491-72f2-4517-adbb-640e6ae70780")
    private BpmnDataInput createDataInputElement(IModelManager modelManager) {
        MObject newElement;
        
        // Create the model element...
        final IStandardModelFactory modelFactory = modelManager.getModelFactory().getFactory(IStandardModelFactory.class);
        newElement = modelFactory.createElement(this.getContext().getMetaclass());
        
        assert (newElement instanceof BpmnDataInput);
        
        BpmnDataInput dataInput = (BpmnDataInput) newElement;
        BpmnActivity activity = (BpmnActivity) getParentElement();
        
        dataInput.setContainer(activity.getContainer());
        
        // Attach the stereotype if needed.
        if (this.getContext().getStereotype() != null) {
            dataInput.getExtension().add(this.getContext().getStereotype());
        }
        
        // Configure element from properties
        final IElementConfigurator elementConfigurer = modelManager.getModelServices().getElementConfigurer();
        elementConfigurer.configure(dataInput, getContext().getProperties());
        
        // Set default name
        IElementNamer elementNamer = modelManager.getModelServices().getElementNamer();
        dataInput.setName(elementNamer.getUniqueName(dataInput));
        return dataInput;
    }

    @objid ("36d037b3-f67d-4a41-bcac-f0c4527d4f2d")
    @Override
    public boolean canExecute() {
        final IGmDiagram gmDiagram = getParentNode().getDiagram();
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        return true;
    }

}
