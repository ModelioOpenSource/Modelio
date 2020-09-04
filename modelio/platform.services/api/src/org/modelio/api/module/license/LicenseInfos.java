/* 
 * Copyright 2013-2018 Modeliosoft
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

@objid ("ebd13ff6-429d-4428-b00b-e9028a3dc185")
public class LicenseInfos implements ILicenseInfos {
    @objid ("b8cae212-2c3f-4e6c-ac3e-4e1a50dbce95")
    private Status status;

    @objid ("2a6712a9-7c83-4a37-9ac7-f349dd4246ac")
    private Date date;

    @objid ("cf130466-12ff-44e8-89d6-da7a33c187d1")
    private String type = "LMX";

    @objid ("2d5c6de2-203f-43e8-b0af-10fd95a7e91a")
    public LicenseInfos(Status status, Date date, String type) {
        this.status = status;
        this.date = date;
        this.type = type;
    }

    @objid ("2ea1fdc8-c689-412f-9550-68f1279a1e89")
    @Override
    public Status getStatus() {
        return this.status;
    }

    @objid ("76cf49fa-7a87-4911-8f55-cef1602e8ac9")
    @Override
    public Date getDate() {
        return this.date;
    }

    @objid ("13233289-c482-403e-9240-df753ed0af4b")
    @Override
    public String getType() {
        return this.type;
    }

    @objid ("ba37e9d7-3381-4236-b228-62e7c7cdcf35")
    @Override
    public String toString() {
        return this.status + " " + this.date;
    }

}
