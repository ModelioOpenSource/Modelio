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
package org.modelio.uml.sequencediagram.editor.contributor;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.SequenceDiagram;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.model.view.template.service.ModelViewTemplateManager;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Creation contributor for Sequence diagrams.
 */
@objid ("e5024b84-27e2-4e4a-9bac-c1e6d91383b2")
public class SequenceDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("1e8c3c35-b1a4-4c7c-a9c4-bb82c13816a0")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("12986a23-d497-48c6-a664-5a65006373b0")
    @Inject
    @Optional
    private ModelViewTemplateManager diagramCreationService;

    @objid ("7d6d59b5-03bf-4790-b567-38a4e85f1a05")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        IModelViewTemplate<AbstractDiagram> creator = this.diagramCreationService.get(getModelViewTemplateId());
        AbstractDiagram diagram = creator.createView(diagramContext);
        if (!diagramName.equals(getLabel())) {
            diagram.setName(diagramName);
        }
        diagram.putNoteContent("ModelerModule", ModelElement.MQNAME, "description", diagramDescription);
        return diagram;
    }

    @objid ("50f86233-4b73-4334-8bf3-33c5d6764f64")
    @Override
    public Image getIconImage() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(SequenceDiagram.class));
        } else {
            return null;
        }
        
    }

    @objid ("77ff93e3-6ecc-47cd-804a-d640b982c7fc")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            allowedScopes.add(new ElementScope(metamodel.getMClass(Package.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Class.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Interface.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Signal.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Actor.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Component.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Node.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(UseCase.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Collaboration.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Operation.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Interaction.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("0d758f29-8747-498c-a335-dd033f7d825b")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("682b34f1-935b-464e-b845-622394dad0c3")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
        
    }

    @objid ("5406bf1d-0df5-479d-ab4a-23f9b850ddea")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, SequenceDiagram.MQNAME);
    }

    @objid ("a9d03917-024c-4a5a-9e37-ff0b64986772")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(SequenceDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

    @objid ("70a0e92e-5144-49e0-9a42-bd1b4766bced")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}
