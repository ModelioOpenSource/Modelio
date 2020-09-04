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

package org.modelio.patterns.exporter.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.patterns.exporter.PatternModelAnalysis;
import org.modelio.patterns.model.RuntimePattern;
import org.modelio.patterns.model.information.Category;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.vbasic.files.FileUtils;

@objid ("9a705216-58e0-4b7b-96d4-3e0be7b97ce2")
public class PatternExporter implements IPatternModelAnalyser, IPatternModelCompiler {
    @objid ("bcd4ab13-85e7-46d8-ad2c-42c0095db361")
    @Override
    public void exportPattern(RuntimePattern pattern) throws IOException, JAXBException {
        Path exportDirectory = Files.createTempDirectory("Pattern");
        try {
            JarExporter jarExporter = new JarExporter();
        
            if (jarExporter.generateJavaPattern(pattern.getModelPattern(), exportDirectory) == null) {
                throw new RuntimeException("Generation error");
            }
        
            if (jarExporter.makeJar(exportDirectory) == false) {
                throw new RuntimeException("Compilation error");
            }
        
            exportManifest(pattern, exportDirectory);
        
            exportImages(pattern, exportDirectory);
        
            PackagingManager packager = createPackaging(exportDirectory, "");
            packager.createZipFile(pattern.getPatternPath().toFile());
        
        } finally {
            // Delete temp directory
            if (Files.exists(exportDirectory)) {
                FileUtils.delete(exportDirectory);
            }
        }
    }

    @objid ("13ea3676-cb96-412d-812d-49e0d53903c5")
    private PackagingManager createPackaging(Path packages, String namespace) {
        PackagingManager packager = new PackagingManager();
        
        String localNamespace = namespace;
        for (File content : packages.toFile().listFiles()) {
            if (content.isDirectory()) {
                packager.addAll(createPackaging(content.toPath(), content.getName() + File.separator + localNamespace));
            } else {
                packager.addFileConfiguration(content, localNamespace + content.getName());
            }
        }
        return packager;
    }

    /**
     * Exporting metadatas from a {@link RuntimePattern}.
     * @see org.modelio.patterns.model.information.Pattern
     * 
     * @throws javax.xml.bind.JAXBException when the pattern metadatas are invalid.
     * @throws java.io.IOException when the pattern can't be read.
     * 
     * 
     * @see org.modelio.patterns.model.information.Pattern 
     */
    @objid ("1f03b502-063b-4d74-90e7-f5b683adbeb1")
    private void exportManifest(RuntimePattern pattern, Path exportDirectory) throws IOException, JAXBException {
        Path manifest = exportDirectory.resolve("Manifest.xml");
        
        JAXBContext jc = JAXBContext.newInstance("org.modelio.patterns.model.information");
        Marshaller m = jc.createMarshaller();
        try (final FileOutputStream stream = new FileOutputStream(manifest.toFile())) {
            m.marshal(pattern.getInfos(), stream);
        }
    }

    /**
     * Export all images files referenced in a {@link RuntimePattern}.
     * @throws IOException
     */
    @objid ("739fa872-b622-48c9-a60e-d2a111aff557")
    private void exportImages(RuntimePattern pattern, Path exportDirectory) throws IOException {
        Path image = Paths.get(pattern.getInfos().getImage());
        if (Files.isRegularFile(image)) {
            exportFile(exportDirectory, image);
        }
        
        for (Object sub : pattern.getInfos().getCategoryAndExternalDependencyAndParameter()) {
            if (sub instanceof Category) {
                String sImage = ((Category) sub).getImage();
                Path cimage = Paths.get(sImage);
                if (Files.isRegularFile(cimage)) {
                    exportFile(exportDirectory, cimage);
                }
            }
        }
    }

    @objid ("6d3e6a3b-14d0-4090-96b6-b7d4dd3a9263")
    private void exportFile(Path exportDirectory, Path file) throws IOException {
        Path target = exportDirectory.resolve("res").resolve(file.getFileName());
        Files.createDirectories(target.getParent());
        Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
    }

    @objid ("0b0fa053-a5d6-489a-bffb-3b4e77704af4")
    @Override
    public PatternModelAnalysis runAnalysis(Package modelPattern) {
        // FIXME Should be done by PatternData#initPattern() instead...
        JarExporter jarExporter = new JarExporter();
        return jarExporter.generateJavaPattern(modelPattern, null);
    }

    @objid ("c6ceef6b-5321-4af3-834d-bc77fc88abab")
    private static class PackagingManager {
        @objid ("fb7ee16a-71ba-4cf1-af0c-ef7744fda1a0")
        private List<PackagedResource> configurationList;

        @objid ("616e1d29-e097-4765-b2af-f3d1da6cf91f")
        public PackagingManager() {
            this.configurationList = new ArrayList<>();
        }

        @objid ("a60663b9-cac5-4472-8b46-efe3712356c2")
        public void addFileConfiguration(File file, String name) {
            this.configurationList.add(new PackagedResource(file, name));
        }

        @objid ("97d81596-bb43-4f48-8706-5de09d852ad1")
        public List<PackagedResource> getResources() {
            return this.configurationList;
        }

        @objid ("1b7c9346-7279-45b5-b832-29c65ad5540c")
        public void addAll(PackagingManager other) {
            this.configurationList.addAll(other.getResources());
        }

        @objid ("906d0b29-6914-4f7f-acf5-ff831b47c561")
        public void createZipFile(File target) {
            target.getParentFile().mkdirs();
            target.delete();
            byte[] buf = new byte[1024];
            try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target))) {
                for (PackagedResource entry : this.configurationList) {
                    try (FileInputStream in = new FileInputStream(entry.resourceFile)) {
                        out.putNextEntry(new ZipEntry(entry.packagingPath));
                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        out.closeEntry();
                        in.close();
                    }
                }
                out.close();
            } catch (IOException e) {
                MessageDialog.openError(Display.getDefault().getActiveShell(), Patterns.I18N.getString("Gui.ErrorTitle"),
                        e.getMessage());
            }
        }

        @objid ("03741d8a-51c2-41e8-90dd-703f883b70d2")
        public class PackagedResource {
            @objid ("710597b6-6eb6-427a-baad-c434b07b8916")
            public String packagingPath;

            @objid ("4f8059d2-c814-4439-8b10-ec8294f2c24b")
            public File resourceFile;

            @objid ("5e0040d0-fe4a-4854-87a5-5623e7732b59")
            public PackagedResource(File resourceFile, String packagingPath) {
                this.resourceFile = resourceFile;
                this.packagingPath = packagingPath;
            }

        }

    }

}
