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
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;

/**
 * ModelElement v3.6.00
 * 
 * 
 * <p>A ModelElement describes every element that can exist in a model. Only low-level Elements are not ModelElements.&nbsp;</p><p>ModelElements can be extended by Stereotypes and TaggedValues, can have Notes, can be the origin or target of Dependencies, and can have Constraints.</p>
 * 
 * 
 * 
 */
@objid ("00886f12-c4be-1fd8-97fe-001ec947cd2a")
public interface ModelElement extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("1d7b6f83-20a9-499b-a6c5-43be66df2a00")
    public static final String MNAME = "ModelElement";

    /**
     * The metaclass qualified name.
     */
    @objid ("763d7906-b94d-4be8-abd8-87f0c4cf14d5")
    public static final String MQNAME = "Infrastructure.ModelElement";

    /**
     * This method add the stereotype specified by the (moduleName, stereotypeName) pair.
     * <p>
     * Does nothing if the stereotype is already present.
     * </p>
     * @since Modelio 3.4
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param stereotypeName The stereotype to find. Cannot be <code>null</code>.
     * @throws ExtensionNotFoundException when the stereotype has not been found.
     */
    @objid ("4601cf60-e004-4047-a69c-5b34dad34860")
    void addStereotype(String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Get a local property value.
     * <p>
     * Return <code>null</code> if no {@link LocalPropertyTable} with the given name exists or it does not contain the given property.
     * </p>
     * @param key a property name
     * @return The property value or <code>null</code>.
     */
    @objid ("762030c9-3782-4956-bb25-f0dc4d749404")
    String getLocalProperty(String key);

    /**
     * This operation returns the first note of the type indicated by the (moduleName, noteTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @return The note or <code>null</code> if the note can't be found.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #getNote(String, String, String)} instead.
     */
    @objid ("dd00f4b1-d804-11e1-b25c-001ec947ccaf")
    @Deprecated
    Note getNote(String moduleName, String noteTypeName);

    /**
     * This operation returns the first note of the type indicated by the (moduleName, noteTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. If <code>null</code>, only types owned by a {@link MetaclassReference} are considered.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @return The note or <code>null</code> if the note can't be found.
     * @since Modelio 3.8
     */
    @objid ("3858b588-75f9-4bbf-ac90-9d6d70eb8539")
    Note getNote(String moduleName, String ownerName, String noteTypeName);

    /**
     * Get the first note of the given type.
     * @param noteType a note type
     * @return the first matching note or <i>null</i>.
     * @since 3.8
     */
    @objid ("818b1862-d416-41f0-90ff-c81dd3496f2a")
    Note getNote(NoteType noteType);

    /**
     * This method returns the content of the first note of the type indicated by the (moduleName, noteTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @return The note or <code>null</code> if the note can't be found.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #getNoteContent(String, String, String)} instead.
     */
    @objid ("dcfe9267-d804-11e1-b25c-001ec947ccaf")
    @Deprecated
    String getNoteContent(String moduleName, String noteTypeName);

    /**
     * This method returns the content of the first note of the type indicated by the (moduleName, noteTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @return The note or <code>null</code> if the note can't be found.
     * @since Modelio 3.8
     */
    @objid ("9e88f67a-e0e9-4bde-b6dc-cd2f18b2a250")
    String getNoteContent(String moduleName, String ownerName, String noteTypeName);

    /**
     * This method returns the content of the first note of the given type.
     * @since 3.8
     * @param noteType the note type.
     * @return The note or <code>null</code> if the note can't be found.
     */
    @objid ("f474c00c-eca4-4530-be6b-b6f812debed8")
    String getNoteContent(NoteType noteType);

    /**
     * Get the first found {@link PropertyTable} that has the given name.
     * @param name a property table name
     * @return the found table or <code>null</code>.
     */
    @objid ("45c55b2e-2831-11e2-bf07-001ec947ccaf")
    PropertyTable getProperties(String name);

    /**
     * Get a property value.
     * <p>
     * Return <code>null</code> if no {@link PropertyTable} with the given name exists or it does not contain the given property.
     * </p>
     * @param tableName The table name. The table may not exist.
     * @param key a property name
     * @return The property value or <code>null</code>.
     */
    @objid ("45c55b29-2831-11e2-bf07-001ec947ccaf")
    String getProperty(String tableName, String key);

    /**
     * Get a {@link Stereotype} defined property value.
     * <p>
     * Return <code>null</code> if no {@link TypedPropertyTable} for the stereotype exists or it does not contain the given property.
     * </p>
     * @param moduleName the name of the module providing stereotype. Cannot be <code>null</code>.
     * @param stereotypeName the name of the stereotype providing the table type. Cannot be <code>null</code>.
     * @param key a property name
     * @return The property value or <code>null</code>.
     * @since Modelio 3.4
     * @throws ExtensionNotFoundException when the Stereotype has not been found.
     */
    @objid ("a102d222-688b-45ac-bc95-5d68fc907403")
    String getProperty(String moduleName, String stereotypeName, String key) throws ExtensionNotFoundException;

    /**
     * Get the first stereotype applied to this element that matches the (moduleName, stereotypeName) pair from this model element.
     * <p>
     * The returned stereotype may be a sub stereotype that derives from the given specification.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param stereotypeName The stereotype to find. Cannot be <code>null</code>.
     * @return the specified stereotype or <code>null</code>.
     */
    @objid ("6c25b162-5c2e-479b-919b-f3a142d61e18")
    Stereotype getStereotype(String moduleName, String stereotypeName);

    /**
     * This method returns the (first) tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @return The tag or <code>null</code> if it can't be found
     * @deprecated since Modelio 3.8, it is recommended to use {@link #getTagValue(String, String, String)} instead.
     */
    @objid ("dcfe9263-d804-11e1-b25c-001ec947ccaf")
    @Deprecated
    TaggedValue getTag(String moduleName, String tagTypeName);

    /**
     * This method returns the (first) tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @return The tag or <code>null</code> if it can't be found
     * @since Modelio 3.8
     */
    @objid ("ecd77d91-578e-4699-9c77-1d92442b21a2")
    TaggedValue getTag(String moduleName, String ownerName, String tagTypeName);

    /**
     * This method returns the first tagged value of the given type.
     * @since 3.8
     * @param tagType a tag type
     * @return The tag or <code>null</code> if it can't be found.
     */
    @objid ("c71a9d84-dd0e-4032-b7d1-422f24d18255")
    TaggedValue getTag(TagType tagType);

    /**
     * This method returns the first parameter value of the first tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @return <code>null</code> if no tag can be found or there are no parameters, otherwise the first parameter value.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #getTagValue(String, String, String)} instead.
     */
    @objid ("dcfe925f-d804-11e1-b25c-001ec947ccaf")
    @Deprecated
    String getTagValue(String moduleName, String tagTypeName);

    /**
     * This method returns the first parameter value of the first tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @return <code>null</code> if no tag can be found or there are no parameters, otherwise the first parameter value.
     * @since Modelio 3.8
     */
    @objid ("4d742b95-6b3c-4a95-86c6-2396f426fb64")
    String getTagValue(String moduleName, String ownerName, String tagTypeName);

    /**
     * This method returns the first parameter value of the first tagged value of the given type.
     * @since 3.8
     * @param tagType a tag type
     * @return <code>null</code> if no tag can be found or there are no parameters, otherwise the first parameter value.
     */
    @objid ("c77d22a9-6031-4cae-a981-3b1f9c71ddc1")
    String getTagValue(TagType tagType);

    /**
     * This method returns the parameter values of the first tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @return <code>null</code> if no tag can be found otherwise the (possibly empty) parameter list
     * @deprecated since Modelio 3.8, it is recommended to use {@link #getTagValues(String, String, String)} instead.
     */
    @objid ("dcfe9259-d804-11e1-b25c-001ec947ccaf")
    @Deprecated
    List<String> getTagValues(String moduleName, String tagTypeName);

    /**
     * This method returns the parameter values of the first tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @return <code>null</code> if no tag can be found otherwise the (possibly empty) parameter list
     * @since Modelio 3.8
     */
    @objid ("3ad52a0d-a099-4a6f-ab45-6682a79e64fb")
    List<String> getTagValues(String moduleName, String ownerName, String tagTypeName);

    /**
     * This method returns the parameter values of the first tagged value of the given type.
     * @since 3.8
     * @param tagType a tag type
     * @return <code>null</code> if no tag can be found otherwise the (possibly empty) parameter list.
     */
    @objid ("95bb3406-e2c3-4bc0-9c0d-cabbfca7e6eb")
    List<String> getTagValues(TagType tagType);

    @objid ("b725ec97-4d1a-4363-b84d-469f09c7acf2")
    boolean isStereotyped(Stereotype stereotype);

    /**
     * Checks if a model element has the stereotype specified by the (moduleName, stereotypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param stereotypeName The stereotype to find. Cannot be <code>null</code>.
     * @return true if the element has the given stereotype or a stereotype derived from the given one.
     */
    @objid ("dcfe9255-d804-11e1-b25c-001ec947ccaf")
    boolean isStereotyped(String moduleName, String stereotypeName);

    /**
     * This method returns <code>true</code> if the element has a tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @return <code>true</code> if the element has a tagged value with the corresponding type.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #isTagged(String, String, String)} instead.
     */
    @objid ("dcfe9251-d804-11e1-b25c-001ec947ccaf")
    @Deprecated
    boolean isTagged(String moduleName, String tagTypeName);

    /**
     * This method returns <code>true</code> if the element has a tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @return <code>true</code> if the element has a tagged value with the corresponding type.
     * @since Modelio 3.8
     */
    @objid ("a39fe099-8d96-4d41-93aa-49d45b9ad69b")
    boolean isTagged(String moduleName, String ownerName, String tagTypeName);

    /**
     * This method returns <code>true</code> if the element has a tagged value of the given type.
     * @since 3.8
     * @param tagType a tag type
     * @return <code>true</code> if the element has a tagged value with the corresponding type.
     */
    @objid ("0381a8ac-93c3-4c83-a2d1-941f704926fa")
    boolean isTagged(TagType tagType);

    /**
     * This method sets the content of the first note of the type indicated by the (moduleName, noteTypeName) pair.
     * <p>
     * If no note with the given type is found, one is created.
     * </p>
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @param content the note content. If value is <code>null</code> the note is deleted.
     * @throws ExtensionNotFoundException when the NoteType has not been found.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #putNoteContent(String, String, String, String)} instead.
     */
    @objid ("73b0ebfc-5dab-4ecb-b39e-3dd5a188916f")
    @Deprecated
    void putNoteContent(String moduleName, String noteTypeName, String content) throws ExtensionNotFoundException;

    /**
     * This method sets the content of the first note of the type indicated by the (moduleName, noteTypeName) pair.
     * <p>
     * If no note with the given type is found, one is created.
     * </p>
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @param content the note content. If value is <code>null</code> the note is deleted.
     * @throws ExtensionNotFoundException when the NoteType has not been found.
     * @since Modelio 3.8
     */
    @objid ("99e30ace-a3d1-4662-9263-99c63a399432")
    void putNoteContent(String moduleName, String ownerName, String noteTypeName, String content) throws ExtensionNotFoundException;

    /**
     * This method sets the content of the first note of the given type.
     * <p>
     * If no note with the given type is found, one is created.
     * </p>
     * @since 3.8
     * @param noteType a note type
     * @param content the note content. If value is <code>null</code> the note is deleted.
     */
    @objid ("59d255ab-4c7a-4ea8-8fa4-ee03c5c9339e")
    void putNoteContent(NoteType noteType, String content);

    /**
     * This method sets the first parameter of the tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * <p>
     * The tagged value and the parameter are created if they don't exist.<br/>
     * Other parameters are deleted.
     * </p>
     * <p>
     * If value is <code>null</code> the existing tag is deleted.
     * </p>
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @param value the values to store on the tag parameters. If value is <code>null</code> the tag is deleted.
     * @throws ExtensionNotFoundException when the TagType has not been found.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #putTagValue(String, String, String, String)} instead.
     */
    @objid ("5caef430-b9e6-4b7c-900c-b7deb17a8197")
    @Deprecated
    void putTagValue(String moduleName, String tagTypeName, String value) throws ExtensionNotFoundException;

    /**
     * This method sets the first parameter of the tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * <p>
     * The tagged value and the parameter are created if they don't exist.<br/>
     * Other parameters are deleted.
     * </p>
     * <p>
     * If value is <code>null</code> the existing tag is deleted.
     * </p>
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @param value the values to store on the tag parameters. If value is <code>null</code> the tag is deleted.
     * @throws ExtensionNotFoundException when the TagType has not been found.
     * @since Modelio 3.8
     */
    @objid ("76d91482-3045-4474-bbc3-3dac75e60a95")
    void putTagValue(String moduleName, String ownerName, String tagTypeName, String value) throws ExtensionNotFoundException;

    /**
     * This method sets the first parameter of the tagged value of the given type.
     * <p>
     * The tagged value and the parameter are created if they don't exist.<br/>
     * Other parameters are deleted.
     * </p>
     * <p>
     * If value is <code>null</code> the existing tag is deleted.
     * </p>
     * @since 3.8
     * @param tagType a tag type
     * @param value the value to store on the tag parameter. If value is <code>null</code> the tag is deleted.
     */
    @objid ("13e1d9e7-8c27-40e9-b334-9213fc7f33e8")
    void putTagValue(TagType tagType, String value);

    /**
     * This method sets the parameters of the tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * <p>
     * The tagged value and the parameters are created if they don't exist, or updated otherwise.<br/>
     * Superfluous parameters are deleted.
     * </p>
     * <p>
     * If values is <code>null</code> or empty list, the existing tag is deleted.
     * </p>
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @param values the values to store on the tag parameters. If value is <code>null</code> or empty list, the tag is deleted.
     * @throws ExtensionNotFoundException when the TagType has not been found.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #putTagValues(String, String, String, List)} instead.
     */
    @objid ("d59462b8-4bf4-4878-a82a-8eede2c93613")
    @Deprecated
    void putTagValues(String moduleName, String tagTypeName, List<String> values) throws ExtensionNotFoundException;

    /**
     * This method sets the parameters of the tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * <p>
     * The tagged value and the parameters are created if they don't exist, or updated otherwise.<br/>
     * Superfluous parameters are deleted.
     * </p>
     * <p>
     * If values is <code>null</code> or empty list, the existing tag is deleted.
     * </p>
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @param values the values to store on the tag parameters. If value is <code>null</code> or empty list, the tag is deleted.
     * @throws ExtensionNotFoundException when the TagType has not been found.
     * @since Modelio 3.8
     */
    @objid ("e1373209-c29c-42f0-85d6-203b8f8c79e6")
    void putTagValues(String moduleName, String ownerName, String tagTypeName, List<String> values) throws ExtensionNotFoundException;

    /**
     * This method sets the parameters of the tagged value of the given type.
     * <p>
     * The tagged value and the parameters are created if they don't exist, or updated otherwise.<br/>
     * Superfluous parameters are deleted.
     * </p>
     * <p>
     * If values is <code>null</code> or empty list, the existing tag is deleted.
     * </p>
     * @since 3.8
     * @param tagType a tag type
     * @param values the new tag parameters
     */
    @objid ("cd39b533-cd5c-489e-9702-77c3da1049b0")
    void putTagValues(TagType tagType, List<String> values);

    /**
     * This method deletes all the notes having this noteType of the type indicated by the (moduleName, noteTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #removeNotes(String, String, String)} instead.
     */
    @objid ("1ce053ff-3366-465d-a318-fba170933dff")
    @Deprecated
    void removeNotes(String moduleName, String noteTypeName);

    /**
     * This method deletes all the notes having this noteType of the type indicated by the (moduleName, noteTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @since Modelio 3.8
     */
    @objid ("6b06183e-c850-48c6-9112-7dd416484877")
    void removeNotes(String moduleName, String ownerName, String noteTypeName);

    /**
     * This method deletes all the notes having this note Type .
     * @param noteType the type of the notes to delete.
     */
    @objid ("9d0d3b40-b4be-4187-b2d9-fa9ce2d5a787")
    void removeNotes(NoteType noteType);

    /**
     * This method removes the stereotype specified by the (moduleName, stereotypeName) pair.
     * <p>
     * If several instances of the stereotype are present, they are all removed.
     * </p>
     * @since Modelio 3.4
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param stereotypeName The stereotype to find. Cannot be <code>null</code>.
     */
    @objid ("f26ff6c2-a983-47c2-bf20-913c7e441ed8")
    void removeStereotypes(String moduleName, String stereotypeName);

    /**
     * This method deletes all the tagged values of the type indicated by the (moduleName, tagTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #removeTags(String, String, String)} instead.
     */
    @objid ("00ecbac8-6298-4f80-825f-8975409d150d")
    @Deprecated
    void removeTags(String moduleName, String tagTypeName);

    /**
     * This method deletes all the tagged values of the type indicated by the (moduleName, tagTypeName) pair.
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @since Modelio 3.8
     */
    @objid ("3385a7fd-fc43-4f90-ab35-01e306738642")
    void removeTags(String moduleName, String ownerName, String tagTypeName);

    /**
     * This method deletes all the tagged values of given type .
     * @param tagType a tag type
     */
    @objid ("1aeee99e-cdea-4eac-b02a-02d4b382c961")
    void removeTags(TagType tagType);

    /**
     * Set a local property value.
     * <p>
     * Update the value of a property in a {@link LocalPropertyTable}. If missing, the table itself is created.
     * </p>
     * <p>
     * Warning: local property tables are only part of the local project, and are never shared with other users.
     * @param key a property name
     * @param value the property value.
     */
    @objid ("2aaaa3c2-dc0c-495d-8060-07b6eb14dc6b")
    void setLocalProperty(String key, String value);

    /**
     * Set a property value.
     * <p>
     * Update the value of a property in a stereotype's {@link TypedPropertyTable}. If missing, the table itself is created.
     * </p>
     * @param moduleName the name of the module providing stereotype. Cannot be <code>null</code>.
     * @param stereotypeName the name of the stereotype providing the table type. Cannot be <code>null</code>.
     * @param key a property name
     * @param value the property value.
     * @throws ExtensionNotFoundException when the stereotype has not been found.
     * @since Modelio 3.4
     */
    @objid ("5bd36280-3dc5-4bdd-9e7c-c5b1fc16e7f1")
    void setProperty(String moduleName, String stereotypeName, String key, String value) throws ExtensionNotFoundException;

    /**
     * Set a property value.
     * <p>
     * Update the value of a property in a {@link PropertyTable}. If missing, the table itself is created.
     * </p>
     * @param tableName The table name. The table may not exist.
     * @param key a property name
     * @param value the property value.
     * @since Modelio 3.4
     */
    @objid ("adf13e7a-b7eb-4f56-bb54-02a378df1260")
    void setProperty(String tableName, String key, String value);

    /**
     * Get the property table for the properties defined by the given stereotype.
     * @since 3.8
     * @param stereotype a stereotype, must not be <code>null</code>.
     * @return the found property table or <code>null</code>.
     */
    @objid ("7987ae45-db7d-4213-9509-0620dd6a1d8f")
    TypedPropertyTable getProperties(Stereotype stereotype);

    /**
     * Get the value of a property defined by a {@link Stereotype}.
     * <p>
     * Return <code>null</code> if no {@link TypedPropertyTable} for the stereotype exists or it does not contain the given property.
     * </p>
     * @param stereotype the stereotype providing the table type. Cannot be <code>null</code>.
     * @param key a property name
     * @return The property value or <code>null</code>.
     * @since Modelio 3.8
     */
    @objid ("fd3a245d-1f02-40f2-9d23-036a0a197b7b")
    String getProperty(Stereotype stereotype, String key);

    /**
     * Set a property value.
     * <p>
     * Update the value of a property in a stereotype's {@link TypedPropertyTable}. If missing, the table itself is created.
     * </p>
     * @param stereotype the stereotype providing the table type. Cannot be <code>null</code>.
     * @param key a property name
     * @param value the property value.
     * @since Modelio 3.8
     */
    @objid ("26e5f0dc-44f4-48df-87f4-d4f2ec75c678")
    void setProperty(Stereotype stereotype, String key, String value);

    /**
     * Get the property table for the properties defined by the given metaclass reference.
     * @since 3.8.1
     * @param ref the metaclass reference providing the table type. Cannot be <code>null</code>.
     * @return the found property table or <code>null</code>.
     */
    @objid ("d3333f65-27b0-4639-8d12-c18e46067a9c")
    TypedPropertyTable getProperties(MetaclassReference ref);

    /**
     * Set a property value.
     * <p>
     * Update the value of a property in a metaclass reference's {@link TypedPropertyTable}. If missing, the table itself is created.
     * </p>
     * @since 3.8.1
     * @param ref the metaclass reference providing the table type. Cannot be <code>null</code>.
     * @param key a property name
     * @param value the property value.
     * @since Modelio 3.8
     */
    @objid ("0bcfac07-5090-425f-9892-18b3766f9735")
    void setProperty(MetaclassReference ref, String key, String value);

    /**
     * Get the value of a property defined by a {@link MetaclassReference}.
     * <p>
     * Return <code>null</code> if no {@link TypedPropertyTable} for the metaclass reference exists or it does not contain the given property.
     * </p>
     * @since 3.8.1
     * @param ref the metaclass reference providing the table type. Cannot be <code>null</code>.
     * @param key a property name
     * @return The property value or <code>null</code>.
     * @since Modelio 3.8
     */
    @objid ("3400a599-984f-4339-a0a0-fcce1b7155ee")
    String getProperty(MetaclassReference ref, String key);

    /**
     * Getter for attribute 'ModelElement.Name'
     * 
     * Metamodel description:
     * <i>Name of the element.</i>
     * 
     */
    @objid ("47c4f310-569c-4555-bab6-313195e867bb")
    String getName();

    /**
     * Setter for attribute 'ModelElement.Name'
     * 
     * Metamodel description:
     * <i>Name of the element.</i>
     * 
     */
    @objid ("4e6e9ba6-bdfe-4e35-ac42-6a526c06cf09")
    void setName(String value);

    /**
     * Getter for relation 'ModelElement->LocalProperties'
     * 
     * Metamodel description:
     * <i>The local property table.
     * 
     * This table is not copied with the element.
     * This table is not versioned with the element on SVN managed models, it is local to the working copy.</i>
     * 
     */
    @objid ("24f35a56-be19-4b75-b074-a10e6ac7ade6")
    LocalPropertyTable getLocalProperties();

    /**
     * Setter for relation 'ModelElement->LocalProperties'
     * 
     * Metamodel description:
     * <i>The local property table.
     * 
     * This table is not copied with the element.
     * This table is not versioned with the element on SVN managed models, it is local to the working copy.</i>
     * 
     */
    @objid ("22052e4d-f12c-4c1c-ae6d-1bdd691a3ebe")
    void setLocalProperties(LocalPropertyTable value);

    /**
     * Getter for relation 'ModelElement->Extension'
     * 
     * Metamodel description:
     * <i><p>Stereotypes metaclassifying the ModelElement.</p>
     * </i>
     * 
     */
    @objid ("bb8514b0-458e-4107-81bf-21b8d7a1471c")
    EList<Stereotype> getExtension();

    /**
     * Filtered Getter for relation 'ModelElement->Extension'
     * 
     * Metamodel description:
     * <i><p>Stereotypes metaclassifying the ModelElement.</p>
     * </i>
     * 
     */
    @objid ("47164bc6-31f4-48b7-9dd5-51da83454f8e")
    <T extends Stereotype> List<T> getExtension(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->DependsOnDependency'
     * 
     * Metamodel description:
     * <i>Designates a Dependency that relates to a supplier ModelElement.</i>
     * 
     */
    @objid ("b134ccc6-14ff-4b7f-98b1-6cf50f18c430")
    EList<Dependency> getDependsOnDependency();

    /**
     * Filtered Getter for relation 'ModelElement->DependsOnDependency'
     * 
     * Metamodel description:
     * <i>Designates a Dependency that relates to a supplier ModelElement.</i>
     * 
     */
    @objid ("0c8b9839-d7a1-4874-bb30-645fd9219fc3")
    <T extends Dependency> List<T> getDependsOnDependency(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Tag'
     * 
     * Metamodel description:
     * <i>TaggedValues annotating the ModelElement.</i>
     * 
     */
    @objid ("a26333ef-6384-4deb-b1d3-f0d443c77703")
    EList<TaggedValue> getTag();

    /**
     * Filtered Getter for relation 'ModelElement->Tag'
     * 
     * Metamodel description:
     * <i>TaggedValues annotating the ModelElement.</i>
     * 
     */
    @objid ("a0072aaf-befd-44e1-ac6a-ebdec884b646")
    <T extends TaggedValue> List<T> getTag(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->ImpactedDependency'
     * 
     * Metamodel description:
     * <i>Dependencies whose source depend on this element.</i>
     * 
     */
    @objid ("5244feac-3cd7-4e79-901d-87dee785e24b")
    EList<Dependency> getImpactedDependency();

    /**
     * Filtered Getter for relation 'ModelElement->ImpactedDependency'
     * 
     * Metamodel description:
     * <i>Dependencies whose source depend on this element.</i>
     * 
     */
    @objid ("8688646a-b861-4407-bd42-9f30b61adbdf")
    <T extends Dependency> List<T> getImpactedDependency(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Properties'
     * 
     * Metamodel description:
     * <i>Owned property tables.</i>
     * 
     */
    @objid ("3e23c62a-73c0-41e8-beb9-d2259a10d865")
    EList<PropertyTable> getProperties();

    /**
     * Filtered Getter for relation 'ModelElement->Properties'
     * 
     * Metamodel description:
     * <i>Owned property tables.</i>
     * 
     */
    @objid ("d0e5e499-22ea-479e-818f-250fb3f631f6")
    <T extends PropertyTable> List<T> getProperties(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Product'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("9a514880-ebf1-4608-9d43-7122538c0062")
    EList<AbstractDiagram> getProduct();

    /**
     * Filtered Getter for relation 'ModelElement->Product'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("374d19cd-3b2f-4c63-8262-ea31155df6f8")
    <T extends AbstractDiagram> List<T> getProduct(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Descriptor'
     * 
     * Metamodel description:
     * <i>Notes (documentation, code, and so on) describing the ModelElement.</i>
     * 
     */
    @objid ("eecebc0a-d9ea-4da7-a202-0d4dde807dfe")
    EList<Note> getDescriptor();

    /**
     * Filtered Getter for relation 'ModelElement->Descriptor'
     * 
     * Metamodel description:
     * <i>Notes (documentation, code, and so on) describing the ModelElement.</i>
     * 
     */
    @objid ("df3e1c48-bab9-4e09-894b-198e7d5f1ba5")
    <T extends Note> List<T> getDescriptor(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Matrix'
     * 
     * Metamodel description:
     * <i>Owned matrices.</i>
     * 
     */
    @objid ("0d746e19-5661-4875-9ff0-ad39e88f1cf9")
    EList<MatrixDefinition> getMatrix();

    /**
     * Filtered Getter for relation 'ModelElement->Matrix'
     * 
     * Metamodel description:
     * <i>Owned matrices.</i>
     * 
     */
    @objid ("fdc2c046-e9a1-464a-89a0-4d0720254f93")
    <T extends MatrixDefinition> List<T> getMatrix(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->impactImpacted'
     * 
     * Metamodel description:
     * <i>Impact link targeting this element.</i>
     * 
     */
    @objid ("ba6f87cd-42cf-45d1-86b0-cd8ca1a76acb")
    EList<ImpactLink> getImpactImpacted();

    /**
     * Filtered Getter for relation 'ModelElement->impactImpacted'
     * 
     * Metamodel description:
     * <i>Impact link targeting this element.</i>
     * 
     */
    @objid ("b1b6492d-8f28-489e-8e47-6660bac41d88")
    <T extends ImpactLink> List<T> getImpactImpacted(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->impactDependsOn'
     * 
     * Metamodel description:
     * <i>Impact links from this element. Gives the elements this one depends on.</i>
     * 
     */
    @objid ("055b1515-e2c6-4b5f-b38a-3631cc1a422b")
    EList<ImpactLink> getImpactDependsOn();

    /**
     * Filtered Getter for relation 'ModelElement->impactDependsOn'
     * 
     * Metamodel description:
     * <i>Impact links from this element. Gives the elements this one depends on.</i>
     * 
     */
    @objid ("05028aa6-9295-4ebc-bf6d-02baa29b20ab")
    <T extends ImpactLink> List<T> getImpactDependsOn(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Attached'
     * 
     * Metamodel description:
     * <i>Attached resources</i>
     * 
     */
    @objid ("a8d1f2ab-35db-4339-bdd7-b567b94794e0")
    EList<AbstractResource> getAttached();

    /**
     * Filtered Getter for relation 'ModelElement->Attached'
     * 
     * Metamodel description:
     * <i>Attached resources</i>
     * 
     */
    @objid ("dff10952-686d-441b-873b-8027be5a66c0")
    <T extends AbstractResource> List<T> getAttached(java.lang.Class<T> filterClass);
}

