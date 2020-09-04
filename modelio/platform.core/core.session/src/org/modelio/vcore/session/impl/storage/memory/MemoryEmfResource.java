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

package org.modelio.vcore.session.impl.storage.memory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vcore.smkernel.SmObjectImpl;

@objid ("605b766f-2d7d-11e2-8aaa-001ec947ccaf")
class MemoryEmfResource implements Resource {
    @objid ("605dd8f4-2d7d-11e2-8aaa-001ec947ccaf")
    private MemoryRepository repo;

    @objid ("605dd8f5-2d7d-11e2-8aaa-001ec947ccaf")
    public MemoryEmfResource(MemoryRepository repository) {
        this.repo = repository;
    }

    @objid ("605dd8f6-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public EList<Adapter> eAdapters() {
        return ECollections.emptyEList();
    }

    @objid ("605dd8f7-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public boolean eDeliver() {
        return false;
    }

    @objid ("605dd8f8-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void eSetDeliver(boolean deliver) {
        if (deliver == true) {
            throw new UnsupportedOperationException();
        }
    }

    @objid ("605dd8f9-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void eNotify(Notification notification) {
        // Do nothing
    }

    @objid ("605dd8fa-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public ResourceSet getResourceSet() {
        return null;
    }

    @objid ("605dd8fb-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public URI getURI() {
        return URI.createGenericURI("memory", this.toString(), null);
    }

    @objid ("605dd8fc-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void setURI(URI uri) {
        throw new UnsupportedOperationException();
    }

    @objid ("605dd8fd-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public long getTimeStamp() {
        return 0;
    }

    @objid ("605dd8fe-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void setTimeStamp(long timeStamp) {
        // do nothing
    }

    @objid ("605dd8ff-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public EList<EObject> getContents() {
        throw new UnsupportedOperationException();
    }

    @objid ("605dd900-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public TreeIterator<EObject> getAllContents() {
        return EcoreUtil.getAllContents(this, true);
    }

    /**
     * Returns the URI fragment that, when passed to getEObject will return the given object.
     * <p>
     * The returned fragment encoding will be "Metaclass,UUID".
     */
    @objid ("605dd901-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public String getURIFragment(EObject eObject) {
        if (eObject instanceof SmObjectImpl) {
            SmObjectImpl smObj = (SmObjectImpl) eObject;
            return smObj.getClassOf().getName()+","+smObj.getUuid().toString();
        }
        
        throw new IllegalArgumentException("object is not a SmObjectImpl");
    }

    /**
     * Returns the resolved object for the given URI fragment.
     * <p>
     * The fragment encoding must be "Metaclass,UUID".
     */
    @objid ("605dd902-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public EObject getEObject(String uriFragment) {
        String[] s = uriFragment.split(",");
        if (s.length != 2) {
            throw new IllegalArgumentException("Fragment must be encoded as 'Metaclass,UUID'.");
        }
        return this.repo.findById(this.repo.getMetaclass(s[0]), uriFragment);
    }

    @objid ("605dd903-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void save(Map<?,?> options) throws IOException {
        this.repo.save((IModelioProgress) options.get(IModelioProgress.class));
    }

    @objid ("605dd904-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void load(Map<?,?> options) throws IOException {
        // Do nothing, loading is automatic.
    }

    @objid ("605dd905-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void save(OutputStream outputStream, Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException("'Save as' is not supported.");
    }

    @objid ("605dd906-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void load(InputStream inputStream, Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException("'Load from' is not supported.");
    }

    @objid ("605dd907-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public boolean isTrackingModification() {
        // Modifications are always tracked by the repository object
        return true;
    }

    @objid ("605dd908-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void setTrackingModification(boolean isTrackingModification) {
        // do nothing
    }

    @objid ("605dd909-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public boolean isModified() {
        return false;
    }

    @objid ("605dd90a-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void setModified(boolean isModified) {
        // Do nothing, this is automatic.
    }

    @objid ("605dd90b-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public boolean isLoaded() {
        return this.repo.isOpen();
    }

    @objid ("605dd90c-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void unload() {
        this.repo.close();
    }

    @objid ("605dd90d-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public void delete(Map<?,?> options) throws IOException {
        // nothing to do
    }

    @objid ("605dd90e-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public EList<Diagnostic> getErrors() {
        return ECollections.emptyEList();
    }

    @objid ("605dd90f-2d7d-11e2-8aaa-001ec947ccaf")
    @Override
    public EList<Diagnostic> getWarnings() {
        return ECollections.emptyEList();
    }

}
