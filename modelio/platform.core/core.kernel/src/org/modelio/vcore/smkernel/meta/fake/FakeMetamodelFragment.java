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

package org.modelio.vcore.smkernel.meta.fake;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
 * Fake metaclasses metamodel fragment.
 */
@objid ("169efd78-92f6-405d-8fb6-1dc59ea4a540")
public class FakeMetamodelFragment implements ISmMetamodelFragment {
    @objid ("4c66a745-0dce-47a9-a604-0b308baaae67")
    private final String name;

    @objid ("be986d9d-bdb7-4b9f-86cf-dd47b443a87f")
    private static final Version DEFAULT_VERSION = new Version(0,0,0);

    @objid ("084be88b-9e57-4425-a994-a5c9e290e56c")
    private final Version version;

    @objid ("e8fa3acb-e136-47aa-a42d-e026602f732a")
    public FakeMetamodelFragment(String name) {
        this (name, null);
    }

    @objid ("1e5a6af4-7145-4721-85f1-54a0ced02fbe")
    public FakeMetamodelFragment(String name, Version version) {
        this.name = name;
        this.version = version != null ? version : DEFAULT_VERSION;
    }

    @objid ("04411042-9255-4681-843d-054f0fbc2282")
    @Override
    public Collection<SmDependencyTypeChecker> createDependencyCheckers(SmMetamodel metamodel) {
        return Collections.emptyList();
    }

    @objid ("5653df56-4807-4ba9-a83c-fcf0a2f31541")
    @Override
    public MExpert createMExpert(SmMetamodel mm) {
        // noop
        return null;
    }

    /**
     * Create the metaclasses.
     * 
     * @return the metaclasses.
     */
    @objid ("e8419915-d6c9-49e8-977c-623f0b6fa852")
    @Override
    public Collection<SmClass> createMetaclasses() {
        return Collections.emptyList();
    }

    @objid ("e9bb3c8b-ba69-4579-8e1e-65be0c6a1786")
    @Override
    public ICheckerFactory getModelShieldCheckers() {
        return ICheckerFactory.NONE;
    }

    @objid ("efc3fe20-06ab-432e-a879-6bc1584c622c")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("172791e7-de20-416a-a4ef-9f93e345c9d3")
    @Override
    public Collection<VersionedItem<MMetamodelFragment>> getNeededFragments() {
        return Arrays.asList(KernelMetamodelFragment.reference());
    }

    @objid ("8b8e6ecc-5839-4d40-b87e-86bf1e420223")
    @Override
    public String getProvider() {
        return "Unknown";
    }

    @objid ("d87f17d3-0bae-4fea-b231-7abd2dfc336d")
    @Override
    public String getProviderVersion() {
        return getVersion().toString();
    }

    @objid ("908f2429-fd77-4f5d-8a42-d2ecde9d0d0c")
    @Override
    public Version getVersion() {
        return this.version;
    }

    /**
     * Tells whether this metamodel fragment is an extension
     * or a standard Modelio metamodel fragment.
     * <p>
     * Standard Modelio metamodel fragments are guaranteed to have no metaclass name collisions.
     * 
     * @return <i>true</i> if the fragment is an extension, <i>false</i> if it is a Modelio standard fragment.
     */
    @objid ("8f321bad-bd83-41a8-bd27-893e42d5a8f9")
    @Override
    public boolean isExtension() {
        return true;
    }

    @objid ("116a18ea-3000-4d02-a7d2-f6b490be3870")
    @Override
    public final boolean isFake() {
        return true;
    }

    @objid ("a92d744a-b02f-4f53-a2f6-f4c6202cd7c9")
    @Override
    public String toString() {
        return String.format("FakeMetamodelFragment ['%s' v%s]", this.name, this.version);
    }

}
