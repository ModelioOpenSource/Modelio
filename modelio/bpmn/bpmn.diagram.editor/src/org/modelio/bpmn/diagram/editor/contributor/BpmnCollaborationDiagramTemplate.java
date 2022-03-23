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
package org.modelio.bpmn.diagram.editor.contributor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.bpmn.diagram.editor.editor.BpmnGmDiagramCreator;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.ModelManager;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnProcess;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;

@objid ("41b3047b-f341-4871-b949-1305f90efc8f")
public class BpmnCollaborationDiagramTemplate implements IModelViewTemplate<AbstractDiagram> {
    @objid ("cf39a4cf-8154-444a-bc48-2d31b2acaba8")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("185ef8a6-7ae6-44ed-ac7b-6b472cff40c2")
    @Inject
    private IEclipseContext eclipseContext;

    @objid ("3d867d59-84ad-4d41-a170-9579f028051b")
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

    @objid ("51bc5b1e-f3d1-44e9-b0a0-f48355585728")
    @Override
    public AbstractDiagram createView(ModelElement base) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        
        final BpmnCollaboration collaboration = (base instanceof BpmnCollaboration) ? (BpmnCollaboration) base : createCollaboration(modelFactory, base);
        
        AbstractDiagram diagram = modelFactory.createBpmnCollaborationDiagram();
        diagram.setOrigin(collaboration);
        diagram.setName(this.mmServices.getElementNamer().getUniqueName(diagram));
        
        if (base instanceof BpmnProcess) {
            BpmnProcess process = (BpmnProcess) base;
            // Create a participant
            BpmnParticipant participant = modelFactory.createBpmnParticipant();
            participant.setName(process.getName());
            participant.setProcess(process);
            participant.setContainer(collaboration);
            // Unmask it
            IGmDiagram input = new BpmnGmDiagramCreator().createDiagram(new ModelManager(this.eclipseContext), diagram);
            if (input != null) {
                // Make the diagram visible at GM level.
                input.setVisible(true);
                // Load from the persistence.
                input.load();
                input.unmaskAsChild(participant, null);
                input.save(true);
                input.dispose();
            }
        }
        return diagram;
    }

    @objid ("08bd9a8e-8f60-420f-9ade-fb4d0b0b7e52")
    private BpmnCollaboration createCollaboration(IStandardModelFactory modelFactory, ModelElement base) {
        BpmnCollaboration collaboration = modelFactory.createBpmnCollaboration();
        if (base instanceof NameSpace) {
            collaboration.setOwner((NameSpace) base);
        } else if (base instanceof Operation) {
            collaboration.setOwnerOperation((Operation) base);
        } else if (base instanceof BpmnProcess) {
            collaboration.setDefinedProcess((BpmnProcess) base);
        }
        collaboration.setName(this.mmServices.getElementNamer().getUniqueName(collaboration));
        return null;
    }

    @objid ("abec45db-dc1e-4d1c-bada-90612b5333f3")
    @Override
    public AbstractDiagram getExistingView(ModelElement base) {
        // Not supported concept
        return null;
    }

    @objid ("fd73c75b-767c-4dca-b643-7399ff229763")
    @Override
    public void updateView(AbstractDiagram existingView) {
        // Not supported concept
    }

    @objid ("80766ded-5daa-4905-b497-3f7933c4971d")
    @Override
    public ModelElement resolveOrigin(ModelElement base) {
        return base;
    }

    @objid ("97318d21-1efb-44af-919a-22a5bc859bb7")
    @Override
    public ModelElement getMainElement(AbstractDiagram view) {
        return view.getOrigin();
    }

    /**
     * Mandatory default c'tor needed by eclipse when loading the extension point.
     */
    @objid ("6f9bf823-905e-4583-b6ef-83893ad42e17")
    public  BpmnCollaborationDiagramTemplate() {
        super();
    }

}
