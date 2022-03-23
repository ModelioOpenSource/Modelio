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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;

/**
 * Define the I18n services for metamodel elements.
 * <p>
 * Warning: metamodel elements are not always translated, in which case their name is directly returned.
 * </p>
 * @since 4.0
 */
@objid ("439e8f15-8272-470c-ad62-345a13c4021d")
public interface IMetamodelI18nSupport {
    /**
     * Get the I18n'ed label for a MClass.
     * @param mClass the metaclass to get the label for.
     * @return the metaclass' label.
     */
    @objid ("1eb21c23-64b4-4f71-ba94-ae1cbf895bc3")
    String getLabel(MClass mClass);

    /**
     * Get the I18n'ed label for a MClass.
     * @param mDep the meta-dependency to get the label for.
     * @return the meta-dependency' label.
     */
    @objid ("ce52f3cd-540c-4ce9-bc27-a34e2e23b0fb")
    String getLabel(MDependency mDep);

    /**
     * Get the I18n'ed label for a MClass.
     * @param mAtt the meta-attribute to get the label for.
     * @return the meta-attribute's label.
     */
    @objid ("885ac799-098e-42ad-bf34-2ab6e0697e96")
    String getLabel(MAttribute mAtt);

}
