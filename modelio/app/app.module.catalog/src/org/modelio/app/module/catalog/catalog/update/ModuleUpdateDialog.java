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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.module.catalog.plugin.AppModules;
import org.modelio.app.update.checker.dialog.UpdatePanel;
import org.modelio.app.update.checker.dialog.UpdatePanelDataModel;
import org.modelio.platform.ui.dialog.ModelioDialog;

@objid ("3a97523c-6868-498c-baa8-a65e45c982ab")
class ModuleUpdateDialog extends ModelioDialog {
    @objid ("8b755504-3c19-4493-92a1-351a02cc7773")
    protected UpdatePanelDataModel model;

    @objid ("33399e13-413a-4907-ab21-bf682e23c5e9")
    private UpdatePanel panel;

    @objid ("cc78bdff-9021-4b14-aa0a-f2826efbd913")
    public  ModuleUpdateDialog(Shell parentShell, UpdatePanelDataModel model) {
        super(parentShell);
        this.model = model;
        
    }

    @objid ("67e6c5f5-995f-4cb8-9b21-0337980af059")
    @Override
    public Control createContentArea(Composite parent) {
        this.panel = new UpdatePanel();
        Control compo = this.panel.createPanel(parent);
        
        final GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        layoutData.minimumHeight = 150;
        compo.setLayoutData(layoutData);
        
        this.panel.setInput(this.model);
        return compo;
    }

    @objid ("7139fad5-4f56-4893-9d30-bb1e740d212f")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, OK, AppModules.I18N.getString("ModuleUpdateDialog.Update"), true);
        createButton(parent, CANCEL, AppModules.I18N.getString("ModuleUpdateDialog.Close"), true);
        
    }

    @objid ("4a98a9b5-8949-4c2e-8a42-63a1dfa29c29")
    @Override
    public void init() {
        getShell().setText(AppModules.I18N.getString("ModuleUpdateDialog.ShellTitle")); //$NON-NLS-1$ );
        setTitle(AppModules.I18N.getString("ModuleUpdateDialog.Title")); //$NON-NLS-1$
        
        setMessage(AppModules.I18N.getMessage("ModuleUpdateDialog.Message")); //$NON-NLS-1$
        
        // Position and resize dialog shell
        final int width = 1100;
        final int height = 800;
        
        final Rectangle refBounds = getShell().getParent().getBounds();
        getShell().setMinimumSize(width, height);
        getShell().layout(true);
        
        getShell().setBounds(refBounds.x + ((refBounds.width - width) / 2), refBounds.y + ((refBounds.height - height) / 2), width, height);
        
    }

    @objid ("0644d8db-b0f6-40a2-93f6-19f5ff0dcf7a")
    @Override
    public boolean close() {
        if (this.panel != null) {
            this.panel.dispose();
            this.panel = null;
        }
        return super.close();
    }

}
