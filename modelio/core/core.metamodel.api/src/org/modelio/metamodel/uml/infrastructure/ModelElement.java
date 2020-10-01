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
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.impact.ImpactLink;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.AbstractResource;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.LocalPropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;

/**
 * ModelElement v3.6.00
 * 
 * 
 * <p>A ModelElement describes every element that can exist in a model. Only low-level Elements are not ModelElements.&nbsp;</p><p>ModelElements can be extended by Stereotypes and TaggedValues, can have Notes, can be the origin or target of Dependencies, and can have Constraints.</p>
 */
@objid ("00886f12-c4be-1fd8-97fe-001ec947cd2a")
public interface ModelElement extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("bbaf5609-1ee2-4ee5-a65b-818cbd8d2014")
    public static final String MNAME = "ModelElement";

    /**
     * The metaclass qualified name.
     */
    @objid ("66839c03-5611-49a2-a15a-ab4e83c73704")
    public static final String MQNAME = "Infrastructure.ModelElement";

    /**
     * This method add the stereotype specified by the (moduleName, stereotypeName) pair.
     * <p>
     * Does nothing if the stereotype is already present.
     * </p>
     * @since Modelio 3.4
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param stereotypeName The stereotype to find. Cannot be <code>null</code>.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the stereotype has not been found.
     */
    @objid ("4601cf60-e004-4047-a69c-5b34dad34860")
    void addStereotype(String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Get a local property value.
     * <p>
     * Return <code>null</code> if no {@link LocalPropertyTable} with the given name exists or it does not contain the given property.
     * </p>
     * 
     * @param key a property name
     * @return The property value or <code>null</code>.
     */
    @objid ("762030c9-3782-4956-bb25-f0dc4d749404")
    String getLocalProperty(String key);

    /**
     * This operation returns the first note of the type indicated by the (moduleName, noteTypeName) pair.
     * 
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
     * 
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
     * 
     * @param noteType a note type
     * @return the first matching note or <i>null</i>.
     * @since 3.8
     */
    @objid ("818b1862-d416-41f0-90ff-c81dd3496f2a")
    Note getNote(NoteType noteType);

    /**
     * This method returns the content of the first note of the type indicated by the (moduleName, noteTypeName) pair.
     * 
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
     * 
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
     * 
     * @param noteType the note type.
     * @return The note or <code>null</code> if the note can't be found.
     */
    @objid ("f474c00c-eca4-4530-be6b-b6f812debed8")
    String getNoteContent(NoteType noteType);

    /**
     * Get the first found {@link PropertyTable} that has the given name.
     * 
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
     * 
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
     * 
     * @param moduleName the name of the module providing stereotype. Cannot be <code>null</code>.
     * @param stereotypeName the name of the stereotype providing the table type. Cannot be <code>null</code>.
     * @param key a property name
     * @return The property value or <code>null</code>.
     * @since Modelio 3.4
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the Stereotype has not been found.
     */
    @objid ("a102d222-688b-45ac-bc95-5d68fc907403")
    String getProperty(String moduleName, String stereotypeName, String key) throws ExtensionNotFoundException;

    /**
     * Get the first stereotype applied to this element that matches the (moduleName, stereotypeName) pair from this model element.
     * <p>
     * The returned stereotype may be a sub stereotype that derives from the given specification.
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param stereotypeName The stereotype to find. Cannot be <code>null</code>.
     * @return the specified stereotype or <code>null</code>.
     */
    @objid ("6c25b162-5c2e-479b-919b-f3a142d61e18")
    Stereotype getStereotype(String moduleName, String stereotypeName);

    /**
     * This method returns the (first) tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * 
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
     * 
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
     * 
     * @param tagType a tag type
     * @return The tag or <code>null</code> if it can't be found.
     */
    @objid ("c71a9d84-dd0e-4032-b7d1-422f24d18255")
    TaggedValue getTag(TagType tagType);

    /**
     * This method returns the first parameter value of the first tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * 
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
     * 
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
     * 
     * @param tagType a tag type
     * @return <code>null</code> if no tag can be found or there are no parameters, otherwise the first parameter value.
     */
    @objid ("c77d22a9-6031-4cae-a981-3b1f9c71ddc1")
    String getTagValue(TagType tagType);

    /**
     * This method returns the parameter values of the first tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * 
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
     * 
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
     * 
     * @param tagType a tag type
     * @return <code>null</code> if no tag can be found otherwise the (possibly empty) parameter list.
     */
    @objid ("95bb3406-e2c3-4bc0-9c0d-cabbfca7e6eb")
    List<String> getTagValues(TagType tagType);

    @objid ("b725ec97-4d1a-4363-b84d-469f09c7acf2")
    boolean isStereotyped(Stereotype stereotype);

    /**
     * Checks if a model element has the stereotype specified by the (moduleName, stereotypeName) pair.
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param stereotypeName The stereotype to find. Cannot be <code>null</code>.
     * @return true if the element has the given stereotype or a stereotype derived from the given one.
     */
    @objid ("dcfe9255-d804-11e1-b25c-001ec947ccaf")
    boolean isStereotyped(String moduleName, String stereotypeName);

    /**
     * This method returns <code>true</code> if the element has a tagged value of the type indicated by the (moduleName, tagTypeName) pair.
     * 
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
     * 
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
     * 
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
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @param content the note content
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the NoteType has not been found.
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
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @param content the note content
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the NoteType has not been found.
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
     * 
     * @param noteType a note type
     * @param content the note content
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
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @param value the values to store on the tag parameters. If value is <code>null</code> the tag is deleted.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the TagType has not been found.
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
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @param value the values to store on the tag parameters. If value is <code>null</code> the tag is deleted.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the TagType has not been found.
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
     * 
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
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @param values the values to store on the tag parameters. If value is <code>null</code> or empty list, the tag is deleted.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the TagType has not been found.
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
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @param values the values to store on the tag parameters. If value is <code>null</code> or empty list, the tag is deleted.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the TagType has not been found.
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
     * 
     * @param tagType a tag type
     * @param values the new tag parameters
     */
    @objid ("cd39b533-cd5c-489e-9702-77c3da1049b0")
    void putTagValues(TagType tagType, List<String> values);

    /**
     * This method deletes all the notes having this noteType of the type indicated by the (moduleName, noteTypeName) pair.
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #removeNotes(String, String, String)} instead.
     */
    @objid ("1ce053ff-3366-465d-a318-fba170933dff")
    @Deprecated
    void removeNotes(String moduleName, String noteTypeName);

    /**
     * This method deletes all the notes having this noteType of the type indicated by the (moduleName, noteTypeName) pair.
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name. Cannot be <code>null</code>.
     * @since Modelio 3.8
     */
    @objid ("6b06183e-c850-48c6-9112-7dd416484877")
    void removeNotes(String moduleName, String ownerName, String noteTypeName);

    /**
     * This method deletes all the notes having this note Type .
     * 
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
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param stereotypeName The stereotype to find. Cannot be <code>null</code>.
     */
    @objid ("f26ff6c2-a983-47c2-bf20-913c7e441ed8")
    void removeStereotypes(String moduleName, String stereotypeName);

    /**
     * This method deletes all the tagged values of the type indicated by the (moduleName, tagTypeName) pair.
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @deprecated since Modelio 3.8, it is recommended to use {@link #removeTags(String, String, String)} instead.
     */
    @objid ("00ecbac8-6298-4f80-825f-8975409d150d")
    @Deprecated
    void removeTags(String moduleName, String tagTypeName);

    /**
     * This method deletes all the tagged values of the type indicated by the (moduleName, tagTypeName) pair.
     * 
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName The tagged value type name. Cannot be <code>null</code>.
     * @since Modelio 3.8
     */
    @objid ("3385a7fd-fc43-4f90-ab35-01e306738642")
    void removeTags(String moduleName, String ownerName, String tagTypeName);

    /**
     * This method deletes all the tagged values of given type .
     * 
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
     * 
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
     * 
     * @param moduleName the name of the module providing stereotype. Cannot be <code>null</code>.
     * @param stereotypeName the name of the stereotype providing the table type. Cannot be <code>null</code>.
     * @param key a property name
     * @param value the property value.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the stereotype has not been found.
     * @since Modelio 3.4
     */
    @objid ("5bd36280-3dc5-4bdd-9e7c-c5b1fc16e7f1")
    void setProperty(String moduleName, String stereotypeName, String key, String value) throws ExtensionNotFoundException;

    /**
     * Set a property value.
     * <p>
     * Update the value of a property in a {@link PropertyTable}. If missing, the table itself is created.
     * </p>
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     */
    @objid ("4124e79d-464f-4499-856b-b699923bf476")
    String getName();

    /**
     * Setter for attribute 'ModelElement.Name'
     * 
     * Metamodel description:
     * <i>Name of the element.</i>
     */
    @objid ("f674c278-1a5e-4e5e-b810-6119261ad04e")
    void setName(String value);

    /**
     * Getter for relation 'ModelElement->LocalProperties'
     * 
     * Metamodel description:
     * <i>The local property table.
     * 
     * This table is not copied with the element.
     * This table is not versioned with the element on SVN managed models, it is local to the working copy.</i>
     */
    @objid ("139b94d9-809a-48d6-9c36-f13c5f568a03")
    LocalPropertyTable getLocalProperties();

    /**
     * Setter for relation 'ModelElement->LocalProperties'
     * 
     * Metamodel description:
     * <i>The local property table.
     * 
     * This table is not copied with the element.
     * This table is not versioned with the element on SVN managed models, it is local to the working copy.</i>
     */
    @objid ("2583104a-e8f8-466c-a9de-deea1e8ff7d3")
    void setLocalProperties(LocalPropertyTable value);

    /**
     * Getter for relation 'ModelElement->Extension'
     * 
     * Metamodel description:
     * <i><p>Stereotypes metaclassifying the ModelElement.</p>
     * </i>
     */
    @objid ("2eb38312-fdea-440c-ae57-37a4b5dcf14d")
    EList<Stereotype> getExtension();

    /**
     * Filtered Getter for relation 'ModelElement->Extension'
     * 
     * Metamodel description:
     * <i><p>Stereotypes metaclassifying the ModelElement.</p>
     * </i>
     */
    @objid ("45f1da17-6b50-488c-b1ac-1ad6023dc927")
    <T extends Stereotype> List<T> getExtension(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->DependsOnDependency'
     * 
     * Metamodel description:
     * <i>Designates a Dependency that relates to a supplier ModelElement.</i>
     */
    @objid ("e4e06517-4f51-4598-8573-d195ba46020b")
    EList<Dependency> getDependsOnDependency();

    /**
     * Filtered Getter for relation 'ModelElement->DependsOnDependency'
     * 
     * Metamodel description:
     * <i>Designates a Dependency that relates to a supplier ModelElement.</i>
     */
    @objid ("4da29aff-ba78-41be-a37b-d2d7a0b1ebea")
    <T extends Dependency> List<T> getDependsOnDependency(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Tag'
     * 
     * Metamodel description:
     * <i>TaggedValues annotating the ModelElement.</i>
     */
    @objid ("82287c6b-85d1-4136-b64a-bfa0249ebc99")
    EList<TaggedValue> getTag();

    /**
     * Filtered Getter for relation 'ModelElement->Tag'
     * 
     * Metamodel description:
     * <i>TaggedValues annotating the ModelElement.</i>
     */
    @objid ("b7b66961-419f-42fa-8a4b-8cc8b4125510")
    <T extends TaggedValue> List<T> getTag(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->ImpactedDependency'
     * 
     * Metamodel description:
     * <i>Dependencies whose source depend on this element.</i>
     */
    @objid ("4dd54cf1-351d-4869-b402-c77a8136f746")
    EList<Dependency> getImpactedDependency();

    /**
     * Filtered Getter for relation 'ModelElement->ImpactedDependency'
     * 
     * Metamodel description:
     * <i>Dependencies whose source depend on this element.</i>
     */
    @objid ("93857f42-8ac1-48a8-ab4c-f2b307b23c99")
    <T extends Dependency> List<T> getImpactedDependency(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Properties'
     * 
     * Metamodel description:
     * <i>Owned property tables.</i>
     */
    @objid ("00a4b840-b272-4a7d-869c-a462b7be54da")
    EList<PropertyTable> getProperties();

    /**
     * Filtered Getter for relation 'ModelElement->Properties'
     * 
     * Metamodel description:
     * <i>Owned property tables.</i>
     */
    @objid ("470db812-dfcf-4222-a671-7f8df02beb81")
    <T extends PropertyTable> List<T> getProperties(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Product'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("7535aa0f-3fe1-4bff-b924-d26ff70494de")
    EList<AbstractDiagram> getProduct();

    /**
     * Filtered Getter for relation 'ModelElement->Product'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b95ffdb2-846f-4dad-b156-456d46271d5e")
    <T extends AbstractDiagram> List<T> getProduct(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Descriptor'
     * 
     * Metamodel description:
     * <i>Notes (documentation, code, and so on) describing the ModelElement.</i>
     */
    @objid ("10420350-313a-4987-ac62-b6b8883684f3")
    EList<Note> getDescriptor();

    /**
     * Filtered Getter for relation 'ModelElement->Descriptor'
     * 
     * Metamodel description:
     * <i>Notes (documentation, code, and so on) describing the ModelElement.</i>
     */
    @objid ("0348ea52-8218-42f5-9ee8-df9418233998")
    <T extends Note> List<T> getDescriptor(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Matrix'
     * 
     * Metamodel description:
     * <i>Owned matrices.</i>
     */
    @objid ("e7d51296-caca-491f-bca2-58af7ba9569d")
    EList<MatrixDefinition> getMatrix();

    /**
     * Filtered Getter for relation 'ModelElement->Matrix'
     * 
     * Metamodel description:
     * <i>Owned matrices.</i>
     */
    @objid ("20281702-c0a3-484f-844c-732d2e5b86be")
    <T extends MatrixDefinition> List<T> getMatrix(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->impactImpacted'
     * 
     * Metamodel description:
     * <i>Impact link targeting this element.</i>
     */
    @objid ("dd2d4138-0617-4911-bd8b-4e8d61e025dd")
    EList<ImpactLink> getImpactImpacted();

    /**
     * Filtered Getter for relation 'ModelElement->impactImpacted'
     * 
     * Metamodel description:
     * <i>Impact link targeting this element.</i>
     */
    @objid ("2e999508-2c6e-4320-adeb-3f2668b1488c")
    <T extends ImpactLink> List<T> getImpactImpacted(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->impactDependsOn'
     * 
     * Metamodel description:
     * <i>Impact links from this element. Gives the elements this one depends on.</i>
     */
    @objid ("337adfd7-a746-419c-8242-d7a976ad72be")
    EList<ImpactLink> getImpactDependsOn();

    /**
     * Filtered Getter for relation 'ModelElement->impactDependsOn'
     * 
     * Metamodel description:
     * <i>Impact links from this element. Gives the elements this one depends on.</i>
     */
    @objid ("6ec8f548-b1c3-436e-83cf-d05747fa3705")
    <T extends ImpactLink> List<T> getImpactDependsOn(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Attached'
     * 
     * Metamodel description:
     * <i>Attached resources</i>
     */
    @objid ("bcd32e59-fd43-46b1-85b3-33614edc6d6d")
    EList<AbstractResource> getAttached();

    /**
     * Filtered Getter for relation 'ModelElement->Attached'
     * 
     * Metamodel description:
     * <i>Attached resources</i>
     */
    @objid ("a12efbd1-2bcd-447f-b641-438129474970")
    <T extends AbstractResource> List<T> getAttached(java.lang.Class<T> filterClass);

}
