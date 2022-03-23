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
package org.modelio.app.ui.login;

import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.version.ModelioVersion;

/**
 * Provides a splash screen shell.
 */
@objid ("2ef50e57-0513-4a4e-8595-a2494608c10c")
public class Splash {
    @objid ("836e0343-84cc-4bed-baee-afc7dc310b79")
    protected Shell shell;

    @objid ("8cbb171f-4eb7-489e-b2a7-4bcae4a1aefd")
    private Label messageLabel;

    @objid ("aaecd7d0-a3ce-463a-8d57-963d784f7f08")
    private Label copyright;

    @objid ("0fb7a0c3-6f72-42b0-8b01-8378503e5540")
    private Image splashImage;

    /**
     * C'tor.
     */
    @objid ("2fec1ef4-28e3-4554-b5e3-ee823eb1ed33")
    public  Splash() {
        this.shell = new Shell(SWT.INHERIT_NONE | SWT.NO_TRIM);
        
        final ImageDescriptor imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(AppUi.PLUGIN_ID, "images/splash600x376.png");
        this.splashImage = imageDescriptor.createImage(true);
        
        this.shell.setBackgroundImage(this.splashImage);
        this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
        this.shell.setSize(this.splashImage.getImageData().width, this.splashImage.getImageData().height);
        final FormLayout formLayout = new FormLayout();
        this.shell.setLayout(formLayout);
        
        createControls(this.shell);
        
    }

    /**
     * Open the spash screen.
     */
    @objid ("47fac1f4-7c5f-47e2-a145-06eff0e7b35c")
    public void open() {
        setCentered();
        this.shell.open();
        
    }

    /**
     * Close the spash screen.
     */
    @objid ("b299db31-c386-443f-a3fc-7a3fbe1b79a7")
    public void close() {
        this.shell.close();
        this.shell = null;
        
        if (this.splashImage != null) {
            this.splashImage.dispose();
            this.splashImage = null;
        }
        
    }

    @objid ("47470b03-4559-433b-8cdd-1db53d0ff89f")
    protected void createControls(Shell shell) {
        // Display Modelio version
        final Label versionLabel = new Label(shell, SWT.NONE);
        versionLabel.setAlignment(SWT.RIGHT);
        
        versionLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
        FormData formData = new FormData();
        formData.top = new FormAttachment(95, 0);
        formData.right = new FormAttachment(100, -10);
        versionLabel.setLayoutData(formData);
        versionLabel.setText("v" + ModelioVersion.VERSION.toString());
        
        this.messageLabel = new Label(shell, SWT.NONE);
        this.messageLabel.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
        
        formData = new FormData();
        formData.top = new FormAttachment(75, 0);
        formData.left = new FormAttachment(50, 10);
        formData.right = new FormAttachment(100, -4);
        this.messageLabel.setLayoutData(formData);
        this.messageLabel.setText("Initializing...");
        
        this.copyright = new Label(shell, SWT.NONE);
        this.copyright.setForeground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
        formData = new FormData();
        formData.top = new FormAttachment(95, 0);
        formData.left = new FormAttachment(0, 10);
        this.copyright.setLayoutData(formData);
        
        BundledMessages b = new BundledMessages(AppUi.LOG, ResourceBundle.getBundle("appui-about"));
        this.copyright.setText(b.getString("Splash.copyright"));
        
    }

    @objid ("1abc433f-7a2b-4800-80a7-f84428998616")
    protected void setCentered() {
        // Positioning in the center of the screen.
        final Rectangle bounds = Display.getCurrent().getPrimaryMonitor().getBounds();
        this.shell.setLocation((bounds.width - this.shell.getSize().x) / 2, (bounds.height - this.shell.getSize().y) / 2);
        
    }

    /**
     * Set a progress message in the splash screen.
     * @param message the message to display.
     */
    @objid ("5cef6e5a-cebe-4874-b06d-798afa0b1ed2")
    public void showMessage(String message) {
        this.messageLabel.setText(message);
        this.messageLabel.redraw();
        // Consume until empty the SWT display event queue to ensure the text is displayed
        while (this.shell.getDisplay().readAndDispatch()) {
            // nothing
        }
        
    }

}
