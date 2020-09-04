/* 
 * Copyright 2013-2019 Modeliosoft
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
    @objid ("263d2b9f-b294-43e1-a937-3e3466b52457")
    @Override
    public int getLicenseKey() {
        return (Integer) getAttVal(((ModuleComponentSmClass)getClassOf()).getLicenseKeyAtt());
    }

    @objid ("58f6d988-55d9-4f0a-abce-42ed25e96008")
    @Override
    public void setLicenseKey(int value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getLicenseKeyAtt(), value);
    }

    @objid ("5b4d07c1-bec4-4948-9639-9bce58fc4023")
    @Override
    public int getMajVersion() {
        return (Integer) getAttVal(((ModuleComponentSmClass)getClassOf()).getMajVersionAtt());
    }

    @objid ("57efda82-dfdd-48a3-aac2-78e23d6a5bde")
    @Override
    public void setMajVersion(int value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMajVersionAtt(), value);
    }

    @objid ("669a534f-3f95-4d80-820d-156cadedc97a")
    @Override
    public int getMinVersion() {
        return (Integer) getAttVal(((ModuleComponentSmClass)getClassOf()).getMinVersionAtt());
    }

    @objid ("42ca1c1b-f6c5-4e70-bb86-e7bc1878db48")
    @Override
    public void setMinVersion(int value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMinVersionAtt(), value);
    }

    @objid ("09a80a71-50e5-4902-8a2a-009c361f4425")
    @Override
    public String getMinMinVersion() {
        return (String) getAttVal(((ModuleComponentSmClass)getClassOf()).getMinMinVersionAtt());
    }

    @objid ("cb552e3d-837e-4e03-8082-16d168c4cb28")
    @Override
    public void setMinMinVersion(String value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMinMinVersionAtt(), value);
    }

    @objid ("6960d52a-4dd8-414f-bdbd-271a018daba5")
    @Override
    public String getMinBinVersionCompatibility() {
        return (String) getAttVal(((ModuleComponentSmClass)getClassOf()).getMinBinVersionCompatibilityAtt());
    }

    @objid ("97771980-8e74-419f-aaab-54ebecb6e7af")
    @Override
    public void setMinBinVersionCompatibility(String value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getMinBinVersionCompatibilityAtt(), value);
    }

    @objid ("d6669dac-8fef-445c-8fce-69acfc07e712")
    @Override
    public String getJavaClassName() {
        return (String) getAttVal(((ModuleComponentSmClass)getClassOf()).getJavaClassNameAtt());
    }

    @objid ("4a3778c3-e5dd-45f2-861b-cdfa89177188")
    @Override
    public void setJavaClassName(String value) {
        setAttVal(((ModuleComponentSmClass)getClassOf()).getJavaClassNameAtt(), value);
    }

    @objid ("183a87e9-1409-4e18-a840-3f99bfba6c6b")
    @Override
    public EList<PropertyType> getDefinedPropertyType() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getDefinedPropertyTypeDep());
    }

    @objid ("6bb51c1a-d473-4015-9061-161c4c80bd41")
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

    @objid ("757d3260-793b-4f1e-afeb-e0b4687130c7")
    @Override
    public EList<Profile> getOwnedProfile() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getOwnedProfileDep());
    }

    @objid ("b2adca13-7498-4235-8664-9f72bb403b04")
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

    @objid ("61a48e08-91a2-47c7-b468-d9cf69f728eb")
    @Override
    public EList<ModuleParameter> getModuleParameter() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getModuleParameterDep());
    }

    @objid ("ff586b89-d51b-4480-971f-564b4c35b433")
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

    @objid ("791022a2-87db-480e-8c12-b677aa91c537")
    @Override
    public EList<ModuleComponent> getDependsOn() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getDependsOnDep());
    }

    @objid ("ac18f535-5728-4e11-86ff-e51c15cf6a98")
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

    @objid ("c3c5489f-34de-4b5e-b1e8-93d689a04faf")
    @Override
    public EList<ModuleComponent> getImpacted() {
        return new SmList<>(this, ((ModuleComponentSmClass)getClassOf()).getImpactedDep());
    }

    @objid ("dac730a9-ab2a-4746-8b9f-0d5d5218fcfe")
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

    @objid ("5ec32d00-0e8b-4497-93b1-530491428dcb")
    @Override
    public SmObjectImpl getCompositionOwner() {
        // Generated implementation
        SmObjectImpl obj;
        return super.getCompositionOwner();
    }

    @objid ("e7f04aee-f9d0-4ed8-8e47-f3a6ae46bbb5")
    @Override
    public SmDepVal getCompositionRelation() {
        // Generated implementation
        SmObjectImpl obj;
        SmDependency dep;
        
        return super.getCompositionRelation();
    }

    @objid ("46592479-a2f0-4000-a2d0-4a25ad8d91b3")
    @Override
    public Object accept(IInfrastructureVisitor v) {
        return v.visitModuleComponent(this);
    }

}
