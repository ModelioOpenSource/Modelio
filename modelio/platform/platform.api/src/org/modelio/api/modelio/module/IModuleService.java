/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.api.modelio.module;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IPeerModule;

/**
 * This interface defines how to get the public interface from modules.
 */
@objid ("22c4edea-6bf5-11e0-a371-001ec947cd2a")
public interface IModuleService {
    /**
     * Returns the list of deployed peer module.
     * @return the collection of deployed peer modules.
     */
    @objid ("00d00158-0001-a8c7-0000-000000000000")
    Collection<IPeerModule> getAllPeerModules();

    /**
     * Get the public services of a specific Java module.
     * <p>
     * <p>
     * This method needs the concrete interface of a module to return the
     * loaded instance of this peer module.
     * <p>
     * <p>
     * For example, the following example shows a call of this method to return a specific module named MyMDAC:
     * <p>
     * <p>
     * <code>
     * IModelingSession session = Modelio.getInstance().getModelingSession();<br>
     * IMyMDACPeerModule peerModule = (IMyMDACPeerModule)session.getModuleService().getPeerModule(IMyMDACPeerModule.class);
     * </code>
     * <p>
     * <p>
     * The returned peer module can be casted without risk to the right desired module, but only if
     * a dependency exists between the returned module, and the one requesting the peer.
     * @param <T> The peer module type.
     * @param peerClass The peer module class.
     * @return the peer module regarding the given metaclass. Might be {@link NullPointerException} if no such module is loaded.
     */
    @objid ("00d00158-0001-a337-0000-000000000000")
    <T extends IPeerModule> T getPeerModule(final Class<T> peerClass);

    /**
     * Get the public services of a specific Java module from its name.
     * <p>
     * <p>
     * The returned peer module can be casted without risk to the right desired module, but only if
     * a dependency exists between the returned module, and the one requesting the peer.
     * @param moduleName The module name.
     * @return the peer module regarding the given metaclass. Might be {@link NullPointerException} if no such module is loaded.
     * @since 2.2
     */
    @objid ("50ba2430-b14f-11e1-8436-002564c97630")
    IPeerModule getPeerModule(final String moduleName);

}
