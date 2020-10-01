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

package org.modelio.gproject.ramc.core.packaging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.ramc.core.model.ModelComponent;
import org.modelio.gproject.ramc.core.packaging.filters.ConfigurableModelFilter;
import org.modelio.gproject.ramc.core.packaging.filters.RamcFilterBuilder;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.MModelServices;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vcore.model.CompositionGetter.IStopFilter;
import org.modelio.vcore.model.CompositionGetter;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.model.filter.IObjectFilter;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.session.impl.SmFactory;
import org.modelio.vcore.smkernel.IRStatus;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("5ff5fb2f-9e9e-11e1-a22d-001ec947ccaf")
class ModelExporter {
    @objid ("bb83a292-4467-11e2-b513-002564c97630")
    private final ICoreSession targetSession;

    @objid ("f4801d3c-ae27-4ec4-af64-820b6adf6500")
    private final Map<String, MObject> aliases = new HashMap<>();

    @objid ("99625d2d-cc56-4e9c-aa46-3311ca599fe6")
    private final DepWalker depWalker = new DepWalker();

    @objid ("9247aa20-bb83-11e1-9fd3-001ec947ccaf")
    private final Set<MObject> done = new HashSet<>();

    @objid ("968cea54-8776-492c-b216-20351dac32f3")
    private IObjectFilter modelFilter;

    @objid ("9247aa1d-bb83-11e1-9fd3-001ec947ccaf")
    private final Set<MObject> objectsToExternalize = new HashSet<>();

    @objid ("9247aa27-bb83-11e1-9fd3-001ec947ccaf")
    private final IRepository targetRepository;

    /**
     * Metamodel fragments found during export.
     */
    @objid ("430b4f6c-4916-42f4-b05d-c1cd17f6ae7a")
    private final Set<MMetamodelFragment> mmFragments = new HashSet<>();

    @objid ("662ae273-f403-466e-bf03-887e3fd1da89")
    private final ICoreSession srcSession;

    @objid ("1e2f0f8d-c916-4efd-ae68-9ab5d0a08ebc")
    private final MClass MODELELEMENT_MCLASS;

    @objid ("c2e03fe8-a5b8-11e1-aa98-001ec947ccaf")
    public ModelExporter(final ICoreSession srcSession, final ICoreSession targetSession, final IRepository targetRepository) {
        assert (srcSession != null);
        assert (targetSession != null);
        assert (targetRepository != null);
        
        this.srcSession = srcSession;
        this.targetSession = targetSession;
        this.targetRepository = targetRepository;
        this.MODELELEMENT_MCLASS = this.srcSession.getMetamodel().getMClass(ModelElement.class);
    }

    @objid ("ac6e882e-a419-11e1-aa98-001ec947ccaf")
    public void addObject(final MObject o) {
        this.objectsToExternalize.add(o);
    }

    @objid ("6e27ea41-a8d2-4e5c-aee7-dbc68daa1361")
    public void configureModelExporter(ModelComponent ramc, boolean exportArtifact, List<IModelComponentContributor> contributors) {
        // The ramc artifact itself
        if (exportArtifact) {
            addObject(ramc.getArtifact());
        }
        
        // Elements directly manifested by the RAMC
        for (Element e : ramc.getExportedElements()) {
            addObject(e);
        }
        
        RamcFilterBuilder builder = new RamcFilterBuilder(this.srcSession.getMetamodel(), ramc.getArtifact());
        
        for (IModelComponentContributor contributor : contributors) {
            // Additional elements by contributor
            for (MObject o : contributor.getElements()) {
                if (o != null) {
                    addObject(o);
                }
            }
            // Filter configuration by contributor
            for (NoteType type : contributor.getNoteTypes()) {
                if (type != null) {
                    builder.addNoteType(type);
                }
            }
            for (TagType type : contributor.getTagTypes()) {
                if (type != null) {
                    builder.addTagType(type);
                }
            }
            for (Stereotype type : contributor.getDependencyStereotypes()) {
                if (type != null) {
                    builder.addDependencyStereotype(type);
                }
            }
        }
        
        // Filter configured by contributors
        setModelFilter(builder.getModelFilter());
    }

    @objid ("ac70ea45-a419-11e1-aa98-001ec947ccaf")
    public void run(Metadatas metadatas) {
        assert (this.modelFilter != null);
        
        createEmptyObjects();
        
        // Export the manifested objects
        for (MObject anObject : this.objectsToExternalize) {
            externalizeObject(anObject);
        }
        
        // Export the stub objects, this will also compute root elements
        Set<MObject> stubs = computeStubObjectsToExport(metadatas);
        externalizeStubObject(stubs);
        
        // Remap roots: as some root elements might have been exported as
        // aliases...
        for (MRef root : metadatas.getRoots()) {
            if (this.aliases.containsKey(root.uuid)) {
                // remap it
                root.uuid = this.aliases.get(root.uuid).getUuid();
            }
        }
        
        this.done.clear();
        
        // Save used metamodel fragments in metadatas
        metadatas.setUsedMetamodelFragments(this.mmFragments);
    }

    @objid ("e61232c9-d02c-11e1-a8eb-001ec947ccaf")
    public void setModelFilter(ConfigurableModelFilter modelFilter) {
        this.modelFilter = modelFilter;
    }

    /**
     * Compute and return the stub objects to export. Register the found roots in metadatas.
     * 
     * @param metadatas @return
     */
    @objid ("c2e03fee-a5b8-11e1-aa98-001ec947ccaf")
    private Set<MObject> computeStubObjectsToExport(Metadatas metadatas) {
        Set<MObject> stubObjectsToExport = new HashSet<>();
        
        for (MObject obj : this.objectsToExternalize) {
            MObject composed = obj.getCompositionOwner();
        
            // Add all parents that have not been serialized until the root
            // package, RequirementContainer, Dictionary or a MDA Modeler
            // Component.
            boolean more = true;
            MObject current = obj;
            do {
                if (composed == null) {
                    // Reached a root
                    metadatas.addRoot(new MRef(current));
                    more = false;
        
                } else if (stubObjectsToExport.contains(composed)) {
                    // Already in the set
                    more = false;
                } else {
                    // The element must not have been serialized in full mode
                    if (!this.done.contains(composed)) {
                        stubObjectsToExport.add(composed);
                        current = composed;
                        composed = composed.getCompositionOwner();
                    } else {
                        // Avoid infinite loop :)
                        more = false;
                    }
                }
        
            } while (more);
        }
        return stubObjectsToExport;
    }

    /**
     * Create empty objects in the target session for each object to externalize and its composition graph.
     */
    @objid ("42bb3384-e5d5-4842-accb-ed7904d4e868")
    private void createEmptyObjects() {
        final SmFactory smFactory = getSmFactory();
        IModel modelService = ((CoreSession) this.targetSession).getModel();
        for (MObject obj : this.objectsToExternalize) {
            SmClass mClass = (SmClass) obj.getMClass();
        
            this.mmFragments.add(mClass.getOrigin());
            smFactory.createObject(mClass, this.targetRepository, obj.getUuid());
        }
        
        Collection<MObject> roots = new ArrayList<>();
        for (MObject o : this.objectsToExternalize) {
            roots.add(o);
        }
        
        final IObjectFilter theModelfilter = this.modelFilter;
        final IStopFilter filter = new IStopFilter() {
        
            @Override
            public boolean accept(MObject val) {
                return theModelfilter.accept(val);
            }
        };
        for (MObject obj : CompositionGetter.getAllChildren(roots, filter)) {
            SmClass metaclass = (SmClass) obj.getMClass();
            String uuid = obj.getUuid();
            // Ignore already created objects.
            SmObjectImpl ret = (SmObjectImpl) modelService.findById(metaclass, uuid);
            if (ret == null || ret.hasStatus(IRStatus.SHELL)) {
                this.mmFragments.add(metaclass.getOrigin());
                smFactory.createObject(metaclass, this.targetRepository, uuid);
            }
        }
    }

    @objid ("ac70ea4a-a419-11e1-aa98-001ec947ccaf")
    private MObject externalizeObject(final MObject obj) {
        // System.out.println("ModelExporter.externalizeObject() " + obj);
        
        // Look for 'obj' equivalent in the target session
        MObject targetObj = this.targetSession.getModel().findById(obj.getMClass(), obj.getUuid());
        
        // If 'obj' is known to have been already processed, return the target
        // equivalent object found above.
        if (this.done.contains(obj)) {
            return targetObj;
        }
        
        // As we are about to process it, add 'obj' to done objects.
        // We have to mark it here because of the natural recursive behavior of
        // externalizeObject
        this.done.add(obj);
        
        if (targetObj == null || targetObj.isShell()) {
            throw new IllegalStateException(obj + " not found or shell in the target session.");
        }
        
        // Copy meta-attribute values
        for (SmAttribute att : ((SmClass) obj.getMClass()).getAllAttDef()) {
            targetObj.mSet(att, obj.mGet(att));
        }
        
        // Export composition dependencies
        for (MDependency desc : this.depWalker.getCompositionDeps(obj)) {
            SmDependency smDep = (SmDependency) desc;
            for (MObject val : obj.mGet(smDep)) {
                if (this.modelFilter.accept(val)) {
                    MObject targetVal = externalizeObject(val);
                    targetObj.mGet(smDep).add(targetVal);
                }
            }
        }
        
        // Export non-composition dependencies
        for (MDependency desc : this.depWalker.getReferenceDeps(obj)) {
            SmDependency smDep = (SmDependency) desc;
            for (MObject val : obj.mGet(smDep)) {
                MObject targetVal = getTargetObject(val);
                targetObj.mGet(smDep).add(targetVal);
            }
        }
        return targetObj;
    }

    @objid ("6dcca2cc-77f5-4860-9da4-ae5e9a6c4536")
    private void externalizeStubObject(Set<MObject> stubObjects) {
        if (stubObjects.isEmpty()) {
            return;
        }
        
        MModelServices modelServices = new MModelServices(this.srcSession);
        
        Stereotype aliasStereotype = null;
        List<Stereotype> stereotypes = modelServices.findStereotypes("ModelerModule", "ModelComponentElementAlias", this.MODELELEMENT_MCLASS);
        if (!stereotypes.isEmpty()) {
            // Get the stereotype from the target session!
            aliasStereotype = (Stereotype) getTargetObject(stereotypes.get(0));
        
            // record used metamodel fragment
            this.mmFragments.add(stereotypes.get(0).getMClass().getOrigin());
        }
        
        TagType uuidTagType = null;
        List<TagType> tagTypes = modelServices.findTagTypes("ModelerModule", "ModelComponentElementAlias", "uuid", this.MODELELEMENT_MCLASS);
        if (!tagTypes.isEmpty()) {
            // Get the tag type from the target session!
            uuidTagType = (TagType) getTargetObject(tagTypes.get(0));
        
            // record used metamodel fragment
            this.mmFragments.add(tagTypes.get(0).getMClass().getOrigin());
        }
        
        // First step: create all the objects
        for (MObject o : stubObjects) {
            // record used metamodel fragment
            this.mmFragments.add(o.getMClass().getOrigin());
        
            // Look for 'obj' equivalent in the target session
            MObject alias = this.targetSession.getModel().findById(o.getMClass(), o.getUuid());
            // If 'obj' is known to have been already processed, return the
            // target equivalent object found above.
            if (this.aliases.containsKey(o.getUuid())) {
                continue;
            }
        
            // Create target object if necessary,the created object is an alias
            if (alias == null) {
                alias = getSmFactory().createObject((SmClass) o.getMClass(), this.targetRepository);
                if (aliasStereotype != null) {
                    IInfrastructureModelFactory targetFactory = MTools.get(alias).getModelFactory(IInfrastructureModelFactory.class);
                    if (alias instanceof ModelElement) {
                        ((ModelElement) alias).getExtension().add(aliasStereotype);
                        TaggedValue tag = targetFactory.createTaggedValue(uuidTagType, (ModelElement) alias);
                        targetFactory.createTagParameter(o.getUuid().toString(), tag);
                    }
                }
        
                this.aliases.put(o.getUuid(), alias);
        
            }
        }
        
        // Second step branch the composition dependencies that are applicable.
        for (MObject o : stubObjects) {
        
            MObject alias = this.aliases.get(o.getUuid());
        
            // Copy meta-attribute values
            for (SmAttribute att : ((SmClass) o.getMClass()).getAllAttDef()) {
                alias.mSet(att, o.mGet(att));
            }
        
            // DO NOT export composition dependencies, unless to already
            // exported
            // objects or to other alias objects
            for (MDependency desc : this.depWalker.getCompositionDeps(o)) {
                SmDependency smDep = (SmDependency) desc;
                for (MObject val : o.mGet(smDep)) {
                    if (this.modelFilter.accept(val)) {
                        if (this.done.contains(val)) {
                            // composition towards a plain exported element
                            MObject target = this.targetSession.getModel().findById(val.getMClass(), val.getUuid());
                            alias.mGet(smDep).add(target);
                        } else if (this.aliases.containsKey(val.getUuid())) {
                            // composition towards another alias
                            alias.mGet(smDep).add(this.aliases.get(val.getUuid()));
                        } else {
                            // ignore
        
                        }
                    }
                }
            }
        
            // DO NOT Export non-composition dependencies
            // for (MDependency desc : this.modelFilter.getReferenceDeps(obj)) {
            // SmDependency smDep = (SmDependency) desc;
            // for (MObject val : obj.mGet(smDep)) {
            // MObject targetVal = getTargetObject(val);
            // targetObj.mGet(smDep).add(targetVal);
            // }
            // }
        
        }
    }

    @objid ("ac70ea4f-a419-11e1-aa98-001ec947ccaf")
    private MObject getTargetObject(final MObject anObject) {
        return getSmFactory().getObjectReference((SmClass) anObject.getMClass(), anObject.getUuid(), anObject.getName());
    }

    @objid ("7aa2da43-4fdd-4a1d-aaab-5f69898411ac")
    private SmFactory getSmFactory() {
        return ((CoreSession) this.targetSession).getSmFactory();
    }

    @objid ("bc08eef2-d369-40c7-8a83-1a28ed412943")
    private static class DepWalker {
        @objid ("c4da04e5-2b54-4895-b60d-d0ea8abb5486")
        private Map<MClass, Collection<MDependency>> compositionDeps = new HashMap<>();

        @objid ("a12ec7d1-4454-49dd-9277-793bb68313ca")
        private Map<MClass, Collection<MDependency>> referenceDeps = new HashMap<>();

        /**
         * Get the metamodel relation to use as composition for a given object.
         * 
         * @param srcObject an object
         * @return the relations to use as composition.
         */
        @objid ("cbd6c07a-e76d-49de-af78-572ead491653")
        public Collection<MDependency> getCompositionDeps(MObject srcObject) {
            Collection<MDependency> ret = this.compositionDeps.get(srcObject.getMClass());
            if (ret != null) {
                return ret;
            }
            
            ret = new ArrayList<>();
            for (MDependency dep : srcObject.getMClass().getDependencies(true)) {
                if (dep.isComposition() || ((SmDependency) dep).isSharedComposition()) {
                    ret.add(dep);
                }
            }
            
            this.compositionDeps.put(srcObject.getMClass(), ret);
            return ret;
        }

        /**
         * Get the metamodel relation to use as reference for a given object.
         * 
         * @param srcObject an object
         * @return the relations to use as reference.
         */
        @objid ("1702baa3-addb-44e1-a883-d5fe8d315dbe")
        public Collection<MDependency> getReferenceDeps(MObject srcObject) {
            Collection<MDependency> ret = this.referenceDeps.get(srcObject.getMClass());
            if (ret != null) {
                return ret;
            }
            
            ret = new ArrayList<>();
            for (MDependency dep : srcObject.getMClass().getDependencies(true)) {
                if (!dep.isComposition() && !dep.isSharedComposition() && ((SmDependency) dep).isPartOf()) {
                    ret.add(dep);
                }
            }
            
            this.referenceDeps.put(srcObject.getMClass(), ret);
            return ret;
        }

        @objid ("e1f6f405-b0d4-4881-ae46-b324d8436371")
        public DepWalker() {
            super();
        }

    }

}
