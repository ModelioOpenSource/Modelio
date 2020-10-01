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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.meta.descriptor.MAggregation;
import org.modelio.vcore.smkernel.meta.descriptor.MAttributeDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MClassDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MClassRef;
import org.modelio.vcore.smkernel.meta.descriptor.MDependencyDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MEnumDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MLinkMetaclassDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelFragmentDescriptor;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * Produce a {@link MetamodelDescriptor} from a {@link MMetamodel}.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("db456600-bd7a-4b34-8c63-7151d5c3cb40")
public class MetamodelWriter {
    @objid ("36224c1c-8b31-4a3e-89c1-ef3e54313fd2")
    private Predicate<MAttribute> attFilter = v -> true;

    @objid ("1d9f2e2b-c50d-430f-8e4b-22f1e23b0cda")
    private Predicate<MClass> classFilter = v -> true;

    @objid ("c0711e6c-dd8f-41cc-8c00-4d948b0cfb15")
    private Predicate<MDependency> depFilter = v -> true;

    @objid ("c60c519d-a35a-4b9b-9f5e-4382fb4bbb66")
    private Collection<Class<? extends Enum<?>>> enumToExport;

    @objid ("cd3de96a-d262-4b1b-b086-de09ae1ea8c1")
    private MMetamodel metamodel;

    @objid ("0bcc024a-eb2d-4e27-bcfe-e58ffd3d85e7")
    private Predicate<MMetamodelFragment> mmFragmentFilter = v -> true;

    @objid ("a6ec919a-321a-48e1-bc60-147a9413f91f")
    @SuppressWarnings("hiding")
    public MetamodelDescriptor run(MMetamodel metamodel) {
        this.metamodel = metamodel;
        MetamodelDescriptor d = new MetamodelDescriptor();
        
        for (MMetamodelFragment mf : metamodel.getFragments(true)) {
            if (this.mmFragmentFilter.test(mf)) {
                MetamodelFragmentDescriptor fd = writeFragment(mf);
                d.addFragment(fd);
            }
        }
        
        writeFakeMetaclasses(d);
        return d;
    }

    @objid ("f6abd1be-d12f-4acb-b496-eae0cafe5742")
    public MetamodelWriter withAttributeFilter(Predicate<MAttribute> attFilter) {
        this.attFilter = attFilter;
        return this;
    }

    @objid ("c3438c30-9126-4a02-ae38-93978beea819")
    public MetamodelWriter withDependencyFilter(Predicate<MDependency> depFilter) {
        this.depFilter = depFilter;
        return this;
    }

    @objid ("e2b88d96-6319-43ce-a673-b846e979f924")
    public MetamodelWriter withFragmentFilter(Predicate<MMetamodelFragment> mmFragmentFilter) {
        this.mmFragmentFilter = mmFragmentFilter;
        return this;
    }

    @objid ("a2f709f8-07dd-43a3-b195-6e42abde549a")
    public MetamodelWriter withMetaclassFilter(Predicate<MClass> classFilter) {
        this.classFilter = classFilter;
        return this;
    }

    /**
     * Produce a {@link MetamodelDescriptor} from a {@link MMetamodel}.
     * 
     * @param metamodel the metamodel to write
     * @return the metamodel descriptor.
     */
    @objid ("69e3d55b-9fc0-46bb-9615-35e6cd997064")
    public static MetamodelDescriptor write(MMetamodel metamodel) {
        return new MetamodelWriter().run(metamodel);
    }

    @objid ("0a991b7f-e387-441a-b746-eaf37e3e5eff")
    private MAggregation getAggreagation(MDependency dep) {
        if(dep.isComposition()) {
            return MAggregation.Composition;
        }
        if (dep.isSharedComposition()) {
            return MAggregation.SharedAggregation;
        }
        return MAggregation.None;
    }

    @objid ("82ea59ba-d02a-4615-b0d2-a86915cc43b4")
    private MetamodelFragmentDescriptor getOrCreateFragmentDescriptor(MetamodelDescriptor d, String fragmentName) {
        return d.getFragments().computeIfAbsent(fragmentName, k -> {
                                    MetamodelFragmentDescriptor fd = new MetamodelFragmentDescriptor();
                                    fd.setName(fragmentName);
                                    fd.setProvider("Fake");
                                    fd.setProviderVersion("");
                                    fd.setVersion(new Version(0,0,0));
                                    fd.setFake(true);
                                    return fd;
                                });
    }

    @objid ("2278136e-c643-4a95-8d99-cc8f17bb8a4d")
    private MClassRef toMClassRef(MClass c) {
        if (c == null) {
            return null;
        } else {
            return new MClassRef(c.getOrigin().getName(), c.getName());
        }
    }

    @objid ("553f238e-4135-46dd-8df7-a9d6f8bf90de")
    @SuppressWarnings("unchecked")
    private MAttributeDescriptor writeAttribute(MAttribute att) {
        MAttributeDescriptor d = new MAttributeDescriptor();
        d.setName(att.getName());
        Class<?> type = att.getType();
        if (type.isEnum()) {
            d.setType(Enum.class);
            d.setEnumType(type.getName());
            this.enumToExport.add((Class<? extends Enum<?>>) type);
        } else {
            d.setType(type);
        }
        return d;
    }

    @objid ("4219a215-eaca-4db7-ab8d-291437c77c26")
    private MDependencyDescriptor writeDependency(MDependency dep) {
        MDependencyDescriptor d = new MDependencyDescriptor();
        d.setMin(dep.getMinCardinality());
        d.setMax(dep.getMaxCardinality());
        d.setName(dep.getName());
        if (dep.getSymetric() != null) {
            d.setOppositeName(dep.getSymetric().getName());
        }
        d.setTarget(toMClassRef(dep.getTarget()));
        d.setAggregation(getAggreagation(dep));
        
        if (dep instanceof SmDependency) {
            SmDependency smd = (SmDependency) dep;
            d.setNavigate((smd.isPartOf() || smd.isComposition() || smd.isSharedComposition()) && ! smd.isDynamic());
            d.setCascadeDelete(smd.hasDirective(SmDirective.SMCDTODELETE) /*smd.isToDelete()*/);
            d.setWeakReference(smd.hasDirective(SmDirective.SMCD_KEEP_DELETED_ON_READONLY));
        }
        return d;
    }

    @objid ("ad833b40-4fae-4ca4-9893-afd2e682eb3b")
    private MEnumDescriptor writeEnum(Class<? extends Enum<?>> enumCls) {
        MEnumDescriptor d = new MEnumDescriptor();
        d.setName(enumCls.getName());
        
        for (Enum<?> val : enumCls.getEnumConstants()) {
            d.getValues().add(val.name());
        }
        return d;
    }

    @objid ("18251f24-9e78-4164-b057-5d9f589aac47")
    private void writeFakeMetaclasses(MetamodelDescriptor d) {
        for (MClass cls : this.metamodel.getRegisteredMClasses()) {
            if (cls.isFake() && this.classFilter.test(cls)) {
                MClassRef cref = MClassRef.fromQualifiedName(cls.getQualifiedName());
                
                MetamodelFragmentDescriptor fd = getOrCreateFragmentDescriptor(d, cref.getFragmentName());
                
                fd.getMetaclasses().add(writeMetaclass(cls));
            }
        }
    }

    @objid ("716072f5-ff75-48f6-83e2-6b6b2ac3162b")
    private MetamodelFragmentDescriptor writeFragment(MMetamodelFragment mf) {
        this.enumToExport = new HashSet<>();
        
        MetamodelFragmentDescriptor d = new MetamodelFragmentDescriptor();
        d.setName(mf.getName());
        d.setProvider(mf.getProvider());
        d.setProviderVersion(mf.getProviderVersion());
        d.setVersion(mf.getVersion());
        d.setFake(mf.isFake());
        
        for (VersionedItem<MMetamodelFragment> neededRef : mf.getNeededFragments()) {
            d.getDependencies().add(new VersionedItem<>(neededRef.getName(), neededRef.getVersion(), null));
        }
        
        for (MClass cls : this.metamodel.getRegisteredMClasses()) {
            if (cls.getOrigin() == mf && this.classFilter.test(cls)) {
                d.getMetaclasses().add(writeMetaclass(cls));
            }
        }
        
        for (Class<? extends Enum<?>> enumCls : this.enumToExport) {
            d.getEnumerations().add(writeEnum(enumCls));
        }
        
        // needed to make MMetamodelFragmentDescriptor.equals() work
        Collections.sort(d.getMetaclasses(), (a,b) -> a.getName().compareTo(b.getName()));
        Collections.sort(d.getEnumerations(), (a,b) -> a.getName().compareTo(b.getName()));
        return d;
    }

    @objid ("95c315ef-e92f-4865-a6d9-6a5236d97c35")
    private MClassDescriptor writeMetaclass(MClass cls) {
        if (cls.isLinkMetaclass()) {
            MLinkMetaclassDescriptor d = new MLinkMetaclassDescriptor();
            writeMetaclassContent(cls, d);
            
            for (MDependency dep : cls.getLinkMetaclassSources()) {
                if (this.depFilter.test(dep)) {
                    d.getSourceDepencencies().add(dep.getName());
                }
            }
            for (MDependency dep : cls.getLinkMetaclassTargets()) {
                if (this.depFilter.test(dep)) {
                    d.getTargetDepencencies().add(dep.getName());
                }
            }
            
            return d;
            
        } else {
            MClassDescriptor d = new MClassDescriptor();
            writeMetaclassContent(cls, d);
            return d;
            
        }
    }

    @objid ("b429c9f0-22cd-4a6b-a23b-c26d476e4b9b")
    private void writeMetaclassContent(MClass cls, MClassDescriptor d) {
        d.setName(cls.getName());
        d.setVersion(cls.getVersion());
        d.setAbstrakt(cls.isAbstract());
        d.setCmsNode(cls.isCmsNode());
        d.setFake(cls.isFake());
        
        for (MAttribute att : cls.getAttributes(false)) {
            if (this.attFilter.test(att)) {
                d.getAttributes().add(writeAttribute(att));
            }
        }
        
        for (MDependency dep : cls.getDependencies(false)) {
            if (this.depFilter.test(dep)) {
                d.getDependencies().add(writeDependency(dep));
            }
        }
        
        d.setParent(toMClassRef(cls.getSuper()));
    }

}
