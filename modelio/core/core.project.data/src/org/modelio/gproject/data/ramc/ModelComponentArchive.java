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

package org.modelio.gproject.data.ramc;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.ProviderNotFoundException;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.plugin.GProjectData;
import org.modelio.gproject.data.project.DefinitionScope;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.GProperties;
import org.modelio.gproject.data.ramc.IModelComponentInfos.ExportedFile;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.files.StreamException;
import org.modelio.vbasic.files.Unzipper;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.version.ModelioVersionGuesser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Represents a model component (RAMC) archive.
 * <p>
 * The archive may then be scanned for informations or deployed to a directory.
 */
@objid ("6f9b358d-2891-4804-b666-855b56498148")
public class ModelComponentArchive {
    @objid ("5614f240-37a2-48a6-b805-54a03980d14e")
    private static final String METADATAS_XML = "metadatas.xml";

    @objid ("67d53a3a-c14b-47a5-ab04-a9dab3c7ac0b")
    private Path archive;

    @objid ("8a5c3e46-573e-4636-a0a0-80f9fdb8f1b0")
    private Path dir;

    @objid ("440fa867-3f9d-4740-a07e-85e7f1da0212")
    private ModelComponentInfos manifest;

    /**
     * Initialize the RAMC from a path that may represent a .ramc archive or an extracted
     * RAMC directory.
     * 
     * @param path a file system path
     * @param isArchive <code>true</code> if the path is a .ramc file, <code>false</code> if the
     * path is a directory.
     */
    @objid ("6ac583a6-47e2-4b4c-a723-1c5c871a22f7")
    public ModelComponentArchive(Path path, boolean isArchive) {
        if (isArchive) {
            this.archive = path;
        } else {
            this.dir = path;
        }
    }

    /**
     * Build a fragment descriptor.
     * 
     * @return the RAMC fragment descriptor.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("0c209457-80d5-4514-8f81-d0758e15372c")
    public FragmentDescriptor getFragmentDescriptor() throws IOException {
        IModelComponentInfos infos = getInfos();
        
        FragmentDescriptor fragmentDescriptor = new FragmentDescriptor();
        
        fragmentDescriptor.setId(this.manifest.getName() + " " + this.manifest.getVersion().toString("V.R.C"));
        fragmentDescriptor.setScope(DefinitionScope.LOCAL);
        fragmentDescriptor.setType(FragmentType.RAMC);
        
        File file = this.archive.toFile();
        URI uri = file.toURI();
        
        fragmentDescriptor.setUri(uri);
        final GProperties properties = new GProperties();
        properties.setProperty("FragmentVersion", infos.getVersion().toString("V.R.C"), DefinitionScope.LOCAL);
        properties.setProperty("FragmentDescription", infos.getDescription(), DefinitionScope.LOCAL);
        fragmentDescriptor.setProperties(properties);
        return fragmentDescriptor;
    }

    /**
     * Get the RAMC informations.
     * 
     * @return the informations.
     * @throws java.io.IOException in case of I/O error.
     */
    @objid ("20253825-c258-4295-8cbb-a32fd82dabeb")
    public IModelComponentInfos getInfos() throws IOException {
        if (this.manifest == null) {
            if (this.archive != null) {
                this.manifest = ManifestReader.readArchive(this.archive);
            } else {
                this.manifest = ManifestReader.readDirectory(this.dir);
            }
        }
        return this.manifest;
    }

    /**
     * Get the RAMC informations from a file system mounted on the RAMC archive
     * 
     * @param fs a file system mounted on the RAMC archive.
     * @return the RAMc informations.
     * @throws java.io.IOException in case of I/O failure.
     */
    @objid ("4867d0ee-d7d1-439b-b71d-3f99148e0fb7")
    public static IModelComponentInfos getInfos(FileSystem fs) throws IOException {
        Path metaPath = fs.getPath(ModelComponentArchive.METADATAS_XML);
        return ManifestReader.readMetadataFile(metaPath);
    }

    /**
     * Get the RAMC information from a RAMC extracted to the given directory.
     * 
     * @param ramcDir the directory here the RAMC is extracted.
     * @return the RAMC informations
     * @throws java.io.IOException in case of I/O failure.
     */
    @objid ("d299b8ce-49af-4865-bcb7-87679da892a7")
    public static IModelComponentInfos getRamcDirectoryInfos(Path ramcDir) throws IOException {
        return ManifestReader.readDirectory(ramcDir);
    }

    /**
     * Deploy the RAMC external files to the deployment directory.
     * 
     * @param deploymentPath the deployment directory
     * @param monitor a progress monitor
     * @throws java.io.IOException in case of I/O failure.
     */
    @objid ("8feb26cf-8636-4145-9ac7-052ac70b09d8")
    public void installExportedFiles(Path deploymentPath, IModelioProgress monitor) throws IOException {
        if (this.archive != null) {
            new ExportedFilesDeployer().deployfromArchive(this.archive, deploymentPath, monitor);
        } else {
            new ExportedFilesDeployer().deployFromDirectory(this.dir, deploymentPath, monitor);
        }
    }

    /**
     * Delete deployment files from the deployment directory.
     * 
     * @param deploymentPath the deployment directory to clean
     * @param monitor a progress monitor
     * @throws java.io.IOException in case of I/O failure.
     */
    @objid ("97ed85b5-a230-4531-8aac-252c8fd81249")
    public void removeExportedFiles(Path deploymentPath, IModelioProgress monitor) throws IOException {
        new ExportedFilesCleaner().cleanup(deploymentPath, getInfos(), monitor);
    }

    @objid ("a15769f9-0e64-4a57-9564-536380680743")
    private static class ExportedFilesCleaner {
        @objid ("79267ad7-c72d-450d-ba62-ed4aeaca002d")
        public void cleanup(Path deploymentPath, IModelComponentInfos manifest, IModelioProgress monitor) {
            // Remove files
            SubProgress progress = SubProgress.convert(monitor, 30);
            
            List<Path> dirToClean = new ArrayList<>();
            
            // Clean up files
            // While cleaning files, collect the directory path
            for (ExportedFile f : manifest.getExportedFiles()) {
                Path fileToDelete = deploymentPath.resolve(f.getPath());
                progress.subTask("Deleting file " + fileToDelete.toString());
                // System.out.printf(" - delete file %s\n",
                // fileToDelete.toString());
            
                if (fileToDelete.startsWith(deploymentPath)) {
                    dirToClean.add(fileToDelete.getParent());
                }
                try {
                    Files.deleteIfExists(fileToDelete);
                } catch (IOException e) {
                    // TODO better error handling (no gui allowed)
                    Log.warning(e);
                }
            }
            progress.worked(10);
            
            // Sort by path length, longer first
            Collections.sort(dirToClean, new Comparator<Path>() {
                @Override
                public int compare(Path o1, Path o2) {
                    return Integer.compare(o2.getNameCount(), o1.getNameCount());
                }
            
            });
            progress.worked(5);
            
            for (Path dir : dirToClean) {
                cleanDirectory(deploymentPath, dir.toFile());
            }
        }

        @objid ("866a0dad-6662-44e5-ad2d-357ac35b0362")
        private void cleanDirectory(Path root, File dir) {
            String[] entries = dir.list();
            if (entries != null && entries.length == 0) {
                File parent = dir.getParentFile();
                dir.delete();
            
                if (parent.toPath().startsWith(root)) {
                    cleanDirectory(root, parent);
                }
            }
        }

        @objid ("9824bac3-420e-446f-8627-9c2595301117")
        public ExportedFilesCleaner() {
            // nothing
        }

    }

    @objid ("8eb3692e-9e9c-11e1-a22d-001ec947ccaf")
    private static class ExportedFilesDeployer {
        @objid ("a01e662e-cc36-11e1-87f1-001ec947ccaf")
        public void deployfromArchive(final Path ramcFile, final Path deploymentPath, IModelioProgress monitor) throws IOException {
            SubProgress subMonitor = SubProgress.convert(monitor, 30);
            subMonitor.subTask(GProjectData.I18N.getMessage("MC.Archive.ExtractModelComponent", ramcFile.getFileName()));
            
            try (TmpDir tmpDirectory = new TmpDir(deploymentPath)) {
                // Unzip the archive
                unzipRamc(ramcFile, tmpDirectory.path, subMonitor.newChild(10));
            
                deployFromDirectory(tmpDirectory.path, deploymentPath, subMonitor);
            }
        }

        @objid ("a020c854-cc36-11e1-87f1-001ec947ccaf")
        private void unzipRamc(Path ramcFile, Path ramcDirectory, IModelioProgress monitor) throws IOException {
            SubProgress subMonitor = SubProgress.convert(monitor);
            Unzipper unzipper = new Unzipper();
            unzipper.setProgressLabelPrefix(GProjectData.I18N.getMessage("MC.Archive.ExtractModelComponent", ramcFile.getFileName()));
            unzipper.unzip(ramcFile, ramcDirectory, subMonitor);
        }

        @objid ("a020c859-cc36-11e1-87f1-001ec947ccaf")
        private Element openMetadata(Path ramcPath) throws IOException {
            Path metadataFile = ramcPath.resolve(ModelComponentArchive.METADATAS_XML);
            
            try (InputStream metadataStream = Files.newInputStream(metadataFile)) {
            
                // Create a DocumentBuilderFactory
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);
                dbf.setXIncludeAware(false);
                // dbf.setSchema(schema);
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
            
                // Create a DocumentBuilder
                DocumentBuilder db = dbf.newDocumentBuilder();
            
                // Parse
                // db.setErrorHandler(parserAPIUsage);
            
                Document xmlDoc = db.parse(metadataStream);
            
                return xmlDoc.getDocumentElement();
            } catch (ParserConfigurationException e) {
                throw new IOException("Cannot init Xml parser", e); //$NON-NLS-1$
            } catch (SAXException e) {
                throw new IOException("Sax parsing exception", e); //$NON-NLS-1$
            } catch (FileSystemException e) {
                throw new IOException(GProjectData.I18N.getMessage("MC.Archive.ManifestParsingIOException", FileUtils.getLocalizedMessage(e))); //$NON-NLS-1$
            } catch (IOException e) {
                throw new IOException("MC.Archive.ManifestParsingIOException", e); //$NON-NLS-1$
            }
        }

        @objid ("a020c85d-cc36-11e1-87f1-001ec947ccaf")
        private void installFiles(Path ramcDirectory, Path projectPath, Element metadataElement) throws IOException {
            try {
                NodeList childNodes = metadataElement.getChildNodes();
            
                Path currentFile = null;
                Path destinationFile = null;
                long date = 0;
            
                for (int i = 0; i < childNodes.getLength(); i++) {
                    Node n = childNodes.item(i);
                    if (n instanceof Element) {
                        Element el = (Element) n;
            
                        if ("file".equals(el.getNodeName())) {
                            currentFile = ramcDirectory.resolve(el.getAttribute("archive-name"));
                            destinationFile = Paths.get(projectPath.toString(), el.getAttribute("destination-path"));
                            date = Long.parseLong(el.getAttribute("mtime"));
                            Files.createDirectories(destinationFile.getParent());
                            Files.deleteIfExists(destinationFile);
                            Files.copy(currentFile, destinationFile);
                            Files.setLastModifiedTime(destinationFile, FileTime.from(date, TimeUnit.SECONDS));
                        }
                    }
                }
            } catch (FileSystemException e) {
                throw new IOException(GProjectData.I18N.getMessage("MC.Archive.ManifestParsingIOException", FileUtils.getLocalizedMessage(e))); //$NON-NLS-1$
            } catch (IOException e) {
                throw new IOException(GProjectData.I18N.getMessage("MC.Archive.ManifestParsingIOException", e.getMessage())); //$NON-NLS-1$
            }
        }

        @objid ("75ac0112-5d04-4942-b8d1-bd15aef226fb")
        public ExportedFilesDeployer() {
            // nothing
        }

        @objid ("7bd2b2c3-8138-4de6-9d7c-31b938309edc")
        public void deployFromDirectory(Path fromDirectory, final Path deploymentPath, IModelioProgress aMonitor) throws IOException {
            SubProgress monitor = SubProgress.convert(aMonitor, 20);
            
            monitor.subTask(GProjectData.I18N.getString("MC.Archive.ImportModelComponent"));
            Element metadataElement = openMetadata(fromDirectory);
            monitor.worked(10);
            
            monitor.subTask(GProjectData.I18N.getString("MC.Archive.ImportFiles"));
            installFiles(fromDirectory, deploymentPath, metadataElement);
            monitor.worked(10);
        }

    }

    /**
     * RAMC manifest file reader.
     */
    @objid ("0821054e-cc30-11e1-87f1-001ec947ccaf")
    private static class ManifestReader {
        /**
         * The Standard Modelio metamodel name. Must be modified if the
         * default Modelio metamodel name changes.
         */
        @objid ("ab6a3cc1-818e-414a-867e-ec6b0e1fc7fa")
        private static final String STD_METAMODEL = "Standard";

        @objid ("7414e0ee-cc3e-11e1-87f1-001ec947ccaf")
        public static ModelComponentInfos readArchive(Path ramcPath) throws IOException {
            try (FileSystem fs = ManifestReader.createZipFileSystem(ramcPath)) {
                Path metaPath = fs.getPath(ModelComponentArchive.METADATAS_XML);
                return ManifestReader.readMetadataFile(metaPath);
            }
        }

        @objid ("57b5e752-434a-44f9-952d-60bf468a4ad0")
        public static ModelComponentInfos readDirectory(Path ramcDir) throws IOException {
            Path metaPath = ramcDir.resolve(ModelComponentArchive.METADATAS_XML);
            return ManifestReader.readMetadataFile(metaPath);
        }

        @objid ("0e036f4a-014d-4173-8396-896b0bbd2d83")
        public static ModelComponentInfos readMetadataFile(Path metaPath) throws IOException {
            ModelComponentInfos modelComponentInfos = new ModelComponentInfos();
            
            try {
                // Create a DocumentBuilderFactory
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);
                dbf.setXIncludeAware(false);
                // dbf.setSchema(schema);
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
            
                // Create a DocumentBuilder
                DocumentBuilder db = dbf.newDocumentBuilder();
            
                // Parse
                // db.setErrorHandler(parserAPIUsage);
                Document xmlDoc;
                try (InputStream metadataStream = Files.newInputStream(metaPath)) {
                    xmlDoc = db.parse(metadataStream);
                }
            
                // get the Manifest version
                Node ramcNode = xmlDoc.getDocumentElement();
                Element ramcElement = (Element) ramcNode;
            
                String manifestVersion = ramcElement.getAttribute("manifest-version");
            
                if (ManifestReader.checkManifestVersion(manifestVersion)) {
                    modelComponentInfos.setName(ramcElement.getAttribute("name"));
                    modelComponentInfos.setProvider(ramcElement.getAttribute("provider"));
            
                    int version = ManifestReader.parseInt(ramcElement, "version");
                    int release = ManifestReader.parseInt(ramcElement, "release");
                    int clevel = ManifestReader.parseInt(ramcElement, "clevel");
                   
                    
                    ManifestReader.updateRequiredModelioMmVersion(ramcElement, modelComponentInfos);
            
                    modelComponentInfos.setVersion(new Version(version, release, clevel));
            
                    NodeList childNodes = xmlDoc.getDocumentElement().getChildNodes();
            
                    for (int i = 0; i < childNodes.getLength(); i++) {
                        Node n = childNodes.item(i);
                        if (n instanceof Element) {
                            Element el = (Element) n;
            
                            switch (el.getNodeName()) {
                            case "description":
                                modelComponentInfos.setDescription(el.getTextContent().trim());
                                break;
                            case "dependencies":
                                ManifestReader.updateRamcDependencies(el, modelComponentInfos);
                                break;
                            case "contributors":
                                ManifestReader.updateContributors(el, modelComponentInfos);
                                break;
                            case "metamodels":
                                ManifestReader.updateMetamodelDependencies(el, modelComponentInfos);
                                break;
                            case "file":
                                ManifestReader.readFile(el, modelComponentInfos);
                                break;
                            case "roots":
                                ManifestReader.updateRoots(el, modelComponentInfos);
                                break;
                            default:
                                break;
                            }
                        }
                    }
            
                    ManifestReader.updateModelioVersion(ramcElement, modelComponentInfos);
            
                    return modelComponentInfos;
            
                } else {
                    String ramcName = ramcElement.getAttribute("name");
                    throw new SAXException(String.format("'%s' has invalid '%s' manifest version.", ramcName, manifestVersion));
                }
            } catch (ParserConfigurationException e) {
                throw new IOException("Cannot initialize Xml parser: " + e.getLocalizedMessage(), e); //$NON-NLS-1$
            } catch (SAXException e) {
                throw new IOException("Invalid manifest: " + e.getLocalizedMessage(), e);
            }
        }

        @objid ("a01e661c-cc36-11e1-87f1-001ec947ccaf")
        private static boolean checkManifestVersion(String manifestVersion) {
            // FIXME : should only check Version 2.0
            
            if (manifestVersion == null || manifestVersion.isEmpty()) {
                return false;
            }
            
            try {
                Version version = new Version(manifestVersion);
                return (version.getMajorVersion() <= 3);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @objid ("7414e0e7-cc3e-11e1-87f1-001ec947ccaf")
        private static FileSystem createZipFileSystem(Path path) throws IOException {
            try {
                return FileSystems.newFileSystem(path, ModelComponentArchive.class.getClassLoader());
            } catch (FileSystemNotFoundException | ProviderNotFoundException | FileSystemAlreadyExistsException e) {
                final FileSystemException e2 = new FileSystemException(path.toString(), null, e.getLocalizedMessage());
                e2.initCause(e);
                throw e2;
            } catch (RuntimeException e) {
                final FileSystemException e2 = new FileSystemException(path.toString(), null, e.getLocalizedMessage());
                e2.initCause(e);
                throw e2;
            }
        }

        @objid ("e3a9e394-9761-4112-913e-ce420362d3cb")
        private static Version guessModelioVersionFromMmVersion(Version mm) throws StreamException {
            try {
                return ModelioVersionGuesser.guessFromStandardMmVersion(mm);
            } catch (IllegalArgumentException e) {
                return new Version(0, 0, 0);
            } catch (UnsupportedOperationException e) {
                return new Version(999, 999, 99);
            }
        }

        @objid ("0aebd321-6b31-42f5-85cf-96f358e1d64b")
        private static int parseInt(Element domElement, String attName) throws SAXException {
            String attVal = domElement.getAttribute(attName);
            try {
                return Integer.parseInt(attVal);
            } catch (NumberFormatException e) {
                throw new SAXException(String.format("'%s' node '%s' attribute has invalid '%s' value.", domElement.getNodeName(), attName, attVal), e);
            }
        }

        @objid ("7b845138-85e8-4b2e-8b06-c5b531eb79c9")
        private static Version parseVersion(Element domElement, String attName) throws SAXException {
            String attVal = domElement.getAttribute(attName);
            try {
                return new Version(attVal);
            } catch (NumberFormatException e) {
                throw new SAXException(String.format("'%s' node '%s' Version attribute has invalid '%s' value.", domElement.getNodeName(), attName, attVal), e);
            }
        }

        @objid ("7414e0f3-cc3e-11e1-87f1-001ec947ccaf")
        private static void readFile(Element el, ModelComponentInfos ret) {
            final String archiveName = el.getAttribute("archive-name");
            final Path path = Paths.get(el.getAttribute("destination-path"));
            final FileTime date = FileTime.fromMillis(Long.parseLong(el.getAttribute("mtime")));
            ret.addFile(new ExportedFile(archiveName, path, date));
        }

        @objid ("a01e6618-cc36-11e1-87f1-001ec947ccaf")
        private static void updateContributors(Element contributorsNode, ModelComponentInfos ret) throws SAXException {
            NodeList contributorNodes = contributorsNode.getChildNodes();
            
            for (int i = 0; i < contributorNodes.getLength(); i++) {
                Node contributorNode = contributorNodes.item(i);
                if (contributorNode instanceof Element) {
                    Element contributorElement = (Element) contributorNode;
            
                    String mdacName = contributorElement.getAttribute("name");
                    Version mdacVersion = ManifestReader.parseVersion(contributorElement, "version");
            
                    VersionedItem<Void> adapter = new VersionedItem<>(mdacName, mdacVersion);
            
                    ret.addModule(adapter);
                }
            }
        }

        @objid ("d1fde1c4-fcf4-4e6e-b5fe-26c73e4012d9")
        private static void updateMetamodelDependencies(Element dependenciesNode, ModelComponentInfos ret) throws SAXException {
            NodeList dependencyNodes = dependenciesNode.getChildNodes();
            
            for (int i = 0; i < dependencyNodes.getLength(); i++) {
                Node dependencyNode = dependencyNodes.item(i);
                if (dependencyNode instanceof Element) {
                    Element dependencyElement = (Element) dependencyNode;
            
                    String dependencyName = dependencyElement.getAttribute("name");
                    Version dependencyVersion = ManifestReader.parseVersion(dependencyElement, "version");
            
                    VersionedItem<Void> adapter = new VersionedItem<>(dependencyName, dependencyVersion);
                    ret.addRequiredMetamodelFragment(adapter);
                }
            }
        }

        @objid ("59aa0afe-7335-4d14-8098-152f89837ad3")
        private static void updateModelioVersion(Element ramcElement, ModelComponentInfos modelComponentInfos) throws SAXException {
            String sModelioVersion = ramcElement.getAttribute("modelioVersion");
            if (sModelioVersion != null && !sModelioVersion.isEmpty()) {
                modelComponentInfos.setModelioVersion(ManifestReader.parseVersion(ramcElement, "modelioVersion"));
            } else {
                Version modelioVersion = modelComponentInfos.getRequiredMetamodelFragments()
                        .stream()
                        .filter(m -> m.getName().equals(ManifestReader.STD_METAMODEL))
                        .map(m -> m.getVersion())
                        .max(Version::compareTo)
                        .map(v -> ManifestReader.guessModelioVersionFromMmVersion(v))
                        .orElse(new Version(1, 0, 0));
                modelComponentInfos.setModelioVersion(modelioVersion);
            }
        }

        @objid ("a01e6614-cc36-11e1-87f1-001ec947ccaf")
        private static void updateRamcDependencies(Element dependenciesNode, ModelComponentInfos ret) throws SAXException {
            NodeList dependencyNodes = dependenciesNode.getChildNodes();
            
            for (int i = 0; i < dependencyNodes.getLength(); i++) {
                Node dependencyNode = dependencyNodes.item(i);
                if (dependencyNode instanceof Element) {
                    Element dependencyElement = (Element) dependencyNode;
            
                    String dependencyName = dependencyElement.getAttribute("name");
                    Version dependencyVersion = ManifestReader.parseVersion(dependencyElement, "version");
                    String id = dependencyElement.getAttribute("id");
            
                    VersionedItem<String> adapter = new VersionedItem<>(dependencyName, dependencyVersion, id);
                    ret.addRequiredRamc(adapter);
                }
            }
        }

        /**
         * Convert from old way to specify metamodel version to new one
         * @param ramcElement
         * @param ret
         * @throws SAXException
         */
        @objid ("f870bdd5-862a-4cc8-931b-b686be230954")
        private static void updateRequiredModelioMmVersion(Element ramcElement, ModelComponentInfos ret) throws SAXException {
            String sMmVersion = ramcElement.getAttribute("metamodel");
            
            if (sMmVersion != null && !sMmVersion.isEmpty()) {
                int mmVersion = ManifestReader.parseInt(ramcElement, "metamodel");
                Version stdMmVersion = new Version(1, 0, mmVersion);
                VersionedItem<Void> adapter = new VersionedItem<>(ManifestReader.STD_METAMODEL, stdMmVersion);
                ret.addRequiredMetamodelFragment(adapter);
            }
        }

        @objid ("30dcb793-a4c2-4f81-a6d5-f40ee63f17c0")
        private static void updateRoots(Element rootsNode, ModelComponentInfos infos) {
            NodeList rootNodes = rootsNode.getChildNodes();
            
            for (int i = 0; i < rootNodes.getLength(); i++) {
                Node rootNode = rootNodes.item(i);
                if (rootNode instanceof Element) {
                    Element rootElement = (Element) rootNode;
            
                    String metaclass = rootElement.getAttribute("metaclass");
                    String name = rootElement.getAttribute("name");
                    String uuid = rootElement.getAttribute("uuid");
                    infos.addRoot(new ModelRef(metaclass, uuid, name));
                }
            }
        }

    }

    /**
     * Creates a temporary directory on instantiation and delete it on {@link #close()}
     */
    @objid ("ebc9aad3-0314-4456-a933-05f9f992c6d6")
    private static class TmpDir implements Closeable {
        @objid ("4dd06cf1-24f5-4ee9-9bd4-dfa2e2f23ce1")
         final Path path;

        @objid ("71de2a7d-9ff8-4c54-9549-c24d09063055")
        public TmpDir(Path deploymentPath) throws IOException {
            this.path = Files.createTempDirectory(deploymentPath, ".tmp-deployment");
        }

        @objid ("c0870711-6fc2-497c-9dbe-620f8b269ee5")
        @Override
        public void close() throws IOException {
            if (this.path != null) {
                FileUtils.delete(this.path);
            }
        }

    }

}
