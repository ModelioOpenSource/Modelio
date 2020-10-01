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

package org.modelio.api.ui.viewtemplate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * A Model View template creates a well-defined specific view from a so-called 'base' element.
 * Views are persisted MObject defining a representation of some part of a model, a view is currently either a diagram or a matrix.
 * 
 * The exact usage or role of the 'base' element depends on the template implementation and goals.
 * 
 * Model View Template are identified by a unique identifier {@link #getId()}.
 * Several 'standard' model views template comes with Modelio, additional ones can be contributed either by extra-plugins or modules.
 */
@objid ("c807abbd-9b9c-4c92-a27b-0e63d740b6e1")
public interface IModelViewTemplate<T extends MObject> {
    /**
     * Get the unique identifier of a model view template.
     * 
     * @return the model view template Id.
     */
    @objid ("a311200a-0f96-4a5b-8e42-e2208010b706")
    String getId();

    /**
     * Create the view for a given element.
     * 
     * @param base The element used by the creator to create the view. Depending on the creator behavior this element can simply be the composition parent of the created diagram or be interpreted differently (example subject of the diagram).
     * @return The created view.
     */
    @objid ("25d2c54e-49ce-4c40-8348-6191e58a49ef")
    T createView(final ModelElement base);

    /**
     * Get the existing view for a given element.
     * Must implement the same 'main' element interpretation as in {@link #createView(ModelElement)}
     * 
     * @param base The element to look a view for.
     * @return The corresponding  view or null.
     */
    @objid ("b4ed1769-92e0-4456-95a1-9c11c3662605")
    T getExistingView(final ModelElement base);

    /**
     * Update a view for the given element.
     * 
     * @param existingView the view to be updated. Must be the one returned by {@link #getExistingView(ModelElement)}.
     */
    @objid ("ecc0b311-deb5-4de8-939a-d694a5f05c28")
    void updateView(T existingView);

    /**
     * Get the composition owner that this creator would use to create a view for main element <b>base</b>.
     * 
     * This defines the 'base element' resolution algorithm implementation for this template.
     * 
     * @param base the base element
     * @return the current view origin.
     */
    @objid ("127fbf1c-ea78-44e8-9713-fa5435835f4c")
    ModelElement resolveOrigin(final ModelElement base);

    /**
     * Get the base element the view was created from.
     * This is the inverse resolution of {@link #getExistingView(ModelElement)}.
     * 
     * @param view a MObject representing a view.
     * @return The element the view was created for by this template.
     */
    @objid ("601b42a7-92dd-4225-9a45-fab6fb6d5cd7")
    ModelElement getMainElement(T view);

}
