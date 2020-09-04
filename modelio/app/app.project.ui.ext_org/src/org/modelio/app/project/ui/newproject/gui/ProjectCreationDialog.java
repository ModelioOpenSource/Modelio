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

package org.modelio.app.project.ui.newproject.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.app.project.core.creation.ProjectCreationDataModel;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.ui.dialog.ModelioDialog;

/**
 * Project creation wizard dialog.
 */
@objid ("004483ce-cc35-1ff2-a7f4-001ec947cd2a")
public class ProjectCreationDialog extends ModelioDialog {
    @objid ("3ef86f84-06b3-418a-981a-f314b28cf71c")
    private Button createButton = null;

    @objid ("b2070684-13a4-4d07-b658-1c93760c3ff6")
    private Button cancelButton = null;

    @objid ("0044e242-cc35-1ff2-a7f4-001ec947cd2a")
    private final ProjectCreationDataModel dataModel;

    @objid ("0044cf50-cc35-1ff2-a7f4-001ec947cd2a")
    private ProjectPanel projectPanel = null;

    @objid ("00459750-cc35-1ff2-a7f4-001ec947cd2a")
    private ProjectCreationController projectController;

    @objid ("0001e5a0-be2f-10b4-9941-001ec947cd2a")
    private final IModuleStore moduleCatalog;

    /**
     * Initialize the dialog.
     * @param moduleCatalog
     * 
     * @param parentShell The parent shell.
     * @param dataModel The dialog data model.
     */
    @objid ("0044ee9a-cc35-1ff2-a7f4-001ec947cd2a")
    public ProjectCreationDialog(final Shell parentShell, final ProjectCreationDataModel dataModel, IModuleStore moduleCatalog) {
        super(parentShell);
        this.dataModel = dataModel;
        this.moduleCatalog = moduleCatalog;
    }

    @objid ("0044ef44-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        this.createButton = createButton(parent, IDialogConstants.OK_ID, AppProjectUiExt.I18N.getString("Create"), false);
        this.cancelButton = createButton(parent, IDialogConstants.CANCEL_ID, AppProjectUiExt.I18N.getString("Cancel"), true);
        updateButtons(true);
    }

    @objid ("0044efda-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public boolean close() {
        this.projectPanel.removeListener(this.projectController);
        return super.close();
    }

    @objid ("00498a04-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public Control createContentArea(final Composite parent) {
        Composite area = new Composite(parent, SWT.NONE);
        area.setLayout(new GridLayout(1, true));
        area.setLayoutData(new GridData(GridData.FILL_BOTH));
        this.projectPanel = new ProjectPanel(area, this.dataModel, this.moduleCatalog);
        this.projectPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        this.projectController = new ProjectCreationController(this, this.dataModel, this.moduleCatalog);
        
        this.projectPanel.refresh();
        return area;
    }

    @objid ("0048e374-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public void init() {
        setLogoImage(null);
        // Put the messages in the banner area
        getShell().setText(AppProjectUiExt.I18N.getString("CreateProjectDialogTitle"));
        setTitle(AppProjectUiExt.I18N.getString("CreateProjectDialogTitle"));
        setMessage(AppProjectUiExt.I18N.getString("CreateProjectDialogMessage"));
        getShell().setMinimumSize(450, 350);
        
        this.projectPanel.projectNameText.setText(this.dataModel.getProjectName());
    }

    @objid ("0048e4aa-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public void setErrorMessage(final String newErrorMessage) {
        super.setErrorMessage(newErrorMessage);
    }

    /**
     * Update buttons state.
     * 
     * @param dataOk ??
     */
    @objid ("0048e540-cc35-1ff2-a7f4-001ec947cd2a")
    public void updateButtons(final boolean dataOk) {
        if (this.cancelButton == null || this.createButton == null) {
            return;
        }
        
        if (dataOk) {
            this.createButton.setEnabled(true);
            getShell().setDefaultButton(this.createButton);
        } else {
            this.createButton.setEnabled(false);
            getShell().setDefaultButton(this.cancelButton);
        }
    }

    @objid ("00470e28-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    protected String getHelpId() {
        return AppProjectUiExt.I18N.getString("ProjectCreationDialog.HelpId");
    }

    @objid ("00470ed2-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    protected void okPressed() {
        this.projectController.updateDataModel();
        super.okPressed();
    }

    @objid ("00470f72-cc35-1ff2-a7f4-001ec947cd2a")
    protected boolean validateData() {
        return this.projectController.validateData();
    }

    @objid ("0091210c-c2c2-10b4-9941-001ec947cd2a")
    public ProjectPanel getProjectPanel() {
        return this.projectPanel;
    }

    @objid ("ebd04149-c269-409a-9494-70c301829420")
    @Override
    protected Point getInitialSize() {
        return new Point(450, 350);
    }

    /**
     * The project tab.
     */
    @objid ("0044b06a-cc35-1ff2-a7f4-001ec947cd2a")
    static class ProjectPanel extends Composite {
        @objid ("9fc8402d-c974-4225-bb51-1c7ba4a57a71")
        private Text projectNameText = null;

        @objid ("de77df9e-e3c0-4307-a533-5b687b723b3e")
        private Text projectDescriptionText = null;

        @objid ("bac54d7e-44f0-4125-b2a2-a73e60bfae6c")
        private Button javaCheckBox;

        @objid ("004721ba-cc35-1ff2-a7f4-001ec947cd2a")
        private final ProjectCreationDataModel dataModel;

        @objid ("0000672a-be2f-10b4-9941-001ec947cd2a")
        private final IModuleStore moduleCatalog;

        @objid ("0044d702-cc35-1ff2-a7f4-001ec947cd2a")
        public ProjectPanel(final Composite parent, final ProjectCreationDataModel dataModel, IModuleStore moduleCatalog) {
            super(parent, SWT.NONE);
            this.dataModel = dataModel;
            this.moduleCatalog = moduleCatalog;
            createContent();
        }

        @objid ("0044d798-cc35-1ff2-a7f4-001ec947cd2a")
        public void addListener(final ProjectCreationController listener) {
            this.projectNameText.addListener(SWT.Modify, listener);
            this.projectDescriptionText.addListener(SWT.Modify, listener);
        }

        @objid ("0048779a-cc35-1ff2-a7f4-001ec947cd2a")
        public String getProjectDescription() {
            return this.projectDescriptionText.getText();
        }

        @objid ("0048783a-cc35-1ff2-a7f4-001ec947cd2a")
        public String getProjectName() {
            return this.projectNameText.getText();
        }

        @objid ("0047267e-cc35-1ff2-a7f4-001ec947cd2a")
        public void invalidateProjectNameField(final boolean onOff) {
            if (onOff) {
                this.projectNameText.setForeground(getDisplay().getSystemColor(SWT.COLOR_RED));
            } else {
                this.projectNameText.setForeground(getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
            }
        }

        @objid ("0047271e-cc35-1ff2-a7f4-001ec947cd2a")
        public void refresh() {
            setProjectName(this.dataModel.getProjectName());
            setProjectDescription(this.dataModel.getProjectDescription());
        }

        @objid ("0047b418-cc35-1ff2-a7f4-001ec947cd2a")
        public void removeListener(final ProjectCreationController listener) {
            this.projectNameText.removeListener(SWT.Modify, listener);
            this.projectDescriptionText.removeListener(SWT.Modify, listener);
        }

        @objid ("0049b3d0-cc35-1ff2-a7f4-001ec947cd2a")
        public void setProjectDescription(final String projectDescription) {
            this.projectDescriptionText.setText(projectDescription);
        }

        @objid ("0049b466-cc35-1ff2-a7f4-001ec947cd2a")
        public void setProjectName(final String projectName) {
            this.projectNameText.setText(projectName);
            this.projectNameText.setSelection(0, projectName.length());
            this.projectNameText.setFocus();
        }

        @objid ("00472eb2-cc35-1ff2-a7f4-001ec947cd2a")
        private void createContent() {
            // Set the layout
            final GridLayout gridLayout = new GridLayout();
            gridLayout.numColumns = 2;
            gridLayout.makeColumnsEqualWidth = false;
            setLayout(gridLayout);
            
            createProjectNameField();
            createProjectDescriptionField();
            createJavaProjectCheckBox();
        }

        @objid ("00484b62-cc35-1ff2-a7f4-001ec947cd2a")
        private void createProjectDescriptionField() {
            final Label descriptionLabel = new Label(this, SWT.NONE);
            final GridData gd_descriptionLabel = new GridData(SWT.LEFT, SWT.TOP, false, false);
            descriptionLabel.setLayoutData(gd_descriptionLabel);
            descriptionLabel.setText(AppProjectUiExt.I18N.getString("Description"));
            
            this.projectDescriptionText = new Text(this, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
            final GridData gd_descriptionText = new GridData(SWT.FILL, SWT.FILL, true, true);
            this.projectDescriptionText.setLayoutData(gd_descriptionText);
        }

        @objid ("0047101c-cc35-1ff2-a7f4-001ec947cd2a")
        private void createProjectNameField() {
            final Label projectNameLabel = new Label(this, SWT.NONE);
            projectNameLabel.setText(AppProjectUiExt.I18N.getString("ProjectName"));
            
            this.projectNameText = new Text(this, SWT.BORDER);
            final GridData gd_projectNameText = new GridData(SWT.FILL, SWT.CENTER, true, false);
            this.projectNameText.setLayoutData(gd_projectNameText);
        }

        @objid ("008ccddc-7ecb-10af-9941-001ec947cd2a")
        private void createJavaProjectCheckBox() {
            @SuppressWarnings("unused")
            Label empty = new Label(this, SWT.NONE);
            this.javaCheckBox = new Button(this, SWT.CHECK);
            this.javaCheckBox.setText(AppProjectUiExt.I18N.getString("JavaProjectCheckbox.label"));
            final GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
            this.javaCheckBox.setLayoutData(gd);
            this.javaCheckBox.setSelection(false);
        }

        @objid ("0092cbb0-c2c2-10b4-9941-001ec947cd2a")
        public boolean isJavaChecked() {
            return this.javaCheckBox.getSelection();
        }

    }

}
