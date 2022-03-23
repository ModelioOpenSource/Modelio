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
package org.modelio.gproject.data.module;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.modelio.gproject.data.module.jaxbv1.JxbModule;
import org.modelio.gproject.data.module.jaxbv2.Jxbv2Module;
import org.modelio.gproject.data.module.migration.Migrator;

/**
 * Service class to load or save a module JAXB model to a file.
 */
@objid ("60b7d124-f1fc-11e1-8543-001ec947ccaf")
public class JaxbModelPersistence {
    /**
     * Load a JAXB model from a jmdac file.
     * @param modulePath the .jmdac file path
     * @return the JAXB module
     * @throws IOException in case of failure.
     */
    @objid ("53e607c5-6c08-4c07-94f1-4d3fc8b580d3")
    public static Jxbv2Module loadJaxbModelFromJmdac(Path modulePath) throws IOException {
        URI uri = modulePath.toUri();
        try {
            uri = new URI("jar:" + uri.getScheme(), uri.getRawSchemeSpecificPart(), uri.getRawFragment());
        
            try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.<String, Object> emptyMap())) {
                Path moduleDir = findModuleDir(fs);
                Path moduleXmlFile = moduleDir.resolve("module.xml");
                if (Files.isRegularFile(moduleXmlFile)) {
                    return JaxbModelPersistence.loadJaxbModel(moduleXmlFile);
                } else {
                    throw new FileNotFoundException("module.xml not found in '" + moduleDir.toUri().toString() + "'");
                }
            }
        } catch (URISyntaxException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        }
        
    }

    /**
     * Load a JAXB model
     * @param moduleXmlFile the module.xml file path
     * @return the JAXB module
     * @throws IOException in case of failure.
     */
    @objid ("b38b2cc5-f27f-11e1-8543-001ec947ccaf")
    public static Jxbv2Module loadJaxbModel(final Path moduleXmlFile) throws IOException {
        try {
            return loadJaxbModelV2(moduleXmlFile);
        } catch (JAXBException e) {
            try {
                return loadJaxbModelV1(moduleXmlFile);
            } catch (JAXBException e1) {
                throw new IOException(e);
            }
        }
        
    }

    /**
     * Save the JAXB model to a file.
     * @param module the JAXB module model to save.
     * @param moduleXmlFile the module.xml file path
     * @throws IOException in case of failure.
     */
    @objid ("82033481-f366-11e1-85f6-002564c97630")
    public static void saveJaxbModel(final JxbModule module, final Path moduleXmlFile) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(moduleXmlFile)) {
            String packageName = JxbModule.class.getPackage().getName();
            JAXBContext jaxbContext = JAXBContext.newInstance(packageName);
            Marshaller marshaller = jaxbContext.createMarshaller();
        
            marshaller.marshal(module, outputStream);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
        
    }

    @objid ("b9889f13-6e54-41da-9acb-51d8584999af")
    private static Path findModuleDir(FileSystem fs) throws IOException {
        for (Path root : fs.getRootDirectories()) {
            try (DirectoryStream<Path> content = Files.newDirectoryStream(root)) {
                for (Path p : content) {
                    Path moduleFile = p.resolve("module.xml");
                    if (Files.isRegularFile(moduleFile))
                        return p;
                }
            }
        }
        throw new NoSuchFileException("/*/module.xml");
        
    }

    @objid ("c1bb102d-6b96-4c07-9012-14b93e046431")
    public static void saveJaxbModel(final Jxbv2Module module, final Path moduleXmlFile) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(moduleXmlFile)) {
            String packageName = Jxbv2Module.class.getPackage().getName();
            JAXBContext jaxbContext = JAXBContext.newInstance(packageName);
            Marshaller marshaller = jaxbContext.createMarshaller();
        
            marshaller.marshal(module, outputStream);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
        
    }

    /**
     * Test arg[1] is the module xml file to process
     * @param args
     */
    @objid ("6b8e1cd1-7963-423b-8055-80dde3b8f6cf")
    public static void main(String[] args) {
        String s = "/home/phv/tmp/module.xml";
        try {
            Jxbv2Module module = loadJaxbModel(Paths.get(s));
            saveJaxbModel(module, Paths.get(s + ".v2"));
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @objid ("0ec60d78-3c53-46fa-b761-3dbc16f619db")
    private static Jxbv2Module loadJaxbModelV1(final Path moduleXmlFile) throws JAXBException, IOException {
        try (InputStream inputStream = Files.newInputStream(moduleXmlFile)) {
            String packageName = JxbModule.class.getPackage().getName();
            JAXBContext jaxbContext = JAXBContext.newInstance(packageName);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            JxbModule moduleV1 = (JxbModule) unmarshaller.unmarshal(inputStream);
            return new Migrator().migrate(moduleV1);
        }
        
    }

    @objid ("1142fe68-5304-4cf9-a636-d8e4522c6020")
    private static Jxbv2Module loadJaxbModelV2(final Path moduleXmlFile) throws JAXBException, IOException {
        try (InputStream inputStream = Files.newInputStream(moduleXmlFile)) {
            String packageName = Jxbv2Module.class.getPackage().getName();
            JAXBContext jaxbContext = JAXBContext.newInstance(packageName);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Jxbv2Module module = (Jxbv2Module) unmarshaller.unmarshal(inputStream);
            return module;
        }
        
    }

}
