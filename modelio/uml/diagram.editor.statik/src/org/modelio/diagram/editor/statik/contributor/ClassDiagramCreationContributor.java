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

package org.modelio.diagram.editor.statik.contributor;

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
import org.modelio.diagram.editor.statik.plugin.DiagramEditorStatik;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.ClassDiagram;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
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
 * Creation contributor for Class diagrams.
 */
@objid ("0cb7cc65-f02f-45d0-8884-201abcebcb31")
public class ClassDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("e1735075-d8df-4f01-aa3a-506793884ba5")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("3fc496f8-a8df-4cab-8eb3-e7d4cff6f480")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        ClassDiagram diagram = modelFactory.createClassDiagram(diagramName, diagramContext, null);
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

    @objid ("78779841-c0b6-42fe-98a8-eab48cee8c06")
    @Override
    public String getDetails() {
        return DiagramEditorStatik.I18N.getString("CreationWizard.Class.Details");
    }

    @objid ("31ba5828-0b49-4893-875e-6c4abe46a22c")
    @Override
    public Image getIcon() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(ClassDiagram.class));
        } else {
            return null;
        }
    }

    @objid ("ed34f3a6-c016-4109-833f-30e00bce96df")
    @Override
    public String getInformation() {
        return DiagramEditorStatik.I18N.getString("CreationWizard.Class.Information");
    }

    @objid ("3cf70c04-d79f-4247-8e4a-adf8077b1a9e")
    @Override
    public String getLabel() {
        return DiagramEditorStatik.I18N.getString("CreationWizard.Class.Name");
    }

    @objid ("141a33a0-1095-4c91-93c9-ffa72a6379e2")
    @Override
    public ImageDescriptor getPreviewImage() {
        Bundle bundle = DiagramEditorStatik.getContext().getBundle();
        URL imageUrl = FileLocator.find(bundle, new Path("images/classdiagrampreview400x300.png"), null);
        return ImageDescriptor.createFromURL(imageUrl);
    }

    @objid ("6c417de3-8a3a-4df2-bfff-f68afedc86d3")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            allowedScopes.add(new ElementScope(metamodel.getMClass(Class.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Package.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Component.class), true, null, true));
            allowedScopes.add(new ElementScope(metamodel.getMClass(Interface.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("435122a2-bdbd-4ef7-9048-9aa85bffe25e")
    protected final void putNoteContent(ModelElement element, String type, String content) {
        try {
            element.putNoteContent("ModelerModule", type, content);
        } catch (ExtensionNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @objid ("9ac5839b-2276-4441-ac08-2f152151bcb7")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("12c2f017-5f7a-4268-9890-797da13bf21d")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
    }

    @objid ("1db20c1e-e4df-4f1f-9dc1-da9f8a4368de")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("3401acc8-d0b1-4e4d-9fe1-d01950592ab1")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, ClassDiagram.MQNAME);
    }

    @objid ("72714adb-266b-43e1-9f12-827160f39c97")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(ClassDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

}
