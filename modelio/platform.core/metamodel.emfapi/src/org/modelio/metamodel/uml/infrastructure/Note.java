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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("615770eb-f5e8-4a51-9085-acfbee94fb10")
    public static final String MNAME = "Note";

    /**
     * The metaclass qualified name.
     */
    @objid ("31ff9cf8-0f64-4c18-a9b2-e3bf21062025")
    public static final String MQNAME = "Infrastructure.Note";

    /**
     * Getter for attribute 'Note.Content'
     * 
     * Metamodel description:
     * <i>Textual content of the Note. This text can be a description or any syntax used for a target language.</i>
     */
    @objid ("93eecfcd-8bb8-4535-8dd3-bfdf3881133d")
    String getContent();

    /**
     * Setter for attribute 'Note.Content'
     * 
     * Metamodel description:
     * <i>Textual content of the Note. This text can be a description or any syntax used for a target language.</i>
     */
    @objid ("53a1b7f9-c5a8-4b92-8670-aef349f4cb76")
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
    @objid ("73e906b5-6435-40b7-91d2-61e60760e9aa")
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
    @objid ("a6cb13a2-78c0-43fe-a8a1-fcd41a8e1107")
    void setMimeType(String value);

    /**
     * Getter for relation 'Note->Model'
     * 
     * Metamodel description:
     * <i>The NoteType defines the different kinds of Notes that are allowed in a particular context (in specific MDACs, for example).</i>
     */
    @objid ("97e80c8a-cb48-4575-9266-eac03ce9cd29")
    NoteType getModel();

    /**
     * Setter for relation 'Note->Model'
     * 
     * Metamodel description:
     * <i>The NoteType defines the different kinds of Notes that are allowed in a particular context (in specific MDACs, for example).</i>
     */
    @objid ("a3ca27ef-aea0-4f3f-a5f9-df53ecad39cd")
    void setModel(NoteType value);

    /**
     * Getter for relation 'Note->Subject'
     * 
     * Metamodel description:
     * <i>The annotated element owning this note.</i>
     */
    @objid ("d000b894-274e-4ee5-b1d4-a9e3d90b84be")
    ModelElement getSubject();

    /**
     * Setter for relation 'Note->Subject'
     * 
     * Metamodel description:
     * <i>The annotated element owning this note.</i>
     */
    @objid ("579cb331-1396-4f87-b508-0d15f0c5a412")
    void setSubject(ModelElement value);

}
