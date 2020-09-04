/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.api.module.parameter.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.configuration.IModuleUserConfiguration;

@objid ("1b3b8899-8fa2-11dd-bbe0-001ec947ccaf")
public class PasswordParameterModel extends ParameterModel {
    @objid ("e41a9cde-feb1-11dd-8b31-0014222a9f79")
    public PasswordParameterModel(IModuleUserConfiguration conf, String name, String label, String description, String defaultValue) {
        super(conf, name, label, description, defaultValue);
    }

    @objid ("559297b6-8bee-4992-a0ed-6af2e36899e7")
    @Override
    public String getStringValue() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0 ; i < getPasswordValue().length() ; i++) {
            ret.append('\u25CF');
        }
        return ret.toString();
    }

    @objid ("f8a19fab-9c3b-4761-aad9-209dcca503b3")
    public String getPasswordValue() {
        return super.getStringValue();
    }

}
