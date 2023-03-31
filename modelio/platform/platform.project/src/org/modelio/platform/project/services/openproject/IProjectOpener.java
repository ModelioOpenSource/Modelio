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
package org.modelio.platform.project.services.openproject;

import java.io.IOException;
import java.net.URI;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IProgressMonitor;
import org.modelio.gproject.auth.GProjectAuthenticationException;
import org.modelio.gproject.data.project.GProjectDescriptor;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Interface for the service that opens a project.
 */
@objid ("d5742991-7836-4806-a101-22aa9e9ca3b6")
public interface IProjectOpener {
    /**
     * Open a project given its loaded descriptor {@link GProjectDescriptor}.
     * @param projectToOpen the project descriptor, never <i>null</i>.
     * @param authData the authentication data
     * @param monitor a progress monitor, may be <i>null</i>.
     * @throws GProjectAuthenticationException on authentication failure
     * @throws IOException on failure
     * @throws InterruptedException if interrupted or cancelled by the user.
     */
    @objid ("5a5bf0fa-4077-45e2-a004-5bb062545251")
    void openProject(GProjectDescriptor projectToOpen, IAuthData authData, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException;

    /**
     * Initialize the service from required services
     * @param service a write access to the project service
     */
    @objid ("0b017211-5a49-42c2-ab4f-dd409ba224b3")
    void configure(IProjectServiceAccess service);

    /**
     * Open a project from an URI.
     * @param projectURI where the project is.
     * @param authData the authentication data
     * @param monitor a progress monitor, may be <i>null</i>.
     * @throws GProjectAuthenticationException on authentication failure
     * @throws IOException on failure
     * @throws InterruptedException if interrupted or cancelled by the user.
     */
    @objid ("5923395d-e5f9-43a2-a7ce-18b290da41a6")
    void openProject(URI projectURI, IAuthData authData, IProgressMonitor monitor) throws GProjectAuthenticationException, IOException, InterruptedException;
}

