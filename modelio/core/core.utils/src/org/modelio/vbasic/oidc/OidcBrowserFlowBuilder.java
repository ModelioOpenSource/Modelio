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
package org.modelio.vbasic.oidc;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;
import org.modelio.vbasic.oidc.flows.OidcBrowserFlow;
import org.modelio.vbasic.oidc.flows.OidcRefreshTokenFlow;

/**
 * Builder of {@link OidcBrowserFlow}.
 * 
 * <h2>Usage</h2>
 * 
 * Call {@link OidcAuthentications#browserFlow(IOidcWebBrowser)} ,
 * then call withXxxx(...) methods and finish with {@link #build()}.
 */
@objid ("8a0316a8-a5ee-4647-90f8-8ca659545461")
public class OidcBrowserFlowBuilder {
    @objid ("4a6fbba3-ed0c-47a9-8dd0-6d4e8114792c")
    private String secret;

    @objid ("20eb9b93-0530-47e5-900b-816560f853aa")
    private String loginHint;

    @objid ("880e22b2-1928-475c-97d4-7ea4af5f186e")
    private String clientId;

    @objid ("36643462-d5b4-4fd9-95c9-92bb80043727")
    private final OIDCProviderMetadata providerMetadata;

    @objid ("023351f9-0b77-464c-8740-60e6eeb1cd7d")
    private IOidcAuthenticationFlow previousAuth;

    @objid ("050ca16f-30f3-4b4b-9b26-0067c018ad66")
    private final IOidcWebBrowser webBrowser;

    /**
     * Package private constructor.
     * @param providerMetadata OIDC provider metadatas
     * @param browser the web browser to use
     */
    @objid ("81659e06-be8e-4ebf-a64f-21faf1136c8e")
     OidcBrowserFlowBuilder(OIDCProviderMetadata providerMetadata, IOidcWebBrowser browser) {
        this.providerMetadata = providerMetadata;
        this.webBrowser = browser;
        this.clientId = OidcAuthentications.PUBLIC_CLIENT_ID;
        
    }

    @objid ("a20c02ed-6ca8-42ca-9209-737698a4ef36")
    public OidcBrowserFlowBuilder withClientSecret(String aClientSecret) {
        this.secret = aClientSecret;
        return this;
    }

    /**
     * To base the new authentication flow on the tokens of another authentication flows.
     * <p>
     * This allows to change the authentication process without loosing the tokens got from the initial authentication flow.
     * @param aPreviousAuth the initial authentication flow.
     * @return this builder
     */
    @objid ("02f6bad7-1f48-4aad-ae86-ba3265ae9cf4")
    public OidcBrowserFlowBuilder withPreviousAuth(IOidcAuthenticationFlow aPreviousAuth) {
        this.previousAuth = aPreviousAuth;
        return this;
    }

    @objid ("7f245d79-9036-4e8c-bbc1-b9ba94f6d72f")
    public OidcBrowserFlowBuilder withLoginHint(String loginHint) {
        this.loginHint = loginHint;
        return this;
    }

    @objid ("342c8916-b5c5-4107-b91a-cb5163afe042")
    public OidcBrowserFlowBuilder withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    @objid ("f27d751f-3ba3-42fb-8809-f260abe09f4b")
    public String getClientId() {
        return this.clientId;
    }

    @objid ("0babacf2-180e-4f81-af31-28336f51de12")
    public String getLoginHint() {
        return this.loginHint;
    }

    @objid ("9509220b-ba52-4a1e-acef-88d1d9acc77e")
    public IOidcAuthenticationFlow getPreviousAuth() {
        return this.previousAuth;
    }

    @objid ("2febad3b-fa44-4433-ab19-edd7addbce6f")
    public String getSecret() {
        return this.secret;
    }

    @objid ("05bf233c-2156-4025-b67e-e987e738b96a")
    public IOidcWebBrowser getWebBrowser() {
        return this.webBrowser;
    }

    /**
     * @return the build authentication flow
     * @throws IOException on error accessing the authentication server
     */
    @objid ("58797215-f6b3-4363-929b-434d559da6f1")
    public IOidcAuthenticationFlow build() throws IOException {
        OidcBrowserFlow browserFlow = new OidcBrowserFlow(this.providerMetadata, this);
        return new OidcRefreshTokenFlow(
                new ClientID(getClientId()),
                OidcAuthentications.makeSecret(getSecret()),
                this.providerMetadata,
                browserFlow,
                getPreviousAuth() != null ? getPreviousAuth().run() : null);
        
    }

}
