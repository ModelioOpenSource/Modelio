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
package org.modelio.api.module.parameter;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("4b47b6dd-8f94-11dd-bbe0-001ec947ccaf")
public interface IParameterGroupModel {
    @objid ("650bf4da-8e2c-11dd-8928-001ec947ccaf")
    List<IParameterModel> getParameters();

    @objid ("5c2ca09a-8f94-11dd-bbe0-001ec947ccaf")
    String getLabel();

    @objid ("ccfcfda2-8f9f-11dd-bbe0-001ec947ccaf")
    String getName();

    @objid ("96421bb1-d478-4bc2-b2dd-b8e13b8dca50")
    void addParameter(IParameterModel p);

    @objid ("b6f5f16e-c601-4905-8427-be2f3da6ff28")
    void removeParameter(IParameterModel p);
}

