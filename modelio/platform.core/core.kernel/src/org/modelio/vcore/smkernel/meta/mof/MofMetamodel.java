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

package org.modelio.vcore.smkernel.meta.mof;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.mapi.MetaclassAlreadyExistException;
import org.modelio.vcore.smkernel.meta.FakeSmClassBuilder;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.MetamodelWriter;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.SmElement;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.descriptor.MClassRef;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * M.O.F metamodel.
 * <p>
 * Flexible metamodel to be used instead of SmMetamodel when migrating a model from a metamodel to another.
 * <p>
 * First add the target metamodel content with {@link #copy(MMetamodel)}
 * then add the source metamodel content that is absent from the target one.
 * Finally you can freely load and transform a loaded model.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("b89513c6-af0d-4016-8035-9e66098fab22")
public class MofMetamodel extends SmMetamodel {
    /**
     * Register a copy of a metaclass.
     * <p>
     * The copy will have a copy of the same dependencies as the source.
     * The dependency copies will point to the same target metaclass as the copied ones.
     * @param origCls the metaclass to copy
     * @param name the new metaclass short name
     * @param fragment the new metaclass fragment.
     * @return the created metaclass
     */
    @objid ("f31f7c4e-f57f-4d35-96f0-b6af26e97201")
    public MofSmClass addCopy(MofSmClass origCls, String name, MofMetamodelFragment fragment) {
        MofSmClass mofCls = new MofSmClass(fragment, name, fragment.getName()+"."+name, origCls.isCmsNode());
        
        mofCls.load(this);
        mofCls.setAbstract(origCls.isAbstract());
        mofCls.setLinkMetaclass(origCls.isLinkMetaclass());
        mofCls.setParent(origCls.getParent());
        
        for (MAttribute origAtt : origCls.getAttributes(false)) {
            MofSmAttribute mofAtt = new MofSmAttribute(mofCls, (SmAttribute)origAtt);
            mofCls.addAttribute(mofAtt);
        }
        
        for (MDependency origDep : origCls.getDependencies(false)) {
            SmDependency origSmDep = (SmDependency) origDep;
        
            MofSmClass mofTargetCls = (MofSmClass) origSmDep.getTarget();
            MofSmDependency mofDep = new MofSmDependency(mofCls, origDep.getName(), mofTargetCls);
            mofDep.setCardinality(origDep.getMinCardinality(), origDep.getMaxCardinality());
            mofDep.addFlags(origSmDep.getDirectives());
            mofDep.setSymetric(origSmDep.getSymetric()); // note: the opposite opposite will not be mofDep
        
            mofCls.addDependency(mofDep);
        }
        
        registerMetaclass(mofCls);
        mofCls.postInit();
        return mofCls;
    }

    /**
     * Get access to metaclass and meta dependencies builders.
     * <p>
     * This class implements {@link AutoCloseable} and should be
     * used in a <i>try-with-resources</i> statement.
     * @return a builder to create new MOF metaclasses and dependencies.
     */
    @objid ("42cfd27a-3f0b-417e-b85a-68a3649a6707")
    public MofBuilder builder() {
        return new MofBuilder();
    }

    /**
     * Copy a metamodel into this metamodel.
     * <p>
     * All copies metaclass will be converted to MOF metaclasses.
     * @param original the metamodel to copy
     */
    @objid ("bfd92201-2995-4203-9753-a0f4554033ba")
    public void copy(MMetamodel original) {
        Map<ISmMetamodelFragment ,ISmMetamodelFragment > copiedFragMaps = new HashMap<>();
        Map<MClass, MofSmClass> clsMap = new HashMap<>();
        
        // Create empty metamodel fragments for each original metamodel fragment
        // absent in this metamodel.
        for (MMetamodelFragment origFrag : original.getFragments(true)) {
            if (getFragment(origFrag.getName()) == null) {
                MofMetamodelFragment mofFr = new MofMetamodelFragment(
                        origFrag.getName(), 
                        origFrag.getVersion(),
                        origFrag.isExtension())
                        .setProvider(origFrag.getProvider())
                        .setProviderVersion(origFrag.getProviderVersion());
        
                addMetamodelFragment(mofFr);
                copiedFragMaps.put((ISmMetamodelFragment) origFrag, mofFr);
            }
        }
        
        //  Exclude metaclasses outside copied metamodel fragments
        Collection<? extends MClass> classesToCopy = original
                .getRegisteredMClasses()
                .stream()
                .filter(cls -> (cls.isFake() || copiedFragMaps.containsKey(cls.getOrigin()) ))
                .collect(Collectors.toList());
        
        // Create MOF metaclasses with their attributes.
        for (MClass origCls : classesToCopy) {
            ISmMetamodelFragment origin ;
            if (origCls.isFake()) {
                origin = getOrCreateFragment(MClassRef.fromQualifiedName(origCls.getQualifiedName()).getFragmentName());
            } else {
                origin = copiedFragMaps.get(origCls.getOrigin());
            }
        
            MofSmClass mofCls = createStubCopy(origCls, origin);
            
            clsMap.put(origCls, mofCls);
        }
        
        // Set inheritance & Copy dependencies
        for (MClass origCls : classesToCopy) {
            MofSmClass mofCls = clsMap.get(origCls);
        
            // Set inheritance
            SmClass mofParentCls = getMClass(origCls.getSuper().getQualifiedName());
            mofCls.setParent(mofParentCls);
        
            // Copy dependencies
            for (MDependency origDep : origCls.getDependencies(false)) {
                MClass origDepTarget = origDep.getTarget();
                SmClass mofTargetCls = clsMap.get(origDepTarget);
                if (mofTargetCls == null) {
                    Log.warning("MofMetamodel.copy: %s.%s : '%s' target class is not copied.", mofCls.getQualifiedName(), origDep, origDepTarget);
                    mofTargetCls = this.getMClass(origDepTarget.getQualifiedName());
                    if (mofTargetCls == null) {
                        // should not happen
                        assert(false) : String.valueOf(origDepTarget);
                        Log.error("MofMetamodel.copy: %s.%s : target class absent in MOF metamodel, make a copy.", mofCls.getQualifiedName(), origDep);
                        mofTargetCls = createStubCopy(origDepTarget, null);
                        clsMap.put(origCls, mofCls);
                    }
                }
        
                MofSmDependency mofDep = new MofSmDependency(mofCls, origDep.getName(), mofTargetCls);
                mofDep.addFlags(((SmElement) origDep).getDirectives());
                mofDep.setCardinality(origDep.getMinCardinality(), origDep.getMaxCardinality());
        
                mofCls.addDependency(mofDep);
                
                // set the original opposite as temporary symetric
                mofDep.setSymetric((SmDependency) origDep.getSymetric());
            }
        }
        
        // First post initialization
        for (MofSmClass mofCls : clsMap.values()) {
            mofCls.postInit();
        }
        
        // initialize dependencies opposite
        for (MofSmClass mofCls : clsMap.values()) {
            for (MDependency dep : mofCls.getDependencies(false)) {
                // get the temporary symetric set above
                // and replace it with its copy.
                MDependency origSym = dep.getSymetric();
                MClass origTarget = dep.getTarget();
        
                if (origSym == null) {
                    Log.warning("MofMetamodel.copy: %s.%s dependency has no opposite.", mofCls.getQualifiedName(), dep);
                } else if (origTarget == null) {
                    Log.warning("MofMetamodel.copy: %s.%s dependency has no target.", mofCls.getQualifiedName(), dep);
                } else {
                    MDependency finalSym = origTarget.getDependency(origSym.getName());
        
                    if (finalSym != null) {
                        ((MofSmDependency) dep).setSymetric((SmDependency) finalSym);
                    } else {
                        Log.warning("MofMetamodel.copy: No matching '%s' dependency found in '%s'", origSym, origTarget.getQualifiedName());
                    }
                } 
            }
        }
        
        // Finish initialization
        for (MofSmClass mofCls : clsMap.values()) {
            mofCls.ensurePostInit();
        }
    }

    /**
     * Find or create the metamodel fragment with the given name.
     * <p>
     * If absent, a new extension metamodel fragment with 1.0.00 version is created.
     * @param fragmentName the metamodel fragment name.
     * @return the found or created metamodel fragment .
     */
    @objid ("1c87a257-707b-4aed-b3f0-8732efa65308")
    public MofMetamodelFragment getOrCreateFragment(String fragmentName) {
        MofMetamodelFragment ret = (MofMetamodelFragment) getFragment(fragmentName);
        if (ret == null) {
            ret = new MofMetamodelFragment(fragmentName);
            addMetamodelFragment(ret);
        }
        return ret;
    }

    @objid ("e7ecf95c-6124-4d6e-8ae4-6195637f4005")
    @Override
    public void merge(MetamodelDescriptor mmDesc) {
        new MofMetamodelMerger(this).merge(mmDesc);
    }

    /**
     * Exclude temporary meta elements from serialization.
     */
    @objid ("7ec4e423-d25a-4882-a735-2868a8b15276")
    @Override
    public MetamodelDescriptor serialize() {
        return new MetamodelWriter()
                                .withAttributeFilter(att -> !( att instanceof MofSmAttribute) || !((MofSmAttribute)att).isTemporary())
                                .withDependencyFilter(dep -> !( dep instanceof MofSmDependency) || !((MofSmDependency)dep).isTemporary())
                                .withMetaclassFilter(mc -> !( mc instanceof MofSmClass) || !((MofSmClass)mc).isTemporary())
                                .withFragmentFilter(mf -> !( mf instanceof MofMetamodelFragment) || !((MofMetamodelFragment)mf).isTemporary())
                                .run(this);
    }

    @objid ("a4005620-24a8-4d75-ad05-287cac22c69f")
    private MofSmClass createStubCopy(MClass origCls, ISmMetamodelFragment origin) {
        MofSmClass mofCls = new MofSmClass(
                origin != null ? origin : getOrCreateFragment(MClassRef.fromQualifiedName(origCls.getQualifiedName()).getFragmentName()), 
                origCls.getName(), 
                origCls.getQualifiedName(), 
                origCls.isCmsNode());
        mofCls.setAbstract(origCls.isAbstract());
        mofCls.setLinkMetaclass(origCls.isLinkMetaclass());
        mofCls.setVersion(origCls.getVersion());
        mofCls.load(this);
        
        
        for (MAttribute origAtt : origCls.getAttributes(false)) {
            MofSmAttribute mofAtt = new MofSmAttribute(mofCls, (SmAttribute)origAtt);
            mofCls.addAttribute(mofAtt);
        }
        
        registerMetaclass(mofCls);
        return mofCls;
    }

    @objid ("0e55248e-f12e-4465-bb99-8bdd813eccc2")
    @Override
    public FakeSmClassBuilder fakeClassBuilder() {
        return new MofFakeSmClassBuilder(this);
    }

    /**
     * MOF dependency builder
     */
    @objid ("b320af39-51d1-4b4e-bf74-57bc71d547b7")
    public class DepBuilder {
        @objid ("e3d0541d-b51f-4027-b6fe-c2d272c92e42")
        private String name;

        @objid ("e9c28367-6030-442f-9ec8-8f0353cac340")
        private int cardMin = 0;

        @objid ("f13ffc47-6d0e-43c8-84f5-5644418196e4")
        private int cardMax = -1;

        @objid ("21178996-45c0-4149-b116-bb9f80d15c2d")
        private EnumSet<SmDirective> flags = EnumSet.noneOf(SmDirective.class);

        @objid ("25b02c02-739a-4d9c-a04b-97c354e82095")
        private boolean temporary;

        @objid ("82bf43c7-dd8f-4b75-b58e-2b8f469f2efd")
        private MofSmClass source;

        @objid ("7f0e6632-3d37-4c74-a2a0-77f99fa04477")
        private MofSmClass target;

        @objid ("4ac70678-e8a5-487e-a68a-274ca9a5a3d3")
        private MofSmDependency opposite;

        @objid ("33d741fb-432f-4b55-b453-4f8cd7ca83ac")
        private DepBuilder oppositeBuilder;

        /**
         * Initialize the builder for a 0..* {noPartOf} dependency
         * @param source
         * @param name
         */
        @objid ("1e6d0b95-b363-4345-adfe-e2bb40702ac3")
        DepBuilder(MofSmClass source, String name) {
            this.name = name;
            this.source = source;
            this.temporary = true;
        }

        /**
         * @param source the dependency source.
         * @return this instance.
         */
        @objid ("62829221-fb34-4ad9-891b-db08e4d559fb")
        public DepBuilder setSource(MofSmClass source) {
            Objects.requireNonNull(source);
            
            this.source = source;
            return this;
        }

        @objid ("507878b9-a5a4-43dc-aa0e-23156954ee33")
        public DepBuilder setTarget(MofSmClass target) {
            Objects.requireNonNull(target);
            
            this.target = target;
            return this;
        }

        @objid ("0bb4aa48-b428-415a-8749-c7a13c6fc950")
        public DepBuilder setTarget(String targetName) {
            Objects.requireNonNull(targetName);
            
            this.target = (MofSmClass) getMClass(targetName);
            if (this.target == null) {
                throw new IllegalArgumentException(String.format("'%s' metaclass not found.", targetName));
            }
            return this;
        }

        /**
         * Set the dependency opposite.
         * @param opposite the dependency opposite.
         * @return this instance.
         */
        @objid ("7a12865e-9865-40a9-93f5-26d97d4a8238")
        public DepBuilder setOpposite(MofSmDependency opposite) {
            Objects.requireNonNull(opposite);
            this.opposite = opposite;
            return this;
        }

        /**
         * Set the opposite dependency from its name in the target metaclass.
         * <p>
         * The opposite dependency must already exist.
         * @param opposite the opposite dependency name.
         * @return this instance.
         */
        @objid ("f8b23ec5-7c4d-4314-9b0b-03a7d383ecc1")
        public DepBuilder setOpposite(String opposite) {
            Objects.requireNonNull(opposite);
            
            this.opposite = (MofSmDependency) this.target.getDependency(opposite);
            if (this.opposite == null) {
                throw new IllegalArgumentException(String.format("'%s' dependency not found in '%s' metaclass .", opposite, this.target.getQualifiedName()));
            }
            return this;
        }

        /**
         * Build the {@link MofSmDependency} and its opposite if asked for.
         * @return the built {@link MofSmDependency}.
         */
        @objid ("ae7e5478-6f79-462b-9394-500982e3ee62")
        public MofSmDependency build() {
            Objects.requireNonNull(this.source, "Source metaclass missing");
            Objects.requireNonNull(this.name, "Dependency name missing");
            Objects.requireNonNull(this.target, "Target metaclass missing");
            
            MofSmDependency built = new MofSmDependency(this.source, this.name, this.target);
            built.setCardinality(this.cardMin, this.cardMax);
            built.addFlags(this.flags);
            built.setTemporary(this.temporary);
            
            if (this.oppositeBuilder != null) {
                this.oppositeBuilder.setSource(this.target);
                this.oppositeBuilder.setTarget(this.source);
                this.oppositeBuilder.opposite = null;
                this.oppositeBuilder.oppositeBuilder = null;
            
                this.opposite = this.oppositeBuilder.build();
            }
            
            if (this.opposite != null) {
                built.setSymetric(this.opposite);
            
                if (this.opposite.getSymetric() == null) {
                    this.opposite.setSymetric(built);
                }
            }
            
            this.source.addDependency(built);
            return built;
        }

        /**
         * Create the opposite dependency.
         * @param oppositeName the opposite name
         * @param oppCardMin the minimum cardinality
         * @param oppCardMax the maximum cardinality
         * @return this instance
         */
        @objid ("6b822207-a75f-4e70-b307-a4acf37d28b4")
        public DepBuilder createOpposite(String oppositeName, int oppCardMin, int oppCardMax) {
            this.oppositeBuilder = new DepBuilder(this.source, oppositeName);
            this.oppositeBuilder.setTemporary(this.temporary);
            this.oppositeBuilder.setCardinality(oppCardMin, oppCardMax);
            return this;
        }

        /**
         * Copy another dependency.
         * <p>
         * This dependency and the other will have the same opposite.
         * @param orig the dependency to copy
         * @param source the source metaclass of the copy
         */
        @objid ("b9409c53-9c94-4307-aef5-08ea108e22c0")
        DepBuilder(SmDependency orig, SmClass source) {
            this.name = orig.getName();
            this.target = (MofSmClass) orig.getTarget();
            this.cardMin = orig.getMinCardinality();
            this.cardMax = orig.getMaxCardinality();
            this.opposite = (MofSmDependency) orig.getSymetric();
            this.flags = orig.getDirectives().isEmpty() ? EnumSet.noneOf(SmDirective.class) : EnumSet.copyOf(orig.getDirectives());
            this.source = (MofSmClass) source;
        }

        @objid ("a3c139f5-4680-44ad-8477-94a0968221d2")
        public DepBuilder setCardinality(int min, int max) {
            this.cardMin = min;
            this.cardMax = max;
            return this;
        }

        @objid ("58ed8157-9cc8-4a8a-9793-514602ac8441")
        public DepBuilder setComposition() {
            this.flags.add(SmDirective.SMCDCOMPONENT);
            this.flags.remove(SmDirective.SMCDSHAREDCOMPONENT);
            this.flags.remove(SmDirective.SMCDPARTOF);
            return this;
        }

        @objid ("8e78eab8-3471-4d8d-b884-b45b7adc1e85")
        public DepBuilder setSharedComposition() {
            this.flags.add(SmDirective.SMCDSHAREDCOMPONENT);
            this.flags.remove(SmDirective.SMCDCOMPONENT);
            this.flags.remove(SmDirective.SMCDPARTOF);
            return this;
        }

        /**
         * Means the role is an opposite role.
         * @return this instance.
         */
        @objid ("69325b57-6105-4afc-ad7e-fe76410e5898")
        public DepBuilder setNoPartOf() {
            this.flags.remove(SmDirective.SMCDSHAREDCOMPONENT);
            this.flags.remove(SmDirective.SMCDCOMPONENT);
            this.flags.remove(SmDirective.SMCDPARTOF);
            return this;
        }

        /**
         * Means the target must be deleted with the source.
         * @return this instance.
         */
        @objid ("99d53888-f5dd-438d-9e63-17e715f13bf0")
        public DepBuilder setIsToDelete() {
            this.flags.add(SmDirective.SMCDTODELETE);
            return this;
        }

        @objid ("f6eb02dd-fba6-420f-808c-2b5d5ba2085b")
        public DepBuilder addFlag(SmDirective flag) {
            this.flags.add(flag);
            return this;
        }

        /**
         * Create the opposite dependency.
         * @param oppositeName the opposite name
         * @param oppositeInitializer a consumer that further initializes the opposite dependency.
         * @return this instance
         */
        @objid ("1685c3b8-544f-4842-bda2-c9558ec5c022")
        public DepBuilder createOpposite(String oppositeName, Consumer<DepBuilder> oppositeInitializer) {
            this.oppositeBuilder = new DepBuilder(this.source, oppositeName);
            oppositeInitializer.accept(this.oppositeBuilder);
            return this;
        }

        /**
         * Set whether build meta elements are temporary.
         * <p>
         * Temporary elements are not persisted in {@link MofMetamodel#serialize()}.
         * @param temporary whether build meta elements are temporary.
         * @return this instance.
         */
        @objid ("52b7f1ca-88b1-4779-8767-3f6d33db9dcb")
        public DepBuilder setTemporary(boolean temporary) {
            this.temporary = temporary;
            return this;
        }

        /**
         * Build the dependency only if it does not already exist.
         * @return the found or built dependency
         */
        @objid ("e1568c5c-4a03-444f-8592-4d92ddb2feab")
        public MofSmDependency getOrCreate() {
            for (MDependency dep : this.source.getDependencies(false)) {
                if (dep.getName().equals(this.name)) {
                    if (dep.getTarget().equals(this.target)) {
                        return (MofSmDependency) dep;
                    }
                }
            }
            return build();
        }

        /**
         * Means the role is a navigable {partOf} role.
         * @return this instance.
         */
        @objid ("252fddd9-a22c-4f75-80c8-02d7519b149d")
        public DepBuilder setPartOf() {
            this.flags.remove(SmDirective.SMCDSHAREDCOMPONENT);
            this.flags.remove(SmDirective.SMCDCOMPONENT);
            this.flags.add(SmDirective.SMCDPARTOF);
            return this;
        }

    }

    /**
     * Metamodel builder facilities.
     * <p>
     * Get access to metaclass and meta dependencies builders.
     * <p>
     * This class implements {@link AutoCloseable} and should be
     * used in a <i>try-with-resources</i> statement.
     */
    @objid ("6fb812b9-4fac-4b21-85b0-e74db8d85ea5")
    public class MofBuilder implements AutoCloseable {
        @objid ("b537c41b-8c25-473b-8f43-1ada02a6c39b")
        private boolean temporary;

        /**
         * Get a builder to create a metaclass.
         * @param clsName the metaclass name
         * @param fragmentName the metamodel fragment name
         * @param isCmsNode whether it is a CMS node
         * @return the builder
         */
        @objid ("59abfbd9-b955-4249-b498-34d14a862bf7")
        public MofClassBuilder createClass(String clsName, String fragmentName, boolean isCmsNode) {
            return new MofClassBuilder(clsName, fragmentName, isCmsNode).setTemporary(this.temporary);
        }

        /**
         * Initialize the builder for a 0..* {noPartOf} dependency without source.
         * @param name the dependency name
         * @return a builder to create the dependency.
         */
        @objid ("8c070673-2165-4e3d-a9ed-e850a79feed1")
        public DepBuilder createDep(String name) {
            return new DepBuilder(null, name).setTemporary(this.temporary);
        }

        /**
         * Build a copy of the given dependency.
         * <p>
         * The original and the copy are configured to share the same opposite.
         * @param orig the dependency to copy
         * @param modelElCls the source of the dependency copy
         * @return a builder to create the dependency.
         */
        @objid ("a855ccbc-b727-4daf-8e43-7d39b9cfa4e5")
        public DepBuilder createDepCopy(SmDependency orig, SmClass modelElCls) {
            DepBuilder depBuilder = new DepBuilder(orig, modelElCls).setTemporary(this.temporary);
            return depBuilder;
        }

        @objid ("9b0d772b-e8ac-40da-bb03-8d535d009e3a")
        @Override
        public void close() {
            for (SmClass cls : getRegisteredMClasses()) {
                if (cls instanceof MofSmClass) {
                    ((MofSmClass) cls).ensurePostInit();
                }
            }
        }

        /**
         * Set whether build meta elements are temporary.
         * <p>
         * Temporary elements are not persisted in {@link MofMetamodel#serialize()}.
         * @param temporary whether build meta elements are temporary.
         * @return this instance.
         */
        @objid ("b4e0ec97-2aaf-4846-b467-6176750884f2")
        public MofBuilder setTemporary(boolean temporary) {
            this.temporary = temporary;
            return this;
        }

    }

    /**
     * Builder to create a metaclass.
     * @author cma
     */
    @objid ("bf9c23da-e0b5-4d6f-91a1-cf3809b41a37")
    public class MofClassBuilder {
        @objid ("0caad6be-db34-4568-8b49-d34eff1d5672")
        private final MofSmClass built;

        @objid ("e8da694c-bc24-40c4-839f-18829c978217")
        MofClassBuilder(String clsName, String fragmentName, boolean isCmsNode) {
            ISmMetamodelFragment origin = getOrCreateFragment(fragmentName);
            String qualifiedName = fragmentName+"."+clsName;
            this.built = new MofSmClass(origin, clsName, qualifiedName, isCmsNode);
            this.built.setTemporary(true);
            this.built.load(MofMetamodel.this);
        }

        @objid ("102e99c0-9dfe-4915-b72f-c506b9c9840c")
        public MofClassBuilder setAbstract(boolean isAbstract) {
            this.built.setAbstract(isAbstract);
            return this;
        }

        @objid ("b581253f-0e35-4e30-a786-cb70e48d8d04")
        public MofClassBuilder setIsCmsNode(boolean isCmsNode) {
            this.built.setIsCmsNode(isCmsNode);
            return this;
        }

        @objid ("896dda71-9473-488c-9fd0-f216de9fc2c9")
        public MofClassBuilder setParent(MofSmClass aMofCls) {
            Objects.requireNonNull(aMofCls);
            this.built.setParent(aMofCls);
            return this;
        }

        @objid ("0b96e22a-c44e-4742-92c0-983fb63de1b2")
        public MofClassBuilder setIsLink(boolean isLinkMetaclass) {
            this.built.setLinkMetaclass(isLinkMetaclass);
            return this;
        }

        @objid ("5f7e6179-a9c2-4971-8144-879bbe5b7151")
        public MofClassBuilder add(DepBuilder depBuilder) {
            depBuilder.setSource(this.built);
            depBuilder.build();
            return this;
        }

        /**
         * Create a SmDirective.SMCDPARTOF 0..* dependency builder.
         * @param name the dependency name
         * @return the dependency builder for further initializations.
         */
        @objid ("6c7993d9-12b4-4299-9085-b3b74b4ead6d")
        public DepBuilder createDep(String name) {
            DepBuilder depBuilder = new DepBuilder(this.built, name);
            depBuilder.addFlag(SmDirective.SMCDPARTOF);
            return depBuilder;
        }

        /**
         * Finish the metaclass building.
         * @return the built metaclass
         */
        @objid ("1cd3b42b-0a6d-4398-9d3d-92825c9ebacc")
        public MofSmClass build() {
            registerMetaclass(this.built);
            this.built.postInit();
            return this.built;
        }

        /**
         * @param version the metaclass version
         * @return this instance
         */
        @objid ("b19bee76-62c0-4658-8d14-e2f0cab3fcc9")
        public MofClassBuilder setVersion(Version version) {
            this.built.setVersion(version);
            return this;
        }

        /**
         * Set whether build meta elements are temporary.
         * <p>
         * Temporary elements are not persisted in {@link MofMetamodel#serialize()}.
         * @param isTemp whether build meta elements are temporary.
         * @return this instance.
         */
        @objid ("1053efc2-c717-4d75-be5c-5b078af8e725")
        public MofClassBuilder setTemporary(boolean isTemp) {
            this.built.setTemporary(isTemp);
            return this;
        }

    }

    @objid ("7a30da55-0d05-4656-8294-d1c81368d7c7")
    protected static class MofFakeSmClassBuilder extends FakeSmClassBuilder {
        @objid ("41f17ef2-6217-44dd-b96e-474a2de54e37")
        protected MofFakeSmClassBuilder(SmMetamodel mm) {
            super(mm);
        }

        @objid ("b82d217e-7eb7-4e7b-a445-ac62e85f6d11")
        @Override
        public SmClass build() throws MetaclassAlreadyExistException {
            Objects.requireNonNull(getFragmentName());
            Objects.requireNonNull(getName());
            
            MofMetamodel mm = (MofMetamodel) getMm();
            //MofMetamodelFragment fakeFragment = mm.getOrCreateFragment(getFragmentName());
            
            MofSmClass cls = mm.builder()
                    .createClass(getName(), getFragmentName(), isCmsNode())
                    .setTemporary(false)
                    .build();
            return cls;
        }

    }

}
