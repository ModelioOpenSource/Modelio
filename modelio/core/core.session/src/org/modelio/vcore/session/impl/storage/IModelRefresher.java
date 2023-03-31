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
package org.modelio.vcore.session.impl.storage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * API interface that must be used by {@link org.modelio.vcore.session.api.repository.IRepository IRepository} implementations
 * to reload an already loaded object.
 * <p>
 * This interface has the same behavior as {@link IModelLoader} but
 * builds model change events and fires them when calling {@link IModelLoader#close()}.
 * <p>
 * All objects that become orphans at the end are deleted.
 * This interface also has a method to explicitly delete model objects.
 */
@objid ("aa5cd05c-1a01-11e2-8eb9-001ec947ccaf")
public interface IModelRefresher extends IModelLoader {
    /**
     * Delete a model object.
     * <p>
     * @param obj the model object to delete.
     */
    @objid ("a7f493ed-4bf1-4af2-855b-8dda4fa42fb9")
    void deleteObject(SmObjectImpl obj);
}

