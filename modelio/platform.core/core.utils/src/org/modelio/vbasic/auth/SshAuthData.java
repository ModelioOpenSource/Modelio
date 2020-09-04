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

package org.modelio.vbasic.auth;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("c422c9c7-3eed-4391-9471-7b9a2bca0034")
public class SshAuthData extends AuthData {
    @objid ("cda97502-0ce7-4987-b712-07ae7224590b")
    private static final String PASSPHRASE = "pass";

    @objid ("1c19f01b-51a8-416b-84e1-7711be62e795")
    private static final String PORTNUMBER = "portNumber";

    @objid ("59580dd9-79c6-457d-a0b6-d3e4eecfbfd6")
    private static final String PRIVATEKEY = "key";

    @objid ("65bfb2b0-ef25-4340-9676-a6de38c5296a")
    private static final String SAVE_PASSPHRASE = "savePass";

    @objid ("013244be-23b1-4d14-a75a-378d2fc96f39")
    private static final String SAVE_PRIVATEKEY = "saveKey";

    @objid ("f8e14bf1-7f5c-4448-9121-79c7774edb47")
    public static final String SCHEME_ID = "AUTH_SSH";

    @objid ("8b482c40-ff66-4eb5-a397-a293a95b9f7e")
    private static final String SSH_USER_NAME = "sshUser";

    @objid ("ba89d55c-21d9-4fdc-96c4-cc56e25404a1")
    private static final String SVN_USER_NAME = "svnUser";

    /**
     * Get the SSH server port to use.
     * 
     * @return the SSH server port to use, -1 if not defined.
     */
    @objid ("e37e74a8-c51b-405a-8b9f-9a317f8b7644")
    public int getPortNumber() {
        return Integer.parseInt(getProperty(PORTNUMBER, "-1"));
    }

    /**
     * @return the SSH private key as stored in the file.
     */
    @objid ("2e01f0ed-31d8-484c-9719-aaf895cfcf58")
    public char[] getPrivateKey() {
        String s = getData().get(PRIVATEKEY);
        if (s == null)
            return null;
        return s.toCharArray();
    }

    /**
     * @return the SSH key passphrase, may be null .
     */
    @objid ("0d2d66ff-3db6-4afe-b670-a0d982326ec4")
    public char[] getPrivateKeyPassword() {
        String s = getData().get(PASSPHRASE);
        if (s == null)
            return null;
        return s.toCharArray();
    }

    @objid ("46004f75-ed61-4c5e-a258-4d67bec9a169")
    @Override
    public String getSchemeId() {
        return SCHEME_ID;
    }

    /**
     * Get the SSH login on the remote machine.
     * @see #getSvnUserName()
     * 
     * @return the SSH user on the remote machine.
     */
    @objid ("f33403ec-ce33-482a-810a-20268c5e6851")
    public String getSshUserName() {
        return getData().get(SSH_USER_NAME);
    }

    /**
     * Get the SVN user name to use once logged on the remote machine.
     * <p>
     * Don't mix with the {@link #getSshUserName() SSH login}.
     * @see #getSshUserName()
     * 
     * @return The SVN repository user.
     */
    @objid ("34a18363-e064-4e46-9d4b-38d33ccc4e57")
    public String getSvnUserName() {
        return getData().get(SVN_USER_NAME);
    }

    @objid ("6c634a0d-4c2e-4288-af6a-57f6df662367")
    @Override
    public boolean isComplete() {
        return !getProperty(SSH_USER_NAME, "").isEmpty() && !getProperty(PRIVATEKEY, "").isEmpty();
    }

    /**
     * @see #serialize(boolean)
     * 
     * @return <code>true</code> if the SSH key password must be saved.
     */
    @objid ("e50eb10e-66da-4338-b430-7d591cdfd8fd")
    public boolean isPrivateKeyPassStored() {
        return Boolean.valueOf(getProperty(SAVE_PASSPHRASE, String.valueOf(false)));
    }

    /**
     * @see #serialize(boolean)
     * 
     * @return <code>true</code> if the SSH key must be saved.
     */
    @objid ("01dce46f-74d9-49cd-8f91-fb143c07e7d2")
    public boolean isPrivateKeyStored() {
        return Boolean.valueOf(getProperty(SAVE_PRIVATEKEY, String.valueOf(false)));
    }

    @objid ("bdb2dcbe-6379-4400-88c7-e2458a56e02c")
    @Override
    public Map<String, String> serialize(boolean forceCredentials) {
        HashMap<String, String> ret = new HashMap<>(getData());
        
        if (!forceCredentials) {
            if (!isPrivateKeyStored())
                ret.remove(PRIVATEKEY);
        
            if (!isPrivateKeyPassStored())
                ret.remove(PASSPHRASE);
        }
        return ret;
    }

    @objid ("242d6eab-6f2e-4f50-aea9-c38f03b2ffb3")
    public SshAuthData setPortNumber(String value) {
        getData().put(PORTNUMBER, value);
        return this;
    }

    @objid ("0bbe80ef-e9c3-4fe7-9d32-277d3dad8b5b")
    public SshAuthData setPortNumber(int value) {
        setPortNumber(String.valueOf(value));
        return this;
    }

    /**
     * Set the SSH private key value.
     * 
     * @param value the SSH private key
     * @return this instance
     */
    @objid ("7be7b7c8-9db2-4967-bd69-10ac8dab08c0")
    public SshAuthData setPrivateKey(String value) {
        getData().put(PRIVATEKEY, value);
        return this;
    }

    @objid ("76033326-92d7-47a4-827a-369ba686d0f6")
    public SshAuthData setPrivateKeyPassword(String value) {
        getData().put(PASSPHRASE, value);
        return this;
    }

    @objid ("0d314bd7-970d-4cb7-9616-ca9f1bd6214c")
    public SshAuthData setSshUser(String value) {
        getData().put(SSH_USER_NAME, value);
        return this;
    }

    @objid ("f2dc2271-8a3d-413b-9f31-f0dc7092389d")
    public SshAuthData setSvnUser(String value) {
        getData().put(SVN_USER_NAME, value);
        return this;
    }

    /**
     * @param store <code>true</code> if the SSH key must be saved.
     * @return this instance
     * @since 4.0
     */
    @objid ("893b076e-0813-4401-81f9-188934510872")
    public SshAuthData setPrivateKeyStored(boolean store) {
        getData().put(SAVE_PRIVATEKEY, String.valueOf(store));
        return this;
    }

    /**
     * @param store <code>true</code> if the SSH key password must be saved.
     * @return this instance
     * @since 4.0
     */
    @objid ("ad46d491-1579-4b3f-871e-dbdd382ec99d")
    public SshAuthData setPrivateKeyPassStored(boolean store) {
        getData().put(SAVE_PASSPHRASE, String.valueOf(store));
        return this;
    }

}
