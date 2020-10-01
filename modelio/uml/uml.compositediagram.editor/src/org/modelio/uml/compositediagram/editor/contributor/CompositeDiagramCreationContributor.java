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

package org.modelio.uml.compositediagram.editor.contributor;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.CompositeStructureDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.model.view.template.service.ModelViewTemplateManager;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Creation contributor for "Composite structure" diagrams.
 */
@objid ("dc5fa1d8-859c-4b4a-80c2-a77e4f11cd67")
public class CompositeDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("0bd45e0e-02e8-4d9c-b33c-6d3f62f99beb")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("950475cf-5c9b-4b00-b736-10e116709ade")
    @Inject
    @Optional
    private ModelViewTemplateManager diagramCreationService;

    @objid ("31948b2e-3006-4eca-9368-cefbf2b60028")
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

    @objid ("d6925b92-d5c9-41c0-949f-0cc59bdc1f37")
    @Override
    public Image getIconImage() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(CompositeStructureDiagram.class));
        } else {
            return null;
        }
    }

    @objid ("fde1bd1b-2a0d-4383-b9f7-cb1eb6224794")
    private StaticDiagram createCompositeDiagram(final IStandardModelFactory modelFactory, final String diagramName, final ModelElement diagramContext) {
        // Create the Composite diagram
        StaticDiagram diagram = modelFactory.createCompositeStructureDiagram();
        diagram.setName(diagramName);
        diagram.setOrigin(diagramContext);
        return diagram;
    }

    @objid ("0e1e20b6-f406-4a3a-b310-7ecd60e52f37")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            allowedScopes.add(new ElementScope(metamodel.getMClass(Collaboration.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Component.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Class.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Node.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("ae195afd-1f55-4376-a4dd-77181646de2a")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("3f6dc677-f082-4489-bec7-ded1e0e8cc3b")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
    }

    @objid ("24366dc5-6000-4b41-adfd-e193c590881f")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, CompositeStructureDiagram.MQNAME);
    }

    @objid ("9872dc2a-8ba9-4fcf-8244-c8b2e37630b2")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(CompositeStructureDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

    @objid ("8ce2177d-c728-4296-85a1-37e1b8b091a5")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}
