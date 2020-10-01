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

package org.modelio.metamodel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.meta.IMetamodelService;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * MClass manipulation class, kept for compatibility issues.
 * @deprecated use {@link IMetamodelService} instead.
 */
@objid ("bb96dba2-1ced-478d-8331-c7292afa1e1a")
@Deprecated
public class Metamodel {
    /**
     * @deprecated use {@link MMetamodel#getMClass(String)}
     */
    @objid ("793687c5-cc3b-4241-8bbd-05fb210832a4")
    @Deprecated
    public static MClass getMClass(String name) {
        return Modelio.getInstance().getMetamodelService().getMetamodel().getMClass(name);
    }

    /**
     * @deprecated use {@link MMetamodel#getMClass(String)}
     */
    @objid ("572cf1bb-1411-456b-a320-caf656c00858")
    @Deprecated
    public static MClass getMClass(Class<? extends MObject> javaInterface) {
        return Modelio.getInstance().getMetamodelService().getMetamodel().getMClass(javaInterface);
    }

    /**
     * @deprecated use {@link MClass#getJavaInterface(String)}
     */
    @objid ("7319b895-edd6-4b17-bb47-d2c1150c32e4")
    @Deprecated
    public static Class<? extends MObject> getJavaInterface(MClass mClass) {
        return mClass.getJavaInterface();
    }

}
