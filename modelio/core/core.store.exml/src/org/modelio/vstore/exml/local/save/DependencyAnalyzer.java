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
package org.modelio.vstore.exml.local.save;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.exml.common.utils.ExmlUtils;

/**
 * Computes CMS node dependencies in a {@link ElementDependencies}.
 */
@objid ("fd26ba05-5986-11e1-991a-001ec947ccaf")
class DependencyAnalyzer {
    @objid ("fd21f734-5986-11e1-991a-001ec947ccaf")
    public ElementDependencies getDependentObjects(final SmObjectImpl object) {
        ElementDependencies deps = new ElementDependencies();
        
        deps.parentNode = ExmlUtils.getParentCmsNode (object);
        
        doGetDependentObjects(object, deps, new HashSet<SmObjectImpl>());
        
        // Remove itself from dependencies
        deps.compNodes.remove(object);
        deps.refNodes.remove(object);
        deps.refDeps.remove(object);
        return deps;
    }

    @objid ("fd21f733-5986-11e1-991a-001ec947ccaf")
    private void doGetDependentObjects(final SmObjectImpl object, ElementDependencies deps, Collection<SmObjectImpl> recursionContext) {
        // If 'object' is already in context, do not process it again (avoid looping)
        if (recursionContext.contains(object)) {
            return;
        }
        
        recursionContext.add(object);
        
        //    cout << " _getDependentObjects for " << object << ", " << const_cast<SmObjectImpl>(object).name()<< std::endl;
        
        // Loop on externalisable non-empty dependencies
        List<SmDependency> dependencies = ExmlUtils.getExternalisableDeps(object);
        
        for (SmDependency dep : dependencies) {
            List<SmObjectImpl> depTargets = object.getDepValList(dep);
        
            if (dep.isComposition()) {
                // When the dependency is a composition:
                //   - if the object is a CMS node, add it as a compDep
                //     - if the object is not a CMS node, process its  dependencies
                for ( SmObjectImpl target : depTargets) {
                    processComponent(object, deps, recursionContext, target);
                }
            } else if (dep.isSharedComposition()) {
                // When the dependency is a composition:
                //   - if the object is a CMS node, add it as a compDep
                //   - if the object is not a CMS node, check its real owner:
                //      - if owned by current node, process its dependencies
                //      - else handle it as a reference dependency
                for ( SmObjectImpl target : depTargets) {
                    if (ExmlUtils.isComposition(object, dep, target)) {
                        processComponent(object, deps, recursionContext, target);
                    } else {
                        processReference(object, deps, target);
                    }
                }
        
            } else {
                // The dep is not a composition.
                //   - if the 'depended on' object is an 'ext', add it
                //   - if the object is not an 'ext', add it as a link and add its parent CMS Node (if one can be found) as an extRef.
                for ( SmObjectImpl target : depTargets) {
                    processReference(object, deps, target);
                }
            }
        }
        
        // remove 'object' from recursion context
        recursionContext.remove(object);
        
    }

    @objid ("a826c267-5afd-44aa-aeb0-42aad8eca103")
    private void processComponent(final SmObjectImpl object, ElementDependencies deps, Collection<SmObjectImpl> recursionContext, SmObjectImpl target) {
        if (!ExmlUtils.sameRepository(object, target)) {
            // Element outside the repository
            deps.extDeps.add(target);
        } else if (target.getClassOf().isCmsNode()) {
            //    cout << " compo= " << o << ", " << dep.name() << ", " << o.name()<< std::endl;//                    cout << " ignored? " << const_cast<SmObjectImpl>(object).name() << ", " << dep.name() << ", " << o.name()<< std::endl;
            deps.compNodes.add(target);
        } else {
            doGetDependentObjects(target, deps, recursionContext);    // !!! recursive call !!!
        }
        
    }

    @objid ("b894cf68-11c2-4ad5-8894-e5420f3a22ad")
    private void processReference(final SmObjectImpl object, ElementDependencies deps, SmObjectImpl target) {
        if (!ExmlUtils.sameRepository(object, target)) {
            // Element outside the repository
            deps.extDeps.add(target);
        } else if ( target.getClassOf().isCmsNode() ) {
            deps.refNodes.add(target);
        } else {
            deps.refDeps.add(target);
        }
        
    }

}
