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
package org.modelio.uml.usecasediagram.editor.contributor;

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
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.model.view.template.service.ModelViewTemplateManager;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Creation contributor for UseCase diagrams.
 */
@objid ("7030acaa-10b7-499b-8763-c6b11bfdbaaf")
public class UseCaseDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("f235a423-55a1-4bde-8940-92b836c8073d")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("7e902793-7793-4980-befc-b8068f85f331")
    @Inject
    @Optional
    private ModelViewTemplateManager diagramCreationService;

    @objid ("8951769b-e100-41a0-86ab-dd9ceb546774")
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

    @objid ("f9f8f011-f2e9-4bd2-af1d-e506cb736b3b")
    @Override
    public Image getIconImage() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(UseCaseDiagram.class));
        } else {
            return null;
        }
        
    }

    @objid ("4f43f0be-8ff1-45b9-bd04-ff3d05c1811e")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            allowedScopes.add(new ElementScope(metamodel.getMClass(Package.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Class.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Interface.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Component.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(UseCase.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("063f3f43-1032-42eb-9d78-1cf609e6d0ed")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("8aa08310-6909-452e-8707-572251976525")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
        
    }

    @objid ("40fdf349-3651-4c40-8c4b-c0a84a97e373")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, UseCaseDiagram.MQNAME);
    }

    @objid ("fb642498-b06d-452c-a5b6-231c7ad76edb")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(UseCaseDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

    @objid ("ed99b51c-3ca0-4195-9c54-c1de57156037")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}
