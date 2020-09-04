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

package org.modelio.api.module.lifecycle;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

/**
 * The life cycle handler of a module is a set of methods that are called by
 * Modelio on some events related to the module lifecycle in the project (such
 * as starting, selecting, stopping and so on...) This interface replaces the
 * former IModuleSession interface.
 * 
 * @since 3.5
 */
@objid ("3a959ed3-7645-4e21-aa67-2f4e673b77c5")
public interface IModuleLifeCycleHandler {
    /**
     * Called when the mdac is selected, which corresponds to the user action
     * "Deploy a mdac...".
     * 
     * @return if the mdac accepts to be selected
     * @throws org.modelio.api.module.lifecycle.ModuleException when an error occurs preventing the module from being
     * selected.
     */
    @objid ("01f40414-0000-328f-0000-000000000000")
    boolean select() throws ModuleException;

    /**
     * Called when the mdac is started. A mdac is started when:<br>
     * <ul>
     * <li>the mdac has just been selected
     * <li>the project has just been opened
     * <li>the mdac has finished to be upgraded from a previous version
     * </ul>
     * 
     * @return if the mdac accepts to be started
     * @throws org.modelio.api.module.lifecycle.ModuleException when an error occurs preventing the module from starting.
     */
    @objid ("01f40414-0000-3294-0000-000000000000")
    boolean start() throws ModuleException;

    /**
     * Called before the mdac is stopped. A mdac is stopped when:<br>
     * <ul>
     * <li>the mdac has just been unselected
     * <li>the project is being closed
     * <li>the mdac is going to be upgraded to another version
     * </ul>
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException when an error occurs preventing the module from stopping.
     */
    @objid ("01f40414-0000-3299-0000-000000000000")
    void stop() throws ModuleException;

    /**
     * Called when the mdac is deselected from the project.
     * 
     * @throws org.modelio.api.module.lifecycle.ModuleException when an error occurs preventing the module from being
     * unselected.
     */
    @objid ("01f40414-0000-329c-0000-000000000000")
    void unselect() throws ModuleException;

    /**
     * Called when the mdac is being upgraded from a previous version.
     * 
     * @param oldVersion The previous version of the MDAC
     * @param oldParameters The previous list of parameters (key, value)
     * @throws org.modelio.api.module.lifecycle.ModuleException when an error occurs preventing the module from being
     * upgraded.
     */
    @objid ("01f40414-0000-329f-0000-000000000000")
    void upgrade(Version oldVersion, Map<String, String> oldParameters) throws ModuleException;

}
