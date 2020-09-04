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

package org.modelio.edition.dialogs.dialog.panels.operation.properties;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.modelio.core.ui.MetamodelLabels;
import org.modelio.core.ui.swt.textelement.TextElement;
import org.modelio.edition.dialogs.dialog.panels.operation.IOperationPropertyModel;
import org.modelio.edition.dialogs.plugin.EditionDialogs;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.ui.panel.IPanelProvider;

/**
 * Reusable panel to edit an operation.
 * <p>
 * Needs an {@link IOperationPropertyModel} as input.
 * </p>
 */
@objid ("050ad305-9298-48fc-965c-03c0471a3e5f")
public class OperationPropertiesPanel implements IPanelProvider {
    @objid ("d71a432d-05e2-47d3-bd86-6e28229da8bc")
    private IOperationPropertyModel opModel;

    @objid ("a9c771cc-ed57-4002-af57-abc1058c6b54")
    private ComboViewer kindCombo;

    @objid ("13095e54-6449-4f1c-86d0-faca9949237a")
    private ComboViewer visibilityCombo;

    @objid ("e0293cb4-ffc4-433b-85b7-10a8549a7472")
    private TextElement redefinitionTextElement;

    @objid ("6b299982-a165-4194-8921-34993a4815d0")
    private Composite container;

    @objid ("0627c74a-38f2-4559-b5f6-fd673a2fc128")
    private Text nameText;

    @objid ("40c9ef18-c627-4f6c-8d01-347c48e36dd0")
    private Button isAbstractCheckbox;

    @objid ("9154f3da-fab5-4091-959b-d19c11a0e4ce")
    private Button isClassCheckbox;

    @objid ("553b650b-cd27-4068-b8d1-7ab14ba06c24")
    private Button isFinalCheckbox;

    @objid ("56d10437-6dd8-48c7-801b-62b079ebe4d9")
    @Override
    public Object createPanel(Composite parent) {
        this.container = new Composite(parent, SWT.BORDER);
        final GridLayout gl = new GridLayout(1, false);
        gl.marginHeight = 8;
        gl.marginWidth = 2;
        
        this.container.setLayout(gl);
        
        final Composite nameField = createNameField(this.container);
        nameField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        // Kind
        final Composite kindField = createKindField(this.container);
        kindField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        // Visibility
        final Composite visibilityField = createVisibilityField(this.container);
        visibilityField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        // IsAbstract
        final Composite abstractField = createIsAbstractField(this.container);
        abstractField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        // IsClass
        final Composite isclassField = createIsClassField(this.container);
        isclassField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        // IsFinal
        final Composite isfinalField = createIsFinalField(this.container);
        isfinalField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        // Redefines
        /*
         * final Composite redefinesField = createRedefinesField(this.container); redefinesField.setLayoutData(new
         * GridData(SWT.FILL, SWT.CENTER, true, false));
         */
        return this.container;
    }

    @objid ("b4bb19d0-2d95-4e89-bde9-67583ad85817")
    @Override
    public Object getPanel() {
        return this.container;
    }

    @objid ("3967a03b-a149-4380-9f2c-6a118594334d")
    @Override
    public void setInput(Object input) {
        if (input instanceof IOperationPropertyModel) {
            this.opModel = (IOperationPropertyModel) input;
        
            this.nameText.setText(this.opModel.getName());
            this.isAbstractCheckbox.setSelection(this.opModel.isAbstract());
            this.isClassCheckbox.setSelection(this.opModel.isClass());
            this.isFinalCheckbox.setSelection(this.opModel.isFinal());
            this.kindCombo.setSelection(new StructuredSelection(this.opModel.getOperationType()));
            this.visibilityCombo.setSelection(new StructuredSelection(this.opModel.getVisibility()));
        
            /*
             * this.redefinitionTextElement.getTextControl().setText("");
             */
        
        } else {
            this.opModel = null;
            this.nameText.setText("");
        }
        setReadOnly(this.opModel != null && !this.opModel.isModifiable());
    }

    @objid ("345b0f3e-3fdc-480c-94fd-fca4035a13a5")
    @Override
    public Object getInput() {
        return this.opModel;
    }

    @objid ("8a316bec-a34d-465e-bf68-b51fe0f0f344")
    private Composite createNameField(Composite parent) {
        final Composite field = new Composite(parent, SWT.NONE);
        final GridLayout l = new GridLayout(2, true);
        l.marginHeight = 0;
        field.setLayout(l);
        
        // Label
        final Label label = new Label(field, SWT.NO_REDRAW_RESIZE);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opName"));
        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        label.setLayoutData(gd);
        
        this.nameText = new Text(field, SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.TOP, true, false);
        this.nameText.setLayoutData(gd);
        
        this.nameText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                final Text text = (Text) e.getSource();
                if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
                    OperationPropertiesPanel.this.opModel.setName(text.getText());
                } else if (e.keyCode == SWT.ESC) {
                    OperationPropertiesPanel.this.nameText.setText(OperationPropertiesPanel.this.opModel.getName());
                }
            }
        });
        
        this.nameText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                final Text text = (Text) e.getSource();
                OperationPropertiesPanel.this.opModel.setName(text.getText());
            }
        });
        return field;
    }

    @objid ("87f35b4b-45d1-4403-a4d2-fe5778d6a642")
    private Composite createKindField(Composite parent) {
        final Composite field = new Composite(parent, SWT.NONE);
        final GridLayout l = new GridLayout(2, true);
        l.marginHeight = 0;
        field.setLayout(l);
        
        final Label label = new Label(field, SWT.NO_REDRAW_RESIZE);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opKind"));
        final GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        label.setLayoutData(gd);
        
        this.kindCombo = new ComboViewer(field, SWT.READ_ONLY);
        this.kindCombo.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        this.kindCombo.setContentProvider(new ArrayContentProvider());
        this.kindCombo.setInput(Arrays.asList("Operation", "Constructor", "Destructor"));
        
        this.kindCombo.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final ComboViewer combo = (ComboViewer) event.getSource();
                final StructuredSelection selection = (StructuredSelection) combo.getSelection();
                if (!selection.isEmpty()) {
                    final String selectedType = (String) selection.getFirstElement();
                    OperationPropertiesPanel.this.opModel.setOperationType(selectedType);
                }
            }
        });
        return field;
    }

    @objid ("4db4ec7e-f259-4525-9c87-5d7faf51f5c1")
    private Composite createVisibilityField(Composite parent) {
        final Composite field = new Composite(parent, SWT.NONE);
        final GridLayout l = new GridLayout(2, true);
        l.marginHeight = 0;
        field.setLayout(l);
        
        final Label label = new Label(field, SWT.NONE);
        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        label.setLayoutData(gd);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opVisibility"));
        
        this.visibilityCombo = new ComboViewer(field, SWT.READ_ONLY);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        this.visibilityCombo.getCombo().setLayoutData(gd);
        this.visibilityCombo.setContentProvider(new ArrayContentProvider());
        this.visibilityCombo.setInput(VisibilityMode.VALUES);
        this.visibilityCombo.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object o) {
                return MetamodelLabels.getString(o.toString());
            }
        });
        
        this.visibilityCombo.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final ComboViewer combo = (ComboViewer) event.getSource();
                final StructuredSelection selection = (StructuredSelection) combo.getSelection();
                if (!selection.isEmpty()) {
                    final VisibilityMode selectedVisibility = (VisibilityMode) selection.getFirstElement();
                    OperationPropertiesPanel.this.opModel.setVisibility(selectedVisibility);
                }
            }
        });
        return field;
    }

    @objid ("5d000f73-e96a-42eb-8375-8386745ca3ca")
    private Composite createIsAbstractField(Composite parent) {
        final Composite field = new Composite(parent, SWT.NONE);
        final GridLayout l = new GridLayout(2, true);
        l.marginHeight = 0;
        field.setLayout(l);
        
        final Label label = new Label(field, SWT.NONE);
        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        label.setLayoutData(gd);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opIsAbstract"));
        
        this.isAbstractCheckbox = new Button(field, SWT.CHECK);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        this.isAbstractCheckbox.setLayoutData(gd);
        
        this.isAbstractCheckbox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final Button checkbox = (Button) e.getSource();
                OperationPropertiesPanel.this.opModel.setAbstract(checkbox.getSelection());
            }
        });
        return field;
    }

    @objid ("32dccd6d-d1e0-4860-bb27-87677461857e")
    private Composite createIsClassField(Composite parent) {
        final Composite field = new Composite(parent, SWT.NONE);
        final GridLayout l = new GridLayout(2, true);
        l.marginHeight = 0;
        field.setLayout(l);
        
        final Label label = new Label(field, SWT.NONE);
        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        label.setLayoutData(gd);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opIsClass"));
        
        this.isClassCheckbox = new Button(field, SWT.CHECK);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        this.isClassCheckbox.setLayoutData(gd);
        
        this.isClassCheckbox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final Button checkbox = (Button) e.getSource();
                OperationPropertiesPanel.this.opModel.setClass(checkbox.getSelection());
            }
        });
        return field;
    }

    @objid ("99248306-5f8d-4c82-928e-a33a88460d87")
    private Composite createIsFinalField(Composite parent) {
        final Composite field = new Composite(parent, SWT.NONE);
        final GridLayout l = new GridLayout(2, true);
        l.marginHeight = 0;
        field.setLayout(l);
        
        final Label label = new Label(field, SWT.NONE);
        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        label.setLayoutData(gd);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opIsFinal"));
        
        this.isFinalCheckbox = new Button(field, SWT.CHECK);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        this.isFinalCheckbox.setLayoutData(gd);
        
        this.isFinalCheckbox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                final Button checkbox = (Button) e.getSource();
                OperationPropertiesPanel.this.opModel.setFinal(checkbox.getSelection());
            }
        });
        return field;
    }

    @objid ("6612550a-dd59-42a8-b247-21a353424801")
    private Composite createRedefinesField(Composite parent) {
        final Composite field = new Composite(parent, SWT.NONE);
        final GridLayout l = new GridLayout(2, true);
        l.marginHeight = 0;
        field.setLayout(l);
        
        final Label label = new Label(field, SWT.NONE);
        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        label.setLayoutData(gd);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opRedefined"));
        
        this.redefinitionTextElement = new TextElement(field, SWT.BORDER, true);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        this.redefinitionTextElement.getTextControl().setLayoutData(gd);
        return field;
    }

    @objid ("5e593f1f-3aea-41a1-a60f-7af45789b263")
    private void setReadOnly(boolean ro) {
        this.isAbstractCheckbox.setGrayed(ro);
        this.isClassCheckbox.setGrayed(ro);
        this.isClassCheckbox.setGrayed(ro);
        this.isFinalCheckbox.setGrayed(ro);
        this.kindCombo.getControl().setEnabled(!ro);
        this.nameText.setEnabled(!ro);
        this.visibilityCombo.getControl().setEnabled(!ro);
    }

    @objid ("dcf3386e-3f8b-4949-a3dd-884bf70769ee")
    @Override
    public boolean isRelevantFor(Object obj) {
        return obj instanceof IOperationPropertyModel;
    }

    @objid ("19877886-9ff2-4979-9493-b1fd20e653e5")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("a0187ac2-b54a-4e6f-813e-750277c0522e")
    @Override
    public void dispose() {
        // nothing to do
    }

}
