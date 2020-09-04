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

package org.modelio.app.project.ui.views.infos;

import java.beans.EventHandler;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Project page view.
 */
@objid ("61641e68-3173-4904-a08c-7c833ce12641")
public class ProjectPageView {
    /**
     * The ID of the view as specified by the extension.
     */
    @objid ("f487ef26-9d82-49f0-88fb-d36d32f6c356")
    public static final String ID = "org.modelio.app.project.ui.part.projectpage";

    @objid ("2586ae42-4f09-4e07-95cf-c862ede205b6")
    private String url;

    @objid ("7d00c469-2d7f-4d2a-a193-a3caf732a371")
    private Browser browser;

    /**
     * C'tor
     */
    @objid ("e5d7f486-50cd-4cb2-bfff-c46ba25dc4c6")
    public ProjectPageView() {
    }

    /**
     * initialize the view.
     * <p>
     * Called by the E4 model.
     * 
     * @param parent the parent composite
     */
    @objid ("7290565c-fe42-4282-8fbf-52a69c798f7a")
    @PostConstruct
    public void createControls(final Composite parent) {
        this.browser = new Browser(parent, SWT.NULL);
        this.browser.setText("");
        
        Listener listener = EventHandler.create(Listener.class, this, "onBrowserActivated");
        this.browser.addListener(SWT.Show , listener);
        //this.browser.addListener(SWT.Activate, listener);
    }

    /**
     * Test method.
     * 
     * @param args command line arguments
     */
    @objid ("239ea6af-a38c-4693-b046-44ff200d9bb3")
    public static void main(final String[] args) {
        final Display d = new Display();
        
        Shell shell = new Shell(d);
        shell.setLayout(new FillLayout());
        ProjectPageView view = new ProjectPageView();
        view.setUrl("http://www.modelio.org");
        view.createControls(shell);
        shell.open();
        while (!shell.isDisposed()) {
            if (!d.readAndDispatch()) {
                d.sleep();
            }
        }
        d.dispose();
    }

    @objid ("96b90d3a-3e3d-4e7b-ac85-da96b5005a93")
    @PreDestroy
    private void dispose() {
        this.browser.dispose();
        this.browser = null;
    }

    @objid ("95cdf026-4383-4136-ba9e-840b7b80db01")
    @Focus
    void setFocus() {
        this.browser.setFocus();
    }

    /**
     * Set the HTML browser URL.
     * 
     * @param viewUrl the URL to view.
     */
    @objid ("e19d9580-31eb-49f7-a1e7-aa39b114e639")
    public void setUrl(final String viewUrl) {
        this.url = viewUrl;
        
        if (this.browser!=null && !this.browser.isDisposed() && this.browser.isVisible())
            onBrowserActivated();
    }

    @objid ("22707d76-ad0e-48d2-a74f-da6fbb6fa7fe")
    public String getUrl() {
        return this.url;
    }

    /**
     * @see #createControls(Composite)
     */
    @objid ("8d635747-e326-4045-9e1d-633d3de9399d")
    public void onBrowserActivated() {
        String browserUrl = this.browser.getUrl();
        if (browserUrl.equals(this.url) ||
                (this.url==null && this.browser.getUrl().isEmpty()))
            return;
        
        if (this.url != null)
            this.browser.setUrl(this.url);
        else
            this.browser.setText("");
    }


// @Optional
// @Inject
//
// public void update(final @Named(IServiceConstants.ACTIVE_SELECTION)
// GProject project) {
// if (browser == null)
// return;
//
// if (project == null) {
// this.browser.setText("");
// return;
// }
//
// String url = project.getProperties().getProperty("url");
// if (url != null) {
// this.browser.setUrl(url);
// } else {
// this.browser.setText("");
// }
// }
// @Optional
// @Inject
//
// public void update(final @Named(IServiceConstants.ACTIVE_SELECTION)
// IModelFragment fragment) {
// if (browser == null || fragment == null)
// return;
//
// String url = fragment.getProperties().getProperty("url",
// "http://www.modelio.org/forum/index.html");
// if (url != null)
// this.browser.setUrl(url);
// else
// this.browser.setText("");
// }
}
