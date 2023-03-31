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
package org.modelio.vbasic.oidc.flows;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.oauth2.sdk.token.Token;
import com.nimbusds.openid.connect.sdk.token.OIDCTokens;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;
import org.modelio.vbasic.log.Log;

@objid ("ee15a0d2-5322-4307-a940-264a68aebd01")
public class NimbusDumper {
    @objid ("32c82f64-d233-4ee9-8061-383b3ef9f6a1")
    public static String prettyPrint(JSONObject js) {
        if (js == null)
            return "<null>";
        return js.toString(new PrettyJsonStyle());
    }

    @objid ("157ed674-b4ca-4cd5-bd58-502a94e13b5a")
    public static String prettyPrint(Token js) {
        if (js == null)
            return "<null>";
        return js.toJSONObject().toString(new PrettyJsonStyle());
    }

    /**
     * Poors man slf4j
     * @param from the caller object, to print its class name
     * @param format a {@link String#format(String, Object...)} format
     * @param args the format arguments
     */
    @objid ("d111a27d-b63a-47a6-994b-a3f520b029e6")
    public static void logTrace(Object from, String format, Object... args) {
        Log.trace(from.getClass().getSimpleName()+": "+format, args);
    }

    @objid ("7791d56c-2911-4626-b3ff-c33618fa187b")
    public static String prettyPrint(JWT js, Boolean areYouSure) {
        if (areYouSure != Boolean.TRUE)
            throw new SecurityException("Sensitive values dumping denied.");
        
        if (js == null)
            return "<null>";
        
        try {
            String str = new JSONObject()
                    .appendField("Header", js.getHeader().toJSONObject())
                    .appendField("JWTClaimsSet", js.getJWTClaimsSet().toJSONObject())
                    .toString(new PrettyJsonStyle());
        
            return js.getClass().getSimpleName()+ " " +str;
        } catch (ParseException e) {
            return e.toString();
        }
        
    }

    @objid ("29dbea32-0a48-4933-a08b-66ddb84649b8")
    public static String dump(HTTPResponse r) {
        if (r==null)
            return "<null>";
        
        StringBuilder s = new StringBuilder();
        s.append("HTTP ").append(r.getStatusCode()).append(" ").append(r.getStatusMessage()).append(" [\n");
        for (Entry<String, List<String>> he : r.getHeaderMap().entrySet()) {
            for (String v : he.getValue()) {
                s.append("  ").append(he.getKey()).append("=").append(v).append("\n");
            }
        }
        s.append("\nContent:").append(r.getContent());
        s.append("\n]");
        return s.toString();
    }

    @objid ("827c0a32-cd30-4543-9187-8ec316f5f61e")
    public static String prettyPrint(Map<String, Object> jsonObject) {
        return JSONValue.toJSONString(jsonObject, new PrettyJsonStyle());
    }

    @objid ("ef4e3808-73c0-48be-8fb1-ed754622e628")
    static void debugDumpTokens(OIDCTokens tokens, Boolean areYouSure) {
        if (areYouSure != Boolean.TRUE)
            throw new SecurityException("Sensitive values dumping denied.");
        
        
        Log.trace("Dumping OIDC tokens...");
        String idTokenDump;
        try {
            idTokenDump = NimbusDumper.prettyPrint(tokens.getIDToken().getJWTClaimsSet().toJSONObject());
        } catch (ParseException e) {
            idTokenDump = e.toString();
        }
        Log.trace("id tokens = "+((tokens.getIDToken()==null) ? "none" : idTokenDump));
        
        AccessToken accessToken = tokens.getAccessToken();
        Log.trace("access token type = "+accessToken.getType());
        Log.trace("access token type = "+accessToken.getLifetime());
        //Log.trace("access token = "+ NimbusDumper.prettyPrint(accessToken));
        //Log.trace("bearer token = "+ NimbusDumper.prettyPrint(tokens.getBearerAccessToken()));
        
        try {
            JWT jwt = JWTParser.parse(accessToken.getValue());
        
            JWTClaimsSet claimset = jwt.getJWTClaimsSet();
            Log.trace("claimset = "+NimbusDumper.prettyPrint(claimset.toJSONObject()));
            Log.trace("claimset expires: "+claimset.getExpirationTime());
            Log.trace("claimset subject: "+claimset.getSubject());
            Log.trace("claimset issue time: "+claimset.getIssueTime());
        
        } catch (java.text.ParseException e) {
            Log.trace("Access token is not JWTClaimset");
            e.printStackTrace();
        }
        
    }

    @objid ("11022f16-1ac1-4375-8e05-9b4cba14efc6")
    private static class PrettyJsonStyle extends JSONStyle {
        @objid ("bd3eab8a-ad74-4f2a-a8cb-5debfc9ba25b")
        private final String step = "  ";

        @objid ("a3aed286-af52-4824-a8a9-75ba3f88166a")
        private String indent = "";

        @objid ("28aba1fc-c829-4ce9-b350-0b9b8762f760")
        public  PrettyJsonStyle() {
            super(FLAG_PROTECT_4WEB);
        }

        @objid ("3b3473a6-4127-4273-99f4-6b7425720710")
        private void breakLine(Appendable out) throws IOException {
            out.append("\n");
            out.append(this.indent);
            
        }

        @objid ("3d920cb6-8f00-408e-9ae7-d44a5cafc085")
        @Override
        public void arrayStart(Appendable out) throws IOException {
            super.arrayStart(out);
            indent();
            
        }

        @objid ("b4556635-ae9b-4523-b7b0-ee2f93187388")
        @Override
        public boolean indent() {
            this.indent += this.step;
            return true;
        }

        @objid ("4a689690-eeda-4454-8fca-7c56ea55a912")
        @Override
        public void arrayStop(Appendable out) throws IOException {
            super.arrayStop(out);
            unindent();
            
        }

        @objid ("e2b43a89-704d-494d-87af-a9e8eb1603a8")
        private void unindent() {
            this.indent = this.indent.substring(2);
        }

        @objid ("9810899c-5fda-41bd-9997-4e73c1a7f643")
        @Override
        public void arrayfirstObject(Appendable out) throws IOException {
            breakLine(out);
            super.arrayfirstObject(out);
            
        }

        @objid ("fd8ff2b3-177b-4b83-acb7-ee74b17312a7")
        @Override
        public void arrayNextElm(Appendable out) throws IOException {
            super.arrayNextElm(out);
            breakLine(out);
            
        }

        @objid ("23a65644-9e02-4d2c-910c-0af747a309aa")
        @Override
        public void objectElmStop(Appendable out) throws IOException {
            super.objectElmStop(out);
        }

        @objid ("5415a89b-2dd4-4d47-8720-458cde27a3ed")
        @Override
        public void objectFirstStart(Appendable out) throws IOException {
            super.objectFirstStart(out);
        }

        @objid ("2ba2be90-a685-4da4-aaab-88e9a013f66f")
        @Override
        public void objectNext(Appendable out) throws IOException {
            super.objectNext(out);
            breakLine(out);
            
        }

        @objid ("908824e1-0aac-44f9-940c-3e1fd0c5476e")
        @Override
        public void objectStart(Appendable out) throws IOException {
            super.objectStart(out);
            indent();
            
        }

        @objid ("367aa2fa-0e74-4f98-bffb-4086edbb9239")
        @Override
        public void objectStop(Appendable out) throws IOException {
            super.objectStop(out);
            unindent();
            
        }

    }

}
