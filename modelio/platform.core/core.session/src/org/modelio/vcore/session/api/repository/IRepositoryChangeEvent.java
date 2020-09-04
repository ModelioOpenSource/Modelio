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

package org.modelio.vcore.session.api.repository;

import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Event fired by a {@link IRepository} when its content changes from outside of the running Modelio.
 */
@objid ("abb99b79-3ef9-4376-bb52-5bf4cf33ba7d")
public interface IRepositoryChangeEvent {
    /**
     * Get the created elements.
     * @return the created element references.
     */
    @objid ("04441b05-913a-47eb-9412-2c6cbce60468")
    List<MRef> getCreatedElements();

    /**
     * Get the elements deleted.
     * @return the deleted element references.
     */
    @objid ("46ace76d-cd3c-4115-a6a8-736e0be07e41")
    List<MRef> getDeletedElements();

    /**
     * Get the Modified elements.
     * @return the updated element references.
     */
    @objid ("a3293439-912d-4e17-b333-bc211accf530")
    List<MRef> getModifiedElements();

    /**
     * Get the created blobs identifiers.
     * @return the created blobs identifiers.
     */
    @objid ("6d441c5a-4d67-4afe-b443-aa5345789e75")
    Collection<String> getCreatedBlobs();

    /**
     * Get the deleted blobs identifiers.
     * @return the deleted blobs identifiers.
     */
    @objid ("6e678f5b-2544-4750-94cc-3463d833d2d8")
    Collection<String> getDeletedBlobs();

    /**
     * Get the updated blobs identifiers.
     * @return the updated blobs identifiers.
     */
    @objid ("dda6b12d-3182-4260-9c77-4b22a17ae716")
    Collection<String> getModifiedBlobs();

    /**
     * @return the modified repository.
     */
    @objid ("8ae8b317-b1c4-4778-b5fd-760ef07c8c64")
    IRepository getRepository();

    /**
     * Get the precision of the repository change event.
     * @return the change event precision.
     */
    @objid ("2a5aeee7-a89f-4e44-a914-99b30eaa52cd")
    Granularity getGranularity();

    /**
     * Precision of the repository change event.
     */
    @objid ("353ea8da-a209-41f0-b227-76f1d91ce89f")
    enum Granularity {
        /**
         * The event contains the exact list of modified elements.
         */
        OBJECT,
        /**
         * The event contains only the changed CMS nodes.
         * <p>
         * eg: an object created inside a CMS node is reported as a changed CMS node.
         */
        CMSNODE,
        /**
         * There is no detail about the change event.
         * <p>
         * The listener can only consider that anything may have been changed in the repository.
         */
        UNDEFINED;
    }

}
