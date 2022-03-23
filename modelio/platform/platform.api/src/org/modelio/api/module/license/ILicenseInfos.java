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
package org.modelio.api.module.license;

import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("75422edf-4fc9-4097-b1d9-9290e4c7428a")
public interface ILicenseInfos {
    @objid ("868fbd4c-0c11-4b3d-8cbd-9db3d5a06186")
    String getType();

    @objid ("747c3749-1990-4be6-8bcf-4ac3040c6488")
    Date getDate();

    @objid ("2d5f536c-691e-4f8c-b533-ba9ab366b853")
    Status getStatus();

    @objid ("889058ab-7290-4b65-8f1a-f0d837bc32c4")
    public enum Status {
        @objid ("fb5c8ee9-161b-430a-a726-b6437d73c667")
        FREE,
        @objid ("0f0339ed-7604-4042-b85d-643fc6fd1985")
        VALID,
        @objid ("7f4290fd-5585-4e51-b32c-00c28826ae36")
        TRIAL_VALID,
        @objid ("dd15fa41-2c8a-48a5-8d2a-59b6968d4f11")
        EXPIRED,
        @objid ("66d9c1a6-b624-42a1-aea5-95d92f8b21c3")
        TRIAL_EXPIRED,
        @objid ("8575e4f6-0a68-48a9-a191-ac5058b72909")
        UNDEFINED,
        @objid ("751ee1f9-c136-406c-ac21-440a000af9cf")
        NO_LICENSE;

    }

}
