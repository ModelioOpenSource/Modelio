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

package org.modelio.gproject.gproject.remote;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.DescriptorServices;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectDescriptorReader;
import org.modelio.gproject.gproject.GProjectAuthenticationException;
import org.modelio.gproject.gproject.IProjectFactory;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.xml.sax.InputSource;

/**
 * Remote project factory.
 * <p>
 * This is the base class for all remote project factory implementations.
 * Many methods are final, they are not meant to be redefined in order to keep
 * the same file geometry for all remote project implementations..
 */
@objid ("176d5c7f-2dfb-4f47-b732-1a34c65e458f")
public abstract class GRemoteProjectFactory implements IProjectFactory {
    /**
     * Name of the remote project descriptor file.
     */
    @objid ("2dd9c111-5e5d-4c4d-8db2-6672824e9798")
    public static final String REMOTE_PROJECT_CONF = "project.server.conf";

    /**
     * Fetch the remote descriptor from its remote location.
     * 
     * @param localDescriptor the local part of a project descriptor.
     * @param authData authentication data.
     * @param monitor a progress monitor
     * @return the XML input source of the server side project descriptor.
     * @throws java.io.IOException in case of failure.
     * @throws org.modelio.gproject.gproject.GProjectAuthenticationException in case of authentication error
     */
    @objid ("9c5cdca1-b1a5-46b5-895d-f581de5aa994")
    public abstract InputSource readRemoteDescriptor(ProjectDescriptor localDescriptor, IAuthData authData, IModelioProgress monitor) throws GProjectAuthenticationException, IOException;

    @objid ("22187d6c-d7a1-43d0-a95e-3a0e0a8af56d")
    @Override
    public final ProjectDescriptor getRemoteDescriptor(final ProjectDescriptor localDescriptor, final IAuthData authData, final IModelioProgress monitor) throws GProjectAuthenticationException, IOException {
        final InputSource is = readRemoteDescriptor(localDescriptor, authData, monitor);
        try {
            final ProjectDescriptor newServerDesc = new ProjectDescriptorReader().read(is, null);
            return newServerDesc;
        } catch (final IOException e) {
            // Enriches the exception with the descriptor content if possible.
            try {
                final InputSource is2 = readRemoteDescriptor(localDescriptor, authData, monitor);
                final String content = FileUtils.readWhole(is2.getByteStream(), "utf-8");
                e.addSuppressed(new Throwable("Descriptor content:\n"+content));
        
            } catch (final Exception e2) {
                e.addSuppressed(e2);
            }
            throw e;
        }
    }

    @objid ("151079c2-d162-4f44-a4f9-f3226ee7955c")
    protected void completeDescriptor(final ProjectDescriptor projectDescriptor, final IAuthData authData, final IModelioProgress aProgress) throws GProjectAuthenticationException, IOException {
        boolean complete = true;
        for (final FragmentDescriptor  fd: projectDescriptor.getFragments()) {
            if (! fd.isValid()) {
                complete = false;
                break;
            }
        }
        
        if (! complete){
            final InputSource is = readRemoteDescriptor(projectDescriptor, authData, aProgress);
        
            final ProjectDescriptor newServerDesc = new ProjectDescriptorReader().read(is, null);
            DescriptorServices.removeSharedPart(projectDescriptor);
            DescriptorServices.merge(newServerDesc, projectDescriptor);
            projectDescriptor.cleanup();
        }
    }

}
