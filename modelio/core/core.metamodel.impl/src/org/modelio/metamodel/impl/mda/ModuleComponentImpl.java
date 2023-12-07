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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.AbstractProjectImpl;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mda.ModuleParameter;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.metamodel.visitors.IInfrastructureVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("43f4fe7e-e0f5-4fc1-a14f-737f4dfc2ee6")
public class ModuleComponentImpl extends AbstractProjectImpl implements ModuleComponent {
    @objid ("3db9e0ce-87d3-4883-bb04-2dcf2b382be1")
    @Override
    public int getLicenseKey() {
        return (Integer) getAttVal(((ModuleComponentSmClass)getClassOf()).getLicenseKeyAtt());
    }

    @objid ("17b3ca1b-1ded-4aed-8821-a2bd03ad09be")
    @Override
    public void setLicenseKey(int value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getLicenseKeyAtt(), value);
    }

    @objid ("d5781df3-3f88-4c8a-be5b-8562e7e4a74c")
    @Override
    public int getMajVersion() {
        return (Integer) getAttVal(((ModuleComponentSmClass)getClassOf()).getMajVersionAtt());
    }

    @objid ("dddceaa2-9faa-42a0-b8b7-19d5c7c30c43")
    @Override
    public void setMajVersion(int value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMajVersionAtt(), value);
    }

    @objid ("9b6d01f5-47de-4e5a-983e-34aa7ab4eec3")
    @Override
    public int getMinVersion() {
        return (Integer) getAttVal(((ModuleComponentSmClass)getClassOf()).getMinVersionAtt());
    }

    @objid ("d0a31f68-8287-4551-ae29-71c6a5447607")
    @Override
    public void setMinVersion(int value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMinVersionAtt(), value);
    }

    @objid ("f4b155c4-9bc5-4a34-8292-c49c0ca2d09b")
    @Override
    public String getMinMinVersion() {
        return (String) getAttVal(((ModuleComponentSmClass)getClassOf()).getMinMinVersionAtt());
    }

    @objid ("53580057-34e3-4caa-88c0-9a3cdd9b54f4")
    @Override
    public void setMinMinVersion(String value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMinMinVersionAtt(), value);
    }

    @objid ("e7389d45-76b2-4b8e-91c3-fa2c1fa529c7")
    @Override
    public String getMinBinVersionCompatibility() {
        return (String) getAttVal(((ModuleComponentSmClass)getClassOf()).getMinBinVersionCompatibilityAtt());
    }

    @objid ("ae01ac74-6fbc-48cf-a56c-04475e80e947")
    @Override
    public void setMinBinVersionCompatibility(String value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMinBinVersionCompatibilityAtt(), value);
    }

    @objid ("bfa5a6c6-c133-46a0-af5e-a4e877564d3d")
    @Override
    public String getJavaClassName() {
        return (String) getAttVal(((ModuleComponentSmClass)getClassOf()).getJavaClassNameAtt());
    }

    @objid ("7145b504-1d12-4076-a9c3-997464da4e53")
    @Override
    public void setJavaClassName(String value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getJavaClassNameAtt(), value);
    }

    @objid ("961a6d16-8307-4cd9-b7f1-c688b2ece7ea")
    @Override
    public EList<PropertyType> getDefinedPropertyType() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getDefinedPropertyTypeDep());
    }

    @objid ("83e6d40b-fec8-4c58-93a2-e2572a612db7")
    @Override
    public <T extends PropertyType> List<T> getDefinedPropertyType(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final PropertyType element : getDefinedPropertyType()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("7ae50cdd-2871-4271-a5b2-43b13d4de172")
    @Override
    public EList<Profile> getOwnedProfile() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getOwnedProfileDep());
    }

    @objid ("3f07fb16-d56d-4537-94ac-6d9e714cf7bc")
    @Override
    public <T extends Profile> List<T> getOwnedProfile(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final Profile element : getOwnedProfile()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("74859676-733f-46e6-bae7-b9f209a43719")
    @Override
    public EList<ModuleParameter> getModuleParameter() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getModuleParameterDep());
    }

    @objid ("ace8f723-0e9c-4c3d-8473-dc655e6a730c")
    @Override
    public <T extends ModuleParameter> List<T> getModuleParameter(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ModuleParameter element : getModuleParameter()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("bdef97a3-0e1e-4566-975f-3205259a50c5")
    @Override
    public EList<ModuleComponent> getDependsOn() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getDependsOnDep());
    }

    @objid ("f8bf7835-e0b6-4648-8b90-628654979dc2")
    @Override
    public <T extends ModuleComponent> List<T> getDependsOn(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ModuleComponent element : getDependsOn()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("6fb0be5e-80a5-4981-b04f-d4caf9594e4a")
    @Override
    public EList<ModuleComponent> getImpacted() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getImpactedDep());
    }

    @objid ("2330c678-d8f5-4896-9eda-13c28cfe44dd")
    @Override
    public <T extends ModuleComponent> List<T> getImpacted(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final ModuleComponent element : getImpacted()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
        
    }

    @objid ("a54917f5-30f8-4e87-b138-5d355c38b40b")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("45e9a7b4-62e0-4220-a4b8-400a39c4cc07")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("fcee1a6d-3b52-4cd4-a057-c09bcbb76c07")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitModuleComponent(this);
    }

}
