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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;

/**
 * NoteType v0.0.9054
 * 
 * 
 * <p>NoteTypes are conditioned by Modelio plug-ins (editors and modules). For example, the definitions of document templates are made before those of the associated text definitions. These are determined by textual headings, which must be entered in order to be used by generated documents. The same applies to generated code, which determines the textual zones to be entered. Services are provided in order to find the available Notes for the current Project.</p>
 * 
 * 
 * 
 */
@objid ("008bb80c-c4be-1fd8-97fe-001ec947cd2a")
public interface NoteType extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("d32daff9-a80f-4325-9283-7a40de26882c")
    public static final String MNAME = "NoteType";

    /**
     * The metaclass qualified name.
     */
    @objid ("84dad833-9872-407b-8855-3d97e1f32a05")
    public static final String MQNAME = "Infrastructure.NoteType";

    @objid ("86c9b1d3-36e0-4281-ae56-13648e6465ec")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'NoteType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this note type will be visible for manual addition/suppression by the user.</p>
     * </i>
     * 
     */
    @objid ("e56b5f7c-1adb-44ae-ab2d-354046ef4e9e")
    boolean isIsHidden();

    /**
     * Setter for attribute 'NoteType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this note type will be visible for manual addition/suppression by the user.</p>
     * </i>
     * 
     */
    @objid ("9ba75724-bd9f-4604-8a37-d926a5235d10")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'NoteType.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The note type label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     * 
     */
    @objid ("e0bc0938-a691-4fa4-938e-ac8152c86609")
    String getLabelKey();

    /**
     * Setter for attribute 'NoteType.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The note type label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     * 
     */
    @objid ("1f2af9a3-f784-4085-912f-0185412bf58f")
    void setLabelKey(String value);

    /**
     * Getter for attribute 'NoteType.MimeType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("1081cfb4-9b91-4924-837d-53e15b225263")
    String getMimeType();

    /**
     * Setter for attribute 'NoteType.MimeType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("d38222e7-b160-474a-a2d8-de3fe434c894")
    void setMimeType(String value);

    /**
     * Getter for relation 'NoteType->Element'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("3dcf1c07-8eb0-4abe-b4ca-42035d49722c")
    EList<Note> getElement();

    /**
     * Filtered Getter for relation 'NoteType->Element'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e058de35-4c22-4f6f-b2a9-0960cee0b9c0")
    <T extends Note> List<T> getElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NoteType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("0ac90b63-aba6-41b5-b01a-4209a97f2920")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'NoteType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("489860e0-4a47-4499-be4f-d12e176893e7")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'NoteType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("96863068-3a82-4091-b6d8-8a137aeaec7a")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'NoteType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("39bb39dc-0171-4e8c-aa29-ce37996b221b")
    void setOwnerReference(MetaclassReference value);
}

