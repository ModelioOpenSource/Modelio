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

package org.modelio.vbasic.xml;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Factory and Container of {@link XMLStreamWriter} that will close the contained
 * XMLStreamWriter when {@link #close()} is called.
 */
@objid ("784fa614-3010-11e2-8359-001ec947ccaf")
public class CloseableXMLStreamWriter implements AutoCloseable {
    @objid ("78520857-3010-11e2-8359-001ec947ccaf")
    private final XMLStreamWriter w;

    /**
     * Create a {@link XMLStreamWriter}.
     * @param os an output stream.
     * @param indent <code>true</code> to instantiate an indenting writer.
     * @throws javax.xml.stream.XMLStreamException in case of failure
     * @throws javax.xml.stream.FactoryConfigurationError in case of failure
     */
    @objid ("78520859-3010-11e2-8359-001ec947ccaf")
    public CloseableXMLStreamWriter(OutputStream os, boolean indent) throws FactoryConfigurationError, XMLStreamException {
        final XMLOutputFactory f = XMLOutputFactory.newFactory();
        
        XMLStreamWriter first = f.createXMLStreamWriter(os, StandardCharsets.UTF_8.toString());
        if (indent)
            this.w = new IndentingXMLStreamWriter(first);
        else
            this.w = first;
    }

    @objid ("7852085e-3010-11e2-8359-001ec947ccaf")
    @Override
    public void close() throws XMLStreamException {
        this.w.close();
    }

    /**
     * @return the underlying {@link XMLStreamWriter}.
     */
    @objid ("78520861-3010-11e2-8359-001ec947ccaf")
    public XMLStreamWriter getW() {
        return this.w;
    }

}
