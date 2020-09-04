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
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;

/**
 * Stereotype v2.1.00
 * 
 * 
 * <p>
 * Through Stereotypes, the end user can create new icons and new adaptations of ModelElements. Stereotypes cannot be created with the Java / Jython language. They are defined at meta level, and do not belong to a ModelElement at model level.
 * </p>
 */
@objid ("008d239a-c4be-1fd8-97fe-001ec947cd2a")
public interface Stereotype extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("3e38e0de-8b50-401f-a292-dea5fde7f688")
    public static final String MNAME = "Stereotype";

    /**
     * The metaclass qualified name.
     */
    @objid ("99c66c1c-96d6-4070-884e-70ed1534f5dc")
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
     * Metamodel description: <i>Image that represents the stereotype in diagrams for elements in image mode.</i>
     */
    @objid ("5f86a133-b5f6-4d84-9875-e5b718729635")
    String getImage();

    /**
     * Setter for attribute 'Stereotype.Image'
     * 
     * Metamodel description: <i>Image that represents the stereotype in diagrams for elements in image mode.</i>
     */
    @objid ("dc2789fc-d065-47f1-803e-0a19b94f60df")
    void setImage(String value);

    /**
     * Getter for attribute 'Stereotype.Icon'
     * 
     * Metamodel description: <i>Icon that can represent the stereotyped ModelElement.</i>
     */
    @objid ("09157790-01e1-47ca-8965-41e54ae7cd97")
    String getIcon();

    /**
     * Setter for attribute 'Stereotype.Icon'
     * 
     * Metamodel description: <i>Icon that can represent the stereotyped ModelElement.</i>
     */
    @objid ("d7f71163-7280-4e1c-a001-b9db958e20bd")
    void setIcon(String value);

    /**
     * Getter for attribute 'Stereotype.IsHidden'
     * 
     * Metamodel description: <i></i>
     */
    @objid ("c623f3c0-124a-4aec-b365-b3cf86f8b552")
    boolean isIsHidden();

    /**
     * Setter for attribute 'Stereotype.IsHidden'
     * 
     * Metamodel description: <i></i>
     */
    @objid ("3f84c133-42be-4b87-ae64-560837b75e46")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'Stereotype.IsAbstract'
     * 
     * Metamodel description: <i>
     * <p>
     * Tells the stereotype is abstract.
     * </p>
     * <p>
     * Abstract stereotypes may not be applied to elements and must serve only as base stereotype for child ones.
     * </p>
     * </i>
     */
    @objid ("59c862d9-2934-46e5-8058-cfbd7194c8de")
    boolean isIsAbstract();

    /**
     * Setter for attribute 'Stereotype.IsAbstract'
     * 
     * Metamodel description: <i>
     * <p>
     * Tells the stereotype is abstract.
     * </p>
     * <p>
     * Abstract stereotypes may not be applied to elements and must serve only as base stereotype for child ones.
     * </p>
     * </i>
     */
    @objid ("6d8b88d9-5ba8-459d-8e58-87dfeb2906f3")
    void setIsAbstract(boolean value);

    /**
     * Getter for attribute 'Stereotype.LabelKey'
     * 
     * Metamodel description: <i>
     * <p>
     * The stereotype label key used to look into module resources for the translated stereotype label.
     * </p>
     * </i>
     */
    @objid ("5b0f6202-5dc0-44e1-8342-06151563107c")
    String getLabelKey();

    /**
     * Setter for attribute 'Stereotype.LabelKey'
     * 
     * Metamodel description: <i>
     * <p>
     * The stereotype label key used to look into module resources for the translated stereotype label.
     * </p>
     * </i>
     */
    @objid ("da3e7bfb-0cad-4e7c-8f26-94e02cf2aec3")
    void setLabelKey(String value);

    /**
     * Getter for attribute 'Stereotype.BaseClassName'
     * 
     * Metamodel description: <i>
     * <p>
     * MetaClass whose instances can be annotated by this Stereotype.
     * </p>
     * </i>
     */
    @objid ("e531b7d9-fe67-466b-8cc0-b586779e3aa4")
    String getBaseClassName();

    /**
     * Setter for attribute 'Stereotype.BaseClassName'
     * 
     * Metamodel description: <i>
     * <p>
     * MetaClass whose instances can be annotated by this Stereotype.
     * </p>
     * </i>
     */
    @objid ("8cc36dd9-0334-4428-b0e3-e6e332c80fa9")
    void setBaseClassName(String value);

    /**
     * Getter for relation 'Stereotype->DefinedTable'
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("fc2e3c56-ceb8-4d81-a7a5-3fc2556f7683")
    PropertyTableDefinition getDefinedTable();

    /**
     * Setter for relation 'Stereotype->DefinedTable'
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("a7e146ab-147e-4d1c-b5be-e71c65e59f85")
    void setDefinedTable(PropertyTableDefinition value);

    /**
     * Getter for relation 'Stereotype->DefinedResourceType'
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("605c6b79-74ff-4dd4-83ee-ec347dc19d29")
    EList<ResourceType> getDefinedResourceType();

    /**
     * Filtered Getter for relation 'Stereotype->DefinedResourceType'
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("f1ff250d-c79a-4e9b-9342-2ce02498a5a0")
    <T extends ResourceType> List<T> getDefinedResourceType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->Owner'
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("35517901-ffa5-46e1-9ed6-2db4a6675823")
    Profile getOwner();

    /**
     * Setter for relation 'Stereotype->Owner'
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("cb2b3503-e407-4777-9a3e-1297c58b0338")
    void setOwner(Profile value);

    /**
     * Getter for relation 'Stereotype->Parent'
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("d1800675-c7f1-434b-b33f-4cafbafae37d")
    Stereotype getParent();

    /**
     * Setter for relation 'Stereotype->Parent'
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("15afa713-b907-43f6-951b-d0786af1623b")
    void setParent(Stereotype value);

    /**
     * Getter for relation 'Stereotype->DefinedTagType'
     * 
     * Metamodel description: <i></i>
     */
    @objid ("0b733e89-85cd-47f1-b73c-c538d7ed9431")
    EList<TagType> getDefinedTagType();

    /**
     * Filtered Getter for relation 'Stereotype->DefinedTagType'
     * 
     * Metamodel description: <i></i>
     */
    @objid ("82863ac1-426e-41a3-b732-1540d5461a09")
    <T extends TagType> List<T> getDefinedTagType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->Child'
     * 
     * Metamodel description: <i>Stereotypes inheriting from this one.</i>
     */
    @objid ("35b07854-813d-4fd7-82af-69d251a23d55")
    EList<Stereotype> getChild();

    /**
     * Filtered Getter for relation 'Stereotype->Child'
     * 
     * Metamodel description: <i>Stereotypes inheriting from this one.</i>
     */
    @objid ("aeaf0cf3-83e5-4924-b3b4-467f218df70e")
    <T extends Stereotype> List<T> getChild(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->DefinedNoteType'
     * 
     * Metamodel description: <i></i>
     */
    @objid ("41ca9c31-5e9c-4d98-aac4-9c4ad6707a42")
    EList<NoteType> getDefinedNoteType();

    /**
     * Filtered Getter for relation 'Stereotype->DefinedNoteType'
     * 
     * Metamodel description: <i></i>
     */
    @objid ("7748e145-eff8-41a1-a4ad-10b40657bf95")
    <T extends NoteType> List<T> getDefinedNoteType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->ExtendedElement'
     * 
     * Metamodel description: <i>
     * <p>
     * All elements in the project this stereotype is applied on.
     * </p>
     * <p>
     * Beware: this list can be huge and take many seconds to compute!
     * </p>
     * </i>
     */
    @objid ("1de62751-34d4-47a1-823d-977c8e2e50bc")
    EList<ModelElement> getExtendedElement();

    /**
     * Filtered Getter for relation 'Stereotype->ExtendedElement'
     * 
     * Metamodel description: <i>
     * <p>
     * All elements in the project this stereotype is applied on.
     * </p>
     * <p>
     * Beware: this list can be huge and take many seconds to compute!
     * </p>
     * </i>
     */
    @objid ("6f823cdd-08b7-4317-855b-8cbaa50b9868")
    <T extends ModelElement> List<T> getExtendedElement(java.lang.Class<T> filterClass);

}
