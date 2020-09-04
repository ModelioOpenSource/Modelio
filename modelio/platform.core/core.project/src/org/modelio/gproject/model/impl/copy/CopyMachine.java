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

package org.modelio.gproject.model.impl.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.model.impl.importer.defaultimporter.DefaultImporter;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.mmextensions.standard.facilities.CompositionInitializer;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Service class used to copy a model object composition tree.
 */
@objid ("01f419b4-0000-291f-0000-000000000000")
public class CopyMachine extends DefaultImporter {
    @objid ("000c3348-5247-1091-8d81-001ec947cd2a")
    private boolean monoSession;

    /**
     * If true, replace in dependencies source composition owners by the target.
     * <p>
     * E.g : when copying a reflexive association to another class, it will stay reflexive in the target.
     * When copying from C1 an operation doxxx(C1 c) to C2, the operation will become doxxx(C2 c).
     * This may be useful for copying clone() methods or static creation methods.
     */
    @objid ("b0efda43-d40a-4891-96d4-b44abe03cf50")
    private boolean replaceSourceOwnerByTarget;

    @objid ("2d75e8e2-6274-4b74-9f10-f7e1068ab7cb")
    private Collection<SmObjectImpl> sourceOwners;

    @objid ("b30bd2b2-d5e3-481f-b5d9-f333a88b3f1c")
    private SmObjectImpl sourceOwnersReplacement;

    @objid ("6dfe4497-bc4e-430e-a815-cadf52e2c467")
    private Collection<SmObjectImpl> sources;

    /**
     * If true, replace in dependencies source composition owners by the target.
     * <p>
     * E.g : when copying a reflexive association to another class, it will stay reflexive in the target.
     * When copying from C1 an operation doxxx(C1 c) to C2, the operation will become doxxx(C2 c).
     * This may be useful for copying clone() methods or static creation methods.
     * 
     * @param replace true to replace source composition owners by the target.
     */
    @objid ("681ceb56-6b98-426c-bffb-ee1e0ad8ae01")
    public void setReplaceSourceOwnerByTarget(boolean replace) {
        this.replaceSourceOwnerByTarget = replace;
    }

    @objid ("000c7f92-5247-1091-8d81-001ec947cd2a")
    SmObjectImpl getMappedObject(SmObjectImpl searchedObject) {
        // Replace if activated reference root composition owner by the target
        if (this.replaceSourceOwnerByTarget && this.sourceOwners.contains(searchedObject)) {
            return this.sourceOwnersReplacement;
        }
        return this.result.getObjectCreatedFrom(searchedObject);
    }

    @objid ("000ca274-5247-1091-8d81-001ec947cd2a")
    boolean isMonoSession() {
        return this.monoSession;
    }

    @objid ("3cdea8f4-78b9-47a8-a2b6-4cfdd88910b4")
    @Override
    protected void fixElement(SmObjectImpl localObject, SmObjectImpl refObject, ICoreSession localSession, ICoreSession refSession) {
        // Call inherited behavior
        super.fixElement(localObject, refObject, localSession, refSession);
        
        // Fix the diagrams
        DiagramsCopier diagramCopier = new DiagramsCopier();
        
        if (localObject instanceof AbstractDiagram) {
            diagramCopier.fixDiagram((AbstractDiagram) localObject, this.result.getCreations());
        }
    }

    /**
     * Redefined to reparent all selection roots, orphan or not.
     */
    @objid ("097acc3b-4530-4bf5-90ef-efc690b0302c")
    @Override
    protected void fixRoots(final ICoreSession localSession, final SmObjectImpl localRoot, List<SmObjectImpl> refRoots) {
        // Gather all roots, orphans or not
        Map<SmObjectImpl, SmDependency> toReparent = new HashMap<>();
        for (SmObjectImpl refRoot : refRoots) {
            SmObjectImpl localObject = this.result.getObjectCreatedFrom(refRoot);
        
            if (localObject == null) {
                localObject = this.result.getObjectUpdatedFrom(refRoot);
            }
        
            if (localObject != null) {
                toReparent.put(localObject, refRoot.getCompositionRelation().dep.getSymetric());
            }
        }
        
        reparentElements(toReparent, localSession, localRoot);
    }

    @objid ("000cc10a-5247-1091-8d81-001ec947cd2a")
    @Override
    protected void importElements(ICoreSession localSession, SmObjectImpl localRoot, ICoreSession refSession, List<SmObjectImpl> refRoots) {
        // Create all 'nodes' and update meta-attributes
        for (SmObjectImpl refToImport : getCompositionGetter().getAllChildren(refRoots)) {
            SmObjectImpl localObject = (SmObjectImpl) localSession.getModel().getGenericFactory().create(refToImport.getClassOf().getJavaInterface(), localSession.getRepositorySupport().getRepository(localRoot != null ? localRoot : refToImport));
        
            // Import attributes
            getAttributesImporter().importAttributes(refToImport, localObject);
        
            this.result.addCreatedObject(localObject, refToImport);
        }
    }

    @objid ("1fda4a17-fe2a-4f22-941f-ce88d50c4977")
    @Override
    protected void importReferenceDependencies(final SmObjectImpl refObject, SmObjectImpl localObject) {
        // import all the reference (not-composition) dependencies
        for (SmDependency refDep : getDependencyGetter().getReferenceDeps(localObject)) {
            // Filter out composition inverses that are also {partOf} : AssociationEnd.Source and LinkEnd.Source
            // for root elements. It may fire unwanted and useless AccessDeniedException.
            if (!(refDep.isCompositionOpposite() && this.sources.contains(refObject))) {
                for (SmObjectImpl orphan : getReferenceDepUpdater().execute(refObject, refDep, localObject)) {
                    this.result.addObjectToGarbage(orphan);
                }
            }
        }
    }

    @objid ("000c41da-5247-1091-8d81-001ec947cd2a")
    @Override
    protected void prepare(ICoreSession localSession, SmObjectImpl localRoot, ICoreSession refSession, List<SmObjectImpl> refRoots) {
        this.monoSession = localSession == refSession;
        
        CopierObjectFinder objectFinder = new CopierObjectFinder(this, localSession.getModel(), localSession.getMetamodel());
        setObjectFinder(objectFinder);
        
        this.sources = refRoots;
        
        ReferenceDependencyCopier depUpdater = new ReferenceDependencyCopier(localSession, objectFinder);
        setCompositionDepUpdater(depUpdater);
        setReferenceDepUpdater(depUpdater);
        
        // Filter children of elements already copied
        // e.g.: remove an operation from the source if its owner class is in the sources too.
        List<SmObjectImpl> reducedRefRoots = new ArrayList<>();
        for (SmObjectImpl obj1 : refRoots) {
            boolean found = false;
            for (SmObjectImpl obj2 : refRoots) {
                if ((obj1 != obj2) && obj1.equals(obj2.getCompositionOwner())) {
                    found = true;
                    break;
                }
            }
        
            if (!found) {
                reducedRefRoots.add(obj1);
            }
        }
        
        if (this.replaceSourceOwnerByTarget) {
            this.sourceOwnersReplacement = localRoot;
            this.sourceOwners = new HashSet<>(reducedRefRoots.size());
            for (SmObjectImpl src : reducedRefRoots) {
                SmObjectImpl srcOwner = src.getCompositionOwner();
                if (srcOwner != null) {
                    this.sourceOwners.add(srcOwner);
                }
            }
        }
        
        super.prepare(localSession, localRoot, refSession, reducedRefRoots);
    }

    @objid ("000cfcc4-5247-1091-8d81-001ec947cd2a")
    @Override
    protected void reparentElements(Map<SmObjectImpl, SmDependency> toReparent, ICoreSession localSession, SmObjectImpl newLocalParent) {
        if (newLocalParent == null && isMonoSession()) {
            for (Entry<SmObjectImpl, SmDependency> elemEntry : toReparent.entrySet()) {
                SmObjectImpl orphan = elemEntry.getKey();
        
                for (Entry<SmObjectImpl, SmObjectImpl> entry : this.result.getCreations().entrySet()) {
                    SmObjectImpl localObject = entry.getValue();
                    if (orphan.equals(localObject)) {
                        SmObjectImpl refObject = entry.getKey();
                        CompositionInitializer initializer = new CompositionInitializer(refObject.getCompositionOwner());
                        initializer.execute(orphan, elemEntry.getValue());
                        break;
                    }
                }
            }
        } else {
            super.reparentElements(toReparent, localSession, newLocalParent);
        }
    }

}
