/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.core.rcp.system;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.help.internal.base.BaseHelpSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.modelio.core.rcp.plugin.CoreRcp;

@objid ("7aafb1f9-8193-4526-ac6f-e8811139860d")
public class ModelioHelpUi {
    @objid ("8236e2ae-04f2-48c2-8fdf-44019cb590ae")
    private static ModelioHelpUi instance;

    @objid ("377f3775-0e99-4112-adf3-c3fdd9edb892")
    private Browser browser;

    @objid ("da85e7dd-6d43-4a06-9849-1d4983270756")
    private Shell shellWindow;

    /**
     * Constructor.
     */
    @objid ("94dcc31c-55ee-461e-8e80-5e858a730079")
    public ModelioHelpUi() {
        ModelioHelpUi.instance = this;
    }

    @objid ("cb356d41-1a89-4952-8c75-cc70d8f0f311")
    private Browser getBrowser() {
        if (this.shellWindow == null || this.browser.isDisposed()) {
            createGui();
        }
        
        if (this.shellWindow.getMinimized()) {
            this.shellWindow.setMinimized(false);
        }
        
        this.shellWindow.forceActive();
        return this.browser;
    }

    @objid ("93c20cf9-485d-45c1-b427-56c38e4c688c")
    public static ModelioHelpUi getInstance() {
        return ModelioHelpUi.instance;
    }

    /**
     * Displays help.
     */
    @objid ("78b0ff90-b749-4048-8f88-56ec78c2bac3")
    public void displayHelp() {
        URL url = BaseHelpSystem.resolve("../index.jsp", false);
        if (url != null) {
            this.getBrowser().setUrl(url.toString());
        } else {
            CoreRcp.LOG.debug("help resource not found: " + "../index.jsp");
        }
    }

    /**
     * Displays a help resource specified as a url.
     * <ul>
     * <li>a URL in a format that can be returned by {@link org.eclipse.help.IHelpResource#getHref() IHelpResource.getHref()}
     * <li>a URL query in the format format <em>key=value&amp;key=value ...</em> The valid keys are: "tab", "toc", "topic", "contextId". For example, <em>toc="/myplugin/mytoc.xml"&amp;topic="/myplugin/references/myclass.html"</em> is valid.
     * </ul>
     */
    @objid ("99148ccc-ddee-458d-9dcb-f0796ac15217")
    public void displayHelpResource(String href) {
        URL url = BaseHelpSystem.resolve(href, false);
        if (url != null) {
            this.getBrowser().setUrl(url.toString());
        } else {
            CoreRcp.LOG.debug("help resource not found: " + href);
        }
    }

    @objid ("4946cad6-70a3-4919-b07b-e72a037dc768")
    public URL resolve(String href, boolean documentOnly) {
        return BaseHelpSystem.resolve(href, documentOnly);
    }

    @objid ("42ca401c-8146-4475-a490-6047ff4daf9d")
    private void createGui() {
        if (Display.getCurrent() != null) {
            this.shellWindow = new Shell(Display.getCurrent().getActiveShell(), SWT.SHELL_TRIM);
        } else {
            this.shellWindow = new Shell(SWT.SHELL_TRIM);
        }
        this.shellWindow.setText(CoreRcp.I18N.getString("HelpWindow.title"));
        this.shellWindow.setLayout(new GridLayout());
        
        this.browser = new Browser(this.shellWindow, SWT.BORDER);
        this.browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.shellWindow.addDisposeListener(new DisposeListener() {
        
            @Override
            public void widgetDisposed(DisposeEvent e) {
                ModelioHelpUi.this.browser = null;
                ModelioHelpUi.this.shellWindow = null;
        
            }
        });
        
        this.shellWindow.setSize(800, 600);
        Monitor primary = this.shellWindow.getDisplay().getPrimaryMonitor();
        Rectangle bounds = primary.getBounds();
        Rectangle rect = this.shellWindow.getBounds();
        
        int x = bounds.x + (bounds.width - rect.width) / 2;
        int y = bounds.y + (bounds.height - rect.height) / 2;
        
        this.shellWindow.setLocation(x, y);
        this.shellWindow.open();
    }

}
