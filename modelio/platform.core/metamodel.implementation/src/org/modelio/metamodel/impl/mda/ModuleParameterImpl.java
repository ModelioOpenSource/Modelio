/* 
 * Copyright 2013-2018 Modeliosoft
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
     Metamodel: Infrastructure, version 2.1.02, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Apr 17, 2018
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
    @objid ("3c199003-3d58-43c5-b435-4e28660f98ae")
    @Override
    public String getGroupName() {
        return (String) getAttVal(((ModuleParameterSmClass)getClassOf()).getGroupNameAtt());
    }

    @objid ("fbaa8503-1249-4429-9fce-e6616486d8dc")
    @Override
    public void setGroupName(String value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getGroupNameAtt(), value);
    }

    @objid ("341a3f01-b21f-4c7f-be66-e627713f8dba")
    @Override
    public ModuleParameterType getType() {
        return (ModuleParameterType) getAttVal(((ModuleParameterSmClass)getClassOf()).getTypeAtt());
    }

    @objid ("ff9bf269-2313-4396-919a-0e2a30ea0382")
    @Override
    public void setType(ModuleParameterType value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getTypeAtt(), value);
    }

    @objid ("580b2471-f389-4f5c-a180-ea7635f19455")
    @Override
    public boolean isIsUserRead() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserReadAtt());
    }

    @objid ("73e64527-59ff-43ca-9c79-3cc63fa8350a")
    @Override
    public void setIsUserRead(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserReadAtt(), value);
    }

    @objid ("6c4ae619-5769-4361-886e-30b44eca9232")
    @Override
    public boolean isIsUserWrite() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserWriteAtt());
    }

    @objid ("da0bb05f-e929-42ff-80e3-be2a9b983080")
    @Override
    public void setIsUserWrite(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserWriteAtt(), value);
    }

    @objid ("464e9871-62fa-4a06-9f37-6e5ea5146b11")
    @Override
    public boolean isIsApiRead() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiReadAtt());
    }

    @objid ("fbe44acd-9aa0-483c-9e78-80ee104c1c62")
    @Override
    public void setIsApiRead(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiReadAtt(), value);
    }

    @objid ("bd38e740-41dd-429f-a88d-dae15c4f12e0")
    @Override
    public boolean isIsApiWrite() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiWriteAtt());
    }

    @objid ("dab17a05-c467-4701-9ab8-f67c8732dfe7")
    @Override
    public void setIsApiWrite(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiWriteAtt(), value);
    }

    @objid ("732f06fc-b4d0-4616-a1ce-2f6b268f0c80")
    @Override
    public String getDefaultValue() {
        return (String) getAttVal(((ModuleParameterSmClass)getClassOf()).getDefaultValueAtt());
    }

    @objid ("ce191c70-d3a5-457f-ab11-019cc743eb54")
    @Override
    public void setDefaultValue(String value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getDefaultValueAtt(), value);
    }

    @objid ("6d0bfd79-01a4-400c-b168-ef7e84bfe6c6")
    @Override
    public ModuleComponent getOwner() {
        Object obj = getDepVal(((ModuleParameterSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ModuleComponent)? (ModuleComponent)obj : null;
    }

    @objid ("62f68025-84ad-493f-a4bb-07d9e127632a")
    @Override
    public void setOwner(ModuleComponent value) {
        appendDepVal(((ModuleParameterSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("834a378c-a9b1-4107-8395-3d929992d117")
    @Override
    public EnumeratedPropertyType getEnumType() {
        Object obj = getDepVal(((ModuleParameterSmClass)getClassOf()).getEnumTypeDep());
        return (obj instanceof EnumeratedPropertyType)? (EnumeratedPropertyType)obj : null;
    }

    @objid ("19233495-122a-446d-8554-e3ec717f081f")
    @Override
    public void setEnumType(EnumeratedPropertyType value) {
        appendDepVal(((ModuleParameterSmClass)getClassOf()).getEnumTypeDep(), (SmObjectImpl)value);
    }

    @objid ("611848f6-ce30-4f6d-9d80-69422e0d1ecb")
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

    @objid ("6956d52d-736d-445b-a84f-e5630549669a")
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

    @objid ("b0578036-2c66-4fd0-ab6a-5acfe4c9986a")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitModuleParameter(this);
    }

}
