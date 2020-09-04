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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.mof.MofSmClass;
import org.modelio.vcore.smkernel.meta.mof.MofSmObjectImpl;

/**
 * Utility class to rename metaclasses.
 * @author cma
 * @since 3.6
 */
@objid ("ba40200d-52bf-435b-8f0d-a01181eb8aef")
public class MetaclassRenamer {
    @objid ("4b98363f-2c73-4361-a736-93664a7c753e")
    private IMofSession mofSession;

    @objid ("4c95c212-d422-485c-801b-e633d8d8e5ca")
    private final Map<String,MofSmClass[]> mcToTransmute = new HashMap<>();

    /**
     * Register a metaclass renaming.
     * @param oldMetaclass the old metaclass
     * @param newMetaclass the new metaclass.
     */
    @objid ("27eb8873-7a96-4ece-b6d2-763a15ea90d1")
    public void addClassRenaming(MofSmClass oldMetaclass, MofSmClass newMetaclass) {
        this.mcToTransmute.put(oldMetaclass.getQualifiedName(), new MofSmClass[]{oldMetaclass,newMetaclass });
    }

    /**
     * Transmute all elements whose metaclass is renamed.
     * @param monitor a progress monitor
     * @param aMofSession the MOF session to use.
     */
    @objid ("0ebd0289-4338-4f87-9943-2816de6bccbe")
    public void transmuteRenamedClasses(IModelioProgress monitor, IMofSession aMofSession) {
        this.mofSession = aMofSession;
        SubProgress mon = SubProgress.convert(monitor, this.mcToTransmute.size());
        for (MofSmClass[] entry : this.mcToTransmute.values()) {
            if (!entry[0].isAbstract()) {
                for (MofSmObjectImpl obj : this.mofSession.findByClass(entry[0], false)) {
                    this.mofSession.transmute(obj, entry[1]);
                }
                
                for (MofSmObjectImpl obj : getAllForeignReferences(entry[0])) {
                    this.mofSession.transmute(obj, entry[1]);
                }
            }
            mon.worked(1);
        }
    }

    /**
     * Get all model objects of the given metaclasses that are not in the repository but
     * are referenced by at least another object.
     * <p>
     * Allows to get model objects not stored in the repository to migrate. Most of them are
     * unresolved references. Unresolved references must be transmuted too when metaclasses names change.
     * @param withSubClasses true to look for sub metaclasses hierarchy.
     * @param metaclass the metaclass to look for
     * @return the found references.
     */
    @objid ("e56965cb-84e3-4cdf-917c-7b0d8a4afbc7")
    private Collection<MofSmObjectImpl> getAllForeignReferences(MClass metaclass) {
        Collection<MofSmObjectImpl> ret = new HashSet<>();
        
        for (MDependency dep : metaclass.getDependencies(true)) {
            if (!(dep.isComposition() || dep.isSharedComposition() || ((SmDependency) dep).isPartOf())) {
                MDependency opposite = dep.getSymetric();
                if (opposite != null) {
                    MClass mcToLoad = opposite.getSource();
        
                    getForeignReferences(ret, metaclass, opposite, mcToLoad);
                    MofSmClass renamed = getNewMetaclass(mcToLoad.getQualifiedName());
                    if (renamed != null) {
                        getForeignReferences(ret, metaclass, opposite, renamed);
                    }
                } else {
                    this.mofSession.getReport().getLogger().format("  Warn: '%s.%s' has no opposite dependency", metaclass.getQualifiedName(), dep);
                }
            }
        }
        return ret;
    }

    @objid ("84f6baa6-c9f0-4e40-8dc5-1a54f318a573")
    private void getForeignReferences(Collection<MofSmObjectImpl> ret, MClass metaclass, MDependency opposite, MClass mcToLoad) {
        for (MofSmObjectImpl toLoad : this.mofSession.findByClass(mcToLoad, true)) {
            for (MObject ref : toLoad.mGet(opposite)) {
                MofSmObjectImpl mofRef = (MofSmObjectImpl) ref;
                if (mofRef.getRepositoryObject().getRepositoryId() != this.mofSession.getTargetRepository().getRepositoryId()) {
                    MofSmClass refClassOf = mofRef.getClassOf();
                    boolean matches = refClassOf.equals(metaclass);
                    if (matches ) {
                        ret.add(mofRef);
                    }
                }
            }
        }
    }

    /**
     * Look for the new metaclass from the old qualified name.
     * @param oldQualifiedName the old metaclass qualified name
     * @return the new metaclass or null.
     */
    @objid ("af5d1c74-62e8-455a-8dc5-32777761f123")
    public MofSmClass getNewMetaclass(String oldQualifiedName) {
        MofSmClass[] entry = this.mcToTransmute.get(oldQualifiedName);
        if (entry != null) {
            return entry[1];
        } else {
            return null;
        }
    }

}
