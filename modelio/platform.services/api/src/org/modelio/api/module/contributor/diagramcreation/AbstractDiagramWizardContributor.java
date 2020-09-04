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
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.AbstractWizardContributor;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Convenience class that provides an abstract implementation of {@link IDiagramWizardContributor} that only provides useful fields and their getters/setters.
 */
@objid ("691786dd-317c-43fb-85c4-67cc56fb5001")
public abstract class AbstractDiagramWizardContributor extends AbstractWizardContributor implements IDiagramWizardContributor {
    @objid ("830f8901-bbf0-472f-a8d8-783d4361c5a5")
    @Override
    public IPanelProvider getWizardPanel() {
        return null;
    }

    @objid ("80bcc6e5-5a87-4a23-959b-c01222fc4a54")
    @Override
    public boolean accept(MObject owner) {
        // Cannot accept null
        if (owner == null) {
            return false;
        }
        
        if (owner instanceof ModelElement) {
            ModelElement me = (ModelElement) owner;
            if (checkCanCreateIn(me)) {
                for (ElementScope scope : getScopes()) {
                    if (scope.isMatching(me)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Called by {@link #accept(MObject)} to check whether the given object has sufficient rights to create the wizard new element.
     * 
     * @param owner the new element owner.
     * @return true if the operation is allowed else false.
     */
    @objid ("1ae3d44c-25fe-47a5-ad89-87b25bbffdb7")
    protected abstract boolean checkCanCreateIn(ModelElement owner);

}
