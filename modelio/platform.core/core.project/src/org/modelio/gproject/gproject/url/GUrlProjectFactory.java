/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.gproject.gproject.url;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectType;
import org.modelio.gproject.gproject.GProject;
import org.modelio.gproject.gproject.GProjectAuthenticationException;
import org.modelio.gproject.gproject.remote.GRemoteProjectFactory;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.net.UriAuthenticationException;
import org.modelio.vbasic.net.UriConnections;
import org.modelio.vbasic.net.UriUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.xml.sax.InputSource;

/**
 * Project factory that produces {@link GUrlProject}.
 */
@objid ("4eeb1445-114e-11e2-8a4f-001ec947ccaf")
public class GUrlProjectFactory extends GRemoteProjectFactory {
    @objid ("4eeb1448-114e-11e2-8a4f-001ec947ccaf")
    @Override
    public GProject createProject(final ProjectDescriptor projectDescriptor) throws IllegalArgumentException {
        return new GUrlProject();
    }

    @objid ("4eeb1449-114e-11e2-8a4f-001ec947ccaf")
    @Override
    public boolean supports(ProjectDescriptor projectDescriptor) {
        return projectDescriptor.getType().equals(ProjectType.HTTP.name());
    }

    @objid ("9540f2aa-501c-4710-b893-0982c35d32e1")
    @Override
    public InputSource readRemoteDescriptor(ProjectDescriptor projectDescriptor, IAuthData authData, IModelioProgress monitor) throws GProjectAuthenticationException, IOException {
        URI remoteUri = UriUtils.asDirectoryUri(getRemoteDirUri(projectDescriptor));
        URI remoteConfUri =  remoteUri.resolve(REMOTE_PROJECT_CONF);
        
        try {
            InputSource inputSource = new InputSource(UriConnections.openInputStream(remoteConfUri, authData));
            inputSource.setSystemId(remoteConfUri.toString());
            return inputSource;
        } catch (UriAuthenticationException e) {
            throw new GProjectAuthenticationException(FileUtils.getLocalizedMessage(e), e);
        }
    }

    @objid ("f11a47b0-e529-4c11-93ca-dbf2b63edf5a")
    static URI getRemoteDirUri(ProjectDescriptor projectDescriptor) throws NoSuchFileException {
        try {
            return new URI(projectDescriptor.getRemoteLocation());
        } catch (URISyntaxException e) {
            NoSuchFileException e2 = new NoSuchFileException(projectDescriptor.getRemoteLocation(), null, e.getLocalizedMessage());
            e2.initCause(e);
            throw e2;
        }
    }

}
