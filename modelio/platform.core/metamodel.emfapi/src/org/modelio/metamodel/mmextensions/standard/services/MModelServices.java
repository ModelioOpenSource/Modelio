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

package org.modelio.metamodel.mmextensions.standard.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.model.api.IElementConfigurator;
import org.modelio.vcore.model.api.IElementNamer;
import org.modelio.vcore.model.api.IModelFactoryService;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.UnknownMetaclassException;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;

/**
 * Implementation of {@link IMModelServices}.
 */
@objid ("0078f866-030f-1035-9f91-001ec947cd2a")
public class MModelServices implements IMModelServices {
    @objid ("007915b2-030f-1035-9f91-001ec947cd2a")
    private ICoreSession session;

    @objid ("954645c8-3e1e-4da5-8659-41fb761424dc")
    private MTools tools;

    @objid ("5bf7bb44-d899-4975-9beb-b546c00f01bf")
    private ExtensionCache extensionCache;

    /**
     * @param session a core modeling session.
     */
    @objid ("007930ce-030f-1035-9f91-001ec947cd2a")
    public MModelServices(ICoreSession session) {
        Objects.requireNonNull(session);
        this.session = session;
        this.tools = MTools.get(session);
        this.extensionCache = new ExtensionCache(session.getMetamodel());
    }

    @objid ("113984af-1772-11e2-aa0d-002564c97630")
    @Override
    public Collection<? extends MObject> findByAtt(MClass metaclass, String att, Object val) {
        return getModel().findByAtt(metaclass, true, att, val);
    }

    @objid ("1139abc3-1772-11e2-aa0d-002564c97630")
    @Override
    public Collection<? extends MObject> findByClass(MClass metaclass) {
        return getModel().findByClass(metaclass, true);
    }

    @objid ("1139d2d6-1772-11e2-aa0d-002564c97630")
    @Override
    public MObject findById(MClass metaclass, final String id) {
        return getModel().findById(metaclass, id);
    }

    @objid ("1139f9e7-1772-11e2-aa0d-002564c97630")
    @Override
    public MObject findByRef(MRef ref) throws UnknownMetaclassException {
        return getModel().findByRef(ref);
    }

    @objid ("008253de-030f-1035-9f91-001ec947cd2a")
    @Override
    public String getCompositionPath(MObject mObject) {
        String name = mObject.getName();
        
        // TODO escape '/'
        
        SmObjectImpl owner = ((SmObjectImpl) mObject).getCompositionOwner();
        if (owner != null) {
            return getCompositionPath(owner) + "/" + name;
        }
        return name;
    }

    @objid ("b3def6de-0d78-4b7d-8f52-efb2a8a4c340")
    @Override
    public IElementConfigurator getElementConfigurer() {
        return this.tools.getConfigurator();
    }

    @objid ("00799014-030f-1035-9f91-001ec947cd2a")
    @Override
    public IElementNamer getElementNamer() {
        return this.tools.getNamer();
    }

    @objid ("d0f67355-951f-4930-a8c4-1e99f302bfbd")
    @Override
    public MMetamodel getMetamodel() {
        return this.session.getMetamodel();
    }

    @objid ("00796594-030f-1035-9f91-001ec947cd2a")
    @Override
    public IModelFactoryService getModelFactory() {
        return this.tools.getModelFactories();
    }

    @objid ("007e32ae-030f-1035-9f91-001ec947cd2a")
    @Override
    public List<NoteType> findNoteTypes(String moduleName, String ownerName, String noteTypeName, MClass metaclass) {
        List<NoteType> ret = new ArrayList<>();
        
        for (ModuleComponent module : getModel().findByClass(ModuleComponent.class)) {
            if (nameMatches(module, moduleName)) {
                for (Profile profile : module.getOwnedProfile()) {
                    for (MetaclassReference reference : profile.getOwnedReference()) {
                        try {
                            MClass referenceClass = getBaseClass(reference.getReferencedClassName());
                            if (metaclass.hasBase(referenceClass) && inheritsFrom(referenceClass, ownerName)) {
                                for (NoteType noteType : reference.getDefinedNoteType()) {
                                    if (nameMatches(noteType, noteTypeName)) {
                                        ret.add(noteType);
                                    }
                                }
                            }
                        } catch (MetaclassNotFoundException e) {
                            reportBadMetaclass(reference, e);
                        }
                    }
        
                    for (Stereotype ste : profile.getDefinedStereotype()) {
                        if (nameMatches(ste.getModule(), moduleName)) {
                            try {
                                MClass steClass = getBaseClass(ste.getBaseClassName());
                                if (metaclass.hasBase(steClass) && inheritsFrom(ste, ownerName)) {
                                    for (NoteType noteType : ste.getDefinedNoteType()) {
                                        if (nameMatches(noteType, noteTypeName)) {
                                            ret.add(noteType);
                                        }
                                    }
                                }
                            } catch (MetaclassNotFoundException e) {
                                reportBadMetaclass(ste, e);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    @objid ("007f38de-030f-1035-9f91-001ec947cd2a")
    @Override
    public List<ResourceType> findResourceTypes(String moduleName, String ownerName, String resourceTypeName, MClass metaclass) {
        List<ResourceType> ret = new ArrayList<>();
        
        for (ModuleComponent module : getModel().findByClass(ModuleComponent.class)) {
            if (nameMatches(module, moduleName)) {
                for (Profile profile : module.getOwnedProfile()) {
                    for (MetaclassReference reference : profile.getOwnedReference()) {
                        try {
                            MClass referenceClass = getBaseClass(reference.getReferencedClassName());
                            if (metaclass.hasBase(referenceClass) && inheritsFrom(referenceClass, ownerName)) {
                                for (ResourceType resourceType : reference.getDefinedResourceType()) {
                                    if (nameMatches(resourceType, resourceTypeName)) {
                                        ret.add(resourceType);
                                    }
                                }
                            }
                        } catch (MetaclassNotFoundException e) {
                            reportBadMetaclass(reference, e);
                        }
                    }
        
                    for (Stereotype ste : profile.getDefinedStereotype()) {
                        if (nameMatches(ste.getModule(), moduleName)) {
                            try {
                                MClass steClass = getBaseClass(ste.getBaseClassName());
                                if (metaclass.hasBase(steClass) && inheritsFrom(ste, ownerName)) {
                                    for (ResourceType resourceType : ste.getDefinedResourceType()) {
                                        if (nameMatches(resourceType, resourceTypeName)) {
                                            ret.add(resourceType);
                                        }
                                    }
                                }
                            } catch (MetaclassNotFoundException e) {
                                reportBadMetaclass(ste, e);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    @objid ("007cd77e-030f-1035-9f91-001ec947cd2a")
    @Override
    public List<Stereotype> findStereotypes(String moduleName, String stereotypeName, MClass metaclass) {
        List<Stereotype> ret = new ArrayList<>();
        
        for (ModuleComponent module : getModel().findByClass(ModuleComponent.class)) {
            if (nameMatches(module, moduleName)) {
                for (Profile profile : module.getOwnedProfile()) {
                    for (Stereotype ste : profile.getDefinedStereotype()) {
                        try {
                            MClass steClass = getBaseClass(ste.getBaseClassName());
                            if (metaclass.hasBase(steClass) && nameMatches(ste, stereotypeName)) {
                                ret.add(ste);
                            }
                        } catch (MetaclassNotFoundException e) {
                            reportBadMetaclass(ste, e);
                        }
                    }
                }
            }
        }
        return ret;
    }

    @objid ("7c8f22c6-8750-45f3-879c-f2eb1d3e14ea")
    @Override
    @Deprecated
    public List<Stereotype> findStereotypes(String moduleName, String stereotypeName, String metaclass) throws UnknownMetaclassException {
        return findStereotypes(moduleName, stereotypeName, resolveMetaclass(metaclass));
    }

    @objid ("008093fa-030f-1035-9f91-001ec947cd2a")
    @Override
    public List<TagType> findTagTypes(String moduleName, String ownerName, String tagTypeName, MClass metaclass) {
        List<TagType> ret = new ArrayList<>();
        
        for (ModuleComponent module : getModel().findByClass(ModuleComponent.class)) {
            if (nameMatches(module, moduleName)) {
                for (Profile profile : module.getOwnedProfile()) {
                    for (MetaclassReference reference : profile.getOwnedReference()) {
                        try {
                            MClass referenceClass = getBaseClass(reference.getReferencedClassName());
                            if (metaclass.hasBase(referenceClass) && inheritsFrom(referenceClass, ownerName)) {
                                for (TagType tagType : reference.getDefinedTagType()) {
                                    if (nameMatches(tagType, tagTypeName)) {
                                        ret.add(tagType);
                                    }
                                }
                            }
                        } catch (MetaclassNotFoundException e) {
                            reportBadMetaclass(reference, e);
                        }
                    }
        
                    for (Stereotype ste : profile.getDefinedStereotype()) {
                        if (nameMatches(ste.getModule(), moduleName)) {
                            try {
                                MClass steClass = getBaseClass(ste.getBaseClassName());
                                if (metaclass.hasBase(steClass) && inheritsFrom(ste, ownerName)) {
                                    for (TagType tagType : ste.getDefinedTagType()) {
                                        if (nameMatches(tagType, tagTypeName)) {
                                            ret.add(tagType);
                                        }
                                    }
                                }
                            } catch (MetaclassNotFoundException e) {
                                reportBadMetaclass(ste, e);
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }

    @objid ("007ddfac-030f-1035-9f91-001ec947cd2a")
    @Override
    public NoteType getNoteType(String moduleName, String ownerName, String noteTypeName, MClass metaclass) throws ElementNotUniqueException {
        NoteType type = this.extensionCache.getNoteType(moduleName, ownerName, noteTypeName, metaclass);
        if (type != null) {
            return type;
        }
        
        List<NoteType> noteTypes = findNoteTypes(moduleName, ownerName, noteTypeName, metaclass);
        if (noteTypes.isEmpty()) {
            return null;
        } else if (noteTypes.size() == 1) {
            type = noteTypes.get(0);
            this.extensionCache.add(moduleName, metaclass, type);
            return type;
        } else {
            throw new ElementNotUniqueException(MessageFormat.format("''{0}'' is not a unique NoteType for ''{1}'' metaclass in ''{2}'' module.", noteTypeName, metaclass.getQualifiedName(), moduleName));
        }
    }

    @objid ("007f9838-030f-1035-9f91-001ec947cd2a")
    @Override
    public ResourceType getResourceType(String moduleName, String ownerName, String resourceTypeName, MClass metaclass) throws ElementNotUniqueException {
        ResourceType type = this.extensionCache.getResourceType(moduleName, ownerName, resourceTypeName, metaclass);
        if (type != null) {
            return type;
        }
        
        List<ResourceType> resourceTypes = findResourceTypes(moduleName, ownerName, resourceTypeName, metaclass);
        if (resourceTypes.isEmpty()) {
            return null;
        } else if (resourceTypes.size() == 1) {
            type = resourceTypes.get(0);
            this.extensionCache.add(moduleName, metaclass, type);
            return type;
        } else {
            throw new ElementNotUniqueException(MessageFormat.format("''{0}'' is not a unique ResourceType for ''{1}'' metaclass in ''{2}'' module.", resourceTypeName, metaclass.getQualifiedName(), moduleName));
        }
    }

    @objid ("5a88b537-7d84-42a0-ad2a-1b48243e17e5")
    @Override
    public Stereotype getStereotype(String moduleName, String stereotypeName, MClass metaclass) throws ElementNotUniqueException {
        List<Stereotype> results = findStereotypes(moduleName, stereotypeName, metaclass);
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        } else {
            throw new ElementNotUniqueException(stereotypeName + " is not a unique Stereotype for " + metaclass.getName());
        }
    }

    @objid ("5d5198bb-ac79-4cb1-a304-4d44e649cae7")
    @Override
    @Deprecated
    public Stereotype getStereotype(String moduleName, String stereotypeName, String metaclass) throws ElementNotUniqueException, UnknownMetaclassException {
        return getStereotype(moduleName, stereotypeName, resolveMetaclass(metaclass));
    }

    @objid ("0080f4a8-030f-1035-9f91-001ec947cd2a")
    @Override
    public TagType getTagType(String moduleName, String ownerType, String tagTypeName, MClass metaclass) throws ElementNotUniqueException {
        TagType type = this.extensionCache.getTagType(moduleName, ownerType, tagTypeName, metaclass);
        if (type != null) {
            return type;
        }
        
        List<TagType> tagTypes = findTagTypes(moduleName, ownerType, tagTypeName, metaclass);
        if (tagTypes.isEmpty()) {
            return null;
        } else if (tagTypes.size() == 1) {
            type = tagTypes.get(0);
            this.extensionCache.add(moduleName, metaclass, type);
            return type;
        } else {
            throw new ElementNotUniqueException(tagTypeName + " is not a unique TagType for " + metaclass.getName() + " in " + moduleName);
        }
    }

    /**
     * Invalidate the service and prevent further usage.
     * @param object a dummy parameter
     */
    @objid ("00913a16-0eae-1035-9f91-001ec947cd2a")
    public void invalidateProject(Object object) {
        this.session = null;
        this.tools = null;
    }

    @objid ("00819d40-030f-1035-9f91-001ec947cd2a")
    private void findElementRecursive(MObject searchRoot, String[] tokens, int tokenIndex, List<MObject> results, Class<? extends org.modelio.vcore.smkernel.mapi.MObject> metaclass) {
        for (SmObjectImpl child : ((SmObjectImpl) searchRoot).getCompositionChildren()) {
            if (nameMatches(child, tokens[tokenIndex])) {
                if (tokens.length - 1 == tokenIndex) {
                    if (metaclass == null || metaclass.isInstance(child)) {
                        results.add(child);
                    }
                } else {
                    findElementRecursive(child, tokens, tokenIndex + 1, results, metaclass);
                }
            }
        }
    }

    @objid ("c3282876-edee-11e1-84b0-002564c97630")
    private MClass getBaseClass(MetaclassReference classRef, Stereotype ste) {
        if (ste != null) {
            try {
                return getBaseClass(ste.getBaseClassName());
            } catch (MetaclassNotFoundException e) {
                reportBadMetaclass(ste, e);
                return null;
            }
        } else if (classRef != null) {
            try {
                return getBaseClass(classRef.getReferencedClassName());
            } catch (MetaclassNotFoundException e) {
                reportBadMetaclass(classRef, e);
                return null;
            }
        }
        return null;
    }

    @objid ("c32b35b7-edee-11e1-84b0-002564c97630")
    private MClass getBaseClass(String baseName) throws MetaclassNotFoundException {
        MClass smBase = this.session.getMetamodel().getMClass(baseName);
        
        if (smBase == null) {
            throw new MetaclassNotFoundException(baseName);
        }
        return smBase;
    }

    @objid ("84203a74-2f0f-11e2-8f81-001ec947ccaf")
    private IModel getModel() throws IllegalStateException {
        if (this.session == null) {
            throw new IllegalStateException("The project is not open.");
        }
        return this.session.getModel();
    }

    @objid ("008224b8-030f-1035-9f91-001ec947cd2a")
    private boolean nameMatches(MObject elt, String pattern) {
        String name = elt != null ? elt.getName() : "";
        return name.matches((pattern == null || pattern.isEmpty()) ? ".*" : pattern) || name.equals(pattern);
    }

    @objid ("841b75ed-2f0f-11e2-8f81-001ec947ccaf")
    private void reportBadMetaclass(MObject el, MetaclassNotFoundException e) {
        Log.warning(el.toString() + ": " + e.getMessage());
    }

    @objid ("fd343412-eeab-4eda-a69a-f27735c4f929")
    private MClass resolveMetaclass(String metaclass) throws UnknownMetaclassException {
        MClass mc = this.session.getMetamodel().getMClass(metaclass);
        if (mc != null) {
            return mc;
        } else {
            throw new UnknownMetaclassException(metaclass);
        }
    }

    /**
     * Answer to the question: does 'stereotype' or one of its inheritance parent matches the given 'stereotypeName'.
     * @param stereotype a stereotype
     * @param stereotypeName the name of a stereotype.
     * @return <code>true</code> if 'stereotype' or one of its inheritance parent matches the given 'stereotypeName'.
     */
    @objid ("cbc7dbe7-cb47-4edb-acbd-1cbcfe6bc7e7")
    private boolean inheritsFrom(Stereotype stereotype, String stereotypeName) {
        if (nameMatches(stereotype, stereotypeName)) {
            return true;
        }
        
        if (stereotype.getParent() != null) {
            if (inheritsFrom(stereotype.getParent(), stereotypeName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Answer to the question: does 'metaclass' or one of its inheritance parent matches the given 'metaclassName'.
     * @param metaclass a metaclass
     * @param metaclassName the name of a metaclass.
     * @return <code>true</code> if 'metaclass' or one of its inheritance parent matches the given 'metaclassName'.
     */
    @objid ("99cc2ba6-37dc-4908-a49f-1dc8fb85478f")
    private boolean inheritsFrom(MClass metaclass, String metaclassName) {
        String name = metaclass.getQualifiedName();
        if (name.matches((metaclassName == null || metaclassName.isEmpty()) ? ".*" : metaclassName) || name.equals(metaclassName)) {
            return true;
        }
        
        if (metaclass.getSuper() != null) {
            if (inheritsFrom(metaclass.getSuper(), metaclassName)) {
                return true;
            }
        }
        return false;
    }

}
