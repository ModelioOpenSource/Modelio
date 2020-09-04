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
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.mda.ModuleComponentData;
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
    @objid ("53b206a9-f8ca-4b9b-874d-1a228e6a0e09")
    @Override
    public int getLicenseKey() {
        return (Integer) getAttVal(((ModuleComponentSmClass)getClassOf()).getLicenseKeyAtt());
    }

    @objid ("74fda74d-44dc-4efa-8e77-f93882373d58")
    @Override
    public void setLicenseKey(int value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getLicenseKeyAtt(), value);
    }

    @objid ("edd6d15a-b5c7-401c-8433-619247d27479")
    @Override
    public int getMajVersion() {
        return (Integer) getAttVal(((ModuleComponentSmClass)getClassOf()).getMajVersionAtt());
    }

    @objid ("6b2fa701-2a47-4141-9a40-3935a7d5e800")
    @Override
    public void setMajVersion(int value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMajVersionAtt(), value);
    }

    @objid ("d6209a97-9e13-4260-8840-bd0f6629414d")
    @Override
    public int getMinVersion() {
        return (Integer) getAttVal(((ModuleComponentSmClass)getClassOf()).getMinVersionAtt());
    }

    @objid ("ff835bca-88b6-4af6-aadf-3ea4de3189d7")
    @Override
    public void setMinVersion(int value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMinVersionAtt(), value);
    }

    @objid ("d0c6680c-b5ca-4403-a89c-8485792938c6")
    @Override
    public String getMinMinVersion() {
        return (String) getAttVal(((ModuleComponentSmClass)getClassOf()).getMinMinVersionAtt());
    }

    @objid ("08d18c3b-7122-42b1-a470-d74fb0c7d281")
    @Override
    public void setMinMinVersion(String value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMinMinVersionAtt(), value);
    }

    @objid ("9b790d24-51ca-4e8d-ba84-eac614cd90d0")
    @Override
    public String getMinBinVersionCompatibility() {
        return (String) getAttVal(((ModuleComponentSmClass)getClassOf()).getMinBinVersionCompatibilityAtt());
    }

    @objid ("e7b1ab7a-8fed-4fe4-a9a5-53181b078cd6")
    @Override
    public void setMinBinVersionCompatibility(String value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMinBinVersionCompatibilityAtt(), value);
    }

    @objid ("c7cea354-daf1-43a6-92c3-921a0a0895e2")
    @Override
    public String getJavaClassName() {
        return (String) getAttVal(((ModuleComponentSmClass)getClassOf()).getJavaClassNameAtt());
    }

    @objid ("c878bc05-0afd-426e-86f8-9c5ba3ebb0fe")
    @Override
    public void setJavaClassName(String value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getJavaClassNameAtt(), value);
    }

    @objid ("99b4fefa-a383-4cc7-8329-be264468578b")
    @Override
    public EList<PropertyType> getDefinedPropertyType() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getDefinedPropertyTypeDep());
    }

    @objid ("d68aa650-9235-4c4c-987e-3e4ed68501bc")
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

    @objid ("f475d657-38f3-46b3-933c-f3a6bc23ab78")
    @Override
    public EList<Profile> getOwnedProfile() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getOwnedProfileDep());
    }

    @objid ("3763402d-ca90-41a4-84b4-112a3817108e")
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

    @objid ("74a28eee-f97c-4f61-ab6a-37bdc356f503")
    @Override
    public EList<ModuleParameter> getModuleParameter() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getModuleParameterDep());
    }

    @objid ("612a6017-378c-4ce2-948c-0375b99019c1")
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

    @objid ("7da34eea-36ac-44f1-a510-f27eead36f8e")
    @Override
    public EList<ModuleComponent> getDependsOn() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getDependsOnDep());
    }

    @objid ("18d25f72-46ad-407d-8217-ddf680234689")
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

    @objid ("80d10197-3faf-4fa2-9a3f-9a81917fd915")
    @Override
    public EList<ModuleComponent> getImpacted() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getImpactedDep());
    }

    @objid ("ae1b240b-f46b-46d8-a961-2f04713198e8")
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

    @objid ("17199ea2-d1a8-4b0b-bd33-a6129e74dcdd")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("5134e2ec-0d55-4616-84f7-11ca654f52f3")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("1bfed02d-59c4-4e4f-9c52-bfb3d420410d")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitModuleComponent(this);
    }

}
