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
    @objid ("fd34e525-915b-44a4-9d8f-62fa15388b43")
    wdFormatDocument(0),
    /**
     * Microsoft Word 97 document format *
     */
    @objid ("a9f34bbe-f908-4345-a94d-498632c5fadb")
    wdFormatDocument97(0),
    /**
     * Word template format. *
     */
    @objid ("c1a11bc3-a9d6-4d5a-849d-7ca1b6b5ac33")
    wdFormatTemplate(1),
    /**
     * Word 97 template format. *
     */
    @objid ("b2899217-6316-44c7-82bb-d1c0411b037a")
    wdFormatTemplate97(1),
    /**
     * Microsoft Windows text format.*
     */
    @objid ("c1f0a2f7-dc48-4551-a037-176bb7c73e6b")
    wdFormatText(2),
    /**
     * Windows text format with line breaks preserved.*
     */
    @objid ("e5dbfced-5e61-429a-8212-d1bd16ce8e98")
    wdFormatTextLineBreaks(3),
    /**
     * Microsoft DOS text format.*
     */
    @objid ("7f2ea645-8aa0-46a0-a32c-8ad0fd2503b8")
    wdFormatDOSText(4),
    /**
     * Microsoft DOS text with line breaks preserved.*
     */
    @objid ("89a8be57-fafe-4de8-b050-4c2b7044926a")
    wdFormatDOSTextLineBreaks(5),
    /**
     * Rich text format (RTF).*
     */
    @objid ("c3159ed7-89d7-420a-af25-240790aaec90")
    wdFormatRTF(6),
    /**
     * Encoded text format.*
     */
    @objid ("cefe674b-3cc9-46b6-a989-e33820ed8300")
    wdFormatEncodedText(7),
    /**
     * Unicode text format. *
     */
    @objid ("d8f68f53-2e72-44c3-920d-b1da87f94c57")
    wdFormatUnicodeText(7),
    /**
     * Standard HTML format *
     */
    @objid ("d1dd49f0-0f31-49c9-be90-2e494e4dbd1d")
    wdFormatHTML(8),
    /**
     * Web archive format.*
     */
    @objid ("0b2939ca-0439-4dd9-aee8-fd48e0551229")
    wdFormatWebArchive(9),
    /**
     * Filtered HTML format.*
     */
    @objid ("43e103e2-fb9f-407f-a34e-13d610d43424")
    wdFormatFilteredHTML(10),
    /**
     * Extensible Markup Language (XML) format.*
     */
    @objid ("0d41978b-2a8c-4cf5-9265-4c297da8e262")
    wdFormatXML(11),
    /**
     * XML document format. <p> Word 2010 .docx format *
     */
    @objid ("d31f0262-e4cd-4f08-8a91-7ea72ae0c052")
    wdFormatXMLDocument(12),
    /**
     * XML document format with macros enabled.*
     */
    @objid ("bd354521-2b81-4497-9617-866b1494018a")
    wdFormatXMLDocumentMacroEnabled(13),
    /**
     * XML template format.*
     */
    @objid ("31a5e8d0-5f0f-4afd-83a8-4e89d403ed64")
    wdFormatXMLTemplate(14),
    /**
     * XML template format with macros enabled.*
     */
    @objid ("ecc1dcf6-edc5-4af1-a652-a7045162e75f")
    wdFormatXMLTemplateMacroEnabled(15),
    /**
     * Word default document file format. For Word 2010, this is the DOCX format *
     */
    @objid ("61d1ab4a-5acc-44cd-b094-931426ea2dd6")
    wdFormatDocumentDefault(16),
    /**
     * PDF format. *
     */
    @objid ("b9d9d065-b578-4e49-affc-2d5ea5bd5831")
    wdFormatPDF(17),
    /**
     * XPS format.*
     */
    @objid ("0656805b-ce10-46c1-af9a-2ea0802738c6")
    wdFormatXPS(18),
    /**
     * Open XML file format saved as a single XML file.*
     */
    @objid ("d6fa7951-d5e5-4ace-ad2c-84c546c1401c")
    wdFormatFlatXML(19),
    /**
     * Open XML file format with macros enabled saved as a single XML file.*
     */
    @objid ("b1f51caa-1785-40e6-aa47-ab68751f463b")
    wdFormatFlatXMLMacroEnabled(20),
    /**
     * Open XML template format saved as a XML single file.*
     */
    @objid ("14a8e445-7b6c-492c-8cd7-89930ace9374")
    wdFormatFlatXMLTemplate(21),
    /**
     * Open XML template format with macros enabled saved as a single XML file.*
     */
    @objid ("758a75ba-8d76-40ed-ab6b-8c91e2d8ca78")
    wdFormatFlatXMLTemplateMacroEnabled(22),
    /**
     * OpenDocument Text format *
     */
    @objid ("525d4bf6-584d-498c-8aad-938679f48c1f")
    wdFormatOpenDocumentText(23),
    /**
     * Other Ms Office document, let OLE handle save format *
     */
    @objid ("0d7a5767-dc2d-4dd7-933e-261625221c75")
    FormatOle(999999);

    @objid ("7f37ea56-1368-4867-a905-2cb7f0c1ec2e")
    private int value;

    @objid ("8701724c-5a5b-4d3a-8fc2-67eae9fa8760")
    private  EWdSaveFormat(int v) {
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
