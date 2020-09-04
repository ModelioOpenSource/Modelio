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

package org.modelio.vstore.exml.common.index.builder;

import java.io.IOError;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.common.index.ICmsNodeIndex;
import org.modelio.vstore.exml.common.index.IUserNodeIndex;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/**
 * Indexes builder.
 * <p>
 * Usage: instantiate and the call {@link #run(InputSource)} for each EXML file in the repository.
 */
@objid ("fd26b9ff-5986-11e1-991a-001ec947ccaf")
public class IndexBuilder {
    @objid ("fd21f6f8-5986-11e1-991a-001ec947ccaf")
    private final DocumentContentHandler defaultHandler;

    @objid ("4698226a-2e68-11e2-8aaa-001ec947ccaf")
    private XMLReader xmlReader;

    /**
     * Initialize the index builder.
     * 
     * @param metamodel the metamodel
     * @param parentIndex the parent/child index
     * @param userIndex the used CMS nodes index.
     */
    @objid ("fd21f71a-5986-11e1-991a-001ec947ccaf")
    public IndexBuilder(SmMetamodel metamodel, ICmsNodeIndex parentIndex, IUserNodeIndex userIndex) {
        this.defaultHandler = new DocumentContentHandler(metamodel, parentIndex, userIndex);
        
        try {
            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxFactory.newSAXParser();
            this.xmlReader = parser.getXMLReader();
        
            this.xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
        
            this.xmlReader.setContentHandler(this.defaultHandler);
            this.xmlReader.setErrorHandler(this.defaultHandler);
        } catch (ParserConfigurationException e) {
            // should never happen
            throw new Error(e);
        } catch (SAXException e) {
            // should never happen
            throw new Error(e);
        }
    }

    /**
     * Run the builder on an XML file.
     * 
     * @param is the XML input source.
     * @throws java.io.IOException in case of failure writing the index.
     * @throws org.modelio.vstore.exml.common.index.builder.InvalidExmlException in case of error reading the EXML source.
     */
    @objid ("fd21f719-5986-11e1-991a-001ec947ccaf")
    public void run(final InputSource is) throws IOException, InvalidExmlException {
        this.defaultHandler.resetModel();
        this.defaultHandler.enterDocumentState();
        
        // Parse stream
        try  {
            this.xmlReader.parse(is);
        } catch(SAXParseException toCatch) {
            String msg = toCatch.getPublicId()+":"+toCatch.getLineNumber()+":"+toCatch.getColumnNumber()+": "+toCatch.getLocalizedMessage();
        
            // If no IOException cause, it is a parsing error,
            // if IOException cause it is a JDBM error
            if (toCatch.getCause() instanceof IOException ) {
                throw new IOException (msg, toCatch);
            } else {
                throw new InvalidExmlException (msg, toCatch);
            }
        } catch (SAXException e) {
            String msg = is.getPublicId()+": "+e.getLocalizedMessage();
            // If no IOException cause, it is a parsing error,
            // if IOException cause it is a JDBM error
            if (e.getCause() instanceof IOException ) {
                throw new IOException (msg, e);
            } else {
                throw new InvalidExmlException (msg, e);
            }
        } catch (IOException e) {
            // it is probably a EXML source reading error.
            String msg = is.getPublicId()+": "+e.getLocalizedMessage();
            throw new InvalidExmlException (msg, e);
        } catch (IOError toCatch) {
            // IOError is thrown by JDBM
            String msg = this.defaultHandler.getLocator()+": "+toCatch.getLocalizedMessage();
            throw new IOException (msg, toCatch);
        }
    }

}
