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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.ElementDescriptor;
import org.modelio.api.module.contributor.diagramcreation.AbstractDiagramWizardContributor;
import org.modelio.api.ui.contributor.DefaultWizardPreviewPanel;
import org.modelio.bpmn.diagram.editor.layout.BpmnLayouter;
import org.modelio.metamodel.bpmn.activities.BpmnSubProcess;
import org.modelio.metamodel.bpmn.bpmnDiagrams.BpmnSubProcessDiagram;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

/**
 * Creation contributor for Bpmn Sub Process diagrams.
 */
@objid ("a28c97ed-47f2-4c89-bc4a-567ec55d1c27")
public class BpmnSubProcessDiagramCreationContributor extends AbstractDiagramWizardContributor {
    @objid ("f14f4998-b1f2-4867-876f-5c3028325fbf")
    @Inject
    @Optional
    protected IMModelServices mmServices;

    @objid ("44349271-c327-4a0f-ad74-754939b75290")
    @Override
    public AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription) {
        final IStandardModelFactory modelFactory = this.mmServices.getModelFactory().getFactory(IStandardModelFactory.class);
        BpmnSubProcessDiagram diagram = createBpmnSubProcessDiagram(modelFactory, diagramContext, diagramName);
        if (diagram != null) {
            diagram.putNoteContent("ModelerModule", ModelElement.MQNAME, "description", diagramDescription);
            // Layout diagram
            new BpmnLayouter(diagram).run();
        }
        return diagram;
    }

    @objid ("98b6f42f-69d4-47e9-8ce4-8c1dce0ee1c6")
    @Override
    public List<ElementScope> getScopes() {
        List<ElementScope> allowedScopes = new ArrayList<>();
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            allowedScopes.add(new ElementScope(metamodel.getMClass(BpmnSubProcess.class), true, null, true));
        }
        return allowedScopes;
    }

    @objid ("cc7fed19-e2d9-4c7e-9372-e36614cc4676")
    @Override
    public Image getIconImage() {
        MMetamodel metamodel = getMetamodel();
        if (metamodel != null) {
            return MetamodelImageService.getIcon(metamodel.getMClass(BpmnSubProcessDiagram.class));
        } else {
            return null;
        }
        
    }

    @objid ("9772d2e9-81f1-454d-9ad5-760e29078870")
    private BpmnSubProcessDiagram createBpmnSubProcessDiagram(final IStandardModelFactory modelFactory, final ModelElement diagramContext, final String diagramName) {
        BpmnSubProcessDiagram diagram = modelFactory.createBpmnSubProcessDiagram();
        diagram.setOrigin(diagramContext);
        if (diagramName.equals(getLabel())) {
            setElementDefaultName(diagram);
        } else {
            diagram.setName(diagramName);
        }
        return diagram;
    }

    @objid ("a03ef6f5-5d6d-4051-a2a6-8543f13399f2")
    protected final void setElementDefaultName(ModelElement element) {
        element.setName(this.mmServices.getElementNamer().getUniqueName(element));
    }

    @objid ("d471350c-95a4-4e36-817e-b0af62ea1f50")
    protected final MMetamodel getMetamodel() {
        if (this.mmServices == null) {
            return null;
        } else {
            return this.mmServices.getMetamodel();
        }
        
    }

    @objid ("647adde4-6182-4029-a2f9-a36c06f0977e")
    @Override
    public final IPanelProvider getWizardPanel() {
        return new DefaultWizardPreviewPanel();
    }

    @objid ("4ff86175-5baf-4e8f-892f-d24bfe99ded2")
    @Override
    protected boolean checkCanCreateIn(ModelElement owner) {
        return MTools.getAuthTool().canAdd(owner, BpmnSubProcessDiagram.MQNAME);
    }

    @objid ("9e538d15-090b-4d7d-b872-ad0e3760e80b")
    @Override
    public ElementDescriptor getCreatedElementType() {
        MClass mClass = this.mmServices.getMetamodel().getMClass(BpmnSubProcessDiagram.class);
        return new ElementDescriptor(mClass, null);
    }

    @objid ("1f777c8a-cbd6-47d0-b6a0-30337760b842")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

}
