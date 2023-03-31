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
package org.modelio.api.modelio.diagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Diagram drawings are diagram graphics that are by nature not related to a model elements.
 * <p>
 * {@link IDiagramGraphic#getElement()} will always return <code>null</code> for instances.
 * 
 * @since 3.1
 */
@objid ("681590d6-c4ba-4649-88e8-05f3402d45a0")
public interface IDiagramDrawing extends IDiagramGraphic {
// nothing more
    }

