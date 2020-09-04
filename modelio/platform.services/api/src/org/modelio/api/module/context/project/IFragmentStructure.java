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

package org.modelio.api.module.context.project;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Element;

/**
 * This interface describes a Fragment in a project: ie a name, a version, a type...
 * 
 * @since 3.5
 */
@objid ("bc975695-2cf9-4625-acbe-b0d0f3951167")
public interface IFragmentStructure {
    /**
     * Get the fragment name.
     * @return a name.
     */
    @objid ("526cd01a-a515-40ff-94cc-5d7a1c9eb902")
    String getName();

    /**
     * Get the fragment type identifier.
     * <p>
     * Might be <b>EXML</b>, <b>EXML_URL</b>, <b>EXML_SVN</b>, <b>RAMC</b> or <b>MDA</b>.
     * </p>
     * @return the fragment type.
     */
    @objid ("ab059b4c-4adc-4319-8a07-17676841a19f")
    String getType();

    /**
     * Get the fragment's URI in the opened project.
     * @return the mounted URI or <code>null</code> if the fragment is not mounted.
     */
    @objid ("69df667e-ddf3-4987-bcaa-51c6dbbfe569")
    String getRemoteLocation();

    /**
     * Get the state of the fragment.
     * <p>
     * Should be <b>UP_FULL</b>, <b>UP_LIGHT</b> or <b>DOWN</b>.
     * </p>
     * @return the fragment state.
     */
    @objid ("4ef123cf-9fbf-432b-83e8-b1e18ecfa735")
    String getState();

    /**
     * Get the root elements of the fragment.
     * @return the root elements of the fragment.
     */
    @objid ("b06ac3fa-1915-4837-b13e-b9327c0b9787")
    List<Element> getRoots();

}
