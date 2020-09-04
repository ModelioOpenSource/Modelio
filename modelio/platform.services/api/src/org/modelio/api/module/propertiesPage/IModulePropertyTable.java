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

package org.modelio.api.module.propertiesPage;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class is a data model for all module's property pages.<br>
 * According to the type of each property, a different editor is added in the property view by Modelio.
 */
@objid ("b66741cd-531f-11dd-bd3f-0014222a9f79")
public interface IModulePropertyTable {
    /**
     * Add an "integer" typed property in the model.
     * @param key the name of the property.
     * @param value the value of the property.
     */
    @objid ("b669a430-531f-11dd-bd3f-0014222a9f79")
    void addProperty(String key, int value);

    /**
     * Add a "string" typed property in the model.
     * @param key the name of the property.
     * @param value the value of the property.
     */
    @objid ("b670cb46-531f-11dd-bd3f-0014222a9f79")
    void addProperty(String key, String value);

    /**
     * Add a "boolean" typed property in the model.
     * @param key the name of the property.
     * @param value the value of the property.
     */
    @objid ("b670cb4f-531f-11dd-bd3f-0014222a9f79")
    void addProperty(String key, boolean value);

    /**
     * Add an "enumeration" typed property in the model.
     * @param key the name of the property.
     * @param value the value of the property.
     * @param enumValues the values of the property.
     */
    @objid ("b6732da6-531f-11dd-bd3f-0014222a9f79")
    void addProperty(String key, String value, String[] enumValues);

    /**
     * Add a read only property in the model.
     * @param key the name of the property.
     * @param value the value of the property.
     */
    @objid ("b6759004-531f-11dd-bd3f-0014222a9f79")
    void addConsultProperty(String key, String value);

    /**
     * Removes all properties from the model.
     */
    @objid ("b677f25f-531f-11dd-bd3f-0014222a9f79")
    void clearTable();

    /**
     * Add a "MObject" typed property in the model.
     * @param key the name of the property.
     * @param value the value of the property.
     * @param acceptedMetaclasses a list of metaclasses that can be set in this property.
     * @param mobjectFilter a filter for the accepted elements.
     * @since 3.7.1
     */
    @objid ("0b195d2a-1f03-4698-a056-a395e60d06ee")
    void addProperty(String key, MObject value, Collection<? extends MClass> acceptedMetaclasses, IMObjectFilter mobjectFilter);

}
