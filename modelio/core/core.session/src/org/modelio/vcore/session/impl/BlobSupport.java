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

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.blob.IBlobChangeEvent;
import org.modelio.vcore.session.api.blob.IBlobChangeListener;
import org.modelio.vcore.session.api.blob.IBlobProvider;
import org.modelio.vcore.session.api.blob.IBlobSupport;
import org.modelio.vcore.session.api.repository.IRepository;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of {@link IBlobSupport}.
 */
@objid ("9a724767-2ca9-4756-a86d-cfa61c12e301")
public class BlobSupport implements IBlobSupport {
    @objid ("01c54bcf-20f2-464b-9aa6-2898d88aa182")
    private Collection<IBlobProvider> providers = new CopyOnWriteArrayList<>();

    @objid ("de4c1af6-76f0-4727-8783-8aec4060a0bd")
    private Collection<IBlobChangeListener> listeners = new CopyOnWriteArrayList<>();

    @objid ("5abd80e3-6813-4ca0-82cc-5f9c363dcb7d")
    @Override
    public void addBlobChangeListener(IBlobChangeListener listener) {
        this.listeners.add(listener);
    }

    @objid ("2c98b5c1-aead-49e6-afb5-62d82c7f1bbe")
    @Override
    public void addBlobProvider(IBlobProvider provider) {
        this.providers.add(provider);
    }

    @objid ("81eda83d-c662-4622-b812-176da102d682")
    @Override
    public Collection<String> getRelatedBlobs(MObject obj) {
        Collection<String> ret = new ArrayList<>();
        
        for(IBlobProvider p : this.providers) {
            ret.addAll(p.getRelatedBlobs(obj));
        }
        return ret;
    }

    @objid ("a2ecf3c1-84ec-466c-a11b-32a66a21c9ba")
    @Override
    public void fireBlobsChanged(IBlobChangeEvent event) {
        for(IBlobChangeListener p : this.listeners) {
            p.blobsChanged(event);
        }
        
    }

    @objid ("91b7d44a-4c53-47c7-99a1-7683052f1354")
    @Override
    public void fireObjectCopied(MObject from, MObject to) {
        IRepository fromRepo = CoreSession.getSession(from).getRepository(from);
        IRepository toRepo = CoreSession.getSession(to).getRepository(to);
        for(IBlobProvider p : this.providers) {
            p.objectCopied(from, fromRepo, to, toRepo);
        }
        
    }

    @objid ("a448037d-3616-4193-a088-885a7b39fcef")
    @Override
    public void removeBlobChangeListener(IBlobChangeListener listener) {
        this.listeners.remove(listener);
    }

    @objid ("9a352046-845e-4163-8c55-5b514aa01f04")
    @Override
    public void removeBlobProvider(IBlobProvider provider) {
        this.providers.remove(provider);
    }

    @objid ("b6d7a867-e980-471c-86e2-40af09620b63")
    @Override
    public void fireObjectsMoved(Collection<? extends MObject> objs, IRepository fromRepo, IRepository toRepo) {
        for(IBlobProvider p : this.providers) {
            p.objectsMoved(objs, fromRepo, toRepo);
        }
        
    }

}
