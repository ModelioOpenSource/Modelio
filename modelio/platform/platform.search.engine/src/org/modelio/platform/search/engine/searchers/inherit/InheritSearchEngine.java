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

package org.modelio.platform.search.engine.searchers.inherit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.platform.search.engine.ISearchCriteria;
import org.modelio.platform.search.engine.ISearchEngine;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IMObjectFilter;

/**
 * The InheritSearchEngine can be used to find the classes/interfaces specializing a given class/interface.<br/>
 * The InheritSearchEngine criteria are defined in {@link InheritSearchCriteria}
 * 
 * @author phv
 */
@objid ("c3299bb4-d695-4b13-95df-e614f4ba4b68")
public class InheritSearchEngine implements ISearchEngine {
    @objid ("190abfbd-ddaf-4c11-af72-369f3ad3a5f0")
    public InheritSearchEngine() {
    }

    @objid ("af51fbe2-b137-4815-bbf4-5d255b0eff3a")
    @Override
    public List<Element> search(ICoreSession session, ISearchCriteria params) {
        assert (params instanceof InheritSearchCriteria);
        final InheritSearchCriteria criteria = (InheritSearchCriteria) params;
        
        Set<NameSpace> rawResults = new HashSet<>();
        
        if (criteria.getRoot() instanceof Interface) {
            if (criteria.isFindSpecializers()) {
                rawResults.addAll(getDerivedInterfaces((Interface) criteria.getRoot(), criteria.isRecursive()));
            }
            if (criteria.isFindImplementers()) {
                rawResults.addAll(getImplementers((Interface) criteria.getRoot(), criteria.isRecursive()));
            }
        
        }
        
        if (criteria.getRoot() instanceof org.modelio.metamodel.uml.statik.Class) {
            if (criteria.isFindSpecializers()) {
                rawResults.addAll(getDerivedClasses((Class) criteria.getRoot(), criteria.isRecursive()));
            }
        }
        return filterResults(rawResults, criteria.getFilter());
    }

    @objid ("e79fcabc-eb77-41c5-97d8-c36608a048fd")
    private Set<NameSpace> getDerivedInterfaces(Interface root, boolean recurse) {
        final Set<NameSpace> results = new HashSet<>();
        this.collectDerivedInterfaces(root, results, new HashSet<NameSpace>(), recurse);
        return results;
    }

    @objid ("e04bc88a-3978-467e-b8e4-f1ee6f4bf5c4")
    private void collectDerivedInterfaces(NameSpace root, Set<NameSpace> results, Set<NameSpace> processed, boolean recurse) {
        if (processed.contains(root)) {
            return;
        }
        processed.add(root);
        for (Generalization g : root.getSpecialization()) {
            final NameSpace derived = g.getSubType();
            if (derived instanceof Interface) {
                results.add(derived);
                if (recurse) {
                    collectDerivedInterfaces(derived, results, processed, recurse);
                }
            }
        }
    }

    @objid ("093a148d-b708-4d9a-8d10-51b5c12a7830")
    private Set<NameSpace> getDerivedClasses(Class root, boolean recurse) {
        final Set<NameSpace> results = new HashSet<>();
        this.collectDerivedClasses(root, results, new HashSet<NameSpace>(), recurse);
        return results;
    }

    @objid ("48a92ef5-8abd-400c-98bf-6c81f7568795")
    private void collectDerivedClasses(NameSpace root, Set<NameSpace> results, Set<NameSpace> processed, boolean recurse) {
        if (processed.contains(root)) {
            return;
        }
        processed.add(root);
        for (Generalization g : root.getSpecialization()) {
            final NameSpace derived = g.getSubType();
            results.add(derived);
            if (recurse) {
                collectDerivedClasses(derived, results, processed, recurse);
            }
        }
    }

    /**
     * Get the implementers of an interface
     * @param root
     * 
     * @param recurse @return
     */
    @objid ("5db35c95-392c-4cba-a12b-82121c4f40d8")
    private Set<NameSpace> getImplementers(Interface root, boolean recurse) {
        Set<NameSpace> results = new HashSet<>();
        collectImplementers(root, results, new HashSet<NameSpace>(), recurse);
        return results;
    }

    @objid ("d3ef2ea7-cc85-49d0-8f48-337cfed6fc31")
    private void collectImplementers(Interface root, Set<NameSpace> results, Set<NameSpace> processed, boolean recurse) {
        if (processed.contains(root)) {
            return;
        }
        processed.add(root);
        
        for (InterfaceRealization g : root.getImplementedLink()) {
            final NameSpace implementer = g.getImplementer();
            results.add(implementer);
            if (recurse) {
                collectDerivedClasses(implementer, results, new HashSet<NameSpace>(), recurse);
            }
        }
        
        if (recurse) {
            for (Generalization g : root.getSpecialization()) {
                NameSpace derived = g.getSubType();
                if (derived instanceof Interface) {
                    collectImplementers((Interface) derived, results, processed, recurse);
                } else {
                    collectDerivedClasses(derived, results, processed, recurse);
                }
            }
        }
    }

    @objid ("05eade32-b6da-42a0-8c7b-75a53221a36b")
    private List<Element> filterResults(Set<NameSpace> rawResults, IMObjectFilter filter) {
        final List<Element> filteredResults = new ArrayList<>();
        if (filter == null) {
            filteredResults.addAll(rawResults);
        } else {
            for (final NameSpace mObject : rawResults) {
                // apply filter
                if (filter.accept(mObject)) {
                    filteredResults.add(mObject);
                }
            }
        }
        return filteredResults;
    }

}
