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

package org.modelio.vcore.smkernel;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.modelio.vcore.emf.ESmAttribute;
import org.modelio.vcore.emf.ESmDependency;
import org.modelio.vcore.emf.MContentListView;
import org.modelio.vcore.emf.MTreeIterator;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MStatus;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * SmObjectImpl instances are the objects that are managed by smkernel along with {@link SmObjectData}.
 * <p>
 * A SmObjectImpl holds a Java weak reference to its SmObjectData data structure. Therefore data can be garbaged at any moment. Before being garbaged, data are in fact swapped to the disk for future re-use by a data cache holder defined in the upper level.
 * <p>
 * SmObjectImpl features cover different areas, each area is covered by a specific interface that SmObjectImpl currently implements:
 * <ul>
 * <li>semantic features management (attributes and dependencies) defined by {@link ISmMeta}
 * <li>storage management covered by {@link ISmStorable}
 * <li>methods for external programmers manipulating the model objects at the meta level defined by {@link MObject}
 * <li>EMF {@link EObject} methods for EMF compatibility
 * </ul>
 */
@objid ("00228210-dbf8-1f1f-85a5-001ec947cd2a")
public abstract class SmObjectImpl implements ISmMeta, ISmStorable, MObject, Serializable, EObject {
    @objid ("4dbc6e88-1cf7-11e2-8eb9-001ec947ccaf")
    private static final long MASK_INVALID = IRStatus.MASK_DELETE | IRStatus.SHELL;

    @objid ("00298b1e-702c-1f21-85a5-001ec947cd2a")
    private static final long serialVersionUID = 2365450794985286365L;

    @objid ("001d0d8a-35bd-10bf-bd58-001ec947cd2a")
    private long liveId;

    @objid ("25484556-b846-4b8c-abd5-9fb79f1588df")
    private String uuid;

    @objid ("5283f9ce-8199-451a-aa9d-e09ea4dfdd36")
    private volatile transient WeakReference<ISmObjectData> dataRef = null;

    /**
     * Get the name of the element.
     * <p>
     * This method returns empty string by default and must be redefined on classes that have a name attribute.
     * @return the element name.
     */
    @objid ("00801d44-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public String getName() {
        return "";
    }

    @objid ("00806a60-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public final String getUuid() {
        return this.uuid;
    }

    @objid ("002a399c-702c-1f21-85a5-001ec947cd2a")
    @Override
    public boolean isShell() {
        try {
            // Don't try to ask composition owner if SmStatus.SHELL bit is not defined,
            // it would end with infinite recursion.
            return getData().hasAnyStatus(IRStatus.SHELL) == StatusState.TRUE;
        } catch (DeadObjectException e) {
            // Designate a dead object as "shell".
            return true;
        }
    }

    @objid ("00721adc-5e9d-1ffc-8433-001ec947cd2a")
    @Override
    public boolean isModifiable() {
        return getStatus().isModifiable();
    }

    /**
     * Ask whether the object is usable.
     * <p>
     * An object is usable if its modeling session and its repository is open and if the object is not deleted.
     * @return <code>true</code> if the object is valid else <code>false</code>.
     */
    @objid ("00817e00-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public final boolean isValid() {
        final IKernelServiceProvider ksp = KernelRegistry.getService0(this.liveId);
        if (ksp == null) {
            return false;
        }
        
        try {
            ISmObjectData data = getData();
            if (data == null) {
                return false;
            }
            return data.hasAnyStatus(SmObjectImpl.MASK_INVALID) != StatusState.TRUE;
        } catch (DeadObjectException e) {
            // A dead object is not valid
            return false;
        }
    }

    /**
     * @return <code>true</code> if the element is deleted, else <code>false</code>.
     */
    @objid ("0083b6b6-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public boolean isDeleted() {
        try {
            // Optimization : avoided call to getSmStatusFlags()
            return SmStatus.isAnySet(getData().getStatus(), IRStatus.MASK_DELETE) == StatusState.TRUE;
        } catch (DeadObjectException e) {
            // A dead object is deleted
            return true;
        }
    }

    /**
     * Check the object is not shell or dead
     * @throws org.modelio.vcore.smkernel.ShellObjectException if the object is shell
     * @throws org.modelio.vcore.smkernel.DeadObjectException if the object is dead
     */
    @objid ("002a7c5e-702c-1f21-85a5-001ec947cd2a")
    private void checkNotShell() throws DeadObjectException, ShellObjectException {
        // getData() may throw DeadObjectException
        if (getData().hasAnyStatus(IRStatus.SHELL) == StatusState.TRUE) {
            throw new ShellObjectException(this);
        }
    }

    /**
     * Hook method that is called after an appendDepVal()
     * <p>
     * Cette methode est appelee apres un append sur une dependance. <br>
     * Elle permet d'effectuer un traitement particulier apres l'ajout d'une dependance. Par defaut, elle ne fait rien.
     * @param dep the modified dependency
     * @param value the added value
     */
    @objid ("007d517c-9fc0-1f4f-9c13-001ec947cd2a")
    public void afterAppendDepVal(final SmDependency dep, final SmObjectImpl value) {
        // nothing to do
    }

    /**
     * Hook method that is called after an eraseDepVal()
     * @param dep the modified model dependency
     * @param value the removed value
     */
    @objid ("007d8ac0-9fc0-1f4f-9c13-001ec947cd2a")
    public void afterEraseDepVal(final SmDependency dep, final SmObjectImpl value) {
        // nothing to do
    }

    @objid ("007dc0da-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public boolean appendDepVal(final SmDependency dep, final SmObjectImpl value) {
        if (dep == null) {
            throw new IllegalArgumentException("cannot append " + value + " to null dependency");
        }
        
        dep.assertValueType(this, value);
        
        boolean returnCode = true;
        
        // Do some cleaning first
        if (dep.getMax() == 1) {
            // Erase the old reference
            SmObjectImpl oldValue = (SmObjectImpl) getDepVal(dep);
            if (Objects.equals(oldValue, value)) {
                // Nothing to do
                return false;
            } else if (oldValue != null) {
                // Erase old reference
                this.eraseDepVal(dep, oldValue);
        
                // Fast exit if appendDepVal(dep, null)
                if (value == null) {
                    return true;
                }
            }
        } else {
            if (value == null) {
                throw new IllegalArgumentException("cannot append null to " + this + "." + dep.getName());
            }
        
            // Prevent dep_val from being twice in the list
            this.eraseDepVal(dep, value);
        }
        
        // ==== Do the job ====================================================
        returnCode = getData().getMetaOf().appendObjDepVal(this, dep, value);
        
        // ==== If the dependency is symmetric and have to propagate
        if (returnCode) {
            propagateAppendToSymetric(dep, value);
        
            afterAppendDepVal(dep, value);
        }
        return returnCode;
    }

    /**
     * <br>
     * Idem que la methode appendDependency, mais a? partir de la dependance (sans verification du nom de la dependance).<br>
     * Cette methode permet d'ajouter une dependance. Si un meta-objet est associe a? l'objet courant la methode passe la main au meta-objet.<br>
     * Exemple: <br>
     * SmObject *dep; SmObject *obj;<br>
     * obj.appendDepVal(ProjectDefinedDomain(),dep);<br>
     * Les anomalies:<br>
     * <i>Type mismatch</i>: Si l'objet n'est pas de type attendu. <br>
     */
    @objid ("007dfd20-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public boolean appendDepVal(final SmDependency dep, final SmObjectImpl value, final int index) {
        // if (isShell()) {
        // throwShellObject();
        // }
        
        if (!dep.isMultiple() && index != 0) {
            throw new IllegalArgumentException(dep.getOwner().getName() + "." + dep.getName() + " is not multiple.");
        }
        
        if (value == null) {
            throw new IllegalArgumentException(" Cannot append null to " + dep);
        }
        
        // dep.checkValueType(this, value);
        
        // ==== Do some cleaning first ===================
        if (dep.getMax() == 1) {
            // Erase the old reference
            SmObjectImpl oldValue = (SmObjectImpl) getDepVal(dep);
            if (oldValue == value) {
                // Nothing to do
                return false;
            } else if (oldValue != null) {
                // Erase old reference
                this.eraseDepVal(dep, oldValue);
            }
        } else {
            // Prevent dep_val from being twice in the list
            this.eraseDepVal(dep, value);
        }
        
        // ==== do the real job ==============================================
        boolean returnCode = getData().getMetaOf().appendObjDepValIndex(this, dep, value, index);
        
        // ==== if the dependency is symmetric and have to propagate ========
        propagateAppendToSymetric(dep, value);
        
        if (returnCode) {
            afterAppendDepVal(dep, value);
        }
        return (returnCode);
    }

    /**
     * Get the value(s) of a dependency via the MetaObject. The returned value is a single possibly null object if the dependency is 'simple' or an unmodifiable possibly empty list if the dependency is multiple.
     */
    @objid ("007f5f9e-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public Object getDepVal(final SmDependency dep) {
        Object values = getData().getMetaOf().getObjDepVal(this, dep);
        
        if (dep.isMultiple()) {
            return (values != null) ? values : Collections.emptyList();
        } else {
            return values;
        }
    }

    @objid ("0081d4b8-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public SmObjectImpl setDepVal(final SmDependency dep, final int index, final SmObjectImpl value) {
        checkNotShell();
        
        // dep.checkValueType(this, value);
        
        // Erase old value
        SmObjectImpl oldVal = eraseDepVal(dep, index);
        
        // Append new value at same index
        appendDepVal(dep, value, index);
        return (oldVal);
    }

    /**
     * <br>
     * Idem que la methode eraseDependency (sans verification du nom de la dependance)<br>
     * Cette methode permet d'enlever une dependance. Si un meta-objet est associe a l'objet courant la methode passe la main au meta-objet.<br>
     * Exemple: <br>
     * 
     * <pre>
     * SmObject *dep; SmObject *obj;<br>
     * obj.eraseDepVal(ProjectDefinedDomain(),dep);<br>
     * </pre>
     * 
     * Les anomalies peuvent etre :<br>
     * <i>Type mismatch</i>: Si l'objet n'est pas du type attendu.<br>
     */
    @objid ("002c27c0-702c-1f21-85a5-001ec947cd2a")
    @Override
    public boolean eraseDepVal(final SmDependency dep, final SmObjectImpl value) {
        // do the job
        boolean returnCode = getData().getMetaOf().eraseObjDepVal(this, dep, value);
        
        // update the symetric dependency
        if (returnCode) {
            propagateEraseToSymetric(dep, value);
        }
        
        if (returnCode) {
            afterEraseDepVal(dep, value);
        }
        return returnCode;
    }

    @objid ("0082d6ce-9fc0-1f4f-9c13-001ec947cd2a")
    private SmObjectImpl eraseDepVal(final SmDependency dep, final int index) {
        @SuppressWarnings ("unchecked")
        SmObjectImpl value = dep.isMultiple() ? ((List<SmObjectImpl>) getDepVal(dep)).get(index) : (SmObjectImpl) getDepVal(dep);
        
        if (eraseDepVal(dep, value)) {
            return value;
        } else {
            return null;
        }
    }

    @objid ("002af5bc-702c-1f21-85a5-001ec947cd2a")
    private void propagateAppendToSymetric(final SmDependency dep, final SmObjectImpl value) {
        SmDependency symetricDep = dep.getSymetric();
        if (value != null && symetricDep != null) {
            // Remove from the symetricDep its old value.
            if (!symetricDep.isMultiple()) {
                SmObjectImpl oldValue = (SmObjectImpl) value.getDepVal(symetricDep);
                if (oldValue != null && !oldValue.equals(this)) {
                    value.eraseDepVal(symetricDep, oldValue);
                }
            }
        
            // Propagate to symetricDep the append
            value.getData().getMetaOf().appendObjDepVal(value, symetricDep, this);
        }
    }

    @objid ("002bc564-702c-1f21-85a5-001ec947cd2a")
    private void propagateEraseToSymetric(final SmDependency dep, final SmObjectImpl value) {
        SmDependency DSymetric = dep.getSymetric();
        if (value != null && DSymetric != null) {
            value.getData().getMetaOf().eraseObjDepVal(value, DSymetric, this);
        }
    }

    @objid ("007e4d34-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public void delete() {
        new DeleteHelper().doDelete(this);
    }

    @objid ("007e6102-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        SmObjectImpl other = (SmObjectImpl) obj;
        
        if (this.uuid == null) {
            if (other.uuid != null) {
                return false;
            }
        } else if (!this.uuid.equals(other.uuid)) {
            return false;
        }
        
        if (this.liveId != other.liveId) {
            return false;
        }
        return true;
    }

    @objid ("007edc7c-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public Object getAttVal(final SmAttribute att) {
        /*
         * if (isShell() && !(att.getName().equalsIgnoreCase("name") || att.equals(SmObjectData.Metadata.statusAtt()))) { // uuid, name, status return null; }
         */
        return getData().getMetaOf().getObjAttVal(this, att);
    }

    @objid ("0081a1d2-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public void setAttVal(final SmAttribute att, final Object value) {
        checkNotShell();
        
        att.assertValueType(this, value);
        
        getData().getMetaOf().setObjAttVal(this, att, value);
    }

    /**
     * Get the object metaclass.
     * <p>
     * You should consider calling the public API {@link #getMClass()} method.
     * @return the object metaclass.
     */
    @objid ("007f0896-9fc0-1f4f-9c13-001ec947cd2a")
    public SmObjectSmClass getClassOf() {
        if (this.dataRef == null || this.dataRef.get() == null) {
            IKernelServiceProvider kernelSvc = KernelRegistry.getService(getLiveId());
            return (SmObjectSmClass) kernelSvc.getMetamodel().getMClass(SmLiveId.getClassId(this.liveId));
        }
        return (SmObjectSmClass) getData().getClassOf();
    }

    /**
     * Get the model object data.
     * @return the model object data.
     * @throws org.modelio.vcore.smkernel.DeadObjectException if the object has definitively been unloaded.
     */
    @objid ("007f3f96-9fc0-1f4f-9c13-001ec947cd2a")
    public final ISmObjectData getData() throws DeadObjectException {
        ISmObjectData data = null;
        
        if (this.dataRef != null) {
            data = this.dataRef.get();
        }
        
        if (data == null) {
            synchronized (this) {
                // First check if another thread didn't restore the reference
                if (this.dataRef != null) {
                    data = this.dataRef.get();
                }
        
                if (data == null) {
                    // Ask for reloading
                    final IKernelServiceProvider ksp = KernelRegistry.getService(this.liveId);
                    data = ksp.loadData(this);
                }
            }
        }
        
        AccessOrderer.accessed(data);
        return data;
    }

    /**
     * Get the object live id.
     * <p>
     * Used by the swap to restore the object.
     * @return the live id.
     */
    @objid ("007f8d7a-9fc0-1f4f-9c13-001ec947cd2a")
    public long getLiveId() {
        return this.liveId;
    }

    /**
     * Get the model object meta object.
     * <p>
     * All read or write operations on the model object go through the metaobject.
     * @return the meta object.
     */
    @objid ("007fae68-9fc0-1f4f-9c13-001ec947cd2a")
    public final IMetaOf getMetaOf() {
        return getData().getMetaOf();
    }

    @objid ("008048a0-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public final IRepositoryObject getRepositoryObject() {
        return getData().getRepositoryObject();
    }

    @objid ("ee67af8e-d4a2-11e1-b069-001ec947ccaf")
    @Override
    public MStatus getStatus() {
        return new MStatusImpl(this);
    }

    /**
     * Tells whether all the given flags are set to <code>true</code> in the status.
     * @param flags the flags to test
     * @return <code>true</code> if all of them are set to <code>true</code>, else <code>false</code>.
     */
    @objid ("00808c70-9fc0-1f4f-9c13-001ec947cd2a")
    public final boolean hasStatus(final long flags) {
        // optimization: should call MetaOf.getAttVal(..) to get pstatus
        long pStatus = getSmStatusFlags();
        StatusState ret = SmStatus.areAllSet(pStatus, flags);
        
        if (ret == StatusState.UNDEFINED) {
            while (ret == StatusState.UNDEFINED) {
                SmObjectImpl owner = getCompositionOwner();
                if (owner == null) {
                    break;
                }
                pStatus = SmStatus.combine(pStatus, owner.getSmStatusFlags());
                ret = SmStatus.areAllSet(pStatus, flags);
            }
        }
        return ret == StatusState.TRUE;
    }

    @objid ("0080bc04-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public int hashCode() {
        // Use the class id, the session id and the UUID to compute hash.
        // Avoid using the repository ID because it may change during the lifetime of the object.
        final short clsid = SmLiveId.getClassId(this.liveId);
        final short kid = SmLiveId.getKid(this.liveId);
        
        final int prime = 31;
        int result = 1;
        result = prime * result + clsid;
        result = prime * result + kid;
        result = prime * result + ((this.uuid == null) ? 0 : this.uuid.hashCode());
        return result;
    }

    /**
     * To be called <strong>just after</strong> the constructor.
     * @param uuid the object identifier
     * @param liveId the object live identifier
     */
    @objid ("0080e882-9fc0-1f4f-9c13-001ec947cd2a")
    @SuppressWarnings ("hiding")
    public final void init(final String uuid, final long liveId) {
        this.liveId = liveId;
        this.uuid = uuid;
        
        if (this.dataRef != null) {
            ISmObjectData d = this.dataRef.get();
            if (d != null) {
                d.init(uuid, liveId);
            }
        }
    }

    /**
     * Initialize the model object data.
     * <p>
     * <b>Note :</b> the data is stored by {@link WeakReference}. <b>Keep a reference</b> on the data to not let it garbage collected !
     * @param data the object data. <b>Keep a reference</b> on the data to not let it garbage collected !
     */
    @objid ("008123ba-9fc0-1f4f-9c13-001ec947cd2a")
    public final void initData(final ISmObjectData data) {
        if (data != null) {
            this.dataRef = new WeakReference<>(data);
        } else {
            this.dataRef = null;
        }
    }

    /**
     * Test if a dependency contains the given value.
     * @param dep a model dependency
     * @param obj a model object to find
     * @return <code>true</code> if found else <code>false</code>.
     */
    @objid ("008148cc-9fc0-1f4f-9c13-001ec947cd2a")
    public boolean hasDepVal(final SmDependency dep, final SmObjectImpl obj) {
        Object depVal = getDepVal(dep);
        if (depVal instanceof Collection<?>) {
            return ((Collection<?>) depVal).contains(obj);
        } else {
            return depVal != null && depVal.equals(obj);
        }
    }

    /**
     * Set the meta object.
     * @param metaObject the new meta object.
     */
    @objid ("0082209e-9fc0-1f4f-9c13-001ec947cd2a")
    public final void setMetaOf(final IMetaOf metaObject) {
        getData().setMetaOf(metaObject);
    }

    @objid ("0082763e-9fc0-1f4f-9c13-001ec947cd2a")
    @Override
    public final void setRepositoryObject(final IRepositoryObject createObject) {
        getData().setRepositoryObject(createObject);
    }

    /**
     * Changes the persistent status of the model object.
     * <p>
     * Use combinations of constants defined in {@link IPStatus} to defined the flags.
     * @param trueFlags the flags to set to true.
     * @param falseFlags the flags to set to false.
     * @param undefFlags the flags to set as not defined.
     */
    @objid ("00829d08-9fc0-1f4f-9c13-001ec947cd2a")
    public void setPStatus(long trueFlags, long falseFlags, long undefFlags) {
        // check flags only contains persistent flags
        assert (((trueFlags | falseFlags | undefFlags) & ~SmStatus.PFLAGS) == 0);
        
        long oldPStatus = SmStatus.getPersistentBits(getSmStatusFlags());
        long newValue = SmStatus.setFlags(oldPStatus, trueFlags, falseFlags, undefFlags);
        
        setAttVal(getClassOf().statusAtt(), newValue);
    }

    @objid ("001abd6e-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public EList<Adapter> eAdapters() {
        return ECollections.emptyEList();
    }

    @objid ("001aef14-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public boolean eDeliver() {
        return false;
    }

    @objid ("001b11ec-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public void eNotify(final Notification notification) {
        throw new UnsupportedOperationException();
    }

    @objid ("001b376c-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public void eSetDeliver(final boolean deliver) {
        throw new UnsupportedOperationException();
    }

    @objid ("001b5cba-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public TreeIterator<EObject> eAllContents() {
        return new MTreeIterator<>(this);
    }

    @objid ("001b8b7c-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public EClass eClass() {
        return getClassOf().getEmfAdapter();
    }

    @objid ("001baf6c-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public EObject eContainer() {
        return getCompositionOwner();
    }

    @objid ("001bd35c-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public EStructuralFeature eContainingFeature() {
        SmDepVal r = getCompositionRelation();
        if (r != null) {
            return r.dep.getSymetric().getEmfAdapter();
        } else {
            return null;
        }
    }

    @objid ("001bf774-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public EReference eContainmentFeature() {
        SmDepVal r = getCompositionRelation();
        if (r != null) {
            return r.dep.getSymetric().getEmfAdapter();
        } else {
            return null;
        }
    }

    @objid ("001c1bf0-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public EList<EObject> eContents() {
        return new MContentListView(this, getClassOf().getAllComponentDepDef());
    }

    @objid ("001c4c38-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public EList<EObject> eCrossReferences() {
        ArrayList<SmDependency> arr = new ArrayList<>(getClassOf().getAllReferenceDepDef());
        arr.addAll(getClassOf().getAllSharedCompositionDep());
        return new MContentListView(this, getClassOf().getAllReferenceDepDef());
    }

    @objid ("001c7c76-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public Object eGet(final EStructuralFeature feature) {
        return eGet(feature, true);
    }

    @objid ("001ca69c-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public Object eGet(EStructuralFeature feature, boolean resolve) {
        if (feature instanceof ESmAttribute) {
            return getAttVal(((ESmAttribute) feature).getSmAtt());
        } else if (feature instanceof ESmDependency) {
            SmDependency dep = ((ESmDependency) feature).getSmDep();
            return getDepVal(dep);
        } else {
            throw new IllegalArgumentException(feature + " does not belong to " + getClassOf().getName());
        }
    }

    @objid ("001cd590-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public Object eInvoke(final EOperation operation, final EList<?> arguments) throws InvocationTargetException {
        throw new UnsupportedOperationException();
    }

    @objid ("001d0fe2-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public boolean eIsProxy() {
        return isShell();
    }

    @objid ("001d35d0-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public boolean eIsSet(final EStructuralFeature feature) {
        return !isShell();
    }

    @objid ("001d6096-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public Resource eResource() {
        return getRepositoryObject().getEmfResource();
    }

    @objid ("001d8760-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    @SuppressWarnings ("unchecked")
    public void eSet(EStructuralFeature feature, Object newValue) {
        if (feature instanceof ESmAttribute) {
            setAttVal(((ESmAttribute) feature).getSmAtt(), newValue);
        } else if (feature instanceof ESmDependency) {
            SmDependency dep = ((ESmDependency) feature).getSmDep();
            if (dep.isMultiple()) {
                List<SmObjectImpl> val = getDepValList(dep);
                val.clear();
                val.addAll((Collection<? extends SmObjectImpl>) newValue);
            } else {
                appendDepVal(dep, (SmObjectImpl) newValue);
            }
        } else {
            throw new IllegalArgumentException(feature + " does not belong to " + getClassOf().getName());
        }
    }

    @objid ("001db582-3c96-1f3d-aafd-001ec947cd2a")
    @Override
    public void eUnset(final EStructuralFeature feature) {
        if (feature instanceof ESmDependency) {
            SmDependency dep = ((ESmDependency) feature).getSmDep();
            getDepValList(dep).clear();
        } else if (feature instanceof ESmAttribute) {
            SmAttribute smAtt = ((ESmAttribute) feature).getSmAtt();
            try {
                // TODO call a smAtt.getType().getDefaultValue()
                setAttVal(smAtt, smAtt.getType().newInstance());
            } catch (InstantiationException e) {
                throw new UnsupportedOperationException(e);
            } catch (IllegalAccessException e) {
                throw new UnsupportedOperationException(e);
            }
        } else {
            throw new IllegalArgumentException(feature + " is not part of " + getClassOf().getName());
        }
    }

    @objid ("005818ee-b454-1f4f-9c13-001ec947cd2a")
    @Override
    @SuppressWarnings ("unchecked")
    public List<SmObjectImpl> getCompositionChildren() {
        ArrayList<SmObjectImpl> results = new ArrayList<>();
        
        for (SmDependency dep : getClassOf().getAllComponentAndSharedDepDef()) {
            Object depVal = getDepVal(dep);
            if (dep.isMultiple()) {
                results.addAll((List<SmObjectImpl>) depVal);
            } else {
                if (depVal != null) {
                    results.add((SmObjectImpl) depVal);
                }
            }
        }
        return results;
    }

    @objid ("dc6c5da6-8fb5-11e1-81e9-001ec947ccaf")
    @SuppressWarnings ("unchecked")
    @Override
    public List<SmObjectImpl> getDepValList(final SmDependency dep) {
        Object ret = getDepVal(dep);
        
        if (ret instanceof List) {
            return (List<SmObjectImpl>) ret;
        } else if (ret == null) {
            return Collections.emptyList();
        } else {
            return Collections.singletonList((SmObjectImpl) ret);
        }
    }

    @objid ("008778be-4d5f-1ffc-8433-001ec947cd2a")
    @Override
    public MClass getMClass() {
        return getClassOf();
    }

    /**
     * {@link MObject#mSet(MAttribute, Object) }
     */
    @objid ("00877e0e-4d5f-1ffc-8433-001ec947cd2a")
    @Override
    public void mSet(MAttribute att, Object value) {
        setAttVal((SmAttribute) att, value);
    }

    @objid ("00877ca6-4d5f-1ffc-8433-001ec947cd2a")
    @Override
    public Object mGet(MAttribute att) {
        return getAttVal((SmAttribute) att);
    }

    @objid ("00877a12-4d5f-1ffc-8433-001ec947cd2a")
    @Override
    public List<MObject> mGet(MDependency dep) {
        return new SmList<>(this, (SmDependency) dep);
    }

    /**
     * Tells whether any the given flags are set to <code>true</code> in the status.
     * @param flag the flags to test
     * @return <code>true</code> if any of them is set to <code>true</code>, else <code>false</code>.
     */
    @objid ("0c638b17-d4cd-11e1-b069-001ec947ccaf")
    public final boolean hasAnyStatus(final long flag) {
        // optimization: should call MetaOf.getAttVal(..) to get pstatus
        long lStatus = getSmStatusFlags();
        StatusState ret = SmStatus.isAnySet(lStatus, flag);
        
        if (ret == StatusState.UNDEFINED) {
            SmObjectImpl owner = this;
            while (ret == StatusState.UNDEFINED) {
                owner = owner.getCompositionOwner();
                if (owner == null) {
                    break;
                }
        
                lStatus = SmStatus.combine(lStatus, owner.getSmStatusFlags());
                ret = SmStatus.isAnySet(lStatus, flag);
            }
        }
        return ret == StatusState.TRUE;
    }

    /**
     * Test the status against required flags and forbidden ones.
     * <p>
     * All required flags must be set and no forbidden one must be set.
     * @param rrequired required runtime flags
     * @param prequired required persistent flags
     * @param rforbidden forbidden runtime flags
     * @param pforbidden forbidden persistent flags
     * @return <code>true</code> if all conditions are met, else <code>false</code>.
     */
    @objid ("c7d04295-d58f-11e1-b069-001ec947ccaf")
    public final boolean hasStatus(final long rrequired, final long prequired, final long rforbidden, final long pforbidden) {
        final long required = rrequired | prequired;
        final long forbidden = rforbidden | pforbidden;
        
        long lStatus = getSmStatusFlags();
        StatusState forbid = SmStatus.isAnySet(lStatus, forbidden);
        StatusState allow = SmStatus.areAllSet(lStatus, required);
        
        if ((allow == StatusState.UNDEFINED && forbid != StatusState.TRUE) || (forbid == StatusState.UNDEFINED && allow != StatusState.FALSE)) {
            SmObjectImpl owner = this;
            while ((allow == StatusState.UNDEFINED && forbid != StatusState.TRUE) || (forbid == StatusState.UNDEFINED && allow != StatusState.FALSE)) {
                owner = owner.getCompositionOwner();
                if (owner == null) {
                    break;
                }
                lStatus = SmStatus.combine(lStatus, owner.getSmStatusFlags());
        
                forbid = SmStatus.isAnySet(lStatus, forbidden);
                allow = SmStatus.areAllSet(lStatus, required);
            }
        }
        
        // The test should be (allow==TRUE && forbid==FALSE)
        // but tolerate (allow==UNDEFINED) although this should not happen.
        return allow != StatusState.FALSE && forbid != StatusState.TRUE;
    }

    /**
     * Dumps the object name, UUID and metaclass.
     * <p>
     * May dump flag strings:
     * <ul>
     * <li><b>DEAD</b> : the object has no more data, the data is not in swap either. This object is not usable anymore and should not be used anymore.
     * <li><b>Shell</b> : the object is an unresolved reference or its loading partially failed. {@link #isShell()} returns true.
     * <li><b>FAKE</b> metaclass name: the object metaclass is an unresolved metaclass reference. {@link MClass#isFake()} on the {@link #getMClass() metaclass} returns true.
     * <li><b>Deleted</b> : The object is deleted but still readable. {@link #isDeleted()} returns true.
     * </ul>
     * In case of exception loading the name the exception string is dumped instead of the name so that this method should never fail even for a broken object.
     */
    @objid ("0052b26e-ee24-1076-aae0-001ec947cd2a")
    @Override
    public String toString() {
        boolean hasNoData = this.dataRef == null || this.dataRef.get() == null;
        
        final StringBuilder s = new StringBuilder(80);
        
        try {
            String name = getName();
            s.append('\'');
            s.append(name);
            s.append('\'');
        } catch (DeadObjectException e) {
            s.append("*DEAD*");
        } catch (RuntimeException | LinkageError | StackOverflowError | OutOfMemoryError e) {
            // Replace the name by the load failure cause
            s.append("!<");
            s.append(e.toString());
            s.append(">!");
        }
        
        s.append("{");
        s.append(this.uuid);
        s.append("} ");
        
        // metaclass
        MClass mClass = getMClass();
        if (mClass.isFake()) {
            s.append(" *FAKE* ");
            s.append(mClass.getQualifiedName());
        } else if (true || mClass.getOrigin().isExtension()) {
            s.append(mClass.getQualifiedName());
        } else {
            s.append(mClass.getName());
        }
        
        // print invalid state
        if (hasNoData) {
            hasNoData = this.dataRef == null || this.dataRef.get() == null;
            if (hasNoData) {
                s.append(" *DEAD*");
            } else {
                s.append(" *data recovered*");
            }
        } else {
            if (isShell()) {
                s.append(" *Shell*");
            }
            if (isDeleted()) {
                s.append(" *Deleted*");
            }
        }
        return s.toString();
    }

    /**
     * Changes the runtime status of the model object.
     * <p>
     * Use combinations of constants defined in {@link IRStatus} to defined the flags.
     * @param trueFlags the flags to set to true.
     * @param falseFlags the flags to set to false.
     * @param undefFlags the flags to set as not defined.
     */
    @objid ("9e51048c-6179-4134-b2d7-365a99e78ebb")
    public void setRStatus(long trueFlags, long falseFlags, long undefFlags) {
        ISmObjectData ldata = getData();
        long oldStatus = ldata.getStatus();
        
        ldata.setRFlags(trueFlags, falseFlags, undefFlags);
        
        long newStatus = ldata.getStatus();
        
        ldata.getMetaOf().objStatusChanged(this, oldStatus, newStatus);
    }

    /**
     * Get the model object status flags.
     * <p>
     * Beware some flags may not be defined on this model object. This is a low level method, it is recommended to use {@link #getStatus()} in most case.
     * @see #getStatus()
     * @return the model object flags.
     */
    @objid ("1127ca90-9397-4dd0-8a27-e7160f6da249")
    public long getSmStatusFlags() {
        getMetaOf().getObjAttVal(this, getClassOf().statusAtt());
        // getRepositoryObject().loadAtt(this, getClassOf().statusAtt());
        return getData().getStatus();
    }

    @objid ("24f92224-adb8-410e-83b6-812488f8cd14")
    @Override
    public int compareTo(MObject o) {
        return getUuid().compareTo(o.getUuid());
    }

    /**
     * Cast operator redefinition if it was C++.
     * <p>
     * Cast this instance to another class/interface.
     * <p>
     * Fake objects redefine this method.
     * @param cls the target java class/interface
     * @return the same object casted (or an adapter)
     */
    @objid ("ae278882-3a57-49f9-8497-1bd2108de4e6")
    @SuppressWarnings ("unchecked")
    public <T extends MObject> T cast(Class<T> cls) {
        return (T) this;
    }

}
