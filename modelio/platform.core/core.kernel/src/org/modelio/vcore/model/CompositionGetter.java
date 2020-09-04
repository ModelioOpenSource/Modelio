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

package org.modelio.vcore.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Composition getting services.
 */
@objid ("0a8ebd1e-598c-11e1-991a-001ec947ccaf")
public class CompositionGetter {
    /**
     * Get all composition children of the given objects recursively.
     * @param roots the model objects to iterate.
     * @return the set of all composition children.
     */
    @objid ("134d92a8-5bcb-11e1-a296-001ec947ccaf")
    public static Set<MObject> getAllChildren(Collection<? extends MObject> roots) {
        return getAllChildren(roots, new IStopFilter() {
                                    @Override
                                    public boolean accept(MObject child) {
                                return true;
                                            }
                                        });
    }

    /**
     * Get all composition children of the given objects recursively.
     * @param roots the model objects to iterate.
     * @param filter a filter that can stop the iteration.
     * @return the set of all composition children.
     */
    @objid ("134ff4fe-5bcb-11e1-a296-001ec947ccaf")
    public static Set<MObject> getAllChildren(final Collection<? extends MObject> roots, IStopFilter filter) {
        Set<MObject> children = new HashSet<>();
        getAllChildren(roots, children, filter);
        return children;
    }

    /**
     * Get all composition children of the given objects recursively.
     * @param roots the model objects to iterate.
     * @param children a set where all composition children will be added.
     * @param filter a filter that can stop the iteration.
     */
    @objid ("134ff506-5bcb-11e1-a296-001ec947ccaf")
    private static void getAllChildren(final Collection<? extends MObject> roots, final Set<MObject> children, final IStopFilter filter) {
        // initialize a current roots list from the passed root elements
        Collection<MObject> currentRoots = new ArrayList<>(roots);
        Collection<MObject> directChildren = new ArrayList<>();
        
        // Loop until there is no root nodes
        while (!currentRoots.isEmpty()) {
            // Get direct childs of current roots into 'impl.list'
            for (MObject o : currentRoots) {
                // This add childs to 'impl.list'
                directChildren.addAll(o.getCompositionChildren());
            }
        
            // Clear the current roots list
            // in order to rebuild it for next iteration
            currentRoots.clear();
        
            // Add each new child to the result set and to the next roots list
            for (MObject child : directChildren) {
                if (children.contains(child) == false && filter.accept(child)) {
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
     * Definition of a filter that can stop iterating a child model object.
     */
    @objid ("134d92a3-5bcb-11e1-a296-001ec947ccaf")
    public interface IStopFilter {
        /**
         * Tells for the given model object whether the iteration continues or it is skipped
         * with its children.
         * @param child a model object
         * @return true to continue the iteration, <code>false</code> to skip it and its child nodes.
         */
        @objid ("134d92a5-5bcb-11e1-a296-001ec947ccaf")
        boolean accept(final MObject child);

    }

}
