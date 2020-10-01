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

import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.platform.search.engine.ISearchCriteria;
import org.modelio.vcore.session.api.model.IMObjectFilter;

@objid ("b05644fe-4b56-43fa-9600-189c638fa9a2")
public class InheritSearchCriteria implements ISearchCriteria {
    /**
     * This flag is only relevant when root is an interface.<br/>
     * If true the searcher will include the classes implementing the interface or any of its specialized sub-interfaces.
     */
    @mdl.prop
    @objid ("3a24c245-e917-4aaf-b001-a69aa8e93873")
    private boolean findImplementers = true;

    @mdl.propgetter
    public boolean isFindImplementers() {
        // Automatically generated method. Please do not modify this code.
        return this.findImplementers;
    }

    @mdl.propsetter
    public void setFindImplementers(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.findImplementers = value;
    }

    /**
     * If true the searcher will include the NameSpace specializing 'root' (ie sub-classes if root is a Class, sub-interfaces if
     * root is an Interface)
     */
    @mdl.prop
    @objid ("0720a2bd-a372-44b8-8a96-f74caf61e890")
    private boolean findSpecializers = true;

    @mdl.propgetter
    public boolean isFindSpecializers() {
        // Automatically generated method. Please do not modify this code.
        return this.findSpecializers;
    }

    @mdl.propsetter
    public void setFindSpecializers(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.findSpecializers = value;
    }

    /**
     * If true the searcher will behave recursively in the inheritance tree.
     */
    @mdl.prop
    @objid ("330bc98e-cb8b-4e8d-9c34-7172bea0bfcb")
    private boolean recursive;

    @mdl.propgetter
    public boolean isRecursive() {
        // Automatically generated method. Please do not modify this code.
        return this.recursive;
    }

    @mdl.propsetter
    public void setRecursive(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.recursive = value;
    }

    @mdl.prop
    @objid ("36a8edf2-605f-4b6f-96b8-83d909804e89")
    private IMObjectFilter filter;

    @mdl.propgetter
    public IMObjectFilter getFilter() {
        // Automatically generated method. Please do not modify this code.
        return this.filter;
    }

    @mdl.propsetter
    public void setFilter(IMObjectFilter value) {
        // Automatically generated method. Please do not modify this code.
        this.filter = value;
    }

    @mdl.prop
    @objid ("8487f4e9-e41e-4797-bb41-1cf02171b787")
    private NameSpace root;

    @mdl.propgetter
    public NameSpace getRoot() {
        // Automatically generated method. Please do not modify this code.
        return this.root;
    }

    @mdl.propsetter
    public void setRoot(NameSpace value) {
        // Automatically generated method. Please do not modify this code.
        this.root = value;
    }

}
