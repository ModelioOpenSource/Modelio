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

package org.modelio.api.modelio.mc;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor;

/**
 * Default implementation of {@link IModelComponentContributor}, storing the current module.
 */
@objid ("a07b6504-ee0c-11dd-bb8c-0014222a9f79")
public abstract class AbstractModelComponentContributor implements IModelComponentContributor {
    @objid ("f7f14890-ee0c-11dd-bb8c-0014222a9f79")
    private IModule module;

    /**
     * Constructor initializing the module.
     * 
     * @param module the module this contributor is about.
     */
    @objid ("f7ec83e0-ee0c-11dd-bb8c-0014222a9f79")
    public AbstractModelComponentContributor(IModule module) {
        this.module = module;
    }

    /**
     * Get the module attached to this contributor.
     * 
     * @return a IModule.
     */
    @objid ("f7eee633-ee0c-11dd-bb8c-0014222a9f79")
    public IModule getModule() {
        return this.module;
    }

}
