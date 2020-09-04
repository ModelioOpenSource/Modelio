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

package org.modelio.vbasic.xml;

import java.util.ArrayDeque;
import java.util.Deque;
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
 * Indenting {@link XMLStreamWriter}.
 * @author Kohsuke Kawaguchi
 */
@objid ("7856cd0e-3010-11e2-8359-001ec947ccaf")
public class IndentingXMLStreamWriter extends DelegatingXMLStreamWriter {
    @objid ("7856cd1b-3010-11e2-8359-001ec947ccaf")
    private int depth = 0;

    @objid ("9d155a31-3010-11e2-8359-001ec947ccaf")
    private String indentStep = "  ";

    @objid ("7856cd10-3010-11e2-8359-001ec947ccaf")
    private static final Object SEEN_NOTHING = new Object();

    @objid ("7856cd12-3010-11e2-8359-001ec947ccaf")
    private static final Object SEEN_ELEMENT = new Object();

    @objid ("7856cd14-3010-11e2-8359-001ec947ccaf")
    private static final Object SEEN_DATA = new Object();

    @objid ("7856cd16-3010-11e2-8359-001ec947ccaf")
    private Object state = SEEN_NOTHING;

    /**
     * stack of states
     */
    @objid ("7856cd17-3010-11e2-8359-001ec947ccaf")
    private Deque<Object> stateStack = new ArrayDeque<>();

    /**
     * Construct an indenting XML writer.
     * 
     * @param writer the underlying XML writer.
     */
    @objid ("7856cd1c-3010-11e2-8359-001ec947ccaf")
    public IndentingXMLStreamWriter(XMLStreamWriter writer) {
        super(writer);
    }

    /**
     * Set the indentation.
     * 
     * @param s the indentation. Please use only spaces or tabs.
     */
    @objid ("7856cd20-3010-11e2-8359-001ec947ccaf")
    public void setIndentStep(String s) {
        this.indentStep = s;
    }

    @objid ("7856cd24-3010-11e2-8359-001ec947ccaf")
    private void onStartElement() throws XMLStreamException {
        this.stateStack.push(SEEN_ELEMENT);
        this.state = SEEN_NOTHING;
        if (this.depth > 0) {
            super.writeCharacters("\n");
        }
        doIndent();
        this.depth++;
    }

    @objid ("7856cd26-3010-11e2-8359-001ec947ccaf")
    private void onEndElement() throws XMLStreamException {
        this.depth--;
        if (this.state == SEEN_ELEMENT) {
            super.writeCharacters("\n");
            doIndent();
        }
        this.state = this.stateStack.pop();
    }

    @objid ("7856cd28-3010-11e2-8359-001ec947ccaf")
    private void onEmptyElement() throws XMLStreamException {
        this.state = SEEN_ELEMENT;
        if (this.depth > 0) {
            super.writeCharacters("\n");
        }
        doIndent();
    }

    /**
     * Print indentation for the current level.
     * writing the indentation characters, or if a filter
     * further down the chain raises an exception.
     * @exception XMLStreamException If there is an error
     */
    @objid ("7856cd2a-3010-11e2-8359-001ec947ccaf")
    private void doIndent() throws XMLStreamException {
        if (this.depth > 0) {
            for (int i = 0; i < this.depth; i++)
                super.writeCharacters(this.indentStep);
        }
    }

    @objid ("7856cd2d-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartDocument() throws XMLStreamException {
        super.writeStartDocument();
        super.writeCharacters("\n");
    }

    @objid ("78592f1c-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartDocument(String version) throws XMLStreamException {
        super.writeStartDocument(version);
        super.writeCharacters("\n");
    }

    @objid ("78592f20-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartDocument(String encoding, String version) throws XMLStreamException {
        super.writeStartDocument(encoding, version);
        super.writeCharacters("\n");
    }

    @objid ("78592f25-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartElement(String localName) throws XMLStreamException {
        onStartElement();
        super.writeStartElement(localName);
    }

    @objid ("78592f29-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartElement(String namespaceURI, String localName) throws XMLStreamException {
        onStartElement();
        super.writeStartElement(namespaceURI, localName);
    }

    @objid ("78592f2e-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeStartElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
        onStartElement();
        super.writeStartElement(prefix, localName, namespaceURI);
    }

    @objid ("78592f34-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeEmptyElement(String namespaceURI, String localName) throws XMLStreamException {
        onEmptyElement();
        super.writeEmptyElement(namespaceURI, localName);
    }

    @objid ("78592f39-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeEmptyElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
        onEmptyElement();
        super.writeEmptyElement(prefix, localName, namespaceURI);
    }

    @objid ("78592f3f-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeEmptyElement(String localName) throws XMLStreamException {
        onEmptyElement();
        super.writeEmptyElement(localName);
    }

    @objid ("78592f43-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeEndElement() throws XMLStreamException {
        onEndElement();
        super.writeEndElement();
    }

    @objid ("78592f46-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeCharacters(String text) throws XMLStreamException {
        this.state = SEEN_DATA;
        super.writeCharacters(text);
    }

    @objid ("78592f4a-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeCharacters(char[] text, int start, int len) throws XMLStreamException {
        this.state = SEEN_DATA;
        super.writeCharacters(text, start, len);
    }

    @objid ("78592f52-3010-11e2-8359-001ec947ccaf")
    @Override
    public void writeCData(String data) throws XMLStreamException {
        this.state = SEEN_DATA;
        super.writeCData(data);
    }

}
