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

package org.modelio.api.module.context.project;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

/**
 * This interface describes a Module: ie a name, a version and an optional URL for
 * its archive.
 * 
 * @since 3.5
 */
@objid ("39c049f7-d5cb-11de-884b-001ec947cd2a")
public interface IModuleStructure {
    /**
     * Get the module name.
     * 
     * @return a name.
     */
    @objid ("5af8b46e-d5cc-11de-884b-001ec947cd2a")
    String getName();

    /**
     * Get the module version.
     * 
     * @return a version
     */
    @objid ("5af8b470-d5cc-11de-884b-001ec947cd2a")
    Version getVersion();

    /**
     * The path to the current module archive.
     * 
     * @return an url for the module archive. Might be <code>null</code>.
     */
    @objid ("5af8b472-d5cc-11de-884b-001ec947cd2a")
    String getArchive();

}
