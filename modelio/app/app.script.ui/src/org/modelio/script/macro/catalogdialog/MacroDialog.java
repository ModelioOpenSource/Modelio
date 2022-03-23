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
package org.modelio.script.macro.catalogdialog;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.script.macro.catalog.Catalog;
import org.modelio.script.macro.catalog.Macro;
import org.modelio.script.plugin.Script;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.osgi.framework.Bundle;

/**
 * Macro catalog edition dialog box.
 */
@objid ("0069639c-c497-106a-bf4f-001ec947cd2a")
public class MacroDialog extends ModelioDialog {
    @objid ("00699132-c497-106a-bf4f-001ec947cd2a")
    private Button iconPathButton;

    @objid ("00699376-c497-106a-bf4f-001ec947cd2a")
    private Button scriptPathButton;

    @objid ("0069948e-c497-106a-bf4f-001ec947cd2a")
    private Button showInMenuToggle;

    @objid ("00699254-c497-106a-bf4f-001ec947cd2a")
    private Button showInToolbarToggle;

    @objid ("0037be78-f1bd-106a-bf4f-001ec947cd2a")
    protected Table metaclassList;

    @objid ("000369e8-2079-106b-bf4f-001ec947cd2a")
    private Macro editedMacro = null;

    @objid ("00037b86-2079-106b-bf4f-001ec947cd2a")
    private Text iconPathText;

    @objid ("0003839c-2079-106b-bf4f-001ec947cd2a")
    private Text scriptDescriptionText;

    @objid ("00038bda-2079-106b-bf4f-001ec947cd2a")
    private Text scriptNameText;

    @objid ("000399e0-2079-106b-bf4f-001ec947cd2a")
    private Text scriptPathText;

    @objid ("0003b7cc-2079-106b-bf4f-001ec947cd2a")
    protected final Catalog catalog;

    /**
     * Create a macro edition dialog for an existing macro
     */
    @objid ("00697800-c497-106a-bf4f-001ec947cd2a")
    public  MacroDialog(Shell parentShell, Macro macroToEdit) {
        super(parentShell);
        this.editedMacro = macroToEdit;
        this.catalog = macroToEdit.getCatalog();
        
    }

    /**
     * Create a macro edition dialog for a new macro
     */
    @objid ("00697896-c497-106a-bf4f-001ec947cd2a")
    public  MacroDialog(Shell parentShell, Catalog catalog) {
        super(parentShell);
        if (catalog == null) {
            throw new NullPointerException("catalog cannot be null.");
        }
        
        this.catalog = catalog;
        this.editedMacro = null;
        
    }

    @objid ("0069a460-c497-106a-bf4f-001ec947cd2a")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        addDefaultButtons(parent);
    }

    @objid ("0069a3ca-c497-106a-bf4f-001ec947cd2a")
    @Override
    public Control createContentArea(Composite parent) {
        Composite scriptPanel = new Composite(parent, SWT.NONE);
        scriptPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        // Name Text:
        final Label scriptNameLabel = new Label(scriptPanel, SWT.NONE);
        scriptNameLabel.setText(Script.I18N.getString("MacroDialog.name.label"));
        this.scriptNameText = new Text(scriptPanel, SWT.SINGLE | SWT.BORDER);
        
        // Path Text:
        final Label scriptPathLabel = new Label(scriptPanel, SWT.NONE);
        scriptPathLabel.setText(Script.I18N.getString("MacroDialog.path.label"));
        
        final Composite scriptPathGroup = new Composite(scriptPanel, SWT.NONE);
        final GridLayout scriptLayout = new GridLayout(2, false);
        scriptLayout.marginLeft = -5;
        scriptPathGroup.setLayout(scriptLayout);
        this.scriptPathText = new Text(scriptPathGroup, SWT.SINGLE | SWT.BORDER);
        final GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, false);
        this.scriptPathText.setLayoutData(layoutData);
        this.scriptPathButton = FileChooserButtonFactory.create(
                scriptPathGroup,
                this.scriptPathText,
                "", // default path
                new String[] { Script.I18N.getString("CatalogDialog.PyFilterNames"),
                        Script.I18N.getString("CatalogDialog.JyFilterNames") },
                new String[] { "*.py", "*.jy" });
        this.scriptPathButton.setImage(UIImages.FILECHOOSE);
        scriptPathGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        // Icon Path Text:
        final Label iconPathLabel = new Label(scriptPanel, SWT.NONE);
        iconPathLabel.setText(Script.I18N.getString("MacroDialog.icon.label"));
        
        final Composite iconPathGroup = new Composite(scriptPanel, SWT.NONE);
        final GridLayout iconLayout = new GridLayout(2, false);
        iconLayout.marginLeft = -5;
        iconPathGroup.setLayout(iconLayout);
        this.iconPathText = new Text(iconPathGroup, SWT.SINGLE | SWT.BORDER);
        this.iconPathText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        this.iconPathButton = FileChooserButtonFactory.create(iconPathGroup, this.iconPathText,
                "", // default path
                new String[] { Script.I18N.getString("MacroDialog.IconPathFilterNames") },
                new String[] { Script.I18N.getString("MacroDialog.IconPathFilterExtensions") });
        this.iconPathButton.setImage(UIImages.FILECHOOSE);
        
        // Description Text:
        final Label scriptDescLabel = new Label(scriptPanel, SWT.NONE);
        scriptDescLabel.setText(Script.I18N.getString("MacroDialog.description.label"));
        
        this.scriptDescriptionText = new Text(scriptPanel, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
        
        // Create "Applicable on" list:
        final Label ApplicableOnLabel = new Label(scriptPanel, SWT.NONE);
        ApplicableOnLabel.setText(Script.I18N.getString("MacroDialog.applicable.label"));
        
        this.metaclassList = new Table(scriptPanel, SWT.BORDER);
        
        this.metaclassList.addMouseListener(new ApplicableOnListener());
        this.metaclassList.setToolTipText(Script.I18N.getString("MacroDialog.applicable.tooltip"));
        
        // Path Text:
        this.showInMenuToggle = new Button(scriptPanel, SWT.CHECK);
        this.showInMenuToggle.setText(Script.I18N.getString("MacroDialog.inmenu.label"));
        
        this.showInToolbarToggle = new Button(scriptPanel, SWT.CHECK);
        this.showInToolbarToggle.setText(Script.I18N.getString("MacroDialog.intoolbar.label"));
        
        updateFields(this.editedMacro);
        
        GridLayoutFactory.swtDefaults().generateLayout(scriptPanel);
        return scriptPanel;
    }

    /**
     * Get the edited macro.
     * @return the edited macro.
     */
    @objid ("006a05cc-c497-106a-bf4f-001ec947cd2a")
    public Macro getMacro() {
        return this.editedMacro;
    }

    @objid ("006a0662-c497-106a-bf4f-001ec947cd2a")
    @Override
    public void init() {
        setTitle(Script.I18N.getString("MacroDialog.title"));
        setMessage(Script.I18N.getString("MacroDialog.description"));
        
    }

    @objid ("006a07a2-c497-106a-bf4f-001ec947cd2a")
    @Override
    protected void okPressed() {
        if (validateScriptPanel()) {
            if (this.editedMacro == null) { // Create a new macro
                this.editedMacro = new Macro(this.catalog);
                updateMacroFromFields();
                this.catalog.addMacro(this.editedMacro);
            } else { // Edit a macro
                updateMacroFromFields();
            }
            super.okPressed();
        }
        
    }

    /**
     * Add a metaclass to the applicable metaclass list table.
     * @param s a metaclass name
     */
    @objid ("006a0842-c497-106a-bf4f-001ec947cd2a")
    void addMetaclass(String s) {
        TableItem i = new TableItem(this.metaclassList, 0);
        i.setText(s);
        i.setImage(MetamodelImageService.getIcon(this.catalog.getMetamodel().getMClass(s)));
        
    }

    /**
     * Check the path is an existing a readable file path.
     * @param pathString The path to check
     */
    @objid ("0069a5c8-c497-106a-bf4f-001ec947cd2a")
    private boolean checkPath(String pathString, boolean emptyAccepted) {
        if (pathString.isEmpty()) {
            return emptyAccepted;
        }
        
        Path scriptPath = Paths.get(pathString);
        if (!Files.exists(scriptPath) || !Files.isRegularFile(scriptPath)) {
            return false;
        }
        return true;
    }

    @objid ("006a08ec-c497-106a-bf4f-001ec947cd2a")
    Collection<String> getApplicableMetaclasses() {
        Collection<String> ret = new ArrayList<>(this.metaclassList.getItemCount());
        for (TableItem i : this.metaclassList.getItems()) {
            ret.add(i.getText());
        }
        return ret;
    }

    @objid ("006a0996-c497-106a-bf4f-001ec947cd2a")
    private void updateFields(Macro script) {
        if (script != null) {
        
            this.scriptNameText.setText(script.getName());
            this.scriptDescriptionText.setText(script.getDescription());
            final Path path = script.getScriptPath();
            if (path != null) {
                this.scriptPathText.setText(path.toString());
            } else {
                this.scriptPathText.setText("");
            }
        
            final Path iconPath = script.getIconPath();
            if (iconPath != null) {
                this.iconPathText.setText(iconPath.toString());
            } else {
                this.iconPathText.setText("");
            }
        
            this.metaclassList.removeAll();
            for (String s : script.getMetaclasses()) {
                addMetaclass(s);
            }
        
            this.showInMenuToggle.setSelection(script.shownInContextualMenu());
            this.showInToolbarToggle.setSelection(script.shownInToolbar());
        
            final boolean modifiable = script.getCatalog().isModifiable();
        
            this.scriptNameText.setEnabled(modifiable);
            this.scriptDescriptionText.setEnabled(modifiable);
            this.scriptPathText.setEnabled(modifiable);
            this.scriptPathButton.setEnabled(modifiable);
            this.iconPathText.setEnabled(modifiable);
            this.iconPathButton.setEnabled(modifiable);
            this.metaclassList.setEnabled(modifiable);
            this.showInMenuToggle.setEnabled(modifiable);
            this.showInToolbarToggle.setEnabled(modifiable);
        } else {
            // scriptPanel.setVisible(false);
        
            this.scriptNameText.setText("");
            this.scriptDescriptionText.setText("");
            this.scriptPathText.setText("");
            this.iconPathText.setText("");
            this.metaclassList.removeAll();
            this.showInMenuToggle.setSelection(false);
            this.showInToolbarToggle.setSelection(false);
        
            this.scriptNameText.setEnabled(true);
            this.scriptDescriptionText.setEnabled(true);
            this.scriptPathText.setEnabled(true);
            this.scriptPathButton.setEnabled(true);
            this.iconPathText.setEnabled(true);
            this.iconPathButton.setEnabled(true);
            this.metaclassList.setEnabled(true);
            this.showInMenuToggle.setEnabled(true);
            this.showInToolbarToggle.setEnabled(true);
        }
        this.editedMacro = script;
        
    }

    @objid ("006a0b80-c497-106a-bf4f-001ec947cd2a")
    private boolean validateScriptPanel() {
        // Name must be non empty
        if (this.scriptNameText.getText().isEmpty()) {
            return false;
        }
        
        // Script path must be an existing readable regular file
        if (!checkPath(this.scriptPathText.getText(), false)) {
            MessageDialog.openError(getShell(), Script.I18N.getString("MacroDialog.error.title"),
                    Script.I18N.getMessage("MacroDialog.error.invalidscriptpath", this.scriptPathText.getText()));
            return false;
        }
        
        // Icon path is optional, otherwise it has to be an existing readable
        // regular file
        if (!checkPath(this.iconPathText.getText(), true)) {
            MessageDialog.openError(getShell(), Script.I18N.getString("MacroDialog.error.title"),
                    Script.I18N.getMessage("MacroDialog.error.invalidiconpath", this.iconPathText.getText()));
            return false;
        }
        return true;
    }

    /**
     * Read the field values into the edited macro
     */
    @objid ("000568c4-2079-106b-bf4f-001ec947cd2a")
    private void updateMacroFromFields() {
        // Save fields data
        this.editedMacro.setName(this.scriptNameText.getText());
        this.editedMacro.setScriptPath(Paths.get(this.scriptPathText.getText()));
        this.editedMacro.setIconPath(this.iconPathText.getText().isEmpty() ? null : Paths.get(this.iconPathText.getText()));
        this.editedMacro.setDescription(this.scriptDescriptionText.getText().replace("\r\n", "\n"));
        this.editedMacro.setShowInContextualMenu(this.showInMenuToggle.getSelection());
        this.editedMacro.setShowInToolbar(this.showInToolbarToggle.getSelection());
        this.editedMacro.setMetaclasses(getApplicableMetaclasses());
        
    }

    @objid ("6a04233f-e2dd-4587-9070-fb1151cf4c45")
    private String getFilePathOf(String fileName) {
        String path = "";
        Bundle bundle = Platform.getBundle(Script.PLUGIN_ID);
        String s = "platform:/plugin/" + bundle.getSymbolicName() + "/" + fileName; // To avoid the space in the bundle path
        URL url = null;
        try {
            url = new URL(s);
            path = FileLocator.toFileURL(url).getPath();
        } catch (Exception e) {
            Script.LOG.debug("File path %s is not found!", s);
            Script.LOG.error(e);
        }
        return path;
    }

    @objid ("ead31fc1-e27d-4ac3-8377-7571dc53a3bc")
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setMinimumSize(600, 400);
        newShell.setText(Script.I18N.getString("MacroDialog.title"));
        
    }

    @objid ("00696428-c497-106a-bf4f-001ec947cd2a")
    @SuppressWarnings ("serial")
    private static class IllegalPathException extends Exception {
        @objid ("00697936-c497-106a-bf4f-001ec947cd2a")
        public  IllegalPathException(String message) {
            super(message);
        }

    }

    /**
     * Listener that opens the metaclass chooser dialog.
     */
    @objid ("006964be-c497-106a-bf4f-001ec947cd2a")
    private class ApplicableOnListener implements MouseListener {
        @objid ("006a1c06-c497-106a-bf4f-001ec947cd2a")
        @Override
        public void mouseDoubleClick(MouseEvent e) {
            // Nothing to do.
        }

        @objid ("006a1c9c-c497-106a-bf4f-001ec947cd2a")
        @Override
        public void mouseDown(MouseEvent e) {
            // Nothing to do.
        }

        @objid ("006a1d32-c497-106a-bf4f-001ec947cd2a")
        @Override
        public void mouseUp(MouseEvent e) {
            final Collection<String> initValues = getApplicableMetaclasses();
            final MetaclassChooserDialog dlg = new MetaclassChooserDialog(getShell(), initValues,
                    MacroDialog.this.catalog.getMetamodel());
            
            if (dlg.open() == IDialogConstants.OK_ID) {
                MacroDialog.this.metaclassList.removeAll();
                for (MClass s : dlg.getSelection()) {
                    addMetaclass(s.getName());
                }
            }
            
        }

        @objid ("9b0b78cb-ec0d-445e-b3e8-301268413ef5")
        public  ApplicableOnListener() {
            super();
        }

    }

}
