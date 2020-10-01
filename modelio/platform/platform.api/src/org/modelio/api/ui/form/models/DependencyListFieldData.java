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

package org.modelio.api.ui.form.models;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.plugin.Api;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("696b0218-f241-4790-a1aa-b5415768e315")
public class DependencyListFieldData implements IFormFieldData {
    @objid ("21750d33-b678-4c1d-a02d-7ba525e60830")
    private final ICandidateProvider candidateProvider;

    @objid ("2a28a609-820d-4812-a3d8-2c35e2d820c0")
    private final ModelElement element;

    @objid ("0f1a36a7-d2d4-42e1-a918-93517413b4d5")
    private final IModelingSession session;

    @objid ("11498e03-8220-4439-ae5f-ca18377fd148")
    private final Stereotype stereotype;

    @objid ("e9118787-5411-430c-9f9c-a5abd42150e3")
    public DependencyListFieldData(IModelingSession session, ModelElement e, Stereotype s, ICandidateProvider candidateProvider) {
        this.session = session;
        this.element = e;
        this.stereotype = s;
        this.candidateProvider = candidateProvider;
    }

    @objid ("ea13bf40-ee68-4358-987d-710507421075")
    @Override
    public String getName() {
        return this.stereotype.getName();
    }

    @objid ("69edd83d-5c5c-4050-b442-22fac1944df0")
    @Override
    public IFormFieldType getType() {
        return new IFormFieldType() {
        
                    @Override
                    public boolean isValidValue(String value) {
                        return false;
                    }
        
                    @Override
                    public String getName() {
                        return "Dependency stereotyped <<" + DependencyListFieldData.this.stereotype.getName() + ">>";
                    }
        
                    @Override
                    public Object[] getEnumeratedValues() {
                        return DependencyListFieldData.this.candidateProvider.getElements(DependencyListFieldData.this.element).toArray();
                    }
                };
    }

    @objid ("725613c3-64dd-4ccb-a71d-74e768f4f5d8")
    @Override
    public Object getValue() {
        List<MObject> result = new ArrayList<>();
        
        for (Dependency dep : this.element.getDependsOnDependency()) {
            if (dep.getExtension().contains(this.stereotype)) {
                ModelElement target = dep.getDependsOn();
                if (target != null) {
                    result.add(target);
                }
            }
        }
        return result;
    }

    @objid ("59b1e9dd-8a42-41da-be36-89edc871ebc6")
    @Override
    public void setValue(Object value) {
        try (ITransaction t = this.session.createTransaction("Modify owner dependencies")) {
            @SuppressWarnings ("unchecked")
            List<MObject> values = (List<MObject>) value;
        
            // Remove unwanted links
            for (Dependency dep : this.element.getDependsOnDependency().toArray(new Dependency[0])) {
                if (dep.getExtension().contains(this.stereotype)) {
                    ModelElement existingTarget = dep.getDependsOn();
                    if (existingTarget == null || !values.contains(existingTarget)) {
                        dep.delete();
                    }
                }
            }
        
            for (MObject newTarget : values) {
                boolean found = false;
        
                // Match existing links
                for (Dependency dep : this.element.getDependsOnDependency().toArray(new Dependency[0])) {
                    if (dep.getExtension().contains(this.stereotype)) {
                        ModelElement existingTarget = dep.getDependsOn();
                        if (existingTarget.equals(newTarget)) {
                            found = true;
                            break;
                        }
                    }
                }
        
                if (!found) {
                    // Create missing link
                    this.session.getModel().createDependency(this.element, (ModelElement) newTarget, this.stereotype);
                }
            }
            t.commit();
        } catch (final RuntimeException e) {
            Api.LOG.error(e);
        }
    }

}
