/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("f9195c4a-b60a-4ef4-b19f-64a02dea4ab2")
    private ComboViewer kindCombo;

    @objid ("c5b9ff00-d338-47e0-9b73-ea14de9653f4")
    private ComboViewer visibilityCombo;

    @objid ("77ff2691-e41b-4c75-91c4-cdb0600f3c3b")
    private Composite container;

    @objid ("5cf779d0-73cb-4c0e-852e-91322302c247")
    private Text nameText;

    @objid ("a64b8232-0c09-4a2f-a114-b0beea98d990")
    private Button isAbstractCheckbox;

    @objid ("ed0992cc-5662-4656-bd0a-236bb64a8f82")
    private Button isClassCheckbox;

    @objid ("1e6c7441-7712-4966-84b5-6803c2c5509f")
    private Button isFinalCheckbox;

    @objid ("d71a432d-05e2-47d3-bd86-6e28229da8bc")
    private IOperationPropertyModel opModel;

    @objid ("e0293cb4-ffc4-433b-85b7-10a8549a7472")
    private TextElement redefinitionTextElement;

    @objid ("56d10437-6dd8-48c7-801b-62b079ebe4d9")
    @Override
    public Object createPanel(Composite parent) {
        this.container = new Composite(parent, SWT.NONE);
        final GridLayout gl = new GridLayout(7, true);
        gl.marginHeight = 4;
        gl.marginWidth = 2;
        gl.verticalSpacing = 2;
        gl.horizontalSpacing = 10;
        
        this.container.setLayout(gl);
        
        createNameField(this.container);
        
        // Kind
        createKindField(this.container);
        
        // Visibility
        createVisibilityField(this.container);
        
        // IsAbstract
        createIsAbstractField(this.container);
        
        // IsClass
        createIsClassField(this.container);
        
        // IsFinal
        createIsFinalField(this.container);
        
        // Redefines
        /*
         * final Composite redefinesField = createRedefinesField(this.container); redefinesField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
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
    private void createNameField(Composite parent) {
        // Label
        final Label label = new Label(parent, SWT.NO_REDRAW_RESIZE);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opName"));
        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        label.setLayoutData(gd);
        
        this.nameText = new Text(parent, SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.TOP, true, false);
        gd.horizontalSpan = 6;
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
    }

    @objid ("87f35b4b-45d1-4403-a4d2-fe5778d6a642")
    private void createKindField(Composite parent) {
        final Label label = new Label(parent, SWT.NO_REDRAW_RESIZE);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opKind"));
        final GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        label.setLayoutData(gd);
        
        this.kindCombo = new ComboViewer(parent, SWT.READ_ONLY);
        this.kindCombo.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
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
    }

    @objid ("4db4ec7e-f259-4525-9c87-5d7faf51f5c1")
    private void createVisibilityField(Composite parent) {
        final Label label = new Label(parent, SWT.NONE);
        GridData gd = new GridData(SWT.RIGHT, SWT.CENTER, true, false);
        label.setLayoutData(gd);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opVisibility"));
        
        this.visibilityCombo = new ComboViewer(parent, SWT.READ_ONLY);
        gd = new GridData(SWT.FILL, SWT.CENTER, false, false);
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
    }

    @objid ("5d000f73-e96a-42eb-8375-8386745ca3ca")
    private void createIsAbstractField(Composite parent) {
        //        final Label label = new Label(parent, SWT.NONE);
        //        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        //        label.setLayoutData(gd);
        //        //label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opIsAbstract"));
        
                this.isAbstractCheckbox = new Button(parent, SWT.CHECK);
                GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
                this.isAbstractCheckbox.setLayoutData(gd);
                this.isAbstractCheckbox.setText(EditionDialogs.I18N.getString("OperationEditPanel.opIsAbstract"));
        
                this.isAbstractCheckbox.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        final Button checkbox = (Button) e.getSource();
                        OperationPropertiesPanel.this.opModel.setAbstract(checkbox.getSelection());
                    }
                });
    }

    @objid ("32dccd6d-d1e0-4860-bb27-87677461857e")
    private void createIsClassField(Composite parent) {
        //        final Label label = new Label(parent, SWT.NONE);
        //        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        //        label.setLayoutData(gd);
        //        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opIsClass"));
        
                this.isClassCheckbox = new Button(parent, SWT.CHECK);
                GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
                this.isClassCheckbox.setLayoutData(gd);
                this.isClassCheckbox.setText(EditionDialogs.I18N.getString("OperationEditPanel.opIsClass"));
                this.isClassCheckbox.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        final Button checkbox = (Button) e.getSource();
                        OperationPropertiesPanel.this.opModel.setClass(checkbox.getSelection());
                    }
                });
    }

    @objid ("99248306-5f8d-4c82-928e-a33a88460d87")
    private void createIsFinalField(Composite parent) {
        //        final Label label = new Label(parent, SWT.NONE);
        //        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        //        label.setLayoutData(gd);
        //        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opIsFinal"));
        
                this.isFinalCheckbox = new Button(parent, SWT.CHECK);
                GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
                this.isFinalCheckbox.setLayoutData(gd);
                this.isFinalCheckbox.setText(EditionDialogs.I18N.getString("OperationEditPanel.opIsFinal"));
        
                this.isFinalCheckbox.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        final Button checkbox = (Button) e.getSource();
                        OperationPropertiesPanel.this.opModel.setFinal(checkbox.getSelection());
                    }
                });
    }

    @objid ("6612550a-dd59-42a8-b247-21a353424801")
    private void createRedefinesField(Composite parent) {
        final Label label = new Label(parent, SWT.NONE);
        GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
        label.setLayoutData(gd);
        label.setText(EditionDialogs.I18N.getString("OperationEditPanel.opRedefined"));
        
        this.redefinitionTextElement = new TextElement(parent, SWT.BORDER, true);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        this.redefinitionTextElement.getTextControl().setLayoutData(gd);
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
