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

package org.modelio.edition.dialogs.dialog.panels.operation;

import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.edition.dialogs.dialog.panels.element.ElementDescriptionPanel;
import org.modelio.edition.dialogs.dialog.panels.operation.params.OperationParametersPanel;
import org.modelio.edition.dialogs.dialog.panels.operation.properties.OperationPropertiesPanel;
import org.modelio.edition.dialogs.dialog.panels.operation.signature.OperationSignaturePanel;
import org.modelio.edition.dialogs.plugin.EditionDialogs;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.ui.panel.IPanelProvider;

/**
 * Edition panel for 'Operation' objects
 */
@objid ("4168f6a3-002b-449a-8682-daa1c05eb5fc")
@Creatable
public class OperationEditPanel implements IPanelProvider {
    @objid ("4ddaa799-6bf1-4d7d-9650-8b2607adc697")
    private Composite area;

    @objid ("520a2552-0339-46ea-b39c-b728f7809d09")
    private ElementDescriptionPanel descriptionPanel;

    @objid ("f3d009e3-c56a-4f57-8600-0eca228013f3")
    private IOperationPropertyModel model = null;

    @objid ("11b19b3a-4a9d-4e18-ab22-d232ea749425")
    private Operation op;

    @objid ("8c4e6166-ffce-4fcf-95b8-5179430ac4ce")
    private OperationParametersPanel parametersPanel;

    @objid ("c89aa290-2402-4d8a-8b73-ee06caaac6f5")
    @Inject
     IProjectService projectService;

    @objid ("c6d634d1-2f62-415b-a129-f6ecc277a065")
    private OperationPropertiesPanel propertiesPanel;

    @objid ("9246f8ef-c269-47df-a607-08fccb4546b7")
    private OperationSignaturePanel signaturePanel;

    @objid ("a63fc1ae-3856-4f96-b09c-7953437b6f89")
    @Override
    public Control createPanel(Composite parent) {
        this.area = new Composite(parent, SWT.NO_REDRAW_RESIZE);
        
        final GridLayout layout = new GridLayout(1, true);
        layout.horizontalSpacing = 0;
        layout.verticalSpacing = 2;
        layout.marginLeft = 2;
        layout.marginRight = 2;
        layout.marginHeight = 2;
        layout.marginBottom = 2;
        this.area.setLayout(layout);
        
        this.propertiesPanel = new OperationPropertiesPanel();
        final Composite propertyArea = (Composite) this.propertiesPanel.createPanel(this.area);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, false);
        propertyArea.setLayoutData(gd);
        
        this.parametersPanel = new OperationParametersPanel();
        final Composite parametersArea = (Composite) this.parametersPanel.createPanel(this.area);
        gd = new GridData(SWT.FILL, SWT.FILL, true, false);
        gd.heightHint = 240;
        parametersArea.setLayoutData(gd);
        
        this.signaturePanel = new OperationSignaturePanel();
        final Composite previewArea = (Composite) this.signaturePanel.createPanel(this.area);
        gd = new GridData(SWT.FILL, SWT.FILL, true, false);
        previewArea.setLayoutData(gd);
        
        this.descriptionPanel = new ElementDescriptionPanel();
        final Composite descriptionArea = (Composite) this.descriptionPanel.createPanel(this.area);
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        descriptionArea.setLayoutData(gd);
        return this.area;
    }

    @objid ("fefe7ee3-a749-4efd-8ae7-7f8bd95ae61a")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("aeb78ed8-7f99-4d83-80db-68ff0e56419c")
    @Override
    public String getHelpTopic() {
        return EditionDialogs.I18N.getString("EditElementDialog.Operation.HELP_TOPIC");
    }

    @objid ("3566cb66-8824-4190-a6c7-4c1f9d6e20fc")
    @Override
    public Object getInput() {
        return this.op;
    }

    @objid ("e573260c-fd98-4b72-b1f5-895754664cf0")
    @Override
    public Object getPanel() {
        return this.area;
    }

    @objid ("a35a9514-c03e-4911-a1d1-bb91dd9719ba")
    @Override
    public boolean isRelevantFor(Object obj) {
        if (obj instanceof ISelection) {
            return SelectionHelper.getFirst((ISelection) obj, Operation.class) != null;
        } else {
            return obj instanceof Operation;
        }
    }

// private ElementDescriptionPanel descriptionPanel;
    @objid ("b9dc33bf-7b61-41dc-9c7a-344bb08b8055")
    @Override
    public void setInput(Object input) {
        Operation newInput = input instanceof ModelElement ? (Operation) input : SelectionHelper.getFirst((ISelection) input, Operation.class);
        
        // Bad input, clear all
        if (newInput == null) {
            this.op = null;
            return;
        }
        
        if (!newInput.isValid()) {
            this.op = null;
            return;
        }
        
        // Input is a valid Operation
        this.op = newInput;
        
        this.model = new OperationModel(this.op);
        
        this.signaturePanel.setInput(this.model);
        this.propertiesPanel.setInput(this.model);
        this.parametersPanel.setInput(this.model);
        this.descriptionPanel.setInput(this.model.getOperation());
    }

    @objid ("99a9d0c7-f615-4102-965c-b8fbd6183468")
    IOperationPropertyModel getModel() {
        return this.model;
    }

}
