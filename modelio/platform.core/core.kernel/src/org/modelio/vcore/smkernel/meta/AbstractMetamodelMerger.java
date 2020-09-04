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

package org.modelio.vcore.smkernel.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectSmClass;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodelFragment;
import org.modelio.vcore.smkernel.meta.descriptor.MAttributeDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MClassDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MClassRef;
import org.modelio.vcore.smkernel.meta.descriptor.MDependencyDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelDescriptor;
import org.modelio.vcore.smkernel.meta.descriptor.MetamodelFragmentDescriptor;
import org.modelio.vcore.smkernel.meta.mof.MofMetamodel;

/**
 * Tool to merge a {@link MetamodelDescriptor} into a {@link MofMetamodel}.
 * <p>
 * Missing metamodel fragments, metaclasses and attributes are created.
 * No other modification is done, for the moment.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("08dd529b-a7f3-4d7d-ade7-66289984f183")
public abstract class AbstractMetamodelMerger {
    /**
     * merge a {@link MetamodelDescriptor} into a {@link MofMetamodel}.
     * <p>
     * Missing metamodel fragments, metaclasses and attributes are created.
     * @param desc the descriptor to merge
     */
    @objid ("4ecad3a3-80db-48d3-9c58-b0f12eabbc54")
    public AbstractMetamodelMerger merge(MetamodelDescriptor desc) {
        List<MetamodelFragmentDescriptor> merge1 = new ArrayList<>();
        List<MMetamodelFragment> merge2 = new ArrayList<>();
        
        for (MetamodelFragmentDescriptor fd : desc.getFragments().values()) {
            MMetamodelFragment f = getMetamodel().getFragment(fd.getName());
            if (f == null) {
                f = createMissingFragment(fd);
                
                merge1.add(fd);
                merge2.add(f);
                createMissingMetaclasses(fd, f);
            } else if (! f.getVersion().equals(fd.getVersion())) {
                merge1.add(fd);
                merge2.add(f);
                createMissingMetaclasses(fd, f);
            }
        }
        
        for (int i = 0; i < merge1.size(); i++) {
            MetamodelFragmentDescriptor fd = merge1.get(i);
            MMetamodelFragment f = merge2.get(i);
            mergeFragment(fd, f);
        }
        
        for (int i = 0; i < merge1.size(); i++) {
            MetamodelFragmentDescriptor fd = merge1.get(i);
            MMetamodelFragment f = merge2.get(i);
            mergeDependencyOpposites(fd, f);
        }
        return this;
    }

    @objid ("6e32e477-74ab-4aed-8659-742a465cdfa8")
    private void mergeFragment(MetamodelFragmentDescriptor fd, MMetamodelFragment f) {
        // Add missing attributes/dependencies
        for (MClassDescriptor md : fd.getMetaclasses()) {
            SmClass m = getMetamodel().getMClass(fd.getName()+"."+md.getName());
            mergeMetaclass(md, m);
        }
    }

    /**
     * Add missing attributes/dependencies
     * @param md the owner metaclass descriptor
     * @param m the owner metaclass
     */
    @objid ("dbc53bc5-3628-4f0b-9e3e-29bddefd65b4")
    private void mergeMetaclass(MClassDescriptor md, SmClass m) {
        MClassRef parentRef = md.getParent();
        if (parentRef != null) {
            MClass descParent = getMetamodel().getMClass(parentRef.getQualifiedName());
            if (descParent != null) {
                MClass currentParent = m.getSuper();
                
                if (! Objects.equals(currentParent, descParent)) {
                    if (currentParent == null || SmObjectSmClass.MQNAME.equals(currentParent.getQualifiedName())) {
                        initMetaclassParent(m, descParent);
                    } else {
                        fixMetaclassParent(m, descParent);
                    }
                }
            }
        }
        
        for (MAttributeDescriptor ad : md.getAttributes()) {
            if (m.getAttribute(ad.getName()) == null) {
                createMissingAttribute(m, ad);
            }
        }
        
        for (MDependencyDescriptor dd : md.getDependencies()) {
            
            if (m.findDependencyDef(dd.getName()) == null) {
                createMissingDependency(m, dd);
            }
        }
        
        postMergeMetaclass(md, m);
    }

    @objid ("1275422a-0c5c-4a34-8a31-2006c62b5eb3")
    protected abstract void mergeDependenciesOpposite(MClassDescriptor md, MClass m);

    @objid ("a184c1a6-1624-4a20-9470-1afe371b6423")
    protected abstract MMetamodelFragment createMissingFragment(MetamodelFragmentDescriptor fd);

    @objid ("f3f520a4-8496-418f-8397-4dbcabaa1f59")
    protected abstract MClass createMissingMetaclass(MetamodelFragmentDescriptor fd, MClassDescriptor md);

    @objid ("e5baa475-a219-4884-bde5-7b78113ee2e4")
    protected abstract void createMissingDependency(MClass m, MDependencyDescriptor dd);

    @objid ("a81f206f-5ff9-4800-9072-0820e5dce2ee")
    protected abstract void createMissingAttribute(MClass m, MAttributeDescriptor ad);

    @objid ("e5216406-bb90-4dea-9926-c5f959b2255d")
    protected abstract void initMetaclassParent(MClass metaclass, MClass parentMetaclass);

    /**
     * The implementation may do something to make metamodels compatible.
     * <p>
     * Default implementation does nothing.
     * @param metaclass the metaclass to fix
     * @param parentMetaclass the parent metaclass in the descriptor.
     */
    @objid ("0ea57d7e-5d20-4b63-9c4c-fd0ce542257f")
    protected void fixMetaclassParent(MClass metaclass, MClass parentMetaclass) {
        // ignore
    }

    @objid ("cb1e3f4f-0f5c-45d9-ad90-db80e42fcf8f")
    protected abstract SmMetamodel getMetamodel();

    @objid ("1359cc1a-9735-484b-afad-4cb3c6a95eef")
    protected abstract void postMergeMetaclass(MClassDescriptor md, MClass m);

    @objid ("902383ce-03b1-43c5-9d1a-bfab9fb846fe")
    private void mergeDependencyOpposites(MetamodelFragmentDescriptor fd, MMetamodelFragment f) {
        // Set dependencies opposites
        for (MClassDescriptor md : fd.getMetaclasses()) {
            MClass m = getMetamodel().getMClass(fd.getName()+"."+md.getName());
            mergeDependenciesOpposite(md, m);
        }
    }

    @objid ("68ccaba6-7dee-4f24-a225-ea88d8f83fa7")
    private void createMissingMetaclasses(MetamodelFragmentDescriptor fd, MMetamodelFragment f) {
        for (MClassDescriptor md : fd.getMetaclasses()) {
            MClass m = getMetamodel().getMClass(fd.getName()+"."+md.getName());
            if (m==null) {
                m = createMissingMetaclass(fd, md);
            }
        }
    }

}
