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

import java.nio.file.Path;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface describes the Modelio project itself: ie its name, path, type...
 * <p>
 * Also describes the installed fragments and modules.
 * </p>
 * 
 * @since 3.5
 */
@objid ("4cd15a8b-b42b-44e7-b338-79f87121bb76")
public interface IProjectStructure {
    /**
     * Get the project's name.
     * @return the project's name.
     */
    @objid ("22daa599-ccdd-4c45-a054-fb3bc6f54d15")
    String getName();

    /**
     * Get the project type, either <b>LOCAL</b> or <b>HTTP</b>.
     * @return the project type.
     */
    @objid ("93510ff2-68ee-42d1-a1fe-a3123ee56fc2")
    String getType();

    /**
     * Get the project directory i.e. the project.conf location.
     * @return the directory containing the project.conf.
     */
    @objid ("dc8ba239-9da0-4a89-83c5-1cabf44c58d4")
    Path getPath();

    /**
     * Get the project remote location.
     * <p>
     * Returns <code>null</code> for <b>LOCAL</b> projects.
     * </p>
     * @see #getType()
     * @return the project remote location.
     */
    @objid ("ac3b1702-359f-4746-95a7-54743ff04a59")
    String getRemoteLocation();

    /**
     * Get the list of all model fragments in the project.
     * <p>
     * The returned list is not modifiable.
     * </p>
     * @return all model fragments.
     */
    @objid ("c6c92167-5308-44f3-8b97-47d60b2e72bd")
    List<IFragmentStructure> getFragments();

    /**
     * Get the list of all modules in the project.
     * <p>
     * The returned list is not modifiable.
     * </p>
     * @return all model fragments.
     */
    @objid ("31f17644-a9a3-41cd-85c7-3106358e992a")
    List<IModuleStructure> getModules();
}

