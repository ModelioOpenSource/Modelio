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

package org.modelio.vcore.smkernel.meta;

import java.util.Optional;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.descriptor.MAttributeDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MClassDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MDependencyDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelFragmentDescriptor;
import org.modelio.vcore.smkernel.meta.fake.FakeSmAttribute;
import org.modelio.vcore.smkernel.meta.fake.FakeSmClass;
import org.modelio.vcore.smkernel.meta.fake.FakeSmDependency;

/**
 * Tool to merge a {@link MetamodelDescriptor} into a {@link MofMetamodel}.
 * <p>
 * Missing metamodel fragments, metaclasses and attributes are created.
 * No other modification is done, for the moment.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("89e96084-92c1-4256-a211-738fb6d0deb1")
public class SmMetamodelMerger extends AbstractMetamodelMerger {
    @objid ("74eca7dc-1c5d-4692-ba3f-2a303ee156a1")
    private final SmMetamodel metamodel;

    /**
     * @param metamodel the metamodel to modify
     */
    @objid ("7bc77959-08aa-499b-9706-b69fe6309294")
    public SmMetamodelMerger(SmMetamodel metamodel) {
        this.metamodel = metamodel;
    }

    /**
     * merge a {@link MetamodelDescriptor} into a {@link SmMetamodelMerger}.
     * <p>
     * Missing metamodel fragments, metaclasses and attributes are created.
     * 
     * @param desc the descriptor to merge
     * @param target the metamodel to modify
     */
    @objid ("65b1406f-79f2-484d-b14a-6dc299d0efc2")
    public static void merge(MetamodelDescriptor desc, SmMetamodel target) {
        new SmMetamodelMerger(target).merge(desc);
    }

    @objid ("4785a11e-c29d-45d0-a177-6e7b4f706275")
    @Override
    protected void createMissingAttribute(MClass m, MAttributeDescriptor ad) {
        if (m instanceof FakeSmClass) {
            FakeSmClass mofSmClass = (FakeSmClass) m;
            FakeSmAttribute mofAtt = new FakeSmAttribute(mofSmClass, ad.getName());
            mofSmClass.registerAttribute(mofAtt);
        }
    }

    @objid ("02be9b7e-c075-4d9c-a6d6-940ed5e94dbd")
    @Override
    protected void createMissingDependency(MClass m, MDependencyDescriptor dd) {
        if (m instanceof FakeSmClass) {
            FakeSmClass fakeMClass = (FakeSmClass)m;
            fakeMClass.addDependency(new FakeSmDependency(fakeMClass,dd));
        }
    }

    @objid ("20f65ef8-cc06-4813-85ea-e201008c99fd")
    @Override
    protected MMetamodelFragment createMissingFragment(MetamodelFragmentDescriptor fd) {
        return this.metamodel.getFakeFragment(fd.getName(), fd.getVersion());
    }

    @objid ("638524b4-a0af-47b5-8f9b-7b0042b9ebb2")
    @Override
    protected MClass createMissingMetaclass(MetamodelFragmentDescriptor fd, MClassDescriptor md) {
        return this.metamodel.fakeClassBuilder()
                                .setName(md.getName())
                                .setFragmentName(fd.getName())
                                .setCmsNode(md.isCmsNode())
                                .build();
    }

    @objid ("01661858-4fab-4329-ad66-d14b0fc07620")
    @Override
    protected SmMetamodel getMetamodel() {
        return this.metamodel;
    }

    @objid ("3f3ed135-0800-40ce-922c-8a946dec169e")
    @Override
    protected void initMetaclassParent(MClass metaclass, MClass parentMetaclass) {
        FakeSmClass fakeSmClass = (FakeSmClass) metaclass;
        
        fakeSmClass.setParent((SmClass) parentMetaclass);
    }

    @objid ("b3efe472-89fe-400e-8e55-bb356c50e449")
    @Override
    protected void mergeDependenciesOpposite(MClassDescriptor md, MClass m) {
        /*
        if (md instanceof MLinkMetaclassDescriptor) {
            MLinkMetaclassDescriptor mld = (MLinkMetaclassDescriptor) md;
            for (String depName : mld.getSourceDepencencies()) {
                SmDependency mofd = (SmDependency) m.getDependency(depName);
                ((FakeSmDependency) mofd).setFlag(SmDirective.SMCDLINKSOURCE, true);
            }
            
            for (String depName : mld.getTargetDepencencies()) {
                SmDependency mofd = (SmDependency) m.getDependency(depName);
                mofd.addFlag(Collections.singleton(SmDirective.SMCDLINKTARGET));
            }
            
        }
        */
        
        for (MDependencyDescriptor dd : md.getDependencies()) {
            SmDependency mofd = (SmDependency) m.getDependency(dd.getName());
            if (mofd instanceof FakeSmDependency) {
                FakeSmDependency fakeDep = (FakeSmDependency) mofd;
                
                Optional.ofNullable(dd.getTarget())
                .map(targetRef -> this.metamodel.getMClass(targetRef.getQualifiedName()))
                .map(mc -> mc.findDependencyDef(dd.getOppositeName()))
                .ifPresent(oppDep -> fakeDep.setSymetric(oppDep));
            }
        }
    }

    @objid ("60ea3046-226d-455e-a6c9-45a2c2241330")
    @Override
    protected void postMergeMetaclass(MClassDescriptor md, MClass m) {
        if (m instanceof FakeSmClass) {
            FakeSmClass mofSmClass = (FakeSmClass) m;
            mofSmClass.postInit();
        }
    }

}
