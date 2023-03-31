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
package org.modelio.vbasic.oidc.registration;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.oauth2.sdk.ErrorObject;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.client.ClientRegistrationErrorResponse;
import com.nimbusds.oauth2.sdk.client.ClientRegistrationResponse;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.openid.connect.sdk.OIDCScopeValue;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;
import com.nimbusds.openid.connect.sdk.rp.ApplicationType;
import com.nimbusds.openid.connect.sdk.rp.OIDCClientInformation;
import com.nimbusds.openid.connect.sdk.rp.OIDCClientInformationResponse;
import com.nimbusds.openid.connect.sdk.rp.OIDCClientMetadata;
import com.nimbusds.openid.connect.sdk.rp.OIDCClientRegistrationRequest;
import com.nimbusds.openid.connect.sdk.rp.OIDCClientRegistrationResponseParser;
import org.modelio.vbasic.oidc.flows.NimbusDumper;

@objid ("2c9f368e-971e-4a12-a2e6-732e35cd6ca9")
@Deprecated
class OidcClientRegistrator {
    @objid ("25f712d3-c918-4ae6-824b-ee435e589114")
    private OIDCClientMetadata createNewOidcClientMetadatas() {
        //String jsonMetadata = "{\"application_type\": \"web\",\"redirect_uris\": [\"http://client.example.com/auth_callback\"],\"response_types\": [\"code\"]}";
        OIDCClientMetadata metadata = new OIDCClientMetadata(); //.parse(JSONObjectUtils.parse(jsonMetadata));
        metadata.setApplicationType(ApplicationType.NATIVE);
        metadata.setEmailContacts(Arrays.asList("agent@modelio.com"));
        metadata.setName("Agent");
        //metadata.setOrganizationName("Modeliosoft");
        //metadata.setScope(new Scope(OIDCScopeValue.EMAIL, OIDCScopeValue.PROFILE));
        metadata.setScope(new Scope(OIDCScopeValue.OPENID, new Scope.Value("agent-client-scope")));
        metadata.setResponseTypes(Collections.singleton(ResponseType.IDTOKEN_TOKEN));
        return metadata;
    }

    @objid ("5a032877-c280-4d76-a4d5-1a9900150c67")
    private OIDCClientInformation registerClient(OIDCProviderMetadata providerMetadata, String registrationKey) throws IOException, ParseException {
        OIDCClientMetadata clientMetadata = createNewOidcClientMetadatas();
        
        // Make registration request
        OIDCClientRegistrationRequest registrationRequest = new OIDCClientRegistrationRequest(
                providerMetadata.getRegistrationEndpointURI(),
                clientMetadata,
                null /*new BearerAccessToken(registrationKey)*/);
        
        
        HTTPResponse httpResponse = registrationRequest.toHTTPRequest().send();
        
        // Parse and check response
        ClientRegistrationResponse registrationResponse = OIDCClientRegistrationResponseParser.parse(httpResponse);
        
        if (registrationResponse instanceof ClientRegistrationErrorResponse) {
            ErrorObject error = ((ClientRegistrationErrorResponse) registrationResponse).getErrorObject();
            throw new IOException(String.format("Client registration failed with HTTP %d %s : %s",
                    httpResponse.getStatusCode(),
                    httpResponse.getStatusMessage(),
                    httpResponse.getContent()));
        }
        
        // Store client information from OP
        OIDCClientInformation clientInformation = ((OIDCClientInformationResponse)registrationResponse).getOIDCClientInformation();
        return clientInformation;
    }

    @objid ("86b7be81-9f66-4a2d-99b1-5bd8621e4628")
    public void run(OIDCProviderMetadata providerMetadata, String registrationKey) {
        OIDCClientInformation infos;
        try {
            System.out.println("Registration ...");
            infos = registerClient(providerMetadata, registrationKey);
            System.out.println("Registration success:");
            System.out.println(NimbusDumper.prettyPrint(infos.toJSONObject()));
        
            System.out.println("Registration access token: \n"+NimbusDumper.prettyPrint(infos.getRegistrationAccessToken()));
            System.out.println("");
        
            try {
                JWT jwt = JWTParser.parse(infos.getRegistrationAccessToken().getValue());
                String s;
                s = NimbusDumper.prettyPrint(jwt.getHeader().toJSONObject());
                System.out.println("Registration access token JWT Header: \n"+s+"\n");
                s = NimbusDumper.prettyPrint(jwt.getJWTClaimsSet().toJSONObject());
                System.out.println("Registration access token JWT claims: \n"+s+"\n");
            } catch (java.text.ParseException e) {
                System.out.println("Registration access token is not JWT: "+e.toString());
            }
        } catch (ParseException | IOException e) {
            System.err.println("Registration Failed:");
            e.printStackTrace();
        }
        
    }

}
