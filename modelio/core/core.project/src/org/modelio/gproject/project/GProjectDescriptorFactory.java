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
package org.modelio.gproject.project;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.auth.GProjectAuthenticationException;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.GProjectDescriptor;
import org.modelio.gproject.data.project.GProjectDescriptorReader;
import org.modelio.gproject.data.project.ILockInfo;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.lock.ProjectLock;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.net.UriAuthenticationException;
import org.modelio.vbasic.net.UriConnections;
import org.modelio.vbasic.net.UriUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.xml.sax.InputSource;

/**
 * Factory that instantiates a GProjectDescriptor from a File path or a remote URI.
 */
@objid ("004d0788-3511-1fc6-b42e-001ec947cd2a")
public class GProjectDescriptorFactory {
    @objid ("9c743b55-2345-4cc7-b718-40cbd03dc9c2")
    public static final String REMOTE_PROJECT_CONF = "project.server.conf";

    /**
     * Get the remote project descriptor for a project descriptor.
     * @param projectDescriptor a project descriptor.
     * @param authData authentication data.
     * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility to call <code>done()</code> on the given monitor. Accepts <code>null</code>, indicating that no progress should be reported
     * and that the operation cannot be cancelled.
     * @return the remote project descriptor.
     * @throws IOException in case of failure
     * @throws GProjectAuthenticationException in case of authentication failure.
     */
    @objid ("96d74dc5-cf49-4d1a-8670-fdb5001db4ab")
    public static GProjectDescriptor getRemoteDescriptor(GProjectDescriptor localDescriptor, IAuthData authData, IModelioProgress monitor) throws IOException, GProjectAuthenticationException {
        //        IProjectFactory f = GProjectFactory.getProjectFactory(projectDescriptor);
        //        return f.getRemoteDescriptor(projectDescriptor, authData, monitor);
        
                final InputSource is = readRemoteDescriptor(localDescriptor, authData, monitor);
                try {
                    final GProjectDescriptor newServerDesc = new GProjectDescriptorReader().read(is, null);
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

    @objid ("2281a346-45b4-4dc8-9cc7-e22ae9f9bd14")
    public static InputSource readRemoteDescriptor(GProjectDescriptor projectDescriptor, IAuthData authData, IModelioProgress monitor) throws IOException, GProjectAuthenticationException {
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

    @objid ("d3301f2e-e727-4a4f-9107-def82f69db44")
    static URI getRemoteDirUri(GProjectDescriptor projectDescriptor) throws NoSuchFileException {
        try {
            return new URI(projectDescriptor.getRemoteLocation());
        } catch (URISyntaxException e) {
            NoSuchFileException e2 = new NoSuchFileException(projectDescriptor.getRemoteLocation(), null, e.getLocalizedMessage());
            e2.initCause(e);
            throw e2;
        }
        
    }

    /**
     * Tells whether the given path is a project space path.
     * @param projectPath a directory path
     * @return <code>true</code> if it is a project space path, else <code>false</code>.
     */
    @objid ("0021c0c8-34d4-1fc7-b42e-001ec947cd2a")
    public static boolean isProjectSpace(final Path projectPath) {
        Path confFile = GProjectDescriptorFactory.getConfigFile(projectPath);
        return Files.isRegularFile(confFile);
    }

    /**
     * Create a ProjectDescriptor from a project directory.
     * @param projectDir the project directory.
     * @return the read project descriptor.
     * @throws IOException in case of error reading the configuration file.
     */
    @objid ("ea6edf6a-d50f-4f48-a07c-7bf27a487875")
    public static GProjectDescriptor readProjectDirectory(final Path projectDir) throws IOException {
        Path confFile = GProjectDescriptorFactory.getConfigFile(projectDir);
        GProjectDescriptor desc = new GProjectDescriptorReader()
                .setDefaultScope(DefinitionScope.LOCAL)
                .read(confFile, null);
        
        try {
            desc.setLockInfo(GProjectDescriptorFactory.getLockInformations(desc));
        } catch (IOException e) {
            // ignore
            Log.trace("Cannot get lock informations on " + projectDir + ":");
            Log.trace(e);
        }
        return desc;
    }

    @objid ("0021e1c0-34d4-1fc7-b42e-001ec947cd2a")
    private static Path getConfigFile(final Path projectPath) {
        return new ProjectFileStructure(projectPath).getProjectConfFile();
    }

    /**
     * Test whether a project is locked.
     * @param desc a project descriptor.
     * @return lock informations if the project is locked, else <i>null</i>.
     * @throws IOException in case of I/O failure
     */
    @objid ("2730c29a-29f2-48da-bf37-e87126767e2b")
    public static ILockInfo getLockInformations(final GProjectDescriptor desc) throws IOException {
        ProjectLock locker = ProjectLock.get(desc.getProjectFileStructure().getProjectRuntimePath(), desc.getName());
        return locker.test();
    }

    /**
     * No instance.
     */
    @objid ("b9e9ef93-f4b0-4a03-bb48-fc37f1f6cfa5")
    private  GProjectDescriptorFactory() {
        // No instance
    }

}
