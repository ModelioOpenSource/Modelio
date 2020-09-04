/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.api.modelio;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface is used to register new services for {@link IModelioServices}.
 * <p>
 * It is designed for Modelio internal use only, and should not be used by any Module.
 * </p>
 * 
 * @since 3.8
 */
@objid ("729ca34b-26d1-49a0-bcaf-bf64a86b2db3")
public interface IModelioServicesRegistry {
    /**
     * Register a new service to be accessible by modules.
     * <p>
     * Usually called when opening a project.
     * </p>
     * @param serviceInterface the interface implemented by the service. Should not be <code>null</code>.
     * @param service an instance of the service itself. Should not be <code>null</code>.
     */
    @objid ("01b15994-d1f2-4cb4-b4d1-4e0f7809c042")
    <I> void registerService(final Class<I> serviceInterface, final I service);

    /**
     * Unregister a service.
     * <p>
     * Usually called when closing a project.
     * </p>
     * @param serviceInterface the interface implemented by the service. Should not be <code>null</code>.
     */
    @objid ("f7560138-06d6-4b76-b50a-7b023c15b79a")
    <I> void unregisterService(final Class<I> serviceInterface);

}
