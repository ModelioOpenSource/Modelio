/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.api.modelio.model;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * ModelingSessionRegistry maintains an application scoped unique map between CoreSession instances and SharedModelingSession instances.
 * 
 * Registration and de-registration of sessions is carried out in org.modelio.api.impl.services.ModelioServices methods
 * 
 * @since 5.1
 */
@objid ("5c5f8b28-dc85-4606-876f-dacfa1ba1303")
public class ModelingSessionRegistry {
    @objid ("7bffaa03-ca1b-4e39-822f-2a6b80ac030f")
    private static Map<Integer, IModelingSession> sessions = new HashMap<>();

    @objid ("c0dfb69f-474e-4c1d-9642-1a5da27da343")
    public static IModelingSession getSession(MObject mObj) {
        return mObj != null ? getSession(CoreSession.getSession(mObj)) : null;
    }

    @objid ("820d7348-4ec1-4051-af42-f49a36a2d190")
    public static IModelingSession getSession(ICoreSession session) {
        return session != null ? sessions.get(session.hashCode()) : null;
    }

    /**
     * Empty private C'tor to avoid creation of instances
     */
    @objid ("8ca2c3c0-54b4-4566-9980-d28d03dfb444")
    private  ModelingSessionRegistry() {
        
    }

    /**
     * Register a coreSession/sharedSession association.
     * @param coreSessionId the coreSession identifier (must be unique)
     * @param mdaSession the shared session associated to the coreSession.
     */
    @objid ("23670ee1-e796-4eb7-8c71-62cca65a0dfc")
    public static void register(int coreSessionId, IModelingSession mdaSession) {
        sessions.putIfAbsent(coreSessionId, mdaSession);
    }

    /**
     * Remove a coreSession/sharedSession association.
     * @param coreSessionId the coreSession identifier (must be unique)
     */
    @objid ("b7d80f4c-386a-41c1-b1c1-ed3158429e57")
    public static void unregister(int coreSessionId) {
        sessions.remove(coreSessionId);
    }

}
