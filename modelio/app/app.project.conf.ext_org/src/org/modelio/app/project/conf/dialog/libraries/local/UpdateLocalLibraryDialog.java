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

package org.modelio.app.project.conf.dialog.libraries.local;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.app.update.checker.dialog.UpdatePanel;
import org.modelio.app.update.checker.dialog.UpdatePanelDataModel;
import org.modelio.ui.dialog.ModelioDialog;

@objid ("6ba6153d-d20d-4710-806b-4ac07b39d44d")
class UpdateLocalLibraryDialog extends ModelioDialog {
    @objid ("53e82b89-f897-4e89-a686-ebedfeb05529")
    protected UpdatePanelDataModel model;

    @objid ("fe4c1f0c-e4e9-49fe-b9c5-e8775af520c2")
    private UpdatePanel panel;

    @objid ("3355d1db-0332-43b2-9612-b92bbc40e0d7")
    public UpdateLocalLibraryDialog(final Shell parentShell, final UpdatePanelDataModel model) {
        super(parentShell);
        this.model = model;
    }

    @objid ("2bb1dc2d-3fa5-4ef7-8931-71ec921beb0e")
    @Override
    public Control createContentArea(final Composite parent) {
        this.panel = new UpdatePanel();
        final Control compo = this.panel.createPanel(parent);
        
        final GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        layoutData.minimumHeight = 150;
        compo.setLayoutData(layoutData);
        
        this.panel.setInput(this.model);
        return compo;
    }

    @objid ("ae8037df-d99c-4f2d-b2ef-c81bccc11d84")
    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        createButton(parent, OK, AppProjectConfExt.I18N.getString("UpdateLocalLibraryDialog.Update"), true);
        createButton(parent, CANCEL, AppProjectConfExt.I18N.getString("UpdateLocalLibraryDialog.Close"), true);
    }

    @objid ("8bd48fe2-c5fa-4494-9104-a8036e1a35f1")
    @Override
    public void init() {
        getShell().setText(AppProjectConfExt.I18N.getString("UpdateLocalLibraryDialog.ShellTitle")); //$NON-NLS-1$ );
        setTitle(AppProjectConfExt.I18N.getString("UpdateLocalLibraryDialog.Title")); //$NON-NLS-1$
        
        setMessage(AppProjectConfExt.I18N.getMessage("UpdateLocalLibraryDialog.Message")); //$NON-NLS-1$
        
        // Position and resize dialog shell
        final int width = 1100;
        final int height = 800;
        
        final Rectangle refBounds = getShell().getParent().getBounds();
        getShell().setMinimumSize(width, height);
        getShell().layout(true);
        
        getShell().setBounds(refBounds.x + (refBounds.width - width) / 2, refBounds.y + (refBounds.height - height) / 2, width, height);
    }

    @objid ("9d5b0e4d-ac9f-4315-9d32-b63432d83964")
    @Override
    public boolean close() {
        if (this.panel != null) {
            this.panel.dispose();
            this.panel = null;
        }
        return super.close();
    }

}
