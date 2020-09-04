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

package org.modelio.app.ui.welcome.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.help.internal.base.BaseHelpSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.app.ui.welcome.plugin.AppUiWelcome;
import org.modelio.core.rcp.uiservice.IModelioUiService;
import org.modelio.ui.progress.IModelioProgressService;

@objid ("a1b3a124-3052-4d98-910a-c5e700f002e2")
public class WelcomeView {
    @objid ("a1e27c88-6f6d-4589-861c-e4a8b90137d3")
    private static String WELCOME_HREF = "org.modelio.welcome/html/Index.html";

    @objid ("db8b9253-6c9c-41af-baf1-b88e2e20db5c")
    public static String PARTID = "org.modelio.app.ui.welcome.part";

    @objid ("e00b46d5-2982-4fe9-a969-36956aadd1fe")
    private WelcomeViewController controller;

    @objid ("019e01fe-0a8b-481e-82a4-dd5c93254b8c")
    private Browser browser;

    @objid ("f61bf743-15b6-42c0-82af-f5d3d7675c99")
    @PostConstruct
    public void createControls(Composite parent, IModelioUiService pm, IProjectService projectService, IModelioProgressService progressService) {
        this.browser = new Browser(parent, SWT.BORDER);
        
        this.controller = new WelcomeViewController(this, pm, projectService, progressService);
        
        this.browser.addLocationListener(new InstallExampleLocationListener(this.controller));
        
        URL url = BaseHelpSystem.resolve(WelcomeView.WELCOME_HREF, true);
        this.browser.setUrl(url.toString());
    }

    @objid ("6c6f0cc8-2e65-4b05-961d-684793abfcb6")
    @Focus
    public void onFocus() {
        URL url = BaseHelpSystem.resolve(WelcomeView.WELCOME_HREF, true);
        this.browser.setUrl(url.toString());
    }

    @objid ("7cf6fc1f-7918-44a6-8be1-170c05f9b51e")
    public static void setWelcomeHref(String href) {
        WelcomeView.WELCOME_HREF = href;
    }

    @objid ("3b3cb234-c9a2-4860-a3a4-98e77992c129")
    @PreDestroy
    public void partClose(final IModelioUiService pm) {
        Display.getCurrent().asyncExec(new Runnable() {
            @Override
            public void run() {
                pm.switchToPerspective(null);
            }
        });
    }

    @objid ("32ad12ce-a2f0-4a45-af71-59a6441d1234")
    public void back() {
        this.browser.back();
    }

    @objid ("b44ccb6d-a81a-4f4c-96fa-518748a3acf8")
    public Control getControl() {
        return this.browser;
    }

    @objid ("58d22a84-10cd-4d26-a42a-66e17d17b5ec")
    private static class InstallExampleLocationListener extends LocationAdapter {
        @objid ("afd9c292-e540-46d9-8d96-f23339a16db9")
        private WelcomeViewController controller;

        @objid ("bd150720-1d80-47f9-8c72-b43d74f02942")
        @Override
        public void changing(LocationEvent event) {
            try {
                if (isExampleInstallationActionLink(new URI(event.location))) {
                    event.doit = false;
                    URI zipUri = getZipFileURI(event.location);
                    InstallExampleLocationListener.this.controller.installExample(zipUri);
                }
            } catch (URISyntaxException e) {
                AppUiWelcome.LOG.error(e);
            }
        }

        @objid ("2a92dd1e-d59e-4510-ba6b-60a3aa1d22bf")
        private boolean isExampleInstallationActionLink(URI uri) {
            return Objects.equals(uri.getQuery(), "action=install") && uri.getPath().endsWith(".zip");
        }

        @objid ("ba3055e6-2a9a-4328-8709-954b65829aaf")
        public InstallExampleLocationListener(WelcomeViewController controller) {
            this.controller = controller;
        }

        @objid ("2b2067ba-3839-4f9e-9bee-1d5db2e1d477")
        private URI getZipFileURI(String location) throws URISyntaxException {
            URI uri = new URI(location);
            return new URI(uri.getScheme(), uri.getRawUserInfo(), uri.getHost(), uri.getPort(), uri.getPath(), null, uri.getFragment());
        }

    }

}
