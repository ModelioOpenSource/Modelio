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

package org.modelio.diagram.editor.deployment.contributor;

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
import org.modelio.diagram.editor.deployment.plugin.DiagramEditorDeployment;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DeploymentDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.osgi.framework.Bundle;

/**
 * Creation contributor for Deployment diagrams.
 */
@objid ("048c9dd2-ea05-40e0-b722-9f60b980c0b8")
public class DeploymentDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("ec2d6353-978d-47ce-a5c3-f6ee9ca3e519")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("5b0f4d77-2d57-403a-a45f-27cd88d73ca9")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        StaticDiagram diagram = createDeploymentDiagram(modelFactory, diagramName, diagramContext);
        
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

    @objid ("e1938cb8-bbce-473b-b147-74dc2575dd17")
    @Override
    public String getDetails() {
        return DiagramEditorDeployment.I18N.getString("CreationWizard.Deployment.Details");
    }

    @objid ("6e8baf47-80da-4d48-9cef-ad0ea9b49185")
    @Override
    public Image getIcon() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(DeploymentDiagram.class));
        } else {
            return null;
        }
    }

    @objid ("fd0d55cf-dce9-4371-a054-d7989a19b1b9")
    @Override
    public String getInformation() {
        return DiagramEditorDeployment.I18N.getString("CreationWizard.Deployment.Information");
    }

    @objid ("ab9d8fa6-4068-4a56-b197-2738eb99fa62")
    @Override
    public String getLabel() {
        return DiagramEditorDeployment.I18N.getString("CreationWizard.Deployment.Name");
    }

    @objid ("db48aada-78b8-4eec-81d7-64f9b3fac2b3")
    private StaticDiagram createDeploymentDiagram(final IStandardModelFactory modelFactory, final String diagramName, final ModelElement diagramContext) {
        // Create the Deployment diagram
        StaticDiagram diagram;
        diagram = modelFactory.createDeploymentDiagram(diagramName, diagramContext, null);
        return diagram;
    }

    @objid ("9e79267d-83c0-4085-83ae-7dc2ac92a9c1")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            allowedScopes.add(new ElementScope(metamodel.getMClass(Artifact.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Package.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Class.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Component.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Node.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("ab986c1a-7a7a-4f07-ac89-8416c8c544b4")
    @Override
    public ImageDescriptor getPreviewImage() {
        Bundle bundle = DiagramEditorDeployment.getContext().getBundle();
        URL imageUrl = FileLocator.find(bundle, new Path("images/deploimentdiagrampreview400x300.png"), null);
        return ImageDescriptor.createFromURL(imageUrl);
    }

    @objid ("18736cc4-e611-4432-aa0d-96af548952aa")
    protected final void putNoteContent(ModelElement element, String type, String content) {
        try {
            element.putNoteContent("ModelerModule", type, content);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @objid ("41f97c79-1c38-4305-9de2-d6803613a290")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("98bb4e1c-e0ec-4b25-9503-b9ddfd442af2")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
    }

    @objid ("192952a4-4cf5-4734-a6cf-3fa3c8423af3")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("e450205b-0e9d-4a9e-830d-4ca550598e5c")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, DeploymentDiagram.MQNAME);
    }

    @objid ("3fb76640-bf22-480a-8d0e-b97f9d622460")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(DeploymentDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

}
