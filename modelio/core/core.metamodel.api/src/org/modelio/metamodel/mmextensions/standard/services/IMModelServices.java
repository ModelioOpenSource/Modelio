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

package org.modelio.metamodel.mmextensions.standard.services;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IModelFactoryService;
import org.modelio.vcore.session.UnknownMetaclassException;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Services to look for model elements and metamodel extensions.
 * <p>
 * TODO : to be split in metamodel independent and metamodel dependent parts.
 */
@objid ("00833f7e-030f-1035-9f91-001ec947cd2a")
public interface IMModelServices {
    /**
     * Get elements in the model from a metaclass, a meta attribute name and its value.
     * <p>
     * If <code>Element</code> is given, every elements in the model will be checked.
     * </p>
     * 
     * @param metaclass the metaclass to look for.
     * @param att the name of the meta attribute to check the value in.
     * @param value the value to look for in the attribute.
     * @return A collection of elements matching the parameters. Might be empty but never <code>null</code>.
     */
    @objid ("1126bf84-1772-11e2-aa0d-002564c97630")
    Collection<? extends MObject> findByAtt(MClass metaclass, final String att, Object value);

    /**
     * Get all elements in the model from their metaclass.
     * 
     * @param metaclass the metaclass to look for.
     * @return A list of all model elements, or null if no match is found.
     */
    @objid ("112d284e-1772-11e2-aa0d-002564c97630")
    Collection<? extends MObject> findByClass(MClass metaclass);

    /**
     * Get an element in the model from its id and metaclass.
     * 
     * @param metaclass the java interface of the metaclass to look for.
     * @param id the id to look for.
     * @return A model element, or null if no match is found.
     */
    @objid ("112d4f62-1772-11e2-aa0d-002564c97630")
    MObject findById(MClass metaclass, final String id);

    /**
     * Get an element in the model from an {@link org.modelio.vcore.smkernel.mapi.MRef}.<br>
     * 
     * @param ref a model object reference.
     * @return A model element, or null if no match is found.
     * @throws org.modelio.vcore.session.UnknownMetaclassException when the reference's metaclass can't be resolved as a {@link MClass}
     */
    @objid ("112d9d82-1772-11e2-aa0d-002564c97630")
    MObject findByRef(MRef ref) throws UnknownMetaclassException;

    @objid ("00885fe0-030f-1035-9f91-001ec947cd2a")
    String getCompositionPath(MObject mObject);

    @objid ("10019844-0a1f-42a1-8bdc-7d83bbaf6271")
    IElementConfigurator getElementConfigurer();

    @objid ("008361e8-030f-1035-9f91-001ec947cd2a")
    IElementNamer getElementNamer();

    @objid ("95074bff-6e8a-46e8-bbc8-ab967fd380b3")
    MMetamodel getMetamodel();

    /**
     * Get the model object creation factory.
     * 
     * @return the model factory.
     */
    @objid ("0083559a-030f-1035-9f91-001ec947cd2a")
    IModelFactoryService getModelFactory();

    /**
     * Find a note type by name and metaclass.
     * 
     * @param moduleName the name of the module owing the note type, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any note type.
     * @param metaclass a metaclass the note type must be applicable to.
     * @return the found note type. Might be <code>null</code> is no element matches the given parameters.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException when resolution is ambiguous and several note types match the given parameters
     */
    @objid ("0085e42c-030f-1035-9f91-001ec947cd2a")
    NoteType getNoteType(String moduleName, String ownerName, String noteTypeName, MClass metaclass) throws ElementNotUniqueException;

    /**
     * Find note types by name and metaclass
     * 
     * @param moduleName the name of the module owing the note type, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param noteTypeName the note type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any note type.
     * @param metaclass the metaclass .
     * @return the found note types. Might be empty.
     */
    @objid ("008618ca-030f-1035-9f91-001ec947cd2a")
    List<NoteType> findNoteTypes(String moduleName, String ownerName, String noteTypeName, MClass metaclass);

    /**
     * Find a resource type by name and metaclass.
     * 
     * @param moduleName the name of the module owing the resource type, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param resourceTypeName the resource type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any resource type.
     * @param metaclass a metaclass the resource type must be applicable to.
     * @return the found resource type. Might be <code>null</code> is no element matches the given parameters.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException when resolution is ambiguous and several resource types match the given parameters
     */
    @objid ("00870aaa-030f-1035-9f91-001ec947cd2a")
    ResourceType getResourceType(String moduleName, String ownerName, String resourceTypeName, MClass metaclass) throws ElementNotUniqueException;

    /**
     * Find resource types by name and metaclass
     * 
     * @param moduleName the name of the module owing the resource type, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param externDocumentTypeName the resource type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any resource type.
     * @param metaclass the metaclass .
     * @return the found resource types. Might be empty.
     */
    @objid ("0086c98c-030f-1035-9f91-001ec947cd2a")
    List<ResourceType> findResourceTypes(String moduleName, String ownerName, String externDocumentTypeName, MClass metaclass);

    /**
     * Find a stereotype by name and metaclass.
     * 
     * @param moduleName the name of the module owing the stereotype, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param stereotypeName the stereotype name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any stereotype.
     * @param metaclass a metaclass the stereotype must be applicable to.
     * @return the found stereotype. Might be <code>null</code> is no element matches the given parameters.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException when resolution is ambiguous and several stereotypes match the given parameters
     */
    @objid ("3afd10ed-9b88-4971-9a1e-5a7a167f8cbb")
    Stereotype getStereotype(String moduleName, String stereotypeName, MClass metaclass) throws ElementNotUniqueException;

    /**
     * Find a stereotype by name and metaclass.
     * 
     * @param moduleName the name of the module owing the stereotype, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param stereotypeName the stereotype name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any stereotype.
     * @param metaclass a metaclass the stereotype must be applicable to.
     * @return the found stereotype. Might be <code>null</code> is no element matches the given parameters.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException when resolution is ambiguous and several stereotypes match the given parameters
     * @throws org.modelio.vcore.session.UnknownMetaclassException when metaclass can't be resolved as a {@link MClass}
     */
    @objid ("59f03700-2573-4d19-9d7a-64b19b3dc89c")
    @Deprecated
    Stereotype getStereotype(String moduleName, String stereotypeName, String metaclass) throws ElementNotUniqueException, UnknownMetaclassException;

    /**
     * Find stereotypes by name and metaclass
     * 
     * @param moduleName the name of the module owing the stereotype, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param stereotypeName the stereotype name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any stereotype.
     * @param metaclass the metaclass .
     * @return the found stereotypes. Might be empty.
     */
    @objid ("0085345a-030f-1035-9f91-001ec947cd2a")
    List<Stereotype> findStereotypes(String moduleName, String stereotypeName, MClass metaclass);

    /**
     * Find stereotypes by name and metaclass
     * 
     * @param moduleName the name of the module owing the stereotype, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param stereotypeName the stereotype name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any stereotype.
     * @param metaclass the metaclass name. Using a qualified name is strongly recommended.
     * @return the found stereotypes.
     * @throws org.modelio.vcore.session.UnknownMetaclassException if the metaclass name does not match an existing metaclass.
     */
    @objid ("c8d8eda1-adb8-4075-aa6c-3522a9cf12e0")
    @Deprecated
    List<Stereotype> findStereotypes(String moduleName, String stereotypeName, String metaclass) throws UnknownMetaclassException;

    /**
     * Find a tag type by name and metaclass.
     * 
     * @param moduleName the name of the module owing the tag type, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName the tag type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any tag type.
     * @param metaclass a metaclass the tag type must be applicable to.
     * @return the found tag type. Might be <code>null</code> is no element matches the given parameters.
     * @throws org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException when resolution is ambiguous and several tag types match the given parameters
     */
    @objid ("0087f366-030f-1035-9f91-001ec947cd2a")
    TagType getTagType(String moduleName, String ownerName, String tagTypeName, MClass metaclass) throws ElementNotUniqueException;

    /**
     * Find tag types by name and metaclass
     * 
     * @param moduleName the name of the module owing the tag type, or a regular expression for module name matching. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any module.
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type. Cannot be <code>null</code>.
     * @param tagTypeName the tag type name, or a regular expression. <code>null</code> or <code>""</code> are interpreted as <code>".*"</code>, i.e. any tag type.
     * @param metaclass the metaclass.
     * @return the found tag types. Might be empty.
     */
    @objid ("0087b040-030f-1035-9f91-001ec947cd2a")
    List<TagType> findTagTypes(String moduleName, String ownerName, String tagTypeName, MClass metaclass);

}
