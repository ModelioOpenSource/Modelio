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

package org.modelio.vstore.exml.versioned;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;
import org.modelio.vstore.exml.common.utils.ExmlUtils;

/**
 * Dependencies of a versioned CMS node.
 */
@objid ("3de290fd-121a-11e2-816a-001ec947ccaf")
public class VersionedNodeDependencies {
    @objid ("3de29126-121a-11e2-816a-001ec947ccaf")
    private final Set<MObject> compManagedNodes = new TreeSet<>(MObjectComparator.instance);

    @objid ("3de29127-121a-11e2-816a-001ec947ccaf")
    private final Set<MObject> refNodes = new TreeSet<>(MObjectComparator.instance);

    @objid ("3de29128-121a-11e2-816a-001ec947ccaf")
    private final Set<MObject> refDeps = new TreeSet<>(MObjectComparator.instance);

    @objid ("3de29129-121a-11e2-816a-001ec947ccaf")
    private final MObject parentNode;

    /**
     * External references.
     * <p>
     * References to elements outside the repository.
     */
    @objid ("3de2912a-121a-11e2-816a-001ec947ccaf")
    private final Set<MObject> extDeps = new TreeSet<>(MObjectComparator.instance);

    @objid ("4c07a284-1224-11e2-816a-001ec947ccaf")
    private final Set<MObject> compLocalNodes = new TreeSet<>(MObjectComparator.instance);

    /**
     * Computes the CMS dependencies of a given CMS node element.
     * 
     * @param el a CMS node
     */
    @objid ("3de2911e-121a-11e2-816a-001ec947ccaf")
    public VersionedNodeDependencies(final SmObjectImpl el) {
        this.parentNode = ExmlUtils.getParentCmsNode (el);
        
        computeDependentObjects(el, new HashSet<MObject>());
        
        // Remove itself from dependencies
        this.compManagedNodes.remove(el);
        this.compLocalNodes.remove(el);
        this.refNodes.remove(el);
        this.refDeps.remove(el);
    }

    /**
     * @return the owned non versioned CMS nodes
     */
    @objid ("ad6ff082-1778-11e2-ac36-001ec947ccaf")
    public Collection<MObject> getCompLocalNodes() {
        return this.compLocalNodes;
    }

    /**
     * @return the owned versioned CMS nodes
     */
    @objid ("ad6ff061-1778-11e2-ac36-001ec947ccaf")
    public Collection<MObject> getCompManagedNodes() {
        return this.compManagedNodes;
    }

    /**
     * Get the model objects outside of the repository.
     * 
     * @return the model objects outside of the repository
     */
    @objid ("ad6ff07b-1778-11e2-ac36-001ec947ccaf")
    public Collection<MObject> getExtDeps() {
        return this.extDeps;
    }

    /**
     * @return the parent CMS node
     */
    @objid ("ad6ff076-1778-11e2-ac36-001ec947ccaf")
    public MObject getParentNode() {
        return this.parentNode;
    }

    /**
     * Get the objects referenced outside this node.
     * <p>
     * The CMS node owning these objects can be got with {@link #getUsedNodes()}.
     * 
     * @return the used objects .
     */
    @objid ("ad6ff06f-1778-11e2-ac36-001ec947ccaf")
    public Collection<MObject> getUsedObjects() {
        return this.refDeps;
    }

    /**
     * Get the CMS nodes used by this CMS node.
     * 
     * @return the used CMS nodes
     */
    @objid ("ad6ff068-1778-11e2-ac36-001ec947ccaf")
    public Collection<MObject> getUsedNodes() {
        return this.refNodes;
    }

    @objid ("3de2911f-121a-11e2-816a-001ec947ccaf")
    private void computeDependentObjects(final SmObjectImpl object, Collection<MObject> recursionContext) {
        // If 'object' is already in context, do not process it again (avoid looping)
        if (recursionContext.contains(object)) {
            return;
        }
        
        recursionContext.add(object);
        
        // Loop on all dependencies
        List<SmDependency> dependencies = object.getClassOf().getAllDepDef();
        
        for (SmDependency dep : dependencies) {
            List<SmObjectImpl> depTargets = object.getDepValList(dep);
        
            if (ExmlUtils.isDepComponent(dep)) {
                // When the dependency is a composition:
                //   - if the object is a CMS node, add it as a compDep
                //     - if the object is not a CMS node, process its  dependencies
                for ( SmObjectImpl target : depTargets) {
                    if (!ExmlUtils.sameRepository(object, target)) {
                        // Element outside the repository
                        this.extDeps.add(target);
                    } else if (target.getMClass().isCmsNode()) {
                        //    cout << " compo= " << o << ", " << dep.name() << ", " << o.name()<< std::endl;//                    cout << " ignored? " << const_cast<SmObjectImpl>(object).name() << ", " << dep.name() << ", " << o.name()<< std::endl;
                        MStatus targetStatus = target.getStatus();
                        if (targetStatus.isCmsManaged() || targetStatus.isCmsToAdd()) {
                            this.compManagedNodes.add(target);
                        } else {
                            this.compLocalNodes.add(target);
                        }
                    } else {
                        computeDependentObjects(target, recursionContext);    // !!! recursive call !!!
                    }
                }
        
            } else if (dep.isPartOf() && ! dep.hasDirective(SmDirective.SMCD_KEEP_DELETED_ON_READONLY)) {
                // The dep is a {partOf}.
                //   - if the 'depended on' object is an 'ext', add it
                //   - if the object is not an 'ext', add it as a link and add its parent CMS Node (if one can be found) as an extRef.
                //
                // ignore SmDirective.SMCD_KEEP_DELETED_ON_READONLY : 
                //  - the dependency may contain references to objects that don't exist (eg: Diagram -> Element).
                for ( SmObjectImpl target : depTargets) {
                    if (!ExmlUtils.sameRepository(object, target)) {
                        // Element outside the repository
                        this.extDeps.add(target);
                    } else if ( target.getClassOf().isCmsNode() ) {
                        this.refNodes.add(target);
                    } else {
                        this.refDeps.add(target);
                        MObject parentCmsNode = ExmlUtils.getParentCmsNode(target);
                        if (parentCmsNode!= null) {
                            this.refNodes.add(parentCmsNode);
                        }
        
                    }
                }
            }
        }
        
        // remove 'object' from recursion context
        recursionContext.remove(object);
    }

    /**
     * MObject comparator that sorts object by name then by UUID.
     */
    @objid ("ab9963d4-8c2e-4b14-ab8a-2c1f11a13870")
    private static class MObjectComparator implements Comparator<MObject> {
        @objid ("f51d83cc-ce0f-4339-b69f-3c58525d55bf")
        public static final Comparator<MObject> instance = new MObjectComparator();

        @objid ("c67520c3-f661-45b8-8077-84d2ddab6653")
        public MObjectComparator() {
            // nothing
        }

        @objid ("0d4cc79f-6ad4-4234-bdba-44f12fe49b76")
        @Override
        public int compare(MObject o1, MObject o2) {
            if (o1 == o2) {
                return 0;
            }
            
            int comp = o1.getName().compareTo(o2.getName());
            if (comp != 0) {
                return comp;
            }
            return o1.getUuid().compareTo(o2.getUuid());
        }

    }

}
