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

package org.modelio.diagram.editor.usecase.contributor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.api.ui.contributor.DefaultWizardPreviewPanel;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.diagram.editor.usecase.plugin.DiagramEditorUseCase;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.osgi.framework.Bundle;

/**
 * Creation contributor for UseCase diagrams.
 */
@objid ("7030acaa-10b7-499b-8763-c6b11bfdbaaf")
public class UseCaseDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("f235a423-55a1-4bde-8940-92b836c8073d")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("8951769b-e100-41a0-86ab-dd9ceb546774")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        AbstractDiagram diagram = createUseCaseDiagram(modelFactory, diagramName, diagramContext);
        if (diagram != null) {
            if (diagramName.equals(getLabel())) {
                setElementDefaultName(diagram);
            } else {
                diagram.setName(diagramName);
            }
            putNoteContent(diagram, "description", diagramDescription);
        }
        return diagram;
    }

    @objid ("8adeba22-d6bd-42d4-9ba6-54abdba961d0")
    @Override
    public String getDetails() {
        return DiagramEditorUseCase.I18N.getString("CreationWizard.UseCase.Details");
    }

    @objid ("f9f8f011-f2e9-4bd2-af1d-e506cb736b3b")
    @Override
    public Image getIcon() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(UseCaseDiagram.class));
        } else {
            return null;
        }
    }

    @objid ("f2696a8e-2759-4401-a96a-efa10931542b")
    @Override
    public String getInformation() {
        return DiagramEditorUseCase.I18N.getString("CreationWizard.UseCase.Information");
    }

    @objid ("f8f298c5-f23e-438e-8a69-616a3f5dcb5d")
    @Override
    public String getLabel() {
        return DiagramEditorUseCase.I18N.getString("CreationWizard.UseCase.Name");
    }

    @objid ("67a84b86-0256-46fb-9723-818b9a707a6a")
    private StaticDiagram createUseCaseDiagram(final IStandardModelFactory modelFactory, final String diagramName, final ModelElement diagramContext) {
        // Create the UseCase diagram
        StaticDiagram diagram;
        diagram = modelFactory.createUseCaseDiagram(diagramName, diagramContext, null);
        return diagram;
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

    @objid ("b6abcf4c-cc74-4d0e-82fb-e55eb3342288")
    @Override
    public ImageDescriptor getPreviewImage() {
        Bundle bundle = DiagramEditorUseCase.getContext().getBundle();
        URL imageUrl = FileLocator.find(bundle, new Path("images/usecasediagrampreview400x300.png"), null);
        return ImageDescriptor.createFromURL(imageUrl);
    }

    @objid ("070565a7-1393-4c52-a9f0-0d1a64745640")
    protected final void putNoteContent(ModelElement element, String type, String content) {
        try {
            element.putNoteContent("ModelerModule", type, content);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
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

    @objid ("9a10a724-6408-4cd0-a041-5cd39c548485")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
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

}
