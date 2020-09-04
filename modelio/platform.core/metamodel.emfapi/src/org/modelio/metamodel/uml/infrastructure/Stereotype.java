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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;

/**
 * Stereotype v2.1.00
 * 
 * 
 * <p>Through Stereotypes, the end user can create new icons and new adaptations of ModelElements. Stereotypes cannot be created with the Java / Jython language. They are defined at meta level, and do not belong to a ModelElement at model level.</p>
 */
@objid ("008d239a-c4be-1fd8-97fe-001ec947cd2a")
public interface Stereotype extends ModelElement {
    /**
     * The metaclass simple name.
     */
    @objid ("bd635881-166f-428c-aa59-bb3e38a6fc84")
    public static final String MNAME = "Stereotype";

    /**
     * The metaclass qualified name.
     */
    @objid ("b3926811-6d09-4242-82ff-c3f80a6ebff9")
    public static final String MQNAME = "Infrastructure.Stereotype";

    @objid ("27dde74f-c802-420a-8472-e6f2e9d112a7")
    ModuleComponent getModule();

    /**
     * Check that <code>this</code> stereotype is a sub-stereotype of <code>parent</code> stereotype.
     * 
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
     */
    @objid ("f646a97c-df9d-4066-a96c-8d20ccd0d19b")
    String getImage();

    /**
     * Setter for attribute 'Stereotype.Image'
     * 
     * Metamodel description:
     * <i>Image that represents the stereotype in diagrams for elements in image mode.</i>
     */
    @objid ("ff9a8197-3e42-46ab-bd71-820a893089e9")
    void setImage(String value);

    /**
     * Getter for attribute 'Stereotype.Icon'
     * 
     * Metamodel description:
     * <i>Icon that can represent the stereotyped ModelElement.</i>
     */
    @objid ("8129819e-9021-4771-89ef-a3d1ac7d7bd6")
    String getIcon();

    /**
     * Setter for attribute 'Stereotype.Icon'
     * 
     * Metamodel description:
     * <i>Icon that can represent the stereotyped ModelElement.</i>
     */
    @objid ("6e399aa9-3ad6-4f49-a75b-8dc902a758b3")
    void setIcon(String value);

    /**
     * Getter for attribute 'Stereotype.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this stereotype will be visible for manual addition/suppression by the user.</p>
     * </i>
     */
    @objid ("192777cf-12cf-4880-bfb4-046f55b3c1cc")
    boolean isIsHidden();

    /**
     * Setter for attribute 'Stereotype.IsHidden'
     * 
     * Metamodel description:
     * <i><p>Indicates whether or not this stereotype will be visible for manual addition/suppression by the user.</p>
     * </i>
     */
    @objid ("f544b284-e1b7-428d-b6e8-760ffac80abf")
    void setIsHidden(boolean value);

    /**
     * Getter for attribute 'Stereotype.IsAbstract'
     * 
     * Metamodel description:
     * <i><p>Tells the stereotype is abstract.</p><p>Abstract stereotypes may not be applied to elements and must serve only as base stereotype for child ones.</p>
     * </i>
     */
    @objid ("adde264e-a042-4a08-b6ce-c5127ff6b5f0")
    boolean isIsAbstract();

    /**
     * Setter for attribute 'Stereotype.IsAbstract'
     * 
     * Metamodel description:
     * <i><p>Tells the stereotype is abstract.</p><p>Abstract stereotypes may not be applied to elements and must serve only as base stereotype for child ones.</p>
     * </i>
     */
    @objid ("77f77b7b-3094-4087-bf50-484995125046")
    void setIsAbstract(boolean value);

    /**
     * Getter for attribute 'Stereotype.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The stereotype label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     */
    @objid ("6bea0aa1-a217-4330-b37d-6a6e6586b655")
    String getLabelKey();

    /**
     * Setter for attribute 'Stereotype.LabelKey'
     * 
     * Metamodel description:
     * <i><p>The stereotype label key used to look into module resources for the translated stereotype label.</p>
     * </i>
     */
    @objid ("995d1e2c-0579-4cd5-acb0-31626d364e30")
    void setLabelKey(String value);

    /**
     * Getter for attribute 'Stereotype.BaseClassName'
     * 
     * Metamodel description:
     * <i><p>MetaClass whose instances can be annotated by this Stereotype.</p>
     * </i>
     */
    @objid ("d07a6cde-3f3d-4581-bdd6-606e94d62222")
    String getBaseClassName();

    /**
     * Setter for attribute 'Stereotype.BaseClassName'
     * 
     * Metamodel description:
     * <i><p>MetaClass whose instances can be annotated by this Stereotype.</p>
     * </i>
     */
    @objid ("685e817a-dcd9-4e33-8657-5c1d338fc0c8")
    void setBaseClassName(String value);

    /**
     * Getter for relation 'Stereotype->DefinedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9620b9e1-3293-4b9a-8e85-7170adfd610e")
    PropertyTableDefinition getDefinedTable();

    /**
     * Setter for relation 'Stereotype->DefinedTable'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e6583b5e-1639-43e2-aeaf-4abfe09ab6aa")
    void setDefinedTable(PropertyTableDefinition value);

    /**
     * Getter for relation 'Stereotype->DefinedResourceType'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("cad89353-31db-452e-80ad-008c91d510a6")
    EList<ResourceType> getDefinedResourceType();

    /**
     * Filtered Getter for relation 'Stereotype->DefinedResourceType'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0b3cb31e-6630-49ce-afe4-a34905245383")
    <T extends ResourceType> List<T> getDefinedResourceType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8dac0868-ef42-43bd-87d7-e4eb2693585b")
    Profile getOwner();

    /**
     * Setter for relation 'Stereotype->Owner'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("966d098c-178b-4351-ac54-2670aa9b763c")
    void setOwner(Profile value);

    /**
     * Getter for relation 'Stereotype->Parent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c9765e77-a706-42c6-aa6a-52670cf4fdd1")
    Stereotype getParent();

    /**
     * Setter for relation 'Stereotype->Parent'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("edc13f38-ea18-4856-ac8f-98ebcf3f7166")
    void setParent(Stereotype value);

    /**
     * Getter for relation 'Stereotype->DefinedTagType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("23a5e876-bfa6-4051-8d72-a97a6f839d92")
    EList<TagType> getDefinedTagType();

    /**
     * Filtered Getter for relation 'Stereotype->DefinedTagType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("03836d0e-b665-4dab-8249-28b9b7b808a8")
    <T extends TagType> List<T> getDefinedTagType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->Child'
     * 
     * Metamodel description:
     * <i>Stereotypes inheriting from this one.</i>
     */
    @objid ("25020361-36d8-437d-84ff-16bc481e8e16")
    EList<Stereotype> getChild();

    /**
     * Filtered Getter for relation 'Stereotype->Child'
     * 
     * Metamodel description:
     * <i>Stereotypes inheriting from this one.</i>
     */
    @objid ("55698fc3-8ecd-406b-816d-c1dbabb2de9e")
    <T extends Stereotype> List<T> getChild(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->DefinedNoteType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("ab6d8673-42f8-4270-90f2-4379bc7d5d1f")
    EList<NoteType> getDefinedNoteType();

    /**
     * Filtered Getter for relation 'Stereotype->DefinedNoteType'
     * 
     * Metamodel description:
     * <i></i>
     */
    @objid ("88117104-4340-4261-ae95-1998d1484e6d")
    <T extends NoteType> List<T> getDefinedNoteType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'Stereotype->ExtendedElement'
     * 
     * Metamodel description:
     * <i><p>All elements in the project this stereotype is applied on.</p><p>Beware: this list can be huge and take many seconds to compute!</p>
     * </i>
     */
    @objid ("de5f773c-76ca-4e9a-b0fe-9304bd63a0ec")
    EList<ModelElement> getExtendedElement();

    /**
     * Filtered Getter for relation 'Stereotype->ExtendedElement'
     * 
     * Metamodel description:
     * <i><p>All elements in the project this stereotype is applied on.</p><p>Beware: this list can be huge and take many seconds to compute!</p>
     * </i>
     */
    @objid ("4b4e95d4-e04d-4e9a-aee1-36dac8b4bfed")
    <T extends ModelElement> List<T> getExtendedElement(java.lang.Class<T> filterClass);

}
