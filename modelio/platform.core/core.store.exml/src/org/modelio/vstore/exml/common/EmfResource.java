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

package org.modelio.vstore.exml.common;

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

/**
 * EMF {@link Resource} implementation for EXML repository.
 */
@objid ("a654c1bb-c064-11e1-b511-001ec947ccaf")
public class EmfResource implements Resource {
    @objid ("8f4156cb-e152-4b08-89c6-1fcab2bd8404")
    private final AbstractExmlRepository repo;

    /**
     * Initialize the EXML adapter.
     * @param abstractExmlRepository the EXML repository.
     */
    @objid ("a654c25f-c064-11e1-b511-001ec947ccaf")
    public EmfResource(AbstractExmlRepository abstractExmlRepository) {
        this.repo = abstractExmlRepository;
    }

    @objid ("a654c25e-c064-11e1-b511-001ec947ccaf")
    @Override
    public EList<Adapter> eAdapters() {
        return ECollections.emptyEList();
    }

    @objid ("a654c25d-c064-11e1-b511-001ec947ccaf")
    @Override
    public boolean eDeliver() {
        return false;
    }

    @objid ("a654c234-c064-11e1-b511-001ec947ccaf")
    @Override
    public void eSetDeliver(boolean deliver) {
        if (deliver) {
            throw new UnsupportedOperationException();
        }
    }

    @objid ("a654c233-c064-11e1-b511-001ec947ccaf")
    @Override
    public void eNotify(Notification notification) {
        // Do nothing
    }

    @objid ("a654c225-c064-11e1-b511-001ec947ccaf")
    @Override
    public ResourceSet getResourceSet() {
        return null;
    }

    @objid ("a654c224-c064-11e1-b511-001ec947ccaf")
    @Override
    public URI getURI() {
        return URI.createURI(this.repo.getURI().toString());
    }

    @objid ("a654c21a-c064-11e1-b511-001ec947ccaf")
    @Override
    public void setURI(URI uri) {
        throw new UnsupportedOperationException();
    }

    @objid ("a654c219-c064-11e1-b511-001ec947ccaf")
    @Override
    public long getTimeStamp() {
        return 0;
    }

    @objid ("a654c215-c064-11e1-b511-001ec947ccaf")
    @Override
    public void setTimeStamp(long timeStamp) {
        // do nothing
    }

    @objid ("a654c214-c064-11e1-b511-001ec947ccaf")
    @Override
    public EList<EObject> getContents() {
        throw new UnsupportedOperationException();
    }

    @objid ("a654c213-c064-11e1-b511-001ec947ccaf")
    @Override
    public TreeIterator<EObject> getAllContents() {
        return EcoreUtil.getAllContents(this, true);
    }

    /**
     * Returns the URI fragment that, when passed to getEObject will return the given object.
     * <p>
     * The returned fragment encoding will be "Metaclass,UUID".
     */
    @objid ("a654c212-c064-11e1-b511-001ec947ccaf")
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
    @objid ("a654c210-c064-11e1-b511-001ec947ccaf")
    @Override
    public EObject getEObject(String uriFragment) {
        String[] s = uriFragment.split(",");
        if (s.length != 2) {
            throw new IllegalArgumentException("Fragment must be encoded as 'Metaclass,UUID'.");
        }
        return this.repo.findById(this.repo.getModelLoaderProvider().getMetamodel().getMClass(s[0]), uriFragment);
    }

    @objid ("a654c20f-c064-11e1-b511-001ec947ccaf")
    @Override
    public void save(Map<?,?> options) throws IOException {
        this.repo.save((IModelioProgress) options.get(IModelioProgress.class));
    }

    @objid ("a654c20b-c064-11e1-b511-001ec947ccaf")
    @Override
    public void load(Map<?,?> options) throws IOException {
        // Do nothing, loading is automatic.
    }

    @objid ("a654c20a-c064-11e1-b511-001ec947ccaf")
    @Override
    public void save(OutputStream outputStream, Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException("'Save as' is not supported.");
    }

    @objid ("a654c205-c064-11e1-b511-001ec947ccaf")
    @Override
    public void load(InputStream inputStream, Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException("'Load from' is not supported.");
    }

    @objid ("a654c204-c064-11e1-b511-001ec947ccaf")
    @Override
    public boolean isTrackingModification() {
        // Modifications are always tracked by the repository object
        return true;
    }

    @objid ("a654c1fe-c064-11e1-b511-001ec947ccaf")
    @Override
    public void setTrackingModification(boolean isTrackingModification) {
        // do nothing
    }

    @objid ("a654c1f3-c064-11e1-b511-001ec947ccaf")
    @Override
    public boolean isModified() {
        return this.repo.isDirty();
    }

    @objid ("a654c1ec-c064-11e1-b511-001ec947ccaf")
    @Override
    public void setModified(boolean isModified) {
        // Do nothing, this is automatic.
    }

    @objid ("a654c1eb-c064-11e1-b511-001ec947ccaf")
    @Override
    public boolean isLoaded() {
        return this.repo.isOpen();
    }

    @objid ("a654c1ea-c064-11e1-b511-001ec947ccaf")
    @Override
    public void unload() {
        this.repo.close();
    }

    @objid ("a654c1e9-c064-11e1-b511-001ec947ccaf")
    @Override
    public void delete(Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException();
    }

    @objid ("a654c1e5-c064-11e1-b511-001ec947ccaf")
    @Override
    public EList<Diagnostic> getErrors() {
        return ECollections.emptyEList();
    }

    @objid ("a654c1e3-c064-11e1-b511-001ec947ccaf")
    @Override
    public EList<Diagnostic> getWarnings() {
        return ECollections.emptyEList();
    }

}
