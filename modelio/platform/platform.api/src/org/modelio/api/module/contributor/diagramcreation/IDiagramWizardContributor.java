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
package org.modelio.api.module.contributor.diagramcreation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * Redefined contract for {@link AbstractDiagram} creations in the Creation Wizard.
 */
@objid ("5bfbaf98-911c-11e0-9de7-002564c97630")
public interface IDiagramWizardContributor extends IWizardContributor {
    @objid ("d94e0d2a-5b07-11e2-9c97-002564c97630")
    @Override
    AbstractDiagram actionPerformed(final ModelElement diagramContext, final String diagramName, final String diagramDescription);

}
