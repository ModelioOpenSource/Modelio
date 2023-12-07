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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;

/**
 * Stereotype v2.1.00
 * 
 * 
 * <p>Through Stereotypes, the end user can create new icons and new adaptations of ModelElements. Stereotypes cannot be created with the Java / Jython language. They are defined at meta level, and do not belong to a ModelElement at model level.</p>
 * 
 * 
 * 
 */
@objid ("008d239a-c4be-1fd8-97fe-001ec947cd2a")
public interface Stereotype extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("44c85697-569f-4c2f-8b93-6976f595ed07")
    public static final String MNAME = "Stereotype";

    /**
     * The metaclass qualified name.
     */
    @objid ("d60a10c8-0486-42be-8e1f-a6cd9d0509a7")
    public static final String MQNAME = "Infrastructure.Stereotype";

    @objid ("27dde74f-c802-420a-8472-e6f2e9d112a7")
    ModuleComponent getModule();

    /**
     * Check that <code>this</code> stereotype is a sub-stereotype of <code>parent</code> stereotype.
     * @param parent a stereotype
     * @return <code>true</code> if <code>this</code> stereotype inherits from the given stereotype. <code>false</code> otherwise.
     * @since 3.8
     */
    @objid ("f4f1f9a4-141f-4af6-b05b-8abf2c2d8791")
    boolean hasBase(Stereotype parent);

    /**
     * Getter for attribute 'Stereotype.Image'
     * 
     * Metamodel description:
     * <i>Image that represents the stereotype in diagrams for elements in image mode.</i>
     * 
     */
    @objid ("29149c4e-5304-48b8-bf36-e2b4815f9636")
    String getImage();

    /**
     * Setter for attribute 'Stereotype.Image'
     * 
     * Metamodel description:
     * <i>Image that represents the stereotype in diagrams for elements in image mode.</i>
     * 
     */
    @objid ("70720aa6-1a8b-4321-bc4c-d1c68055e414")
    void setImage(String value);

    /**
     * Getter for attribute 'Stereotype.Icon'
     * 
     * Metamodel description:
     * <i>Icon that can represent the stereotyped ModelElement.</i>
     * 
     */
    @objid ("d98d0518-869c-467b-ab92-2fd98155cb0e")
    String getIcon();

    /**
     * Setter for attribute 'Stereotype.Icon'
     * 
     * Metamodel description:
     * <i>Icon that can represent the stereotyped ModelElement.</i>
     * 
     */
    @objid ("1dd8bc53-3061-4801-9291-54f5a1461e26")
    void setIcon(String value);

    /**
     * Getter for attribute 'Stereotype.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this stereotype will be visible for manual addition/suppression by the user.</p>
     * </i>
     * 
     */
    @objid ("7a266180-bd64-48b7-9637-f69662454ee0")
    boolean isIsHidden();

    /**
     * Setter for attribute 'Stereotype.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this stereotype will be visible for manual addition/suppression by the user.</p>
     * </i>
     * 
     */
    @objid ("7852257a-d2ea-4570-99f1-10ac2a0f9da1")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'Stereotype.IsAbstract'
     * 
     * Metamodel description:
     * <i><p>Tells the stereotype is abstract.</p><p>Abstract stereotypes may not be applied to elements and must serve only as base stereotype for child ones.</p>
     * </i>
     * 
     */
    @objid ("157e0544-289f-460e-88e6-704ad37391ae")
    boolean isIsAbstract();

    /**
     * Setter for attribute 'Stereotype.IsAbstract'
     * 
     * Metamodel description:
     * <i><p>Tells the stereotype is abstract.</p><p>Abstract stereotypes may not be applied to elements and must serve only as base stereotype for child ones.</p>
     * </i>
     * 
     */
    @objid ("a3d85ae5-9b56-4942-9f9f-df22378cb70e")
    void setIsAbstract(boolean value);

    /**
     * Getter for attribute 'Stereotype.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The stereotype label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     * 
     */
    @objid ("17dfd735-cb7d-4eb8-b6f7-12d5c65c394f")
    String getLabelKey();

    /**
     * Setter for attribute 'Stereotype.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The stereotype label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     * 
     */
    @objid ("439275b0-01bd-450e-b00d-b1811144f8fd")
    void setLabelKey(String value);

    /**
     * Getter for attribute 'Stereotype.BaseClassName'
     * 
     * Metamodel description:
     * <i><p>MetaClass whose instances can be annotated by this Stereotype.</p>
     * </i>
     * 
     */
    @objid ("217dad3c-0090-4e59-bcf4-1bc09487e9f6")
    String getBaseClassName();

    /**
     * Setter for attribute 'Stereotype.BaseClassName'
     * 
     * Metamodel description:
     * <i><p>MetaClass whose instances can be annotated by this Stereotype.</p>
     * </i>
     * 
     */
    @objid ("bccc4486-2027-4d7d-8492-0646dbac1ab1")
    void setBaseClassName(String value);

    /**
     * Getter for relation 'Stereotype->DefinedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("57757b5e-1e0f-426f-834d-198fe1c6cf9a")
    PropertyTableDefinition getDefinedTable();

    /**
     * Setter for relation 'Stereotype->DefinedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("46ac57fe-be3e-4c9d-8939-e5749dfa30e1")
    void setDefinedTable(PropertyTableDefinition value);

    /**
     * Getter for relation 'Stereotype->DefinedResourceType'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("fb61d71d-3f7f-4692-9572-e485c4192529")
    EList<ResourceType> getDefinedResourceType();

    /**
     * Filtered Getter for relation 'Stereotype->DefinedResourceType'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("22b24651-0b54-440c-80df-3d2b276fc5d8")
    <T extends ResourceType> List<T> getDefinedResourceType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("2ce14133-d9a4-4003-b5d8-baa8d80869e4")
    Profile getOwner();

    /**
     * Setter for relation 'Stereotype->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("32103a4d-d877-424f-9a9e-c89d4bf8f67b")
    void setOwner(Profile value);

    /**
     * Getter for relation 'Stereotype->Parent'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("e904d0e6-881d-4831-b454-46ea2b285857")
    Stereotype getParent();

    /**
     * Setter for relation 'Stereotype->Parent'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8738aed0-7cd8-4350-ac52-622b1c41936f")
    void setParent(Stereotype value);

    /**
     * Getter for relation 'Stereotype->DefinedTagType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("03939e51-7a84-48bf-8f41-37c7628106e9")
    EList<TagType> getDefinedTagType();

    /**
     * Filtered Getter for relation 'Stereotype->DefinedTagType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("62f0d43c-529c-4af6-b56c-8d9e5b6c6721")
    <T extends TagType> List<T> getDefinedTagType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->Child'
     * 
     * Metamodel description:
     * <i>Stereotypes inheriting from this one.</i>
     * 
     */
    @objid ("91a8a5a5-6def-47e4-8a46-33534f8cded9")
    EList<Stereotype> getChild();

    /**
     * Filtered Getter for relation 'Stereotype->Child'
     * 
     * Metamodel description:
     * <i>Stereotypes inheriting from this one.</i>
     * 
     */
    @objid ("d70333fd-2e31-41f9-8940-48817d0183f8")
    <T extends Stereotype> List<T> getChild(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->DefinedNoteType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("be10fa65-ebdb-49b5-9b36-2f28b350e93c")
    EList<NoteType> getDefinedNoteType();

    /**
     * Filtered Getter for relation 'Stereotype->DefinedNoteType'
     * 
     * Metamodel description:
     * <i></i>
     * 
     */
    @objid ("4d5e2133-800d-42de-a2af-fa514114c40a")
    <T extends NoteType> List<T> getDefinedNoteType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->ExtendedElement'
     * 
     * Metamodel description:
     * <i><p>All elements in the project this stereotype is applied on.</p><p>Beware: this list can be huge and take many seconds to compute!</p>
     * </i>
     * 
     */
    @objid ("2669c84a-1c58-4ef0-a8b8-dfaf08d4c68f")
    EList<ModelElement> getExtendedElement();

    /**
     * Filtered Getter for relation 'Stereotype->ExtendedElement'
     * 
     * Metamodel description:
     * <i><p>All elements in the project this stereotype is applied on.</p><p>Beware: this list can be huge and take many seconds to compute!</p>
     * </i>
     * 
     */
    @objid ("724d6b1a-bc5f-4397-804b-572eef4e7aef")
    <T extends ModelElement> List<T> getExtendedElement(java.lang.Class<T> filterClass);
}

