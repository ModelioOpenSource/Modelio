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

package org.modelio.xmi.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.xmi.api.FormatExport;
import org.modelio.xmi.api.XMIExtension;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.preferences.XmiPreferencesKeys;

/**
 * This class creates central composite of the XMI export dialogs (model and profile exports).
 * It is composed of several SWT composites, each of them is dedicated to a specific export option
 * i.e. the file path, the extension of the file, the UML version, etc.
 * 
 * This is a SWT composite
 * @author ebrosse
 */
@objid ("ab8ce695-54ca-4a64-a74f-1dde0fb28dc3")
public class OptionComposite extends Composite {
    @objid ("2c448d34-f105-481c-98b1-9e6142bae763")
    private FormatChooserComposite format = null;

    @objid ("3af6fefb-8223-4353-9598-8400952d11de")
    private Button compatibility = null;

    @objid ("96852c9b-9908-4e58-a59b-c5fa4100aa5a")
    private Group groupOwner = null;

    @objid ("8b72a6f9-7973-4842-88ac-322ac0da6c6b")
    private Group groupExtension = null;

    @objid ("09256899-20da-45b3-9c6d-9e32c264df6e")
    private Button xmi = null;

    @objid ("e9f99190-0dad-4073-b7c2-04645ce1a6f4")
    private Button uml = null;

    @objid ("32cc8da7-aeb7-46d9-babb-0e0e1a2ce470")
    private Group groupCompatibility = null;

    @objid ("608727ea-63a4-4c11-b307-9dd42d006faf")
    protected FileDialog dialog = null;

    /**
     * The constructor of the OptionComposite
     * 
     * @param parent : the parent composite
     * @param style : the SWT style of the returned composite
     */
    @objid ("ff77c766-2014-4379-8435-d87cfa32294e")
    public OptionComposite(final Shell parent, final int style, final int typeSelection, IProjectService projectService) {
        super(parent, style);       
        
        setLayout(new FormLayout());
        this.dialog = new FileDialog(parent, typeSelection);
        
        IPreferenceStore prefs = projectService.getProjectPreferences(Xmi.PLUGIN_ID);
        
        this.groupOwner = new Group(this, SWT.NONE);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.marginWidth = 20;
        gridLayout.horizontalSpacing = 20;
        this.groupOwner.setLayout(gridLayout);
        
        final FormData fd_group = new FormData();
        
        fd_group.bottom = new FormAttachment(100, -5);
        fd_group.top = new FormAttachment(0, 0);
        fd_group.right = new FormAttachment(100, 0);
        fd_group.left = new FormAttachment(0, 0);
        
        this.groupOwner.setLayoutData(fd_group);
        
        this.groupCompatibility = new Group(this.groupOwner, SWT.NONE);
        
        final RowLayout rl_groupCompatibility = new RowLayout(2);
        
        rl_groupCompatibility.justify = true;
        rl_groupCompatibility.fill = true;
        rl_groupCompatibility.marginLeft = 40;
        rl_groupCompatibility.marginRight = 80;
        final GridData fd_groupCompatibility = new GridData();
        fd_groupCompatibility.grabExcessHorizontalSpace = true;
        
        this.groupCompatibility.setLayoutData(fd_groupCompatibility);
        this.groupCompatibility.setLayout(rl_groupCompatibility);
        
        this.compatibility = new Button(this.groupCompatibility, SWT.CHECK);
        final RowData fd_compatibility = new RowData();
        fd_compatibility.height = 35;
        this.compatibility.setLayoutData(fd_compatibility);
        
               
        String compatibilityMdacParameterValueString = prefs.getString(XmiPreferencesKeys.XMIANNOTATION_PREFKEY);
        
        boolean compatibilityMdacParameterValue = Boolean.valueOf(compatibilityMdacParameterValueString);               
        
        this.compatibility.setSelection(compatibilityMdacParameterValue);
        this.format = new FormatChooserComposite(this.groupCompatibility, SWT.NONE, SWT.OPEN, projectService);
        
        final RowData fd_format = new RowData();
        fd_format.height = 35;
        this.format.setLayoutData(fd_format);
        
        this.groupExtension = new Group(this.groupOwner,  SWT.NONE);  
        
        final RowLayout fd_groupExtension = new RowLayout(2);
        fd_groupExtension.fill = true;
        fd_groupExtension.marginLeft = 40;
        fd_groupExtension.marginRight = 60;
        fd_groupExtension.justify = false;
        
        this.groupExtension.setLayout(fd_groupExtension);
        
        this.xmi = new Button(this.groupExtension, SWT.RADIO);
        
        final RowData fd_xmi = new RowData();
        fd_xmi.height = 35;
        this.xmi.setLayoutData(fd_xmi);
        
        this.xmi.setText(".xmi");
        
        this.uml = new Button(this.groupExtension, SWT.RADIO);
        final RowData fd_uml = new RowData();
        fd_uml.height = 35;
        this.uml.setLayoutData(fd_uml);
        this.uml.setText(".uml");
        
        String extension = prefs.getString(XmiPreferencesKeys.XMIEXTENSION_PREFKEY);
        if (extension.equals(XMIExtension.UML.toString())) {
            this.uml.setSelection(true);
        } else {
            this.xmi.setSelection(true);
        }
        
        this.groupOwner.setText(Xmi.I18N.getString("fileChooser.options.export.group.option.name"));
        this.groupCompatibility.setText(Xmi.I18N.getString("fileChooser.options.export.group.compatibility.name"));
        this.compatibility.setText(Xmi.I18N.getString("fileChooser.options.export.enableRoundtrip.name"));
        this.groupExtension.setText(Xmi.I18N.getString("fileChooser.options.export.group.extension.name"));
        
        this.uml.setToolTipText(Xmi.I18N.getString("fileChooser.options.export.description.uml"));      
        this.groupCompatibility.setToolTipText(Xmi.I18N.getString("fileChooser.options.export.description.annotation"));       
        this.xmi.setToolTipText(Xmi.I18N.getString("fileChooser.options.export.description.xmi"));
        
        this.groupOwner.pack();
    }

    /**
     * This method returns the UML radio button
     * 
     * @return SWT Button
     */
    @objid ("a2ca9326-6b1e-49c4-9183-51452e8bb1a4")
    public Button getUMLButton() {
        return this.uml;
    }

    /**
     * This method returns the XMI radio button
     * 
     * @return SWT Button
     */
    @objid ("382784ac-738f-4456-a08d-453fa58ff4d0")
    public Button getXMIButton() {
        return this.xmi;
    }

    /**
     * This method returns the specified file extension
     * 
     * @return the file extension .xmi or .uml
     */
    @objid ("010bcdad-d248-42eb-a9b0-5b4bbd1f9d4a")
    public XMIExtension getExtension() {
        if (this.uml.getSelection())
            return XMIExtension.UML;
        else
            return XMIExtension.XMI;
    }

    /**
     * This method returns the compatibility checkbox
     * 
     * @return SWT CheckBox
     */
    @objid ("2e28c92d-1bbe-45b5-9779-64a430585274")
    public Button getCompatibilityButton() {
        return this.compatibility;
    }

    /**
     * This methods updates the selection of .uml file extension
     */
    @objid ("14003bcc-0fad-45a9-9c1c-2781d5f03159")
    public void setUMLSelected() {
        this.uml.setSelection(true);
        this.xmi.setSelection(false);
    }

    /**
     * This methods updates the selection of .xmi file extension
     */
    @objid ("7c8c9a4d-d140-44e1-81ec-dbbb4466a20e")
    public void setXMISelected() {
        this.xmi.setSelection(true);
        this.uml.setSelection(false);
    }

    /**
     * This method returns the availability of the annotation export
     * 
     * @return true if the annotation must be exported
     */
    @objid ("f6b952fe-1cfc-464b-8655-7d0b7462c2a6")
    public boolean isAnnoted() {
        return this.compatibility.getSelection();
    }

    /**
     * This method returns the SWT FileDialog created inside the FileChooserComposite
     * 
     * @return the owned FileDialog
     */
    @objid ("9aec9821-32f0-4b9d-8bbf-ba02a6e9d549")
    public FileDialog getDialog() {
        return this.dialog;
    }

    /**
     * This method returns the format selected
     * 
     * @return FormatExport
     */
    @objid ("53763a56-229f-414e-a035-20263beb1e2c")
    public FormatExport getFormat() {
        return this.format.getFormat();
    }

}
