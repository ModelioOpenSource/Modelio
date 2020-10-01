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
import org.modelio.api.module.contributor.AbstractWizardContributor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Convenience abstract class that provides an abstract implementation of {@link IDiagramWizardContributor}.
 * 
 * <p><u>Note to contributor implementors:</u><br/>
 * This implementation is based on {@link AbstractWizardContributor} that supports fields and their getters/setters.<br/>
 * This implementation also provides a default implementation of the {@link #accept(MObject)} method that deals with checking the scopes.<br/>
 * By inheriting from this abstract class you only have to provide implementations for the two abstract methods:
 * <ul>
 * <li>{@link #checkCanCreateIn(ModelElement)}</li>
 * <li>{@link #actionPerformed(ModelElement, String, String)}</li>
 * </ul>
 * </p>
 */
@objid ("691786dd-317c-43fb-85c4-67cc56fb5001")
public abstract class AbstractDiagramWizardContributor extends AbstractWizardContributor implements IDiagramWizardContributor {
}
