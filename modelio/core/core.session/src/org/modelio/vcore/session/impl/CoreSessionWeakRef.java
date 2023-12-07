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
package org.modelio.vcore.session.impl;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.ICoreSessionListener;
import org.modelio.vcore.session.api.IMetamodelSupport;
import org.modelio.vcore.session.api.blob.IBlobSupport;
import org.modelio.vcore.session.api.memory.IMemoryManager;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.api.model.change.IModelChangeSupport;
import org.modelio.vcore.session.api.repository.IRepositorySupport;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * {@link ICoreSession} implementation that delegates to a {@link WeakReference}.
 * <p>
 * To be used to keep a reference to a {@link ICoreSession} without preventing it to be garbage collected.
 * It prevents memory leaks.
 * <p>
 * <b>Warning</b> : don't use <code>CoreSessionWeakRef</code> as hash map key, hashCode() and equals() are not implemented.
 * 
 * @author cmarin
 * @since 5.4.0
 */
@objid ("092a7614-d7d8-4121-a027-156ec80d654d")
public class CoreSessionWeakRef implements ICoreSession {
    @objid ("a2a625a7-d301-4d49-bb62-589335f8b75a")
    protected final WeakReference<ICoreSession> ref;

    @objid ("81ab3ed4-40f7-4eac-9a1a-40cc17a9088f")
    protected ICoreSession getWrapped() {
        ICoreSession wrapped = this.ref.get();
        if (wrapped == null)
            throw new IllegalStateException("The session has been closed and garbaged.");
        return wrapped;
    }

    @objid ("2dc3beb2-2a95-4189-b4d8-7fcb27ad9865")
    public  CoreSessionWeakRef(ICoreSession sess) {
        Objects.requireNonNull(sess);
        this.ref = new WeakReference<>(sess);
        
    }

    @objid ("736ddd45-1004-45e7-a7cb-fab6fade81cc")
    @Override
    public void close() {
        ICoreSession wrapped = this.ref.get();
        if (wrapped == null)
            return;
        
        wrapped.close();
        
    }

    @objid ("05f8a678-ca40-4106-913f-58895f97ab8d")
    @Override
    public IBlobSupport getBlobSupport() {
        return getWrapped().getBlobSupport();
    }

    @objid ("0135dc12-8e93-4256-92d7-ac0bfe657b52")
    @Override
    public IMemoryManager getMemoryManager() {
        return getWrapped().getMemoryManager();
    }

    @objid ("b45f0904-12ff-4059-8a05-86e2d363ec32")
    @Override
    public SmMetamodel getMetamodel() {
        return getWrapped().getMetamodel();
    }

    @objid ("1c8a1bd9-608a-4808-823c-10a53908c5d0")
    @Override
    public IMetamodelSupport getMetamodelSupport() {
        return getWrapped().getMetamodelSupport();
    }

    @objid ("1dd335e8-241b-43bd-8493-1b02397c9227")
    @Override
    public IModel getModel() {
        return getWrapped().getModel();
    }

    @objid ("f4835c70-c028-46f8-92cc-c8fa59fbd859")
    @Override
    public IModelChangeSupport getModelChangeSupport() {
        return getWrapped().getModelChangeSupport();
    }

    @objid ("5fc60bce-cef5-4b10-b16a-d830ca242f41")
    @Override
    public IRepositorySupport getRepositorySupport() {
        return getWrapped().getRepositorySupport();
    }

    @objid ("7d2c95ab-6257-44ed-8b2d-bfde781cacc2")
    @Override
    public ScheduledExecutorService getSchedulerService() {
        return getWrapped().getSchedulerService();
    }

    @objid ("04adfa23-59bf-4d98-b599-90cbb8b35121")
    @Override
    public ITransactionSupport getTransactionSupport() {
        return getWrapped().getTransactionSupport();
    }

    @objid ("d0d776bc-fa47-45fd-96a9-f83ce30690ab")
    @Override
    public boolean isDirty() {
        return getWrapped().isDirty();
    }

    @objid ("54e9a9e2-af4d-4cf5-b20f-00e213cfc3f2")
    @Override
    public void save(IModelioProgress monitor) throws IOException {
        getWrapped().save(monitor);
    }

    @objid ("6fb0e4fe-6a76-4783-9d24-d02a15bda159")
    @Override
    public boolean isValid() {
        ICoreSession wrapped = this.ref.get();
        if (wrapped == null)
            return false;
        return wrapped.isValid();
    }

    @objid ("b4251404-7b7b-4a9f-9fcd-d16dde5d7dc1")
    @Override
    public void addSessionListener(ICoreSessionListener listener) {
        getWrapped().addSessionListener(listener);
    }

    @objid ("78bf7fcc-c67a-4438-a52b-f833e208d201")
    @Override
    public void removeSessionListener(ICoreSessionListener listener) {
        ICoreSession wrapped = this.ref.get();
        
        // Ignore call if session closed
        if (wrapped == null)
            return ;
        
        wrapped.removeSessionListener(listener);
        
    }

}
