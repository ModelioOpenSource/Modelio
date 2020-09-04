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

package org.modelio.editors.richnote.gui.creation.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.modelio.editors.richnote.gui.creation.RichNoteDescriptor;
import org.modelio.editors.richnote.gui.creation.doctype.DocTypeChooserDriver;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.metamodel.uml.infrastructure.ResourceType;

@objid ("e303291d-46b4-4423-a2b6-37292ef4ddf5")
class ModelPanelUI {
    @objid ("d38ad7d3-b8ee-4b23-857c-f57babc187d6")
    private Text abstractText;

    @objid ("dd3cf8b5-1b24-4e4a-8e1c-f6ccf8f37dc8")
    private ModelPanelController controller;

    @objid ("dafc8f8f-570e-40e5-8052-57caf4d39ec3")
    private DocTypeChooserDriver docTypeDriver;

    @objid ("bd4bdc53-5ce0-46ff-bdff-5e201b323d52")
    private StructuredViewer docTypeViewer;

    @objid ("d61e4c1e-7066-4b88-ac87-aef5eec7c477")
    private Text nameText;

    @objid ("99d7dc84-dad6-4584-a911-d3648f5fe982")
     Composite top = null;

    @objid ("c2253e98-3cd1-4e5f-94b6-f6350861ea70")
    public ModelPanelUI(ModelPanelController controller) {
        this.controller = controller;
    }

    @objid ("84ce8b31-79ea-402e-9c3a-5f69dd0984f8")
    public Control createUI(Composite parent) {
        this.top = new Composite(parent, SWT.NONE);
        this.top.setLayout(new GridLayout(2, false));
        
        // Name field
        Label nameLabel = new Label(this.top, SWT.NONE);
        nameLabel.setText(EditorsRichNote.I18N.getString("ModelWizardPage.Name.label"));
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).align(SWT.LEFT, SWT.CENTER).create());
        
        this.nameText = new Text(this.top, SWT.SINGLE | SWT.BORDER);
        this.nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        this.nameText.setToolTipText(EditorsRichNote.I18N.getString("ModelWizardPage.Name.tooltip"));
        this.nameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ModelPanelUI.this.controller.onNameChanged(ModelPanelUI.this.nameText.getText(), true);
            }
        });
        
        this.nameText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ModelPanelUI.this.controller.onNameChanged(ModelPanelUI.this.nameText.getText(), false);
            }
        });
        
        // Document type
        Label docTypeLabel = new Label(this.top, SWT.NONE);
        docTypeLabel.setText(EditorsRichNote.I18N.getString("ModelWizardPage.DocType.label"));
        docTypeLabel.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
        
        this.docTypeDriver = new DocTypeChooserDriver();
        this.docTypeViewer = this.docTypeDriver.createViewer(this.top);
        this.docTypeViewer.getControl().setToolTipText(EditorsRichNote.I18N.getString("ModelWizardPage.DocType.tooltip"));
        this.docTypeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        this.docTypeViewer.addSelectionChangedListener(e -> {
            ModelPanelUI.this.controller.onDocTypeChanged(ModelPanelUI.this.docTypeDriver.getSelection());
        });
        
        // Abstract field
        Label abstractLabel = new Label(this.top, 0);
        abstractLabel.setText(EditorsRichNote.I18N.getString("ModelWizardPage.Abstract.label"));
        abstractLabel.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());
        
        this.abstractText = new Text(this.top, SWT.MULTI | SWT.BORDER);
        this.abstractText.setToolTipText(EditorsRichNote.I18N.getString("ModelWizardPage.Abstract.tooltip"));
        this.abstractText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        this.abstractText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ModelPanelUI.this.controller.onAbstractChanged(ModelPanelUI.this.abstractText.getText(), true);
            }
        });
        
        this.abstractText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ModelPanelUI.this.controller.onAbstractChanged(ModelPanelUI.this.abstractText.getText(), false);
            }
        });
        return this.top;
    }

    @objid ("d2068e8c-c6f3-4073-8103-fb85cc654589")
    public void dispose() {
        this.top.dispose();
    }

    @objid ("f226047f-e057-4569-bb05-3d92de5bc8b4")
    public void update(RichNoteDescriptor data) {
        if (data != null) {
            this.nameText.setText(data.getName());
            this.docTypeDriver.init(data.getTargetElement());
        
            // Really ugly hack to ensure a default type
            ResourceType defaultType = this.docTypeDriver.getDefaultDocumentType();
            if (data.getDocumentType() == null)
                controller.onDocTypeChanged(defaultType);
        
            this.docTypeDriver.selectInViewer(data.getDocumentType());
            this.abstractText.setText(data.getAbstract());
        }
    }

}
