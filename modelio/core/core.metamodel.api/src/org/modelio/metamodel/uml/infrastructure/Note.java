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
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Note v0.0.9054
 * 
 * 
 * <p>Notes correspond to the UML notes appearing in diagrams on ModelElements. They can include implementation code, documentation and every possible kind of textual information. Notes are related to NoteTypes, which declare the Notes permitted in a model. In Modelio, Notes belong to their annotated ModelElement. Notes correspond to the UML 2.0 Comment metaclass. The Note name defines the purpose of the Note, and has to conform to the NoteType name, where this is defined.</p>
 * 
 * 
 * 
 */
@objid ("0089d1ea-c4be-1fd8-97fe-001ec947cd2a")
public interface Note extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("c860f785-1ce6-48e7-8f36-1316dcf189f3")
    public static final String MNAME = "Note";

    /**
     * The metaclass qualified name.
     */
    @objid ("841d04ce-001d-4278-b202-42c44d0dc1cc")
    public static final String MQNAME = "Infrastructure.Note";

    /**
     * Getter for attribute 'Note.Content'
     * 
     * Metamodel description:
     * <i>Textual content of the Note. This text can be a description or any syntax used for a target language.</i>
     * 
     */
    @objid ("c401caba-24af-4438-9444-53d5ed27eac7")
    String getContent();

    /**
     * Setter for attribute 'Note.Content'
     * 
     * Metamodel description:
     * <i>Textual content of the Note. This text can be a description or any syntax used for a target language.</i>
     * 
     */
    @objid ("6e5ea3de-9380-4e1e-95f1-465005769112")
    void setContent(String value);

    /**
     * Getter for attribute 'Note.MimeType'
     * 
     * Metamodel description:
     * <i>The mime type of the note. 
     * This is an indication about the contents of the note and about the fromat of this content.
     * If no value is set (null or empty string), the mime type for the Note is taken from the NoteType.
     * 
     * </i>
     * 
     */
    @objid ("b37bdfcb-f072-49e3-a390-96825cc2a567")
    String getMimeType();

    /**
     * Setter for attribute 'Note.MimeType'
     * 
     * Metamodel description:
     * <i>The mime type of the note. 
     * This is an indication about the contents of the note and about the fromat of this content.
     * If no value is set (null or empty string), the mime type for the Note is taken from the NoteType.
     * 
     * </i>
     * 
     */
    @objid ("051a3d6c-a412-4b8c-96d5-e42640ebdfcb")
    void setMimeType(String value);

    /**
     * Getter for relation 'Note->Model'
     * 
     * Metamodel description:
     * <i>The NoteType defines the different kinds of Notes that are allowed in a particular context (in specific MDACs, for example).</i>
     * 
     */
    @objid ("63bb9463-0e67-430e-a64d-13e4b8396a12")
    NoteType getModel();

    /**
     * Setter for relation 'Note->Model'
     * 
     * Metamodel description:
     * <i>The NoteType defines the different kinds of Notes that are allowed in a particular context (in specific MDACs, for example).</i>
     * 
     */
    @objid ("9fee4867-a74b-4a17-ba51-a8032d2f040d")
    void setModel(NoteType value);

    /**
     * Getter for relation 'Note->Subject'
     * 
     * Metamodel description:
     * <i>The annotated element owning this note.</i>
     * 
     */
    @objid ("ce8d9d51-124a-498d-9e16-154b7dffdc1a")
    ModelElement getSubject();

    /**
     * Setter for relation 'Note->Subject'
     * 
     * Metamodel description:
     * <i>The annotated element owning this note.</i>
     * 
     */
    @objid ("39401a80-7498-4541-af21-20282f2538d6")
    void setSubject(ModelElement value);
}

