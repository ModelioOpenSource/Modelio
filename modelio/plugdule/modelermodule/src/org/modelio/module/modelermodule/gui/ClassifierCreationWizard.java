/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.module.modelermodule.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.module.modelermodule.i18n.I18nMessageService;

/**
 * Basic swt dialog asking for a name and classifier sub-type.
 */
@objid ("1c55d9ee-c392-426e-82f3-c9c390a31c4a")
public class ClassifierCreationWizard {
    @objid ("ef79a40d-3eb8-4989-86bd-4bc4f38efb23")
    protected String name;

    @objid ("c2429659-854e-42da-81b6-e78b59a7802a")
    protected String selected;

    @objid ("7bac5507-c8a5-470c-9b33-9ccbf203f4dc")
    protected Instance instance;

    @objid ("df378d9d-024e-4ec7-8b4f-64ee87136fa9")
    protected Text text;

    @objid ("f185cca6-118c-4d3a-9c08-5d993a1bcdd9")
    protected Shell shell;

    /**
     * Get the inputed name.
     * @return a name for the new classifier.
     */
    @objid ("a58792ce-1af2-40d4-83da-9bf3f2f291fa")
    public String getChosenName() {
        return this.name;
    }

    /**
     * Get the selected classifier type.
     * @return a classifier sub-type name: Class, Component, Interface or Node.
     */
    @objid ("c0825a83-6a1a-465d-83b4-3f3eba14d53c")
    public String getChosenClass() {
        return this.selected;
    }

    /**
     * Default constructor.
     * @param parent the parent shell.
     * @param instance the instance indicating which classifier sub-types should be made available.
     * Interfaces and Nodes won't be available is the instance has ports.
     */
    @objid ("2a5f40e9-e5f1-480b-87a5-3aa7ee34495e")
    public  ClassifierCreationWizard(final Shell parent, final Instance instance) {
        this.name = "";
        this.selected = I18nMessageService.getString("module.gui.classifierWizard.class");
        this.instance = instance;
        
        createContents(parent);
        
    }

    /**
     * Open the window
     */
    @objid ("6a046262-483f-4bde-b794-59a06b86dfe1")
    public void open() {
        ShellHelper.centerShell(this.shell);
        
        this.shell.open();
        this.shell.layout();
        
        final Display display = this.shell.getDisplay();
        while (!this.shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        
    }

    /**
     * Create contents of the window
     */
    @objid ("f26b626e-0f37-4eb4-b7e8-17fba4bcc6b2")
    protected void createContents(Shell parent) {
        this.shell = new Shell(parent, SWT.DIALOG_TRIM);
        
        this.shell.setLayout(new FormLayout());
        this.shell.setText(I18nMessageService.getString("module.gui.classifierWizard.title"));
        
        final Label nameLabel = new Label(this.shell, SWT.NONE);
        nameLabel.setAlignment(SWT.CENTER);
        final FormData fd_nameLabel = new FormData();
        fd_nameLabel.right = new FormAttachment(100, -5);
        fd_nameLabel.left = new FormAttachment(0, 5);
        fd_nameLabel.top = new FormAttachment(0, 5);
        nameLabel.setLayoutData(fd_nameLabel);
        nameLabel.setText(I18nMessageService.getString("module.gui.classifierWizard.name"));
        
        this.text = new Text(this.shell, SWT.BORDER);
        final FormData fd_text = new FormData();
        fd_text.right = new FormAttachment(nameLabel, 0, SWT.RIGHT);
        fd_text.top = new FormAttachment(nameLabel, 5, SWT.BOTTOM);
        fd_text.left = new FormAttachment(nameLabel, 0, SWT.LEFT);
        this.text.setText(this.instance.getName());
        this.text.setLayoutData(fd_text);
        
        final Label classLabel = new Label(this.shell, SWT.NONE);
        classLabel.setAlignment(SWT.CENTER);
        final FormData fd_classLabel = new FormData();
        fd_classLabel.right = new FormAttachment(this.text, 0, SWT.RIGHT);
        fd_classLabel.top = new FormAttachment(this.text, 5, SWT.BOTTOM);
        fd_classLabel.left = new FormAttachment(this.text, 0, SWT.LEFT);
        classLabel.setLayoutData(fd_classLabel);
        classLabel.setText(I18nMessageService.getString("module.gui.classifierWizard.type"));
        
        Composite buttons = createButtons();
        
        Group composite_1;
        composite_1 = new Group(this.shell, SWT.NONE);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        composite_1.setLayout(gridLayout);
        FormData fd_composite = new FormData();
        fd_composite.top = new FormAttachment(classLabel, 0, SWT.DEFAULT);
        fd_composite.left = new FormAttachment(0, 5);
        fd_composite.right = new FormAttachment(100, -5);
        fd_composite.bottom = new FormAttachment(buttons, 0);
        composite_1.setLayoutData(fd_composite);
        
        createClasses(composite_1);
        
        this.shell.pack();
        this.shell.setSize(this.shell.getSize().x + 30, this.shell.getSize().y);
        
    }

    @objid ("69140fe7-a73d-4231-8bc9-ce16ef83859f")
    private Composite createButtons() {
        Composite composite = new Composite(this.shell, SWT.NONE);
        final RowLayout rowLayout = new RowLayout();
        rowLayout.justify = true;
        composite.setLayout(rowLayout);
        final FormData fd_composite_1 = new FormData();
        fd_composite_1.left = new FormAttachment(0, 5);
        fd_composite_1.bottom = new FormAttachment(100, 0);
        fd_composite_1.right = new FormAttachment(100, -5);
        composite.setLayoutData(fd_composite_1);
        
        final Composite composite_2 = new Composite(composite, SWT.NONE);
        composite_2.setLayout(new FillLayout());
        
        final Button okButton = new Button(composite_2, SWT.NONE);
        okButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                try {
                    ClassifierCreationWizard.this.name = ClassifierCreationWizard.this.text.getText();
                } catch (Exception e) {
                    ClassifierCreationWizard.this.name = "";
                    ClassifierCreationWizard.this.selected = "";
                }
                dispose();
            }
        });
        okButton.setText(I18nMessageService.getString("module.gui.ok"));
        
        Button cancelButton;
        cancelButton = new Button(composite_2, SWT.NONE);
        cancelButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                ClassifierCreationWizard.this.name = "";
                dispose();
            }
        });
        cancelButton.setText(I18nMessageService.getString("module.gui.cancel"));
        return composite;
    }

    @objid ("14a7cace-d73f-4b0b-8aaf-ba53950c4ad6")
    private void createClasses(final Group composite_1) {
        SelectionAdapter listener = new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                ClassifierCreationWizard.this.selected = ((String) event.widget.getData());
            }
        };
        
        final Button classButton = new Button(composite_1, SWT.RADIO);
        classButton.setText(I18nMessageService.getString("module.gui.classifierWizard.class"));
        classButton.setData(I18nMessageService.getString("module.gui.classifierWizard.class"));
        classButton.setSelection(true);
        classButton.addSelectionListener(listener);
        
        final Button componentButton = new Button(composite_1, SWT.RADIO);
        componentButton.setText(I18nMessageService.getString("module.gui.classifierWizard.component"));
        componentButton.setData(I18nMessageService.getString("module.gui.classifierWizard.component"));
        componentButton.addSelectionListener(listener);
        
        if (this.instance != null && this.instance.getPart(Port.class).size() == 0) {
            final Button interfaceButton = new Button(composite_1, SWT.RADIO);
            interfaceButton.setText(I18nMessageService.getString("module.gui.classifierWizard.interface"));
            interfaceButton.setData(I18nMessageService.getString("module.gui.classifierWizard.interface"));
            interfaceButton.addSelectionListener(listener);
        
            final Button nodeButton = new Button(composite_1, SWT.RADIO);
            nodeButton.setText(I18nMessageService.getString("module.gui.classifierWizard.node"));
            nodeButton.setData(I18nMessageService.getString("module.gui.classifierWizard.node"));
            nodeButton.addSelectionListener(listener);
        }
        
    }

    @objid ("9355ee74-82dc-4501-b12d-d94133a979be")
    protected void dispose() {
        this.shell.dispose();
    }

}
