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
package org.modelio.platform.model.ui.nattable.viewer.model;

import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceStore;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.gproject.core.IGProject;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.core.picking.IModelioPickingService;
import org.modelio.platform.core.project.ICurrentProjectService;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of {@link INatTableViewerContext}, to use only if you have no {@link ICurrentProjectService} at hand (eg. the diff/merge dialog).
 * 
 * @since Valkyrie 3.8
 */
@objid ("f578bd0e-e346-4e65-97ab-5fe6232b1628")
public class NatTableViewerContext implements INatTableViewerContext {
    
    @mdl.prop
    @objid ("3d6be7de-2fb1-4d99-8cd5-f0ab409da154")
    public final IActivationService activationService;

    @mdl.propgetter
    public IActivationService getActivationService() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.activationService;
    }

    
    @mdl.prop
    @objid ("bfeeea0c-17d5-4052-9b29-532d52276722")
    public final IMModelServices modelService;

    @mdl.propgetter
    public IMModelServices getModelService() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.modelService;
    }

    
    @mdl.prop
    @objid ("d7882aa3-ee09-40b2-9c82-6d89912eebb5")
    public final IModelioNavigationService navigationService;

    @mdl.propgetter
    public IModelioNavigationService getNavigationService() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.navigationService;
    }

    
    @mdl.prop
    @objid ("3c9f7766-d3f6-4021-82c1-f81c2a499068")
    public final IModelioPickingService pickingService;

    @mdl.propgetter
    public IModelioPickingService getPickingService() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.pickingService;
    }

    
    @mdl.prop
    @objid ("216d4e56-4a25-40fa-86f1-6120e6fcc633")
    public final ICurrentProjectService projectService;

    
    @mdl.prop
    @objid ("bbad667c-0671-4a77-ae8f-d58925f71184")
    public IMdaExpert mdaExpert;

    @mdl.propgetter
    public IMdaExpert getMdaExpert() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.mdaExpert;
    }

    /**
     * Constructor to use only if you have no {@link ICurrentProjectService} at hand (eg. the diff/merge dialog).
     * @param session the core session
     * @param modelService the model service
     * @param activationService the activation service to open the edition dialog
     * @param navigationService the navigation service
     * @param pickingService the picking service
     */
    @objid ("cd8c4245-bc68-4aa4-9ea7-2d2abb19cf13")
    public  NatTableViewerContext(ICoreSession session, IMModelServices modelService, IActivationService activationService, IModelioNavigationService navigationService, IModelioPickingService pickingService) {
        super();
        
        this.projectService = new StubProjectService(session);
        this.modelService = modelService;
        this.activationService = activationService;
        this.navigationService = navigationService;
        this.pickingService = pickingService;
        this.mdaExpert = YesMdaExpert.INSTANCE;
        
    }

    @objid ("53251e82-ec3e-43d1-9817-accbb1cf6f87")
    @Override
    public IPreferenceStore getProjectPreferences(String nodeId) {
        return this.projectService.getProjectPreferences(nodeId);
    }

    @objid ("1ae8aab8-dbea-4bf7-bbe4-8144913c88ab")
    @Override
    public ICoreSession getSession() {
        return this.projectService.getSession();
    }

    @objid ("73c1c396-71ab-4b15-9b6f-082512208b00")
    public static NatTableViewerContext empty() {
        return new NatTableViewerContext(null, null, null, null, null);
    }

    @objid ("131e98bc-51ca-40c6-9ba0-81cbeaa172d0")
    @Override
    public <T> T getService(Class<T> clazz) {
        return null;
    }

    /**
     * Stub project service used when none is available (eg. in the diff/merge dialog)
     */
    @objid ("9556f894-7f4c-41df-9f99-1d8c2a9ea481")
    private static class StubProjectService implements ICurrentProjectService {
        @objid ("0b8b3123-3de8-411d-b41c-5c4e78cc0cf1")
        private final ICoreSession coreSession;

        @objid ("93372808-f267-4414-8284-120f4ff6496d")
        private final PreferenceStore preferenceStore;

        @objid ("c72a0611-d3b4-4aee-9c50-35acf7aa885c")
        public  StubProjectService(ICoreSession session) {
            this.coreSession = session;
            this.preferenceStore = new PreferenceStore();
            
        }

        @objid ("c76a9965-4501-41fc-953b-280763cff8bf")
        @Override
        public String getName() {
            return getClass().getSimpleName();
        }

        @objid ("4e01ffea-284b-4e3a-aa02-5b4d1d4d207b")
        @Override
        public IGProject getOpenedProject() {
            return null;
        }

        @objid ("168d3ca8-04c3-4ca8-84df-db3a25abb129")
        @Override
        public IPreferenceStore getProjectPreferences(String nodeId) {
            return this.preferenceStore;
        }

        @objid ("88ea5396-b8ca-48f9-ba74-1a420a0b62ac")
        @Override
        public ICoreSession getSession() {
            return this.coreSession;
        }

        @objid ("fab85483-a586-40b7-b281-837cf656902e")
        @Override
        public IPreferenceStore getStatePreferences() {
            return this.preferenceStore;
        }

        @objid ("e1446250-7319-4814-8271-5c372c6cfeae")
        @Override
        public boolean isDirty() {
            return this.coreSession.isDirty();
        }

        @objid ("b179969f-98c4-4264-a903-fb4eb620cf32")
        @Override
        public Path getWorkspace() {
            throw new UnsupportedOperationException();
        }

    }

    @objid ("e501c21b-ae1f-4307-af57-9ab54774adc1")
    private static class YesMdaExpert implements IMdaExpert {
        @objid ("86686415-f94b-4350-b14e-a30b8c93abe0")
        public static final YesMdaExpert INSTANCE = new YesMdaExpert();

        @objid ("b73c0f93-09e9-440d-aa55-62ae9a3ec34f")
        @Override
        public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject from, MObject to) {
            return true;
        }

        @objid ("5f8fb7c5-65a0-420f-af04-287815aaad13")
        @Override
        public boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass, MClass toMetaclass) {
            return true;
        }

        @objid ("b5ce70a4-88b1-4afb-85f1-4dde3b9fd666")
        @Override
        public boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass) {
            return true;
        }

        @objid ("5881bbcc-8a10-40b2-bf93-4941bfd7556b")
        @Override
        public boolean canSource(Stereotype linkStereotype, MObject linkMetaclass, MObject from) {
            return true;
        }

        @objid ("220d482d-2d72-4042-bea2-fc1e972eeb30")
        @Override
        public boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass) {
            return true;
        }

        @objid ("940e9ec4-e826-4694-b4e0-3e7405e18dbf")
        @Override
        public boolean canTarget(Stereotype linkStereotype, MObject linkMetaclass, MObject to) {
            return true;
        }

        @objid ("0aafb327-78d1-458f-a794-a5a8cc748f7a")
        @Override
        public boolean canLink(ElementScope linkScope, ElementScope fromScope, ElementScope toScope) {
            return true;
        }

        @objid ("b12ce057-5691-47f6-8f83-7a28f1bec35b")
        @Override
        public boolean canSource(ElementScope linkScope, ElementScope fromScope) {
            return true;
        }

        @objid ("c2335a84-4c37-450c-b3fd-28fd54e2fd62")
        @Override
        public boolean canSource(ElementScope linkScope, MObject from) {
            return true;
        }

        @objid ("4e41f4ff-59a0-4e10-8b52-b452282a1451")
        @Override
        public boolean canTarget(ElementScope linkScope, ElementScope toScope) {
            return true;
        }

    }

}
