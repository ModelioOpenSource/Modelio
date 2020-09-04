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

package org.modelio.linkeditor.ext.depfilter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

@objid ("1b61a60e-5e33-11e2-b81d-002564c97630")
class DialogModel {
    @objid ("1b61a61b-5e33-11e2-b81d-002564c97630")
    private static final String FILTER = "filter model changed";

    @objid ("1b61a60f-5e33-11e2-b81d-002564c97630")
    private Map<ModuleComponent, Set<Stereotype>> notFilterStereotypes = new HashMap<>();

    @objid ("1b61a615-5e33-11e2-b81d-002564c97630")
    private Map<ModuleComponent, Set<Stereotype>> filterStereotypes = new HashMap<>();

    @objid ("1b61a61d-5e33-11e2-b81d-002564c97630")
    private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    @objid ("8fd409c6-5efc-11e2-a8be-00137282c51b")
    private IMModelServices modelServices;

    @objid ("1b61a61e-5e33-11e2-b81d-002564c97630")
    public List<Stereotype> getFilter() {
        // Build complete list from current filter map.
        List<Stereotype> filter = new ArrayList<>();
        filter.clear();
        for (ModuleComponent module : this.filterStereotypes.keySet()) {
            for (Stereotype stereotype : this.filterStereotypes.get(module)) {
                filter.add(stereotype);
            }
        }
        return filter;
    }

    @objid ("1b61a624-5e33-11e2-b81d-002564c97630")
    public DialogModel(final List<Stereotype> enabledStereotypes, IMModelServices modelServices) {
        this.modelServices = modelServices;
        
        // Build the list of all stereotypes brought by mdacs for Dependency.
        List<Stereotype> stereotypes = this.modelServices.findStereotypes(".*", ".*", Dependency.MQNAME);
        
        for (Stereotype stereo : stereotypes) {
            if (!stereo.isIsHidden() && !"trace".equals(stereo.getName())) {
                ModuleComponent owningModule = stereo.getOwner().getOwnerModule();
        
                Map<ModuleComponent, Set<Stereotype>> mapToUse;
                if (enabledStereotypes.contains(stereo)) {
                    mapToUse = this.filterStereotypes;
                } else {
                    mapToUse = this.notFilterStereotypes;
                }
                Set<Stereotype> set = mapToUse.get(owningModule);
                if (set == null) {
                    mapToUse.put(owningModule, new HashSet<Stereotype>());
                    set = mapToUse.get(owningModule);
                }
                set.add(stereo);
            }
        }
    }

    /**
     * Adds a specific stereotype to the filter.
     * 
     * @param stereotype the stereotype to add to the filter.
     */
    @objid ("1b61a62a-5e33-11e2-b81d-002564c97630")
    public void addToFilter(final Stereotype stereotype) {
        // Find the module bringing this stereotype.
        ModuleComponent module = stereotype.getOwner().getOwnerModule();
        assert (module != null) : "Could not find module that brought the stereotype: " + stereotype.getName();
        
        this.notFilterStereotypes.get(module).remove(stereotype);
        if (this.notFilterStereotypes.get(module).isEmpty()) {
            this.notFilterStereotypes.remove(module);
        }
        Set<Stereotype> set = this.filterStereotypes.get(module);
        if (set == null) {
            this.filterStereotypes.put(module, new HashSet<Stereotype>());
            set = this.filterStereotypes.get(module);
        }
        set.add(stereotype);
        this.listeners.firePropertyChange(FILTER, null, stereotype);
    }

    /**
     * Removes a specific stereotype from the filter.
     * 
     * @param stereotype the stereotype to remove from the filter.
     */
    @objid ("1b61a62f-5e33-11e2-b81d-002564c97630")
    public void removeFromFilter(final Stereotype stereotype) {
        // Find the module bringing this stereotype.
        ModuleComponent module = stereotype.getOwner().getOwnerModule();
        assert (module != null) : "Could not find module that brought the stereotype: " + ModuleI18NService.getLabel(stereotype);
        
        this.filterStereotypes.get(module).remove(stereotype);
        if (this.filterStereotypes.get(module).isEmpty()) {
            this.filterStereotypes.remove(module);
        }
        Set<Stereotype> set = this.notFilterStereotypes.get(module);
        if (set == null) {
            this.notFilterStereotypes.put(module, new HashSet<Stereotype>());
            set = this.notFilterStereotypes.get(module);
        }
        set.add(stereotype);
        this.listeners.firePropertyChange(FILTER, null, stereotype);
    }

    /**
     * Adds all stereotypes of a module to the filter.
     * 
     * @param module the module which stereotypes are to be added to the filter.
     */
    @objid ("1b61a634-5e33-11e2-b81d-002564c97630")
    public void addToFilter(final ModuleComponent module) {
        Set<Stereotype> fromSet = this.notFilterStereotypes.get(module);
        if (fromSet != null) {
            Set<Stereotype> toSet = this.filterStereotypes.get(module);
            if (toSet == null) {
                this.filterStereotypes.put(module, new HashSet<Stereotype>());
                toSet = this.filterStereotypes.get(module);
            }
        
            toSet.addAll(fromSet);
            fromSet.clear();
            this.notFilterStereotypes.remove(module);
            this.listeners.firePropertyChange(FILTER, null, module);
        }
    }

    /**
     * Removes all stereotypes of a module from the filter.
     * 
     * @param module the module which stereotypes are to be removed from the filter.
     */
    @objid ("1b61a639-5e33-11e2-b81d-002564c97630")
    public void removeFromFilter(final ModuleComponent module) {
        Set<Stereotype> fromSet = this.filterStereotypes.get(module);
        if (fromSet != null) {
            Set<Stereotype> toSet = this.notFilterStereotypes.get(module);
            if (toSet == null) {
                this.notFilterStereotypes.put(module, new HashSet<Stereotype>());
                toSet = this.notFilterStereotypes.get(module);
            }
        
            toSet.addAll(fromSet);
            fromSet.clear();
            this.filterStereotypes.remove(module);
            this.listeners.firePropertyChange(FILTER, null, module);
        }
    }

    @objid ("1b61a63e-5e33-11e2-b81d-002564c97630")
    Map<ModuleComponent, Set<Stereotype>> getNotFilterStereotypes() {
        return this.notFilterStereotypes;
    }

    @objid ("1b61a647-5e33-11e2-b81d-002564c97630")
    Map<ModuleComponent, Set<Stereotype>> getFilterStereotypes() {
        return this.filterStereotypes;
    }

    @objid ("1b61a650-5e33-11e2-b81d-002564c97630")
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.listeners.removePropertyChangeListener(listener);
        this.listeners.addPropertyChangeListener(listener);
    }

    @objid ("1b61a654-5e33-11e2-b81d-002564c97630")
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.listeners.removePropertyChangeListener(listener);
    }

}
