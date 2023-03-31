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
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.xml.CloseableXMLStreamWriter;

/**
 * Serialize a {@link GProjectDescriptor} in a XML file or an {@link OutputStream}.
 * <p>
 * The output may be encoded if the {@value #CONF_ENCRYPT_PROP} property is set.
 */
@objid ("48da4fe7-a0af-4704-b46f-0832f5a5d22f")
public class GProjectDescriptorWriter {
    /**
     * File name in the same directory as the configuration file that may contain the encoding of the written project.conf file.
     */
    @objid ("5c70eb03-96d1-4003-834d-b61d3f5406da")
    static final String CONF_ENCRYPT_FILE = "project.conf.encrypt";

    /**
     * Project property to set in order to encode the file content. Only "base64" is supported for the moment.
     */
    @objid ("988d7193-a698-4240-9c9d-2cd11f0953a1")
    static final String CONF_ENCRYPT_PROP = "project.conf.encrypt";

    /**
     * if true, authentication data credentials will be written for shared scoped authentications.
     * 
     * @since 4.0.0
     */
    @objid ("58e27b8b-9602-42be-a285-a929f92ef65c")
    private boolean withSharedAuthCredentials;

    @objid ("1fced17c-f6db-403c-a7ef-6cccd0a4ef3b")
    private XMLStreamWriter out;

    @objid ("142f8d0c-a95e-4a9c-84cd-2895e6b3ff81")
    private Path projectPath;

    /**
     * Set whether authentication data credentials will be written for shared scoped authentications.
     * @param writeSharedAuthCredentials true to write shared authentication credentials
     * @return this instance to chain calls.
     * @since 4.0.0
     */
    @objid ("4286b9d2-af21-4fd8-85ef-1a5ba78b6da3")
    public GProjectDescriptorWriter withSharedAuthCredentials(boolean writeSharedAuthCredentials) {
        this.withSharedAuthCredentials = writeSharedAuthCredentials;
        return this;
    }

    /**
     * Save the project descriptor to a file.
     * <p>
     * Fragments URI are made relative from the project path when possible.
     * @param d the descriptor to save
     * @throws IOException in case of failure.
     */
    @objid ("adf5b1d5-8118-46af-adb3-66932bf7f5b5")
    public void write(final GProjectDescriptor d) throws IOException {
        final Path projectFilePath = d.getProjectFileStructure().getProjectConfFile();
        this.projectPath = projectFilePath.getParent();
        
        // Setup encoding
        final String encoding = d.getProperties().getValue(GProjectDescriptorWriter.CONF_ENCRYPT_PROP, "");
        boolean isEncoded = encoding.equals("base64");
        
        if (isEncoded) {
            // Write encoding in a parallel file
            Files.write(this.projectPath.resolve(GProjectDescriptorWriter.CONF_ENCRYPT_FILE), encoding.getBytes(StandardCharsets.UTF_8));
        } else {
            Files.deleteIfExists(this.projectPath.resolve(GProjectDescriptorWriter.CONF_ENCRYPT_FILE));
        }
        
        // Write configuration
        try (OutputStream os = Files.newOutputStream(projectFilePath);
                final BufferedOutputStream os2 = new BufferedOutputStream(isEncoded ? Base64.getEncoder().wrap(os) : os);) {
            write(d, os2);
        }
        
    }

    /**
     * Write the project descriptor to an output stream.
     * @param d the descriptor to save
     * @param os the stream to write the project descriptor to.
     * @throws IOException in case of failure.
     */
    @objid ("dcb18c78-5f63-47b0-8645-3f7af5305d57")
    public void write(final GProjectDescriptor d, final OutputStream os) throws IOException {
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

    @objid ("b1e9f537-7024-462c-a145-8d907483366b")
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
     * @param value the string value to check
     * @return the value if not <i>null</i>. Empty string if <i>null</i>.
     */
    @objid ("12faaf46-6e4b-43f0-8630-8258a8c294a9")
    private String nullToEmpty(String value) {
        if (value == null) {
            // Workaround Oracle database changing empty strings to NULL pointer.
            return "";
        } else {
            return value;
        }
        
    }

    @objid ("fa04b3f7-315a-431c-8d44-f92a390ee96e")
    private static String toStringOrEmpty(Object o) {
        if (o == null) {
            return "";
        } else {
            return o.toString();
        }
        
    }

    @objid ("db5eb3e2-758a-42bb-b44c-79ec2e5139e5")
    private void writeAuth(AuthDescriptor auth) throws XMLStreamException {
        if (auth != null && auth.getScheme() != null) {
            DefinitionScope authScope = auth.getScope();
            boolean writeCreds = this.withSharedAuthCredentials && authScope == DefinitionScope.SHARED;
        
            this.out.writeStartElement("auth");
            writeAttribute("scheme", auth.getScheme());
        
            writeScope(authScope);
            for (Entry<String, String> e : auth.getData().serialize(writeCreds).entrySet()) {
                writePropValueTag("prop", "name", "value", e.getKey(), e.getValue(), null);
            }
        
            this.out.writeEndElement();
        } else {
            this.out.writeStartElement("auth");
            writeAttribute("scheme", AuthDescriptor.AUTH_TYPE_ASK);
            writeScope(DefinitionScope.LOCAL);
            this.out.writeEndElement();
        }
        
    }

    @objid ("93a4b0b5-d73a-48e2-98d9-47102ce700df")
    private void writeProjectDescriptor(final GProjectDescriptor projectDescriptor) throws XMLStreamException {
        this.out.writeStartElement("project");
        writeAttribute("name", projectDescriptor.getName());
        writeAttribute("type", toStringOrEmpty(projectDescriptor.getType()));
        writeAttribute("version", String.valueOf(projectDescriptor.getFormatVersion()));
        writeAttribute("projectSpaceVersion", String.valueOf(projectDescriptor.getProjectSpaceVersion()));
        
        if (projectDescriptor.getModelioVersion() != null) {
            writeAttribute("modelioVersion", projectDescriptor.getModelioVersion().toString("V.R"));
        }
        
        boolean writeProjectPath = writeProjectPath(projectDescriptor);
        if (writeProjectPath) {
            writeAttribute("path", projectDescriptor.getProjectFileStructure().getProjectPath().toString());
        }
        
        if (!ProjectType.LOCAL.toString().equals(projectDescriptor.getType())) {
            // Write remote location
            writeAttribute("remote", projectDescriptor.getRemoteLocation());
        }
        
        // Write authentication data
        writeAuth(projectDescriptor.getAuthDescriptor());
        
        // Write parts
        for (GProjectPartDescriptor f : projectDescriptor.getPartDescriptors()) {
            if (f.getType() == GProjectPartType.RESOURCE) {
                // Keep resources for later
                continue;
            }
            writePartDescriptor(f);
        }
        
        // Write resources
        writeResources(projectDescriptor);
        
        // Write project propertiess
        writeProperties(projectDescriptor.getProperties());
        
        this.out.writeEndElement();
        
    }

    @objid ("c2a2bd4b-ec54-48ae-b203-6f8aa9939750")
    private void writePartDescriptor(final GProjectPartDescriptor fd) throws XMLStreamException {
        switch (fd.getType()) {
        case EXMLFRAGMENT:
        case SVNFRAGMENT:
        case RAMC:
            writeFragmentDescriptor(fd);
            break;
        case FEATURE:
            writeFeatureDescriptor(fd);
            break;
        case MODULE:
            writeModuleDescriptor(fd);
            break;
        case RESOURCE:
            writeResourceDescriptor(fd);
            break;
        default:
            break;
        }
        
    }

    @objid ("af66e15e-6d26-40aa-9b1f-277913ecaf64")
    private void writeFragmentDescriptor(final GProjectPartDescriptor d) throws XMLStreamException {
        this.out.writeStartElement("fragment");
        writeAttribute("type", d.getType().toString());
        writeAttribute("id", d.getId());
        
        // Version value is optional
        writeVersion(d.getVersion());
        
        // Label value is optional
        writeLabel(d.getLabel());
        
        if (d.getDefinitionScope() != null) {
            writeScope(d.getDefinitionScope());
        } else {
            writeScope(DefinitionScope.LOCAL);
        }
        
        URI uri = getRelativeUri(d.getLocation());
        if (uri != null) {
            writeAttribute("uri", uri.toString());
        }
        
        writeAuth(d.getAuth());
        writeProperties(d.getProperties());
        this.out.writeEndElement();
        
    }

    @objid ("5c333f43-c56d-4eb9-b0a6-7a4571f7328f")
    private void writeModuleDescriptor(final GProjectPartDescriptor d) throws XMLStreamException {
        this.out.writeStartElement("module");
        writeAttribute("name", d.getId());
        
        writeVersion(d.getVersion());
        writeLabel(d.getLabel());
        writeScope(d.getDefinitionScope());
        
        URI uri = getRelativeUri(d.getLocation());
        String uriString = uri == null ? "" : uri.toString();
        writeAttribute("uri", uriString);
        
        writeAuth(d.getAuth());
        writeProperties(d.getProperties());
        this.out.writeEndElement();
        
    }

    @objid ("5818faa2-44ae-4ab7-acf9-4902d9c0ae20")
    private void writeResourceDescriptor(final GProjectPartDescriptor d) throws XMLStreamException {
        this.out.writeStartElement("resource");
        this.out.writeAttribute("id", d.getId());
        
        URI location = getRelativeUri(d.getLocation());
        String uriString = location == null ? "" : location.toString();
        writeAttribute("location", uriString);
        
        writeLabel(d.getLabel());
        writeProperties(d.getProperties());
        
        this.out.writeEndElement();
        
    }

    @objid ("5a9c702e-708a-4e7d-957a-0a3bd02ddb42")
    private void writeAttribute(String name, String value) throws XMLStreamException {
        if (value != null) {
            this.out.writeAttribute(name, value);
        } else {
            throw new XMLStreamException(String.format("No writable value for attribute '%s'", name));
        }
        
    }

    @objid ("2ad21377-e089-4859-85c8-873a12eb137b")
    private void writeAttribute(String name, String value, String fallback) throws XMLStreamException {
        if (value != null) {
            this.out.writeAttribute(name, value);
        } else if (fallback != null) {
            this.out.writeAttribute(name, fallback);
        } else {
            throw new XMLStreamException(String.format("No writable value for attribute '%s'", name));
        }
        
    }

    @objid ("41438c7c-490e-4a3d-b7d5-545867ec260d")
    private void writeFeatureDescriptor(final GProjectPartDescriptor d) throws XMLStreamException {
        this.out.writeStartElement("feature");
        writeAttribute("type", d.getType().toString());
        writeAttribute("id", d.getId());
        writeVersion(d.getVersion());
        writeLabel(d.getLabel());
        writeScope(d.getDefinitionScope());
        writeProperties(d.getProperties());
        this.out.writeEndElement();
        
    }

    @objid ("d286a452-cea0-4eca-9eee-930c596fb32a")
    private boolean writeProjectPath(final GProjectDescriptor projectDescriptor) {
        if (projectDescriptor.getProjectFileStructure() == null || projectDescriptor.getProjectFileStructure().getProjectPath() == null || this.projectPath == null) {
            return false;
        }
        return !projectDescriptor.getProjectFileStructure().getProjectPath().startsWith(this.projectPath);
    }

    /**
     * Write a key=value style DOM element.
     * <p>
     * If the value is short it is written as a DOM attribute. In the other case it is a DOM TEXT.
     * @param tagName the DOM element TAG name
     * @param keyAtt the key DOM attribute name
     * @param valueAtt the value DOM attribute name
     * @param key the property key
     * @param value the property value
     * @throws XMLStreamException on bug
     */
    @objid ("0d435b2b-8a52-494e-9da7-55fd73d1cf43")
    private void writePropValueTag(String tagName, String keyAtt, String valueAtt, String key, String value, DefinitionScope scope) throws XMLStreamException {
        String v = nullToEmpty(value);
        if (v.length() > 20 || v.contains("\n")) {
            this.out.writeStartElement(tagName);
            this.out.writeAttribute(keyAtt, key);
            if (scope != null) {
                writeScope(scope);
            }
            this.out.writeCharacters(v);
            this.out.writeEndElement();
        } else {
            this.out.writeEmptyElement(tagName);
            this.out.writeAttribute(keyAtt, key);
            this.out.writeAttribute(valueAtt, v);
            if (scope != null) {
                writeScope(scope);
            }
        }
        
    }

    @objid ("a74d60a9-e209-4b0c-8f68-e87af6875133")
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

    @objid ("6cb2a027-686b-4406-a69c-5395794182a0")
    private void writeResources(GProjectDescriptor projectDescriptor) throws XMLStreamException {
        List<GProjectPartDescriptor> resources = projectDescriptor.getPartDescriptors(GProjectPartType.RESOURCE);
        if (resources.isEmpty()) {
            return;
        }
        
        this.out.writeStartElement("resources");
        for (GProjectPartDescriptor res : resources) {
            writePartDescriptor(res);
        
        }
        this.out.writeEndElement();
        
    }

    @objid ("a07023fd-0e59-4c6f-92e9-40c3b4d84275")
    private void writeScope(DefinitionScope scope) throws XMLStreamException {
        if (scope != null) {
            this.out.writeAttribute("scope", scope.name());
        } else {
            this.out.writeAttribute("scope", DefinitionScope.LOCAL.name());
        }
        
    }

    @objid ("50e51b8c-8045-426b-8d48-0d9ecf7d15fe")
    private void writeLabel(String label) throws XMLStreamException {
        if (label != null && !label.trim().isEmpty()) {
            this.out.writeAttribute("label", label);
        }
        
    }

    @objid ("fe90aa20-ab6c-4eda-b981-da9d51d8d4c7")
    private void writeVersion(Version version) throws XMLStreamException {
        if (version != null) {
            this.out.writeAttribute("version", version.toString());
        }
        
    }

}
