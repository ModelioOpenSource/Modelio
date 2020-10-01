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

@objid ("5cc2a72d-3372-4725-8550-2cbe92d81faf")
public interface IFormFieldType {
    @objid ("c5d6f3e2-d89d-4140-821f-e5e1eb36cd2d")
    Object[] getEnumeratedValues();

    @objid ("af50615f-d97b-4ad3-b796-f4d645ea56fa")
    String getName();

    @objid ("cd2c4a03-de10-4ba6-b0e6-7eecf1261064")
    boolean isValidValue(String value);

}
