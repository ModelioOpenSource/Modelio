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
package org.modelio.vstore.exml.local.loader.sax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.storage.IModelLoader;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vstore.exml.common.model.DependencyNotFoundException;
import org.modelio.vstore.exml.common.utils.ExmlUtils;
import org.modelio.vstore.exml.local.loader.sax.IDependencyContentHook.Content;

/**
 * Data model for a read model object.
 */
@objid ("2af7927c-3faf-11e2-87cb-001ec947ccaf")
final class ObjectDataModel implements IObjectDataModel {
    /**
     * Tells whether it is a new loaded object or it
     * already existed in memory.
     */
    @objid ("67e4dcb0-2a7f-4cc6-b908-b492bd9b8b12")
    private final boolean isNew;

    /**
     * Already read dependencies while loading a model object.
     * <p>
     * Other dependencies will be reset.
     */
    @objid ("2af7927d-3faf-11e2-87cb-001ec947ccaf")
    private final Collection<SmDependency> readDeps = new ArrayList<>();

    @objid ("2af79281-3faf-11e2-87cb-001ec947ccaf")
    private final SmObjectImpl current;

    @objid ("2af79282-3faf-11e2-87cb-001ec947ccaf")
    private SmDependency currentDep;

    @objid ("2af79283-3faf-11e2-87cb-001ec947ccaf")
    private List<SmObjectImpl> currentDepContent = DataModel.EMPTY_DEP;

    @objid ("2af79286-3faf-11e2-87cb-001ec947ccaf")
    private AbstractState currentstate;

    @objid ("934e2a22-babc-4938-bdae-46df73aac977")
    private final DataModel dataModel;

    @objid ("2af79287-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void finishDependenciesLoading() {
        if ( this.dataModel.getVersion() >= 3) {
            final IModelLoader theModelLoader = this.dataModel.modelLoader;
            final IDependencyContentHook depHook = this.dataModel.depContentHook;
            if (this.isNew) {
                if (depHook != null) {
                    // The object is now in memory so all dependencies were empty on start.
                    // Add the non hooked local content
                    for (Content dep : depHook.getContent(this.current)) {
                        if (! this.readDeps.contains(dep.getDep())) {
                            theModelLoader.loadDependency(this.current, dep.getDep(), dep.getContent());
                        }
                    }
                }
            } else {
                // Object was already in memory:
                // Look for non loaded dependencies and replace their content by the hooked content
                // or empty them.
                for (SmDependency dep: ExmlUtils.getExternalisableDeps(this.current)) {
                    if (! this.readDeps.contains(dep)) {
                        if (depHook == null) {
                            theModelLoader.loadDependency(this.current, dep,  DataModel.EMPTY_DEP);
                        } else {
                            List<SmObjectImpl> moreContent = depHook.getContent(this.current, dep) ;
                            if (moreContent == null) {
                                moreContent = DataModel.EMPTY_DEP;
                            }
        
                            theModelLoader.loadDependency(this.current, dep,  moreContent);
                        }
                    }
                }
            }
        }
        
    }

    @objid ("2af79289-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void setCurrentState(AbstractState currentstate) {
        this.currentstate = currentstate;
    }

    @objid ("2af7928c-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public AbstractState getCurrentState() {
        return this.currentstate;
    }

    @objid ("2af79290-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void updateCurrentDependency() {
        if (this.currentDep != null) {
            if (this.dataModel.depContentHook!=null) {
                List<SmObjectImpl> moreContent = this.dataModel.depContentHook.getContent(this.current, 
                        this.currentDep);
        
                if (moreContent != null) {
                    if (this.currentDepContent == DataModel.EMPTY_DEP) {
                        this.currentDepContent = moreContent;
                    } else {
                        this.currentDepContent.addAll(moreContent);
                    }
                }
            }
        
            this.dataModel.modelLoader.loadDependency(this.current, 
                    this.currentDep, 
                    this.currentDepContent);
            this.readDeps.add(this.currentDep);
        }
        
        this.currentDepContent = DataModel.EMPTY_DEP;
        this.currentDep = null;
        
    }

    @objid ("2af79292-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void beginDependency(String depName) throws DependencyNotFoundException {
        this.currentDep = findDependencyDef(this.current, depName);
        
        if (this.currentDep == null) {
            throw new DependencyNotFoundException("'"+depName + "' dependency not found for "+this.current);
        }
        
    }

    @objid ("2af79295-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public void addToDep(SmObjectImpl obj) {
        assert (obj != null);
        
        if (this.currentDepContent == DataModel.EMPTY_DEP) {
            this.currentDepContent = new ArrayList<>(3);
        }
        
        this.currentDepContent.add(obj);
        
    }

    /**
     * Find the SmDependency from its name.
     * <p>
     * Modelio 2 compatibility : convert to camel case if not found
     * @param object an object
     * @param relation the relation name
     * @return the found dependency or <code>null</code>.
     */
    @objid ("2af79298-3faf-11e2-87cb-001ec947ccaf")
    private SmDependency findDependencyDef(SmObjectImpl object, final String relation) {
        SmDependency smdep = object.getClassOf().getDependencyDef(relation);
        
        if (smdep == null ){
            // Modelio compatibility : convert to camel case
            String rel2 = relation.substring(0, 1).toLowerCase(Locale.ROOT)+relation.substring(1);
            smdep = object.getClassOf().getDependencyDef(rel2);
        }
        return smdep;
    }

    @objid ("2af792a0-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public SmObjectImpl getObject() {
        return this.current;
    }

    @objid ("2af792a4-3faf-11e2-87cb-001ec947ccaf")
    @Override
    public String toString() {
        return this.current + ", dep="+ this.currentDep + " , size=" + this.currentDepContent.size()+ ", state=" +this.currentstate;
    }

    /**
     * Initialize the object data model
     * @param dataModel TODO
     * @param obj the loaded object
     * @param isNew <code>true</code> if the object didn't exist in memory.
     */
    @objid ("0e2ec301-3fc4-11e2-87cb-001ec947ccaf")
    public  ObjectDataModel(DataModel dataModel, SmObjectImpl obj, boolean isNew) {
        this.dataModel = dataModel;
        this.current = obj;
        this.isNew = isNew;
        
    }

}
