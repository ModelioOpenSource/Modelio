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

package org.modelio.vstore.exml.common.model;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Represents a dependency content.
 */
@objid ("fd26ba07-5986-11e1-991a-001ec947ccaf")
public class ObjDep {
    /**
     * The object
     */
    @objid ("fd21f5f1-5986-11e1-991a-001ec947ccaf")
    public SmObjectImpl src;

    /**
     * The dependency
     */
    @objid ("fd21f5f2-5986-11e1-991a-001ec947ccaf")
    public SmDependency dep;

    /**
     * The dependency content.
     */
    @objid ("fd21f5a1-5986-11e1-991a-001ec947ccaf")
    public List<SmObjectImpl> content = new ArrayList<>();

    /**
     * Constructor.
     * @param src the object
     * @param dep the dependency.
     */
    @objid ("fd245746-5986-11e1-991a-001ec947ccaf")
    public ObjDep(SmObjectImpl src, SmDependency dep) {
        this.src = src;
        this.dep = dep;
    }

}
