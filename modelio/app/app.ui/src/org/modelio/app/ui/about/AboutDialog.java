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

package org.modelio.app.ui.about;

import java.net.URL;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.modelio.app.core.ModelioEnv;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.version.ModelioVersion;

@objid ("00449c9c-cc35-1ff2-a7f4-001ec947cd2a")
public class AboutDialog extends TrayDialog {
    @objid ("0047064e-cc35-1ff2-a7f4-001ec947cd2a")
    private Image aboutImage = null;

    @objid ("0066a5a8-d87d-1040-a120-001ec947cd2a")
    private final ModelioEnv modelioEnv;

    @objid ("18ffe803-6087-4633-8eda-b6d4fa64f431")
    private BundledMessages aboutI18N;

    /**
     * Creates the dialog.
     * @param modelioEnv
     * 
     * @param parentShell the parent window shell.
     */
    @objid ("0047078e-cc35-1ff2-a7f4-001ec947cd2a")
    public AboutDialog(final Shell parentShell, ModelioEnv modelioEnv) {
        super(parentShell);
        this.modelioEnv = modelioEnv;
        this.aboutI18N = new BundledMessages(AppUi.LOG, ResourceBundle.getBundle("appui-about"));
    }

    @objid ("0047082e-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(this.aboutI18N.getString("About.Title"));
    }

    @objid ("00498e78-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    protected Control createContents(final Composite parent) {
        final Control contents = super.createContents(parent);
        return contents;
    }

    @objid ("00498f22-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    protected Control createDialogArea(final Composite parent) {
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
        
        final StyledText aboutText = new StyledText(workArea, SWT.MULTI | SWT.READ_ONLY | SWT.WRAP);
        
        aboutText.setCaret(null);
        aboutText.setCursor(null);
        final GridData textData = new GridData();
        textData.horizontalAlignment = GridData.FILL;
        textData.verticalAlignment = GridData.FILL;
        textData.grabExcessHorizontalSpace = true;
        textData.grabExcessVerticalSpace = true;
        textData.widthHint = 400;
        textData.horizontalIndent = 6;
        textData.verticalIndent = 4;
        
        aboutText.setLayoutData(textData);
        
        final String copyright = this.aboutI18N.getString("About.Copyright");
        final String appName = this.aboutI18N.getString("About.Application");
        final String tagline = this.aboutI18N.getString("About.TagLine");
        
        final StringBuilder text = new StringBuilder(300);
        text.append(appName);
        text.append("\n");
        text.append(tagline);
        text.append("\n\n");
        text.append(this.aboutI18N.getString("About.Version"));
        text.append(" ");
        text.append(ModelioVersion.VERSION.toString());
        text.append("\n");
        
        text.append(this.aboutI18N.getString("About.ProductBuildId"));
        text.append(" ");
        text.append(ModelioVersion.BUILDID);
        text.append("\n");
        
        // text.append(this.aboutI18N.getString("About.MetamodelVersion"));
        // text.append(" ");
        // text.append(version.getMetamodelVersion());
        // text.append("\n");
        
        text.append(this.aboutI18N.getString("About.System"));
        text.append(" ");
        text.append(Platform.getOS());
        text.append(" (");
        text.append(System.getProperty("os.version"));
        text.append(") ");
        text.append("\n");
        
        text.append(this.aboutI18N.getString("About.Arch"));
        text.append(" ");
        text.append(Platform.getOSArch());
        text.append("\n");
        text.append("\n");
        
        text.append(copyright);
        aboutText.setText(text.toString());
        
        final StyleRange style1 = new StyleRange();
        style1.start = 0;
        style1.length = 7;
        style1.fontStyle = SWT.BOLD;
        aboutText.setStyleRange(style1);
        return workArea;
    }

    @objid ("00498fcc-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        final Button b = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        b.setFocus();
    }

    @objid ("00499076-cc35-1ff2-a7f4-001ec947cd2a")
    @Override
    public boolean close() {
        this.aboutImage.dispose();
        this.aboutImage = null;
        return super.close();
    }

}
