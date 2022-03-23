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

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.modelio.gproject.data.ramc.IModelComponentInfos.ExportedFile;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vbasic.xml.CloseableXMLStreamWriter;

/**
 * Write a RAMC manifest as XML to a stream.
 * @author cma
 * @since 3.6
 */
@objid ("792c099a-15e6-482f-b5bc-bde5c5341a13")
public class ManifestWriter {
    @objid ("da173a3c-8925-4097-b69d-1a6e899dd849")
    private IModelComponentInfos manifest;

    @objid ("4713279a-11ce-49e3-9db4-ae0e727fd2e5")
    private XMLStreamWriter writer;

    /**
     * @param manifestToWrite the manifest to write
     * @param out the output stream
     * @throws IOException on failure
     */
    @objid ("8e9ab286-9023-4819-8609-b250326b1430")
    public void write(IModelComponentInfos manifestToWrite, OutputStream out) throws IOException {
        try (CloseableXMLStreamWriter w = new CloseableXMLStreamWriter(out, true)){
            this.writer = w.getW();
            this.manifest = manifestToWrite;
            
            write ();
        } catch (FactoryConfigurationError | XMLStreamException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        }
        
    }

    @objid ("9b74427b-24c9-4354-8e2e-fc87f18757e2")
    private void write() throws XMLStreamException {
        writeMetadataHeader();
        
        // Dependencies
        writeMetadataDependencies();
            
        // Contributors
        writeMetadataContributors();
            
        // Contributors
        writeMetadataMetamodels();
            
        // Roots
        writeMetadataRoots();
            
        // Exported files
        writeFileEntries();
            
        // Footer
        writeMetadataFooter();
        
    }

    @objid ("38d0f95c-ac82-4c5b-a43d-bba051ed9e98")
    private void writeMetadataHeader() throws XMLStreamException {
        this.writer.writeStartDocument();
        this.writer.writeStartElement("ram-component");
        this.writer.writeAttribute("manifest-version", IModelComponentInfos.MANIFEST_VERSION.toString());
        this.writer.writeAttribute("name", this.manifest.getName());
        this.writer.writeAttribute("version", String.valueOf(this.manifest.getVersion().getMajorVersion()));
        this.writer.writeAttribute("release", String.valueOf(this.manifest.getVersion().getMinorVersion()));
        this.writer.writeAttribute("clevel", String.valueOf(this.manifest.getVersion().getBuildVersion()));
        this.writer.writeAttribute("modelioVersion", this.manifest.getModelioVersion().toString());
        this.writer.writeAttribute("provider", this.manifest.getProvider());
        this.writer.writeStartElement("description");
        this.writer.writeCData(this.manifest.getDescription());
        this.writer.writeEndElement();
        
    }

    @objid ("9b2698a2-770d-4b8e-a781-79d2d82e6d33")
    private void writeMetadataDependencies() throws XMLStreamException {
        this.writer.writeStartElement("dependencies");
        
        for (VersionedItem<?> parentRamc : this.manifest.getRequiredModelComponents()) {
            this.writer.writeEmptyElement("dependency");
            this.writer.writeAttribute("name", parentRamc.getName());
            this.writer.writeAttribute("version", parentRamc.getVersion().toString());
        }
        this.writer.writeEndElement();
        
    }

    @objid ("39d86a68-f2e2-4877-8b41-229b48682255")
    private void writeMetadataContributors() throws XMLStreamException {
        this.writer.writeStartElement("contributors");
        
        for (VersionedItem<?> parentRamc : this.manifest.getContributingModules()) {
            this.writer.writeEmptyElement("contributor");
            this.writer.writeAttribute("name", parentRamc.getName());
            this.writer.writeAttribute("version", parentRamc.getVersion().toString());
        }
        this.writer.writeEndElement();
        
    }

    @objid ("388c48dc-9ec4-4415-a4f2-94e741c873c5")
    private void writeMetadataRoots() throws XMLStreamException {
        this.writer.writeStartElement("roots");
        
        for (ModelRef ref : this.manifest.getRoots()) {
            this.writer.writeEmptyElement("root");
            this.writer.writeAttribute("metaclass", ref.mc);
            this.writer.writeAttribute("name", ref.name);
            this.writer.writeAttribute("uuid", ref.uuid);
        }
        this.writer.writeEndElement();
        
    }

    @objid ("d59684ba-0619-4652-9e10-85596e880f57")
    private void writeFileEntries() throws XMLStreamException {
        //this.writer.writeStartElement("roots");
        
        for (ExportedFile ref : this.manifest.getExportedFiles()) {
            this.writer.writeEmptyElement("file");
            this.writer.writeAttribute("archive-name", ref.getNameInArchive());
            this.writer.writeAttribute("destination-path", ref.getPath().toString());
            this.writer.writeAttribute("mtime", String.valueOf(ref.getDate().to(TimeUnit.SECONDS)));
        }
        //this.writer.writeEndElement();
        
    }

    @objid ("f6251cbb-7ec3-4254-93d8-452fdcc69a1a")
    private void writeMetadataFooter() throws XMLStreamException {
        this.writer.writeEndElement();
        this.writer.writeEndDocument();
        
    }

    @objid ("2393b778-33ba-4d2b-b274-74f377c87bd7")
    private void writeMetadataMetamodels() throws XMLStreamException {
        this.writer.writeStartElement("metamodels");
        
        for (VersionedItem<?> mm : this.manifest.getRequiredMetamodelFragments()) {
            this.writer.writeEmptyElement("metamodel");
            this.writer.writeAttribute("name", mm.getName());
            this.writer.writeAttribute("version", mm.getVersion().toString());
        }
        this.writer.writeEndElement();
        
    }

}
