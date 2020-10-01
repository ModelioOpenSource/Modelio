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

package org.modelio.gproject.gproject.url;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DescriptorServices;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectType;
import org.modelio.gproject.gproject.IGProjectEnv;
import org.modelio.gproject.gproject.ProjectWriter;
import org.modelio.gproject.gproject.remote.GRemoteProject;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.net.UriConnections;
import org.modelio.vbasic.net.UriUtils;
import org.modelio.vbasic.progress.IModelioProgress;

/**
 * Represents a project shared on a HTTP server or anything that can be accessed from an URI.
 * <p>
 * The URI must be handled by {@link UriConnections} in order to work.
 */
@objid ("c4a5b65a-0bac-11e2-bed6-001ec947ccaf")
public class GUrlProject extends GRemoteProject {
    @objid ("68245233-0d5c-11e2-9d8a-001ec947ccaf")
    private URI remoteUri;

    /**
     * Instantiate an URI remote project.
     * <p>
     * To be called by the factory only.
     */
    @objid ("f7ea37ec-0f09-11e2-bd8d-001ec947ccaf")
    public GUrlProject() {
        // nothing to do.
    }

    @objid ("68245237-0d5c-11e2-9d8a-001ec947ccaf")
    @Override
    public String getRemoteLocation() {
        return this.remoteUri.toString();
    }

    @objid ("32961a91-0d61-11e2-8e4b-001ec947ccaf")
    @Override
    protected void load(ProjectDescriptor projectDescriptor, IAuthData authData, IGProjectEnv configuration, IModelioProgress aProgress) throws IOException {
        super.load(projectDescriptor, authData, configuration, aProgress);
        
        this.remoteUri = getRemoteUri(projectDescriptor);
    }

    /**
     * Get the remote project location from a project descriptor.
     * <p>
     * The default implementation directly reads the {@link ProjectDescriptor#getRemoteLocation()}
     * and converts it to an URI ending with a '/'.
     * 
     * @param projectDescriptor a project descriptor.
     * @return the remote project URI.
     * @throws java.io.IOException if the location in the descriptor is invalid.
     */
    @objid ("2a68c81d-2fea-4218-b800-8ef9d11b7052")
    private URI getRemoteUri(ProjectDescriptor projectDescriptor) throws IOException {
        URI ret;
        try {
            ret = UriUtils.asDirectoryUri(new URI(projectDescriptor.getRemoteLocation()));
        } catch (URISyntaxException e) {
            NoSuchFileException e2 = new NoSuchFileException(projectDescriptor.getRemoteLocation(), null, e.getLocalizedMessage());
            e2.initCause(e);
            throw e2;
        }
        return ret;
    }

    @objid ("e0f84f49-f8d6-4109-82e0-565ee6e35589")
    @Override
    public ProjectType getType() {
        return ProjectType.HTTP;
    }

    @objid ("a5417f96-1005-4f50-9080-9c1a13e1c49d")
    @Override
    protected void setRemoteLocation(String remoteLocation) throws URISyntaxException {
        this.remoteUri = UriUtils.asDirectoryUri(new URI(remoteLocation));
    }

    /**
     * Redefined because {@link #remoteUri} is modified to have a trailing '/'.
     */
    @objid ("5ffd37a7-09ba-45b4-b11b-c99f24ac4a1e")
    @Override
    protected boolean needsReconfiguration(ProjectDescriptor serverDesc) {
        ProjectDescriptor currentDesc = new ProjectWriter(this).writeProject();
        DescriptorServices.removeLocalPart(currentDesc);
        
        // Remove the trailing '/' from the remote location
        URI u = this.remoteUri;
        String path = this.remoteUri.getRawPath();
        
        if (path.lastIndexOf("/") == path.length()-1) {
            try {
                path = path.substring(0, path.length()-1);
                u = new URI(u.getScheme(), u.getUserInfo(), u.getHost(), u.getPort(),
                        path, u.getQuery(), u.getFragment());
        
                currentDesc.setRemoteLocation(u.toString());
            } catch (URISyntaxException | IndexOutOfBoundsException e) {
                Log.trace(e);
            }
        }
        
        // Compare
        return ! serverDesc.equals(currentDesc);
    }

}
