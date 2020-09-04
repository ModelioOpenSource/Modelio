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

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This interface describes a ModelComponent: ie a name, a version and an
 * optional URL for its archive.
 * The returned archive may be null.
 */
@objid ("e6470c4c-d5cb-11de-884b-001ec947cd2a")
public interface IModelComponentDescriptor {
    /**
     * Get the name of the model component.
     * 
     * @return the name of the model component.
     */
    @objid ("318987b6-d5cc-11de-884b-001ec947cd2a")
    String getName();

    /**
     * Get the version of the model component.
     * 
     * @return the version of the model component.
     */
    @objid ("318987b8-d5cc-11de-884b-001ec947cd2a")
    String getVersion();

    /**
     * @return relative files deployed by the model component in the project.
     * @since 3.7.1
     */
    @objid ("3be6a92c-7600-42c9-a5b4-10e2f76a6816")
    default List<Path> getDeployedResources() {
        return Collections.emptyList();
    }

    /**
     * @param filter a path filter
     * @return relative files deployed by the model component in the project and matching the filter.
     * @throws java.io.IOException If an I/O error occurs
     * @since 3.7.1
     */
    @objid ("824b657c-f9cd-4a18-b755-fdd5d8b87deb")
    default List<Path> getDeployedResources(Filter<Path> filter) throws IOException {
        List<Path> ret = new ArrayList<>();
        for (Path entry : getDeployedResources()) {
            if (filter.accept(entry)) {
                ret.add(entry);
            }
        }
        return Collections.unmodifiableList(ret);
    }

}
