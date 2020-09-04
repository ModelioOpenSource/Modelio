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

package org.modelio.editors.richnote.microsoft.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Specifies the format to use when saving a Word document.
 * @see <a href="http://msdn.microsoft.com/en-us/library/ff839952.aspx">Office 2010 documentation </a>
 * @see <a href="http://msdn.microsoft.com/en-us/library/bb238158.aspx">Office 2007 documentation </a>
 */
@objid ("e4af78ed-b4d3-42e9-a021-1af9588a66da")
enum EWdSaveFormat {
    /**
     * Microsoft Office Word 97 - 2003 binary file format *
     */
    wdFormatDocument (0),
    /**
     * Microsoft Word 97 document format *
     */
    wdFormatDocument97 (0),
    /**
     * Word template format. *
     */
    wdFormatTemplate (1),
    /**
     * Word 97 template format. *
     */
    wdFormatTemplate97 (1),
    /**
     * Microsoft Windows text format.*
     */
    wdFormatText (2),
    /**
     * Windows text format with line breaks preserved.*
     */
    wdFormatTextLineBreaks (3),
    /**
     * Microsoft DOS text format.*
     */
    wdFormatDOSText (4),
    /**
     * Microsoft DOS text with line breaks preserved.*
     */
    wdFormatDOSTextLineBreaks (5),
    /**
     * Rich text format (RTF).*
     */
    wdFormatRTF (6),
    /**
     * Encoded text format.*
     */
    wdFormatEncodedText (7),
    /**
     * Unicode text format. *
     */
    wdFormatUnicodeText (7),
    /**
     * Standard HTML format *
     */
    wdFormatHTML (8),
    /**
     * Web archive format.*
     */
    wdFormatWebArchive (9),
    /**
     * Filtered HTML format.*
     */
    wdFormatFilteredHTML (10),
    /**
     * Extensible Markup Language (XML) format.*
     */
    wdFormatXML (11),
    /**
     * XML document format. <p> Word 2010 .docx format *
     */
    wdFormatXMLDocument (12),
    /**
     * XML document format with macros enabled.*
     */
    wdFormatXMLDocumentMacroEnabled (13),
    /**
     * XML template format.*
     */
    wdFormatXMLTemplate (14),
    /**
     * XML template format with macros enabled.*
     */
    wdFormatXMLTemplateMacroEnabled (15),
    /**
     * Word default document file format. For Word 2010, this is the DOCX format *
     */
    wdFormatDocumentDefault (16),
    /**
     * PDF format. *
     */
    wdFormatPDF (17),
    /**
     * XPS format.*
     */
    wdFormatXPS (18),
    /**
     * Open XML file format saved as a single XML file.*
     */
    wdFormatFlatXML (19),
    /**
     * Open XML file format with macros enabled saved as a single XML file.*
     */
    wdFormatFlatXMLMacroEnabled (20),
    /**
     * Open XML template format saved as a XML single file.*
     */
    wdFormatFlatXMLTemplate (21),
    /**
     * Open XML template format with macros enabled saved as a single XML file.*
     */
    wdFormatFlatXMLTemplateMacroEnabled (22),
    /**
     * OpenDocument Text format *
     */
    wdFormatOpenDocumentText (23),
    /**
     * Other Ms Office document, let OLE handle save format *
     */
    FormatOle (999999);

    @objid ("7f37ea56-1368-4867-a905-2cb7f0c1ec2e")
    private int value;

    @objid ("8701724c-5a5b-4d3a-8fc2-67eae9fa8760")
    private EWdSaveFormat(int v) {
        this.value = v;
    }

    /**
     * Get the matching Word WdSaveFormat value.
     * @return the Ms Word matching value.
     */
    @objid ("703c473a-0699-440c-81e8-4bc0f725e681")
    public int getValue() {
        return this.value;
    }

    /**
     * Get the enum value matching the given integer constant.
     * @param v a Word WdSaveFormat constant.
     * @return the matching enum.
     */
    @objid ("f555b94f-1b65-4eea-88cb-c0e632f15f57")
    public static EWdSaveFormat get(int v) {
        for (EWdSaveFormat l : values()) {
            if (l.value == v)
                return l;
        }
        throw new IllegalArgumentException(v+" is not a known format.");
    }

}
