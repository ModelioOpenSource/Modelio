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
package org.modelio.vcore.smkernel.meta.mof;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.ICheckerFactory;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.KernelMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependencyTypeChecker;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * MOF metaclasses metamodel fragment.
 */
@objid ("e122ed27-342e-414d-b995-3279b7043c0c")
public class MofMetamodelFragment implements ISmMetamodelFragment {
    @objid ("91ec799f-e57f-4587-a7a8-140be03f8237")
    private final String name;

    @objid ("07ade684-18c8-40f6-9fe6-99e8c2319962")
    private final boolean isExtension;

    
    @mdl.prop
    @objid ("5b189c86-a805-46f5-acc5-a74132da5cc2")
    public boolean temporary;

    @mdl.propgetter
    public boolean isTemporary() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.temporary;
    }

    @mdl.propsetter
    public void setTemporary(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.temporary = value;
    }

    @objid ("897f2e0f-7b22-4b35-b449-ad0eed41ca12")
    private String providerVersion;

    @objid ("aa2e51fe-2b7c-457e-a2f3-3ed933f79a75")
    private String provider;

    @objid ("ddbd216c-4867-4284-9ad9-d3cbd6770425")
    private final Version version;

    @objid ("e3eb36ca-cc2a-44d6-a44f-2ec64404e013")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("2ba2202f-e497-435d-a03c-92efb37e1b07")
    @Override
    public Version getVersion() {
        return this.version;
    }

    @objid ("0cfa96da-cb23-462a-aef7-b556534721d7")
    @Override
    public String getProvider() {
        return this.provider;
    }

    @objid ("6cb92773-7837-464e-82f0-7d10697d4d26")
    @Override
    public String getProviderVersion() {
        return this.providerVersion;
    }

    @objid ("c6fbb708-ee1a-4e1e-93c1-321599f3a29c")
    @Override
    public Collection<SmDependencyTypeChecker> createDependencyCheckers(SmMetamodel metamodel) {
        return Collections.emptyList();
    }

    /**
     * Create the metaclasses.
     * @return the metaclasses.
     */
    @objid ("6247bde4-af88-4f8a-8e7d-3b455046a2f4")
    @Override
    public Collection<SmClass> createMetaclasses() {
        return Collections.emptyList();
    }

    @objid ("07afd21d-a51b-44a0-a3d6-36416920f835")
    @Override
    public boolean isExtension() {
        return this.isExtension;
    }

    @objid ("9102d69c-8125-4654-843b-7e7cc5bb4263")
    @Override
    public ICheckerFactory getModelShieldCheckers() {
        return ICheckerFactory.NONE;
    }

    @objid ("b06a8412-f2ef-4267-a493-32d332b02132")
    @Override
    public Collection<VersionedItem<MMetamodelFragment>> getNeededFragments() {
        return Arrays.asList(KernelMetamodelFragment.reference());
    }

    /**
     * Creates a metamodel fragment .
     * <p>
     * Also set whether this metamodel fragment is an extension
     * or a standard Modelio metamodel fragment.
     * <p>
     * Standard Modelio metamodel fragments are guaranteed to have no metaclass name collisions.
     * @param name the metamodel fragment name.
     * @param version the metamodel fragment version.
     * @param isExtension <i>true</i> if the fragment is an extension, <i>false</i> if it is a Modelio standard fragment.
     */
    @objid ("5dcd6596-5669-4212-ae9f-9cf41a987a55")
    public  MofMetamodelFragment(String name, Version version, boolean isExtension) {
        this.name = name;
        this.version = version;
        this.isExtension = isExtension;
        this.provider = "Modeliosoft MOF service";
        this.providerVersion = "";
        
    }

    @objid ("5de343b8-b854-43ba-9637-52b66a0b7fa6")
    @Override
    public MExpert createMExpert(SmMetamodel mm) {
        // no expert
        return null;
    }

    /**
     * Creates a metamodel fragment with 1.0.0 as version.
     * @param name the metamodel fragment name.
     */
    @objid ("adb267f6-8ada-4ed5-ba0f-7933df10ff63")
     MofMetamodelFragment(String name) {
        this.name = name;
        this.version = new Version(1,0,0);
        this.isExtension = true;
        this.provider = "Modeliosoft MOF service";
        this.providerVersion = "";
        
    }

    /**
     * Set the provider.
     * @param provider the provider.
     * @return this instance.
     */
    @objid ("d5aa0979-b5a2-4449-a027-30c465ee0286")
    public MofMetamodelFragment setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    /**
     * @param providerVersion the provider version
     * @return this instance.
     */
    @objid ("1aa9e0d5-215b-4f0e-a03f-db1be134c04f")
    public MofMetamodelFragment setProviderVersion(String providerVersion) {
        this.providerVersion = providerVersion;
        return this;
    }

    @objid ("9a2439a9-d857-4087-b995-05522e9c139d")
    @Override
    public String toString() {
        return String.format("%s['%s' v%s from %s v%s]", getClass().getSimpleName(), getName(), getVersion(), getProvider(), getProviderVersion());
    }

    @objid ("14720ef4-82c4-4aeb-b687-b997563703bf")
    @Override
    public boolean isFake() {
        return false;
    }

}
