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
     Metamodel: Infrastructure, version 2.1.04, by Modeliosoft
     Generator version: 3.14.00
     Generated on: May 3, 2023
*/

package org.modelio.metamodel.impl.mda;

import java.util.ArrayList;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
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
    @objid ("269125e7-1c12-4c02-bc48-68b1ebb3e0d8")
    @Override
    public String getGroupName() {
        return (String) getAttVal(((ModuleParameterSmClass)getClassOf()).getGroupNameAtt());
    }

    @objid ("1b3a95a7-cfbf-4d69-afe1-f0b91863dd03")
    @Override
    public void setGroupName(String value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getGroupNameAtt(), value);
    }

    @objid ("d085223c-197b-4f80-885f-0ae8258b9717")
    @Override
    public ModuleParameterType getType() {
        return (ModuleParameterType) getAttVal(((ModuleParameterSmClass)getClassOf()).getTypeAtt());
    }

    @objid ("aac6b980-8c83-4c5d-8219-17a35cfe84f7")
    @Override
    public void setType(ModuleParameterType value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getTypeAtt(), value);
    }

    @objid ("a3c69370-591b-47eb-bf7a-c1c1193325ac")
    @Override
    public boolean isIsUserRead() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserReadAtt());
    }

    @objid ("0ba5aacf-5469-4d88-9166-26f3546f739c")
    @Override
    public void setIsUserRead(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserReadAtt(), value);
    }

    @objid ("0e88190b-7609-475a-84d9-f6db6a7653a4")
    @Override
    public boolean isIsUserWrite() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserWriteAtt());
    }

    @objid ("af2b287b-24d0-4a2d-87dc-0713c67df4dd")
    @Override
    public void setIsUserWrite(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsUserWriteAtt(), value);
    }

    @objid ("378cd107-143a-4021-be17-2c128d70e893")
    @Override
    public boolean isIsApiRead() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiReadAtt());
    }

    @objid ("56990302-005e-4173-be90-883e1374dcb6")
    @Override
    public void setIsApiRead(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiReadAtt(), value);
    }

    @objid ("f7df0cf5-8770-4247-9c81-257e42075b2b")
    @Override
    public boolean isIsApiWrite() {
        return (Boolean) getAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiWriteAtt());
    }

    @objid ("3555ec37-cba4-41e7-bda9-d04742216c5f")
    @Override
    public void setIsApiWrite(boolean value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getIsApiWriteAtt(), value);
    }

    @objid ("8e471cba-a8e4-4b41-8767-ecd7bf79c667")
    @Override
    public String getDefaultValue() {
        return (String) getAttVal(((ModuleParameterSmClass)getClassOf()).getDefaultValueAtt());
    }

    @objid ("167aa18d-73cb-444a-8cc3-f763a9f3dd8a")
    @Override
    public void setDefaultValue(String value) {
        setAttVal(((ModuleParameterSmClass)getClassOf()).getDefaultValueAtt(), value);
    }

    @objid ("7a5698f5-3654-4c91-9c86-ca5624a07485")
    @Override
    public ModuleComponent getOwner() {
        Object obj = getDepVal(((ModuleParameterSmClass)getClassOf()).getOwnerDep());
        return (obj instanceof ModuleComponent)? (ModuleComponent)obj : null;
    }

    @objid ("5cd024e0-2aba-4543-ba63-aa063d73df24")
    @Override
    public void setOwner(ModuleComponent value) {
        appendDepVal(((ModuleParameterSmClass)getClassOf()).getOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("6e8517f4-1f07-4291-a357-cd1f58114f99")
    @Override
    public EnumeratedPropertyType getEnumType() {
        Object obj = getDepVal(((ModuleParameterSmClass)getClassOf()).getEnumTypeDep());
        return (obj instanceof EnumeratedPropertyType)? (EnumeratedPropertyType)obj : null;
    }

    @objid ("c8fe4304-cf03-49a4-9d3d-46d27af92cd0")
    @Override
    public void setEnumType(EnumeratedPropertyType value) {
        appendDepVal(((ModuleParameterSmClass)getClassOf()).getEnumTypeDep(), (SmObjectImpl)value);
    }

    @objid ("9ccb02d6-398c-41a1-a6f7-d5c27ab1ac68")
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

    @objid ("09e8d528-d7b6-41e6-af87-18774dc683ea")
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

    @objid ("ecfe7ee3-58b7-4301-93d0-0a3d93318e93")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitModuleParameter(this);
    }

}
