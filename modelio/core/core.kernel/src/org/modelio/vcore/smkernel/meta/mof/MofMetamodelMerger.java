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

import java.util.Collections;
import java.util.Optional;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.meta.AbstractMetamodelMerger;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.descriptor.MAttributeDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MClassDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MDependencyDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MLinkMetaclassDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelFragmentDescriptor;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel.DepBuilder;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * Tool to merge a {@link MetamodelDescriptor} into a {@link MofMetamodel}.
 * <p>
 * Missing metamodel fragments, metaclasses and attributes are created.
 * No other modification is done, for the moment.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("80857722-3a14-4e85-beb5-dd8490c15516")
public class MofMetamodelMerger extends AbstractMetamodelMerger {
    @objid ("a46fe53a-c991-4eb1-9640-4f007cdc3d41")
    private boolean temporary;

    @objid ("8bcc1a4b-26b9-49a6-bfe6-5861faf1d829")
    private final MofMetamodel metamodel;

    /**
     * @param metamodel the metamodel to modify
     */
    @objid ("1f7f603c-d5f6-4dd7-86b5-5898eb22fa4b")
    public MofMetamodelMerger(MofMetamodel metamodel) {
        this.metamodel = metamodel;
    }

    @objid ("7eb10f02-8464-4db8-bf90-86768c2550f9")
    @Override
    protected void mergeDependenciesOpposite(MClassDescriptor md, MClass m) {
        if (md instanceof MLinkMetaclassDescriptor) {
            MLinkMetaclassDescriptor mld = (MLinkMetaclassDescriptor) md;
            for (String depName : mld.getSourceDepencencies()) {
                MofSmDependency mofd = (MofSmDependency) m.getDependency(depName);
                mofd.addFlags(Collections.singleton(SmDirective.SMCDLINKSOURCE));
            }
            
            for (String depName : mld.getTargetDepencencies()) {
                MofSmDependency mofd = (MofSmDependency) m.getDependency(depName);
                mofd.addFlags(Collections.singleton(SmDirective.SMCDLINKTARGET));
            }
            
        }
        
        for (MDependencyDescriptor dd : md.getDependencies()) {
            MofSmDependency mofd = (MofSmDependency) m.getDependency(dd.getName());
            
            Optional.ofNullable(dd.getTarget())
            .map(targetRef -> this.metamodel.getMClass(targetRef.getQualifiedName()))
            .map(mc -> (SmDependency) mc.getDependency(dd.getOppositeName()))
            .ifPresent(oppDep -> mofd.setSymetric(oppDep));
        }
    }

    @objid ("b293a956-22fc-4165-a367-40ce00c9675a")
    @Override
    protected MMetamodelFragment createMissingFragment(MetamodelFragmentDescriptor fd) {
        MofMetamodelFragment mf = new MofMetamodelFragment(fd.getName(), fd.getVersion(), true);
        mf.setProvider(fd.getProvider());
        mf.setProviderVersion(mf.getProviderVersion());
        mf.setTemporary(this.temporary);
        this.metamodel.addMetamodelFragment(mf);
        return mf;
    }

    @objid ("79d6b5c8-1e4b-456d-a132-305372a39ea0")
    @Override
    protected MofSmClass createMissingMetaclass(MetamodelFragmentDescriptor fd, MClassDescriptor md) {
        return this.metamodel
                                                .builder()
                                                .setTemporary(this.temporary)
                                                .createClass(md.getName(), fd.getName(), md.isCmsNode())
                                                .setVersion(md.getVersion())
                                                .build();
    }

    @objid ("b864f1b2-44d2-4042-ac10-29e6e482c4b8")
    @Override
    protected void createMissingDependency(MClass m, MDependencyDescriptor dd) {
        String targetQName = dd.getTarget().getQualifiedName();
        if ("SmObject".equals(dd.getTarget().getClassName())) {
            // workaround classCastException in DepBuilder.setTarget
            targetQName = "Infrastructure.Element";
        }
        
        DepBuilder depBuilder = this.metamodel.builder()
                .setTemporary(this.temporary)
                .createDep(dd.getName())
                .setCardinality(dd.getMin(), dd.getMax())
                .setSource((MofSmClass) m)
                .setTarget(targetQName);
        
        switch(dd.getAggregation()) {
        case Composition:
            depBuilder.setComposition();
            break;
        case SharedAggregation:
            depBuilder.setSharedComposition();
            break;
        case None:
        default:
            if (dd.isNavigate()) {
                depBuilder.setPartOf();
            } else {
                depBuilder.setNoPartOf();
            }
            break;
        }
        
        if (dd.isCascadeDelete()) {
            depBuilder.addFlag(SmDirective.SMCDTODELETE);
        }
        if (dd.isWeakReference()) {
            depBuilder.addFlag(SmDirective.SMCD_KEEP_DELETED_ON_READONLY);
        }
        
        depBuilder.build();
    }

    @objid ("51ad3d93-a49c-4eb9-ae53-e07fa1f7e3b0")
    @Override
    protected void createMissingAttribute(MClass m, MAttributeDescriptor ad) {
        MofSmClass mofSmClass = (MofSmClass) m;
        MofSmAttribute mofAtt = new MofSmAttribute(mofSmClass, ad.getName());
        mofAtt.setTemporary(this.temporary);
        mofSmClass.addAttribute(mofAtt);
    }

    @objid ("8a8501f9-2cf1-408c-97f5-1de4858495f2")
    @Override
    protected void initMetaclassParent(MClass metaclass, MClass parentMetaclass) {
        ((MofSmClass) metaclass).setParent((SmClass) parentMetaclass);
    }

    @objid ("e70cfb51-c0cf-4554-8c69-b5f5ab4682c2")
    @Override
    protected SmMetamodel getMetamodel() {
        return this.metamodel;
    }

    @objid ("b0dbfb5e-5fff-4334-8b22-00100dac920b")
    @Override
    protected void postMergeMetaclass(MClassDescriptor md, MClass m) {
        MofSmClass mofSmClass = (MofSmClass) m;
        mofSmClass.ensurePostInit();
    }

    /**
     * Set whether created meta elements are temporary.
     * <p>
     * Temporary meta elements are not serialized in {@link MofMetamodel#serialize()}.
     * 
     * @param temporary whether created meta elements are temporary.
     * @return this instance.
     */
    @objid ("af81c983-569a-4f66-a602-b2c16e709234")
    public MofMetamodelMerger setTemporary(boolean temporary) {
        this.temporary = temporary;
        return this;
    }

}
