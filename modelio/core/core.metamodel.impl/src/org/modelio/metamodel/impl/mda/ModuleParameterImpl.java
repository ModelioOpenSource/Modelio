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
     Metamodel: Infrastructure, version 2.1.03, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Dec 13, 2018
*/
package org.modelio.metamodel.impl.mda;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.mda.ModuleParameterData;
import org.modelio.metamodel.impl.uml.infrastructure.ModelElementImpl;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.mda.ModuleParameterType;
import org.modelio.metamodel.uml.infrastructure.properties.EnumeratedPropertyType;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("00650bb2-c4bf-1fd8-97fe-001ec947cd2a")
public class ModuleParameterImpl extends ModelElementImpl implements ModuleParameter {
    @objid ("3a9decb1-4beb-417b-899c-3d9b595bb14d")
    @Override
    public String getGroupName() {
        return (String) getAttVal(((ModuleParameterSmClass)getClassOf()).getGroupNameAtt());
    }

    @objid ("3d509149-97b7-44aa-9433-2e145d756ca1")
    @Override
    public void setGroupName(String value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getGroupNameAtt(), value);
    }

    @objid ("1d346f1b-4fe5-4a03-b4e6-5879792fc2b5")
    @Override
    public ModuleParameterType getType() {
        return (ModuleParameterType) getAttVal(((ModuleParameterSmClass)getClassOf()).getTypeAtt());
    }

    @objid ("09dafd20-1510-4646-8954-cc90a67abd05")
    @Override
    public void setType(ModuleParameterType value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getTypeAtt(), value);
    }

    @objid ("c74f0212-3295-422a-856e-bd87583baf2a")
    @Override
    public boolean isIsUserRead() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserReadAtt());
    }

    @objid ("c7583686-b532-4ada-9bef-504c59584dc1")
    @Override
    public void setIsUserRead(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserReadAtt(), value);
    }

    @objid ("93d09ddc-2823-47a9-a2ee-5bdcf5abb0c9")
    @Override
    public boolean isIsUserWrite() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserWriteAtt());
    }

    @objid ("5d9e16dd-7d8c-4cf6-a995-876a080dde6d")
    @Override
    public void setIsUserWrite(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserWriteAtt(), value);
    }

    @objid ("801bb7d3-5011-4cbe-93d7-e4f2bd675105")
    @Override
    public boolean isIsApiRead() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiReadAtt());
    }

    @objid ("4ef99f71-c5c2-43de-adc2-de38d8df0488")
    @Override
    public void setIsApiRead(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiReadAtt(), value);
    }

    @objid ("ffeac3d2-ec02-4741-98ed-576ca1e2fa06")
    @Override
    public boolean isIsApiWrite() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiWriteAtt());
    }

    @objid ("71a5800d-f864-454c-83a0-a9748312cbf5")
    @Override
    public void setIsApiWrite(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiWriteAtt(), value);
    }

    @objid ("524b9c38-2aea-440e-834e-11eeee655600")
    @Override
    public String getDefaultValue() {
        return (String) getAttVal(((ModuleParameterSmClass)getClassOf()).getDefaultValueAtt());
    }

    @objid ("165377dd-b7fb-4b4a-9503-9b4f96b6a277")
    @Override
    public void setDefaultValue(String value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getDefaultValueAtt(), value);
    }

    @objid ("dc4f363d-5b9e-4f64-8b6e-de8a0427dc5e")
    @Override
    public ModuleComponent getOwner() {
        Object obj = getDepVal(((ModuleParameterSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ModuleComponent)? (ModuleComponent)obj : null;
    }

    @objid ("4c3cde04-f4b2-41b1-934f-f40f4cac5c76")
    @Override
    public void setOwner(ModuleComponent value) {
        appendDepVal(((ModuleParameterSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("3dda73d1-8c88-465f-b0d3-603c932da14c")
    @Override
    public EnumeratedPropertyType getEnumType() {
        Object obj = getDepVal(((ModuleParameterSmClass)getClassOf()).getEnumTypeDep());
        return (obj instanceof EnumeratedPropertyType)? (EnumeratedPropertyType)obj : null;
    }

    @objid ("bc23e4c1-0607-46e1-aa70-f224a10b94d4")
    @Override
    public void setEnumType(EnumeratedPropertyType value) {
        appendDepVal(((ModuleParameterSmClass)getClassOf()).getEnumTypeDep(), (SmObjectImpl)value);
    }

    @objid ("49202d4f-7fd9-42dc-b3b2-95e9e7174133")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        // Owner
        obj = (SmObjectImpl)this.getDepVal(((ModuleParameterSmClass)getClassOf()).getOwnerDep());
        if (obj != null)
          return obj;
        return super.getCompositionOwner();
    }

    @objid ("91af75e8-f6cd-4ffe-9cb4-838f32d47bc1")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        // Owner
        dep = ((ModuleParameterSmClass)getClassOf()).getOwnerDep();
        obj = (SmObjectImpl)this.getDepVal(dep);
        if (obj != null) return new SmDepVal(dep, obj);
        
        return super.getCompositionRelation();
    }

    @objid ("0d311b9f-9d02-4334-9cef-c2bde65f9e4d")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitModuleParameter(this);
    }

}
