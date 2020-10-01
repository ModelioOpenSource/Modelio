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

package org.modelio.api.impl.app.picking;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.picking.IPickingClient;
import org.modelio.api.modelio.picking.IPickingProvider;
import org.modelio.api.modelio.picking.IPickingService;
import org.modelio.api.modelio.picking.IPickingSession;
import org.modelio.platform.core.picking.IModelioPickingService;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("c6b5d130-2f52-47ba-bf7c-f9ac201d0940")
public class PickingService implements IPickingService {
    @objid ("305ab18b-346b-4f57-86be-3061dee906ad")
    private List<PickingClientProxy> registeredClientProxies = new ArrayList<>();

    @objid ("54e21a7d-7930-4854-90bd-07b7d92c0ae4")
    private IModelioPickingService modelioPicking;

    @objid ("49787dd9-de57-4b26-ad1a-c05a25471db6")
    private static org.modelio.platform.core.picking.IPickingSession currentSession;

    @objid ("b9b2c1ba-dafa-4b49-86c6-9d750e511bd5")
    private List<IPickingProvider> registeredProviderProxies = new ArrayList<>();

    /**
     * be a provider
     */
    @objid ("288d39f1-d710-41ee-9fbf-74c9114e805c")
    @Override
    public void registerPickingProvider(final IPickingProvider pickingProvider) {
        this.registeredProviderProxies.add(pickingProvider);
    }

    @objid ("aef50c23-1623-483c-81dc-0189a0ba1f1a")
    @Override
    public void unregisterPickingProvider(final IPickingProvider pickingProvider) {
        this.registeredProviderProxies.remove(pickingProvider);
    }

    /**
     * be a client
     */
    @objid ("ac998525-dd5e-4241-9c61-836225629b23")
    @Override
    public IPickingSession startPickingSession(final IPickingClient client) {
        if (PickingService.currentSession == null) {
            PickingClientProxy proxyClient = new PickingClientProxy(client);
            PickingService.currentSession = this.modelioPicking.startPicking(proxyClient);
        }
        return new PickingSessionProxy(currentSession);
    }

    @objid ("e311ed24-b8f9-4c55-8b61-dd2d5fb7995c")
    @Override
    public void endPickingSession(final IPickingSession session) {
        if (session != null) {
            PickingSessionProxy sessionProxy = (PickingSessionProxy) session;
            if (sessionProxy.getSession() != null) {
                this.modelioPicking.stopPicking(sessionProxy.getSession());
            }
            PickingService.currentSession = null;
        }
    }

    @objid ("8da6ced7-df88-4dbb-b89e-490b48beac1c")
    public List<IPickingProvider> getPickingProvider() {
        return this.registeredProviderProxies;
    }

    @objid ("6868bba4-8c76-4e28-a3a9-3a6fc8702a3b")
    public PickingService(IModelioPickingService modelioPickin) {
        this.modelioPicking = modelioPickin;
    }

    @objid ("459bfb4d-5b42-44c6-af4f-196971a528fc")
    static class PickingClientProxy implements org.modelio.platform.core.picking.IPickingClient {
        @objid ("8e766d0d-5297-4207-a684-467098000cb3")
        private IPickingClient client;

        @objid ("00f54bad-cbf9-4a24-a43d-91ba538de859")
        public PickingClientProxy(IPickingClient client) {
            this.client = client;
        }

        @objid ("381bf268-f8c7-4fa8-9b27-191bdc40a339")
        @Override
        public boolean hover(MObject target) {
            return this.client.acceptElement(target);
        }

        @objid ("1cd0dcd6-741a-4788-8456-fe1f89cec4f9")
        @Override
        public boolean pick(MObject target) {
            return this.client.setElement(target);
        }

        @objid ("b1a0ec1a-a5a2-4ae7-9db9-55bbf88783b2")
        @Override
        public void abort() {
            this.client.pickingAborted();
        }

    }

}
