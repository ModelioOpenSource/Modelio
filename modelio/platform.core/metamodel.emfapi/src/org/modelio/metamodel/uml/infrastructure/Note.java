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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
*/
package org.modelio.metamodel.uml.infrastructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;

/**
 * Note v0.0.9054
 * 
 * 
 * <p>Notes correspond to the UML notes appearing in diagrams on ModelElements. They can include implementation code, documentation and every possible kind of textual information. Notes are related to NoteTypes, which declare the Notes permitted in a model. In Modelio, Notes belong to their annotated ModelElement. Notes correspond to the UML 2.0 Comment metaclass. The Note name defines the purpose of the Note, and has to conform to the NoteType name, where this is defined.</p>
 */
@objid ("0089d1ea-c4be-1fd8-97fe-001ec947cd2a")
public interface Note extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("9904f3cf-0eb5-4993-b9f8-3ee2fdf8cb32")
    public static final String MNAME = "Note";

    /**
     * The metaclass qualified name.
     */
    @objid ("a0a51370-2442-4066-aff6-bd6398cdd8ac")
    public static final String MQNAME = "Infrastructure.Note";

    /**
     * Getter for attribute 'Note.Content'
     * 
     * Metamodel description:
     * <i>Textual content of the Note. This text can be a description or any syntax used for a target language.</i>
     */
    @objid ("2adbaea7-2cb7-4f24-aa15-26d5d5a4cbc2")
    String getContent();

    /**
     * Setter for attribute 'Note.Content'
     * 
     * Metamodel description:
     * <i>Textual content of the Note. This text can be a description or any syntax used for a target language.</i>
     */
    @objid ("9b845830-5eeb-4f5c-a432-4822b4921531")
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
     */
    @objid ("d2b32389-aa41-4baa-9563-4811758911ff")
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
     */
    @objid ("1a2cbe3d-2638-4372-a959-5f828898edfe")
    void setMimeType(String value);

    /**
     * Getter for relation 'Note->Model'
     * 
     * Metamodel description:
     * <i>The NoteType defines the different kinds of Notes that are allowed in a particular context (in specific MDACs, for example).</i>
     */
    @objid ("6f502643-0725-4a4f-95aa-962c1ae1ccf6")
    NoteType getModel();

    /**
     * Setter for relation 'Note->Model'
     * 
     * Metamodel description:
     * <i>The NoteType defines the different kinds of Notes that are allowed in a particular context (in specific MDACs, for example).</i>
     */
    @objid ("f3f4cc86-976a-4637-96ba-6a1c93831be9")
    void setModel(NoteType value);

    /**
     * Getter for relation 'Note->Subject'
     * 
     * Metamodel description:
     * <i>The annotated element owning this note.</i>
     */
    @objid ("99ebe215-79e1-4e10-a45c-a5618e9ced48")
    ModelElement getSubject();

    /**
     * Setter for relation 'Note->Subject'
     * 
     * Metamodel description:
     * <i>The annotated element owning this note.</i>
     */
    @objid ("43f6eb0a-21ce-42a1-9c2b-49e631ac39e3")
    void setSubject(ModelElement value);

}
