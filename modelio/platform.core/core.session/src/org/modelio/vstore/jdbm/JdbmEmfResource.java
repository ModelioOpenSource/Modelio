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

package org.modelio.vstore.jdbm;

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

@objid ("988bcf80-cbca-4ce5-9fae-382146b6fb77")
class JdbmEmfResource implements Resource {
    @objid ("e596df35-67b9-45ac-a1ec-d2f7a16009bb")
    private JdbmRepository repo;

    @objid ("54ec8e31-da47-413e-ab14-ba71865c83f5")
    public JdbmEmfResource(JdbmRepository repository) {
        this.repo = repository;
    }

    @objid ("429e31cf-c1f4-4e6c-b7f5-5f038d3362f7")
    @Override
    public EList<Adapter> eAdapters() {
        return ECollections.emptyEList();
    }

    @objid ("dd444258-b53d-43a9-9057-fe15c16c25b0")
    @Override
    public boolean eDeliver() {
        return false;
    }

    @objid ("69aea08f-8400-416c-b87d-f8904a8b1158")
    @Override
    public void eSetDeliver(boolean deliver) {
        if (deliver == true) {
            throw new UnsupportedOperationException();
        }
    }

    @objid ("e986bc6c-0d18-4c17-bae3-3d88de1f947e")
    @Override
    public void eNotify(Notification notification) {
        // Do nothing
    }

    @objid ("963b9787-5eaf-4b12-ad73-9872c01adfb4")
    @Override
    public ResourceSet getResourceSet() {
        return null;
    }

    @objid ("a196b704-2e76-4ff0-8755-d320c8cad539")
    @Override
    public URI getURI() {
        return URI.createGenericURI("memory", toString(), null);
    }

    @objid ("093407d3-0479-4bde-9a02-46e07ef8508a")
    @Override
    public void setURI(URI uri) {
        throw new UnsupportedOperationException();
    }

    @objid ("31726987-225d-4e45-a836-86f374d5e47a")
    @Override
    public long getTimeStamp() {
        return 0;
    }

    @objid ("beb696dd-81e6-47ed-bd49-59b82cb77b72")
    @Override
    public void setTimeStamp(long timeStamp) {
        // do nothing
    }

    @objid ("b4d2735a-ee53-4e30-adf8-74ec3698ba6a")
    @Override
    public EList<EObject> getContents() {
        throw new UnsupportedOperationException();
    }

    @objid ("854dc55d-89fb-4fc6-b8c7-660f7a3432a0")
    @Override
    public TreeIterator<EObject> getAllContents() {
        return EcoreUtil.getAllContents(this, true);
    }

    /**
     * Returns the URI fragment that, when passed to getEObject will return the given object.
     * <p>
     * The returned fragment encoding will be "Metaclass,UUID".
     */
    @objid ("a6d0821b-0a4f-4f80-9968-75a0154d849e")
    @Override
    public String getURIFragment(EObject eObject) {
        SmObjectImpl smObj = (SmObjectImpl) eObject;
        return smObj.getClassOf().getName()+","+smObj.getUuid();
    }

    /**
     * Returns the resolved object for the given URI fragment.
     * <p>
     * The fragment encoding must be "Metaclass,UUID".
     */
    @objid ("b79b055b-004f-4268-8f6a-b2188b82d4c2")
    @Override
    public EObject getEObject(String uriFragment) {
        String[] s = uriFragment.split(",");
        if (s.length != 2) {
            throw new IllegalArgumentException("'"+uriFragment+"' fragment URI is not encoded as 'Metaclass,UUID'.");
        }
        return this.repo.findById(this.repo.getMetamodel().getMClass(s[0]), uriFragment);
    }

    @objid ("5e180217-a13c-4283-be31-ed8963d3919d")
    @Override
    public void save(Map<?,?> options) throws IOException {
        this.repo.save((IModelioProgress) options.get(IModelioProgress.class));
    }

    @objid ("f713b457-0164-439b-8480-d88b1c967462")
    @Override
    public void load(Map<?,?> options) throws IOException {
        // Do nothing, loading is automatic.
    }

    @objid ("c99b290e-07e1-4d79-85cf-6b96ae267fd7")
    @Override
    public void save(OutputStream outputStream, Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException("'Save as' is not supported.");
    }

    @objid ("00c99b2b-a587-4c27-819e-741a315661ba")
    @Override
    public void load(InputStream inputStream, Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException("'Load from' is not supported.");
    }

    @objid ("3215f580-c613-4a7f-b1ce-d53c27df7ccc")
    @Override
    public boolean isTrackingModification() {
        // Modifications are always tracked by the repository object
        return true;
    }

    @objid ("191ef0af-b5b3-4ef7-9b15-4b3de3e2f021")
    @Override
    public void setTrackingModification(boolean isTrackingModification) {
        // do nothing
    }

    @objid ("999f6c43-8004-4bce-9400-52d551ffa356")
    @Override
    public boolean isModified() {
        return false;
    }

    @objid ("af577321-2bcf-47a2-9695-56e6a40062a7")
    @Override
    public void setModified(boolean isModified) {
        // Do nothing, this is automatic.
    }

    @objid ("4311d98a-abdd-4287-aa7e-2a56fd174af9")
    @Override
    public boolean isLoaded() {
        return this.repo.isOpen();
    }

    @objid ("a9aa737d-eedd-4fd9-96a6-f1fcb5f1e33f")
    @Override
    public void unload() {
        this.repo.close();
    }

    @objid ("01d9376e-b8b5-4f2c-9073-fa23bdba8c4f")
    @Override
    public void delete(Map<?,?> options) throws IOException {
        // nothing to do
    }

    @objid ("0b560596-7fda-4793-ab0a-382aeef0caaf")
    @Override
    public EList<Diagnostic> getErrors() {
        return ECollections.emptyEList();
    }

    @objid ("a7867b81-c60d-4d18-b8b6-b871b0b27f35")
    @Override
    public EList<Diagnostic> getWarnings() {
        return ECollections.emptyEList();
    }

}
