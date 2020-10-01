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

package org.modelio.api.modelio.model.scope;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IElementFilter;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a028491c-f28f-4056-b6ec-abb55ef98008")
public class ElementScopeFilter implements IElementFilter {
    @objid ("a3bf973e-71c0-4fb9-98d1-dbccf61e7ba5")
     List<ElementScope> scopes;

    @objid ("611d977b-9c72-4e02-9aff-c5e9e350ea7d")
    public ElementScopeFilter(ElementScope scope) {
        this.scopes = new ArrayList();
        this.scopes.add(scope);
    }

    @objid ("472a04c8-f65a-41b0-8eaa-b08a64b4148c")
    public ElementScopeFilter(List<ElementScope> scopes) {
        scopes = new ArrayList<>(scopes);
    }

    @objid ("8524c65c-c3cd-4460-8fa3-9e8de3ad4a20")
    public ElementScopeFilter() {
        this.scopes = new ArrayList();
    }

    @objid ("a6b0b2a0-309d-4581-9247-6b73cb152885")
    @Override
    public boolean accept(MObject element) {
        for (ElementScope scope : this.scopes)
            if (scope.isMatching(element))
                    return true;
        return false;
    }

    @objid ("8976d35f-b86a-42c4-b572-96319433d1ff")
    public ElementScopeFilter addScope(ElementScope scope) {
        this.scopes.add(scope);
        return this;
    }

}
