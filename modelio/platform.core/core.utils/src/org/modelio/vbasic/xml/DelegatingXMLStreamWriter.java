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

package org.modelio.vbasic.xml;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2005-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at 
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
/**
 * Delegating {@link XMLStreamWriter}.
 * 
 * @author Kohsuke Kawaguchi
 */
@objid ("78520866-3010-11e2-8359-001ec947ccaf")
abstract class DelegatingXMLStreamWriter implements XMLStreamWriter {
    @objid ("78520868-3010-11e2-8359-001ec947ccaf")
    private final XMLStreamWriter writer;

    @objid ("7852086a-3010-11e2-8359-001ec947ccaf")
    public DelegatingXMLStreamWriter(XMLStreamWriter writer) {
        this.writer = writer;
    }

    @objid ("7852086d-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartElement(String localName) throws XMLStreamException {
        this.writer.writeStartElement(localName);
    }

    @objid ("78546a98-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartElement(String namespaceURI, String localName) throws XMLStreamException {
        this.writer.writeStartElement(namespaceURI, localName);
    }

    @objid ("78546a9d-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
        this.writer.writeStartElement(prefix, localName, namespaceURI);
    }

    @objid ("78546aa3-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeEmptyElement(String namespaceURI, String localName) throws XMLStreamException {
        this.writer.writeEmptyElement(namespaceURI, localName);
    }

    @objid ("78546aa8-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeEmptyElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
        this.writer.writeEmptyElement(prefix, localName, namespaceURI);
    }

    @objid ("78546aae-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeEmptyElement(String localName) throws XMLStreamException {
        this.writer.writeEmptyElement(localName);
    }

    @objid ("78546ab2-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeEndElement() throws XMLStreamException {
        this.writer.writeEndElement();
    }

    @objid ("78546ab5-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeEndDocument() throws XMLStreamException {
        this.writer.writeEndDocument();
    }

    @objid ("78546ab8-3010-11e2-8359-001ec947ccaf")
    @Override
    public void close() throws XMLStreamException {
        this.writer.close();
    }

    @objid ("78546abb-3010-11e2-8359-001ec947ccaf")
    @Override
    public void flush() throws XMLStreamException {
        this.writer.flush();
    }

    @objid ("78546abe-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeAttribute(String localName, String value) throws XMLStreamException {
        this.writer.writeAttribute(localName, value);
    }

    @objid ("78546ac3-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeAttribute(String prefix, String namespaceURI, String localName, String value) throws XMLStreamException {
        this.writer.writeAttribute(prefix, namespaceURI, localName, value);
    }

    @objid ("78546aca-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeAttribute(String namespaceURI, String localName, String value) throws XMLStreamException {
        this.writer.writeAttribute(namespaceURI, localName, value);
    }

    @objid ("78546ad0-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeNamespace(String prefix, String namespaceURI) throws XMLStreamException {
        this.writer.writeNamespace(prefix, namespaceURI);
    }

    @objid ("78546ad5-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeDefaultNamespace(String namespaceURI) throws XMLStreamException {
        this.writer.writeDefaultNamespace(namespaceURI);
    }

    @objid ("78546ad9-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeComment(String data) throws XMLStreamException {
        this.writer.writeComment(data);
    }

    @objid ("78546add-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeProcessingInstruction(String target) throws XMLStreamException {
        this.writer.writeProcessingInstruction(target);
    }

    @objid ("78546ae1-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeProcessingInstruction(String target, String data) throws XMLStreamException {
        this.writer.writeProcessingInstruction(target, data);
    }

    @objid ("78546ae6-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeCData(String data) throws XMLStreamException {
        this.writer.writeCData(data);
    }

    @objid ("78546aea-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeDTD(String dtd) throws XMLStreamException {
        this.writer.writeDTD(dtd);
    }

    @objid ("78546aee-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeEntityRef(String name) throws XMLStreamException {
        this.writer.writeEntityRef(name);
    }

    @objid ("78546af2-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartDocument() throws XMLStreamException {
        this.writer.writeStartDocument();
    }

    @objid ("7856ccdb-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartDocument(String version) throws XMLStreamException {
        this.writer.writeStartDocument(version);
    }

    @objid ("7856ccdf-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartDocument(String encoding, String version) throws XMLStreamException {
        this.writer.writeStartDocument(encoding, version);
    }

    @objid ("7856cce4-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeCharacters(String text) throws XMLStreamException {
        this.writer.writeCharacters(text);
    }

    @objid ("7856cce8-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeCharacters(char[] text, int start, int len) throws XMLStreamException {
        this.writer.writeCharacters(text, start, len);
    }

    @objid ("7856ccf0-3010-11e2-8359-001ec947ccaf")
    @Override
    public String getPrefix(String uri) throws XMLStreamException {
        return this.writer.getPrefix(uri);
    }

    @objid ("7856ccf6-3010-11e2-8359-001ec947ccaf")
    @Override
    public void setPrefix(String prefix, String uri) throws XMLStreamException {
        this.writer.setPrefix(prefix, uri);
    }

    @objid ("7856ccfb-3010-11e2-8359-001ec947ccaf")
    @Override
    public void setDefaultNamespace(String uri) throws XMLStreamException {
        this.writer.setDefaultNamespace(uri);
    }

    @objid ("7856ccff-3010-11e2-8359-001ec947ccaf")
    @Override
    public void setNamespaceContext(NamespaceContext context) throws XMLStreamException {
        this.writer.setNamespaceContext(context);
    }

    @objid ("7856cd03-3010-11e2-8359-001ec947ccaf")
    @Override
    public NamespaceContext getNamespaceContext() {
        return this.writer.getNamespaceContext();
    }

    @objid ("7856cd08-3010-11e2-8359-001ec947ccaf")
    @Override
    public Object getProperty(String name) throws IllegalArgumentException {
        return this.writer.getProperty(name);
    }

}
