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

package org.modelio.vstore.jdbm7;

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

@objid ("0c968302-a14b-48ac-a7a7-9225c88fd488")
class JdbmEmfResource implements Resource {
    @objid ("420559e1-b8fa-4d53-b44e-2b5ad3bc7b49")
    private JdbmRepository repo;

    @objid ("6de56417-f7bb-47ce-921a-3b2fbc9c457c")
    public JdbmEmfResource(JdbmRepository repository) {
        this.repo = repository;
    }

    @objid ("7bfeed64-3362-46b1-9652-964de4173900")
    @Override
    public EList<Adapter> eAdapters() {
        return ECollections.emptyEList();
    }

    @objid ("13a7212b-b5fc-424f-8714-21a495edef45")
    @Override
    public boolean eDeliver() {
        return false;
    }

    @objid ("2190ef3c-b508-4782-98a0-161f14934c42")
    @Override
    public void eSetDeliver(boolean deliver) {
        if (deliver == true) {
            throw new UnsupportedOperationException();
        }
    }

    @objid ("1d4bb515-dda4-4057-8fb7-d71881663567")
    @Override
    public void eNotify(Notification notification) {
        // Do nothing
    }

    @objid ("84e097eb-f8b1-4e7c-8ad2-8cc995bc3684")
    @Override
    public ResourceSet getResourceSet() {
        return null;
    }

    @objid ("4ddc38ea-fa56-4c89-a110-b858ff24b71e")
    @Override
    public URI getURI() {
        return URI.createGenericURI("memory", this.toString(), null);
    }

    @objid ("dfb6c1a0-dd0e-4d65-8fe1-99bbf35a9006")
    @Override
    public void setURI(URI uri) {
        throw new UnsupportedOperationException();
    }

    @objid ("b5fa7764-bd63-4acb-8291-2fa5ee524416")
    @Override
    public long getTimeStamp() {
        return 0;
    }

    @objid ("e07a15e0-830b-46da-b085-58619cb321f8")
    @Override
    public void setTimeStamp(long timeStamp) {
        // do nothing
    }

    @objid ("386fb6f1-ade6-4b7a-961d-12028df26728")
    @Override
    public EList<EObject> getContents() {
        throw new UnsupportedOperationException();
    }

    @objid ("00d0a99f-2274-4c76-b7a6-98425c49a84c")
    @Override
    public TreeIterator<EObject> getAllContents() {
        return EcoreUtil.getAllContents(this, true);
    }

    /**
     * Returns the URI fragment that, when passed to getEObject will return the given object.
     * <p>
     * The returned fragment encoding will be "Metaclass,UUID".
     */
    @objid ("64f3233a-b94b-4287-9b0f-a8e2fd218601")
    @Override
    public String getURIFragment(EObject eObject) {
        if (eObject instanceof SmObjectImpl) {
            SmObjectImpl smObj = (SmObjectImpl) eObject;
            return smObj.getClassOf().getName()+","+smObj.getUuid().toString();
        }
        
        throw new IllegalArgumentException(eObject+" is not a SmObjectImpl");
    }

    /**
     * Returns the resolved object for the given URI fragment.
     * <p>
     * The fragment encoding must be "Metaclass,UUID".
     */
    @objid ("caae9c8e-3929-455e-96f2-537c24fb8ec9")
    @Override
    public EObject getEObject(String uriFragment) {
        String[] s = uriFragment.split(",");
        if (s.length != 2) {
            throw new IllegalArgumentException("'"+uriFragment+"' fragment URI is not encoded as 'Metaclass,UUID'.");
        }
        return this.repo.findById(this.repo.getMetamodel().getMClass(s[0]), uriFragment);
    }

    @objid ("fe69282b-6d16-4f94-a0f6-b41262782e48")
    @Override
    public void save(Map<?,?> options) throws IOException {
        this.repo.save((IModelioProgress) options.get(IModelioProgress.class));
    }

    @objid ("80c67326-8864-4a76-aad9-5c3f66376bcf")
    @Override
    public void load(Map<?,?> options) throws IOException {
        // Do nothing, loading is automatic.
    }

    @objid ("2d8a0603-1437-4bb6-861f-ddab8bcd9e56")
    @Override
    public void save(OutputStream outputStream, Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException("'Save as' is not supported.");
    }

    @objid ("9fcb34a4-326f-4b94-aa77-88e94720d329")
    @Override
    public void load(InputStream inputStream, Map<?,?> options) throws IOException {
        throw new UnsupportedOperationException("'Load from' is not supported.");
    }

    @objid ("a30c3f56-68a0-4d33-b6c8-a3aae78d0815")
    @Override
    public boolean isTrackingModification() {
        // Modifications are always tracked by the repository object
        return true;
    }

    @objid ("ccdf92ab-a61b-43d6-8bf3-00252cbd7443")
    @Override
    public void setTrackingModification(boolean isTrackingModification) {
        // do nothing
    }

    @objid ("707d75f1-fd6c-44f7-869c-f1f3eee8ac18")
    @Override
    public boolean isModified() {
        return false;
    }

    @objid ("4417d609-cd70-4cdc-956d-fd50dccde956")
    @Override
    public void setModified(boolean isModified) {
        // Do nothing, this is automatic.
    }

    @objid ("beaf4941-23d9-42ee-8d00-cc7722ea74b1")
    @Override
    public boolean isLoaded() {
        return this.repo.isOpen();
    }

    @objid ("41bece29-9db2-417a-9a4d-243834b5377f")
    @Override
    public void unload() {
        this.repo.close();
    }

    @objid ("a080007c-d8c3-48d9-b10b-bf02ae2f11e2")
    @Override
    public void delete(Map<?,?> options) throws IOException {
        // nothing to do
    }

    @objid ("b6d80485-a381-4236-94c2-88c7f30f1cd0")
    @Override
    public EList<Diagnostic> getErrors() {
        return ECollections.emptyEList();
    }

    @objid ("da84bba1-ee71-471e-8437-3ae0c5a5bac6")
    @Override
    public EList<Diagnostic> getWarnings() {
        return ECollections.emptyEList();
    }

}
