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
package org.modelio.metamodel.mda;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.mda.ModuleParameter;
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
 */
@objid ("006567e2-c4bf-1fd8-97fe-001ec947cd2a")
public interface ModuleComponent extends AbstractProject {
    /**
     * The metaclass simple name.
     */
    @objid ("e3559952-8e44-456f-a74e-69ae91c479f3")
    public static final String MNAME = "ModuleComponent";

    /**
     * The metaclass qualified name.
     */
    @objid ("99530df8-9d9c-4dee-b888-9a0426bef187")
    public static final String MQNAME = "Infrastructure.ModuleComponent";

    /**
     * Getter for attribute 'ModuleComponent.LicenseKey'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c8926609-3194-4472-b86f-e27b32206f70")
    int getLicenseKey();

    /**
     * Setter for attribute 'ModuleComponent.LicenseKey'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9e36d5f2-c776-47c2-a5f7-f8a49a14648b")
    void setLicenseKey(int value);

    /**
     * Getter for attribute 'ModuleComponent.MajVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("51841eeb-f8c2-43c2-b557-d258b7005577")
    int getMajVersion();

    /**
     * Setter for attribute 'ModuleComponent.MajVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4350a9d9-8864-4049-93a5-2c067287a6c7")
    void setMajVersion(int value);

    /**
     * Getter for attribute 'ModuleComponent.MinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e503fc4a-cbc7-4b80-8a74-161e9ab6549e")
    int getMinVersion();

    /**
     * Setter for attribute 'ModuleComponent.MinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e629d311-c093-47c9-8e71-6a20c24947f9")
    void setMinVersion(int value);

    /**
     * Getter for attribute 'ModuleComponent.MinMinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e554d5e4-96db-4dcd-b75f-a35dece945c2")
    String getMinMinVersion();

    /**
     * Setter for attribute 'ModuleComponent.MinMinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("02b8a086-44b2-437e-9204-92339a161273")
    void setMinMinVersion(String value);

    /**
     * Getter for attribute 'ModuleComponent.MinBinVersionCompatibility'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3a1ba869-d107-4152-a8f0-873cd001ca54")
    String getMinBinVersionCompatibility();

    /**
     * Setter for attribute 'ModuleComponent.MinBinVersionCompatibility'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("9a908a3b-f2cb-4151-b715-a809e21bd8dd")
    void setMinBinVersionCompatibility(String value);

    /**
     * Getter for attribute 'ModuleComponent.JavaClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3d5c1961-ea0d-4e23-9aaa-ebfcea7d62ec")
    String getJavaClassName();

    /**
     * Setter for attribute 'ModuleComponent.JavaClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f8eb8a94-0274-495b-90b6-84a9385bf487")
    void setJavaClassName(String value);

    /**
     * Getter for relation 'ModuleComponent->DefinedPropertyType'
     * 
     * Metamodel description:
     * <i><p>Deprecated : add property types in a profile.</p>
     * </i>
     */
    @objid ("2557c685-5bff-47df-9ef4-f88bf506f4a8")
    EList<PropertyType> getDefinedPropertyType();

    /**
     * Filtered Getter for relation 'ModuleComponent->DefinedPropertyType'
     * 
     * Metamodel description:
     * <i><p>Deprecated : add property types in a profile.</p>
     * </i>
     */
    @objid ("fdb83583-17d6-4194-bdb8-0f67deb1b43b")
    <T extends PropertyType> List<T> getDefinedPropertyType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->OwnedProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ddd397d6-6a57-4f48-a203-e6f12ee47ead")
    EList<Profile> getOwnedProfile();

    /**
     * Filtered Getter for relation 'ModuleComponent->OwnedProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("5cb67a3e-ea38-474c-8d11-4f4b3dca0005")
    <T extends Profile> List<T> getOwnedProfile(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->ModuleParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4f9f698b-8805-435a-94e3-664029351314")
    EList<ModuleParameter> getModuleParameter();

    /**
     * Filtered Getter for relation 'ModuleComponent->ModuleParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("561d7e9c-397e-4a6d-8ba5-3015b84305a2")
    <T extends ModuleParameter> List<T> getModuleParameter(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->DependsOn'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("e45844ff-3faf-4684-8e91-a9701f1e9629")
    EList<ModuleComponent> getDependsOn();

    /**
     * Filtered Getter for relation 'ModuleComponent->DependsOn'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("71a01b94-cc67-4519-95cd-b1186cbf9e02")
    <T extends ModuleComponent> List<T> getDependsOn(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->Impacted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bfe62ab9-ce32-48c2-82fe-66287ee4e35e")
    EList<ModuleComponent> getImpacted();

    /**
     * Filtered Getter for relation 'ModuleComponent->Impacted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("ccf2d218-61c6-46f4-b8cf-b808fec16de8")
    <T extends ModuleComponent> List<T> getImpacted(java.lang.Class<T> filterClass);

}
