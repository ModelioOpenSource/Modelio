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

package org.modelio.patterns.catalog.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.ui.ModelioDialog;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.platform.ui.panel.IPanelListener;

@objid ("c79f9e39-1616-4746-b676-b05fc024c92d")
public class PatternCatalogDialog extends ModelioDialog {
    @objid ("1212919c-1dfe-472a-a8f6-795d3942f819")
    private PatternCatalogPanel panel;

    @objid ("7d317228-5a9c-4b6a-be81-5b9e5c105ff7")
    @Override
    public void init() {
        setTitle(Patterns.I18N.getMessage("PatternCatalogDialog.title"));
        setMessage(Patterns.I18N.getMessage("PatternCatalogDialog.message"));
    }

    @objid ("33ededc0-d7ae-46f4-b2af-d9984fbb74a7")
    @Override
    public Control createContentArea(Composite parent) {
        return this.panel.createPanel(parent);
    }

    @objid ("740d84c7-0c08-446b-a9d8-993bd1e0a8d1")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        Button okButton = createButton(parent, IDialogConstants.OK_ID, Patterns.I18N.getMessage("PatternCatalogDialog.apply.button"), false);
        createButton(parent, IDialogConstants.CANCEL_ID, Patterns.I18N.getMessage("PatternCatalogDialog.close.button"), false);
        
        okButton.setEnabled(false);
    }

    @objid ("b3fa5ce1-cf56-46cd-9fa1-ee2eea00ea0c")
    @Override
    protected Point getInitialSize() {
        return new Point(600, 400);
    }

    @objid ("0100c9cd-6ac2-4020-a159-cb708efe4966")
    @Override
    protected String getHelpId() {
        return this.panel.getHelpTopic();
    }

    @objid ("1a67017c-bd91-4325-9a43-cea00bd3a6d2")
    public PatternCatalogDialog(Shell parentShell, PatternCatalogData data) {
        super(parentShell);
        this.setShellStyle(SWT.MODELESS | SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX);
        
        this.panel = new PatternCatalogPanel();
        this.panel.setInput(data);
        this.panel.addListener(new IPanelListener() {
            @Override
            public void dataChanged(Object o, boolean isValidate) {
                PatternCatalogData data = (PatternCatalogData) o;
                getButton(IDialogConstants.OK_ID).setEnabled(data.getSelectedPattern() != null);
            }
        });
    }

    @objid ("b11feccc-30cc-4f93-884a-c9049f1bd02a")
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Patterns.I18N.getMessage("PatternCatalogDialog.title"));
    }

}
