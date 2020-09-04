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

package org.modelio.vcore.session.impl.storage.serialized;

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
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * EMF adapter for {@link SerializedRepository}.
 */
@objid ("9c64a8f0-c031-11e1-b511-001ec947ccaf")
class EmfResource implements Resource {
    @objid ("ddba0d5a-c063-11e1-b511-001ec947ccaf")
    private SerializedRepository repo;

    @objid ("ddba0d5b-c063-11e1-b511-001ec947ccaf")
    public EmfResource(SerializedRepository serializedRepository) {
        this.repo = serializedRepository;
    }

    @objid ("ddba0d5e-c063-11e1-b511-001ec947ccaf")
    @Override
    public EList<Adapter> eAdapters() {
        return ECollections.emptyEList();
    }

    @objid ("ddba0d65-c063-11e1-b511-001ec947ccaf")
    @Override
    public boolean eDeliver() {
        return false;
    }

    @objid ("ddba0d6a-c063-11e1-b511-001ec947ccaf")
    @Override
    public void eSetDeliver(boolean deliver) {
        if (deliver == true) {
            throw new UnsupportedOperationException();
        }
    }

    @objid ("ddbc6fb2-c063-11e1-b511-001ec947ccaf")
    @Override
    public void eNotify(Notification notification) {
        // Do nothing
    }

    @objid ("ddbc6fb6-c063-11e1-b511-001ec947ccaf")
    @Override
    public ResourceSet getResourceSet() {
        return null;
    }

    @objid ("ddbc6fbb-c063-11e1-b511-001ec947ccaf")
    @Override
    public URI getURI() {
        return URI.createFileURI(this.repo.getPath());
    }

    @objid ("ddbc6fc0-c063-11e1-b511-001ec947ccaf")
    @Override
    public void setURI(URI uri) {
        throw new UnsupportedOperationException();
    }

    @objid ("ddbc6fc4-c063-11e1-b511-001ec947ccaf")
    @Override
    public long getTimeStamp() {
        return 0;
    }

    @objid ("ddbc6fc9-c063-11e1-b511-001ec947ccaf")
    @Override
    public void setTimeStamp(long timeStamp) {
        // do nothing
    }

    @objid ("ddbc6fcd-c063-11e1-b511-001ec947ccaf")
    @Override
    public EList<EObject> getContents() {
        throw new UnsupportedOperationException();
    }

    @objid ("ddbc6fd3-c063-11e1-b511-001ec947ccaf")
    @Override
    public TreeIterator<EObject> getAllContents() {
        return EcoreUtil.getAllContents(this, true);
    }

    /**
     * Returns the URI fragment that, when passed to getEObject will return the given object.
     * <p>
     * The returned fragment encoding will be "Metaclass,UUID".
     */
    @objid ("ddbc6fda-c063-11e1-b511-001ec947ccaf")
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
    @objid ("ddbc6fe0-c063-11e1-b511-001ec947ccaf")
    @Override
    public EObject getEObject(String uriFragment) {
        String[] s = uriFragment.split(",");
        if (s.length != 2) {
            throw new IllegalArgumentException("Fragment must be encoded as 'Metaclass,UUID'.");
        }
        
        SmClass mclass = this.repo.getMetamodel().getMClass(s[0]);
        return this.repo.findById(mclass, uriFragment);
    }

    @objid ("ddbc6fe7-c063-11e1-b511-001ec947ccaf")
    @Override
    public void save(Map<?,?> options) throws IOException {
        this.repo.save((IModelioProgress) options.get(IModelioProgress.class));
    }

    @objid ("ddbc6fee-c063-11e1-b511-001ec947ccaf")
    @Override
    public void load(Map<?,?> options) throws IOException {
        // Do nothing, loading is automatic.
    }

    @objid ("ddbc6ff5-c063-11e1-b511-001ec947ccaf")
    @Override
    public void save(OutputStream outputStream, Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException("'Save as' is not supported.");
    }

    @objid ("ddbc6ffd-c063-11e1-b511-001ec947ccaf")
    @Override
    public void load(InputStream inputStream, Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException("'Load from' is not supported.");
    }

    @objid ("ddbc7005-c063-11e1-b511-001ec947ccaf")
    @Override
    public boolean isTrackingModification() {
        // Modifications are always tracked by the repository object
        return true;
    }

    @objid ("ddbc700a-c063-11e1-b511-001ec947ccaf")
    @Override
    public void setTrackingModification(boolean isTrackingModification) {
        // do nothing
    }

    @objid ("ddbc700e-c063-11e1-b511-001ec947ccaf")
    @Override
    public boolean isModified() {
        return this.repo.isDirty();
    }

    @objid ("ddbed20d-c063-11e1-b511-001ec947ccaf")
    @Override
    public void setModified(boolean isModified) {
        // Do nothing, this is automatic.
    }

    @objid ("ddbed211-c063-11e1-b511-001ec947ccaf")
    @Override
    public boolean isLoaded() {
        return this.repo.isOpen();
    }

    @objid ("ddbed216-c063-11e1-b511-001ec947ccaf")
    @Override
    public void unload() {
        this.repo.close();
    }

    @objid ("ddbed219-c063-11e1-b511-001ec947ccaf")
    @Override
    public void delete(Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException();
    }

    @objid ("ddbed220-c063-11e1-b511-001ec947ccaf")
    @Override
    public EList<Diagnostic> getErrors() {
        return ECollections.emptyEList();
    }

    @objid ("ddbed227-c063-11e1-b511-001ec947ccaf")
    @Override
    public EList<Diagnostic> getWarnings() {
        return ECollections.emptyEList();
    }

}
