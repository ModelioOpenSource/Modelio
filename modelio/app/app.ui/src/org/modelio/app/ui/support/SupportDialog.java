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
package org.modelio.app.ui.support;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.platform.ui.dialog.ModelioDialog2;

@objid ("18604248-92f0-4636-ad92-a7a2ee781136")
public class SupportDialog extends ModelioDialog2 {
    @objid ("f2c3bcb9-fc55-42db-b971-184727051086")
    private Image aboutImage = null;

    @objid ("7fded2cc-9b5d-4343-ae51-476db4ed4b2a")
    protected  SupportDialog(Shell parentShell) {
        super(parentShell);
    }

    @objid ("a7f0e6b8-bf22-4e30-8c2e-fb4bd1f66287")
    @Override
    protected void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    }

    @objid ("852e8839-3509-4a0e-9922-4421666441d7")
    @Override
    protected Point getInitialSize() {
        return new Point(800, 420);
    }

    @objid ("603ea22c-a7cf-402b-bb74-299e5281b330")
    @Override
    protected boolean isResizable() {
        return false;
    }

    @objid ("4e8b0f3a-db18-40b9-9f6a-ac66b7a2cf78")
    @Override
    protected Control createContentArea(Composite parent) {
        this.getShell().setText(AppUi.I18N.getString("SupportDialog.title"));
        final Composite workArea = new Composite(parent, SWT.BORDER);
        final GridLayout workLayout = new GridLayout();
        workLayout.marginHeight = 0;
        workLayout.marginWidth = 0;
        workLayout.verticalSpacing = 0;
        workLayout.horizontalSpacing = 0;
        workLayout.numColumns = 2;
        
        workArea.setLayout(workLayout);
        workArea.setLayoutData(new GridData(GridData.FILL_BOTH));
        workArea.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        
        final URL url = FileLocator.find(Platform.getBundle(AppUi.PLUGIN_ID), new Path("images/about250x330.png"), null);
        final ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url);
        this.aboutImage = imageDescriptor.createImage();
        
        final Label imageLabel = new Label(workArea, SWT.NONE);
        imageLabel.setText("First Name");
        imageLabel.setImage(this.aboutImage);
        final GridData imgData = new GridData();
        imgData.horizontalAlignment = GridData.FILL;
        imgData.verticalAlignment = GridData.BEGINNING;
        imgData.grabExcessHorizontalSpace = false;
        
        imageLabel.setLayoutData(imgData);
        Browser contentBrowser = new Browser(workArea, SWT.NO_SCROLL);
        final GridData textData = new GridData();
        textData.horizontalAlignment = GridData.FILL;
        textData.verticalAlignment = GridData.FILL;
        textData.grabExcessHorizontalSpace = true;
        textData.grabExcessVerticalSpace = true;
        textData.widthHint = 400;
        textData.heightHint = 200;
        textData.horizontalIndent = 6;
        textData.verticalIndent = 4;
        contentBrowser.setLayoutData(textData);
        contentBrowser.setText(formatHtmlMessage());
        return workArea;
    }

    @objid ("ee1d9d3e-c29f-4ff0-8f37-f818b2ae2a71")
    private String formatHtmlMessage() {
        StringBuffer content = new StringBuffer();
        content.append("<body scroll=\"auto\" style=\"overflow-y: hidden;\">");
        content.append("<div style=\"margin : 10px;\">");
        content.append("<h3 style=\"color:#7283af\"><b>" + AppUi.I18N.getString("SupportDialog.technical.title") +"</b></h3>");
        String mail =  AppUi.I18N.getString("SupportDialog.technical.email");
        content.append("<p>" + AppUi.I18N.getString("SupportDialog.technical.content") + "<a href=\"mailto:" +  mail+ "\">"+mail+"</a>" +"</p>");
        content.append("</div>");
        
        content.append("<div style=\"margin : 10px;\">");
        content.append("<h3 style=\"color:#7283af\"><b>" + AppUi.I18N.getString("SupportDialog.commercial.title") +"</b></h3>");
        mail =  AppUi.I18N.getString("SupportDialog.commercial.email");
        content.append("<p>" + AppUi.I18N.getString("SupportDialog.commercial.content")+  "<a href=\"mailto:" + mail + "\">"+mail+"</a>" +"</p>");
        content.append("</div>");
        content.append(" </body>");
        return content.toString();
    }

    @objid ("0aa23f6a-f808-42e4-a936-477987d707b5")
    @Override
    protected void init() {
        // TODO Auto-generated method stub
    }

}
