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

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Hook on the data model that can alter the content of a loaded dependency.
 */
@objid ("e26997d5-4065-11e2-87cb-001ec947ccaf")
public interface IDependencyContentHook {
    /**
     * get the content of a given model dependency.
     * <p>
     * Can modify the content of the dependency.
     * 
     * @param obj the loading model object
     * @param dep the dependency being loaded.
     * @return the read dependency content or <code>null</code> if nothing.
     */
    @objid ("ddfaf33d-407a-11e2-87cb-001ec947ccaf")
    List<SmObjectImpl> getContent(SmObjectImpl obj, SmDependency dep);

    /**
     * Get the dependencies for which content has to be added.
     * 
     * @param obj a model object
     * @return dependencies for which content has to be added. Returns an empty list if nothing to return.
     */
    @objid ("fdbb3a3f-4f18-4a69-a614-94f838b5caaf")
    Collection<? extends Content> getContent(SmObjectImpl obj);

    /**
     * Content for a dependency on a model object
     */
    @objid ("e62bc1c1-d6d6-4994-88b2-898ef9ae0ed1")
    interface Content {
        /**
         * @return the refered dependency
         */
        @objid ("ebeea589-7578-4cc8-b30d-96d309391edb")
        SmDependency getDep();

        /**
         * @return the dependency content
         */
        @objid ("11d91fe5-4bb8-4cc6-a6dd-fef6ee08116e")
        List<SmObjectImpl> getContent();

    }

}
