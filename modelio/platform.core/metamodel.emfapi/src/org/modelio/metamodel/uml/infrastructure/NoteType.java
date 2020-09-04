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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

/**
 * NoteType v0.0.9054
 * 
 * 
 * <p>NoteTypes are conditioned by Modelio plug-ins (editors and modules). For example, the definitions of document templates are made before those of the associated text definitions. These are determined by textual headings, which must be entered in order to be used by generated documents. The same applies to generated code, which determines the textual zones to be entered. Services are provided in order to find the available Notes for the current Project.</p>
 */
@objid ("008bb80c-c4be-1fd8-97fe-001ec947cd2a")
public interface NoteType extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("a5dc1f8f-65a3-4159-97b9-ea107771a5dd")
    public static final String MNAME = "NoteType";

    /**
     * The metaclass qualified name.
     */
    @objid ("26335595-921b-4280-9d75-69c27c3a9b6c")
    public static final String MQNAME = "Infrastructure.NoteType";

    @objid ("86c9b1d3-36e0-4281-ae56-13648e6465ec")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'NoteType.IsHidden'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("fff09dde-318c-4cbd-a664-d2aaf8ea5642")
    boolean isIsHidden();

    /**
     * Setter for attribute 'NoteType.IsHidden'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f0833bb0-28e8-4902-b960-9e54e3dfc023")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'NoteType.LabelKey'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("7ea1b714-6303-45c2-9ae5-62bc97810ba5")
    String getLabelKey();

    /**
     * Setter for attribute 'NoteType.LabelKey'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("8ef42045-ab4a-46fb-b71e-c526cb92cf83")
    void setLabelKey(String value);

    /**
     * Getter for attribute 'NoteType.MimeType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("570df1c9-f7fb-440e-8644-1300dedaa800")
    String getMimeType();

    /**
     * Setter for attribute 'NoteType.MimeType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("b08e1558-458c-4d65-905a-a213072c1c45")
    void setMimeType(String value);

    /**
     * Getter for relation 'NoteType->Element'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("734fac16-b75c-45c3-83c4-0ce8ab938284")
    EList<Note> getElement();

    /**
     * Filtered Getter for relation 'NoteType->Element'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e58b951a-bb01-408a-85a9-d8773d4df10f")
    <T extends Note> List<T> getElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NoteType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("41152a02-f56c-4a6e-a0da-12ce2c14e741")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'NoteType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0d9f98e4-6cb6-4b53-9440-93836352d79b")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'NoteType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("6453da3b-7c3c-4bab-bad1-0f799d5d1f5f")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'NoteType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b02c82d4-175e-40b3-9b8f-a0b1e31d24e0")
    void setOwnerReference(MetaclassReference value);

}
