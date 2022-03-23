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
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;

/**
 * {@link IFormFieldData} for {@link TaggedValue} with one {@link TagParameter}.
 * @author cma
 * @since 3.7.1
 */
@objid ("5d1c25c5-63f6-42b2-9b51-032695c16ff4")
public class StringTagFieldData extends AbstractTagFieldData {
    @objid ("1502a944-79ca-49f8-b2c4-0523ba93cc1b")
    public  StringTagFieldData(IModelingSession session, ModelElement me, String moduleName, String tagTypeName) {
        super(session, me, moduleName, tagTypeName);
    }

    @objid ("49119367-31e1-4d3d-9295-9001d881600a")
    public  StringTagFieldData(IModelingSession session, ModelElement me, TagType tagType) {
        super(session, me, tagType);
    }

    @objid ("90fb11ce-bd72-4de9-b5df-00b4db3efbbe")
    @Override
    public Object getValue() {
        final String s = this.editedEl.getTagValue(this.moduleName, this.tagTypeName);
        return s != null ? s : "";
    }

    @objid ("f8a090ff-0bed-43a7-985b-4bbfa51bbcf9")
    @Override
    public void setValue(Object value) {
        try (ITransaction t = getModelingSession().createTransaction(String.format("Set '%s' tag on %s", this.tagTypeName, this.editedEl))) {
            this.editedEl.putTagValue(this.moduleName, this.tagTypeName, value == null ? null : value.toString());
            t.commit();
        } catch (final ExtensionNotFoundException e) {
            Api.LOG.error(e);
        }
        
    }

}
