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

@objid ("45bf3101-c471-418c-80ae-196e5c7e1c6b")
public interface IFormFieldData {
    @objid ("20ebd1c1-0541-4e7c-98c7-0ec41c5f2acd")
    String getName();

    @objid ("7f4aafef-c035-48b5-8c30-501eeaf04173")
    IFormFieldType getType();

    @objid ("d82884e3-4f22-4636-9767-5460307f0bea")
    Object getValue();

    @objid ("9863c546-aeb2-44fe-9f5a-a11d5c7b94f1")
    void setValue(Object value);

}
