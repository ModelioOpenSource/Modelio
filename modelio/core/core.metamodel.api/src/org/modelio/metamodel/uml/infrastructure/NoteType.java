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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
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
    @objid ("f5214290-729f-45c4-844a-40b499acc190")
    public static final String MNAME = "NoteType";

    /**
     * The metaclass qualified name.
     */
    @objid ("7e0e800d-9926-4e9e-ae07-2e0a5613fb2b")
    public static final String MQNAME = "Infrastructure.NoteType";

    @objid ("86c9b1d3-36e0-4281-ae56-13648e6465ec")
    ModuleComponent getModule();

    /**
     * Getter for attribute 'NoteType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this note type will be visible for manual addition/suppression by the user.</p>
     * </i>
     */
    @objid ("f1cc01f4-1ef3-4cab-bc6d-945e962973e3")
    boolean isIsHidden();

    /**
     * Setter for attribute 'NoteType.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this note type will be visible for manual addition/suppression by the user.</p>
     * </i>
     */
    @objid ("5b20c8ff-9719-4043-a71c-e1d0447c0a78")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'NoteType.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The note type label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     */
    @objid ("e523d6ba-9493-4378-a58a-4cc1b5376c1d")
    String getLabelKey();

    /**
     * Setter for attribute 'NoteType.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The note type label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     */
    @objid ("cd72b2ae-dee7-4085-a5c2-79bb2ed0265b")
    void setLabelKey(String value);

    /**
     * Getter for attribute 'NoteType.MimeType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("0c7bcadc-474c-4ff3-8ac7-2659367c4410")
    String getMimeType();

    /**
     * Setter for attribute 'NoteType.MimeType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("610f92db-e55f-4260-81eb-32a48bdfad36")
    void setMimeType(String value);

    /**
     * Getter for relation 'NoteType->Element'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("379829eb-a458-4070-b30d-0b40c94d1c42")
    EList<Note> getElement();

    /**
     * Filtered Getter for relation 'NoteType->Element'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bc36d71e-65bf-4efa-ac5d-b7b2026d61c3")
    <T extends Note> List<T> getElement(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'NoteType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d7480b42-cbb3-4ebe-88cb-0dfa012af190")
    Stereotype getOwnerStereotype();

    /**
     * Setter for relation 'NoteType->OwnerStereotype'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("963e0d39-4f88-494e-be35-ad6d475d79e1")
    void setOwnerStereotype(Stereotype value);

    /**
     * Getter for relation 'NoteType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9bf3ff4f-4204-4409-899d-cc48f3420875")
    MetaclassReference getOwnerReference();

    /**
     * Setter for relation 'NoteType->OwnerReference'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("dd57f229-726a-4bab-a45e-f90cee61163b")
    void setOwnerReference(MetaclassReference value);

}
