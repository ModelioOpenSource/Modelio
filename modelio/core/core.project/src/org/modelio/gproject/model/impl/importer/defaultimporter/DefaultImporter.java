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

package org.modelio.gproject.model.impl.importer.defaultimporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.model.impl.importer.core.AbstractImporter;
import org.modelio.gproject.model.impl.importer.core.IAttributesImporter;
import org.modelio.gproject.model.impl.importer.core.IBrokenDependencyHandler;
import org.modelio.gproject.model.impl.importer.core.ICompositionGetter;
import org.modelio.gproject.model.impl.importer.core.IDependencyGetter;
import org.modelio.gproject.model.impl.importer.core.IDependencyUpdater;
import org.modelio.gproject.model.impl.importer.core.IImportFilter;
import org.modelio.gproject.model.impl.importer.core.IObjectFinder;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Service class used to import model from a ICoreSession to another one.
 * <p>
 * The identifiers are kept same.
 */
@objid ("006b567a-d3aa-108f-8d81-001ec947cd2a")
public class DefaultImporter extends AbstractImporter {
    @mdl.prop
    @objid ("006c15e2-d3aa-108f-8d81-001ec947cd2a")
    private IAttributesImporter attributesImporter;

    @mdl.propgetter
    protected IAttributesImporter getAttributesImporter() {
        // Automatically generated method. Please do not modify this code.
        return this.attributesImporter;
    }

    @mdl.propsetter
    protected void setAttributesImporter(IAttributesImporter value) {
        // Automatically generated method. Please do not modify this code.
        this.attributesImporter = value;
    }

    @mdl.prop
    @objid ("006bf4c2-d3aa-108f-8d81-001ec947cd2a")
    private IBrokenDependencyHandler brokenDependencyHandler;

    @mdl.propgetter
    protected IBrokenDependencyHandler getBrokenDependencyHandler() {
        // Automatically generated method. Please do not modify this code.
        return this.brokenDependencyHandler;
    }

    @mdl.propsetter
    protected void setBrokenDependencyHandler(IBrokenDependencyHandler value) {
        // Automatically generated method. Please do not modify this code.
        this.brokenDependencyHandler = value;
    }

    @mdl.prop
    @objid ("006c7d34-d3aa-108f-8d81-001ec947cd2a")
    private IDependencyUpdater compositionDepUpdater;

    @mdl.propgetter
    protected IDependencyUpdater getCompositionDepUpdater() {
        // Automatically generated method. Please do not modify this code.
        return this.compositionDepUpdater;
    }

    @mdl.propsetter
    protected void setCompositionDepUpdater(IDependencyUpdater value) {
        // Automatically generated method. Please do not modify this code.
        this.compositionDepUpdater = value;
    }

    @mdl.prop
    @objid ("006bfd50-d3aa-108f-8d81-001ec947cd2a")
    private ICompositionGetter compositionGetter;

    @mdl.propgetter
    protected ICompositionGetter getCompositionGetter() {
        // Automatically generated method. Please do not modify this code.
        return this.compositionGetter;
    }

    @mdl.propsetter
    protected void setCompositionGetter(ICompositionGetter value) {
        // Automatically generated method. Please do not modify this code.
        this.compositionGetter = value;
    }

    @mdl.prop
    @objid ("006c0548-d3aa-108f-8d81-001ec947cd2a")
    private IDependencyGetter dependencyGetter;

    @mdl.propgetter
    protected IDependencyGetter getDependencyGetter() {
        // Automatically generated method. Please do not modify this code.
        return this.dependencyGetter;
    }

    @mdl.propsetter
    protected void setDependencyGetter(IDependencyGetter value) {
        // Automatically generated method. Please do not modify this code.
        this.dependencyGetter = value;
    }

    @mdl.prop
    @objid ("006c912a-d3aa-108f-8d81-001ec947cd2a")
    private IImportFilter importFilter;

    @mdl.propgetter
    protected IImportFilter getImportFilter() {
        // Automatically generated method. Please do not modify this code.
        return this.importFilter;
    }

    @mdl.propsetter
    protected void setImportFilter(IImportFilter value) {
        // Automatically generated method. Please do not modify this code.
        this.importFilter = value;
    }

    @mdl.prop
    @objid ("006c0d68-d3aa-108f-8d81-001ec947cd2a")
    private IObjectFinder objectFinder;

    @mdl.propgetter
    protected IObjectFinder getObjectFinder() {
        // Automatically generated method. Please do not modify this code.
        return this.objectFinder;
    }

    @mdl.propsetter
    protected void setObjectFinder(IObjectFinder value) {
        // Automatically generated method. Please do not modify this code.
        this.objectFinder = value;
    }

    @mdl.prop
    @objid ("006c8766-d3aa-108f-8d81-001ec947cd2a")
    private IDependencyUpdater referenceDepUpdater;

    @mdl.propgetter
    protected IDependencyUpdater getReferenceDepUpdater() {
        // Automatically generated method. Please do not modify this code.
        return this.referenceDepUpdater;
    }

    @mdl.propsetter
    protected void setReferenceDepUpdater(IDependencyUpdater value) {
        // Automatically generated method. Please do not modify this code.
        this.referenceDepUpdater = value;
    }

    @objid ("0087f532-e548-108f-8d81-001ec947cd2a")
    @Override
    protected void fixElement(SmObjectImpl localObject, SmObjectImpl refObject, ICoreSession localSession, ICoreSession refSession) {
        // Copy related blobs
        localSession.getBlobSupport().fireObjectCopied(refObject, localObject);
    }

    @objid ("006df060-d3aa-108f-8d81-001ec947cd2a")
    @Override
    protected void importCompositionDependencies(final SmObjectImpl refObject, SmObjectImpl localObject) {
        // import all the composition dependencies
        for (SmDependency refDep : getDependencyGetter().getCompositionDeps(localObject)) {
            for (SmObjectImpl orphan : this.compositionDepUpdater.execute(refObject, refDep, localObject)) {
                this.result.addObjectToGarbage(orphan);
            }
        }
    }

    @objid ("0003ef8a-5247-1091-8d81-001ec947cd2a")
    @Override
    protected void importElements(ICoreSession localSession, SmObjectImpl localRoot, ICoreSession refSession, List<SmObjectImpl> refRoots) {
        IRepository localRepository = localSession.getRepositorySupport().getRepository(localRoot);
        
        for (SmObjectImpl refToImport : this.compositionGetter.getAllChildren(refRoots)) {
        
            // If 'toImport' is a shell object the import will not be viable
            // BrokenDependencyHandler will be called as soon as an imported element needs the missing element.
            if (refToImport.isShell() || this.importFilter.select(refToImport) == false) {
                continue;
            }
        
            SmObjectImpl localObject = this.objectFinder.getSameObject(refToImport);
            if (localObject == null || localObject.isShell()) {
                // there is no local object equivalent to 'toImport' => create a new object
                SmClass localMClass = (SmClass) this.objectFinder.getSameMetaclass(refToImport.getClassOf());
                if (localMClass != null) {
                    localObject = ((CoreSession) localSession).getSmFactory().createObject(localMClass, localRepository, refToImport.getUuid());
        
                    // import its attributes
                    this.attributesImporter.importAttributes(refToImport, localObject);
        
                    // store the created object in the results
                    this.result.addCreatedObject(localObject, refToImport);
                }
            } else {
                assert (refToImport.equals(localObject) == false);
        
                if (localObject.isDeleted()) {
                    // restore before import
                    localObject.getMetaOf().objUndeleted(localObject);
                }
        
                // import its attributes
                this.attributesImporter.importAttributes(refToImport, localObject);
        
                // store the updated object in the results
                this.result.addUpdatedObject(localObject, refToImport);
            }
        }
    }

    @objid ("00045e3e-5247-1091-8d81-001ec947cd2a")
    @Override
    protected void importReferenceDependencies(final SmObjectImpl refObject, SmObjectImpl localObject) {
        // import all the reference (not-composition) dependencies
        for (SmDependency refDep : getDependencyGetter().getReferenceDeps(localObject)) {
            for (SmObjectImpl orphan : getReferenceDepUpdater().execute(refObject, refDep, localObject)) {
                this.result.addObjectToGarbage(orphan);
            }
        }
    }

    @objid ("006cc262-d3aa-108f-8d81-001ec947cd2a")
    @Override
    protected void prepare(final ICoreSession localSession, final SmObjectImpl localRoot, final ICoreSession refSession, List<SmObjectImpl> refRoots) {
        if (this.importFilter == null) {
            this.importFilter = new ImportAllFilter();
        }
        if (this.objectFinder == null) {
            this.objectFinder = new DefaultObjectFinder(localSession.getModel(), localSession.getMetamodel());
        }
        
        if (this.brokenDependencyHandler == null) {
            this.brokenDependencyHandler = new DefaultBrokenDependencyHandler();
        }
        
        if (this.referenceDepUpdater == null) {
            this.referenceDepUpdater = new DefaultReferenceDependencyUpdater(this.brokenDependencyHandler, this.objectFinder, localSession);
        }
        
        if (this.compositionDepUpdater == null) {
            this.compositionDepUpdater = new DefaultCompositionDependencyUpdater(this.brokenDependencyHandler, this.objectFinder, localSession);
        }
        
        if (this.dependencyGetter == null) {
            this.dependencyGetter = new DefaultDependencyGetter();
        }
        
        if (this.compositionGetter == null) {
            this.compositionGetter = new DefaultCompositionGetter();
        }
        
        if (this.attributesImporter == null) {
            this.attributesImporter = new DefaultAttributesImporter(this.objectFinder);
        }
    }

    @objid ("0003a58e-5247-1091-8d81-001ec947cd2a")
    @Override
    protected void reparentElements(Map<SmObjectImpl, SmDependency> orphans, ICoreSession localSession, SmObjectImpl localRoot) {
        // Process broken links by delegation to the customized IBrokenDependencyHandler
        this.brokenDependencyHandler.postProcess();
        
        // Re-attach orphan roots
        if (localRoot != null) {
            AddToRootProcessor addProc = new AddToRootProcessor();
            for (SmObjectImpl stillOrphan : addProc.reparentOrphans(orphans, localRoot, localSession)) {
                // Some elements are still orphan, delete them
                this.result.addObjectToDelete(stillOrphan);
            }
        }
    }

    @objid ("0086434a-e548-108f-8d81-001ec947cd2a")
    private static final class AddToRootProcessor {
        @objid ("00865c9a-e548-108f-8d81-001ec947cd2a")
        protected AddToRootProcessor() {
            // Nothing to do
        }

        @objid ("008669b0-e548-108f-8d81-001ec947cd2a")
        public List<SmObjectImpl> reparentOrphans(final Map<SmObjectImpl, SmDependency> orphans, SmObjectImpl fallbackParent, ICoreSession localSession) {
            ImportCompositionInitializer initializer = new ImportCompositionInitializer(fallbackParent);
            
            List<SmObjectImpl> stillOrphans = new ArrayList<>();
            
            for (Entry<SmObjectImpl, SmDependency> orphan : orphans.entrySet()) {
                if (orphan != null && !orphan.getKey().isDeleted()) {
                    if (!attach(orphan.getKey(), orphan.getValue(), initializer, localSession)) {
                        stillOrphans.add(orphan.getKey());
                    }
                }
            }
            return stillOrphans;
        }

        @objid ("0086b500-e548-108f-8d81-001ec947cd2a")
        private boolean attach(final SmObjectImpl orphan, SmDependency smDependency, ImportCompositionInitializer initializer, ICoreSession localSession) {
            // Try adding it to the local root
            boolean res = initializer.execute(orphan, smDependency);
            return (res || AbstractImporter.isRoot(orphan, localSession));
        }

        @objid ("0086e0d4-e548-108f-8d81-001ec947cd2a")
        private static final class ImportCompositionInitializer {
            @objid ("1d3bf74b-3a36-4b21-8ee8-6b52b31759a2")
            private final SmObjectImpl parent;

            @objid ("7069bf50-1a87-40b7-9e97-0039f66a91a2")
            private MExpert mExpert;

            @objid ("008700be-e548-108f-8d81-001ec947cd2a")
            protected ImportCompositionInitializer(final SmObjectImpl aParent) {
                this.parent = aParent;
                this.mExpert = this.parent.getMClass().getMetamodel().getMExpert();
            }

            @objid ("da522b09-f2a5-422a-a760-6cb9f071afc0")
            public boolean execute(final SmObjectImpl obj, final SmDependency adep) {
                if (this.parent == null) {
                    return false;
                }
                
                SmDependency dep = adep;
                
                if (dep == null || !this.parent.getMClass().hasBase(adep.getSource())) {
                    dep = (SmDependency) this.mExpert.getDefaultCompositionDep(this.parent, obj);
                }
                
                if (dep != null &&
                        obj.getMClass().hasBase(dep.getTarget()) &&
                        this.mExpert.canCompose(this.parent, obj, dep.getName())) {
                    // Try a generic approach
                
                    List<MObject> mGet = this.parent.mGet(dep);
                    if (mGet != null) {
                        mGet.add(obj);
                        return true;
                    }
                }
                return false;
            }

        }

    }

}
