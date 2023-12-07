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

package org.modelio.metamodel.mda;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.uml.infrastructure.AbstractProject;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;

/**
 * ModuleComponent v0.0.9054
 * 
 * 
 * Module Modelio specific metaclass.
 * 
 * Represents a Modelio module.
 * It inherits from Component, its nearest looking like UML representation.
 * 
 * 
 */
@objid ("006567e2-c4bf-1fd8-97fe-001ec947cd2a")
public interface ModuleComponent extends AbstractProject {
    /**
     * The metaclass simple name.
     */
    @objid ("bde8abe9-9da3-4909-ac1d-6d1598c869fa")
    public static final String MNAME = "ModuleComponent";

    /**
     * The metaclass qualified name.
     */
    @objid ("a37edb2c-906b-4572-b138-9efb5bc6e038")
    public static final String MQNAME = "Infrastructure.ModuleComponent";

    /**
     * Getter for attribute 'ModuleComponent.LicenseKey'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("f303748a-bc8a-4817-96df-08563117c78c")
    int getLicenseKey();

    /**
     * Setter for attribute 'ModuleComponent.LicenseKey'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("28b18403-3ee5-4045-a0d2-1685262c5a23")
    void setLicenseKey(int value);

    /**
     * Getter for attribute 'ModuleComponent.MajVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("c35874b0-c76a-422d-bc98-3805e1ecff77")
    int getMajVersion();

    /**
     * Setter for attribute 'ModuleComponent.MajVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("2faeceba-c92f-4384-a95d-04729cf21f51")
    void setMajVersion(int value);

    /**
     * Getter for attribute 'ModuleComponent.MinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("dd696a3b-34c1-4db9-bf1d-fcdd2f04b5d5")
    int getMinVersion();

    /**
     * Setter for attribute 'ModuleComponent.MinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("9c8365f4-f577-4e7d-a579-cf4eb56cfc18")
    void setMinVersion(int value);

    /**
     * Getter for attribute 'ModuleComponent.MinMinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("889ebae4-19f2-431d-80d7-82bc2d3d59f6")
    String getMinMinVersion();

    /**
     * Setter for attribute 'ModuleComponent.MinMinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("5263990c-4092-44d5-a4e4-74d854af886b")
    void setMinMinVersion(String value);

    /**
     * Getter for attribute 'ModuleComponent.MinBinVersionCompatibility'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("1059e553-e016-4216-96b6-17a21eb6d92f")
    String getMinBinVersionCompatibility();

    /**
     * Setter for attribute 'ModuleComponent.MinBinVersionCompatibility'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("59936308-8f00-4b9b-aac4-31f3a5918b20")
    void setMinBinVersionCompatibility(String value);

    /**
     * Getter for attribute 'ModuleComponent.JavaClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("5a6afda9-af32-4801-af81-bf9ae8793db3")
    String getJavaClassName();

    /**
     * Setter for attribute 'ModuleComponent.JavaClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("b03ebc64-3bf6-4978-b055-647c378f5b6e")
    void setJavaClassName(String value);

    /**
     * Getter for relation 'ModuleComponent->DefinedPropertyType'
     * 
     * Metamodel description:
     * <i><p>Deprecated : add property types in a profile.</p>
     * </i>
     * 
     */
    @objid ("8faf624d-7d98-4916-8fec-fc07940fec90")
    EList<PropertyType> getDefinedPropertyType();

    /**
     * Filtered Getter for relation 'ModuleComponent->DefinedPropertyType'
     * 
     * Metamodel description:
     * <i><p>Deprecated : add property types in a profile.</p>
     * </i>
     * 
     */
    @objid ("9d4f96d3-ac90-40fe-9d6c-f29e578afde5")
    <T extends PropertyType> List<T> getDefinedPropertyType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->OwnedProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("9a0d70de-2825-4b41-b714-714b031463e5")
    EList<Profile> getOwnedProfile();

    /**
     * Filtered Getter for relation 'ModuleComponent->OwnedProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8a7a9001-9e6d-46c3-960a-0b1338f3571b")
    <T extends Profile> List<T> getOwnedProfile(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->ModuleParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("64808af8-3c0d-4924-ab82-d6304147135f")
    EList<ModuleParameter> getModuleParameter();

    /**
     * Filtered Getter for relation 'ModuleComponent->ModuleParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("44471179-b91d-49ad-8e6e-9c3d952c8c4f")
    <T extends ModuleParameter> List<T> getModuleParameter(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->DependsOn'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("120e3bf1-5630-405a-bfb4-16d9b9c1f3f2")
    EList<ModuleComponent> getDependsOn();

    /**
     * Filtered Getter for relation 'ModuleComponent->DependsOn'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("8b74007b-b1b3-491d-9527-1baaa4f8b345")
    <T extends ModuleComponent> List<T> getDependsOn(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->Impacted'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("6a576933-4664-4e3b-8911-8cce8ec33623")
    EList<ModuleComponent> getImpacted();

    /**
     * Filtered Getter for relation 'ModuleComponent->Impacted'
     * 
     * Metamodel description:
     * <i>null</i>
     * 
     */
    @objid ("ded6f9d5-bbc7-4808-ba8c-c36c8f494e18")
    <T extends ModuleComponent> List<T> getImpacted(java.lang.Class<T> filterClass);
}

