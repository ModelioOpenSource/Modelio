/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.vcore.session.api.blob;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Related blob provider interface.
 * <p>
 * To be registered on the {@link IBlobSupport}.
 */
@objid ("8fd9c441-c111-4ca2-b395-95b51962b08b")
public interface IBlobProvider {
    /**
     * Get all blobs related to a given model object.
     * <p>
     * Asks all registered blob providers.
     * 
     * @param obj a model object
     * @return all related blobs
     */
    @objid ("46e9ce60-ae74-4ed2-9cac-32b0eae1ea86")
    Collection<String> getRelatedBlobs(MObject obj);

    /**
     * Called when a model object has been copied or imported.
     * <p>
     * Both objects may belong to the same project or to different ones.
     * The model objects may have the same identifier in case of import or
     * different identifiers in case of copy.
     * <p>
     * The implementation should decide what to do with the blobs it handles.
     * An implementation usually duplicate the blobs of the original object.
     * 
     * @param from the original model object
     * @param fromRepo the original model object repository
     * @param to the model object copy.
     * @param toRepo the object copy repository
     */
    @objid ("c6402278-1060-487e-b503-8e62de46aaa5")
    void objectCopied(MObject from, IRepository fromRepo, MObject to, IRepository toRepo);

    /**
     * Called when model objects have been moved to another repository.
     * <p>
     * The model objects are already in the new repository.
     * <p>
     * The implementation should decide what to do with the blobs it handles.
     * An implementation usually moves the blobs of the moved object to the destination repository.
     * 
     * @param objs the moved model objects. the model object is already in the new repository.
     * @param fromRepo its previous repository
     * @param destRepo its new repository.
     */
    @objid ("d00a97c8-6f5e-4199-bb62-10d828b6dce7")
    void objectsMoved(Collection<? extends MObject> objs, IRepository fromRepo, IRepository destRepo);

}
