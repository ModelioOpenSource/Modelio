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

package org.modelio.api.impl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.impl.model.change.ModelChangeEvent;
import org.modelio.api.impl.model.change.StatusChangeEvent;
import org.modelio.api.impl.transaction.TransactionWrapper;
import org.modelio.api.modelio.model.IMetamodelExtensions;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.modelio.model.IUmlModel;
import org.modelio.api.modelio.model.event.IModelChangeEvent;
import org.modelio.api.modelio.model.event.IModelChangeHandler;
import org.modelio.api.modelio.model.event.IModelChangeListener;
import org.modelio.api.modelio.model.event.IStatusChangeEvent;
import org.modelio.api.modelio.model.event.IStatusChangeListener;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.vcore.session.UnknownMetaclassException;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * Implementation of the {@link IModelingSession} interface.
 */
@objid ("cae6fe24-f4aa-424c-9d8b-1d11fb01eadd")
public class SharedModelingSession implements org.modelio.vcore.session.api.model.change.IModelChangeListener, IModelingSession, org.modelio.vcore.session.api.model.change.IModelChangeHandler, org.modelio.vcore.session.api.model.change.IStatusChangeListener {
    @objid ("59c7a663-11ce-481e-9ef5-3657868edfe8")
    private String name;

    @objid ("166cf6f0-2581-4c4f-8595-ba427e5b7e62")
    private List<IModelChangeHandler> modelChangeHandlers = null;

    @objid ("4ed33d78-845c-441b-88aa-119a43464271")
    private List<IModelChangeListener> modelChangeListeners = null;

    @objid ("124db96e-65cf-40c2-af7c-d2f67de0ea88")
    private ICoreSession session;

    @objid ("4fb073ad-3889-4ac8-b5ed-52a3c0a690ec")
    private List<IStatusChangeListener> statusChangeListeners = null;

    @objid ("57d2f05a-546b-40cb-bae3-468ba840bb86")
    private IUmlModel umlModel;

    @objid ("0cfb5cfa-3d35-4f57-8188-ef08e6916a14")
    private MetamodelExtensions metamodelExtensions;

    /**
     * @param openedProject the project
     * @param modelService a model service working on the project.
     */
    @objid ("f72f5713-f301-4dad-9c7e-9c4013836cfb")
    public SharedModelingSession(final GProject openedProject, final IMModelServices modelService) {
        this.session = openedProject.getSession();
        this.modelChangeListeners = new ArrayList<>();
        this.modelChangeHandlers = new ArrayList<>();
        this.statusChangeListeners = new ArrayList<>();
        
        // Create UmlModel delegation:
        this.umlModel = new UMLModel(openedProject, modelService, this.session.getModel());
        
        this.metamodelExtensions = new MetamodelExtensions(modelService);
        
        // Set this modeling session as core model change listener:
        this.session.getModelChangeSupport().addModelChangeListener(this);
        this.session.getModelChangeSupport().addModelChangeHandler(this);
        this.session.getModelChangeSupport().addStatusChangeListener(this);
    }

    @objid ("60763c1b-502d-477e-bfb1-7b0d9e378ad1")
    @Override
    public void addModelHandler(final IModelChangeHandler handler) {
        this.modelChangeHandlers.add(handler);
    }

    @objid ("0b607b1a-79d8-4e8a-9ac9-c048dc368be4")
    @Override
    public void addModelListener(final IModelChangeListener listener) {
        this.modelChangeListeners.add(listener);
    }

    @objid ("0629ec12-5448-40cd-bd14-86ce877c3a9a")
    @Override
    public void addStatusListener(final IStatusChangeListener listener) {
        this.statusChangeListeners.add(listener);
    }

    @objid ("cb36700b-8bdc-4170-acd3-e54d8685774f")
    @Override
    public ITransaction createTransaction(final String transactionName) {
        return new TransactionWrapper(this.session.getTransactionSupport().createTransaction(transactionName));
    }

    @objid ("65b799dd-5390-4a69-96ed-da4d93e3dafd")
    @Override
    public <T extends MObject> Collection<T> findByAtt(final Class<T> metaclass, final String att, final String value) {
        return this.session.getModel().findByAtt(metaclass, att, value, IModel.ISVALID);
    }

    @objid ("70bd49d9-e304-4dc8-aa5a-d39c3d11fe72")
    @Override
    public Collection<? extends MObject> findByAtt(final MClass metaclass, final String att, final String value) {
        return this.session.getModel().findByAtt(metaclass, att, value, IModel.ISVALID);
    }

    @objid ("cc20abe2-1d01-4a10-8a97-fa766e21b5f0")
    @Override
    public <T extends MObject> Collection<T> findByClass(final Class<T> metaclass) {
        return this.session.getModel().findByClass(metaclass, IModel.ISVALID);
    }

    @objid ("bac3ba39-bca7-4d26-828e-64059333b0e0")
    @Override
    public Collection<? extends MObject> findByClass(final MClass metaclass) {
        return this.session.getModel().findByClass(metaclass, IModel.ISVALID);
    }

    @objid ("70da10f2-a234-4e8d-83b7-b6d78bb1a012")
    @Override
    public <T extends MObject> T findElementById(final Class<T> metaclass, final String id) {
        try {
            return this.session.getModel().findById(metaclass, id, IModel.ISVALID);
        } catch (@SuppressWarnings ("unused") IllegalArgumentException e) {
            return null;
        }
    }

    @objid ("8f56cd02-569d-4b0c-89f6-1606ce62c63d")
    @Override
    public MObject findElementById(final MClass metaclass, final String id) {
        try {
            return this.session.getModel().findById(metaclass, id, IModel.ISVALID);
        } catch (@SuppressWarnings ("unused") IllegalArgumentException e) {
            return null;
        }
    }

    @objid ("bd0c2a72-72dd-4726-bbb1-473c467040a4")
    @Override
    public IMetamodelExtensions getMetamodelExtensions() {
        return this.metamodelExtensions;
    }

    @objid ("0b1483c3-2d44-4569-95e1-9d9b22a66e62")
    @Override
    public IUmlModel getModel() {
        return this.umlModel;
    }

    @objid ("eadc7e58-4cfe-42f3-96e8-5af9024726aa")
    @Override
    public String getName() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.name;
    }

    @objid ("59f766b4-26db-440d-8f12-0d29d3121722")
    @Override
    public void getRequirementModel() {
        // Empty
    }

    @objid ("53bace9c-4edb-49eb-9dae-bcdd4a3a5c4c")
    @Override
    public void handleModelChange(final org.modelio.vcore.session.api.model.change.IModelChangeEvent event) {
        IModelChangeEvent mdaEvent = new ModelChangeEvent(event);
        
        for (IModelChangeHandler listener : this.modelChangeHandlers) {
            listener.handleModelChange(this, mdaEvent);
        }
    }

    @objid ("72917866-ee90-47d0-a961-b3394c7b114a")
    @Override
    public void modelChanged(final org.modelio.vcore.session.api.model.change.IModelChangeEvent event) {
        IModelChangeEvent mdaEvent = new ModelChangeEvent(event);
        
        for (IModelChangeListener listener : this.modelChangeListeners) {
            listener.modelChanged(this, mdaEvent);
        }
    }

    @objid ("c6b0cc15-565c-4dd0-80d2-63422729ad28")
    @Override
    public void removeModelHandler(final IModelChangeHandler handler) {
        this.modelChangeHandlers.remove(handler);
    }

    @objid ("a5b8a35b-16e1-47d5-8fc3-a06ba853c7a1")
    @Override
    public void removeModelListener(final IModelChangeListener listener) {
        this.modelChangeListeners.remove(listener);
    }

    @objid ("1ef02b9c-d804-49b9-b6ca-95dd9ca58f62")
    @Override
    public void removeStatusListener(final IStatusChangeListener listener) {
        this.statusChangeListeners.remove(listener);
    }

    @objid ("62436df8-05cd-4805-8fb1-bd83a11bf9e5")
    @Override
    public void statusChanged(final org.modelio.vcore.session.api.model.change.IStatusChangeEvent event) {
        IStatusChangeEvent mdaEvent = new StatusChangeEvent(event);
        
        for (IStatusChangeListener listener : this.statusChangeListeners) {
            listener.statusChanged(this, mdaEvent);
        }
    }

    @objid ("057a052e-3a7c-4d21-8e24-198f9c21da8c")
    @Override
    protected void finalize() throws Throwable {
        // Remove this session from core model change listeners:
        this.session.getModelChangeSupport().removeModelChangeListener(this);
        this.session.getModelChangeSupport().removeModelChangeHandler(this);
        this.session.getModelChangeSupport().removeStatusChangeListener(this);
        super.finalize();
    }

    /**
     * Check if an element is part of a library, i.e. in a model component or an HTTP fragment.
     * 
     * @param element the element to check.
     * @return <code>true</code> if the element is part of a library.
     */
    @objid ("eb20b992-6e9e-4e3b-b63e-b5d71d4d64d5")
    public boolean isInLibrary(final MObject element) {
        MStatus status = element.getStatus();
        // Model components are libraries
        if (status.isRamc()) {
            return true;
        }
        // Modifiable elements are not libraries
        if (status.isModifiable()) {
            return false;
        }
        // Read only elements that are cms are not libraries
        return !status.isCmsManaged();
    }

    @objid ("478ca844-f16a-43cc-a1a3-7eb4ec9e1297")
    @Override
    public MObject findByRef(MRef ref) {
        try {
            return this.session.getModel().findByRef(ref);
        } catch (@SuppressWarnings ("unused") UnknownMetaclassException e) {
            return null;
        }
    }

}
