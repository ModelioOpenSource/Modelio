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

package org.modelio.diagram.editor.object.contributor;

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
import org.modelio.diagram.editor.object.plugin.DiagramEditorObject;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ObjectDiagram;
import org.modelio.metamodel.diagrams.StaticDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.osgi.framework.Bundle;

/**
 * Creation contributor for Object diagrams.
 */
@objid ("842d3fb3-1bb3-4b41-bb1e-e2dea98586b4")
public class ObjectDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("a67c24d0-af20-4f0e-851a-2fb486070fe1")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("72d2a8c5-1cf9-4e73-8b69-d7416699d26f")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        StaticDiagram diagram = createObjectDiagram(modelFactory, diagramName, diagramContext);
        
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

    @objid ("2b6d6bcd-a5aa-4531-8732-1c540c86b699")
    @Override
    public String getDetails() {
        return DiagramEditorObject.I18N.getString("CreationWizard.Object.Details");
    }

    @objid ("c66ea11a-df11-41f9-93e7-4ddef0cf5b45")
    @Override
    public Image getIcon() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(ObjectDiagram.class));
        } else {
            return null;
        }
    }

    @objid ("47ef6f72-b57c-4971-b58b-2fdae6f87c17")
    @Override
    public String getInformation() {
        return DiagramEditorObject.I18N.getString("CreationWizard.Object.Information");
    }

    @objid ("e18147ba-1726-46a3-ba9f-df112f1f0c20")
    @Override
    public String getLabel() {
        return DiagramEditorObject.I18N.getString("CreationWizard.Object.Name");
    }

    @objid ("aae84d9d-955d-431a-83a9-73cf5ee9a549")
    private StaticDiagram createObjectDiagram(final IStandardModelFactory modelFactory, final String diagramName, final ModelElement diagramContext) {
        // Create the Object diagram
        return modelFactory.createObjectDiagram(diagramName, diagramContext, null);
    }

    @objid ("80e19b0d-f304-4835-bc7d-17fbd3bda55e")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            allowedScopes.add(new ElementScope(metamodel.getMClass(Artifact.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(BindableInstance.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Class.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Collaboration.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Component.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Instance.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Node.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Package.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("f7f364be-17a0-4e27-aa49-6defd7eedf50")
    @Override
    public ImageDescriptor getPreviewImage() {
        Bundle bundle = DiagramEditorObject.getContext().getBundle();
        URL imageUrl = FileLocator.find(bundle, new Path("images/objectdiagrampreview400x300.png"), null);
        return ImageDescriptor.createFromURL(imageUrl);
    }

    @objid ("021e3746-2ff2-425c-a115-0cdde1ad9b5d")
    protected final void putNoteContent(ModelElement element, String type, String content) {
        try {
            element.putNoteContent("ModelerModule", type, content);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @objid ("7805da7f-c6e0-488d-a5d6-fa8879cb9e60")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("a5fb8500-5fa1-4859-8293-6dbaa6a9ddf4")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
    }

    @objid ("10ab7158-a005-4b58-bf37-8f71d2af985b")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("1133c846-b8c6-47a3-adf9-1b5a2e95f5df")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, ObjectDiagram.MQNAME);
    }

    @objid ("eca69763-ac22-4ff7-9a65-9ffabf7da43b")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(ObjectDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

}
