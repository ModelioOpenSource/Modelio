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

package org.modelio.app.project.conf.dialog.modules;

import java.io.IOException;
import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.project.conf.plugin.AppProjectConfExt;
import org.modelio.ui.dialog.ModelioDialog;
import org.osgi.framework.Bundle;

@objid ("27698d42-4b5b-4785-aaa5-ffa8d7eaafe3")
public class ModuleRemovalConfirmationDialog extends ModelioDialog {
    @objid ("83e31e85-8140-424c-b921-57b6387b8d0b")
    private static final int HEIGHT = 700;

    @objid ("de47a6e8-320e-4af5-9f20-67f0b1c47f62")
    private static final int WIDTH = 600;

    @objid ("b8fc0c21-1500-488c-b49a-bd0cb1b123dc")
    public ModuleRemovalConfirmationDialog(Shell parentShell) {
        super(parentShell);
    }

    @objid ("72d0e8a5-45b7-474f-a9a4-e359c0816ff4")
    @Override
    public Control createContentArea(Composite parent) {
        final Browser browser = new Browser(parent, SWT.BORDER);
        browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        browser.setMenu(new Menu(browser));
        browser.setJavascriptEnabled(false);
        final IPath helpUrl = new Path(AppProjectConfExt.I18N.getString("RemoveMdacsDlg.Confirm.Url"));
        final Bundle bundle = Platform.getBundle(AppProjectConfExt.PLUGIN_ID);
        try {
            final URL url = FileLocator.toFileURL(FileLocator.find(bundle, helpUrl, null));
            browser.setUrl(url.toString());
        } catch (final IOException e) {
            AppProjectConfExt.LOG.error(e);
        }
        return browser;
    }

    @objid ("05c764e3-de5c-41f4-b1ae-c2a6360f3ab2")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.YES_LABEL, false);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.NO_LABEL, true);
    }

    @objid ("36011b57-042e-4e7f-beca-da43363f3746")
    @Override
    public void init() {
        getShell().setText(AppProjectConfExt.I18N.getString("RemoveMdacsDlg.Confirm.Title"));
        setTitle(AppProjectConfExt.I18N.getString("RemoveMdacsDlg.Confirm.Title"));
        setMessage(AppProjectConfExt.I18N.getString("RemoveMdacsDlg.Confirm.Text"));
        
        getShell().setMinimumSize(WIDTH, HEIGHT);
        
        final Rectangle b = getShell().getParent().getBounds();
        getShell().setBounds(b.x + (b.width - WIDTH) / 2, b.y + (b.height - HEIGHT) / 2, WIDTH, HEIGHT);
    }

}
