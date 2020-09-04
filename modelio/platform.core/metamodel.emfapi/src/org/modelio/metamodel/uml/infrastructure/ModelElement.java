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
 * <p>
 * A ModelElement describes every element that can exist in a model. Only low-level Elements are not ModelElements.&nbsp;
 * </p>
 * <p>
 * ModelElements can be extended by Stereotypes and TaggedValues, can have Notes, can be the origin or target of Dependencies, and can have Constraints.
 * </p>
 */
@objid ("00886f12-c4be-1fd8-97fe-001ec947cd2a")
public interface ModelElement extends Element {
    /**
     * The metaclass simple name.
     */
    @objid ("d23ceee5-7e7f-4cf8-a282-5f4ca3dda33c")
    public static final String MNAME = "ModelElement";

    /**
     * The metaclass qualified name.
     */
    @objid ("c0899c74-900c-4aa6-98c0-e428c656e0cc")
    public static final String MQNAME = "Infrastructure.ModelElement";

    /**
     * This method add the stereotype specified by the (moduleName, stereotypeName) pair.
     * <p>
     * Does nothing if the stereotype is already present.
     * </p>
     * @since Modelio 3.4
     * @param moduleName the name of the module providing the type. Cannot be <code>null</code>.
     * @param stereotypeName The stereotype to find. Cannot be <code>null</code>.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the stereotype has not been found.
     */
    @objid ("4601cf60-e004-4047-a69c-5b34dad34860")
    void addStereotype(String moduleName, String stereotypeName) throws ExtensionNotFoundException;

    /**
     * Getter for relation 'ModelElement->Attached'
     * 
     * Metamodel description: <i>Attached resources</i>
     */
    @objid ("a9564807-4ec4-46aa-b441-1da40d9cb371")
    EList<AbstractResource> getAttached();

    /**
     * Filtered Getter for relation 'ModelElement->Attached'
     * 
     * Metamodel description: <i>Attached resources</i>
     */
    @objid ("48ee0fc3-ded8-4a37-9a0f-7a3caec0519f")
    <T extends AbstractResource> List<T> getAttached(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->DependsOnDependency'
     * 
     * Metamodel description: <i>Designates a Dependency that relates to a supplier ModelElement.</i>
     */
    @objid ("d7152675-70b3-45a5-a92b-bb1322b88a2d")
    EList<Dependency> getDependsOnDependency();

    /**
     * Filtered Getter for relation 'ModelElement->DependsOnDependency'
     * 
     * Metamodel description: <i>Designates a Dependency that relates to a supplier ModelElement.</i>
     */
    @objid ("96742781-e8cb-4612-982c-63fa8074b3b5")
    <T extends Dependency> List<T> getDependsOnDependency(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Descriptor'
     * 
     * Metamodel description: <i>Notes (documentation, code, and so on) describing the ModelElement.</i>
     */
    @objid ("e67a4a1d-72ae-4a82-9512-970e406ad9eb")
    EList<Note> getDescriptor();

    /**
     * Filtered Getter for relation 'ModelElement->Descriptor'
     * 
     * Metamodel description: <i>Notes (documentation, code, and so on) describing the ModelElement.</i>
     */
    @objid ("79809e64-78f0-4570-9ec2-0da084e79481")
    <T extends Note> List<T> getDescriptor(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->Extension'
     * 
     * Metamodel description: <i>
     * <p>
     * Stereotypes metaclassifying the ModelElement.
     * </p>
     * </i>
     */
    @objid ("e3b3cb47-f606-40df-9ce0-0daef6fb8fe9")
    EList<Stereotype> getExtension();

    /**
     * Filtered Getter for relation 'ModelElement->Extension'
     * 
     * Metamodel description: <i>
     * <p>
     * Stereotypes metaclassifying the ModelElement.
     * </p>
     * </i>
     */
    @objid ("852176b0-265a-4f89-ba23-47b485ed5f83")
    <T extends Stereotype> List<T> getExtension(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->impactDependsOn'
     * 
     * Metamodel description: <i>Impact links from this element. Gives the elements this one depends on.</i>
     */
    @objid ("d40684e0-a6fb-4148-9b65-153c5d9ebe10")
    EList<ImpactLink> getImpactDependsOn();

    /**
     * Filtered Getter for relation 'ModelElement->impactDependsOn'
     * 
     * Metamodel description: <i>Impact links from this element. Gives the elements this one depends on.</i>
     */
    @objid ("9ad94c78-a67f-457b-b5ab-9464e18d6fa5")
    <T extends ImpactLink> List<T> getImpactDependsOn(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->impactImpacted'
     * 
     * Metamodel description: <i>Impact link targeting this element.</i>
     */
    @objid ("22f65ec4-1376-4fe2-be61-20c96cd194b6")
    EList<ImpactLink> getImpactImpacted();

    /**
     * Filtered Getter for relation 'ModelElement->impactImpacted'
     * 
     * Metamodel description: <i>Impact link targeting this element.</i>
     */
    @objid ("68314c17-956f-4fe7-ab8a-51ca2ff9d29f")
    <T extends ImpactLink> List<T> getImpactImpacted(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->ImpactedDependency'
     * 
     * Metamodel description: <i>Dependencies whose source depend on this element.</i>
     */
    @objid ("a7e12cb2-e7fd-4278-9901-402231f67751")
    EList<Dependency> getImpactedDependency();

    /**
     * Filtered Getter for relation 'ModelElement->ImpactedDependency'
     * 
     * Metamodel description: <i>Dependencies whose source depend on this element.</i>
     */
    @objid ("4aa79fd4-b8cd-4fab-a76e-66484394c5fb")
    <T extends Dependency> List<T> getImpactedDependency(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModelElement->LocalProperties'
     * 
     * Metamodel description: <i>The local property table.
     * 
     * This table is not copied with the element. This table is not versioned with the element on SVN managed models, it is local to the working copy.</i>
     */
    @objid ("621eb6f3-ea7e-4194-8efc-8f8ba913988c")
    LocalPropertyTable getLocalProperties();

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
     * Getter for relation 'ModelElement->Matrix'
     * 
     * Metamodel description: <i>Owned matrices.</i>
     */
    @objid ("e9399e7a-9135-412a-8c8a-c41ae7cbc81e")
    EList<MatrixDefinition> getMatrix();

    /**
     * Filtered Getter for relation 'ModelElement->Matrix'
     * 
     * Metamodel description: <i>Owned matrices.</i>
     */
    @objid ("6e6319f5-e143-4e09-ab97-630a8afb3659")
    <T extends MatrixDefinition> List<T> getMatrix(java.lang.Class<T> filterClass);

    /**
     * Getter for attribute 'ModelElement.Name'
     * 
     * Metamodel description: <i>Name of the element.</i>
     */
    @objid ("d9a3a564-e1fe-4849-a75e-e889baff86c7")
    @Override
    String getName();

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
     * Getter for relation 'ModelElement->Product'
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("bd76a5d3-c643-4226-b5e2-d0e25fe64d12")
    EList<AbstractDiagram> getProduct();

    /**
     * Filtered Getter for relation 'ModelElement->Product'
     * 
     * Metamodel description: <i>null</i>
     */
    @objid ("ebf999ea-0ca8-4444-9583-bc07b66888f2")
    <T extends AbstractDiagram> List<T> getProduct(java.lang.Class<T> filterClass);

    /**
     * Get the first found {@link PropertyTable} that has the given name.
     * @param name a property table name
     * @return the found table or <code>null</code>.
     */
    @objid ("45c55b2e-2831-11e2-bf07-001ec947ccaf")
    PropertyTable getProperties(String name);

    /**
     * Getter for relation 'ModelElement->Properties'
     * 
     * Metamodel description: <i>Owned property tables.</i>
     */
    @objid ("68e72a3c-a1d9-48f7-b27d-2b999d0860a7")
    EList<PropertyTable> getProperties();

    /**
     * Filtered Getter for relation 'ModelElement->Properties'
     * 
     * Metamodel description: <i>Owned property tables.</i>
     */
    @objid ("a1583d00-037f-484f-83f4-a7f064310c4d")
    <T extends PropertyTable> List<T> getProperties(java.lang.Class<T> filterClass);

    /**
     * Get the property table for the properties defined by the given stereotype.
     * @since 3.8
     * @param stereotype a stereotype, must not be <code>null</code>.
     * @return the found property table or <code>null</code>.
     */
    @objid ("67d0e07c-b242-484f-8696-beb6eb818c1b")
    TypedPropertyTable getProperties(Stereotype stereotype);

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
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException when the Stereotype has not been found.
     */
    @objid ("a102d222-688b-45ac-bc95-5d68fc907403")
    String getProperty(String moduleName, String stereotypeName, String key) throws ExtensionNotFoundException;

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
    @objid ("b56da8d3-2494-4b69-8f03-752a223aab13")
    String getProperty(Stereotype stereotype, String key);

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
     * Getter for relation 'ModelElement->Tag'
     * 
     * Metamodel description: <i>TaggedValues annotating the ModelElement.</i>
     */
    @objid ("68c6dd3b-65b9-43ca-86b1-94269e6b9815")
    EList<TaggedValue> getTag();

    /**
     * Filtered Getter for relation 'ModelElement->Tag'
     * 
     * Metamodel description: <i>TaggedValues annotating the ModelElement.</i>
     */
    @objid ("f54f8fe3-dbf6-4670-8c11-784b09e40a1d")
    <T extends TaggedValue> List<T> getTag(java.lang.Class<T> filterClass);

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
     * Setter for relation 'ModelElement->LocalProperties'
     * 
     * Metamodel description: <i>The local property table.
     * 
     * This table is not copied with the element. This table is not versioned with the element on SVN managed models, it is local to the working copy.</i>
     */
    @objid ("7cf1fd84-1496-4b81-bb31-f69a43d6a898")
    void setLocalProperties(LocalPropertyTable value);

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
     * Setter for attribute 'ModelElement.Name'
     * 
     * Metamodel description: <i>Name of the element.</i>
     */
    @objid ("8d6ef054-42df-4d99-bcd6-5f1d69d9c83d")
    @Override
    void setName(String value);

    /**
     * Set a property value.
     * <p>
     * Update the value of a property in a stereotype's {@link TypedPropertyTable}. If missing, the table itself is created.
     * </p>
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
     * @param tableName The table name. The table may not exist.
     * @param key a property name
     * @param value the property value.
     * @since Modelio 3.4
     */
    @objid ("adf13e7a-b7eb-4f56-bb54-02a378df1260")
    void setProperty(String tableName, String key, String value);

    /**
     * Set a property value.
     * <p>
     * Update the value of a property in a stereotype's {@link TypedPropertyTable}. If missing, the table itself is created.
     * </p>
     * @param stereotypeName the stereotype providing the table type. Cannot be <code>null</code>.
     * @param key a property name
     * @param value the property value.
     * @since Modelio 3.8
     */
    @objid ("9295a084-7a27-4647-8321-915efeccb93e")
    void setProperty(Stereotype stereotype, String key, String value);

}
