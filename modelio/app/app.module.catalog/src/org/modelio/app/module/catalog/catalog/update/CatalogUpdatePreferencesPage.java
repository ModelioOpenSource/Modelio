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

package org.modelio.app.module.catalog.catalog.update;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.modelio.app.module.catalog.plugin.AppModules;
import org.modelio.platform.core.ModelioEnv;
import org.modelio.platform.preferences.plugin.Preferences;
import org.modelio.vbasic.files.FileUtils;

/**
 * Modules catalog preference page.
 */
@objid ("e46387a6-a669-48b9-8131-ca484a12b266")
public class CatalogUpdatePreferencesPage extends FieldEditorPreferencePage {
    /**
     * Display only latest revisions preference key.
     */
    @objid ("4f2fbba1-9b34-45c5-baa9-60e144b94cff")
    public static final String CATALOG_SHOW_LATEST = "ModuleCatalog.ShowLatest";

    /**
     * Display only compatible modules versions preference key.
     */
    @objid ("f7972004-9d30-41f0-ace7-5daf81fe3edf")
    public static final String CATALOG_SHOW_COMPATIBLE = "ModuleCatalog.ShowCompatible";

    @objid ("2031fdc1-fe68-455a-99c5-b8dc40143e45")
    protected Path currentModulePath;

    @objid ("ccc658e7-d065-4734-8168-fc42209824bf")
    @Inject
     ModelioEnv modelioEnv;

    @objid ("040af4a8-d2c2-4d51-b019-8219374a321e")
    private BooleanFieldEditor showLatestField;

    @objid ("a81c510f-6329-47da-b2c2-f648e9952cab")
    private BooleanFieldEditor showCompatibleField;

    @objid ("fb51d859-02f8-4b90-afb1-2d5b0ece4075")
    private DirectoryFieldEditor localCatalogPathFieldEditor;

    /**
     * Public constructor.
     */
    @objid ("1be2933f-72b6-4b49-bdba-c23599147a59")
    public CatalogUpdatePreferencesPage() {
        init();
    }

    @objid ("15d552ed-ef13-4b70-976f-38cb7885a75d")
    private void init() {
        IPersistentPreferenceStore prefs = Preferences.getPreferences();
        prefs.setDefault(CatalogUpdatePreferencesPage.CATALOG_SHOW_COMPATIBLE, true);
        prefs.setDefault(CatalogUpdatePreferencesPage.CATALOG_SHOW_LATEST, true);
        setPreferenceStore(prefs);
        this.currentModulePath = Paths.get(getPreferenceStore().getString(ModelioEnv.MODULE_PATH_PREFERENCE));
    }

    @objid ("d86fcd83-8195-4b5d-bb51-eaf5a3b1d8c6")
    @Override
    public void createFieldEditors() {
        this.showLatestField = new BooleanFieldEditor(CatalogUpdatePreferencesPage.CATALOG_SHOW_LATEST, AppModules.I18N.getString("ModuleCatalog.Preference.ShowOnlyLatest"), getFieldEditorParent());
        addField(this.showLatestField);
        
        this.showCompatibleField = new BooleanFieldEditor(CatalogUpdatePreferencesPage.CATALOG_SHOW_COMPATIBLE, AppModules.I18N.getString("ModuleCatalog.Preference.ShowOnlyCompatible"), getFieldEditorParent());
        addField(this.showCompatibleField);
        
        final Composite composite = getFieldEditorParent();
        
        this.localCatalogPathFieldEditor = new DirectoryFieldEditor(ModelioEnv.MODULE_PATH_PREFERENCE, AppModules.I18N.getString("ModuleCatalog.Preference.MoveCatalog.label"), composite);
        this.localCatalogPathFieldEditor.setChangeButtonText(AppModules.I18N.getString("ModuleCatalog.Preference.MoveCatalog.button"));
        final Text localCatalogPathText = this.localCatalogPathFieldEditor.getTextControl(composite);
        localCatalogPathText.setEditable(false);
        
        Label label = new Label(composite, SWT.NONE);
        label.setText(AppModules.I18N.getString("ModuleCatalog.Preference.MoveCatalog.description"));
        final GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
        label.setLayoutData(gd);
        
        // Add a modify listener to move the old catalog's contents to the new one
        localCatalogPathText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                onModifyLocalCatalogPath(Paths.get(((Text) e.widget).getText()));
            }
        });
        
        addField(this.localCatalogPathFieldEditor);
    }

    @objid ("19fa08d8-2016-4506-ae31-3b968694612b")
    @Override
    public boolean performCancel() {
        // Restore original catalog
        Path originalPath = Paths.get(getPreferenceStore().getString(ModelioEnv.MODULE_PATH_PREFERENCE));
        
        if (Files.exists(this.currentModulePath) && !this.currentModulePath.equals(originalPath)) {
            try {
                FileUtils.copyDirectoryTo(this.currentModulePath, originalPath);
                FileUtils.delete(this.currentModulePath);
            } catch (IOException e1) {
                // Error occured during copy, reset the Text's value
                AppModules.LOG.error(e1.getMessage());
            }
        
            this.currentModulePath = originalPath;
        }
        return super.performCancel();
    }

    @objid ("34945669-9941-41b1-96f6-104adde34e2b")
    private void onModifyLocalCatalogPath(Path proposedPath) {
        // Dumb case : no change
        if (this.currentModulePath.equals(proposedPath)) {
            return;
        }
        
        // Check validity of the new path:
        // - must exist
        // - be a directory
        // - cannot be the .modelio/version base directory itself
        // when invalid, restore the current value and return.
        
        if (!Files.isDirectory(proposedPath)) {
            this.localCatalogPathFieldEditor.setStringValue(this.currentModulePath.toString());
        
            MessageDialog.openInformation(this.getShell(),
                    AppModules.I18N.getString("ModuleCatalog.Preference.MoveCatalog.FailTitle"),
                    AppModules.I18N.getMessage("ModuleCatalog.Preference.MoveCatalog.PathMustExistMessage", proposedPath.toString()));
        
            return;
        }
        if (proposedPath.startsWith(this.currentModulePath)) {
            this.localCatalogPathFieldEditor.setStringValue(this.currentModulePath.toString());
        
            MessageDialog.openInformation(this.getShell(),
                    AppModules.I18N.getString("ModuleCatalog.Preference.MoveCatalog.FailTitle"),
                    AppModules.I18N.getMessage("ModuleCatalog.Preference.MoveCatalog.InvalidPathMessage", proposedPath.toString()));
        
            return;
        }
        try {
            if (Files.isSameFile(proposedPath, modelioEnv.getRuntimeDataPath())) {
                this.localCatalogPathFieldEditor.setStringValue(this.currentModulePath.toString());
                MessageDialog.openInformation(this.getShell(),
                        AppModules.I18N.getString("ModuleCatalog.Preference.MoveCatalog.FailTitle"),
                        AppModules.I18N.getMessage("ModuleCatalog.Preference.MoveCatalog.InvalidPathMessage", proposedPath.toString()));
                return;
            }
        } catch (IOException e) {
            MessageDialog.openError(this.getShell(), AppModules.I18N.getString("ModuleCatalog.Preference.MoveCatalog.FailTitle"),
                    AppModules.I18N.getMessage("ModuleCatalog.Preference.MoveCatalog.FailMessage", e.getMessage()));
            return;
        }
        
        // If a current catalog exists copy its contents in the new destination
        if (Files.exists(CatalogUpdatePreferencesPage.this.currentModulePath)) {
            try {
                FileUtils.copyDirectoryTo(CatalogUpdatePreferencesPage.this.currentModulePath, proposedPath);
        
                // Delete the old catalog
                try {
                    FileUtils.delete(CatalogUpdatePreferencesPage.this.currentModulePath);
                } catch (IOException e1) {
                    AppModules.LOG.error(e1.getMessage());
                }
        
                MessageDialog.openInformation(this.getShell(), AppModules.I18N.getString("ModuleCatalog.Preference.MoveCatalog.SuccessTitle"),
                        AppModules.I18N.getString("ModuleCatalog.Preference.MoveCatalog.SuccessMessage"));
        
                CatalogUpdatePreferencesPage.this.currentModulePath = proposedPath;
        
                return;
        
            } catch (IOException e1) {
                // Error occured during copy, reset the Text's value
                this.localCatalogPathFieldEditor.setStringValue(this.currentModulePath.toString());
                AppModules.LOG.error(e1.getMessage());
                MessageDialog.openError(this.getShell(), AppModules.I18N.getString("ModuleCatalog.Preference.MoveCatalog.FailTitle"),
                        AppModules.I18N.getMessage("ModuleCatalog.Preference.MoveCatalog.FailMessage", e1.getMessage()));
                return;
            }
        
        
        }
    }

}
