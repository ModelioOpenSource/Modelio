/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.model.event;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This interface provide the API of a Status Change Event
 */
@objid ("6efba1ea-f791-11dd-83f5-0014222a9f79")
public interface IStatusChangeEvent {
    /**
     * Get the element whose access rights changed.
     * 
     * @return element whose access rights changed.
     */
    @objid ("48596c10-ce58-4f71-af1f-765275a52767")
    Collection<MObject> getAccessChanged();

    /**
     * @return elements whose CMS state changed.
     */
    @objid ("b047c0f0-1567-45f0-8c28-f97b5dbfe245")
    Collection<MObject> getCmsStatusChanged();

    /**
     * @return elements whose audit state changed.
     */
    @objid ("8351d194-7a98-4577-aafb-630abbbbccf3")
    Collection<MObject> getAuditStatusChanged();

    /**
     * @return elements whose shell state changed.
     */
    @objid ("d8f22c02-da9c-4302-b67c-faa61c8a6f23")
    Collection<MObject> getShellStateChanged();

    /**
     * Get all the elements whose status changed.
     * 
     * @return the changed elements.
     */
    @objid ("1326b059-63d8-4e08-bd3e-53a8a0388521")
    Collection<MObject> getStatusChanged();

}
