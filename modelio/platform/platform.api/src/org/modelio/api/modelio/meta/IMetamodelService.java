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

package org.modelio.api.modelio.meta;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Expert for all Metamodel concerns.
 */
@objid ("cc2a2aed-723e-11e0-89ea-002564c97630")
public interface IMetamodelService {
    /**
     * Get the Xxxx interface java class corresponding to the given metaclass name.
     * 
     * @param metaclassName a metaclass name.
     * @return The I class or <code>null</code> if the requested metaclass cannot be found..
     * @deprecated Since Modelio 3.4 use {@link #getMetamodel()}.{@link MMetamodel#getMClass(String)}
     */
    @objid ("cc441b38-723e-11e0-89ea-002564c97630")
    @Deprecated
    Class<? extends MObject> getMetaclass(final String metaclassName);

    /**
     * Returns the textual name of a metaclass.<br>
     * <em>Note: The returned name is <u>NOT</u> i18n'd.</em>
     * @see MMetamodel#getMClass(Class)
     * 
     * @param metaclass the metaclass whose name is sought, or <code>null</code> if the given class is not a metaclass.
     * @return the textual name of the metaclass.
     */
    @objid ("cc45a1d7-723e-11e0-89ea-002564c97630")
    String getMetaclassName(final Class<? extends MObject> metaclass);

    /**
     * Get the metaclasses that inherit from the given metaclass.
     * <p>
     * The given metaclass will in the result list.
     * 
     * @param metaclass The parent metaclass of the wanted metaclasses.
     * @return A list of metaclasses that inherit from the given metaclass.
     */
    @objid ("cc45a1dd-723e-11e0-89ea-002564c97630")
    List<Class<? extends MObject>> getInheritingMetaclasses(final Class<? extends MObject> metaclass);

    /**
     * Get the current project metamodel.
     * 
     * @return the metamodel.
     * @since 3.4
     */
    @objid ("cc1429fd-6316-4a63-bd1f-bde1920872b4")
    MMetamodel getMetamodel();

    /**
     * @return the I18n services for metamodel elements
     * @since 4.0
     */
    @objid ("a53406cd-aa12-46a8-89aa-f09c332b497d")
    IMetamodelI18nSupport getI18nSupport();

}
