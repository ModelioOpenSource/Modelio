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

package org.modelio.audit.preferences.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.AuditModelController;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.audit.preferences.ui.editingsupports.AuditActivationEditingSupport;
import org.modelio.audit.preferences.ui.editingsupports.AuditSeverityEditingSupport;
import org.modelio.audit.service.IAuditService;
import org.modelio.vbasic.files.FileUtils;

/**
 * Audit preferences editor GUI.
 */
@objid ("2b7f61d2-a060-423f-9acf-5acf8d8b4dc9")
public class AuditPreferenceUi {
    @objid ("bb68e997-f5a1-4881-b860-ee989c743c90")
    private static final String AUDIT_CONF_FILTER = "*.xml";

    @objid ("b4f2427c-a1ef-4a7f-ac1a-f5ebb76b07d8")
    private static final String AUDIT_CONF_EXT = ".xml";

    @objid ("5b697db6-2d8e-452c-bd31-448d4a5940e3")
    private Button exportToFile;

    @objid ("6a8eab62-89f0-4510-840e-fe26f4afaf0b")
    private Button importFromFile;

    @objid ("974124ca-0adc-4508-8e3a-51a7a799015a")
    private Button factory;

    @objid ("231da431-be9a-40c6-a0ed-54120b7595fa")
    private Button save;

    @objid ("7e70e13b-29f7-429e-8712-0d3441dc0659")
    private TreeViewer treeViewer;

    @objid ("c2c1e386-680a-40cf-829c-81072dded501")
    private AuditConfigurationModel preferences;

    @objid ("d5633ba9-d650-478c-94e8-f508c1c352e4")
    private IAuditService auditService;

    /**
     * Initialize the preference editor.
     * @param auditService the audit service
     */
    @objid ("b20b8808-ba8a-46d9-8367-6fe4544fd982")
    public AuditPreferenceUi(IAuditService auditService) {
        this.auditService = auditService;
        this.preferences = this.auditService.getConfigurationModel();
    }

    /**
     * Create the GUI
     * @param parent the parent composite where the content must be created.
     * @return the created root composite
     */
    @objid ("0dd74ccb-3555-44b4-8b96-9f0c4b493eb5")
    public Control createContents(Composite parent) {
        Composite root = new Composite(parent, SWT.NONE);
        root.setLayout(new GridLayout(1, true));
        
        this.treeViewer = new TreeViewer(root, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        this.treeViewer.getTree().setHeaderVisible(false);
        this.treeViewer.getTree().setLinesVisible(true);
        
        // Layout the viewer
        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 1;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        gridData.minimumHeight = 400;
        gridData.minimumWidth = 900;
        this.treeViewer.getControl().setLayoutData(gridData);
        
        // Create columns
        String[] columnTitles = { Audit.I18N.getString("Audit.PreferenceUI.Id"), Audit.I18N.getString("Audit.PreferenceUI.Activation"), Audit.I18N.getString("Audit.PreferenceUI.Severity"), Audit.I18N.getString("Audit.PreferenceUI.Summary") };
        int[] columnInitialWidths = { 150, 25, 25, 730 };
        EditingSupport[] editingSupport = { null, new AuditSeverityEditingSupport(this.treeViewer), new AuditActivationEditingSupport(this.treeViewer), null };
        
        for (int i = 0; i < columnTitles.length; i++) {
            TreeViewerColumn column = createTreeViewerColumn(columnTitles[i], columnInitialWidths[i]);
            column.setLabelProvider(new AuditPropertyLabelProvider(this.preferences.getAuditConfigurationPlan()));
            column.setEditingSupport(editingSupport[i]);
        }
        
        AuditPropertyContentProvider content = new AuditPropertyContentProvider();
        this.treeViewer.setContentProvider(content);
        
        this.treeViewer.setInput(this.preferences);
        
        // The save/restore/factory settings button bar
        Composite composite = new Composite(root, SWT.NONE);
        GridData dataComposite = new GridData(SWT.FILL, SWT.FILL, true, false);
        composite.setLayoutData(dataComposite);
        composite.setLayout(new RowLayout(SWT.HORIZONTAL));
        
        this.exportToFile = new Button(composite, SWT.NONE);
        this.exportToFile.setText(Audit.I18N.getMessage("Preferences.Audit.Export.Label"));
        this.exportToFile.setToolTipText(Audit.I18N.getMessage("Preferences.Audit.Export.Tooltip"));
        
        this.importFromFile = new Button(composite, SWT.NONE);
        this.importFromFile.setText(Audit.I18N.getMessage("Preferences.Audit.Import.Label"));
        this.importFromFile.setToolTipText(Audit.I18N.getMessage("Preferences.Audit.Import.Tooltip"));
        
        this.factory = new Button(composite, SWT.NONE);
        this.factory.setText(Audit.I18N.getMessage("Preferences.Audit.Factory.Label"));
        this.factory.setToolTipText(Audit.I18N.getMessage("Preferences.Audit.Factory.Tooltip"));
        
        this.save = new Button(composite, SWT.NONE);
        this.save.setText(Audit.I18N.getMessage("Audit.PreferenceUI.Save.Label"));
        this.save.setToolTipText(Audit.I18N.getMessage("Audit.PreferenceUI.Save.Tooltip"));
        
        addListeners();
        return root;
    }

    @objid ("c9d05c97-84cf-4bc6-bf9c-d6f7ce1ba715")
    private TreeViewerColumn createTreeViewerColumn(String title, int bound) {
        final TreeViewerColumn column = new TreeViewerColumn(this.treeViewer, SWT.NONE);
        column.getColumn().setText(title);
        column.getColumn().setWidth(bound);
        column.getColumn().setResizable(true);
        column.getColumn().setMoveable(true);
        return column;
    }

    @objid ("9fdd3a8b-ea1b-4995-8d8f-5fe67d3d33bf")
    private void addListeners() {
        this.exportToFile.addListener(SWT.Selection, e -> onExportConfiguration());
        this.importFromFile.addListener(SWT.Selection, e -> onImportConfiguration());
        this.factory.addListener(SWT.Selection, e -> onResetConfiguration());
        this.save.addListener(SWT.Selection, e -> onSaveConfiguration());
    }

    @objid ("31e99d6a-2708-4a2a-9dd8-823ac0f8e17e")
    private void reportError(String title, IOException e) {
        Shell parentShell = this.save.getShell();
        String message;
        if (e instanceof FileSystemException) {
            message = FileUtils.getLocalizedMessage(e);
        } else {
            message = e.getLocalizedMessage();
        }
        
        MessageDialog.openError(parentShell, title, message);
    }

    @objid ("ee8bdeac-b155-4b7c-a8ae-4e6f12392593")
    private void onSaveConfiguration() {
        // replace the current by the updated plan
        this.auditService.apply(this.preferences);
    }

    @objid ("3720bb4a-09ea-45d9-bce4-5ca41bbe2df0")
    private void onResetConfiguration() {
        // use a configurator for factory settings
        this.preferences = this.auditService.getFactorySettings();
        this.treeViewer.setInput(this.preferences);
    }

    @objid ("028925ef-2ed4-4fc9-a870-0c8922282da2")
    private void onImportConfiguration() {
        // import audit configuration from file
        Shell parentShell = Display.getDefault().getActiveShell();
        FileDialog dlg = new FileDialog(parentShell, SWT.OPEN);
        dlg.setFilterExtensions(new String[] { AuditPreferenceUi.AUDIT_CONF_FILTER });
        dlg.setFilterNames(new String[] { Audit.I18N.getString("Preferences.Audit.Export.FileType") });
        String result = dlg.open();
        if (result != null) { // Result is null when canceling
            File file = new File(dlg.getFilterPath(), dlg.getFileName());
        
            // use a configurator for 'file' settings
            AuditModelController configurator = new AuditModelController(this.auditService.getConfigurationModel());
            try {
                configurator.applyAuditConfiguration(file);
        
                this.preferences = configurator.getModel();
                this.treeViewer.setInput(this.preferences);
            } catch (IOException e) {
                reportError(Audit.I18N.getString("Preferences.Audit.Import.Error"), e);
            }
        }
    }

    @objid ("de4899d9-7695-42af-b99c-6f14ce349f9e")
    private void onExportConfiguration() {
        // export current audit config to file
        
        Shell parentShell = this.save.getShell();
        FileDialog dlg = new FileDialog(parentShell, SWT.SAVE);
        dlg.setFilterExtensions(new String[] { AuditPreferenceUi.AUDIT_CONF_FILTER });
        dlg.setFilterNames(new String[] { Audit.I18N.getString("Preferences.Audit.Import.FileType") });
        String result = dlg.open();
        if (result != null) { // Result is null when canceling
            File file = new File(dlg.getFilterPath(), dlg.getFileName());
        
            if (file.getName().endsWith(AuditPreferenceUi.AUDIT_CONF_EXT) == false) {
                file = new File(file.getAbsolutePath() + AuditPreferenceUi.AUDIT_CONF_EXT);
            }
        
            try {
                // copy original settings
                Files.copy(this.auditService.getConfigurationFile().toPath(), file.toPath());
        
                // use a configurator
                AuditModelController configurator = new AuditModelController(this.preferences);
        
                // save to the new file
                configurator.writeConfiguration(file);
            } catch (IOException e) {
                reportError(Audit.I18N.getString("Preferences.Audit.Export.Error"), e);
            }
        
        }
    }

}
