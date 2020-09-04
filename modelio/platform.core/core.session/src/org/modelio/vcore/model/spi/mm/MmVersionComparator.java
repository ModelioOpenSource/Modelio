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

package org.modelio.vcore.model.spi.mm;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MetamodelVersionDescriptor;

/**
 * Metamodel descriptor compatibility comparator for migrations.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("691065d2-78a1-4c8a-9602-6ae6e4c88660")
public class MmVersionComparator {
    @objid ("ca28d9b7-43c1-40b0-ab92-ed885a380746")
    private final MetamodelVersionDescriptor sourceMetamodel;

    @objid ("7db69864-f980-4b2e-a12c-83e666ac8bcf")
    private MetamodelVersionDescriptor targetMetamodel;

    /**
     * @return the recorded source metamodel to open.
     */
    @objid ("50057a37-aa61-4349-a695-f80cdd37b556")
    public MetamodelVersionDescriptor getSource() {
        return this.sourceMetamodel;
    }

    /**
     * @return the recorded available metamodel.
     */
    @objid ("a840eaa6-b2f5-4c54-bb81-6f487788cb3e")
    public MetamodelVersionDescriptor getTarget() {
        return this.targetMetamodel;
    }

    /**
     * Tells whether the target metamodel is compatible with the source metamodel to open.
     * <p>
     * See {@link MetamodelVersionDescriptor#isCompatibleWith(MetamodelVersionDescriptor, boolean)}.
     * @param allowBuildCompatible whether to allow build compatible
     * @return whether the target metamodel is "build compatible" with the source metamodel to open.
     */
    @objid ("50eb4fbe-e3e8-49a0-b6fb-1178d19e6431")
    public boolean isTargetCompatible(boolean allowBuildCompatible) {
        return this.targetMetamodel.isCompatibleWith(this.sourceMetamodel, allowBuildCompatible);
    }

    /**
     * Get a copy of the tester with common metamodel fragments removed.
     * @return a tester without common metamodel fragments .
     */
    @objid ("fb8504f1-bf2c-41ca-908d-af1d1b8934c8")
    public MmVersionComparator withCommonRemoved() {
        MetamodelVersionDescriptor src = this.sourceMetamodel.copy().filter(m -> !m.getVersion().equals(this.targetMetamodel.getVersion(m.getName())));
        MetamodelVersionDescriptor target = this.targetMetamodel.copy().filter(m -> !m.getVersion().equals(this.sourceMetamodel.getVersion(m.getName())));
        return MmVersionComparator.withSource(src).withTarget(target);
    }

    /**
     * Get a copy of the tester with metamodel fragments missing from any side removed.
     * @return a tester without missing metamodel fragments.
     */
    @objid ("113c7dde-6d7a-4a99-aadc-de1c353724aa")
    public MmVersionComparator withMissingRemoved() {
        MetamodelVersionDescriptor src = this.sourceMetamodel.copy().filter(m -> this.targetMetamodel.getVersion(m.getName()) != null);
        MetamodelVersionDescriptor target = this.targetMetamodel.copy().filter(m -> this.sourceMetamodel.getVersion(m.getName()) != null);
        return MmVersionComparator.withSource(src).withTarget(target);
    }

    /**
     * Get a copy of the tester with metamodel fragments missing from the source side removed.
     * @return a tester without missing source metamodel fragments.
     */
    @objid ("a977de00-039e-4ae4-93d5-00cf1794380b")
    public MmVersionComparator withMissingSourcesRemoved() {
        MetamodelVersionDescriptor src = this.sourceMetamodel.copy().filter(m -> this.targetMetamodel.getVersion(m.getName()) != null);
        return MmVersionComparator.withSource(src).withTarget(this.targetMetamodel);
    }

    /**
     * Initialize a tester with the source metamodel to open.
     * @param mmToOpen the source metamodel to open
     * @return a tester.
     */
    @objid ("ff429b47-8243-44d2-b5fd-b56ce82bd829")
    public static MmVersionComparator withSource(MetamodelVersionDescriptor mmToOpen) {
        MmVersionComparator r = new MmVersionComparator(mmToOpen);
        return r;
    }

    /**
     * Set the metamodel that will be used to access the model.
     * @param curMm the available metamodel
     * @return the same instance.
     */
    @objid ("b0a22247-d763-421c-a3f3-426ee93e3e7d")
    public MmVersionComparator withTarget(MetamodelVersionDescriptor curMm) {
        this.targetMetamodel = curMm;
        return this;
    }

    @objid ("274d93b4-55f3-4c7a-9be9-4031a9fe457c")
    protected MmVersionComparator(MetamodelVersionDescriptor srcMmToOpen) {
        this.sourceMetamodel = srcMmToOpen;
    }

}
