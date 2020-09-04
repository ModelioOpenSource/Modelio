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
    @objid ("a43d997e-04fe-491f-8b4c-9434cf21885f")
    public static final String MNAME = "ModuleComponent";

    /**
     * The metaclass qualified name.
     */
    @objid ("7e6e5d67-dbbb-4a9f-993c-44051548f00a")
    public static final String MQNAME = "Infrastructure.ModuleComponent";

    /**
     * Getter for attribute 'ModuleComponent.LicenseKey'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0c516706-9b70-4fe9-8703-8220012b550d")
    int getLicenseKey();

    /**
     * Setter for attribute 'ModuleComponent.LicenseKey'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("d96db625-14f5-48e2-847e-ef0549d56088")
    void setLicenseKey(int value);

    /**
     * Getter for attribute 'ModuleComponent.MajVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3727f4a3-58c5-4aa6-afe5-1a0af4c42b2d")
    int getMajVersion();

    /**
     * Setter for attribute 'ModuleComponent.MajVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1709a902-b531-46c0-aa51-85fbcae4226a")
    void setMajVersion(int value);

    /**
     * Getter for attribute 'ModuleComponent.MinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("86afe0a7-ed0c-4451-9024-a468e153bbf2")
    int getMinVersion();

    /**
     * Setter for attribute 'ModuleComponent.MinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("4e5dffba-8955-462e-82d9-155efa121663")
    void setMinVersion(int value);

    /**
     * Getter for attribute 'ModuleComponent.MinMinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c99fb4d4-bf41-4c0a-b099-ccf4f7eda7f5")
    String getMinMinVersion();

    /**
     * Setter for attribute 'ModuleComponent.MinMinVersion'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("8405488b-34a4-47f9-8fe8-c882a56df1a3")
    void setMinMinVersion(String value);

    /**
     * Getter for attribute 'ModuleComponent.MinBinVersionCompatibility'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("c4a27a9c-487e-4a1a-b156-1c6b51fdad6e")
    String getMinBinVersionCompatibility();

    /**
     * Setter for attribute 'ModuleComponent.MinBinVersionCompatibility'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("88ec8330-f135-4900-9140-3b452e84147d")
    void setMinBinVersionCompatibility(String value);

    /**
     * Getter for attribute 'ModuleComponent.JavaClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("1444fa92-9858-4712-8e24-548fcbbb340f")
    String getJavaClassName();

    /**
     * Setter for attribute 'ModuleComponent.JavaClassName'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("471fdca9-f232-4b39-a492-4a4f266a19c3")
    void setJavaClassName(String value);

    /**
     * Getter for relation 'ModuleComponent->DefinedPropertyType'
     * 
     * Metamodel description:
     * <i><p>Deprecated : add property types in a profile.</p>
     * </i>
     */
    @objid ("b6d27b12-dc1a-49c3-a872-d50dc9c39d3d")
    EList<PropertyType> getDefinedPropertyType();

    /**
     * Filtered Getter for relation 'ModuleComponent->DefinedPropertyType'
     * 
     * Metamodel description:
     * <i><p>Deprecated : add property types in a profile.</p>
     * </i>
     */
    @objid ("73513b90-a6da-4d59-88f9-f1cd3997cd0e")
    <T extends PropertyType> List<T> getDefinedPropertyType(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->OwnedProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("bbff7286-4325-4281-8eba-1766a950602c")
    EList<Profile> getOwnedProfile();

    /**
     * Filtered Getter for relation 'ModuleComponent->OwnedProfile'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("0297307c-5fcd-4118-a50e-41753c2f9bca")
    <T extends Profile> List<T> getOwnedProfile(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->ModuleParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("30db2533-2962-4d31-840a-4f9e4a9354a6")
    EList<ModuleParameter> getModuleParameter();

    /**
     * Filtered Getter for relation 'ModuleComponent->ModuleParameter'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("3aef7a99-d340-40eb-9ac6-76e51fb40316")
    <T extends ModuleParameter> List<T> getModuleParameter(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->DependsOn'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("12a526b6-fb42-4dc6-8279-582015326d70")
    EList<ModuleComponent> getDependsOn();

    /**
     * Filtered Getter for relation 'ModuleComponent->DependsOn'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("b4696b24-5467-4982-8584-3d40db8b5add")
    <T extends ModuleComponent> List<T> getDependsOn(java.lang.Class<T> filterClass);

    /**
     * Getter for relation 'ModuleComponent->Impacted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("a948880a-0b47-4f18-91ff-13c224b1efa7")
    EList<ModuleComponent> getImpacted();

    /**
     * Filtered Getter for relation 'ModuleComponent->Impacted'
     * 
     * Metamodel description:
     * <i>null</i>
     */
    @objid ("f9ede7b7-3f7c-40ab-99a3-b197fca0130d")
    <T extends ModuleComponent> List<T> getImpacted(java.lang.Class<T> filterClass);

}
