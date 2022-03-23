/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/

package org.modelio.metamodel.impl.uml.statik;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementData;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("001e961e-c4bf-1fd8-97fe-001ec947cd2a")
public class TemplateParameterSubstitutionData extends UmlModelElementData {
    @objid ("322ddfe6-2cc8-4bab-9dab-8929eca5b350")
    Object mValue = "";

    @objid ("67688c46-f584-4638-a807-44f93f082779")
    SmObjectImpl mOwner;

    @objid ("5898002e-7874-4e14-b7d9-453f482b651a")
    SmObjectImpl mActual;

    @objid ("290f269d-cd44-43c8-bcda-cfbdd112ced9")
    SmObjectImpl mFormalParameter;

    @objid ("43cc9cd3-5a1f-448d-a7ef-282e49093d40")
    public  TemplateParameterSubstitutionData(TemplateParameterSubstitutionSmClass smClass) {
        super(smClass);
    }

}
