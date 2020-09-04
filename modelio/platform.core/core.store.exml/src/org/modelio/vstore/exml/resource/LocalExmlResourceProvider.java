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

package org.modelio.vstore.exml.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vstore.exml.common.RepositoryVersions;

/**
 * Resource provider for repositories on the local file system.
 */
@objid ("92cf54a7-0326-11e2-b5bf-001ec947ccaf")
public class LocalExmlResourceProvider extends AbstractExmlResourceProvider {
    @objid ("7294666b-cc33-4f49-b9cd-ce4fc0771fc4")
    private String name;

    @objid ("cd668a26-2eec-4c30-918c-878a84635f6e")
    private final Path indexPath;

    @objid ("56431bde-0482-4788-a969-e8537a140013")
    private final Path stampPath;

    @objid ("1b898bae-c8f8-4a31-bed0-666f507ebd51")
    private final Path versionPath;

    @objid ("cf50780e-03e4-11e2-b5bf-001ec947ccaf")
    private final Path modelPath;

    @objid ("cf50780d-03e4-11e2-b5bf-001ec947ccaf")
    protected final Path repositoryPath;

    /**
     * Initialize the resource provider.
     * @param repositoryPath a path on the local file system.
     * @param runtimePath a path on the local file system containing repository data
     * that may be discarded. This directory will usually contain the EXML indexes.
     * @deprecated use {@link #LocalExmlResourceProvider(Path, Path, String)}
     */
    @objid ("4a42dd38-085f-49f2-85c6-5b9eb651c672")
    @Deprecated
    public LocalExmlResourceProvider(Path repositoryPath, Path runtimePath) {
        this(repositoryPath, runtimePath, repositoryPath.toString());
    }

    /**
     * Initialize the resource provider.
     * @param repositoryPath a path on the local file system.
     * @param runtimePath a path on the local file system containing repository data
     * that may be discarded. This directory will usually contain the EXML indexes.
     * @param name the resource name, returned by {@link #getName()}
     */
    @objid ("be505de5-4462-4451-9764-fb11edf3c768")
    public LocalExmlResourceProvider(Path repositoryPath, Path runtimePath, String name) {
        this.repositoryPath = repositoryPath;
        this.name = name;
        this.modelPath = repositoryPath.resolve(IExmlRepositoryGeometry.MODEL_DIRNAME);
        this.stampPath = repositoryPath.resolve(IStampGeometry.STAMP_DIR_NAME).resolve(IStampGeometry.STAMP_FILE_NAME);
        this.indexPath = runtimePath.resolve(IExmlRepositoryGeometry.INDEX_DIRNAME);
        this.versionPath = repositoryPath.resolve(IExmlRepositoryGeometry.FORMAT_VERSION_PATH);
    }

    @objid ("cf50781d-03e4-11e2-b5bf-001ec947ccaf")
    @Override
    public void buildIndexes(IModelioProgress monitor) throws IOException {
        Files.createDirectories(this.indexPath);
    }

    @objid ("cf50782b-03e4-11e2-b5bf-001ec947ccaf")
    @Override
    public void close() {
        // Nothing to do.
    }

    @objid ("cf52da6e-03e4-11e2-b5bf-001ec947ccaf")
    @Override
    public void commit() throws IOException {
        // TODO Implement a local ACID transaction to ensure consistency when
        // write failure.
        
        // Write the stamp
        if (isWriteable()) {
            writeStamp();
        }
    }

    @objid ("b7bd8a6c-220c-4225-88ff-aa5b1540ce6e")
    @Override
    public boolean exists() throws IOException {
        return Files.isDirectory(this.modelPath);
    }

    @objid ("cf50782e-03e4-11e2-b5bf-001ec947ccaf")
    @Override
    public Collection<ExmlResource> getAllResources(IModelioProgress aMonitor) throws IOException {
        List<ExmlResource> toBuild = new ArrayList<>();
        SubProgress monitor = SubProgress.convert(aMonitor, "Getting all repository content...", 5);
        
        try (DirectoryStream<Path> classDs = Files.newDirectoryStream(this.modelPath, entry -> Files.isDirectory(entry))) {
            for (Path dir : classDs) {
                try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir, "*.exml")) {
                    for (Path f : dirStream) {
                        toBuild.add(new LocalResource(f));
                    }
                }
                monitor.worked(1);
                monitor.setWorkRemaining(5);
            }
        }
        return Collections.unmodifiableCollection(toBuild);
    }

    @objid ("8dc9f0b0-135d-4f44-9ce7-365988d3d50c")
    @Override
    public File getIndexAccessPath() {
        return this.indexPath.toFile();
    }

    @objid ("ab62866f-ec6c-415f-b3dd-d8e662af28c9")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("cf507812-03e4-11e2-b5bf-001ec947ccaf")
    @Override
    public ExmlResource getRelativePathResource(String relativePath) {
        return new LocalResource(this.repositoryPath.resolve(relativePath));
    }

    @objid ("7acb5016-121e-4da7-839c-f01355078455")
    @Override
    public String getStamp() {
        try {
            try {
                return FileUtils.readWhole(this.stampPath, "UTF-8");
            } catch (NoSuchFileException e) {
                // TODO 23/02/2013: Compat with old stamp file name, to remove later
                Path old = this.stampPath.getParent().resolve("stamp.conf");
                return FileUtils.readWhole(old, "UTF-8");
            }
        } catch (IOException e) {
            return "";
        }
    }

    @objid ("cf52da74-03e4-11e2-b5bf-001ec947ccaf")
    @Override
    public URI getURI() {
        return this.repositoryPath.toUri();
    }

    @objid ("c1ed907d-0941-4154-92f4-875605f2f2c2")
    @Override
    public boolean isBrowsable() {
        return true;
    }

    @objid ("5a5f0ba1-0724-11e2-9eb7-001ec947ccaf")
    @Override
    public boolean isWriteable() {
        return true;
    }

    @objid ("b40b56ec-1517-42ed-9037-69cade27d4a5")
    @Override
    public RepositoryVersions readRepositoryVersion() throws IOException {
        RepositoryVersions v = super.readRepositoryVersion();
        if (v == null) {
            // guess version
            List<String> cmsNodes = Files.list(this.modelPath).map(p -> p.getFileName().toString()).collect(Collectors.toList());
            return new RepositoryVersions(0, cmsNodes);
        }
        return v;
    }

    /**
     * Set the repository name.
     * @param name the repository name.
     */
    @objid ("7a4eab85-01fe-4946-96f6-c458bb62e242")
    public void setName(String name) {
        this.name = name;
    }

    @objid ("e82a245c-9825-4914-82f2-b32bec3091fc")
    @Override
    public String toString() {
        return "'" + this.name + "' local repository @" + this.repositoryPath;
    }

    @objid ("5c8a68d4-53b4-4224-bf82-ca99692d14e9")
    @Override
    public void writeStamp() throws IOException {
        Path stampDir = this.stampPath.getParent();
        
        if (!stampDir.getFileSystem().isReadOnly()) {
            byte[] bytes = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);
        
            Files.createDirectories(stampDir);
            Files.write(this.stampPath, bytes);
        }
    }

    @objid ("cf52da71-03e4-11e2-b5bf-001ec947ccaf")
    @Override
    protected void doCreateRepository(MMetamodel metamodel) throws IOException {
        ExmlRepositoryCreator c = new ExmlRepositoryCreator(this.repositoryPath, getGeometry(), metamodel);
        c.createRepositoryStructure();
    }

    /**
     * Local EXML resource.
     */
    @objid ("cf52da5d-03e4-11e2-b5bf-001ec947ccaf")
    protected static final class LocalResource implements ExmlResource {
        @objid ("cf52da60-03e4-11e2-b5bf-001ec947ccaf")
        private Path p;

        @objid ("cf52da61-03e4-11e2-b5bf-001ec947ccaf")
        public LocalResource(Path p) {
            this.p = p;
        }

        @objid ("cf52da64-03e4-11e2-b5bf-001ec947ccaf")
        @Override
        public InputStream read() throws IOException {
            if (!Files.isRegularFile(this.p)) {
                return null;
            } else {
                return Files.newInputStream(this.p);
            }
        }

        @objid ("cf52da69-03e4-11e2-b5bf-001ec947ccaf")
        @Override
        public OutputStream write() throws IOException {
            Files.createDirectories(this.p.getParent());
            return Files.newOutputStream(this.p);
        }

        @objid ("97902336-12de-11e2-816a-001ec947ccaf")
        @Override
        public void delete() throws IOException {
            Files.deleteIfExists(this.p);
        }

        @objid ("92e120c5-2cd2-11e2-81f1-001ec947ccaf")
        @Override
        public String getPublicLocation() {
            return this.p.toString();
        }

    }

}
