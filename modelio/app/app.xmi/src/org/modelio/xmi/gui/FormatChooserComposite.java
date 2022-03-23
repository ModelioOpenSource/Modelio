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
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.xmi.api.FormatExport;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.preferences.XmiPreferencesKeys;

/**
 * This class defines the format chooser composite.
 * This composite is composed of
 * - a combo field in order to specify the desired format
 * - a label
 * 
 * It is a SWT composite
 * @author ebrosse
 */
@objid ("750ee385-f363-475c-be2e-1865a06062f9")
public class FormatChooserComposite extends Composite {
    @objid ("90ac9cb6-b887-4af5-ab6f-f3e972382f22")
    private FormatExport[] formats = FormatExport.values();

    @objid ("4e262123-292b-42f7-823b-a8fc57160e48")
    private Label label = null;

    @objid ("8b6e3ff2-f898-470b-bd7b-32008e651d27")
    private Combo combo = null;

    /**
     * This method sets the label of the composite
     * @param label : the label of the composite
     */
    @objid ("90ef5b0e-b16b-4df6-a6ac-9bb3726c7dd4")
    public void setText(final String label) {
        if (label != null)
            this.label.setText(label);
        
    }

    /**
     * Constructor of the FileChooserComposite.
     * It needs :
     * - the parent composite
     * - its SWT style
     * - the selection type of the SWT FileDialog
     * @param parent : the SWT composite owner
     * @param style : the SWT style
     * @param typeSelection : the SWT selection type
     */
    @objid ("414c1b40-011e-447b-b27a-674f42c13ad4")
    public  FormatChooserComposite(final Composite parent, final int style, final int typeSelection, IProjectService projectService) {
        super(parent, style);
        
        setLayout(new FormLayout());
        
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        this.setLayout(gridLayout);
        
        this.label = new Label(this, SWT.WRAP);
        this.combo = new Combo(this, SWT.READ_ONLY); 
        final GridData gridData = new GridData();
        gridData.widthHint = 130;
        this.combo.setLayoutData(gridData);
        
        
        IPreferenceStore prefs = projectService.getProjectPreferences(Xmi.PLUGIN_ID);
        String currentFormat = prefs.getString(XmiPreferencesKeys.XMIFORMAT_PREFKEY);
        
        if( currentFormat.equals(""))
            currentFormat = FormatExport.EMF300.toString();
        
        for (int i = 0; i < this.formats.length; i++) {
            this.combo.add(Xmi.I18N.getString("$ui.parameter.versionExport." + this.formats[i].name()));
        
            if (this.formats[i].name().equals(currentFormat))
                this.combo.select(i);
        }
        
        this.label.setText(Xmi.I18N.getString("fileChooser.options.export.version.name") + " : ");
        this.combo.setToolTipText(Xmi.I18N.getString("fileChooser.options.export.description.compatibility"));      
        this.label.setToolTipText(Xmi.I18N.getString("fileChooser.options.export.description.compatibility"));
        
    }

    /**
     * This method returns the UML version specified
     * @return a UML version of the export
     */
    @objid ("38e50c36-c787-4821-9028-45b52e0ca113")
    public FormatExport getFormat() {
        return this.formats[this.combo.getSelectionIndex()];
    }

}
