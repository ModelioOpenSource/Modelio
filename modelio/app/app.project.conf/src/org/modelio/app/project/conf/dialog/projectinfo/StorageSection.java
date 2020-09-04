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

package org.modelio.app.project.conf.dialog.projectinfo;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.plugin.AppProjectConf;
import org.modelio.ui.UIColor;
import org.modelio.vbasic.files.FileUtils;

/**
 * Displays the 'storage' informations about a project.
 */
@objid ("a746126c-33f6-11e2-a514-002564c97630")
class StorageSection {
    /**
     * The project that is currently being displayed by the section.
     */
    @objid ("a746126e-33f6-11e2-a514-002564c97630")
    protected ProjectModel displayedProject;

    @objid ("a746397d-33f6-11e2-a514-002564c97630")
    protected Text storagePath;

    @objid ("a746397e-33f6-11e2-a514-002564c97630")
    protected Text storageSize;

    @objid ("a7459d5a-33f6-11e2-a514-002564c97630")
    protected Text projectDate;

    @objid ("a746397f-33f6-11e2-a514-002564c97630")
    public StorageSection() {
    }

    @objid ("a746608e-33f6-11e2-a514-002564c97630")
    public void setInput(ProjectModel projectAdapter) {
        this.displayedProject = projectAdapter;
        
        if (projectAdapter == null) {
            clearFields();
        } else {
            fillFields();
        }
    }

    @objid ("a7466091-33f6-11e2-a514-002564c97630")
    private void fillFields() {
        if (this.displayedProject.getProjectFileStructure() != null) {
            this.storagePath.setText(this.displayedProject.getProjectFileStructure().getProjectPath().toString());
            this.storageSize.setText(AppProjectConf.I18N.getString("StorageSection.ComputingSize"));
        
            // Compute storage size in another thread
            final Text lstorageSize = this.storageSize;
            final Display display = this.storageSize.getDisplay();
            new Thread(AppProjectConf.I18N.getString("StorageSection.ComputingSize")) {
                @Override
                public void run() {
                    long projectSize = -1;
                    String msg = "?";
                    try {
                        projectSize = FileUtils.computeSize(StorageSection.this.displayedProject.getProjectFileStructure().getProjectPath()) / 1024 / 1024;    // Unit: Megabyte
                        msg = Long.toString(projectSize) + " " + AppProjectConf.I18N.getString("StorageSection.SizeUnit");
                    } catch (FileSystemException e) {
                        AppProjectConf.LOG.warning(e);
                        msg = FileUtils.getLocalizedMessage(e);
                    } catch (IOException e) {
                        AppProjectConf.LOG.warning(e);
                        msg = e.getLocalizedMessage();
                    }
        
                    final String textContent = msg;
        
                    display.asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            if (lstorageSize.isDisposed()) {
                                return;
                            }
        
                            lstorageSize.setText(textContent);
                        }
                    });
                }
            }.start();
        } else {
            this.storagePath.setText(""); //$NON-NLS-1$
            this.storageSize.setText(""); //$NON-NLS-1$
        }
        
        this.storagePath.setEnabled(true);
        this.storageSize.setEnabled(true);
        
        try {
            this.projectDate.setText(Files.getLastModifiedTime(this.displayedProject.getProjectFileStructure().getProjectConfFile()).toString());
        } catch (IOException e) {
            this.projectDate.setText(""); //$NON-NLS-1$
        }
        this.projectDate.setEnabled(true);
    }

    @objid ("a746879e-33f6-11e2-a514-002564c97630")
    private void clearFields() {
        this.storagePath.setText(""); //$NON-NLS-1$
        this.storagePath.setEnabled(false);
        this.storageSize.setText(""); //$NON-NLS-1$
        this.storageSize.setEnabled(false);
        this.projectDate.setText(""); //$NON-NLS-1$
        this.projectDate.setEnabled(false);
    }

    @objid ("a74687a0-33f6-11e2-a514-002564c97630")
    public Section createControls(final FormToolkit toolkit, final Composite parent) {
        Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE);
        section.setText(AppProjectConf.I18N.getString("StorageSection.Storage")); //$NON-NLS-1$
        
        section.setExpanded(true);
        
        Composite composite = toolkit.createComposite(section, SWT.WRAP);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.makeColumnsEqualWidth = false;
        composite.setLayout(layout);
        
        // Storage path
        Label storageLabel = toolkit.createLabel(composite, AppProjectConf.I18N.getString("StorageSection.Path"), SWT.NULL); //$NON-NLS-1$
        storageLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true));
        
        this.storagePath = toolkit.createText(composite, "", SWT.NULL); //$NON-NLS-1$
        this.storagePath.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.storagePath.setEditable(false);
        this.storagePath.setForeground(UIColor.LABEL_TIP_FG);
        this.storagePath.setBackground(UIColor.TEXT_READONLY_BG);
        
        // Project date
        Label dateLabel = toolkit.createLabel(composite, AppProjectConf.I18N.getString("StorageSection.Date"), SWT.NULL); //$NON-NLS-1$
        dateLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true));
        
        this.projectDate = toolkit.createText(composite, "", SWT.NULL); //$NON-NLS-1$
        this.projectDate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.projectDate.setEditable(false);
        this.projectDate.setForeground(UIColor.LABEL_TIP_FG);
        this.projectDate.setBackground(UIColor.TEXT_READONLY_BG);
        
        // Project date
        Label sizeLabel = toolkit.createLabel(composite, AppProjectConf.I18N.getString("StorageSection.Size"), SWT.NULL); //$NON-NLS-1$
        sizeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true));
        
        this.storageSize = toolkit.createText(composite, "", SWT.NULL); //$NON-NLS-1$
        this.storageSize.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.storageSize.setEditable(false);
        this.storageSize.setForeground(UIColor.LABEL_TIP_FG);
        this.storageSize.setBackground(UIColor.TEXT_READONLY_BG);
        
        toolkit.paintBordersFor(composite);
        section.setClient(composite);
        return section;
    }

    @objid ("a746d5c0-33f6-11e2-a514-002564c97630")
    private Text createTextField(final FormToolkit toolkit, final Composite composite, final String label, final String value) {
        Label fixedLabel = toolkit.createLabel(composite, label, SWT.NULL);
        GridData gd = new GridData(SWT.RIGHT, SWT.FILL, false, true);
        fixedLabel.setLayoutData(gd);
        
        Text text = toolkit.createText(composite, value, SWT.NULL);
        gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        
        text.setLayoutData(gd);
        text.setEditable(false);
        return text;
    }

}
