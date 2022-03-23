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
package org.modelio.vcore.smkernel.meta;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.smkernel.SmObjectSmClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.ICheckerFactory;

/**
 * Fragment containing the kernel metaclasses.
 * <p>
 * Each SmMetamodel contain one KernelMetamodelFragment.
 * 
 * @author cmarin
 */
@objid ("c567fa54-f3cc-4bfb-80bc-0cb5a14f2710")
public final class KernelMetamodelFragment implements ISmMetamodelFragment {
    @objid ("fab48707-bc24-4a78-9106-ef60d51523c8")
    private static final String PROVIDER = "Modeliosoft";

    @objid ("6db24a5d-7945-48da-b4c2-9045d9e4cb73")
    private static final String NAME = "modelio.kernel";

    @objid ("32857069-cbb5-4375-816d-3c3a0161b407")
    private DefaultMetaExpert mExpert;

    @objid ("6ab240f4-15e1-4cdf-8eaf-c3725e7e57fb")
    private static final Version VERSION = new Version(1, 0, 0);

    /**
     * Create the metaclasses.
     * @return the metaclasses.
     */
    @objid ("54a0fc04-d1e2-44fb-8fd3-77aefa661257")
    @Override
    public Collection<SmClass> createMetaclasses() {
        final SmClass smClass = new SmObjectSmClass(this);
        return Arrays.asList(smClass);
    }

    /**
     * The kernel version. Should not move much.
     */
    @objid ("14a8cbde-4f9e-48ab-9c01-603aacba99bb")
    @Override
    public Version getVersion() {
        return VERSION;
    }

    @objid ("500305f6-1842-4914-bd7c-63456e08d5a8")
    @Override
    public String getProvider() {
        return PROVIDER;
    }

    @objid ("493648f1-2bb0-4502-aaa4-00f6b6c2786d")
    @Override
    public String getName() {
        return NAME;
    }

    @objid ("7c48fa3f-f7ab-4a2e-97f5-7f0d19c6909b")
    @Override
    public List<SmDependencyTypeChecker> createDependencyCheckers(SmMetamodel mm) {
        return Collections.emptyList();
    }

    @objid ("ac44ef2c-7aee-46c8-9a4b-1dbf91570fad")
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.getClass() == KernelMetamodelFragment.class;
    }

    @objid ("9c2beecf-d62d-4733-9257-e84b402a65ac")
    @Override
    public int hashCode() {
        return KernelMetamodelFragment.class.hashCode() * 31;
    }

    @objid ("7a741b6e-8cf4-41d9-ad3a-b64618a8862a")
    @Override
    public String getProviderVersion() {
        return getVersion().toString();
    }

    /**
     * Tells whether this metamodel fragment is an extension
     * or a standard Modelio metamodel fragment.
     * <p>
     * Standard Modelio metamodel fragments are guaranteed to have no metaclass name collisions.
     * @return <i>true</i> if the fragment is an extension, <i>false</i> if it is a Modelio standard fragment.
     */
    @objid ("fc2c77a9-669d-46ac-af8a-948662af7693")
    @Override
    public boolean isExtension() {
        return false;
    }

    @objid ("8425bd92-0c4a-43e7-a27d-392844a4d169")
    @Override
    public ICheckerFactory getModelShieldCheckers() {
        return ICheckerFactory.NONE;
    }

    @objid ("b876f869-8738-4f33-bc32-e5f716167959")
    @Override
    public Collection<VersionedItem<MMetamodelFragment>> getNeededFragments() {
        return Collections.emptyList();
    }

    /**
     * @return a reference of the kernel metamodel fragment.
     */
    @objid ("f3cd4a61-4628-4e3b-a834-7494c4f7e201")
    public static VersionedItem<MMetamodelFragment> reference() {
        return new VersionedItem<>(NAME, VERSION);
    }

    @objid ("9054bfeb-94ab-46a6-b974-f2559b5e8521")
    @Override
    public MExpert createMExpert(SmMetamodel mm) {
        return new DefaultMetaExpert();
    }

    @objid ("3e9ce325-38a9-4f8e-b778-f1d5098d6720")
    @Override
    public boolean isFake() {
        return false;
    }

}
