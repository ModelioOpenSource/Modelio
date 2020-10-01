/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.ui.contributor;

import java.net.MalformedURLException;
import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.plugin.Api;
import org.modelio.api.ui.ModelioDialog;

/**
 * This class implements the dialog box that display a HTML documentation file.
 */
@objid ("30ea8c1f-6d1b-4826-8ec2-f9777dd804d1")
class BrowserDialog extends ModelioDialog {
    @objid ("91b9c0ce-5cc2-49e0-b03c-27d0ec3ec9e1")
    protected String docUrl;

    @objid ("5b471705-34f2-4ae2-a44c-db264dbcee72")
    protected Browser browser;

    /**
     * Create an BrowserDialog instance.
     * 
     * @param parentShell The parent shell.
     * @param docUrl The documentation url to display.
     */
    @objid ("d3bc5d6b-8c27-4e91-ae6c-6e48b1c835a8")
    public BrowserDialog(final Shell parentShell, final String docUrl) {
        super(parentShell);
        this.docUrl = docUrl;
    }

    /**
     * Add buttons to the buttons bar in the bottom of the dialog.
     * <p>
     * Here we just need to have a "close" button.
     * 
     * @param parent the parent composite of the dialog.
     */
    @objid ("6d91fbf4-00cd-4f7a-936e-6ca15283cc78")
    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    }

    /**
     * This is the main method that is called to construct the GUI content of the box.
     * 
     * @param parent the parent composite of the dialog.
     */
    @objid ("d9d926fa-58bf-4b88-896e-e6bec32332ce")
    @Override
    public Control createContentArea(final Composite parent) {
        this.browser = new Browser(parent, SWT.BORDER);
        this.browser.setLayoutData(new GridData(GridData.FILL_BOTH));
        this.browser.setMenu(new Menu(this.browser));
        this.browser.setJavascriptEnabled(false);
        
        this.browser.addLocationListener(new LocationAdapter() {
            @Override
            public void changing(LocationEvent event)
            {
        
                String newLocation = event.location;
                try {
                    URL url = new URL(newLocation);
                    if (url.getQuery() != null) {
                        BrowserDialog.this.browser.setUrl(BrowserDialog.this.docUrl);
                    }
                } catch (MalformedURLException e) {
                    Api.LOG.error(e);
                }
        
            }
        });
        
        this.browser.setUrl(this.docUrl);
        return this.browser;
    }

    /**
     * Init is called when the dialog box is opened.
     */
    @objid ("82264484-b5f8-44aa-bff6-10bd5f9204bd")
    @Override
    public void init() {
        setLogoImage(null);
        // Put the messages in the banner area
        getShell().setText(Api.I18N.getString("BrowserDialog.DialogTitle"));
        setTitle(Api.I18N.getString("BrowserDialog.DialogTitle"));
        //setMessage(Messages.getString("BrowserDialog.DialogMessage"));
        getShell().setSize(600, 550);
        getShell().setMinimumSize(600, 550);
    }

}
