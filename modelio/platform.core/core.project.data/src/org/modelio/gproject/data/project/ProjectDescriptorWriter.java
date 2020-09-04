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

package org.modelio.gproject.data.project;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.todo.InstallModuleDescriptor;
import org.modelio.gproject.data.project.todo.RemoveModuleDescriptor;
import org.modelio.gproject.data.project.todo.TodoActionDescriptor;
import org.modelio.gproject.data.project.todo.TodoDescriptor;
import org.modelio.gproject.data.project.todo.UpdateModuleDescriptor;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.xml.CloseableXMLStreamWriter;

/**
 * Serialize a {@link ProjectDescriptor} in a XML file or an {@link OutputStream}.
 * <p>
 * The output may be encoded if the {@value #CONF_ENCRYPT_PROP} property
 * is set.
 */
@objid ("5ce5d428-b006-4b5e-9d44-bbb936e1de46")
public class ProjectDescriptorWriter {
    /**
     * File name in the same directory as the configuration file that may contain the
     * encoding of the written project.conf file.
     */
    @objid ("60039d75-073d-43af-9012-8aa7822cf00c")
     static final String CONF_ENCRYPT_FILE = "project.conf.encrypt";

    /**
     * Project property to set in order to encode the file content.
     * Only "base64" is supported for the moment.
     */
    @objid ("fc351e30-461d-4c32-a376-1e569143ad13")
     static final String CONF_ENCRYPT_PROP = "project.conf.encrypt";

    @objid ("6de7ed36-6d2c-48d0-91f7-13363e54e8de")
    private boolean withScope = true;

    /**
     * if true, authentication data credentials will be written for shared scoped authentications.
     * @since 4.0.0
     */
    @objid ("9741823b-0dc9-47a3-a4ac-a3b53fd7e82a")
    private boolean withSharedAuthCredentials;

    @objid ("f5fc95ef-d84d-45c5-9888-e9a796eda3a7")
    private XMLStreamWriter out;

    @objid ("d7dbaec3-197b-4add-8cda-4f085d5e0e8c")
    private Path projectPath;

    /**
     * Set whether the scope is saved.
     * <p>
     * The scope is saved by default.
     * 
     * @param withScope <code>true</code> to save the scope, <code>false</code> to ignore it.
     */
    @objid ("24ad0eec-f611-4616-9c8b-8d5b5e812415")
    public void setWithScope(boolean withScope) {
        this.withScope = withScope;
    }

    /**
     * Set whether authentication data credentials will be written for shared scoped authentications.
     * 
     * @param writeSharedAuthCredentials true to write shared authentication credentials
     * @return this instance to chain calls.
     * @since 4.0.0
     */
    @objid ("7d09d543-b867-4428-9d0c-b391fee03d6e")
    public ProjectDescriptorWriter withSharedAuthCredentials(boolean writeSharedAuthCredentials) {
        this.withSharedAuthCredentials = writeSharedAuthCredentials;
        return this;
    }

    /**
     * Save the project descriptor to a file.
     * <p>
     * Fragments URI are made relative from the project path when possible.
     * 
     * @param d the descriptor to save
     * @throws java.io.IOException in case of failure.
     */
    @objid ("d34e36af-fbe6-479c-9088-8163657da49c")
    public void write(final ProjectDescriptor d) throws IOException {
        final Path projectFilePath = d.getProjectFileStructure().getProjectConfFile();
        this.projectPath = projectFilePath.getParent();
        
        OutputStream os = Files.newOutputStream(projectFilePath);
        
        // Setup encoding
        final String encoding = d.getProperties().getValue(ProjectDescriptorWriter.CONF_ENCRYPT_PROP, "");
        if (encoding.equals("base64")) {
            os = new Base64OutputSream(os);
            // Write encoding in a parallel file
            Files.write(this.projectPath.resolve(ProjectDescriptorWriter.CONF_ENCRYPT_FILE), encoding.getBytes());
        } else {
            Files.deleteIfExists(this.projectPath.resolve(ProjectDescriptorWriter.CONF_ENCRYPT_FILE));
        }
        
        // Write configuration
        try (final BufferedOutputStream os2 = new BufferedOutputStream(os);) {
            write(d, os2);
        }
    }

    /**
     * Write the project descriptor to an output stream.
     * 
     * @param d the descriptor to save
     * @param os the stream to write the project descriptor to.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("b79c9aec-7b98-4dc1-9b23-759a90d579dc")
    public void write(final ProjectDescriptor d, final OutputStream os) throws IOException {
        try (CloseableXMLStreamWriter cout = new CloseableXMLStreamWriter(os, true)) {
            this.out = cout.getW();
            this.out.writeStartDocument(StandardCharsets.UTF_8.name(), "1.0");
            this.out.writeComment("GENERATED FILE, PLEASE DO NOT EDIT!!!");
        
            writeProjectDescriptor(d);
        
            this.out.writeEndDocument();
        
        } catch (FactoryConfigurationError e) {
            throw new IOException(e);
        } catch (XMLStreamException e) {
            throw new IOException(e);
        } finally {
            this.out = null;
            this.projectPath = null;
        }
    }

    @objid ("6bd0cd88-3d3a-4ac7-9e58-548c9b66f8cf")
    private URI getRelativeUri(URI uri) {
        if (uri == null) {
            return null;
        } else if (this.projectPath == null) {
            return uri;
        } else {
            return this.projectPath.toUri().relativize(uri);
        }
    }

    /**
     * Workaround Oracle database changing empty strings to NULL pointer.
     * 
     * @param value the string value to check
     * @return the value if not <i>null</i>. Empty string if <i>null</i>.
     */
    @objid ("00a9d660-24ec-48db-9822-8e7c9ece2154")
    private String nullToEmpty(String value) {
        if (value == null) {
            // Workaround Oracle database changing empty strings to NULL pointer.
            return "";
        } else {
            return value;
        }
    }

    @objid ("e0471547-b256-4045-b7dc-047ddd26ca18")
    private static String toStringOrEmpty(Object o) {
        if (o == null) {
            return "";
        } else {
            return o.toString();
        }
    }

    @objid ("f65ec1a6-b5db-46bb-b639-3abcda192552")
    private void writeAuth(AuthDescriptor auth) throws XMLStreamException {
        if (auth != null && auth.getScheme() != null) {
            DefinitionScope authScope = auth.getScope();
            boolean writeCreds = this.withSharedAuthCredentials && authScope==DefinitionScope.SHARED;
        
            this.out.writeStartElement("auth");
            this.out.writeAttribute("scheme", auth.getScheme());
        
            writeScope(authScope);
            for (Entry<String, String> e : auth.getData().serialize(writeCreds).entrySet()) {
                writePropValueTag("prop", "name", "value", e.getKey(), e.getValue(), null);
            }
        
            this.out.writeEndElement();
        } else {
            this.out.writeStartElement("auth");
            this.out.writeAttribute("scheme", AuthDescriptor.AUTH_TYPE_ASK);
            writeScope(DefinitionScope.LOCAL);
            this.out.writeEndElement();
        }
    }

    @objid ("86ad358c-380d-4937-9631-5897174d52f6")
    private void writeFragmentDescriptor(final FragmentDescriptor f) throws XMLStreamException {
        this.out.writeStartElement("fragment");
        this.out.writeAttribute("id", f.getId());
        if (f.getScope() != null) {
            URI uri = getRelativeUri(f.getUri());
            this.out.writeAttribute("uri", uri == null ? "" : uri.toString());
            this.out.writeAttribute("type", f.getType().toString());
            writeScope(f.getScope());
        }
        
        writeAuth(f.getAuthDescriptor());
        writeProperties(f.getProperties());
        
        this.out.writeEndElement();
    }

    @objid ("9eabebb5-d8c3-4d0f-9f9b-2eb328b1aede")
    private void writeModuleDescriptor(final ModuleDescriptor m) throws XMLStreamException {
        writeModuleDescriptor(m, "module");
    }

    @objid ("54ed463d-6149-4f5d-9fe4-d2bbca75a63e")
    private void writeModuleDescriptor(final ModuleDescriptor m, String tagName) throws XMLStreamException {
        this.out.writeStartElement(tagName);
        this.out.writeAttribute("name", m.getName());
        if (m.getScope() != null) {
            this.out.writeAttribute("version", m.getVersion().toString());
            if (m.getArchiveLocation() != null) {
                this.out.writeAttribute("archive", m.getArchiveLocation().toString());
            }
            this.out.writeAttribute("activated", Boolean.toString(m.isActivated()));
            writeScope(m.getScope());
        }
        
        writeAuth(m.getAuthDescriptor());
        writeProperties(m.getParameters());
        
        this.out.writeEndElement();
    }

    @objid ("cae6a381-15fa-44f9-b6fd-421e0e25abb8")
    private void writeProjectDescriptor(final ProjectDescriptor projectDescriptor) throws XMLStreamException {
        this.out.writeStartElement("project");
        this.out.writeAttribute("name", projectDescriptor.getName());
        this.out.writeAttribute("type", toStringOrEmpty(projectDescriptor.getType()));
        this.out.writeAttribute("version", String.valueOf(projectDescriptor.getFormatVersion()));
        this.out.writeAttribute("projectSpaceVersion", String.valueOf(projectDescriptor.getProjectSpaceVersion()));
        this.out.writeAttribute("modelioVersion", toStringOrEmpty(projectDescriptor.getModelioVersion()));
        
        boolean writeProjectPath = writeProjectPath(projectDescriptor);
        if (writeProjectPath) {
            this.out.writeAttribute("path", projectDescriptor.getProjectFileStructure().getProjectPath().toString());
        }
        
        if (!ProjectType.LOCAL.toString().equals(projectDescriptor.getType())) {
            // Write remote location
            this.out.writeAttribute("remote", projectDescriptor.getRemoteLocation());
        }
        
        // Write authentication data
        writeAuth(projectDescriptor.getAuthDescriptor());
        
        // Write fragments
        for (FragmentDescriptor f : projectDescriptor.getFragments()) {
            writeFragmentDescriptor(f);
        }
        
        for (ModuleDescriptor m : projectDescriptor.getModules()) {
            writeModuleDescriptor(m);
        }
        
        writeResources(projectDescriptor);
        
        writeProperties(projectDescriptor.getProperties());
        
        writeTodo(projectDescriptor.getTodo());
        
        this.out.writeEndElement();
    }

    @objid ("4334917b-dd35-4da8-b51c-66a7bef1ca8b")
    private boolean writeProjectPath(final ProjectDescriptor projectDescriptor) {
        if (projectDescriptor.getProjectFileStructure() == null || projectDescriptor.getProjectFileStructure().getProjectPath() == null || this.projectPath == null) {
            return false;
        }
        return !projectDescriptor.getProjectFileStructure().getProjectPath().startsWith(this.projectPath);
    }

    /**
     * Write a key=value style DOM element.
     * <p>
     * If the value is short it is written as a DOM attribute. In the other case it is a DOM TEXT.
     * 
     * @param tagName the DOM element TAG name
     * @param keyAtt the key DOM attribute name
     * @param valueAtt the value DOM attribute name
     * @param key the property key
     * @param value the property value
     * @throws javax.xml.stream.XMLStreamException on bug
     */
    @objid ("e11115ca-034f-4cab-946e-456f9901a687")
    private void writePropValueTag(String tagName, String keyAtt, String valueAtt, String key, String value, DefinitionScope scope) throws XMLStreamException {
        String v = nullToEmpty(value);
        if (v.length() > 20 || v.contains("\n")) {
            this.out.writeStartElement(tagName);
            this.out.writeAttribute(keyAtt, key);
            if (scope != null)
                writeScope(scope);
            this.out.writeCharacters(v);
            this.out.writeEndElement();
        } else {
            this.out.writeEmptyElement(tagName);
            this.out.writeAttribute(keyAtt, key);
            this.out.writeAttribute(valueAtt, v);
            if (scope != null)
                writeScope(scope);
        }
    }

    @objid ("b7c6922f-d9c6-42fd-a7e4-8347a4d10076")
    private void writeProperties(final GProperties gProperties) throws XMLStreamException {
        this.out.writeStartElement("properties");
        
        ArrayList<String> keys = new ArrayList<>(gProperties.keys());
        Collections.sort(keys);
        
        for (String key : keys) {
            GProperties.Entry e = gProperties.getProperty(key);
            writePropValueTag("prop", "name", "value", e.getName(), e.getValue(), e.getScope());
        }
        this.out.writeEndElement();
    }

    @objid ("21023e07-5a55-4443-8bd2-5c04f14b5a84")
    private void writeResources(ProjectDescriptor projectDescriptor) throws XMLStreamException {
        if (projectDescriptor.getSharedResources().isEmpty()) {
            return;
        }
        
        this.out.writeStartElement("resources");
        for (ResourceDescriptor res : projectDescriptor.getSharedResources()) {
            this.out.writeEmptyElement("resource");
            this.out.writeAttribute("id", res.getId());
            this.out.writeAttribute("location", res.getTargetLocation());
            this.out.writeAttribute("timestamp", String.valueOf(res.getTimestamp()));
        }
        this.out.writeEndElement();
    }

    @objid ("7c9416d8-feb7-46ee-9345-440ccb7388b1")
    private void writeScope(DefinitionScope scope) throws XMLStreamException {
        if (this.withScope && scope != null) {
            this.out.writeAttribute("scope", scope.name());
        }
    }

    @objid ("52cd96f6-51a0-47b4-a741-cffeccbd2ac4")
    private void writeToDoInstallAction(InstallModuleDescriptor action) throws XMLStreamException {
        this.out.writeStartElement("install_module");
        writeModuleDescriptor(action.getModuleDescriptor(), "new_module");
        this.out.writeEndElement();
    }

    @objid ("366159b0-c76c-46f1-b791-3f247e72d067")
    private void writeToDoRemoveAction(RemoveModuleDescriptor action) throws XMLStreamException {
        this.out.writeStartElement("remove_module");
        this.out.writeAttribute("old_module", action.getModuleName());
        this.out.writeEndElement();
    }

    @objid ("ac8af55a-9115-4c20-916d-e21b191d916d")
    private void writeToDoUpdateAction(UpdateModuleDescriptor action) throws XMLStreamException {
        this.out.writeStartElement("update_module");
        this.out.writeAttribute("old_module", action.getOldModuleName());
        
        writeModuleDescriptor(action.getNewModuleDescriptor(), "new_module");
        this.out.writeEndElement();
    }

    @objid ("d7ddcf32-7e69-4214-943f-2a65b232ee75")
    private void writeTodo(TodoDescriptor todo) throws XMLStreamException {
        this.out.writeStartElement("todo");
        
        for (TodoActionDescriptor action : todo.getActions()) {
            if (!action.isValid()) {
                // shield against buggy descriptor
                Log.trace(new IllegalArgumentException(String.format("Don't write invalid %s", action)));
            } else if (action instanceof InstallModuleDescriptor) {
                writeToDoInstallAction((InstallModuleDescriptor) action);
            } else if (action instanceof UpdateModuleDescriptor) {
                writeToDoUpdateAction((UpdateModuleDescriptor) action);
            } else if (action instanceof RemoveModuleDescriptor) {
                writeToDoRemoveAction((RemoveModuleDescriptor) action);
            }
        }
        
        this.out.writeEndElement();
    }

    /**
     * Filter output streams that encodes data to Base64.
     * <p>
     * Whole data is buffered. The data is converted to Base64 and written only on {@link #close()}.
     */
    @objid ("4ef87c8a-bb5d-4d01-84aa-fe0335fdc3bd")
    private static class Base64OutputSream extends OutputStream {
        @objid ("21556450-eebf-4d46-b908-9461f44d6178")
        private OutputStream out;

        @objid ("97c1a87b-fc20-4801-bfe9-57ce6d9b8afa")
        private ByteArrayOutputStream bs = new ByteArrayOutputStream(1024);

        @objid ("7d2ce6ea-6a1c-4de8-bd90-f20e5ac0c5bf")
        public Base64OutputSream(OutputStream out) {
            super();
            this.out = out;
        }

        @objid ("7e552b65-1f18-41f2-8e10-9815f51d5576")
        @Override
        public void write(byte[] b) throws IOException {
            this.bs.write(b);
        }

        @objid ("e27202e1-a4fb-4dc5-8ab7-6f1346fc64ed")
        @Override
        public void write(int b) throws IOException {
            this.bs.write(b);
        }

        @objid ("9a998348-ff9e-4545-ba9c-8d324adc4014")
        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            this.bs.write(b, off, len);
        }

        @objid ("c2b568f1-2147-453e-b5b5-39dc45335a2f")
        @Override
        public void close() throws IOException {
            if (this.bs != null) {
                String conv = javax.xml.bind.DatatypeConverter.printBase64Binary(this.bs.toByteArray());
            
                this.out.write(conv.getBytes());
            
                this.out.close();
                this.bs = null;
            }
            
            super.close();
        }

    }

}
