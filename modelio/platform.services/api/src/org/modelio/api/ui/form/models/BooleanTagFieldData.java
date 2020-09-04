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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.plugin.Api;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.TagType;

/**
 * {@link IFormFieldData} for TaggedValue with one TagParameter.
 * @author cma
 * @since 3.7.1
 */
@objid ("04dbd9a6-b14d-487e-aa3d-22f22aefe033")
public class BooleanTagFieldData extends AbstractTagFieldData {
    @objid ("9232ec59-95b1-498e-beed-36b6c8e53387")
    public BooleanTagFieldData(IModelingSession session, ModelElement me, String moduleName, String tagTypeName) {
        super(session, me, moduleName, tagTypeName);
    }

    @objid ("24cc65c6-0e14-4751-9707-7f6766b1f9bd")
    public BooleanTagFieldData(IModelingSession session, ModelElement me, TagType tagType) {
        super(session, me, tagType);
    }

    @objid ("03d1218b-cc25-4283-bca2-8261f3092913")
    @Override
    public Object getValue() {
        return Boolean.valueOf(getTag() != null);
    }

    @objid ("082183e1-487f-40c9-8fc5-056ae013d284")
    @Override
    public void setValue(Object value) {
        if (getValue() == value) {
            return;
        }
        
        try (ITransaction t = getModelingSession().createTransaction(String.format("Set '%s' tag on %s", this.tagTypeName, this.editedEl))) {
            this.editedEl.putTagValue(this.moduleName, this.tagTypeName, value == null ? null : value.toString());
            t.commit();
        } catch (final ExtensionNotFoundException e) {
            Api.LOG.error(e);
        }
    }

}
