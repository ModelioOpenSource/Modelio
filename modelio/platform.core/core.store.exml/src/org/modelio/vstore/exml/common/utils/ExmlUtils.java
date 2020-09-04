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

package org.modelio.vstore.exml.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.CompositionGetter.IStopFilter;
import org.modelio.vcore.smkernel.SmLiveId;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * EXML utilities.
 */
@objid ("fd26ba12-5986-11e1-991a-001ec947ccaf")
public final class ExmlUtils {
    /**
     * Get the CMS node that stores the given object.
     * <p>
     * Returns the object itself if it is a CMS node.
     * @param initialObj an object
     * @return its CMS node.
     */
    @objid ("fd2457f8-5986-11e1-991a-001ec947ccaf")
    public static SmObjectImpl getCmsNode(SmObjectImpl initialObj) {
        SmObjectImpl obj = initialObj;
        while (obj != null && ! obj.getClassOf().isCmsNode())
        {
            obj = obj.getCompositionOwner();
        }
        return obj;
    }

    /**
     * Get all SmDependencies that must be serialized.
     * <p>
     * Are to be serialized:
     * <li> strong composition dependencies
     * <li> shared composition dependencies
     * <li> 'partof' dependencies that are not opposites of strong or shared composition.
     * </ul>
     * The returned list may be freely modified.
     * @param object a model object
     * @return all SmDependency to serialize.
     */
    @objid ("fd21f732-5986-11e1-991a-001ec947ccaf")
    public static List<SmDependency> getExternalisableDeps(final SmObjectImpl object) {
        SmClass objectClass = object.getClassOf();
        List<SmDependency> allDeps = objectClass.getAllDepDef();
        List<SmDependency> deps = new ArrayList<>(allDeps.size());
        
        for (SmDependency dep : allDeps) {
            // Serialize :
            // - component dependencies
            // - and 'partof' dependencies that are not composition opposites
            if (ExmlUtils.isDepComponent(dep) || (dep.isPartOf() && ! dep.isCompositionOpposite())) {
                deps.add(dep);
            }
        }
        return deps;
    }

    /**
     * Get all objects contained in the given CMS node using generic navigation.
     * <p>
     * The given CMS node and Child CMS nodes are excluded.
     * The returned list may be freely modified.
     * @param cmsNode a CMS node object
     * @return all contained objects.
     */
    @objid ("df1fddf5-1c43-11e2-8eb9-001ec947ccaf")
    public static Collection<SmObjectImpl> getLoadedCmsNodeContent(SmObjectImpl cmsNode) {
        final ArrayList<SmObjectImpl> results = new ArrayList<>();
        
        getLoadedCmsNodeContent(cmsNode, results);
        return results;
    }

    /**
     * Get the parent CMS node of this object.
     * @param object an object
     * @return the parent CMS node.
     */
    @objid ("fd21f730-5986-11e1-991a-001ec947ccaf")
    public static MObject getParentCmsNode(final MObject object) {
        MObject parent = object.getCompositionOwner();
        while (parent != null && !parent.getMClass().isCmsNode()) {
            parent = parent.getCompositionOwner();
        }
        return parent;
    }

    /**
     * Tells whether the source object is the composition owner of dest object by the given dependency.
     * <p>
     * The source and target object must be linked by the relation.
     * <p>
     * If the dependency is a composition, always return true (it's a shortcut).
     * If the dependency is a shared composition, test whether the target composition owner
     * is the source object.
     * @param src a model object
     * @param dep a model dependency
     * @param dest a model object
     * @return <code>true</code> if source is the composition owner of target.
     */
    @objid ("8265a510-b4ac-4983-b6f0-966e4f61982c")
    public static boolean isComposition(SmObjectImpl src, SmDependency dep, SmObjectImpl dest) {
        if (dep.isComponent()) {
            return true;
        }
        if (!dep.isSharedComposition()) {
            return false;
        }
        return src.equals(dest.getCompositionOwner());
    }

    /**
     * Tells whether the dependency is a composition or a shared composition.
     * @param dep a dependency
     * @return <code>true</code> if the dependency is a composition or a shared composition else <code>false</code>.
     */
    @objid ("fd21f72f-5986-11e1-991a-001ec947ccaf")
    public static boolean isDepComponent(final SmDependency dep) {
        return (dep.isComponent() || dep.isSharedComposition());
    }

    /**
     * Tells whether the 2 objects are in the same repository.
     * @param o1 an object
     * @param o2 an object
     * @return <code>true</code> if both objects are in the same repository, else <code>false</code>
     */
    @objid ("f0ddc0ae-92d7-11e1-be7e-001ec947ccaf")
    public static boolean sameRepository(final MObject o1, final MObject o2) {
        return sameRepository((SmObjectImpl)o1, (SmObjectImpl)o2);
    }

    /**
     * Tells whether the 2 objects are in the same repository.
     * @param o1 an object
     * @param o2 an object
     * @return <code>true</code> if both objects are in the same repository, else <code>false</code>
     */
    @objid ("1c1116d6-fead-44d8-91fa-93cec307d8b8")
    public static boolean sameRepository(final SmObjectImpl o1, final SmObjectImpl o2) {
        return SmLiveId.getRid(o2.getLiveId()) == SmLiveId.getRid(o1.getLiveId());
    }

    /**
     * @deprecated No instances.
     */
    @objid ("fd2457f6-5986-11e1-991a-001ec947ccaf")
    @Deprecated
    private ExmlUtils() {
    }

    /**
     * Get all objects contained in the given CMS node using generic navigation.
     * @param root the model object to iterate.
     * @param children a set where all composition children will be added.
     */
    @objid ("7c074589-3259-4c27-84a0-33ea32b235fe")
    private static void getLoadedCmsNodeContent(final SmObjectImpl root, final Collection<SmObjectImpl> children) {
        // initialize a current roots list from the passed root elements
        Collection<SmObjectImpl> directChildren = new ArrayList<>();
        Collection<SmObjectImpl> currentRoots = new ArrayList<>();
        
        currentRoots.add(root);
        
        // Loop until there is no root nodes
        while (!currentRoots.isEmpty()) {
            // Get direct childs of current roots into 'impl.list'
            for (SmObjectImpl o : currentRoots) {
                // This add childs to 'impl.list'
                directChildren.addAll(getLoadedCompoChildren(o));
            }
        
            // Clear the current roots list
            // in order to rebuild it for next iteration
            currentRoots.clear();
        
            // Add each new child to the result set and to the next roots list
            for (SmObjectImpl child : directChildren) {
                if (!children.contains(child) && !child.getMClass().isCmsNode()) {
                    // Add the child to the set
                    children.add(child);
        
                    // Add the child to the next roots list
                    currentRoots.add(child);
                }
            }
        
            // Drop direct children list and create new one
            directChildren = new ArrayList<>();
        
        }
    }

    /**
     * Get composition children already in memory using generic way.
     * <p>
     * The returned list may be freely modified.
     * @param obj a model object
     * @return its loaded composition children
     */
    @objid ("c78ed825-57d9-462d-ab55-79c1027ba73d")
    private static Collection<SmObjectImpl> getLoadedCompoChildren(SmObjectImpl obj) {
        ArrayList<SmObjectImpl> results = new ArrayList<>();
        
        for (SmDependency dep : obj.getClassOf().getAllComponentAndSharedDepDef()) {
            if (dep.isMultiple()) {
                Collection<SmObjectImpl> depVal = dep.getValueAsCollection(obj.getData());
                results.addAll(depVal);
            } else {
                SmObjectImpl depVal = (SmObjectImpl) dep.getValue(obj.getData());
                if (depVal != null) {
                    results.add(depVal);
                }
            }
        }
        return results;
    }

    /**
     * Filter that stops on CMS nodes.
     */
    @objid ("fd26ba02-5986-11e1-991a-001ec947ccaf")
    private static class IsCmsNodeContentFilter implements IStopFilter {
        @objid ("fd21f62e-5986-11e1-991a-001ec947ccaf")
        public static IsCmsNodeContentFilter instance = new IsCmsNodeContentFilter();

        @objid ("fd21f71b-5986-11e1-991a-001ec947ccaf")
        @Override
        public boolean accept(MObject anObject) {
            return ! anObject.getMClass().isCmsNode();
        }

    }

}
