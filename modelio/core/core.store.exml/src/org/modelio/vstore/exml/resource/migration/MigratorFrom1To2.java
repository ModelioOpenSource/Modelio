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

package org.modelio.vstore.exml.resource.migration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorReader;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptorWriter;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;
import org.modelio.vstore.exml.common.RepositoryVersions;
import org.modelio.vstore.exml.common.model.ExmlTags;
import org.modelio.vstore.exml.plugin.VStoreExml;
import org.modelio.vstore.exml.resource.ExmlFileAccess;
import org.modelio.vstore.exml.resource.ExmlRepositoryGeometry1;
import org.modelio.vstore.exml.resource.ExmlRepositoryGeometry2;
import org.modelio.vstore.exml.resource.IExmlRepositoryGeometry;

/**
 * EXML repository format migrator from version 1 to 2.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("9148f9a2-c76c-47d6-bf6c-ff3aefa69a58")
public class MigratorFrom1To2 {
    @objid ("112c71c6-cd82-46b8-872f-bb6059e1aa21")
    private ExmlFileAccess from;

    @objid ("c0b94cad-a035-4555-866a-74cc505c5cae")
    private ExmlFileAccess to;

    @objid ("4ce6cf2c-046d-4bbe-a08f-465a928dcdbf")
    private final Path repositoryPath;

    @objid ("d75c9553-865d-4879-afd3-69f667d3dd67")
    private final MofMetamodel metamodel;

    @objid ("1176a95f-716e-4b9e-b9d4-e511af97e460")
    private final PrintWriter logger;

    @objid ("24db66d9-9939-4f8c-99ad-d85219f93311")
    private Collection<Path> createdDirectories = new HashSet<>();

    /**
     * @param repositoryPath the repository path
     * @param metamodel the metamodel
     */
    @objid ("9526eeaa-8233-4b7e-9be2-f09fe0cf9312")
    public MigratorFrom1To2(Path repositoryPath, MMetamodel metamodel, PrintWriter logger) {
        this.repositoryPath = repositoryPath;
        this.metamodel = new MofMetamodel();
        this.logger = logger;
        this.from = new ExmlFileAccess(repositoryPath.toFile(), new ExmlRepositoryGeometry1());
        this.to = new ExmlFileAccess(repositoryPath.toFile(), new ExmlRepositoryGeometry2());
        
        this.metamodel.copy(metamodel);
    }

    /**
     * Run the migration.
     * 
     * @param monitor a progress monitor
     * @throws java.io.IOException on failure.
     */
    @objid ("d2f3db64-7979-437f-871b-6155e055b64d")
    public void execute(IModelioProgress monitor) throws IOException {
        SubProgress mon = SubProgress.convert(monitor, 6);
        begin(mon.newChild(1));
        
        File metamodelDescriptorFile = this.from.getMetamodelDescriptorFile();
        if (metamodelDescriptorFile.isFile()) {
            MetamodelDescriptor mmDesc = MetamodelDescriptorReader.readFrom(Files.newInputStream(metamodelDescriptorFile.toPath()), metamodelDescriptorFile.toString());
            this.metamodel.merge(mmDesc);
        }
        
        createMissingDirectories(mon.newChild(1));
        moveAllResources(mon.newChild(1));
        deleteObsoleteDirectories(mon.newChild(1));
        
        new FilesRegenerator(this.to, this.metamodel, f -> fileModified(f)).run(mon.newChild(1));
        
        saveFormatVersion(mon.newChild(1));
        
        commit(mon.newChild(1));
    }

    /**
     * Called at the end.
     * <p>
     * Does nothing by default.
     * 
     * @param monitor a progress monitor.
     * @throws java.io.IOException on failure.
     */
    @objid ("c79a59a3-c776-4bc3-91a4-6f7259a9eb5e")
    protected void commit(IModelioProgress monitor) throws IOException {
        // nothing by default
    }

    /**
     * Called at the beginning.
     * <p>
     * Does nothing by default.
     * 
     * @param monitor a progress monitor.
     * @throws java.io.IOException on failure.
     */
    @objid ("065e624f-e7ee-443b-b6b1-eea9401f8de4")
    protected void begin(IModelioProgress monitor) throws IOException {
        // nothing by default
    }

    @objid ("05af947c-9e5b-4bc2-984e-1c18472c3daa")
    private void saveFormatVersion(IModelioProgress monitor) throws IOException {
        Path filePath = getFormatVersionFilePath();
        try (OutputStream out = Files.newOutputStream(filePath)) {
        
            RepositoryVersions format = new RepositoryVersions(RepositoryVersions.CURRENT_FORMAT, this.metamodel);
            format.write(out);
        }
        
        filePath = getMetamodelDescriptorFilePath();
        try (OutputStream out = Files.newOutputStream(filePath)) {
            MetamodelDescriptor desc = this.metamodel.serialize();
            new MetamodelDescriptorWriter().write(desc, out);
        }
    }

    @objid ("e4a4e33e-f3de-4fa6-8a74-10e575dfaab5")
    private void createMissingDirectories(IModelioProgress monitor) throws IOException {
        Collection<String> newDirs = this.to.getGeometry().getInitialDirectories(this.metamodel);
        
        SubProgress mon = SubProgress.convert(monitor, newDirs.size());
        
        for (String newDir : newDirs) {
            Path resolvedNewDir = this.repositoryPath.resolve(newDir);
            if (!Files.isDirectory(resolvedNewDir)) {
                createNewDirectory(resolvedNewDir);
            }
            mon.worked(1);
        }
    }

    @objid ("3d4ce3ba-417c-4a6e-b833-640d00b80f5c")
    private void deleteObsoleteDirectories(IModelioProgress monitor) throws IOException {
        Collection<File> newDirs = this.to.getInitialDirectories(this.metamodel);
        
        Path modelDir = this.repositoryPath.resolve(this.from.getGeometry().getModelPath());
        Collection<Path> oldDirs = Files.list(modelDir).collect(Collectors.toList());
        
        SubProgress mon = SubProgress.convert(monitor, oldDirs.size());
        
        int i = 0;
        int count = oldDirs.size();
        for (Path oldDir : oldDirs) {
            monitor.subTask(VStoreExml.I18N.getMessage("MigratorFrom1To2.deletingDirectories.progress", i, count));
        
            if (!newDirs.contains(oldDir.toFile()) && !this.createdDirectories.contains(oldDir)) {
                deleteDirectory(oldDir);
            }
            mon.worked(1);
        }
    }

    /**
     * @param oldDir the directory path relative to the repository root.
     * @return true if a directory was deleted, false if it didn't exist.
     * @throws java.io.IOException on failure
     */
    @objid ("1d332867-146d-4330-9f02-6c4c529a8590")
    protected boolean deleteDirectory(Path oldDir) throws IOException {
        // The directory should be empty
        try {
            return Files.deleteIfExists(oldDir);
        } catch (DirectoryNotEmptyException e) {
            // Add debugging infos
            try {
                String content = Files.list(oldDir).map(d -> "   - " + d.toString()).collect(Collectors.joining("\n", "Directory content:\n", ""));
                e.addSuppressed(new Throwable(content));
            } catch (IOException e2) {
                e.addSuppressed(e2);
            }
            throw e;
        }
    }

    /**
     * Create a new directory
     * 
     * @param newDir the directory path relative to the repository root.
     * @throws java.io.IOException on failure
     */
    @objid ("d6a672b2-43cf-423b-b9e3-5c1cc77f5ca0")
    protected void createNewDirectory(Path newDir) throws IOException {
        Files.createDirectories(newDir);
        this.createdDirectories.add(newDir);
    }

    @objid ("78dca872-b003-4af3-a169-fffd870b5881")
    private void moveAllResources(IModelioProgress aMonitor) throws IOException {
        SubProgress monitor = SubProgress.convert(aMonitor, VStoreExml.I18N.getMessage("MigratorFrom1To2.moveAllResources.task"), 10);
        
        forEachExmlFile(this.from, new IFileOp() {
            private int count = 0;
            private int movedCount = 0;
            private final ExmlFileAccess fromAccess = MigratorFrom1To2.this.from;
            private final ExmlFileAccess toAccess = MigratorFrom1To2.this.to;
        
            @Override
            public void run(Path fromPath) throws IOException {
                File fromFile = fromPath.toFile();
        
                MRef ref = readFixedRef(this.fromAccess, fromFile);
                if (ref != null) {
                    boolean isLocal = fromFile.getPath().endsWith(IExmlRepositoryGeometry.EXT_LOCAL_EXML);
                    Path targetFile = isLocal ? this.toAccess.getLocalExmlFile(ref).toPath() : this.toAccess.getExmlFile(ref).toPath();
        
                    if (!fromPath.equals(targetFile)) {
                        ensureDirectoryExist(targetFile.getParent());
                        moveFile(fromPath, targetFile);
                        this.movedCount++;
                    }
                }
                this.count++;
        
                monitor.worked(1);
                monitor.setWorkRemaining(10);
                monitor.subTask(VStoreExml.I18N.getMessage("MigratorFrom1To2.moveAllResources.progress", this.count, this.movedCount));
        
            }
        });
    }

    @objid ("153fd3df-18ec-462d-9fe0-8c548cbde637")
    protected void moveFile(Path fromPath, Path targetPath) throws IOException {
        Files.move(fromPath, targetPath);
    }

    @objid ("b8c0a0e1-376b-47fd-ba12-55fc24926b19")
    protected Path getFormatVersionFilePath() {
        return this.repositoryPath.resolve(IExmlRepositoryGeometry.FORMAT_VERSION_PATH);
    }

    @objid ("8399d4c3-9c76-408a-9b5d-f5ba0973cb87")
    protected Path getRepositoryPath() {
        return this.repositoryPath;
    }

    @objid ("6492ceaa-da20-441f-8acb-a1a5a04bb330")
    protected final PrintWriter getLogger() {
        return this.logger;
    }

    @objid ("b5538708-d2ff-4681-89fd-4a70c3bb2fa5")
    protected final MMetamodel getMetamodel() {
        return this.metamodel;
    }

    @objid ("beb45a20-df94-4068-aa32-b6c7418704be")
    private MRef readFixedRef(ExmlFileAccess reader, File fromFile) throws IOException {
        MRef ret = reader.readRefFromFile(fromFile);
        if (ret != null) {
            MClass mc = this.metamodel.getMClass(ret.mc);
            if (mc == null) {
                this.logger.printf("  warn: %s: Unknown metaclass for '%s'.\n", fromFile, ret);
            } else if (!mc.getQualifiedName().equals(ret.mc)) {
                this.logger.printf("  warn: %s: metaclass fixed to '%s' for '%s'.\n", fromFile, mc.getQualifiedName(), ret);
                ret.mc = mc.getQualifiedName();
            }
        }
        return ret;
    }

    @objid ("c0cde9e5-3f56-40ca-84ca-493eef366803")
    private void ensureDirectoryExist(Path dirPath) throws IOException {
        if (!Files.isDirectory(dirPath)) {
            createNewDirectory(dirPath);
        }
    }

    @objid ("7bca0e32-c0da-4b61-a1bc-6091f63df9f2")
    protected final Path getMetamodelDescriptorFilePath() {
        return this.repositoryPath.resolve(this.to.getGeometry().getMetamodelDescriptorPath());
    }

    @objid ("03555c04-c0af-43a1-8fea-f590d23a4fbc")
    protected static final void forEachExmlFile(ExmlFileAccess access, IFileOp op) throws IOException {
        try (DirectoryStream<Path> classDs = Files.newDirectoryStream(access.getModelDirectory().toPath(), entry -> Files.isDirectory(entry))) {
            for (Path dir : classDs) {
                try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir, "*.exml");) {
                    for (Path fromPath : dirStream) {
                        op.run(fromPath);
                    }
                }
            }
        }
    }

    /**
     * Report a file as modified.
     * 
     * @param modifiedFile the modified file
     * @throws java.io.IOException on hook failure.
     */
    @objid ("55a58071-8b5b-4928-b8b1-05584e27396d")
    protected void fileModified(Path modifiedFile) throws IOException {
        // nothing by default
    }

    /**
     * EXML file regenerator that converts short metaclass names to qualified metaclass names.
     * @author cma
     * @since 3.7
     */
    @objid ("424d2bc4-ed3a-48db-8645-567c9adc53d6")
    protected static class FilesRegenerator {
        @objid ("1e517a71-8d5c-4fcc-a567-530981bb5062")
        private long count;

        @objid ("4d5e034d-0b46-4fbe-8d37-b94250c307de")
        private static final Collection<String> tagsToConvert = new HashSet<>(Arrays.asList(
    			ExmlTags.TAG_COMPID, 
    			ExmlTags.TAG_CMSNODE_PID,
    			ExmlTags.TAG_DEPS_EXTID, 
    			ExmlTags.TAG_FOREIGNID, 
    			ExmlTags.TAG_ID, 
    			ExmlTags.TAG_PID));

        @objid ("d0f9465b-1aaf-42f6-b36a-a7c57635a2e4")
        private final ExmlFileAccess exmlAccess;

        @objid ("e3bea488-ff49-4917-b692-45e61aec0dda")
        private final MofMetamodel metamodel;

        @objid ("ba8fc9c2-a0c7-46be-b82f-168e286927b3")
        private final IFileOp fileModifiedHook;

        @objid ("f6bb9024-0adb-4a17-a39b-810cfb797bfc")
        private final XMLEventFactory eventFactory;

        @objid ("4628f041-1e96-4227-aece-fd0c25ce0fbf")
        public FilesRegenerator(ExmlFileAccess exmlAccess, MofMetamodel metamodel, IFileOp fileModifiedHook) {
            super();
            this.exmlAccess = exmlAccess;
            this.metamodel = metamodel;
            this.fileModifiedHook = fileModifiedHook;
            this.eventFactory = XMLEventFactory.newInstance();
        }

        @objid ("5f1710b4-4241-4d91-9475-7ddb9853c0a4")
        public void run(IModelioProgress amonitor) throws IOException {
            SubProgress monitor = SubProgress.convert(amonitor, 5);
            
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            
            forEachExmlFile(this.exmlAccess, exmlFile -> {
                Path tmpFile = Files.createTempFile("", ".exml");
            
                try(InputStream is = Files.newInputStream(exmlFile);
                        OutputStream os = Files.newOutputStream(tmpFile);) 
                {
                    XMLEventReader evReader = inputFactory.createXMLEventReader(exmlFile.toString(), is);
                    XMLEventWriter evWriter = outputFactory.createXMLEventWriter(os, StandardCharsets.UTF_8.name());
                    
                    try (XmlCloser c1 = evReader::close; XmlCloser c2 = evWriter::close;) 
                    {
                        rewriteResourceContent(evReader, evWriter);
                    }
                } catch (XMLStreamException e) {
                    throw new IOException(e.getLocalizedMessage(), e);
                }
            
                // replace original by rewritten content
                Files.copy(tmpFile, exmlFile, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                this.fileModifiedHook.run(exmlFile);
            
                Files.delete(tmpFile);
                monitor.worked(1);
                monitor.setWorkRemaining(5);
            
                monitor.subTask(VStoreExml.I18N.getMessage("FilesRegenerator.progress", ++this.count));
            });
        }

        @objid ("3af8989b-3359-47b7-a429-a649720e3b56")
        private void rewriteResourceContent(XMLEventReader reader, XMLEventWriter writer) throws XMLStreamException {
            while(reader.hasNext()) {
                XMLEvent event = (XMLEvent) reader.next();
                if (event.getEventType() == XMLStreamConstants.START_ELEMENT 
                        && tagsToConvert.contains(event.asStartElement().getName().getLocalPart())) {
                    event = convertIdTag(event.asStartElement());
                }
                writer.add(event);
            }
        }

        @objid ("5a4d5959-73fc-4164-ba4a-6759b7a075f0")
        private XMLEvent convertIdTag(StartElement event) {
            List<Attribute> atts = new ArrayList<>(5);
            
            for (Iterator<Attribute> it = event.getAttributes(); it.hasNext();) {
                Attribute att = it.next();
                if (att.getName().getLocalPart().equals(ExmlTags.ATT_ID_MC)) {
                    String oldMc = att.getValue();
                    SmClass mc = this.metamodel.getMClass(oldMc);
                    if (mc == null) {
                        final Location loc = event.getLocation();
                        Log.warning("%s: Unknow '%s' metaclass found at %s:%d:%d", getClass().getSimpleName(), oldMc, loc.getSystemId(), loc.getLineNumber(), loc.getColumnNumber());
                        atts.add(att);
                    } else if (mc.getQualifiedName().equals(oldMc)) {
                        atts.add(att);
                    } else {
                        // replace by qualified metaclass name
                        atts.add(this.eventFactory.createAttribute(att.getName(), mc.getQualifiedName()));
                    }
                } else {
                    atts.add(att);
                }
            }
            return this.eventFactory.createStartElement(event.getName(), atts.iterator(), event.getNamespaces());
        }

    }

    @objid ("2f338e0a-96ed-4e98-80f9-d4317aacbc8d")
    @FunctionalInterface
    public interface IFileOp {
        @objid ("3fd2242c-72f4-433e-bcdc-ebebb5b96eb9")
        void run(Path p) throws IOException;

    }

    /**
     * {@link AutoCloseable} function to close an {@link XMLEventReader} or {@link XMLEventWriter}.
     * @author cma
     * @since 3.7
     */
    @objid ("919d129a-8fae-48d7-b0ce-981b91b554ce")
    @FunctionalInterface
    private interface XmlCloser extends AutoCloseable {
        @objid ("817f247d-79a6-43b8-8b61-cef1cab25303")
        @Override
        void close() throws XMLStreamException;

    }

}
